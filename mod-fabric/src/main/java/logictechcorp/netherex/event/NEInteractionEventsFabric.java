package logictechcorp.netherex.event;

import logictechcorp.netherex.events.NEInteractionEvents;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;

public class NEInteractionEventsFabric
{
    public static void register()
    {
        onShearBlock();
    }

    static void onShearBlock()
    {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) ->
        {
            if (level.isClientSide)
            {
                return InteractionResult.PASS;
            }

            ItemStack heldStack = player.getItemInHand(hand);

            if (player.isSpectator() || hitResult.getType() != HitResult.Type.BLOCK || !heldStack.is(Items.SHEARS))
            {
                return InteractionResult.PASS;
            }

            BlockPos hitPos = hitResult.getBlockPos();
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
                return InteractionResult.PASS;
            }

            return InteractionResult.SUCCESS_SERVER;
        });
    }
}