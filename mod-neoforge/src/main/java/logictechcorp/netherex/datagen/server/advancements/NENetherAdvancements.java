package logictechcorp.netherex.datagen.server.advancements;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.*;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;
import java.util.function.Consumer;

public class NENetherAdvancements implements AdvancementSubProvider
{
    public NENetherAdvancements()
    {

    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer)
    {
        HolderGetter<Block> blocks = registries.lookupOrThrow(Registries.BLOCK);
        HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);
        HolderGetter<Biome> biomes = registries.lookupOrThrow(Registries.BIOME);
        HolderGetter<EntityType<?>> entities = registries.lookupOrThrow(Registries.ENTITY_TYPE);

        AdvancementHolder netherExRoot = Advancement.Builder
                .advancement()
                .display(
                        NetherExBlocks.WARPED_NETHER_BRICKS.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_nether.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_nether.description"),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/nether.png"),
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("enter_nether", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(Level.NETHER))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/root");

        // Ruthless Sands
        AdvancementHolder enterRuthlessSandsBiome = Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExBlocks.GLOOMY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.RUTHLESS_SANDS.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.RUTHLESS_SANDS))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.RUTHLESS_SANDS.location().getPath() + "_biome");

        Advancement.Builder.advancement().parent(enterRuthlessSandsBiome)
                .display(
                        Items.QUARTZ,
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_spinout.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_spinout.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("kill_spinout", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NetherExEntityTypes.SPINOUT.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/kill_spinout");

        Advancement.Builder.advancement().parent(enterRuthlessSandsBiome)
                .display(
                        NetherExItems.WISP_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_wisp.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_wisp.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("kill_wisp", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NetherExEntityTypes.WISP.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/kill_wisp");

        // Torrid Wasteland
        AdvancementHolder enterTorridWastelandBiome = Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExBlocks.FIERY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.TORRID_WASTELAND.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.TORRID_WASTELAND))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.TORRID_WASTELAND.location().getPath() + "_biome");

        Advancement.Builder.advancement().parent(enterTorridWastelandBiome)
                .display(
                        NetherExItems.SALAMANDER_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_salamander.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_salamander.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("kill_salamander", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NetherExEntityTypes.SALAMANDER.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/kill_salamander");

        Advancement.Builder.advancement().parent(enterTorridWastelandBiome)
                .display(
                        Items.MAGMA_CREAM,
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.tame_salamander.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.tame_salamander.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("tame_salamander", TameAnimalTrigger.TriggerInstance.tamedAnimal(EntityPredicate.Builder.entity().of(NetherExEntityTypes.SALAMANDER.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/tame_salamander");

        // Fungi Forest
        AdvancementHolder enterFungiForestBiome = Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExBlocks.LIVELY_NETHERRACK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion(NetherExBiomes.FUNGI_FOREST.location().toString(), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(biomes.getOrThrow(NetherExBiomes.FUNGI_FOREST))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/enter_" + NetherExBiomes.FUNGI_FOREST.location().getPath() + "_biome");

        Advancement.Builder.advancement().parent(enterFungiForestBiome)
                .display(
                        NetherExItems.MOGUS_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_mogus.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_mogus.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("kill_mogus", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NetherExEntityTypes.MOGUS.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/kill_mogus");

        // Shroomlights
        AdvancementHolder shearShroomlights = Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        Blocks.SHROOMLIGHT,
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.shear_shroomlights.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.shear_shroomlights.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("shear_shroomlight", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                        LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(Blocks.SHROOMLIGHT)),
                        ItemPredicate.Builder.item().of(Items.SHEARS)
                ))
                .addCriterion("shear_twisted_shroomlight", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                        LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(NetherExBlocks.TWISTED_SHROOMLIGHT.get())),
                        ItemPredicate.Builder.item().of(Items.SHEARS)
                ))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, NetherExConstants.MOD_ID + ":nether/shear_shroomlights");

        Advancement.Builder.advancement().parent(shearShroomlights)
                .display(
                        NetherExItems.SHROOMFRUIT.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.plant_shroomfruits.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.plant_shroomfruits.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("plant_shroomfruit", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(NetherExBlocks.SHROOMSTEM.get()))
                .addCriterion("plant_twisted_shroomfruit", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(NetherExBlocks.TWISTED_SHROOMSTEM.get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, NetherExConstants.MOD_ID + ":nether/plant_shroomfruits");

        Advancement.Builder.advancement().parent(shearShroomlights)
                .display(
                        NetherExBlocks.SHROOMSTEM.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.consume_shroomfruits.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.consume_shroomfruits.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("consume_shroomfruit", ConsumeItemTrigger.TriggerInstance.usedItem(NetherExItems.SHROOMFRUIT.get()))
                .addCriterion("consume_twisted_shroomfruit", ConsumeItemTrigger.TriggerInstance.usedItem(NetherExItems.TWISTED_SHROOMFRUIT.get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, NetherExConstants.MOD_ID + ":nether/consume_shroomfruits");

        // Hoglin Tusk
        AdvancementHolder pickUpHoglinTusk = Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExItems.HOGLIN_TUSK.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.pick_up_hoglin_tusk.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.pick_up_hoglin_tusk.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("pick_up_hoglin_tusk", PickedUpItemTrigger.TriggerInstance.thrownItemPickedUpByPlayer(
                        Optional.empty(),
                        Optional.of(ItemPredicate.Builder.item().of(NetherExItems.HOGLIN_TUSK.get()).build()),
                        Optional.empty()
                ))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/pick_up_hoglin_tusk");

        AdvancementHolder brewPotionOfKnocking = Advancement.Builder.advancement().parent(pickUpHoglinTusk)
                .display(
                        Items.POTION,
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.brew_potion_of_knocking.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.brew_potion_of_knocking.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("brew_potion_of_knocking", CriteriaTriggers.BREWED_POTION.createCriterion(new BrewedPotionTrigger.TriggerInstance(Optional.empty(), Optional.of(NetherExPotions.KNOCKING.asHolder()))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/brew_potion_of_knocking");

        Advancement.Builder.advancement().parent(brewPotionOfKnocking)
                .display(
                        Items.POTION,
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.brew_potion_of_shrugging.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.brew_potion_of_shrugging.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("brew_potion_of_shrugging", CriteriaTriggers.BREWED_POTION.createCriterion(new BrewedPotionTrigger.TriggerInstance(Optional.empty(), Optional.of(NetherExPotions.SHRUGGING.asHolder()))))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/brew_potion_of_shrugging");

        // Miscellaneous
        Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExItems.FLAEMOTH_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_flaemoth.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.kill_flaemoth.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("kill_flaemoth", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(NetherExEntityTypes.FLAEMOTH.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/kill_flaemoth");

        Advancement.Builder.advancement().parent(netherExRoot)
                .display(
                        NetherExBlocks.KILN.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.craft_kiln.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".nether.craft_kiln.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("craft_kiln", RecipeCraftedTrigger.TriggerInstance.craftedItem(getRecipeKey(NetherExBlocks.KILN.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":nether/craft_kiln");
    }

    protected static ResourceLocation getRecipeKey(ItemLike itemLike)
    {
        return NetherExConstants.resource(getItemName(itemLike));
    }

    protected static String getItemName(ItemLike itemLike)
    {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).getPath();
    }
}
