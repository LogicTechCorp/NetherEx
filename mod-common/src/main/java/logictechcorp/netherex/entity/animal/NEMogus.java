package logictechcorp.netherex.entity.animal;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.util.GeckoLibUtil;
import logictechcorp.netherex.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.variant.VariantUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NEMogus extends Animal implements GeoEntity
{
    private static final EntityDataAccessor<Holder<NEMogusVariant>> VARIANT_ID = SynchedEntityData.defineId(NEMogus.class, NetherExEntityDataSerializers.MOGUS_VARIANT);

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    public NEMogus(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
        setPathfindingMalus(PathType.FIRE, -1.0F);
        setPathfindingMalus(PathType.LAVA, -1.0F);
        setPathfindingMalus(PathType.WATER, -1.0F);
    }

    public static AttributeSupplier createAttributes()
    {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 4.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.35d)
                .build();
    }

    public static boolean checkMogusSpawnRules(EntityType<NEMogus> entityType, LevelAccessor level, EntitySpawnReason entitySpawnReason, BlockPos pos, RandomSource random)
    {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(VARIANT_ID, VariantUtils.getDefaultOrAny(registryAccess(), NetherExMogusVariants.BROWN));
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.25d));
        goalSelector.addGoal(2, new FollowParentGoal(this, 1.1d));
        goalSelector.addGoal(3, new BreedGoal(this, 1.0d));
        goalSelector.addGoal(4, new TemptGoal(this, 1.2d, stack -> stack.is(NetherExItemTags.MOGUS_FOOD), false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>("controller", 0, (animationState ->
        {
            String animationName;

            if (animationState.isMoving())
            {
                animationName = "animation.mogus.walk";
            }
            else
            {
                animationName = "animation.mogus.idle";
            }

            animationState.controller().setAnimation(RawAnimation.begin().thenLoop(animationName));
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader level)
    {
        return level.isUnobstructed(this) && !level.containsAnyLiquid(getBoundingBox());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, @Nullable SpawnGroupData spawnGroupData)
    {
        spawnGroupData = super.finalizeSpawn(levelAccessor, difficultyInstance, entitySpawnReason, spawnGroupData);

        Holder<NEMogusVariant> mogusVariant = NetherExMogusVariants.getRandomSpawnVariant(registryAccess(), random);
        setVariant(mogusVariant);
        return spawnGroupData;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output)
    {
        super.addAdditionalSaveData(output);
        getVariant().unwrapKey().ifPresent(variantResourceKey -> output.putString("variant", variantResourceKey.identifier().toString()));
    }

    @Override
    public void readAdditionalSaveData(ValueInput input)
    {
        super.readAdditionalSaveData(input);
        Optional.ofNullable(Identifier.tryParse(input.getStringOr("variant", "")))
                .map(location -> ResourceKey.create(NetherExRegistries.Keys.MOGUS_VARIANT, location))
                .flatMap(key -> registryAccess().lookupOrThrow(NetherExRegistries.Keys.MOGUS_VARIANT).get(key))
                .ifPresent(this::setVariant);
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(NetherExItemTags.MOGUS_FOOD);
    }

    public void setVariant(Holder<NEMogusVariant> variant)
    {
        entityData.set(VARIANT_ID, variant);
    }

    public Holder<NEMogusVariant> getVariant()
    {
        return entityData.get(VARIANT_ID);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return animatableInstanceCache;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob otherParent)
    {
        NEMogus mogus = NetherExEntityTypes.MOGUS.get().create(serverLevel, EntitySpawnReason.BREEDING);

        if (mogus != null)
        {
            Holder<NEMogusVariant> variant;

            if (getVariant() == ((NEMogus) otherParent).getVariant())
            {
                variant = getVariant();
            }
            else
            {
                variant = random.nextBoolean() ? getVariant() : ((NEMogus) otherParent).getVariant();
            }

            mogus.setVariant(variant);
        }

        return mogus;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level)
    {
        return level.getBlockState(pos.below()).is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT) ? 10.0f : 0.0f;
    }
}