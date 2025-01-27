package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEMogus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NEMogusModel extends DefaultedEntityGeoModel<NEMogus>
{
    public NEMogusModel()
    {
        super(NetherExConstants.resource("mogus"));
    }

    @Override
    public ResourceLocation getTextureResource(NEMogus mogus, GeoRenderer<NEMogus> renderer)
    {
        return mogus.getVariant().value().texture();
    }
}