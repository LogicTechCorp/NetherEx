package logictechcorp.netherex.event;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.events.NEInteractionEvents;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID)
public class NEInteractionEventsForge
{
    @SubscribeEvent
    public static void onShearBlock(UseItemOnBlockEvent event)
    {
        Level level = event.getLevel();

        if (level.isClientSide)
        {
            event.setCancellationResult(InteractionResult.PASS);
            event.setCanceled(true);
            return;
        }

        Player player = event.getPlayer();
        ItemStack heldStack = event.getItemStack();

        if (player.isSpectator() || !heldStack.is(Items.SHEARS))
        {
            return;
        }

        BlockPos hitPos = event.getPos();
        Block usedBlock = level.getBlockState(hitPos).getBlock();

        ServerLevel serverLevel = (ServerLevel) level;
        ServerPlayer serverPlayer = (ServerPlayer) player;

        boolean shearedBlock = false;

        if (usedBlock == Blocks.SHROOMLIGHT)
        {
            NEInteractionEvents.shearShroomlight(
                    serverLevel,
                    hitPos,
                    serverPlayer,
                    heldStack,
                    NetherExBlocks.HOLLOW_SHROOMLIGHT.get().defaultBlockState(),
                    NetherExItems.SHROOMFRUIT.get()
            );
            shearedBlock = true;
        }
        else if (usedBlock == NetherExBlocks.TWISTED_SHROOMLIGHT.get())
        {
            NEInteractionEvents.shearShroomlight(
                    serverLevel,
                    hitPos,
                    serverPlayer,
                    heldStack,
                    NetherExBlocks.HOLLOW_TWISTED_SHROOMLIGHT.get().defaultBlockState(),
                    NetherExItems.TWISTED_SHROOMFRUIT.get()
            );
            shearedBlock = true;
        }

        if (!shearedBlock)
        {
            return;
        }

        event.setCancellationResult(InteractionResult.SUCCESS_SERVER);
        event.setCanceled(true);
    }
}