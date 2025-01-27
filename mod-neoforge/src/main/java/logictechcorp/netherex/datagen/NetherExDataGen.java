package logictechcorp.netherex.datagen;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.datagen.client.language.NELanguageProviderEN_US;
import logictechcorp.netherex.datagen.client.model.NEEquipmentAssetProvider;
import logictechcorp.netherex.datagen.client.model.NEModelProvider;
import logictechcorp.netherex.datagen.server.NEDataPackProvider;
import logictechcorp.netherex.datagen.server.loot.NEBlockLootProvider;
import logictechcorp.netherex.datagen.server.loot.NEEntityLootProvider;
import logictechcorp.netherex.datagen.server.recipes.NERecipeProvider;
import logictechcorp.netherex.datagen.server.tags.NEBiomeTagsProvider;
import logictechcorp.netherex.datagen.server.tags.NEBlockTagsProvider;
import logictechcorp.netherex.datagen.server.tags.NEItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetherExDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        // Server
        registries = event.addProvider(new NEDataPackProvider(packOutput, registries)).getRegistryProvider();
        event.addProvider(new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(NEBlockLootProvider::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(NEEntityLootProvider::new, LootContextParamSets.ENTITY)
                ),
                registries)
        );
        event.addProvider(new NERecipeProvider.Runner(packOutput, registries));
        event.addProvider(new NEBiomeTagsProvider(packOutput, registries));
        event.createBlockAndItemTags(NEBlockTagsProvider::new, NEItemTagsProvider::new);

        // Client
        event.addProvider(new NEModelProvider(packOutput));
        event.addProvider(new NELanguageProviderEN_US(packOutput));
        event.addProvider(new NEEquipmentAssetProvider(packOutput));
    }
}
