package logictechcorp.netherex.block;

import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NETwistedShroomstemBlock extends NEAbstractShroomstemBlock
{
    public static final MapCodec<NETwistedShroomstemBlock> CODEC = simpleCodec(NETwistedShroomstemBlock::new);

    public NETwistedShroomstemBlock(Properties properties)
    {
        super(NetherExBlocks.TWISTED_SHROOMLIGHT.get()::defaultBlockState, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos)
    {
        return state.is(Blocks.WARPED_WART_BLOCK) || state.is(BlockTags.WARPED_STEMS);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader levelReader, BlockPos pos, BlockState state, boolean p_388788_)
    {
        return new ItemStack(NetherExItems.TWISTED_SHROOMFRUIT.get());
    }

    @Override
    protected MapCodec<? extends BushBlock> codec()
    {
        return CODEC;
    }
}
