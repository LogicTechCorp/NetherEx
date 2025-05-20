package logictechcorp.netherex.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public record NEStructureTracker(ResourceKey<Structure> structure, GlobalPos target)
{
    public static final Codec<NEStructureTracker> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                            ResourceKey.codec(Registries.STRUCTURE)
                                    .fieldOf("structure")
                                    .forGetter(NEStructureTracker::structure),
                            GlobalPos.CODEC
                                    .fieldOf("target")
                                    .forGetter(NEStructureTracker::target)
                    )
                    .apply(instance, NEStructureTracker::new)
    );
}
