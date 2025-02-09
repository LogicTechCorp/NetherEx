package logictechcorp.netherex.client.entity.monster;

import logictechcorp.netherex.entity.monster.NESpinout;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class NESpinoutRenderer extends GeoEntityRenderer<NESpinout>
{
    public NESpinoutRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NESpinoutModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}