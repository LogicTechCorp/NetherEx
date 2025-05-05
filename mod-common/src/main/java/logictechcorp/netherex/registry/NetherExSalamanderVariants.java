package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NESalamanderVariant;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;

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
        register(context, ORANGE, ORANGE.location().getPath(), 20);
        register(context, BLACK, BLACK.location().getPath(), 1);
    }

    public static Holder<NESalamanderVariant> getRandomSpawnVariant(RegistryAccess registryAccess, RandomSource randomSource)
    {
        SimpleWeightedRandomList.Builder<Holder.Reference<NESalamanderVariant>> spawnVariantsBuilder = new SimpleWeightedRandomList.Builder<>();
        Registry<NESalamanderVariant> registry = registryAccess.registryOrThrow(NetherExRegistries.Keys.SALAMANDER_VARIANT);
        registry.holders().forEach(salamanderVariantRef -> spawnVariantsBuilder.add(salamanderVariantRef, salamanderVariantRef.value().spawnWeight()));
        Optional<Holder.Reference<NESalamanderVariant>> key = spawnVariantsBuilder.build().getRandomValue(randomSource);
        return key.orElseGet(() -> registry.getHolder(ORANGE).orElseThrow());
    }

    private static void register(BootstrapContext<NESalamanderVariant> context, ResourceKey<NESalamanderVariant> Key, String name, int spawnWeight)
    {
        ResourceLocation texture = NetherExConstants.resource("textures/entity/salamander/" + name + ".png");
        ResourceLocation lootTable = NetherExConstants.resource("entities/salamander/" + name);
        context.register(Key, new NESalamanderVariant(texture, lootTable, spawnWeight));
    }

    private static ResourceKey<NESalamanderVariant> createKey(String name)
    {
        return ResourceKey.create(NetherExRegistries.Keys.SALAMANDER_VARIANT, NetherExConstants.resource(name));
    }
}