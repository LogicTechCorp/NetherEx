package logictechcorp.netherex.entity.monster;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class NEWisp extends PathfinderMob implements NeutralMob, FlyingAnimal, GeoEntity
{
    private static final EntityDataAccessor<Long> DATA_ANGER_END_TIME = SynchedEntityData.defineId(NEWisp.class, EntityDataSerializers.LONG);
    private static final UniformInt PERSISTENT_ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
    private @Nullable EntityReference<LivingEntity> persistentAngerTarget;

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    public NEWisp(EntityType<? extends PathfinderMob> entityType, Level level)
    {
        super(entityType, level);
        moveControl = new FlyingMoveControl(this, 20, true);
        xpReward = Enemy.XP_REWARD_MEDIUM;
    }

    public static AttributeSupplier createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 4.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.FLYING_SPEED, 0.6d)
                .add(Attributes.ATTACK_DAMAGE, 2.0d)
                .build();
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
        flyingPathNavigation.setRequiredPathLength(16.0f);
        return flyingPathNavigation;
    }


    public static boolean checkWispSpawnRules(EntityType<NEWisp> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random)
    {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(DATA_ANGER_END_TIME, -1L);
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2d, false));
        goalSelector.addGoal(2, new NEWispWanderGoal());
        goalSelector.addGoal(3, new FloatGoal(this));

        targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>("controller", 0, (animationState ->
        {

            String animationName = "animation.wisp.idle";
            animationState.controller().setAnimation(RawAnimation.begin().thenLoop(animationName));
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output)
    {
        super.addAdditionalSaveData(output);
        addPersistentAngerSaveData(output);
    }

    @Override
    public void readAdditionalSaveData(ValueInput input)
    {
        super.readAdditionalSaveData(input);
        readPersistentAngerSaveData(level(), input);
    }

    @Override
    public void startPersistentAngerTimer()
    {
        setTimeToRemainAngry(PERSISTENT_ANGER_TIME_RANGE.sample(random));
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos)
    {

    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block)
    {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return animatableInstanceCache;
    }

    @Override
    public void setPersistentAngerEndTime(long endTime)
    {
        entityData.set(DATA_ANGER_END_TIME, endTime);
    }

    @Override
    public long getPersistentAngerEndTime()
    {
        return entityData.get(DATA_ANGER_END_TIME);
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getPersistentAngerTarget()
    {
        return persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable EntityReference<LivingEntity> inPersistentAngerTarget)
    {
        persistentAngerTarget = inPersistentAngerTarget;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level)
    {
        return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    public boolean isFlying()
    {
        return !onGround();
    }

    @Override
    public boolean isFlapping()
    {
        return isFlying() && tickCount % 2 == 0;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    class NEWispWanderGoal extends Goal
    {
        NEWispWanderGoal()
        {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse()
        {
            return navigation.isDone() && random.nextInt(10) == 0;
        }

        public boolean canContinueToUse()
        {
            return navigation.isInProgress();

        }

        public void start()
        {
            Vec3 pos = findPos();

            if (pos != null)
            {
                navigation.moveTo(navigation.createPath(BlockPos.containing(pos), 1), 1.0d);
            }
        }

        private Vec3 findPos()
        {
            Vec3 viewVector = getViewVector(0.0f);
            Vec3 randomHoverPos = HoverRandomPos.getPos(NEWisp.this, 8, 7, viewVector.x, viewVector.z, ((float) Math.PI / 2.0f), 3, 1);

            return randomHoverPos != null ? randomHoverPos : AirAndWaterRandomPos.getPos(NEWisp.this, 8, 4, -2, viewVector.x, viewVector.z, ((float) Math.PI / 2.0f));
        }
    }
}
