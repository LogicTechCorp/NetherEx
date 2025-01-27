package logictechcorp.netherex.entity.animal;

import logictechcorp.netherex.registry.NetherExEntityDataSerializers;
import logictechcorp.netherex.registry.NetherExFlaemothVariants;
import logictechcorp.netherex.registry.NetherExItemTags;
import logictechcorp.netherex.registry.NetherExRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class NEFlaemoth extends Animal implements FlyingAnimal, VariantHolder<Holder<NEFlaemothVariant>>, GeoEntity
{
    private static final EntityDataAccessor<Holder<NEFlaemothVariant>> VARIANT_ID = SynchedEntityData.defineId(NEFlaemoth.class, NetherExEntityDataSerializers.FLAEMOTH_VARIANT);

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    private int underWaterTicks;

    public NEFlaemoth(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
        moveControl = new FlyingMoveControl(this, 20, true);
        setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        setPathfindingMalus(PathType.LAVA, -1.0F);
        setPathfindingMalus(PathType.WATER, -1.0F);
    }

    public static AttributeSupplier createAttributes()
    {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 10.0d)
                .add(Attributes.FLYING_SPEED, 0.6d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.ATTACK_DAMAGE, 2.0d)
                .add(Attributes.FOLLOW_RANGE, 48.0d)
                .build();
    }

    public static boolean checkFlaemothSpawnRules(EntityType<NEFlaemoth> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random)
    {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        Registry<NEFlaemothVariant> registry = registryAccess().lookupOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);
        builder.define(VARIANT_ID, registry.get(NetherExFlaemothVariants.CRIMSON).or(registry::getAny).orElseThrow());
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new TemptGoal(this, 1.15d, (stack) -> stack.is(NetherExItemTags.FLAEMOTH_FOOD), false));
        goalSelector.addGoal(2, new PanicGoal(this, 1.5d));
        goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0d));
        goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level)
    {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level)
        {
            @Override
            public boolean isStableDestination(BlockPos pos)
            {
                return !level.getBlockState(pos.below()).isAir();
            }
        };

        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(false);
        return flyingPathNavigation;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, (animationState ->
        {
            AnimationController<NEFlaemoth> animationController = animationState.getController();

            String animationName;

            if (animationState.getAnimatable().isFlying())
            {
                animationName = "animation.flaemoth.fly";
            }
            else
            {
                animationName = "animation.flaemoth.idle";
            }

            animationController.setAnimation(RawAnimation.begin().thenLoop(animationName));
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason spawnReason, @Nullable SpawnGroupData spawnGroupData)
    {
        spawnGroupData = super.finalizeSpawn(levelAccessor, difficultyInstance, spawnReason, spawnGroupData);

        Holder<Biome> biome = levelAccessor.getBiome(blockPosition());
        Holder<NEFlaemothVariant> flaemothVariantHolder;

        if (spawnReason == EntitySpawnReason.NATURAL)
        {
            flaemothVariantHolder = NetherExFlaemothVariants.getBiomeSpawnVariant(registryAccess(), biome, random);
        }
        else
        {
            flaemothVariantHolder = NetherExFlaemothVariants.getRandomSpawnVariant(registryAccess(), random);
        }

        setVariant(flaemothVariantHolder);
        return spawnGroupData;
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
                .map(location -> ResourceKey.create(NetherExRegistries.Keys.FLAEMOTH_VARIANT, location))
                .flatMap(key -> registryAccess().lookupOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT).get(key))
                .ifPresent(this::setVariant);
    }

    @Override
    protected void customServerAiStep(ServerLevel level)
    {
        if (isInWaterOrBubble())
        {
            underWaterTicks++;
        }
        else
        {
            underWaterTicks = 0;
        }

        if (underWaterTicks > 20)
        {
            hurtServer(level, damageSources().drown(), 1.0F);
        }
    }

    @Override
    protected void checkFallDamage(double distance, boolean onGround, BlockState state, BlockPos pos)
    {

    }

    @Override
    protected void jumpInLiquid(TagKey<Fluid> fluidTagKey)
    {
        setDeltaMovement(getDeltaMovement().add(0.0D, 0.01D, 0.0D));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return animatableInstanceCache;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob)
    {
        return null;
    }

    @Override
    public Holder<NEFlaemothVariant> getVariant()
    {
        return entityData.get(VARIANT_ID);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader levelReader)
    {
        return levelReader.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    public boolean isFlying()
    {
        return !onGround();
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(NetherExItemTags.FLAEMOTH_FOOD);
    }

    @Override
    public void setVariant(Holder<NEFlaemothVariant> variant)
    {
        entityData.set(VARIANT_ID, variant);
    }
}