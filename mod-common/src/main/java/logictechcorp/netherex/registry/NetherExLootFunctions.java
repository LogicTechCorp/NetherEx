package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.world.level.storage.loot.functions.NECompassStructureTrackerFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

import java.util.function.Supplier;

public class NetherExLootFunctions
{
    public static final RegistrationProvider<LootItemFunctionType<?>> LOOT_ITEM_FUNCTION_TYPE = RegistrationProvider.get(BuiltInRegistries.LOOT_FUNCTION_TYPE, NetherExConstants.MOD_ID);

    public static final RegistryObject<LootItemFunctionType<?>, LootItemFunctionType<NECompassStructureTrackerFunction>> COMPASS_STRUCTURE_TRACKER_FUNCTION = registerItem("compass_structure_tracker_function", () -> new LootItemFunctionType<>(NECompassStructureTrackerFunction.CODEC));

    public static void initialize()
    {

    }

    public static <I extends LootItemFunction> RegistryObject<LootItemFunctionType<?>, LootItemFunctionType<I>> registerItem(String name, Supplier<LootItemFunctionType<I>> lootItemFunctionTypeSupplier)
    {
        return LOOT_ITEM_FUNCTION_TYPE.register(name, lootItemFunctionTypeSupplier);
    }
}
