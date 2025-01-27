package logictechcorp.netherex.mixin;

import com.google.common.collect.ImmutableList;
import logictechcorp.netherex.block.NEGlowingObsidianBlock;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class NELiquidBlockMixinFabric extends Block
{
    @Shadow
    @Final
    public static ImmutableList<Direction> POSSIBLE_FLOW_DIRECTIONS;

    @Shadow
    @Final
    protected FlowingFluid fluid;

    public NELiquidBlockMixinFabric(Properties properties)
    {
        super(properties);
    }

    @Shadow
    protected abstract void fizz(LevelAccessor levelAccessor, BlockPos pos);

    @Inject(
            method = "shouldSpreadLiquid",
            at = @At("HEAD"),
            cancellable = true
    )
    private void netherExpansionShouldSpreadLiquid(Level level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> callback)
    {
        if (fluid.is(FluidTags.LAVA))
        {
            for (Direction checkDirection : POSSIBLE_FLOW_DIRECTIONS)
            {
                BlockPos checkPos = pos.relative(checkDirection);
                BlockState checkState = level.getBlockState(checkPos);

                if (checkState.is(Blocks.OBSIDIAN))
                {
                    if (NEGlowingObsidianBlock.hasHeater(level, checkPos))
                    {
                        level.setBlockAndUpdate(checkPos, NetherExBlocks.GLOWING_OBSIDIAN.get().defaultBlockState());
                        fizz(level, checkPos);
                        callback.setReturnValue(false);
                    }
                }
            }
        }
    }
}