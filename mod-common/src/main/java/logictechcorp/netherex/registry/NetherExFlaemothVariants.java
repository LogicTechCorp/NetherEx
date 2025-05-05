package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemothVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

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
        register(context, CRIMSON, CRIMSON.location().getPath(), Biomes.CRIMSON_FOREST, 10);
        register(context, WARPED, WARPED.location().getPath(), Biomes.WARPED_FOREST, 10);
    }

    public static Holder<NEFlaemothVariant> getBiomeSpawnVariant(RegistryAccess registryAccess, Holder<Biome> biome, RandomSource randomSource)
    {
        SimpleWeightedRandomList.Builder<Holder.Reference<NEFlaemothVariant>> spawnVariantsBuilder = new SimpleWeightedRandomList.Builder<>();
        Registry<NEFlaemothVariant> registry = registryAccess.registryOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);
        registry.holders().filter(flaemoth -> flaemoth.value().spawnBiomes().contains(biome)).forEach(flaemothVariantRef -> spawnVariantsBuilder.add(flaemothVariantRef, flaemothVariantRef.value().spawnWeight()));
        Optional<Holder.Reference<NEFlaemothVariant>> key = spawnVariantsBuilder.build().getRandomValue(randomSource);
        return key.orElseGet(() -> registry.getHolder(CRIMSON).orElseThrow());
    }

    public static Holder<NEFlaemothVariant> getRandomSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        SimpleWeightedRandomList.Builder<Holder.Reference<NEFlaemothVariant>> spawnVariantsBuilder = new SimpleWeightedRandomList.Builder<>();
        Registry<NEFlaemothVariant> registry = registryAccess.registryOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);
        registry.holders().forEach(flaemothVariantRef -> spawnVariantsBuilder.add(flaemothVariantRef, flaemothVariantRef.value().spawnWeight()));
        Optional<Holder.Reference<NEFlaemothVariant>> key = spawnVariantsBuilder.build().getRandomValue(randomSource);
        return key.orElseGet(() -> registry.getHolder(CRIMSON).orElseThrow());
    }

    private static void register(BootstrapContext<NEFlaemothVariant> context, ResourceKey<NEFlaemothVariant> Key, String name, ResourceKey<Biome> spawnBiomes, int spawnWeight)
    {
        ResourceLocation texture = NetherExConstants.resource("textures/entity/flaemoth/" + name + ".png");
        ResourceLocation lootTable = NetherExConstants.resource("entities/flaemoth/" + name);
        context.register(Key, new NEFlaemothVariant(texture, lootTable, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(spawnBiomes)), spawnWeight));
    }

    private static ResourceKey<NEFlaemothVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.FLAEMOTH_VARIANT, NetherExConstants.resource(name));
    }
}