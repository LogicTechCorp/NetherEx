package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEFlaemothVariant;
import logictechcorp.netherex.entity.animal.NEMogusVariant;
import logictechcorp.netherex.entity.animal.NESalamanderVariant;
import logictechcorp.netherex.platform.registration.EntityDataSerializerHelper;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;

public class NetherExEntityDataSerializers
{
    public static final EntityDataSerializer<Holder<NESalamanderVariant>> SALAMANDER_VARIANT = registerSimpleEntityDataSerializer(
            "salamander_variant",
            NESalamanderVariant.DATA_HOLDER_STREAM_CODEC
    );
    public static final EntityDataSerializer<Holder<NEMogusVariant>> MOGUS_VARIANT = registerSimpleEntityDataSerializer(
            "mogus_variant",
            NEMogusVariant.DATA_HOLDER_STREAM_CODEC
    );
    public static final EntityDataSerializer<Holder<NEFlaemothVariant>> FLAEMOTH_VARIANT = registerSimpleEntityDataSerializer(
            "flaemoth_variant",
            NEFlaemothVariant.DATA_HOLDER_STREAM_CODEC
    );

    public static void initialize()
    {

    }

    private static <E> EntityDataSerializer<Holder<E>> registerSimpleEntityDataSerializer(String name, StreamCodec<? super RegistryFriendlyByteBuf, Holder<E>> codec)
    {
        return EntityDataSerializerHelper.INSTANCE.register(NetherExConstants.resource(name), EntityDataSerializer.forValueType(codec));
    }
}