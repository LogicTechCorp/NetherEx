package logictechcorp.netherex;

import logictechcorp.netherex.registry.NetherExBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class NetherExClientFabric implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        NetherExClient.registerEntityRenderers();
        registerBlockRenderTypes();
    }

    private void registerBlockRenderTypes()
    {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                NetherExBlocks.THORNSTALK.get(),
                NetherExBlocks.BROWN_ELDER_MUSHROOM.get(),
                NetherExBlocks.RED_ELDER_MUSHROOM.get(),
                NetherExBlocks.WARPED_WART.get(),
                NetherExBlocks.CRIMSON_ROOT_THATCH.get(),
                NetherExBlocks.WARPED_ROOT_THATCH.get(),
                NetherExBlocks.SHROOMSTEM.get(),
                NetherExBlocks.TWISTED_SHROOMSTEM.get()
        );
    }
}
