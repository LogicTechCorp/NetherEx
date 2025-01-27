package logictechcorp.netherex.mixin.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.throwables.MixinError;

@Mixin(EntityRenderers.class)
public interface NEEntityRenderersMixin
{
    @Invoker("register")
    static <T extends Entity> void register(EntityType<? extends T> entityType, EntityRendererProvider<T> provider)
    {
        throw new MixinError("Mixin did not apply!");
    }
}
