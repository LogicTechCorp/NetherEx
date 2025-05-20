package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.item.component.NEStructureTracker;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class NetherExDataComponents
{
    public static final RegistrationProvider<DataComponentType<?>> DATA_COMPONENT_TYPES = RegistrationProvider.get(BuiltInRegistries.DATA_COMPONENT_TYPE, NetherExConstants.MOD_ID);

    public static final RegistryObject<DataComponentType<?>, DataComponentType<NEStructureTracker>> STRUCTURE_TRACKER = register("structure_tracker", (builder) -> builder.persistent(NEStructureTracker.CODEC));

    public static void initialize()
    {

    }

    private static <T> RegistryObject<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder)
    {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }
}
