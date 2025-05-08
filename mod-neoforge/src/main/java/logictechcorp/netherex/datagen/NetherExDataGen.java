package logictechcorp.netherex.datagen;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.datagen.client.language.NELanguageProviderEN_US;
import logictechcorp.netherex.datagen.client.model.NEBlockModelProvider;
import logictechcorp.netherex.datagen.client.model.NEItemModelProvider;
import logictechcorp.netherex.datagen.server.NEDatapackProvider;
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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetherExDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = event.createProvider(NEDatapackProvider::new).getRegistryProvider();

        // Server
        event.addProvider(new NELootTableProvider(packOutput, registries));
        event.createProvider(NELootModifiers::new);
        event.createProvider(NERecipeProvider::new);
        event.addProvider(new NEBiomeTagsProvider(packOutput, registries, existingFileHelper));
        NEBlockTagsProvider blockTagsProvider = new NEBlockTagsProvider(packOutput, registries, existingFileHelper);
        event.addProvider(blockTagsProvider);
        event.addProvider(new NEItemTagsProvider(packOutput, registries, blockTagsProvider.contentsGetter(), existingFileHelper));
        event.addProvider(NEAdvancements.create(packOutput, registries));

        // Client
        event.addProvider(new NEBlockModelProvider(packOutput, existingFileHelper));
        event.addProvider(new NEItemModelProvider(packOutput, existingFileHelper));
        event.createProvider(NELanguageProviderEN_US::new);
    }
}
