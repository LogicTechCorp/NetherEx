package logictechcorp.netherex.datagen.server.advancements;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBiomes;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.function.Consumer;

public class NENetherAdvancements implements AdvancementSubProvider
{
    public NENetherAdvancements()
    {

    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer)
    {
        HolderGetter<Biome> biomes = registries.lookupOrThrow(Registries.BIOME);

        AdvancementHolder enterNetherExBiome = Advancement.Builder
                .advancement()
                .display(
                        NetherExBlocks.POLISHED_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExConstants.MOD_ID + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExConstants.MOD_ID + "_biome.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/nether.png"),
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion(NetherExBiomes.RUTHLESS_SANDS.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.RUTHLESS_SANDS))))
                .addCriterion(NetherExBiomes.TORRID_WASTELAND.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.TORRID_WASTELAND))))
                .addCriterion(NetherExBiomes.FUNGI_FOREST.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.FUNGI_FOREST))))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, NetherExConstants.MOD_ID + ":nether/root");

        AdvancementHolder enterRuthlessSandsBiome = Advancement.Builder
                .advancement()
                .parent(enterNetherExBiome)
                .display(
                        NetherExBlocks.GLOOMY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.RUTHLESS_SANDS.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.RUTHLESS_SANDS))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome");

        AdvancementHolder enterTorridWastelandBiome = Advancement.Builder
                .advancement()
                .parent(enterNetherExBiome)
                .display(
                        NetherExBlocks.FIERY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.TORRID_WASTELAND.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.TORRID_WASTELAND))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome");


        AdvancementHolder enterFungiForestBiome = Advancement.Builder
                .advancement()
                .parent(enterNetherExBiome)
                .display(
                        NetherExBlocks.LIVELY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.FUNGI_FOREST.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.FUNGI_FOREST))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome");
    }
}
