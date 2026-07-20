package logictechcorp.netherex.registry;

import com.mojang.serialization.MapCodec;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.world.level.storage.loot.functions.NECompassStructureTrackerFunction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.function.Supplier;

public class NetherExLootFunctions
{
    public static final RegistrationProvider<MapCodec<? extends LootItemFunction>> LOOT_FUNCTION = RegistrationProvider.get(BuiltInRegistries.LOOT_FUNCTION_TYPE, NetherExConstants.MOD_ID);

    public static final RegistryObject<MapCodec<? extends LootItemFunction>, MapCodec<? extends LootItemFunction>> COMPASS_STRUCTURE_TRACKER_FUNCTION = registerLootFunction("compass_structure_tracker_function", () -> NECompassStructureTrackerFunction.CODEC);

    public static void initialize()
    {

    }

    public static RegistryObject<MapCodec<? extends LootItemFunction>, MapCodec<? extends LootItemFunction>> registerLootFunction(String name, Supplier<MapCodec<? extends LootItemFunction>> lootItemFunctionTypeSupplier)
    {
        return LOOT_FUNCTION.register(name, lootItemFunctionTypeSupplier);
    }
}
