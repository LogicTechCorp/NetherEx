package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.monster.NESpinout;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NESpinoutRenderer extends GeoEntityRenderer<NESpinout>
{
    public NESpinoutRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new DefaultedEntityGeoModel<>(NetherExConstants.resource("spinout"), true));
    }
}