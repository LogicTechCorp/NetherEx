package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemoth;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NEFlaemothModel extends DefaultedEntityGeoModel<NEFlaemoth>
{
    public NEFlaemothModel()
    {
        super(NetherExConstants.resource("flaemoth"));
    }

    @Override
    public ResourceLocation getTextureResource(NEFlaemoth flaemoth, GeoRenderer<NEFlaemoth> renderer)
    {
        return flaemoth.getVariant().value().texture();
    }
}