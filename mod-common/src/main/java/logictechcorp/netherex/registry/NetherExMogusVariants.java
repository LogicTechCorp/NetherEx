package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEMogusVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;

import java.util.Optional;

public class NetherExMogusVariants
{
    public static final ResourceKey<NEMogusVariant> BROWN = createKey("brown");
    public static final ResourceKey<NEMogusVariant> RED = createKey("red");

    public static void initialize()
    {
    }

    public static void bootstrap(BootstrapContext<NEMogusVariant> context)
    {
        register(context, BROWN, BROWN.location().getPath(), 10);
        register(context, RED, RED.location().getPath(), 10);
    }

    public static Holder<NEMogusVariant> getBiomeSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        SimpleWeightedRandomList.Builder<Holder.Reference<NEMogusVariant>> spawnVariantsBuilder = new SimpleWeightedRandomList.Builder<>();
        Registry<NEMogusVariant> registry = registryAccess.registryOrThrow(NetherExRegistries.Keys.MOGUS_VARIANT);
        registry.holders().forEach(mogusVariantRef -> spawnVariantsBuilder.add(mogusVariantRef, mogusVariantRef.value().spawnWeight()));
        Optional<Holder.Reference<NEMogusVariant>> key = spawnVariantsBuilder.build().getRandomValue(randomSource);
        return key.orElseGet(() -> registry.getHolder(BROWN).orElseThrow());
    }

    private static void register(BootstrapContext<NEMogusVariant> context, ResourceKey<NEMogusVariant> Key, String name, int spawnWeight)
    {
        ResourceLocation texture = NetherExConstants.resource("textures/entity/mogus/" + name + ".png");
        ResourceLocation lootTable = NetherExConstants.resource("entities/mogus/" + name);
        context.register(Key, new NEMogusVariant(texture, lootTable, spawnWeight));
    }

    private static ResourceKey<NEMogusVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.MOGUS_VARIANT, NetherExConstants.resource(name));
    }
}