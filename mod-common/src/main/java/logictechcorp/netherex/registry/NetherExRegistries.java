package logictechcorp.netherex.registry;

import com.mojang.serialization.Codec;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemothVariant;
import logictechcorp.netherex.entity.animal.NEMogusVariant;
import logictechcorp.netherex.entity.animal.NESalamanderVariant;
import logictechcorp.netherex.platform.registration.registries.DatapackRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class NetherExRegistries
{
    public static final List<DatapackRegistry<?>> DATAPACK_REGISTRIES = new ArrayList<>();

    public static final DatapackRegistry<NESalamanderVariant> SALAMANDER_VARIANT = register(
            Keys.SALAMANDER_VARIANT,
            NESalamanderVariant.ELEMENT_CODEC,
            NESalamanderVariant.NETWORK_CODEC,
            NetherExSalamanderVariants::bootstrap
    );
    public static final DatapackRegistry<NEMogusVariant> MOGUS_VARIANT = register(
            Keys.MOGUS_VARIANT,
            NEMogusVariant.ELEMENT_CODEC,
            NEMogusVariant.NETWORK_CODEC,
            NetherExMogusVariants::bootstrap
    );
    public static final DatapackRegistry<NEFlaemothVariant> FLAEMOTH_VARIANT = register(
            Keys.FLAEMOTH_VARIANT,
            NEFlaemothVariant.ELEMENT_CODEC,
            NEFlaemothVariant.NETWORK_CODEC,
            NetherExFlaemothVariants::bootstrap
    );

    public static void initialize()
    {

    }

    private static <T> DatapackRegistry<T> register(ResourceKey<Registry<T>> key, @Nonnull Codec<T> elementCodec, @Nullable Codec<T> networkCodec, @Nullable RegistrySetBuilder.RegistryBootstrap<T> bootstrap)
    {
        DatapackRegistry<T> datapackRegistry = DatapackRegistry.builder(key)
                .withElementCodec(elementCodec)
                .withNetworkCodec(networkCodec)
                .withBootstrap(bootstrap)
                .build();
        DATAPACK_REGISTRIES.add(datapackRegistry);
        return datapackRegistry;
    }

    public static class Keys
    {
        public static final ResourceKey<Registry<NESalamanderVariant>> SALAMANDER_VARIANT = createRegistryKey("salamander_variant");
        public static final ResourceKey<Registry<NEMogusVariant>> MOGUS_VARIANT = createRegistryKey("mogus_variant");
        public static final ResourceKey<Registry<NEFlaemothVariant>> FLAEMOTH_VARIANT = createRegistryKey("flaemoth_variant");

        private static <T> ResourceKey<Registry<T>> createRegistryKey(String name)
        {
            return ResourceKey.createRegistryKey(NetherExConstants.resource(name));
        }
    }
}