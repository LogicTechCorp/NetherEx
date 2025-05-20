package logictechcorp.netherex.event;

import logictechcorp.netherex.registry.NetherExItems;
import logictechcorp.netherex.world.level.storage.loot.functions.NECompassStructureTrackerFunction;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class NELootEventsFabric
{
    public static void register()
    {
        onLoadLootTables();
    }

    static void onLoadLootTables()
    {
        LootTableEvents.MODIFY.register((lootTableKey, builder, lootTableSource, registries) ->
        {
            if (lootTableSource.isBuiltin())
            {
                HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);

                EntityType.HOGLIN.getDefaultLootTable().ifPresent(defaultLootTable ->
                {
                    if (lootTableKey == defaultLootTable)
                    {
                        builder.withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(NetherExItems.RIBS.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                        .apply(SmeltItemFunction.smelted().when(shouldSmeltLoot(registries)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        );
                        builder.withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(NetherExItems.HOGLIN_TUSK.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F)))
                                )
                        );
                    }
                });

                if (lootTableKey == BuiltInLootTables.BASTION_OTHER)
                {
                    builder.withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0f))
                            .add(LootItem.lootTableItem(Items.COMPASS)
                                    .apply(NECompassStructureTrackerFunction.makeCompassGlobalPosTracker()
                                            .structure(structures.get(BuiltinStructures.FORTRESS).get())
                                            .searchRadius(50)
                                            .skipKnownStructures(false)
                                    )
                            )
                    );
                }
            }
        });
    }

    private static LootPool.Builder singularLoot(ItemLike item)
    {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(item));
    }

    private static AnyOfCondition.Builder shouldSmeltLoot(HolderLookup.Provider registries)
    {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))), LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(registrylookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))))))));
    }
}
