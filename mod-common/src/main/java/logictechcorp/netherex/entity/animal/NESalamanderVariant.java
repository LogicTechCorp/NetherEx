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

public record NESalamanderVariant(ResourceLocation texture, ResourceLocation lootTable, int spawnWeight)
{
    public static final Codec<NESalamanderVariant> ELEMENT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            ResourceLocation.CODEC.fieldOf("texture").forGetter(NESalamanderVariant::texture),
                            ResourceLocation.CODEC.fieldOf("loot_table").forGetter(NESalamanderVariant::lootTable),
                            Codec.INT.fieldOf("spawn_weight").forGetter(NESalamanderVariant::spawnWeight)
                    )
                    .apply(instance, NESalamanderVariant::new)
    );
    public static final Codec<NESalamanderVariant> NETWORK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            ResourceLocation.CODEC.fieldOf("texture").forGetter(NESalamanderVariant::texture)
                    )
                    .apply(instance, (texture) -> new NESalamanderVariant(texture, NetherExConstants.resource("empty"), 0))
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, NESalamanderVariant> DATA_STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            NESalamanderVariant::texture,
            ResourceLocation.STREAM_CODEC,
            NESalamanderVariant::lootTable,
            ByteBufCodecs.INT,
            NESalamanderVariant::spawnWeight,
            NESalamanderVariant::new
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<NESalamanderVariant>> DATA_HOLDER_STREAM_CODEC = ByteBufCodecs.holder(
            NetherExRegistries.Keys.SALAMANDER_VARIANT,
            DATA_STREAM_CODEC
    );
}