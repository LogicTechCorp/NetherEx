package logictechcorp.netherex.block;

import logictechcorp.netherex.block.state.properties.NEFumaroleStage;
import logictechcorp.netherex.block.state.properties.NEHeatLevel;
import logictechcorp.netherex.registry.NetherExBlockTags;
import logictechcorp.netherex.util.NEEnumUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NEFumaroleBlock extends NEBaseHeatBlock
{
    public static final EnumProperty<NEFumaroleStage> STAGE = EnumProperty.create("stage", NEFumaroleStage.class);
    protected static final VoxelShape SHAPE = Block.box(0.0d, 0.0d, 0.0d, 16.0d, 8.0d, 16.0d);

    public NEFumaroleBlock(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(STAGE, NEFumaroleStage.HEAT_UP));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        NEFumaroleStage stage = state.getValue(STAGE);
        NEFumaroleStage nextStage = NEEnumUtils.fromOrdinal(NEFumaroleStage.class, stage.ordinal() + 1, true);
        level.setBlock(pos, state.setValue(STAGE, nextStage), 3);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
    {
        NEHeatLevel heatLevel = state.getValue(HEAT_LEVEL);
        NEFumaroleStage stage = state.getValue(STAGE);

        double posX = pos.getX() + 0.5d;
        double posY = pos.getY() + 0.5d;
        double posZ = pos.getZ() + 0.5d;

        if (stage == NEFumaroleStage.HEAT_UP)
        {
            level.addParticle(ParticleTypes.POOF, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
            level.addParticle(ParticleTypes.ASH, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
            level.addParticle(ParticleTypes.WHITE_ASH, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
        }
        else if (stage == NEFumaroleStage.ERUPTION)
        {
            int height = random.nextIntBetweenInclusive(heatLevel.getMinHeight(), heatLevel.getMaxHeight());
            double particleSpeed = 0.02d;

            for (int i = 0; i < height; i++)
            {
                double speedY = random.nextGaussian() * particleSpeed;
                double newPosY = posY + random.nextGaussian() * height / 2;

                level.addParticle(ParticleTypes.SMOKE, posX, newPosY, posZ, 0.0d, speedY, 0.0d);
                level.addParticle(ParticleTypes.LARGE_SMOKE, posX, newPosY, posZ, 0.0d, speedY, 0.0d);
                level.addParticle(ParticleTypes.POOF, posX, newPosY, posZ, 0.0d, speedY, 0.0d);
                level.addParticle(ParticleTypes.ASH, posX, newPosY, posZ, 0.0d, speedY, 0.0d);
                level.addParticle(ParticleTypes.WHITE_ASH, posX, newPosY, posZ, 0.0d, speedY, 0.0d);
            }
        }
        else
        {
            level.addParticle(ParticleTypes.ASH, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
            level.addParticle(ParticleTypes.WHITE_ASH, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        RandomSource random = level.getRandom();
        updateHeatLevel(state, level, pos);

        if (!level.getBlockTicks().hasScheduledTick(pos, this))
        {
            level.scheduleTick(pos, this, getTimeUntilNextStage(state, random) * 20);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
    {
        updateHeatLevel(state, level, pos);
    }

    public void updateHeatLevel(BlockState state, Level level, BlockPos pos)
    {
        BlockState belowState = level.getBlockState(pos.below());
        NEHeatLevel newHeatLevel = NEHeatLevel.NO_HEAT;

        if (belowState.is(NetherExBlockTags.LOW_FUMAROLE_HEATER))
        {
            newHeatLevel = NEHeatLevel.LOW_HEAT;
        }
        else if (belowState.is(NetherExBlockTags.HIGH_FUMAROLE_HEATER))
        {
            newHeatLevel = NEHeatLevel.HIGH_HEAT;
        }

        if (newHeatLevel != state.getValue(HEAT_LEVEL))
        {
            level.setBlock(pos, state.setValue(HEAT_LEVEL, newHeatLevel), 3);
        }
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType)
    {
        return false;
    }

    public int getTimeUntilNextStage(BlockState state, RandomSource random)
    {
        NEHeatLevel heatLevel = state.getValue(HEAT_LEVEL);
        NEFumaroleStage stage = state.getValue(STAGE);

        if (stage == NEFumaroleStage.HEAT_UP)
        {
            return heatLevel.getHeatUpTime() + random.nextIntBetweenInclusive(0, 8);
        }
        else if (stage == NEFumaroleStage.ERUPTION)
        {
            return heatLevel.getEruptionTime();
        }

        return heatLevel.getCoolDownTime();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }
}
