package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetherExEntityTypesNeoForge
{
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event)
    {
        NetherExEntityTypes.ENTITY_ATTRIBUTES.forEach((entityTypeSupplier, attributeSupplierSupplier) -> event.put(entityTypeSupplier.get(), attributeSupplierSupplier.get()));
    }
}