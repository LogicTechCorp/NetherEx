package logictechcorp.netherex.entity.animal;

import logictechcorp.netherex.registry.NetherExBlockTags;
import logictechcorp.netherex.registry.NetherExEntityDataSerializers;
import logictechcorp.netherex.registry.NetherExRegistries;
import logictechcorp.netherex.registry.NetherExSalamanderVariants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;
import java.util.UUID;

public class NESalamander extends TamableAnimal implements NeutralMob, VariantHolder<Holder<NESalamanderVariant>>, GeoEntity
{
    private static final TargetingConditions.Selector PREY_SELECTOR = (livingEntity, serverLevel) ->
    {
        EntityType<?> entityType = livingEntity.getType();
        return entityType == EntityType.SPIDER || entityType == EntityType.BEE || entityType == EntityType.SILVERFISH || entityType == EntityType.SLIME;
    };
    private static final EntityDataAccessor<Holder<NESalamanderVariant>> VARIANT_ID = SynchedEntityData.defineId(NESalamander.class, NetherExEntityDataSerializers.SALAMANDER_VARIANT);
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(NESalamander.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(30, 60);

    private UUID persistentAngerTarget;

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    public NESalamander(EntityType<? extends TamableAnimal> entityType, Level level)
    {
        super(entityType, level);
        setPathfindingMalus(PathType.WATER, -1.0f);
        setPathfindingMalus(PathType.LAVA, 0.0f);
        setPathfindingMalus(PathType.DANGER_FIRE, 0.0f);
        setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0f);
    }

    public static AttributeSupplier createAttributes()
    {
        return TamableAnimal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 12.0d)
                .add(Attributes.ATTACK_DAMAGE, 3.0d)
                .add(Attributes.ATTACK_SPEED, 3.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.285f)
                .build();
    }

    public static boolean checkSalamanderSpawnRules(EntityType<NESalamander> entityType, LevelAccessor level, EntitySpawnReason entitySpawnReason, BlockPos pos, RandomSource random)
    {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        Registry<NESalamanderVariant> registry = registryAccess().lookupOrThrow(NetherExRegistries.Keys.SALAMANDER_VARIANT);
        builder.define(VARIANT_ID, registry.get(NetherExSalamanderVariants.ORANGE).or(registry::getAny).orElseThrow());
        builder.define(REMAINING_ANGER_TIME, 0);
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4f));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0d, true));
        goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0d, 10.0f, 2.0f));
        goalSelector.addGoal(5, new BreedGoal(this, 1.0d));
        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0f));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        targetSelector.addGoal(0, new OwnerHurtByTargetGoal(this));
        targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        targetSelector.addGoal(4, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller_idle_walk", 0, (animationState ->
        {
            AnimationController<NESalamander> animationController = animationState.getController();
            RawAnimation animation = null;

            if (animationState.isMoving())
            {
                animation = RawAnimation.begin().thenPlay("animation.salamander.walk");
            }
            else
            {
                if (random.nextInt(5) == 0)
                {
                    animation = RawAnimation.begin().thenPlay("animation.salamander.idle.gnash");
                }
            }

            animationController.setAnimation(animation);
            return PlayState.CONTINUE;
        })));

        controllerRegistrar.add(new AnimationController<>(this, "controller_sit_stand", 0, (animationState ->
        {
            AnimationController<NESalamander> animationController = animationState.getController();
            RawAnimation animation = null;

            if (isTame())
            {
                if (isInSittingPose())
                {
                    animation = RawAnimation.begin().thenPlayAndHold("animation.salamander.sit");
                }
                else
                {
                    animation = RawAnimation.begin().thenPlay("animation.salamander.stand");
                }
            }

            animationController.setAnimation(animation);
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level)
    {
        return level.isUnobstructed(this) && !level.containsAnyLiquid(getBoundingBox());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason spawnReason, SpawnGroupData spawnGroupData)
    {
        Holder<NESalamanderVariant> salamanderVariant = NetherExSalamanderVariants.getRandomSpawnVariant(registryAccess(), random);
        setVariant(salamanderVariant);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnReason, spawnGroupData);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag)
    {
        super.addAdditionalSaveData(compoundTag);
        getVariant().unwrapKey().ifPresent(variantResourceKey -> compoundTag.putString("variant", variantResourceKey.location().toString()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag)
    {
        super.readAdditionalSaveData(compoundTag);
        Optional.ofNullable(ResourceLocation.tryParse(compoundTag.getString("variant")))
                .map(location -> ResourceKey.create(NetherExRegistries.Keys.SALAMANDER_VARIANT, location))
                .flatMap(key -> registryAccess().lookupOrThrow(NetherExRegistries.Keys.SALAMANDER_VARIANT).get(key))
                .ifPresent(this::setVariant);
    }

    @Override
    public void startPersistentAngerTimer()
    {
        setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME_RANGE.sample(random));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack heldStack = player.getItemInHand(hand);

        if (isTame())
        {
            if (isFood(heldStack) && getHealth() < getMaxHealth())
            {
                usePlayerItem(player, hand, heldStack);
                FoodProperties foodProperties = heldStack.get(DataComponents.FOOD);
                float nutrition = foodProperties != null ? (float) foodProperties.nutrition() : 1.0f;
                heal(2.0f * nutrition);
                return InteractionResult.SUCCESS;
            }
            else
            {
                InteractionResult interactionResult = super.mobInteract(player, hand);

                if (!interactionResult.consumesAction() && isOwnedBy(player))
                {
                    setOrderedToSit(!isOrderedToSit());
                    jumping = false;
                    navigation.stop();
                    setTarget(null);
                    return InteractionResult.SUCCESS.withoutItem();
                }
                else
                {
                    return interactionResult;
                }

            }
        }
        else if (!level().isClientSide && heldStack.is(Items.BONE) && !isAngry())
        {
            heldStack.consume(1, player);
            tryToTame(player);
            return InteractionResult.SUCCESS_SERVER;
        }
        else
        {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float amount)
    {
        if (isInvulnerableTo(serverLevel, damageSource))
        {
            return false;
        }
        else
        {
            setOrderedToSit(false);

            Entity entity = damageSource.getEntity();

            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow))
            {
                amount = (amount + 1.0f) / 2.0f;
            }

            return super.hurtServer(serverLevel, damageSource, amount);
        }
    }

    private void tryToTame(Player player)
    {
        if (random.nextInt(3) == 0)
        {
            tame(player);
            navigation.stop();
            setTarget(null);
            setOrderedToSit(true);
            level().broadcastEntityEvent(this, (byte) 7);
        }
        else
        {
            level().broadcastEntityEvent(this, (byte) 6);
        }
    }

    @Override
    public boolean canMate(Animal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!isTame())
        {
            return false;
        }
        else if (!(otherAnimal instanceof NESalamander salamander))
        {
            return false;
        }
        else
        {

            if (!salamander.isTame())
            {
                return false;
            }
            else if (salamander.isInSittingPose())
            {
                return false;
            }
            else
            {
                return isInLove() && salamander.isInLove();
            }
        }
    }

    @Override
    public boolean canBeLeashed()
    {
        return !isAngry() && super.canBeLeashed();
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(ItemTags.FISHES);
    }

    @Override
    public boolean isOnFire()
    {
        return false;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner)
    {
        if (!(target instanceof Creeper) && !(target instanceof Ghast))
        {
            if (target instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) target))
            {
                return false;
            }
            else if (target instanceof AbstractHorse && ((AbstractHorse) target).isTamed())
            {
                return false;
            }
            else
            {
                return !(target instanceof TamableAnimal) || !((TamableAnimal) target).isTame();
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public Holder<NESalamanderVariant> getVariant()
    {
        return entityData.get(VARIANT_ID);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return animatableInstanceCache;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent)
    {
        return null;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level)
    {
        return level.getBlockState(pos.below()).is(NetherExBlockTags.NETHERRACK) ? 10.0f : 0.0f;
    }

    @Override
    public UUID getPersistentAngerTarget()
    {
        return persistentAngerTarget;
    }

    @Override
    public int getRemainingPersistentAngerTime()
    {
        return entityData.get(REMAINING_ANGER_TIME);
    }

    @Override
    public void setVariant(Holder<NESalamanderVariant> variant)
    {
        entityData.set(VARIANT_ID, variant);
    }

    @Override
    public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime)
    {
        entityData.set(REMAINING_ANGER_TIME, remainingPersistentAngerTime);
    }

    @Override
    public void setPersistentAngerTarget(UUID inPersistentAngerTarget)
    {
        persistentAngerTarget = inPersistentAngerTarget;
    }

    @Override
    public void setTame(boolean tamed, boolean applyTamingSideEffects)
    {
        super.setTame(tamed, applyTamingSideEffects);

        if (tamed)
        {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(24.0d);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.5d);
            setHealth(24.0f);
        }
        else
        {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0d);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0d);
        }
    }
}
