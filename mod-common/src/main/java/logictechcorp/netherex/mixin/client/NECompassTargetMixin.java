package logictechcorp.netherex.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import logictechcorp.netherex.item.component.NEGlobalPosTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(targets = "net/minecraft/client/renderer/item/properties/numeric/CompassAngleState$CompassTarget$1")
public abstract class NECompassTargetMixin
{
    @WrapMethod(
            method = "get(Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/core/GlobalPos;"
    )
    @Unique
    private GlobalPos netherEx$get(ClientLevel level, ItemStack stack, Entity entity, Operation<GlobalPos> original)
    {
        NEGlobalPosTracker globalPosTracker = stack.get(NetherExDataComponents.GLOBAL_POS_TRACKER.get());

        if (globalPosTracker != null)
        {
            return globalPosTracker.target().orElse(null);
        }

        return original.call(level, stack, entity);
    }
}
