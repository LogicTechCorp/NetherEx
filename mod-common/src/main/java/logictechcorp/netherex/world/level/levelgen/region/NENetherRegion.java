package logictechcorp.netherex.world.level.levelgen.region;

import com.mojang.datafixers.util.Pair;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class NENetherRegion extends Region
{
    public NENetherRegion(int weight)
    {
        super(NetherExConstants.resource("nether"), RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        addBiome(mapper, Climate.parameters(0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), NetherExBiomes.RUTHLESS_SANDS);
        addBiome(mapper, Climate.parameters(-0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.175f), NetherExBiomes.TORRID_WASTELAND);
        addBiome(mapper, Climate.parameters(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.375f), NetherExBiomes.FUNGI_FOREST);
    }
}