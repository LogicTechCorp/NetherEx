package logictechcorp.netherex.datagen;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.datagen.client.language.NELanguageProviderEN_US;
import logictechcorp.netherex.datagen.client.model.NEEquipmentAssetProvider;
import logictechcorp.netherex.datagen.client.model.NEModelProvider;
import logictechcorp.netherex.datagen.server.NEDataPackProvider;
import logictechcorp.netherex.datagen.server.advancements.NEAdvancements;
import logictechcorp.netherex.datagen.server.loot.NELootModifiers;
import logictechcorp.netherex.datagen.server.loot.NELootTableProvider;
import logictechcorp.netherex.datagen.server.recipes.NERecipeProvider;
import logictechcorp.netherex.datagen.server.tags.NEBiomeTagsProvider;
import logictechcorp.netherex.datagen.server.tags.NEBlockTagsProvider;
import logictechcorp.netherex.datagen.server.tags.NEItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetherExDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.createProvider(NEDataPackProvider::new).getRegistryProvider();

        // Server
        event.addProvider(new NELootTableProvider(packOutput, registries));
        event.createProvider(NELootModifiers::new);
        event.createProvider(NERecipeProvider.Runner::new);
        event.createProvider(NEBiomeTagsProvider::new);
        event.createBlockAndItemTags(NEBlockTagsProvider::new, NEItemTagsProvider::new);
        event.addProvider(NEAdvancements.create(packOutput, registries));

        // Client
        event.createProvider(NEModelProvider::new);
        event.createProvider(NELanguageProviderEN_US::new);
        event.createProvider(NEEquipmentAssetProvider::new);
    }
}
