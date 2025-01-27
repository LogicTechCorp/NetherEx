package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemoth;
import logictechcorp.netherex.entity.animal.NEMogus;
import logictechcorp.netherex.entity.animal.NESalamander;
import logictechcorp.netherex.entity.monster.NESpinout;
import logictechcorp.netherex.entity.monster.NEWisp;
import logictechcorp.netherex.entity.projectile.NEAshenArrow;
import logictechcorp.netherex.platform.NEEntityHelper;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NetherExEntityTypes
{
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(BuiltInRegistries.ENTITY_TYPE, NetherExConstants.MOD_ID);

    public static final Map<Supplier<EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier>> ENTITY_ATTRIBUTES = new HashMap<>();

    public static final RegistryObject<EntityType<?>, EntityType<NESpinout>> SPINOUT = registerEntityType("spinout", NESpinout::createAttributes, EntityType.Builder.of(NESpinout::new, MobCategory.MONSTER).sized(0.55f, 1.8f).fireImmune());
    public static final RegistryObject<EntityType<?>, EntityType<NEWisp>> WISP = registerEntityType("wisp", NEWisp::createAttributes, EntityType.Builder.of(NEWisp::new, MobCategory.MONSTER).sized(0.55f, 0.55f).fireImmune());
    public static final RegistryObject<EntityType<?>, EntityType<NESalamander>> SALAMANDER = registerEntityType("salamander", NESalamander::createAttributes, EntityType.Builder.of(NESalamander::new, MobCategory.MONSTER).sized(1.25f, 0.5f).fireImmune());
    public static final RegistryObject<EntityType<?>, EntityType<NEMogus>> MOGUS = registerEntityType("mogus", NEMogus::createAttributes, EntityType.Builder.of(NEMogus::new, MobCategory.AMBIENT).sized(0.5f, 0.9f).fireImmune());
    public static final RegistryObject<EntityType<?>, EntityType<NEFlaemoth>> FLAEMOTH = registerEntityType("flaemoth", NEFlaemoth::createAttributes, EntityType.Builder.of(NEFlaemoth::new, MobCategory.AMBIENT).sized(0.4f, 0.5f).fireImmune());

    public static final RegistryObject<EntityType<?>, EntityType<NEAshenArrow>> ASHEN_ARROW = registerEntityType("ashen_arrow", EntityType.Builder.<NEAshenArrow>of(NEAshenArrow::new, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(20));

    public static void initialize()
    {
        registerSpawnPlacements();
    }

    private static void registerSpawnPlacements()
    {
        NEEntityHelper.INSTANCE.registerSpawnPlacement(SPINOUT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NESpinout::checkSpinoutSpawnRules);
        NEEntityHelper.INSTANCE.registerSpawnPlacement(WISP, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NEWisp::checkWispSpawnRules);
        NEEntityHelper.INSTANCE.registerSpawnPlacement(SALAMANDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NESalamander::checkSalamanderSpawnRules);
        NEEntityHelper.INSTANCE.registerSpawnPlacement(MOGUS, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NEMogus::checkMogusSpawnRules);
        NEEntityHelper.INSTANCE.registerSpawnPlacement(FLAEMOTH, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NEFlaemoth::checkFlaemothSpawnRules);
    }

    private static <E extends Entity> RegistryObject<EntityType<?>, EntityType<E>> registerEntityType(String name, EntityType.Builder<E> builder)
    {
        return ENTITY_TYPES.register(name, () -> builder.build(createKey(name)));
    }

    private static <E extends LivingEntity> RegistryObject<EntityType<?>, EntityType<E>> registerEntityType(String name, Supplier<AttributeSupplier> attributeSupplierSupplier, EntityType.Builder<E> builder)
    {
        RegistryObject<EntityType<?>, EntityType<E>> entityType = ENTITY_TYPES.register(name, () -> builder.build(createKey(name)));
        ENTITY_ATTRIBUTES.put(entityType::get, attributeSupplierSupplier);
        return entityType;
    }

    private static ResourceKey<EntityType<?>> createKey(String name)
    {
        return ResourceKey.create(Registries.ENTITY_TYPE, NetherExConstants.resource(name));
    }
}