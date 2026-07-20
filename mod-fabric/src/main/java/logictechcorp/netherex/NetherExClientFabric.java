package logictechcorp.netherex;

import net.fabricmc.api.ClientModInitializer;

public class NetherExClientFabric implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        NetherExClient.registerEntityRenderers();
    }
}
