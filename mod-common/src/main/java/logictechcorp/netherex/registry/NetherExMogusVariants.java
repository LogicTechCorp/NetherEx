package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEMogusVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;

import java.util.List;
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
        register(context, BROWN, BROWN.identifier().getPath(), 10);
        register(context, RED, RED.identifier().getPath(), 10);
    }

    public static Holder<NEMogusVariant> getRandomSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        Registry<NEMogusVariant> registry = registryAccess.lookupOrThrow(NetherExRegistries.Keys.MOGUS_VARIANT);
        List<Holder.Reference<NEMogusVariant>> mogusVariants = registry.listElements().toList();
        Optional<Holder.Reference<NEMogusVariant>> randomVariant = WeightedRandom.getRandomItem(randomSource, mogusVariants, mogusVariantRef -> mogusVariantRef.value().spawnWeight());
        return randomVariant.orElseGet(() -> registry.get(BROWN).orElseThrow());
    }

    private static void register(BootstrapContext<NEMogusVariant> context, ResourceKey<NEMogusVariant> Key, String name, int spawnWeight)
    {
        Identifier texture = NetherExConstants.identifier("textures/entity/mogus/" + name + ".png");
        Identifier lootTable = NetherExConstants.identifier("entities/mogus/" + name);
        context.register(Key, new NEMogusVariant(texture, lootTable, spawnWeight));
    }

    private static ResourceKey<NEMogusVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.MOGUS_VARIANT, NetherExConstants.identifier(name));
    }
}