package logictechcorp.netherex.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class NEBoomstoneBlock extends Block
{
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public NEBoomstoneBlock(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(UNSTABLE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(UNSTABLE);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
    {
        explode(level, state, pos);
    }

    public void explode(Level level, BlockState state, BlockPos pos)
    {
        level.removeBlock(pos, false);
        level.explode(null, null, null, pos.getCenter(), 5.0f, true, Level.ExplosionInteraction.BLOCK);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack toolStack)
    {
        if (!EnchantmentHelper.hasTag(toolStack, EnchantmentTags.PREVENTS_DECORATED_POT_SHATTERING))
        {
            setUnstable(level, state, pos);
        }
        else
        {
            super.playerDestroy(level, player, pos, state, blockEntity, toolStack);
        }
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion)
    {
        BlockState state = level.getBlockState(pos);
        setUnstable(level, state, pos);
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion)
    {
        return false;
    }

    public void setUnstable(Level level, BlockState state, BlockPos pos)
    {
        if (!state.is(this))
        {
            return;
        }

        level.setBlock(pos, state.setValue(UNSTABLE, true), 3);
        level.scheduleTick(pos, this, 1);
    }
}
