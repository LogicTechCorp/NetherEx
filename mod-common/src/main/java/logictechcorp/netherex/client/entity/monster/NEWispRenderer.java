package logictechcorp.netherex.client.entity.monster;

import logictechcorp.netherex.entity.monster.NEWisp;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class NEWispRenderer extends GeoEntityRenderer<NEWisp>
{
    public NEWispRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEWispModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}