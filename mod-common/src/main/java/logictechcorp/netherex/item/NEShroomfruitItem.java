package logictechcorp.netherex.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class NEShroomfruitItem extends ItemNameBlockItem
{
    private final Supplier<MobEffectInstance> mobEffectInstance;

    public NEShroomfruitItem(Block block, Supplier<MobEffectInstance> inMobEffectInstance, Properties properties)
    {
        super(block, properties);
        mobEffectInstance = inMobEffectInstance;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
    {
        if (!level.isClientSide() && livingEntity.hasEffect(MobEffects.GLOWING))
        {
            livingEntity.addEffect(mobEffectInstance.get());
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
