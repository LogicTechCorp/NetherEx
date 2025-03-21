package logictechcorp.netherex.datagen.server.loot;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class NELootModifiers extends GlobalLootModifierProvider
{
    public NELootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, NetherExConstants.MOD_ID);
    }

    @Override
    protected void start()
    {
        add("add_netherite_horse_armor_to_bastion_treasure", new AddTableLootModifier(
                new LootItemCondition[]{LootTableIdCondition.builder(BuiltInLootTables.BASTION_TREASURE.location()).build()},
                NetherExLootTables.NETHERITE_HORSE_ARMOR_ADDITION)
        );
    }
}
