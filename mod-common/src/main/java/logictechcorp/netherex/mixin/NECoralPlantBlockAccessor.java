package logictechcorp.netherex.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralPlantBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CoralPlantBlock.class)
public interface NECoralPlantBlockAccessor
{
    @Accessor
    Block getDeadBlock();
}
