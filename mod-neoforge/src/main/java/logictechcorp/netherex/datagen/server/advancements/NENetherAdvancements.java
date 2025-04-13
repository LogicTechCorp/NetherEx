package logictechcorp.netherex.datagen.server.advancements;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBiomes;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExEntityTypes;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
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
        HolderGetter<EntityType<?>> entities = registries.lookupOrThrow(Registries.ENTITY_TYPE);

        AdvancementHolder enterNetherExBiome = Advancement.Builder
                .advancement()
                .display(
                        NetherExBlocks.POLISHED_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExConstants.MOD_ID + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExConstants.MOD_ID + "_biome.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/nether.png"),
                        AdvancementType.TASK,
                        true,
                        false,
                        true
                )
                .addCriterion(NetherExBiomes.RUTHLESS_SANDS.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.RUTHLESS_SANDS))))
                .addCriterion(NetherExBiomes.TORRID_WASTELAND.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.TORRID_WASTELAND))))
                .addCriterion(NetherExBiomes.FUNGI_FOREST.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.FUNGI_FOREST))))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, NetherExConstants.MOD_ID + ":nether/root");

        AdvancementHolder enterRuthlessSandsBiome = Advancement.Builder.advancement().parent(enterNetherExBiome)
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

        Advancement.Builder.advancement().parent(enterRuthlessSandsBiome)
                .display(
                        NetherExItems.SPINOUT_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_spinout.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_spinout.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_spinout", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.SPINOUT.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/killed_spinout");

        Advancement.Builder.advancement().parent(enterRuthlessSandsBiome)
                .display(
                        NetherExItems.WISP_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_wisp.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_wisp.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_wisp", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.WISP.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/killed_wisp");

        AdvancementHolder enterTorridWastelandBiome = Advancement.Builder.advancement().parent(enterNetherExBiome)
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

        Advancement.Builder.advancement().parent(enterTorridWastelandBiome)
                .display(
                        NetherExItems.SALAMANDER_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_salamander.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_salamander.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_salamander", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.SALAMANDER.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/killed_salamander");

        Advancement.Builder.advancement().parent(enterTorridWastelandBiome)
                .display(
                        NetherExItems.SALAMANDER_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.tamed_salamander.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.tamed_salamander.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("tamed_salamander", TameAnimalTrigger.TriggerInstance.tamedAnimal(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.SALAMANDER.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/tamed_salamander");

        AdvancementHolder enterFungiForestBiome = Advancement.Builder.advancement().parent(enterNetherExBiome)
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

        Advancement.Builder.advancement().parent(enterFungiForestBiome)
                .display(
                        NetherExItems.MOGUS_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_mogus.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.killed_mogus.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_mogus", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.MOGUS.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/killed_mogus");
    }
}
