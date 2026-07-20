package logictechcorp.netherex.client.entity.projectile;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.projectile.NEAshenArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class NEAshenArrowRenderer extends ArrowRenderer<NEAshenArrow, ArrowRenderState>
{
    private static final Identifier TEXTURE = NetherExConstants.identifier("textures/entity/projectiles/ashen_arrow.png");

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
    protected Identifier getTextureLocation(ArrowRenderState arrowRenderState)
    {
        return TEXTURE;
    }
}