package logictechcorp.netherex.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;

public record NEFlaemothVariant(Identifier texture, Identifier lootTable, HolderSet<Biome> spawnBiomes, int spawnWeight)
{
    public static final Codec<NEFlaemothVariant> ELEMENT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("texture").forGetter(NEFlaemothVariant::texture),
                            Identifier.CODEC.fieldOf("loot_table").forGetter(NEFlaemothVariant::lootTable),
                            RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("spawn_biomes").forGetter(NEFlaemothVariant::spawnBiomes),
                            Codec.INT.fieldOf("spawn_weight").forGetter(NEFlaemothVariant::spawnWeight)
                    )
                    .apply(instance, NEFlaemothVariant::new)
    );
    public static final Codec<NEFlaemothVariant> NETWORK_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Identifier.CODEC.fieldOf("texture").forGetter(NEFlaemothVariant::texture)
                    )
                    .apply(instance, (texture) -> new NEFlaemothVariant(texture, NetherExConstants.identifier("empty"), HolderSet.empty(), 0))
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, NEFlaemothVariant> DATA_STREAM_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC,
            NEFlaemothVariant::texture,
            Identifier.STREAM_CODEC,
            NEFlaemothVariant::lootTable,
            ByteBufCodecs.holderSet(Registries.BIOME),
            NEFlaemothVariant::spawnBiomes,
            ByteBufCodecs.INT,
            NEFlaemothVariant::spawnWeight,
            NEFlaemothVariant::new
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<NEFlaemothVariant>> DATA_HOLDER_STREAM_CODEC = ByteBufCodecs.holder(
            NetherExRegistries.Keys.FLAEMOTH_VARIANT,
            DATA_STREAM_CODEC
    );
}