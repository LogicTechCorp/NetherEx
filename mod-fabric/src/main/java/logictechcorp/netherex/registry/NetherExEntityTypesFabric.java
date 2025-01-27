package logictechcorp.netherex.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class NetherExEntityTypesFabric
{
    public static void registerAttributes()
    {
        NetherExEntityTypes.ENTITY_ATTRIBUTES.forEach((entityTypeSupplier, attributeSupplierSupplier) -> FabricDefaultAttributeRegistry.register(entityTypeSupplier.get(), attributeSupplierSupplier.get()));
    }
}