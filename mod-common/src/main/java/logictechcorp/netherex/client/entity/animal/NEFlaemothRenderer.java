package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.entity.animal.NEFlaemoth;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NEFlaemothRenderer extends GeoEntityRenderer<NEFlaemoth>
{
    public NEFlaemothRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEFlaemothModel());
    }
}