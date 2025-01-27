package logictechcorp.netherex.block;

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

public class NEShroomstemBlock extends NEAbstractShroomstemBlock
{
    public static final MapCodec<NEShroomstemBlock> CODEC = simpleCodec(NEShroomstemBlock::new);

    public NEShroomstemBlock(Properties properties)
    {
        super(Blocks.SHROOMLIGHT::defaultBlockState, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos)
    {
        return state.is(Blocks.NETHER_WART_BLOCK) || state.is(BlockTags.CRIMSON_STEMS);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos pos, BlockState state, boolean p_388788_)
    {
        return new ItemStack(NetherExItems.SHROOMFRUIT.get());
    }

    @Override
    protected MapCodec<? extends BushBlock> codec()
    {
        return CODEC;
    }
}
