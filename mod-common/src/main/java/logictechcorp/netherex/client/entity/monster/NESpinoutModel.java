package logictechcorp.netherex.client.entity.monster;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.monster.NESpinout;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NESpinoutModel extends DefaultedEntityGeoModel<NESpinout>
{
    private static final ResourceLocation TEXTURE = NetherExConstants.resource("textures/entity/spinout/spinout.png");

    public NESpinoutModel()
    {
        super(NetherExConstants.resource("spinout"));
    }

    @Override
    public ResourceLocation getTextureResource(NESpinout animatable, GeoRenderer<NESpinout> renderer)
    {
        return TEXTURE;
    }
}