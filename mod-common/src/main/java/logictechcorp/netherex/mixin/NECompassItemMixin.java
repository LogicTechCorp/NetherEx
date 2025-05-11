package logictechcorp.netherex.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CompassItem.class)
public class NECompassItemMixin
{
    @Inject(
            method = "useOn",
            at = @At("HEAD")
    )
    @Unique
    public void netherEx$useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> callback)
    {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();

        if (level.getBlockState(clickedPos).is(Blocks.LODESTONE))
        {
            if (stack.has(NetherExDataComponents.GLOBAL_POS_TRACKER.get()))
            {
                stack.remove(NetherExDataComponents.GLOBAL_POS_TRACKER.get());
            }
        }
    }

    @Unique
    @WrapMethod(method = "isFoil")
    public boolean netherEx$isFoil(ItemStack stack, Operation<Boolean> original)
    {
        return stack.has(NetherExDataComponents.GLOBAL_POS_TRACKER.get()) || original.call(stack);
    }

    @Unique
    @WrapMethod(method = "getName")
    public Component netherEx$getName(ItemStack stack, Operation<Component> original)
    {
        return stack.has(NetherExDataComponents.GLOBAL_POS_TRACKER.get()) ? Component.translatable("item." + NetherExConstants.MOD_ID + ".tracker_compass") : original.call(stack);
    }
}
