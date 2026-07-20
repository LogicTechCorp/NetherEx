package logictechcorp.netherex.client.entity.animal;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemoth;
import logictechcorp.netherex.registry.NetherExDataTickets;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class NEFlaemothModel extends DefaultedEntityGeoModel<NEFlaemoth>
{
    public NEFlaemothModel()
    {
        super(NetherExConstants.identifier("flaemoth"));
    }

    @Override
    public void addAdditionalStateData(NEFlaemoth animatable, @Nullable Object relatedObject, GeoRenderState renderState)
    {
        renderState.addGeckolibData(NetherExDataTickets.VARIANT_TEXTURE, animatable.getVariant().value().texture());
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState)
    {
        Identifier texture = renderState.getGeckolibData(NetherExDataTickets.VARIANT_TEXTURE);
        return texture != null ? texture : super.getTextureResource(renderState);
    }
}