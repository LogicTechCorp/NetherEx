package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.entity.animal.NESalamander;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NESalamanderRenderer extends GeoEntityRenderer<NESalamander>
{
    public NESalamanderRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NESalamanderModel());
    }
}