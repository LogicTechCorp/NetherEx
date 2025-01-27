package logictechcorp.netherex.registry;

import logictechcorp.netherex.block.NEGlowingObsidianBlock;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

public class NetherExFluidInteractionsNeoForge
{
    public static void register()
    {
        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                (level, currentPos, relativePos, currentState) -> level.getBlockState(relativePos).is(Blocks.OBSIDIAN) && NEGlowingObsidianBlock.hasHeater(level, relativePos),
                (level, currentPos, relativePos, currentState) ->
                {
                    level.setBlockAndUpdate(relativePos, NetherExBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                    level.levelEvent(1501, relativePos, 0);
                }
        ));
    }
}