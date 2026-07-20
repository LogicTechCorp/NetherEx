package logictechcorp.netherex.client.entity.animal;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.monster.NEWisp;
import net.minecraft.resources.Identifier;

public class NEWispModel extends DefaultedEntityGeoModel<NEWisp>
{
    private static final Identifier TEXTURE = NetherExConstants.identifier("textures/entity/wisp/wisp.png");

    public NEWispModel()
    {
        super(NetherExConstants.identifier("wisp"));
    }


    @Override
    public Identifier getTextureResource(GeoRenderState renderState)
    {
        return TEXTURE;
    }
}