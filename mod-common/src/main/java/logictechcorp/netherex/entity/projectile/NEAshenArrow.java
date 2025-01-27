package logictechcorp.netherex.entity.projectile;

import logictechcorp.netherex.registry.NetherExEntityTypes;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class NEAshenArrow extends AbstractArrow
{
    public NEAshenArrow(EntityType<? extends AbstractArrow> entityType, Level level)
    {
        super(entityType, level);
    }

    public NEAshenArrow(double x, double y, double z, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon)
    {
        super(NetherExEntityTypes.ASHEN_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    public NEAshenArrow(LivingEntity owner, Level level, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon)
    {
        super(NetherExEntityTypes.ASHEN_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (level().isClientSide() && !isInGround())
        {
            level().addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0d, 0.0d, 0.0d);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target)
    {
        super.doPostHurtEffects(target);
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(NetherExItems.ASHEN_ARROW.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem()
    {
        return new ItemStack(NetherExItems.ASHEN_ARROW.get());
    }
}