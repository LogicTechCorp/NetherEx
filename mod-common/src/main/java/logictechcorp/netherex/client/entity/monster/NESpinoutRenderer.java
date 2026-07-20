package logictechcorp.netherex.client.entity.monster;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import logictechcorp.netherex.entity.monster.NESpinout;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class NESpinoutRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<NESpinout, R>
{
    public NESpinoutRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NESpinoutModel());
        withRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}