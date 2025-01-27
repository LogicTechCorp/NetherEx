package logictechcorp.netherex.platform;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

public interface NEEntityHelper
{
    NEEntityHelper INSTANCE = Services.load(NEEntityHelper.class);

    <E extends Mob> void registerSpawnPlacement(Supplier<EntityType<E>> entityTypeSupplier, SpawnPlacementType spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<E> predicate);

    record SpawnPlacementData<E extends Mob>(SpawnPlacementType placement, Heightmap.Types heightMap, SpawnPlacements.SpawnPredicate<E> predicate)
    {
    }
}
