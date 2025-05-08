package logictechcorp.netherex.datagen.server;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NEDatapackProvider extends DatapackBuiltinEntriesProvider
{
    private static RegistrySetBuilder getRegistrySetBuilder()
    {
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, NetherExFeatureConfigs::bootstrap)
                .add(Registries.PLACED_FEATURE, NetherExFeaturePlacements::bootstrap)
                .add(Registries.BIOME, NetherExBiomes::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NetherExBiomeModifiersNeoForge::boostrap);
        NetherExRegistries.DATAPACK_REGISTRIES.forEach(datapackRegistry -> datapackRegistry.addToSet(registrySetBuilder));
        return registrySetBuilder;
    }

    public NEDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, getRegistrySetBuilder(), Set.of(NetherExConstants.MOD_ID));
    }
}
