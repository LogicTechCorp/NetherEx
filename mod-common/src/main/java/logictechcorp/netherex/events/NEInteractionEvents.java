package logictechcorp.netherex.events;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NEInteractionEvents
{
    public static void shearShroomlight(ServerLevel serverLevel, BlockPos pos, ServerPlayer serverPlayer, ItemStack stack, BlockState hollowState, Item fruitItem)
    {
        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        serverLevel.setBlock(pos, hollowState, 3);
        serverLevel.playSound(serverPlayer, pos, SoundEvents.BEEHIVE_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);

        RandomSource random = serverLevel.getRandom();
        Block.popResource(serverLevel, pos, new ItemStack(fruitItem, random.nextIntBetweenInclusive(2, 4)));

        stack.hurtAndBreak(1, serverPlayer, EquipmentSlot.MAINHAND);
    }
}
