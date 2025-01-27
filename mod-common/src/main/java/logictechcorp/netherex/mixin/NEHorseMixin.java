package logictechcorp.netherex.mixin;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.item.NENetheriteHorseArmorItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Horse.class)
public abstract class NEHorseMixin extends AbstractHorse implements VariantHolder<Variant>
{
    @Unique
    private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new AttributeModifier(NetherExConstants.resource("knockback_resistance"), 0.4f, AttributeModifier.Operation.ADD_VALUE);

    protected NEHorseMixin(EntityType<? extends AbstractHorse> entityType, Level level)
    {
        super(entityType, level);
    }

    @Override
    public void setBodyArmorItem(ItemStack stack)
    {
        super.setBodyArmorItem(stack);

        if (!level().isClientSide())
        {
            getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(KNOCKBACK_RESISTANCE_MODIFIER);

            if (stack.getItem() instanceof NENetheriteHorseArmorItem)
            {
                getAttribute(Attributes.KNOCKBACK_RESISTANCE).addTransientModifier(KNOCKBACK_RESISTANCE_MODIFIER);
            }
        }
    }
}