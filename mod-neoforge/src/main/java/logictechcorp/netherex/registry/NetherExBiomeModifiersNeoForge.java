package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.EnumSet;
import java.util.List;

public class NetherExBiomeModifiersNeoForge
{
    private static final ResourceKey<BiomeModifier> REMOVE_HIDDEN_SPRINGS_BIOME_MODIFIER = createKey("remove_hidden_springs_biome_modifier");
    private static final ResourceKey<BiomeModifier> REMOVE_WARPED_FUNGI = createKey("remove_warped_fungi");
    private static final ResourceKey<BiomeModifier> ADD_TWISTED_WARPED_FUNGI = createKey("add_twisted_warped_fungi");
    private static final ResourceKey<BiomeModifier> ADD_BOOMSTONE_BIOME_MODIFIER = createKey("add_boomstone_biome_modifier");
    private static final ResourceKey<BiomeModifier> ADD_FUMAROLE_BIOME_MODIFIER = createKey("add_fumarole_biome_modifier");
    private static final ResourceKey<BiomeModifier> ADD_FLAEMOTHS_BIOME_MODIFIER = createKey("add_flaemoths_biome_modifier");
    private static final ResourceKey<BiomeModifier> ADD_WISPS_BIOME_MODIFIER = createKey("add_wisps_biome_modifier");

    public static void boostrap(BootstrapContext<BiomeModifier> context)
    {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderSet<Biome> vanillaNetherBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.NETHER_WASTES),
                biomes.getOrThrow(Biomes.SOUL_SAND_VALLEY),
                biomes.getOrThrow(Biomes.BASALT_DELTAS),
                biomes.getOrThrow(Biomes.CRIMSON_FOREST),
                biomes.getOrThrow(Biomes.WARPED_FOREST)
        );

        HolderSet<Biome> vanillaNetherForestBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.CRIMSON_FOREST),
                biomes.getOrThrow(Biomes.WARPED_FOREST)
        );

        context.register(REMOVE_HIDDEN_SPRINGS_BIOME_MODIFIER, new BiomeModifiers.RemoveFeaturesBiomeModifier(
                vanillaNetherBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(NetherPlacements.SPRING_CLOSED)),
                EnumSet.of(GenerationStep.Decoration.UNDERGROUND_DECORATION)
        ));
        context.register(REMOVE_WARPED_FUNGI, new BiomeModifiers.RemoveFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.WARPED_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(TreePlacements.WARPED_FUNGI)),
                EnumSet.of(GenerationStep.Decoration.VEGETAL_DECORATION)
        ));
        context.register(ADD_TWISTED_WARPED_FUNGI, new BiomeModifiers.AddFeaturesBiomeModifier(
                vanillaNetherBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(NetherExFeaturePlacements.TWISTED_WARPED_FUNGI)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION
        ));
        context.register(ADD_BOOMSTONE_BIOME_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                vanillaNetherBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(NetherExFeaturePlacements.ORE_BOOMSTONE)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION
        ));
        context.register(ADD_FUMAROLE_BIOME_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BASALT_DELTAS)),
                HolderSet.direct(
                        placedFeatures.getOrThrow(NetherExFeaturePlacements.BASALT_FUMAROLE),
                        placedFeatures.getOrThrow(NetherExFeaturePlacements.BLACKSTONE_FUMAROLE)
                ),
                GenerationStep.Decoration.UNDERGROUND_DECORATION
        ));
        context.register(ADD_FLAEMOTHS_BIOME_MODIFIER, new BiomeModifiers.AddSpawnsBiomeModifier(
                vanillaNetherForestBiomes,
                List.of(new MobSpawnSettings.SpawnerData(NetherExEntityTypes.FLAEMOTH.get(), 15, 1, 4))
        ));
        context.register(ADD_WISPS_BIOME_MODIFIER, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SOUL_SAND_VALLEY)),
                List.of(new MobSpawnSettings.SpawnerData(NetherExEntityTypes.WISP.get(), 15, 1, 4))
        ));
    }

    private static ResourceKey<BiomeModifier> createKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, NetherExConstants.resource(name));
    }
}
