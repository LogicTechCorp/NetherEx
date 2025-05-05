package logictechcorp.netherex.mixin;

import net.minecraft.data.models.model.TextureSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.throwables.MixinError;

@Mixin(TextureSlot.class)
public interface NETextureSlotInvoker
{
    @Invoker("create")
    static TextureSlot create(String id)
    {
        throw new MixinError("Mixin did not apply!");
    }
}
