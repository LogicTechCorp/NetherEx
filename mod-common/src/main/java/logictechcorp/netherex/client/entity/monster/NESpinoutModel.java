package logictechcorp.netherex.client.entity.monster;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.monster.NESpinout;
import net.minecraft.resources.Identifier;

public class NESpinoutModel extends DefaultedEntityGeoModel<NESpinout>
{
    private static final Identifier TEXTURE = NetherExConstants.identifier("textures/entity/spinout/spinout.png");

    public NESpinoutModel()
    {
        super(NetherExConstants.identifier("spinout"));
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState)
    {
        return TEXTURE;
    }
}