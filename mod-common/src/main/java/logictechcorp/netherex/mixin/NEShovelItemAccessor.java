package logictechcorp.netherex.mixin;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.throwables.MixinError;

import java.util.Map;

@Mixin(ShovelItem.class)
public interface NEShovelItemAccessor
{
    @Accessor("FLATTENABLES")
    static Map<Block, BlockState> getFlattenables()
    {
        throw new MixinError("Mixin did not apply!");
    }
}