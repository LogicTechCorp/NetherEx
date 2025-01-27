package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class NetherExFeaturePlacements
{
    public static final ResourceKey<PlacedFeature> PATCH_FIRE = createKey("patch_fire");
    public static final ResourceKey<PlacedFeature> PATCH_FIRE_HEAVY = createKey("patch_fire_heavy");

    public static final ResourceKey<PlacedFeature> ORE_QUARTZ = createKey("ore_quartz");
    public static final ResourceKey<PlacedFeature> ORE_GOLD = createKey("ore_gold");
    public static final ResourceKey<PlacedFeature> ORE_ANCIENT_DEBRIS_SMALL = createKey("ore_ancient_debris_small");
    public static final ResourceKey<PlacedFeature> ORE_ANCIENT_DEBRIS_LARGE = createKey("ore_ancient_debris_large");
    public static final ResourceKey<PlacedFeature> ORE_MAGMA = createKey("ore_magma");
    public static final ResourceKey<PlacedFeature> ORE_MAGMA_HEAVY = createKey("ore_magma_heavy");
    public static final ResourceKey<PlacedFeature> ORE_GRAVEL = createKey("ore_gravel");
    public static final ResourceKey<PlacedFeature> ORE_SOUL_SAND = createKey("ore_soul_sand");
    public static final ResourceKey<PlacedFeature> ORE_BLACKSTONE = createKey("ore_blackstone");
    public static final ResourceKey<PlacedFeature> ORE_BOOMSTONE = createKey("ore_boomstone");
    public static final ResourceKey<PlacedFeature> ORE_BOOMSTONE_HEAVY = createKey("ore_boomstone_heavy");

    public static final ResourceKey<PlacedFeature> SPRING_OPEN = createKey("spring_open");
    public static final ResourceKey<PlacedFeature> SPRING_OPEN_HEAVY = createKey("spring_open_heavy");
    public static final ResourceKey<PlacedFeature> GLOWSTONE = createKey("glowstone");
    public static final ResourceKey<PlacedFeature> GLOWSTONE_EXTRA = createKey("glowstone_extra");
    public static final ResourceKey<PlacedFeature> TWISTED_WARPED_FUNGI = createKey("twisted_warped_fungi");

    public static final ResourceKey<PlacedFeature> THORNSTALK = createKey("thornstalk");

    public static final ResourceKey<PlacedFeature> HUGE_BROWN_ELDER_MUSHROOM = createKey("huge_brown_elder_mushroom");
    public static final ResourceKey<PlacedFeature> HUGE_RED_ELDER_MUSHROOM = createKey("huge_red_elder_mushroom");
    public static final ResourceKey<PlacedFeature> HUGE_ELDER_MUSHROOMS = createKey("huge_elder_mushrooms");

    public static final ResourceKey<PlacedFeature> BASALT_FUMAROLE = createKey("basalt_fumarole");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_FUMAROLE = createKey("blackstone_fumarole");

    public static void initialize()
    {

    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder.Reference<ConfiguredFeature<?, ?>> patchFireReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.PATCH_FIRE);
        register(context, PATCH_FIRE, patchFireReference, CountPlacement.of(UniformInt.of(0, 5)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());
        register(context, PATCH_FIRE_HEAVY, patchFireReference, CountPlacement.of(UniformInt.of(10, 20)), InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome());
        register(context, ORE_QUARTZ, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_QUARTZ), CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());
        register(context, ORE_GOLD, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_GOLD), CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());
        register(context, ORE_ANCIENT_DEBRIS_SMALL, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_ANCIENT_DEBRIS_SMALL), InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome());
        register(context, ORE_ANCIENT_DEBRIS_LARGE, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_ANCIENT_DEBRIS_LARGE), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(24)), BiomeFilter.biome());

        Holder.Reference<ConfiguredFeature<?, ?>> oreMagmaReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_MAGMA);
        register(context, ORE_MAGMA, oreMagmaReference, CountPlacement.of(4), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(27), VerticalAnchor.absolute(36)), BiomeFilter.biome());
        register(context, ORE_MAGMA_HEAVY, oreMagmaReference, CountPlacement.of(32), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome());
        register(context, ORE_GRAVEL, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_GRAVEL), CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(41)), BiomeFilter.biome());
        register(context, ORE_SOUL_SAND, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_SOUL_SAND), CountPlacement.of(12), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(31)), BiomeFilter.biome());
        register(context, ORE_BLACKSTONE, configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_BLACKSTONE), CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(31)), BiomeFilter.biome());

        Holder.Reference<ConfiguredFeature<?, ?>> oreBoomstoneReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.ORE_BOOMSTONE);
        register(context, ORE_BOOMSTONE, oreBoomstoneReference, CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());
        register(context, ORE_BOOMSTONE_HEAVY, oreBoomstoneReference, CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());

        Holder.Reference<ConfiguredFeature<?, ?>> springOpenReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.SPRING_OPEN);
        register(context, SPRING_OPEN, springOpenReference, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());
        register(context, SPRING_OPEN_HEAVY, springOpenReference, CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.RANGE_8_8, BiomeFilter.biome());

        Holder.Reference<ConfiguredFeature<?, ?>> glowstoneReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.GLOWSTONE_BLOB);
        register(context, GLOWSTONE, glowstoneReference, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
        register(context, GLOWSTONE_EXTRA, glowstoneReference, CountPlacement.of(BiasedToBottomInt.of(0, 9)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());

        Holder<ConfiguredFeature<?, ?>> twistedWarpedFungusReference = configuredFeatures.getOrThrow(NetherExFeatureConfigs.TWISTED_WARPED_FUNGUS);
        register(context, TWISTED_WARPED_FUNGI, twistedWarpedFungusReference, CountOnEveryLayerPlacement.of(8), BiomeFilter.biome());

        register(context, THORNSTALK, configuredFeatures.getOrThrow(NetherExFeatureConfigs.THORNSTALK), CountOnEveryLayerPlacement.of(8), BiomeFilter.biome());

        register(context, HUGE_BROWN_ELDER_MUSHROOM, configuredFeatures.getOrThrow(NetherExFeatureConfigs.HUGE_BROWN_ELDER_MUSHROOM), CountOnEveryLayerPlacement.of(5), BiomeFilter.biome());
        register(context, HUGE_RED_ELDER_MUSHROOM, configuredFeatures.getOrThrow(NetherExFeatureConfigs.HUGE_RED_ELDER_MUSHROOM), CountOnEveryLayerPlacement.of(5), BiomeFilter.biome());
        register(context, HUGE_ELDER_MUSHROOMS, configuredFeatures.getOrThrow(NetherExFeatureConfigs.HUGE_ELDER_MUSHROOMS), CountOnEveryLayerPlacement.of(10), BiomeFilter.biome());

        register(context, BASALT_FUMAROLE, configuredFeatures.getOrThrow(NetherExFeatureConfigs.BASALT_FUMAROLE), CountPlacement.of(UniformInt.of(4, 8)), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());
        register(context, BLACKSTONE_FUMAROLE, configuredFeatures.getOrThrow(NetherExFeatureConfigs.BLACKSTONE_FUMAROLE), CountPlacement.of(UniformInt.of(4, 8)), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome());
    }

    private static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, NetherExConstants.resource(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placements)
    {
        context.register(key, new PlacedFeature(configuredFeature, List.copyOf(placements)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placements)
    {
        register(context, key, configuredFeature, List.of(placements));
    }
}