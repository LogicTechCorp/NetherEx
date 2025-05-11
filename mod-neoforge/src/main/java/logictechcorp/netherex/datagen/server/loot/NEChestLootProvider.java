package logictechcorp.netherex.datagen.server.loot;

import logictechcorp.netherex.registry.NetherExItems;
import logictechcorp.netherex.registry.NetherExLootTables;
import logictechcorp.netherex.world.level.storage.loot.functions.CompassGlobalPosTrackerFunction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class NEChestLootProvider implements LootTableSubProvider
{
    private final HolderLookup.Provider registries;

    public NEChestLootProvider(HolderLookup.Provider inRegistries)
    {
        registries = inRegistries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer)
    {
        HolderLookup.RegistryLookup<Structure> structures = registries.lookupOrThrow(Registries.STRUCTURE);

        consumer.accept(NetherExLootTables.NETHERITE_HORSE_ARMOR_ADDITION, singularLoot(NetherExItems.NETHERITE_HORSE_ARMOR.get()));
        consumer.accept(NetherExLootTables.FORTRESS_TRACKER_COMPASS_ADDITION, LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(Items.COMPASS)
                        .apply(CompassGlobalPosTrackerFunction.makeCompassGlobalPosTracker()
                                .structure(structures.get(BuiltinStructures.FORTRESS).get())
                                .searchRadius(50)
                                .skipKnownStructures(false)
                        )
                )
        ));
    }

    private LootTable.Builder singularLoot(ItemLike item)
    {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(item)));
    }
}
