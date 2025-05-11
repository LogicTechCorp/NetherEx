package logictechcorp.netherex.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import logictechcorp.netherex.item.component.NEGlobalPosTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.throwables.MixinApplyError;

@Mixin(ItemProperties.class)
public abstract class NEItemPropertiesMixin
{
    @WrapOperation(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/item/ItemProperties;register(Lnet/minecraft/world/item/Item;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/item/ClampedItemPropertyFunction;)V"
            )
    )
    @Unique
    private static void netherEx$redirectRegisterForCompass(Item item, ResourceLocation location, ClampedItemPropertyFunction function, Operation<Void> original)
    {
        if (item == Items.COMPASS && location.getPath().equals("angle"))
        {
            register(item, location, new CompassItemPropertyFunction((level, stack, entity) ->
            {
                LodestoneTracker lodestoneTracker = stack.get(DataComponents.LODESTONE_TRACKER);

                if (lodestoneTracker != null)
                {
                    return lodestoneTracker.target().orElse(null);
                }

                NEGlobalPosTracker globalPosTracker = stack.get(NetherExDataComponents.GLOBAL_POS_TRACKER.get());

                if (globalPosTracker != null)
                {
                    return globalPosTracker.target().orElse(null);
                }

                return CompassItem.getSpawnPosition(level);
            }));
        }
        else
        {
            original.call(item, location, function);
        }
    }

    @Invoker("register")
    private static void register(Item item, ResourceLocation id, ClampedItemPropertyFunction property)
    {
        throw new MixinApplyError("Failed to apply mixin!");
    }
}
