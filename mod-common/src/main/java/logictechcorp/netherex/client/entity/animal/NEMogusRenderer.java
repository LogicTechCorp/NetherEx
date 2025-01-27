package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.entity.animal.NEMogus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NEMogusRenderer extends GeoEntityRenderer<NEMogus>
{
    public NEMogusRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEMogusModel());
    }
}