package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

@AutoService(NEEntityHelper.class)
public class NEEntityHelperFabric implements NEEntityHelper
{
    @Override
    public <E extends Mob> void registerSpawnPlacement(Supplier<EntityType<E>> entityTypeSupplier, SpawnPlacementType spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<E> predicate)
    {
        SpawnPlacements.register(entityTypeSupplier.get(), spawnPlacementType, heightmapType, predicate);
    }
}
