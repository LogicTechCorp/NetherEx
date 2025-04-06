package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

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

    private static Biome ruthlessSands(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        BiomeSpecialEffects.Builder biomeSpecialEffectsBuilder = new BiomeSpecialEffects.Builder()
                .fogColor(1787717)
                .waterColor(4159204)
                .waterFogColor(329011)
                .skyColor(calculateSkyColor(2.0f))
                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.00625f))
                .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0d))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111d))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY));

        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.WISP.get(), 8, 1, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.SPINOUT.get(), 30, 2, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 20, 1, 3))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 1, 2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4))
                .addMobCharge(NetherExEntityTypes.WISP.get(), 0.7d, 0.15d)
                .addMobCharge(EntityType.STRIDER, 0.7d, 0.15d)
                .addMobCharge(NetherExEntityTypes.SPINOUT.get(), 0.7d, 0.15d)
                .addMobCharge(EntityType.WITHER_SKELETON, 0.7d, 0.15d)
                .addMobCharge(EntityType.GHAST, 0.7d, 0.15d)
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

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(biomeSpecialEffectsBuilder.build())
                .mobSpawnSettings(mobSpawnSettingsBuilder.build())
                .generationSettings(biomeGenerationSettingsBuilder.build())
                .build();
    }

    private static Biome torridWasteland(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        BiomeSpecialEffects.Builder biomeSpecialEffectsBuilder = new BiomeSpecialEffects.Builder()
                .fogColor(10505495)
                .waterColor(4159204)
                .waterFogColor(329011)
                .skyColor(calculateSkyColor(2.0f))
                .ambientLoopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0d))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111d))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_NETHER_WASTES));

        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.SALAMANDER.get(), 60, 2, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 10, 1, 2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 40, 2, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 30, 1, 2));

        BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, configuredWorldCarverHolderGetter)
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_NORMAL)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_NORMAL)
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
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_FIERY_QUARTZ);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(biomeSpecialEffectsBuilder.build())
                .mobSpawnSettings(mobSpawnSettingsBuilder.build())
                .generationSettings(biomeGenerationSettingsBuilder.build())
                .build();
    }

    private static Biome fungiForest(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarverHolderGetter)
    {
        BiomeSpecialEffects.Builder biomeSpecialEffectsBuilder = new BiomeSpecialEffects.Builder()
                .fogColor(1705242)
                .waterColor(4159204)
                .waterFogColor(329011)
                .skyColor(calculateSkyColor(2.0f))
                .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0d))
                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111d))
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_WARPED_FOREST));

        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(NetherExEntityTypes.MOGUS.get(), 120, 4, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 15, 1, 2));

        BiomeGenerationSettings.Builder biomeGenerationSettingsBuilder = new BiomeGenerationSettings.Builder(placedFeatureHolderGetter, configuredWorldCarverHolderGetter)
                .addCarver(Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, NetherExFeaturePlacements.HUGE_ELDER_MUSHROOMS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.GLOWSTONE);
        addOres(biomeGenerationSettingsBuilder, false);
        biomeGenerationSettingsBuilder
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_LIVELY_GOLD)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherExFeaturePlacements.ORE_LIVELY_QUARTZ);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(biomeSpecialEffectsBuilder.build())
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
        return ResourceKey.create(Registries.BIOME, NetherExConstants.resource(name));
    }
}