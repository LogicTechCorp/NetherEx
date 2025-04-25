package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Set;
import java.util.function.Predicate;

public class NetherExBiomeModifiersFabric
{
    public static void register()
    {
        Predicate<BiomeSelectionContext> vanillaNetherBiomes = BiomeSelectors.includeByKey(
                Biomes.NETHER_WASTES,
                Biomes.SOUL_SAND_VALLEY,
                Biomes.BASALT_DELTAS,
                Biomes.CRIMSON_FOREST,
                Biomes.WARPED_FOREST
        );

        removeFeature(
                "remove_hidden_springs_biome_modification",
                vanillaNetherBiomes,
                Set.of(NetherPlacements.SPRING_CLOSED)
        );
        removeFeature("remove_warped_fungi",
                BiomeSelectors.includeByKey(Biomes.WARPED_FOREST),
                Set.of(TreePlacements.WARPED_FUNGI)
        );
        addFeature("add_twisted_warped_fungi",
                BiomeSelectors.includeByKey(Biomes.WARPED_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                Set.of(NetherExFeaturePlacements.TWISTED_WARPED_FUNGI)
        );
        addFeature(
                "add_boomstone_biome_modification",
                vanillaNetherBiomes,
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                Set.of(NetherExFeaturePlacements.ORE_BOOMSTONE)
        );
        addFeature(
                "add_fumaroles_biome_modification",
                BiomeSelectors.includeByKey(Biomes.BASALT_DELTAS),
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                Set.of(
                        NetherExFeaturePlacements.BASALT_FUMAROLE,
                        NetherExFeaturePlacements.BLACKSTONE_FUMAROLE
                )
        );
        addSpawn(
                BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST),
                MobCategory.AMBIENT,
                NetherExEntityTypes.FLAEMOTH.get(),
                15,
                1,
                2
        );
        addSpawn(
                BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
                MobCategory.AMBIENT,
                NetherExEntityTypes.WISP.get(),
                15,
                1,
                2
        );
    }

    private static void addSpawn(Predicate<BiomeSelectionContext> biomeSelector, MobCategory spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize)
    {
        BiomeModifications.addSpawn(biomeSelector, spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
    }

    private static void addFeature(String name, Predicate<BiomeSelectionContext> biomeSelector, GenerationStep.Decoration step, Set<ResourceKey<PlacedFeature>> placedFeatures)
    {
        BiomeModifications.create(NetherExConstants.resource(name)).add(ModificationPhase.ADDITIONS, biomeSelector, context ->
        {
            placedFeatures.forEach(placedFeature -> context.getGenerationSettings().addFeature(step, placedFeature));
        });
    }

    private static void removeFeature(String name, Predicate<BiomeSelectionContext> biomeSelector, Set<ResourceKey<PlacedFeature>> placedFeatures)
    {
        BiomeModifications.create(NetherExConstants.resource(name)).add(ModificationPhase.REMOVALS, biomeSelector, context ->
        {
            placedFeatures.forEach(placedFeature -> context.getGenerationSettings().removeFeature(placedFeature));
        });
    }
}