package logictechcorp.netherex.client.entity.animal;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NESalamander;
import logictechcorp.netherex.registry.NetherExDataTickets;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class NESalamanderModel extends DefaultedEntityGeoModel<NESalamander>
{
    public NESalamanderModel()
    {
        super(NetherExConstants.identifier("salamander"));
    }

    @Override
    public void addAdditionalStateData(NESalamander animatable, @Nullable Object relatedObject, GeoRenderState renderState)
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