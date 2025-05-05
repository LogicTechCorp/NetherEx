package logictechcorp.netherex.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class NEPathBlock extends Block
{
    protected static final VoxelShape SHAPE = Block.box(0.0f, 0.0f, 0.0f, 16.0f, 15.0f, 16.0f);

    protected Supplier<Block> originalBlock;

    public NEPathBlock(Supplier<Block> inOriginalBlock, BlockBehaviour.Properties properties)
    {
        super(properties);
        originalBlock = inOriginalBlock;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        turnToOriginalBlock(null, state, level, pos);
    }

    public void turnToOriginalBlock(@Nullable Entity entity, BlockState state, Level level, BlockPos pos)
    {
        BlockState newState = pushEntitiesUp(state, originalBlock.get().defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, newState));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (direction == Direction.UP && !state.canSurvive(level, pos))
        {
            level.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockState aboveState = level.getBlockState(pos.above());
        return !aboveState.isFaceSturdy(level, pos, Direction.DOWN) || aboveState.is(BlockTags.FENCE_GATES);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();

        if (defaultBlockState().canSurvive(level, clickedPos))
        {
            return super.getStateForPlacement(context);
        }

        return pushEntitiesUp(defaultBlockState(), originalBlock.get().defaultBlockState(), level, clickedPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }
}
