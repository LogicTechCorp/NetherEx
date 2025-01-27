package logictechcorp.netherex.entity.monster;

import logictechcorp.netherex.entity.goal.RandomFloatAroundGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NEWisp extends FlyingMob implements Enemy, GeoEntity
{
    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    public NEWisp(EntityType<? extends FlyingMob> entityType, Level level)
    {
        super(entityType, level);
        moveControl = new NEWispMoveControl(this);
        xpReward = XP_REWARD_MEDIUM;
    }

    public static AttributeSupplier createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 4.0f)
                .add(Attributes.MOVEMENT_SPEED, 1.0f)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .build();
    }

    public static boolean checkWispSpawnRules(EntityType<NEWisp> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random)
    {
        return true;
    }

    @Override
    protected void registerGoals()
    {
        goalSelector.addGoal(5, new RandomFloatAroundGoal(this));

        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_390241_, p_390242_) -> Math.abs(p_390241_.getY() - this.getY()) <= (double) 4.0F));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, (animationState ->
        {
            AnimationController<NEWisp> animationController = animationState.getController();

            String animationName = "animation.wisp.idle";
            animationController.setAnimation(RawAnimation.begin().thenLoop(animationName));
            return PlayState.CONTINUE;
        })));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return animatableInstanceCache;
    }

    static class NEWispMoveControl extends MoveControl
    {
        private final NEWisp wisp;
        private int floatDuration;

        public NEWispMoveControl(NEWisp inWisp)
        {
            super(inWisp);
            wisp = inWisp;
        }

        @Override
        public void tick()
        {
            if (operation == Operation.MOVE_TO && floatDuration-- <= 0)
            {
                floatDuration = floatDuration + wisp.getRandom().nextInt(5) + 2;
                Vec3 vec3 = new Vec3(wantedX - wisp.getX(), wantedY - wisp.getY(), wantedZ - wisp.getZ());
                double distance = vec3.length();
                vec3 = vec3.normalize();

                if (canReach(vec3, Mth.ceil(distance)))
                {
                    wisp.setDeltaMovement(wisp.getDeltaMovement().add(vec3.scale(0.1)));
                }
                else
                {
                    operation = Operation.WAIT;
                }
            }
        }

        private boolean canReach(Vec3 pos, int length)
        {
            AABB boundingBox = wisp.getBoundingBox();

            for (int i = 1; i < length; i++)
            {
                boundingBox = boundingBox.move(pos);

                if (!wisp.level().noCollision(wisp, boundingBox))
                {
                    return false;
                }
            }

            return true;
        }
    }
}
