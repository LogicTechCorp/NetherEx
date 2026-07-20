package logictechcorp.netherex.client.entity.animal;

import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import logictechcorp.netherex.entity.animal.NEMogus;
import logictechcorp.netherex.registry.NetherExDataTickets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jspecify.annotations.Nullable;

public class NEMogusRenderer<R extends EntityRenderState & GeoRenderState> extends GeoEntityRenderer<NEMogus, R>
{
    public NEMogusRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new NEMogusModel());
    }

    @Override
    public void addRenderData(NEMogus animatable, @Nullable Void relatedObject, R renderState, float partialTick)
    {
        renderState.addGeckolibData(NetherExDataTickets.IS_BABY, animatable.isBaby());
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<R> renderPassInfo, BoneSnapshots snapshots)
    {
        float rootScale = renderPassInfo.getOrDefaultGeckolibData(NetherExDataTickets.IS_BABY, false) ? 0.5f : 1.0f;
        snapshots.ifPresent("mogus", bone -> bone.setScale(rootScale, rootScale, rootScale));
    }
}