package logictechcorp.netherex.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import logictechcorp.netherex.item.component.NEStructureTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.CompassAngleState;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CompassAngleState.class)
public abstract class NECompassAngleStateMixin
{
    @WrapOperation(
            method = "calculate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/item/properties/numeric/CompassAngleState$CompassTarget;get(Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/core/GlobalPos;"
            )
    )
    private GlobalPos getGlobalPos(CompassAngleState.CompassTarget instance, ClientLevel clientLevel, ItemStack stack, Entity entity, Operation<GlobalPos> original)
    {
        NEStructureTracker structureTracker = stack.get(NetherExDataComponents.STRUCTURE_TRACKER.get());

        if (structureTracker != null)
        {
            return structureTracker.target();
        }

        return original.call(instance, clientLevel, stack, entity);
    }
}
