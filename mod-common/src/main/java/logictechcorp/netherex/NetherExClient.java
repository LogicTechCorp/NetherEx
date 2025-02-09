package logictechcorp.netherex;

import logictechcorp.netherex.client.entity.animal.NEFlaemothRenderer;
import logictechcorp.netherex.client.entity.animal.NEMogusRenderer;
import logictechcorp.netherex.client.entity.animal.NESalamanderRenderer;
import logictechcorp.netherex.client.entity.animal.NEWispRenderer;
import logictechcorp.netherex.client.entity.monster.NESpinoutRenderer;
import logictechcorp.netherex.client.entity.projectile.NEAshenArrowRenderer;
import logictechcorp.netherex.mixin.client.NEEntityRenderersMixin;
import logictechcorp.netherex.registry.NetherExEntityTypes;

public class NetherExClient
{
    public static void registerEntityRenderers()
    {
        NEEntityRenderersMixin.register(NetherExEntityTypes.SPINOUT.get(), NESpinoutRenderer::new);
        NEEntityRenderersMixin.register(NetherExEntityTypes.WISP.get(), NEWispRenderer::new);
        NEEntityRenderersMixin.register(NetherExEntityTypes.SALAMANDER.get(), NESalamanderRenderer::new);
        NEEntityRenderersMixin.register(NetherExEntityTypes.MOGUS.get(), NEMogusRenderer::new);
        NEEntityRenderersMixin.register(NetherExEntityTypes.FLAEMOTH.get(), NEFlaemothRenderer::new);
        NEEntityRenderersMixin.register(NetherExEntityTypes.ASHEN_ARROW.get(), NEAshenArrowRenderer::new);
    }
}
