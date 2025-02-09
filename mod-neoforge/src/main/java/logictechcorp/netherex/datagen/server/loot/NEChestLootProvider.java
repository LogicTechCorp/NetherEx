package logictechcorp.netherex.datagen.server.loot;

import logictechcorp.netherex.registry.NetherExItems;
import logictechcorp.netherex.registry.NetherExLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
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
        consumer.accept(NetherExLootTables.NETHERITE_HORSE_ARMOR_ADDITION, singularLoot(NetherExItems.NETHERITE_HORSE_ARMOR.get()));
    }

    private LootTable.Builder singularLoot(ItemLike item)
    {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(item)));
    }
}
