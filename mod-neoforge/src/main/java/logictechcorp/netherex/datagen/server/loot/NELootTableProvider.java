package logictechcorp.netherex.datagen.server.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NELootTableProvider extends LootTableProvider
{
    private static final List<LootTableProvider.SubProviderEntry> SUB_PROVIDERS = List.of(
            new LootTableProvider.SubProviderEntry(NEBlockLootProvider::new, LootContextParamSets.BLOCK),
            new LootTableProvider.SubProviderEntry(NEEntityLootProvider::new, LootContextParamSets.ENTITY),
            new LootTableProvider.SubProviderEntry(NEChestLootProvider::new, LootContextParamSets.CHEST)
    );

    public NELootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, Collections.emptySet(), SUB_PROVIDERS, registries);
    }
}
