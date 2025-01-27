package logictechcorp.netherex.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExRegistries;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record NEMogusVariant(ResourceLocation texture, ResourceLocation lootTable, int spawnWeight)
{
    public static final Codec<NEMogusVariant> ELEMENT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            ResourceLocation.CODEC.fieldOf("texture").forGetter(NEMogusVariant::texture),
                            ResourceLocation.CODEC.fieldOf("loot_table").forGetter(NEMogusVariant::lootTable),
                            Codec.INT.fieldOf("spawn_weight").forGetter(NEMogusVariant::spawnWeight)
                    )
                    .apply(instance, NEMogusVariant::new)
    );
    public static final Codec<NEMogusVariant> NETWORK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            ResourceLocation.CODEC.fieldOf("texture").forGetter(NEMogusVariant::texture)
                    )
                    .apply(instance, (texture) -> new NEMogusVariant(texture, NetherExConstants.resource("empty"), 0))
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, NEMogusVariant> DATA_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            NEMogusVariant::texture,
            ResourceLocation.STREAM_CODEC,
            NEMogusVariant::lootTable,
            ByteBufCodecs.INT,
            NEMogusVariant::spawnWeight,
            NEMogusVariant::new
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<NEMogusVariant>> DATA_HOLDER_STREAM_CODEC = ByteBufCodecs.holder(
            NetherExRegistries.Keys.MOGUS_VARIANT,
            DATA_STREAM_CODEC
    );
}