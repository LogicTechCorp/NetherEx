package logictechcorp.netherex.client.entity.animal;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import logictechcorp.netherex.entity.animal.NESalamander;
import logictechcorp.netherex.registry.NetherExDataTickets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jspecify.annotations.Nullable;

public class NESalamanderRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<NESalamander, R>
{
    public NESalamanderRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NESalamanderModel());
    }

    @Override
    public void addRenderData(NESalamander animatable, @Nullable Void relatedObject, R renderState, float partialTick)
    {
        renderState.addGeckolibData(NetherExDataTickets.IS_BABY, animatable.isBaby());
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> renderPassInfo, BoneSnapshots snapshots)
    {
        float rootScale = renderPassInfo.getOrDefaultGeckolibData(NetherExDataTickets.IS_BABY, false) ? 0.5f : 1.0f;
        snapshots.ifPresent("salamander", bone -> bone.setScale(rootScale, rootScale, rootScale));
    }
}