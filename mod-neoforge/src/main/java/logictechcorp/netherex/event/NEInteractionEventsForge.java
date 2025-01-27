package logictechcorp.netherex.event;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID)
public class NEInteractionEventsForge
{
    @SubscribeEvent
    public static void onShearBlock(PlayerInteractEvent.RightClickBlock event)
    {
        Player player = event.getEntity();
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();
        BlockHitResult hitResult = event.getHitVec();

        ItemStack heldStack = player.getItemInHand(hand);

        if (player.isSpectator() || hitResult.getType() != HitResult.Type.BLOCK || !heldStack.is(Items.SHEARS))
        {
            return;
        }

        BlockPos hitPos = hitResult.getBlockPos();
        Block usedBlock = level.getBlockState(hitPos).getBlock();
        RandomSource random = level.getRandom();

        boolean shearedBlock = false;

        if (usedBlock == Blocks.SHROOMLIGHT)
        {
            level.setBlock(hitPos, NetherExBlocks.HOLLOW_SHROOMLIGHT.get().defaultBlockState(), 3);
            level.playSound(player, hitPos, SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
            Block.popResource(level, hitPos, new ItemStack(NetherExItems.SHROOMFRUIT.get(), random.nextIntBetweenInclusive(2, 4)));
            heldStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            shearedBlock = true;
        }
        else if (usedBlock == NetherExBlocks.TWISTED_SHROOMLIGHT.get())
        {
            level.setBlock(hitPos, NetherExBlocks.HOLLOW_TWISTED_SHROOMLIGHT.get().defaultBlockState(), 3);
            level.playSound(player, hitPos, SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
            Block.popResource(level, hitPos, new ItemStack(NetherExItems.TWISTED_SHROOMFRUIT.get(), random.nextIntBetweenInclusive(2, 4)));
            heldStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
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