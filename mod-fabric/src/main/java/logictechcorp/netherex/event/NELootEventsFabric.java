package logictechcorp.netherex.event;

import logictechcorp.netherex.registry.NetherExItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
                if (lootTableKey == BuiltInLootTables.BASTION_TREASURE)
                {
                    builder.withPool(singularLoot(NetherExItems.NETHERITE_HORSE_ARMOR.get()));
                }
            }
        });
    }

    private static LootPool.Builder singularLoot(ItemLike item)
    {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).add(LootItem.lootTableItem(item));
    }
}
