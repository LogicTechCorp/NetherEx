package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import logictechcorp.netherex.NetherExConstants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@AutoService(NEEntityHelper.class)
public class NEEntityHelperNeoForge implements NEEntityHelper
{
    private static final Map<Supplier<? extends EntityType<?>>, SpawnPlacementData<?>> SPAWN_PLACEMENTS = new HashMap<>();

    @Override
    public <E extends Mob> void registerSpawnPlacement(Supplier<EntityType<E>> entityTypeSupplier, SpawnPlacementType spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<E> predicate)
    {
        SPAWN_PLACEMENTS.put(entityTypeSupplier, new SpawnPlacementData<>(spawnPlacementType, heightmapType, predicate));
    }

    @EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onRegisterSpawnPlacements(RegisterSpawnPlacementsEvent event)
        {
            SPAWN_PLACEMENTS.forEach((entityType, spawnPlacementData) ->
                    event.register(
                            (EntityType<Mob>) entityType.get(),
                            spawnPlacementData.placement(),
                            spawnPlacementData.heightMap(),
                            (SpawnPlacements.SpawnPredicate<Mob>) spawnPlacementData.predicate(),
                            RegisterSpawnPlacementsEvent.Operation.REPLACE
                    )
            );
        }
    }
}
