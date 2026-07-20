package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class NetherExBiomes extends OverworldBiomes
{
    public static final ResourceKey<Biome> RUTHLESS_SANDS = createKey("ruthless_sands");
    public static final ResourceKey<Biome> TORRID_WASTELAND = createKey("torrid_wasteland");
    public static final ResourceKey<Biome> FUNGI_FOREST = createKey("fungi_forest");

    public static void bootstrap(BootstrapContext<Biome> context)
    {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(RUTHLESS_SANDS, ruthlessSands(placedFeatures, configuredWorldCarvers));
        context.register(TORRID_WASTELAND, torridWasteland(placedFeatures, configuredWorldCarvers));
        context.register(FUNGI_FOREST, fungiForest(placedFeatures, configuredWorldCarvers));
    }

    private static Biome.BiomeBuilder baseBiome()
    {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build());
    }

    private static Biome ruthlessSands(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.AMBIENT, 8, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.WISP.get(), 4, 4))
                .addSpawn(MobCategory.CREATURE, 60, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2))
                .addSpawn(MobCategory.MONSTER, 30, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.SPINOUT.get(), 4, 4))
                .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 3, 3))
                .addSpawn(MobCategory.MONSTER, 50, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 4, 4))
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
                .addMobCharge(NetherExEntityTypes.WISP.get(), 0.7d, 0.15d)
                .addMobCharge(EntityType.STRIDER, 0.7d, 0.15d)
                .addMobCharge(NetherExEntityTypes.SPINOUT.get(), 0.4d, 0.25d)
                .addMobCharge(EntityType.WITHER_SKELETON, 0.4d, 0.25d)
                .addMobCharge(EntityType.GHAST, 0.4d, 0.25d)
                .addMobCharge(EntityType.ENDERMAN, 0.7d, 0.15d);

        BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, configuredWorldCarverHolderGetter)
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, NetherExFeaturePlacements.THORNSTALK)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NetherPlacements.BASALT_PILLAR)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.SPRING_OPEN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_SOUL_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_CRIMSON_ROOTS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_SOUL_SAND);
        addOres(biomeGenerationSettingsBuilder, false);
        biomeGenerationSettingsBuilder
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_GLOOMY_GOLD)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_GLOOMY_QUARTZ);

        return baseBiome()
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 1787717)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY))
                .setAttribute(
                        EnvironmentAttributes.AMBIENT_SOUNDS,
                        new AmbientSounds(
                                Optional.of(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP),
                                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0d)),
                                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111d))
                        )
                )
                .mobSpawnSettings(mobSpawnSettingsBuilder.build())
                .generationSettings(biomeGenerationSettingsBuilder.build())
                .build();
    }

    private static Biome torridWasteland(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 60, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.SALAMANDER.get(), 4, 4))
                .addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 4, 4))
                .addSpawn(MobCategory.MONSTER, 40, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 4, 4))
                .addSpawn(MobCategory.CREATURE, 15, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2))
                .addMobCharge(NetherExEntityTypes.SALAMANDER.get(), 0.4d, 0.25d)
                .addMobCharge(EntityType.ZOMBIFIED_PIGLIN, 0.4d, 0.25d)
                .addMobCharge(EntityType.MAGMA_CUBE, 0.4d, 0.25d)
                .addMobCharge(EntityType.STRIDER, 0.7d, 0.15d);

        BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, configuredWorldCarverHolderGetter)
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_MAGMA_HEAVY)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.SPRING_OPEN_HEAVY)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.PATCH_FIRE_HEAVY)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NETHER)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);
        addOres(biomeGenerationSettingsBuilder, true);
        biomeGenerationSettingsBuilder
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_FIERY_GOLD)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_FIERY_QUARTZ)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NORMAL)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_NORMAL);

        return baseBiome()
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 10505495)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES))
                .setAttribute(
                        EnvironmentAttributes.AMBIENT_SOUNDS,
                        new AmbientSounds(
                                Optional.of(SoundEvents.AMBIENT_NETHER_WASTES_LOOP),
                                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0d)),
                                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111d))
                        )
                )
                .mobSpawnSettings(mobSpawnSettingsBuilder.build())
                .generationSettings(biomeGenerationSettingsBuilder.build())
                .build();
    }

    private static Biome fungiForest(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 60, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.MOGUS.get(), 1, 2))
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 4, 4))
                .addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 1, 2))
                .addMobCharge(NetherExEntityTypes.MOGUS.get(), 0.4d, 0.25d)
                .addMobCharge(EntityType.ENDERMAN, 0.7d, 0.15d)
                .addMobCharge(EntityType.STRIDER, 0.7d, 0.15d);

        BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, configuredWorldCarverHolderGetter)
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NetherExFeaturePlacements.HUGE_ELDER_MUSHROOMS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE);
        addOres(biomeGenerationSettingsBuilder, false);
        biomeGenerationSettingsBuilder
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_LIVELY_GOLD)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_LIVELY_QUARTZ);

        return baseBiome()
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 1705242)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_WARPED_FOREST))
                .setAttribute(
                        EnvironmentAttributes.AMBIENT_SOUNDS,
                        new AmbientSounds(
                                Optional.of(SoundEvents.AMBIENT_WARPED_FOREST_LOOP),
                                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0d)),
                                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111d))
                        )
                )
                .mobSpawnSettings(mobSpawnSettingsBuilder.build())
                .generationSettings(biomeGenerationSettingsBuilder.build())
                .build();
    }

    public static void addOres(BiomeGenerationSettings.Builder builder, boolean useHeavyPlacements)
    {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_GRAVEL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_BLACKSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_ANCIENT_DEBRIS_SMALL)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_ANCIENT_DEBRIS_LARGE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, useHeavyPlacements ? NetherExFeaturePlacements.ORE_BOOMSTONE_HEAVY : NetherExFeaturePlacements.ORE_BOOMSTONE);
    }

    private static ResourceKey<Biome> createKey(String name)
    {
        return ResourceKey.create(Registries.BIOME, NetherExConstants.identifier(name));
    }
}