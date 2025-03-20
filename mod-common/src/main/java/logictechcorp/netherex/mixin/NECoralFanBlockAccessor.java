package logictechcorp.netherex.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralFanBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CoralFanBlock.class)
public interface NECoralFanBlockAccessor
{
    @Accessor
    Block getDeadBlock();
}
