package logictechcorp.netherex.client.entity.animal;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import logictechcorp.netherex.entity.animal.NEFlaemoth;
import logictechcorp.netherex.registry.NetherExDataTickets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jspecify.annotations.Nullable;

public class NEFlaemothRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<NEFlaemoth, R>
{
    public NEFlaemothRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEFlaemothModel());
    }

    @Override
    public void addRenderData(NEFlaemoth animatable, @Nullable Void relatedObject, R renderState, float partialTick)
    {
        renderState.addGeckolibData(NetherExDataTickets.IS_BABY, animatable.isBaby());
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> renderPassInfo, BoneSnapshots snapshots)
    {
        float rootScale = renderPassInfo.getOrDefaultGeckolibData(NetherExDataTickets.IS_BABY, false) ? 0.5f : 1.0f;
        snapshots.ifPresent("flaemoth", bone -> bone.setScale(rootScale, rootScale, rootScale));
    }
}