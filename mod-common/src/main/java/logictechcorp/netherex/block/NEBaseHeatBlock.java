package logictechcorp.netherex.block;

import logictechcorp.netherex.block.state.properties.NEHeatLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class NEBaseHeatBlock extends Block
{
    public static final EnumProperty<NEHeatLevel> HEAT_LEVEL = EnumProperty.create("heat_level", NEHeatLevel.class);

    public NEBaseHeatBlock(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(HEAT_LEVEL, NEHeatLevel.NO_HEAT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(HEAT_LEVEL);
    }
}
