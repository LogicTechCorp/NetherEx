package logictechcorp.netherex.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record NEGlobalPosTracker(Optional<GlobalPos> target)
{
    public static final Codec<NEGlobalPosTracker> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                            GlobalPos.CODEC.optionalFieldOf("target")
                                    .forGetter(NEGlobalPosTracker::target)
                    )
                    .apply(instance, NEGlobalPosTracker::new)
    );

    public static final StreamCodec<ByteBuf, NEGlobalPosTracker> STREAM_CODEC = StreamCodec.composite(
            GlobalPos.STREAM_CODEC.apply(ByteBufCodecs::optional),
            NEGlobalPosTracker::target, NEGlobalPosTracker::new
    );
}
