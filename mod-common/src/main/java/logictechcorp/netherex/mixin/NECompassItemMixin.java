package logictechcorp.netherex.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import logictechcorp.netherex.item.component.NEStructureTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import logictechcorp.netherex.world.level.levelgen.structure.NEStructureLodestoneMarker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(CompassItem.class)
public class NECompassItemMixin
{
    @Inject(
            method = "inventoryTick",
            at = @At("HEAD")
    )
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected, CallbackInfo callback)
    {
        if (level instanceof ServerLevel serverLevel && serverLevel.getGameTime() % 20 == 0)
        {
            NEStructureTracker structureTracker = stack.get(NetherExDataComponents.STRUCTURE_TRACKER.get());

            if (structureTracker != null)
            {
                HolderLookup.RegistryLookup<Structure> structures = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE);
                Holder<Structure> structure = structures.getOrThrow(structureTracker.structure());
                GlobalPos globalPos = structureTracker.target();

                if (globalPos.dimension() == serverLevel.dimension())
                {
                    StructureStart structureStart = serverLevel.structureManager().getStructureAt(globalPos.pos(), structure.value());

                    if (structureStart.isValid())
                    {
                        structureStart.getPieces().forEach(structurePiece ->
                        {
                            if (structurePiece instanceof NEStructureLodestoneMarker lodestoneMarker && lodestoneMarker.getStructureLodestonePos() != null)
                            {
                                HolderLookup.RegistryLookup<PoiType> pois = serverLevel.registryAccess().lookupOrThrow(Registries.POINT_OF_INTEREST_TYPE);
                                serverLevel.getPoiManager().add(lodestoneMarker.getStructureLodestonePos(), pois.getOrThrow(PoiTypes.LODESTONE));

                                LodestoneTracker lodestoneTracker = new LodestoneTracker(Optional.of(GlobalPos.of(serverLevel.dimension(), lodestoneMarker.getStructureLodestonePos())), true);
                                stack.set(DataComponents.LODESTONE_TRACKER, lodestoneTracker);
                                stack.remove(NetherExDataComponents.STRUCTURE_TRACKER.get());
                            }
                        });
                    }
                }
            }
        }
    }

    @Inject(
            method = "useOn",
            at = @At("HEAD")
    )
    public void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> callback)
    {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();

        if (level.getBlockState(clickedPos).is(Blocks.LODESTONE))
        {
            if (stack.has(NetherExDataComponents.STRUCTURE_TRACKER.get()))
            {
                stack.remove(NetherExDataComponents.STRUCTURE_TRACKER.get());
            }
        }
    }

    @WrapMethod(method = "isFoil")
    public boolean isFoil(ItemStack stack, Operation<Boolean> original)
    {
        return stack.has(NetherExDataComponents.STRUCTURE_TRACKER.get()) || original.call(stack);
    }

    @WrapMethod(method = "getDescriptionId")
    public String getDescriptionId(ItemStack stack, Operation<String> original)
    {
        return stack.has(NetherExDataComponents.STRUCTURE_TRACKER.get()) ? "item.minecraft.lodestone_compass" : original.call(stack);
    }
}
