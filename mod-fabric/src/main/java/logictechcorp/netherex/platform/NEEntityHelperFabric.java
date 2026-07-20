package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import logictechcorp.netherex.NetherExConstants;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityDataRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
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
    public <E> EntityDataSerializer<Holder<E>> registerEntityDataSerializer(String name, StreamCodec<? super RegistryFriendlyByteBuf, Holder<E>> codec)
    {
        EntityDataSerializer<Holder<E>> entityDataSerializer = EntityDataSerializer.forValueType(codec);
        FabricEntityDataRegistry.register(NetherExConstants.identifier(name), entityDataSerializer);
        return entityDataSerializer;
    }

    @Override
    public <E extends Mob> void registerSpawnPlacement(Supplier<EntityType<E>> entityTypeSupplier, SpawnPlacementType spawnPlacementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<E> predicate)
    {
        SpawnPlacements.register(entityTypeSupplier.get(), spawnPlacementType, heightmapType, predicate);
    }
}
