package logictechcorp.netherex;

import logictechcorp.netherex.registry.NetherExFluidInteractionsNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(NetherExConstants.MOD_ID)
public class NetherExNeoForge
{
    public NetherExNeoForge(IEventBus eventBus)
    {
        NetherEx.onLoad();
        eventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        NetherEx.onCommonSetup();
        NetherEx.onInitializeTerraBlender();
        NetherExFluidInteractionsNeoForge.register();
    }
}
