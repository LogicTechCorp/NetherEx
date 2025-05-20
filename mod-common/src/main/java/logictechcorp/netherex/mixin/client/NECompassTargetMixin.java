package logictechcorp.netherex.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import logictechcorp.netherex.item.component.NEStructureTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/client/renderer/item/properties/numeric/CompassAngleState$CompassTarget$1")
public abstract class NECompassTargetMixin
{
    @WrapMethod(
            method = "get(Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/core/GlobalPos;"
    )
    private GlobalPos get(ClientLevel level, ItemStack stack, Entity entity, Operation<GlobalPos> original)
    {
        NEStructureTracker structureTracker = stack.get(NetherExDataComponents.STRUCTURE_TRACKER.get());

        if (structureTracker != null)
        {
            return structureTracker.target();
        }

        return original.call(level, stack, entity);
    }
}
