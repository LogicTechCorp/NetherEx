package logictechcorp.netherex.client.entity.animal;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import logictechcorp.netherex.entity.monster.NEWisp;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class NEWispRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<NEWisp, R>
{
    public NEWispRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEWispModel());
        withRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}