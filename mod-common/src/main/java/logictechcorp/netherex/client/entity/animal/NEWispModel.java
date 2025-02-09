package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.monster.NEWisp;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NEWispModel extends DefaultedEntityGeoModel<NEWisp>
{
    private static final ResourceLocation TEXTURE = NetherExConstants.resource("textures/entity/wisp/wisp.png");

    public NEWispModel()
    {
        super(NetherExConstants.resource("wisp"));
    }

    @Override
    public ResourceLocation getTextureResource(NEWisp animatable, GeoRenderer<NEWisp> renderer)
    {
        return TEXTURE;
    }
}