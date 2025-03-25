package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.entity.NEKilnBlockEntity;
import logictechcorp.netherex.platform.NEBlockEntityHelper;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class NetherExBlockEntityTypes
{
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistrationProvider.get(BuiltInRegistries.BLOCK_ENTITY_TYPE, NetherExConstants.MOD_ID);

    public static final RegistryObject<BlockEntityType<?>, BlockEntityType<NEKilnBlockEntity>> KILN = registerBlockEntityType("kiln", NEKilnBlockEntity::new, () -> new Block[]{NetherExBlocks.KILN.get()});

    public static void initialize()
    {

    }

    private static <E extends BlockEntity> RegistryObject<BlockEntityType<?>, BlockEntityType<E>> registerBlockEntityType(String name, BiFunction<BlockPos, BlockState, E> createBlockEntityFunc, Supplier<Block[]> blocksSupplier)
    {
        return BLOCK_ENTITY_TYPES.register(name, () -> NEBlockEntityHelper.INSTANCE.createBlockEntityType(createBlockEntityFunc, blocksSupplier.get()));
    }
}
