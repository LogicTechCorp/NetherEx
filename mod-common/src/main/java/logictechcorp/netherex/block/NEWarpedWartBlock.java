package logictechcorp.netherex.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NEWarpedWartBlock extends BushBlock
{
    public static final MapCodec<NEWarpedWartBlock> CODEC = simpleCodec(NEWarpedWartBlock::new);

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0d, 8.0d, 0.0d, 16.0d, 16.0d, 16.0d),
            Block.box(0.0d, 6.0d, 0.0d, 16.0d, 16.0d, 16.0d),
            Block.box(0.0d, 6.0d, 0.0d, 16.0d, 16.0d, 16.0d),
            Block.box(0.0d, 2.0d, 0.0d, 16.0d, 16.0d, 16.0d)
    };

    public NEWarpedWartBlock(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        int age = state.getValue(AGE);

        if (age < 3 && random.nextInt(10) == 0)
        {
            state = state.setValue(AGE, age + 1);
            level.setBlock(pos, state, 2);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos)
    {
        BlockPos abovePos = pos.above();
        return mayPlaceOn(levelReader.getBlockState(abovePos), levelReader, abovePos);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state)
    {
        return state.getValue(AGE) < 3;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos)
    {
        return state.is(Blocks.SOUL_SOIL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext)
    {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected MapCodec<? extends BushBlock> codec()
    {
        return CODEC;
    }
}
