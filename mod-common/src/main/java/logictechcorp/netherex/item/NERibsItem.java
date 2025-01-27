package logictechcorp.netherex.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class NERibsItem extends Item
{
    public NERibsItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity)
    {
        return 64;
    }
}
