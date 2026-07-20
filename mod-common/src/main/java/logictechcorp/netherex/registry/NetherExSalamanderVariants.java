package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NESalamanderVariant;
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

public class NetherExSalamanderVariants
{
    public static final ResourceKey<NESalamanderVariant> ORANGE = createKey("orange");
    public static final ResourceKey<NESalamanderVariant> BLACK = createKey("black");

    public static void initialize()
    {
    }

    public static void bootstrap(BootstrapContext<NESalamanderVariant> context)
    {
        register(context, ORANGE, ORANGE.identifier().getPath(), 20);
        register(context, BLACK, BLACK.identifier().getPath(), 1);
    }

    public static Holder<NESalamanderVariant> getRandomSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        Registry<NESalamanderVariant> registry = registryAccess.lookupOrThrow(NetherExRegistries.Keys.SALAMANDER_VARIANT);
        List<Holder.Reference<NESalamanderVariant>> salamanderVariants = registry.listElements().toList();
        Optional<Holder.Reference<NESalamanderVariant>> randomVariant = WeightedRandom.getRandomItem(randomSource, salamanderVariants, salamanderVariantRef -> salamanderVariantRef.value().spawnWeight());
        return randomVariant.orElseGet(() -> registry.get(ORANGE).orElseThrow());
    }

    private static void register(BootstrapContext<NESalamanderVariant> context, ResourceKey<NESalamanderVariant> Key, String name, int spawnWeight)
    {
        Identifier texture = NetherExConstants.identifier("textures/entity/salamander/" + name + ".png");
        Identifier lootTable = NetherExConstants.identifier("entities/salamander/" + name);
        context.register(Key, new NESalamanderVariant(texture, lootTable, spawnWeight));
    }

    private static ResourceKey<NESalamanderVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.SALAMANDER_VARIANT, NetherExConstants.identifier(name));
    }
}