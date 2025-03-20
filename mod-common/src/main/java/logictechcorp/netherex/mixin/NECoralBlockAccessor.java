package logictechcorp.netherex.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CoralBlock.class)
public interface NECoralBlockAccessor
{
    @Accessor
    Block getDeadBlock();
}
