package logictechcorp.netherex.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import logictechcorp.netherex.registry.NetherExBlockEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractFurnaceBlockEntity.class)
public class NEAbstractFurnaceBlockEntityMixin
{
    @ModifyReturnValue(
            method = "getTotalCookTime",
            at = @At("RETURN")
    )
    private static int getTotalCookTime(int original, @Local(argsOnly = true) ServerLevel level, @Local(argsOnly = true) AbstractFurnaceBlockEntity furnace)
    {
        if (furnace.getType() == NetherExBlockEntityTypes.KILN.get())
        {
            if (level.dimension() == Level.NETHER)
            {
                return original / 2;
            }
        }

        return original;
    }
}
