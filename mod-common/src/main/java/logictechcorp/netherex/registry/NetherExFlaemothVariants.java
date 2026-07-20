package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemothVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;
import java.util.Optional;

public class NetherExFlaemothVariants
{
    public static final ResourceKey<NEFlaemothVariant> CRIMSON = createKey("crimson");
    public static final ResourceKey<NEFlaemothVariant> WARPED = createKey("warped");

    public static void initialize()
    {
    }

    public static void bootstrap(BootstrapContext<NEFlaemothVariant> context)
    {
        register(context, CRIMSON, CRIMSON.identifier().getPath(), Biomes.CRIMSON_FOREST, 10);
        register(context, WARPED, WARPED.identifier().getPath(), Biomes.WARPED_FOREST, 10);
    }

    public static Holder<NEFlaemothVariant> getBiomeSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome, RandomSource randomSource)
    {
        Registry<NEFlaemothVariant> registry = registryAccess.lookupOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);
        List<Holder.Reference<NEFlaemothVariant>> flaemothVariants = registry.listElements().filter(flaemoth -> flaemoth.value().spawnBiomes().contains(biome)).toList();
        Optional<Holder.Reference<NEFlaemothVariant>> randomVariant = WeightedRandom.getRandomItem(randomSource, flaemothVariants, flaemothVariantRef -> flaemothVariantRef.value().spawnWeight());
        return randomVariant.orElseGet(() -> registry.get(CRIMSON).orElseThrow());
    }

    public static Holder<NEFlaemothVariant> getRandomSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        Registry<NEFlaemothVariant> registry = registryAccess.lookupOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);
        List<Holder.Reference<NEFlaemothVariant>> flaemothVariants = registry.listElements().toList();
        Optional<Holder.Reference<NEFlaemothVariant>> randomVariant = WeightedRandom.getRandomItem(randomSource, flaemothVariants, flaemothVariantRef -> flaemothVariantRef.value().spawnWeight());
        return randomVariant.orElseGet(() -> registry.get(CRIMSON).orElseThrow());
    }

    private static void register(BootstrapContext<NEFlaemothVariant> context, ResourceKey<NEFlaemothVariant> Key, String name, ResourceKey<Biome> spawnBiomes, int spawnWeight)
    {
        Identifier texture = NetherExConstants.identifier("textures/entity/flaemoth/" + name + ".png");
        Identifier lootTable = NetherExConstants.identifier("entities/flaemoth/" + name);
        context.register(Key, new NEFlaemothVariant(texture, lootTable, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(spawnBiomes)), spawnWeight));
    }

    private static ResourceKey<NEFlaemothVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.FLAEMOTH_VARIANT, NetherExConstants.identifier(name));
    }
}