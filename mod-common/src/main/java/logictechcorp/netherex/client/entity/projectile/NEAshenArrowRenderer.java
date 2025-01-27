package logictechcorp.netherex.client.entity.projectile;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.projectile.NEAshenArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class NEAshenArrowRenderer extends ArrowRenderer<NEAshenArrow, ArrowRenderState>
{
    private static final ResourceLocation TEXTURE = NetherExConstants.resource("textures/entity/projectiles/ashen_arrow.png");

    public NEAshenArrowRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState()
    {
        return new ArrowRenderState();
    }

    @Override
    protected ResourceLocation getTextureLocation(ArrowRenderState arrowRenderState)
    {
        return TEXTURE;
    }
}