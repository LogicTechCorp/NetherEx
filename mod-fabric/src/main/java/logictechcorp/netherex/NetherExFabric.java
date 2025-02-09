package logictechcorp.netherex;

import logictechcorp.netherex.event.NEInteractionEventsFabric;
import logictechcorp.netherex.event.NELootEventsFabric;
import logictechcorp.netherex.registry.NetherExBiomeModifiersFabric;
import logictechcorp.netherex.registry.NetherExEntityTypesFabric;
import net.fabricmc.api.ModInitializer;
import terrablender.api.TerraBlenderApi;

public class NetherExFabric implements ModInitializer, TerraBlenderApi
{
    @Override
    public void onInitialize()
    {
        NetherEx.onLoad();
        NetherEx.onCommonSetup();
        NetherExEntityTypesFabric.registerAttributes();
        NetherExBiomeModifiersFabric.register();
        NELootEventsFabric.register();
        NEInteractionEventsFabric.register();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        NetherEx.onInitializeTerraBlender();
    }
}
