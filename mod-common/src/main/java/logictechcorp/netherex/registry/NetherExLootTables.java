package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class NetherExLootTables
{
    private static final Set<ResourceKey<LootTable>> LOOT_TABLE_KEYS = new HashSet<>();

    public static final ResourceKey<LootTable> SALAMANDER_ORANGE = register("entities/salamander/orange");
    public static final ResourceKey<LootTable> SALAMANDER_BLACK = register("entities/salamander/black");
    public static final ResourceKey<LootTable> MOGUS_BROWN = register("entities/mogus/brown");
    public static final ResourceKey<LootTable> MOGUS_RED = register("entities/mogus/red");
    public static final ResourceKey<LootTable> FLAEMOTH_CRIMSON = register("entities/flaemoth/crimson");
    public static final ResourceKey<LootTable> FLAEMOTH_WARPED = register("entities/flaemoth/warped");

    public static final ResourceKey<LootTable> NETHERITE_HORSE_ARMOR_ADDITION = register("chests/netherite_horse_armor_addition");

    private static ResourceKey<LootTable> register(String name)
    {
        return register(ResourceKey.create(Registries.LOOT_TABLE, NetherExConstants.resource(name)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> name)
    {
        if (LOOT_TABLE_KEYS.add(name))
        {
            return name;
        }
        else
        {
            throw new IllegalArgumentException(name.location() + " is already a registered loot table!");
        }
    }

}
