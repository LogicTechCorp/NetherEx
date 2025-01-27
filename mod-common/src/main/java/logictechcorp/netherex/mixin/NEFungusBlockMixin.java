package logictechcorp.netherex.mixin;

import logictechcorp.netherex.registry.NetherExFeatureConfigs;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(FungusBlock.class)
public abstract class NEFungusBlockMixin extends BushBlock implements BonemealableBlock
{
    protected NEFungusBlockMixin(Properties properties)
    {
        super(properties);
    }

    @Inject(
            method = "getFeature",
            at = @At("HEAD"),
            cancellable = true
    )
    private void netherex$getFeature(LevelReader level, CallbackInfoReturnable<Optional<? extends Holder<ConfiguredFeature<?, ?>>>> callback)
    {
        if (this == Blocks.WARPED_FUNGUS)
        {
            callback.setReturnValue(level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(NetherExFeatureConfigs.TWISTED_WARPED_FUNGUS_PLANTED));
        }

        callback.cancel();
    }
}
