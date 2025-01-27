package logictechcorp.netherex;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetherExNeoForgeClient
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(NetherExClient::registerEntityRenderers);
    }
}
