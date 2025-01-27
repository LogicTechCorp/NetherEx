package logictechcorp.netherex.item;

import logictechcorp.netherex.entity.projectile.NEAshenArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NEAshenArrowItem extends ArrowItem
{
    public NEAshenArrowItem(Properties properties)
    {
        super(properties);
    }

    @NotNull
    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon)
    {
        return new NEAshenArrow(shooter, level, ammo.copyWithCount(1), weapon);
    }
}