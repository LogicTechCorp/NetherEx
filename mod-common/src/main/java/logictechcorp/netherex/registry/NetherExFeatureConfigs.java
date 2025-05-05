package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.world.level.levelgen.feature.config.NEBigMushroomFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

public class NetherExFeatureConfigs
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FIRE = createKey("patch_fire");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLOOMY_QUARTZ = createKey("ore_gloomy_quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GLOOMY_GOLD = createKey("ore_gloomy_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FIERY_QUARTZ = createKey("ore_fiery_quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FIERY_GOLD = createKey("ore_fiery_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIVELY_QUARTZ = createKey("ore_lively_quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIVELY_GOLD = createKey("ore_lively_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ANCIENT_DEBRIS_SMALL = createKey("ore_ancient_debris_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ANCIENT_DEBRIS_LARGE = createKey("ore_ancient_debris_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MAGMA = createKey("ore_magma");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GRAVEL = createKey("ore_gravel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SOUL_SAND = createKey("ore_soul_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BLACKSTONE = createKey("ore_blackstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BOOMSTONE = createKey("ore_boomstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_OPEN = createKey("spring_open");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWSTONE_BLOB = createKey("glowstone_blob");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TWISTED_WARPED_FUNGUS = createKey("twisted_warped_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TWISTED_WARPED_FUNGUS_PLANTED = createKey("twisted_warped_fungus_planted");

    public static final ResourceKey<ConfiguredFeature<?, ?>> THORNSTALK = createKey("thornstalk");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BROWN_ELDER_MUSHROOM = createKey("huge_brown_elder_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_RED_ELDER_MUSHROOM = createKey("huge_red_elder_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_ELDER_MUSHROOMS = createKey("huge_elder_mushrooms");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_FUMAROLE = createKey("basalt_fumarole");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_FUMAROLE = createKey("blackstone_fumarole");

    public static void initialize()
    {

    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        FeatureUtils.register(context, PATCH_FIRE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FIRE)), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesTag(Direction.DOWN.getNormal(), NetherExBlockTags.NETHERRACK)))));

        RuleTest gloomyNetherrackRuleTest = new BlockMatchTest(NetherExBlocks.GLOOMY_NETHERRACK.get());
        register(context, ORE_GLOOMY_QUARTZ, Feature.ORE, new OreConfiguration(gloomyNetherrackRuleTest, NetherExBlocks.GLOOMY_QUARTZ_ORE.get().defaultBlockState(), 14));
        register(context, ORE_GLOOMY_GOLD, Feature.ORE, new OreConfiguration(gloomyNetherrackRuleTest, NetherExBlocks.GLOOMY_GOLD_ORE.get().defaultBlockState(), 10));

        RuleTest fieryNetherrackRuleTest = new BlockMatchTest(NetherExBlocks.FIERY_NETHERRACK.get());
        register(context, ORE_FIERY_QUARTZ, Feature.ORE, new OreConfiguration(fieryNetherrackRuleTest, NetherExBlocks.FIERY_QUARTZ_ORE.get().defaultBlockState(), 14));
        register(context, ORE_FIERY_GOLD, Feature.ORE, new OreConfiguration(fieryNetherrackRuleTest, NetherExBlocks.FIERY_GOLD_ORE.get().defaultBlockState(), 10));

        RuleTest livelyNetherrackRuleTest = new BlockMatchTest(NetherExBlocks.LIVELY_NETHERRACK.get());
        register(context, ORE_LIVELY_QUARTZ, Feature.ORE, new OreConfiguration(livelyNetherrackRuleTest, NetherExBlocks.LIVELY_QUARTZ_ORE.get().defaultBlockState(), 14));
        register(context, ORE_LIVELY_GOLD, Feature.ORE, new OreConfiguration(livelyNetherrackRuleTest, NetherExBlocks.LIVELY_GOLD_ORE.get().defaultBlockState(), 10));

        RuleTest netherrackRuleTest = new TagMatchTest(NetherExBlockTags.NETHERRACK);
        RuleTest netherBaseStoneRuleTest = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        register(context, ORE_ANCIENT_DEBRIS_SMALL, Feature.SCATTERED_ORE, new OreConfiguration(netherBaseStoneRuleTest, Blocks.ANCIENT_DEBRIS.defaultBlockState(), 2, 1.0f));
        register(context, ORE_ANCIENT_DEBRIS_LARGE, Feature.SCATTERED_ORE, new OreConfiguration(netherrackRuleTest, Blocks.ANCIENT_DEBRIS.defaultBlockState(), 3, 1.0f));
        register(context, ORE_MAGMA, Feature.ORE, new OreConfiguration(netherrackRuleTest, Blocks.MAGMA_BLOCK.defaultBlockState(), 33));
        register(context, ORE_GRAVEL, Feature.ORE, new OreConfiguration(netherrackRuleTest, Blocks.GRAVEL.defaultBlockState(), 33));
        register(context, ORE_SOUL_SAND, Feature.ORE, new OreConfiguration(netherrackRuleTest, Blocks.SOUL_SAND.defaultBlockState(), 12));
        register(context, ORE_BLACKSTONE, Feature.ORE, new OreConfiguration(netherrackRuleTest, Blocks.BLACKSTONE.defaultBlockState(), 33));
        register(context, ORE_BOOMSTONE, Feature.ORE, new OreConfiguration(netherrackRuleTest, NetherExBlocks.BOOMSTONE.get().defaultBlockState(), 4, 1.0f));

        HolderSet<Block> netherrackHolder = context.lookup(BuiltInRegistries.BLOCK.key()).getOrThrow(NetherExBlockTags.NETHERRACK);
        register(context, SPRING_OPEN, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), false, 4, 1, netherrackHolder));
        register(context, GLOWSTONE_BLOB, NetherExFeatures.GLOWSTONE_BLOB.get());

        BlockPredicate replaceableBlocks = BlockPredicate.matchesBlocks(
                Blocks.OAK_SAPLING,
                Blocks.SPRUCE_SAPLING,
                Blocks.BIRCH_SAPLING,
                Blocks.JUNGLE_SAPLING,
                Blocks.ACACIA_SAPLING,
                Blocks.CHERRY_SAPLING,
                Blocks.DARK_OAK_SAPLING,
                Blocks.MANGROVE_PROPAGULE,
                Blocks.DANDELION,
                Blocks.TORCHFLOWER,
                Blocks.POPPY,
                Blocks.BLUE_ORCHID,
                Blocks.ALLIUM,
                Blocks.AZURE_BLUET,
                Blocks.RED_TULIP,
                Blocks.ORANGE_TULIP,
                Blocks.WHITE_TULIP,
                Blocks.PINK_TULIP,
                Blocks.OXEYE_DAISY,
                Blocks.CORNFLOWER,
                Blocks.WITHER_ROSE,
                Blocks.LILY_OF_THE_VALLEY,
                Blocks.BROWN_MUSHROOM,
                Blocks.RED_MUSHROOM,
                Blocks.WHEAT,
                Blocks.SUGAR_CANE,
                Blocks.ATTACHED_PUMPKIN_STEM,
                Blocks.ATTACHED_MELON_STEM,
                Blocks.PUMPKIN_STEM,
                Blocks.MELON_STEM,
                Blocks.LILY_PAD,
                Blocks.NETHER_WART,
                Blocks.COCOA,
                Blocks.CARROTS,
                Blocks.POTATOES,
                Blocks.CHORUS_PLANT,
                Blocks.CHORUS_FLOWER,
                Blocks.TORCHFLOWER_CROP,
                Blocks.PITCHER_CROP,
                Blocks.BEETROOTS,
                Blocks.SWEET_BERRY_BUSH,
                Blocks.WARPED_FUNGUS,
                Blocks.CRIMSON_FUNGUS,
                Blocks.WEEPING_VINES,
                Blocks.WEEPING_VINES_PLANT,
                Blocks.TWISTING_VINES,
                Blocks.TWISTING_VINES_PLANT,
                Blocks.CAVE_VINES,
                Blocks.CAVE_VINES_PLANT,
                Blocks.SPORE_BLOSSOM,
                Blocks.AZALEA,
                Blocks.FLOWERING_AZALEA,
                Blocks.MOSS_CARPET,
                Blocks.PINK_PETALS,
                Blocks.BIG_DRIPLEAF,
                Blocks.BIG_DRIPLEAF_STEM,
                Blocks.SMALL_DRIPLEAF
        );

        register(context, TWISTED_WARPED_FUNGUS, Feature.HUGE_FUNGUS, new HugeFungusConfiguration(Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_STEM.defaultBlockState(), Blocks.WARPED_WART_BLOCK.defaultBlockState(), NetherExBlocks.TWISTED_SHROOMLIGHT.get().defaultBlockState(), replaceableBlocks, false));
        register(context, TWISTED_WARPED_FUNGUS_PLANTED, Feature.HUGE_FUNGUS, new HugeFungusConfiguration(Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_STEM.defaultBlockState(), Blocks.WARPED_WART_BLOCK.defaultBlockState(), NetherExBlocks.TWISTED_SHROOMLIGHT.get().defaultBlockState(), replaceableBlocks, true));

        register(context, THORNSTALK, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(4, PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NetherExBlocks.THORNSTALK.get())), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(NetherExBlocks.THORNSTALK.get().defaultBlockState(), BlockPos.ZERO))))));

        NEBigMushroomFeatureConfiguration bigBrownElderMushroomConfig = new NEBigMushroomFeatureConfiguration(BlockStateProvider.simple(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(NetherExBlocks.ELDER_MUSHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)));
        NEBigMushroomFeatureConfiguration bigRedElderMushroomConfig = new NEBigMushroomFeatureConfiguration(BlockStateProvider.simple(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.UP, true).setValue(HugeMushroomBlock.DOWN, false)), BlockStateProvider.simple(NetherExBlocks.ELDER_MUSHROOM_STEM.get().defaultBlockState().setValue(HugeMushroomBlock.UP, false).setValue(HugeMushroomBlock.DOWN, false)));
        register(context, HUGE_BROWN_ELDER_MUSHROOM, NetherExFeatures.HUGE_BROWN_ELDER_MUSHROOM.get(), bigBrownElderMushroomConfig);
        register(context, HUGE_RED_ELDER_MUSHROOM, NetherExFeatures.HUGE_RED_ELDER_MUSHROOM.get(), bigRedElderMushroomConfig);
        register(context, HUGE_ELDER_MUSHROOMS, Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfiguration(PlacementUtils.inlinePlaced(NetherExFeatures.HUGE_BROWN_ELDER_MUSHROOM.get(), bigBrownElderMushroomConfig, BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(NetherExBlocks.BROWN_ELDER_MUSHROOM.get().defaultBlockState(), BlockPos.ZERO)))), PlacementUtils.inlinePlaced(NetherExFeatures.HUGE_RED_ELDER_MUSHROOM.get(), bigRedElderMushroomConfig, BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(NetherExBlocks.RED_ELDER_MUSHROOM.get().defaultBlockState(), BlockPos.ZERO))))));
        register(context, BASALT_FUMAROLE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 7, 7, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NetherExBlocks.BASALT_FUMAROLE.get().defaultBlockState())), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.AIR), BlockPredicate.noFluid(Direction.DOWN.getNormal().below()), BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.BASALT), BlockPredicate.matchesTag(Direction.DOWN.getNormal(), NetherExBlockTags.LOW_FUMAROLE_HEATER), BlockPredicate.matchesTag(Direction.DOWN.getNormal(), NetherExBlockTags.HIGH_FUMAROLE_HEATER))))));
        register(context, BLACKSTONE_FUMAROLE, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 7, 7, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(NetherExBlocks.BLACKSTONE_FUMAROLE.get().defaultBlockState())), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(Direction.UP.getNormal(), Blocks.AIR), BlockPredicate.noFluid(Direction.DOWN.getNormal().below()), BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.BLACKSTONE), BlockPredicate.matchesTag(Direction.DOWN.getNormal(), NetherExBlockTags.LOW_FUMAROLE_HEATER), BlockPredicate.matchesTag(Direction.DOWN.getNormal(), NetherExBlockTags.HIGH_FUMAROLE_HEATER))))));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, NetherExConstants.resource(name));
    }

    private static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature)
    {
        register(context, key, feature, FeatureConfiguration.NONE);
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature(feature, config));
    }
}
