package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.*;
import logictechcorp.netherex.platform.NEBlockHelper;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

public class NetherExBlocks
{
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, NetherExConstants.MOD_ID);
    public static final RegistrationProvider<Item> ITEM_BLOCKS = RegistrationProvider.get(BuiltInRegistries.ITEM, NetherExConstants.MOD_ID);

    public static final RegistryObject<Block, Block> GLOOMY_NETHERRACK = registerBaseBlockWithBaseItem("gloomy_netherrack", Properties.GLOOMY_NETHERRACK);
    public static final RegistryObject<Block, Block> GLOOMY_QUARTZ_ORE = registerBlockWithBaseItem("gloomy_quartz_ore", (properties) -> new DropExperienceBlock(UniformInt.of(2, 5), properties), Properties.GLOOMY_QUARTZ_ORE);
    public static final RegistryObject<Block, Block> GLOOMY_GOLD_ORE = registerBlockWithBaseItem("gloomy_gold_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 1), properties), Properties.GLOOMY_GOLD_ORE);
    public static final RegistryObject<Block, Block> GLOOMY_NETHERRACK_PATH = registerBlockWithBaseItem("gloomy_netherrack_path", (properties) -> new NEPathBlock(GLOOMY_NETHERRACK, properties), Properties.GLOOMY_NETHERRACK_PATH);
    public static final RegistryObject<Block, Block> GLOOMY_NETHER_BRICKS = registerBaseBlockWithBaseItem("gloomy_nether_bricks", Properties.GLOOMY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CRACKED_GLOOMY_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_gloomy_nether_bricks", Properties.CRACKED_GLOOMY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_GLOOMY_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_gloomy_nether_bricks", Properties.CHISELED_GLOOMY_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> GLOOMY_NETHER_BRICK_FENCE = registerFenceBlock("gloomy_nether_brick_fence", Properties.GLOOMY_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> GLOOMY_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("gloomy_nether_brick_fence_gate", Properties.GLOOMY_NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> GLOOMY_NETHER_BRICK_SLAB = registerSlabBlock("gloomy_nether_brick_slab", Properties.GLOOMY_NETHER_BRICK_SLAB);
    public static final RegistryObject<Block, StairBlock> GLOOMY_NETHER_BRICK_STAIRS = registerStairBlock("gloomy_nether_brick_stairs", GLOOMY_NETHER_BRICKS, Properties.GLOOMY_NETHER_BRICK_STAIRS);
    public static final RegistryObject<Block, WallBlock> GLOOMY_NETHER_BRICK_WALL = registerWallBlock("gloomy_nether_brick_wall", Properties.GLOOMY_NETHER_BRICK_WALL);
    public static final RegistryObject<Block, Block> POLISHED_GLOOMY_NETHERRACK = registerBaseBlockWithBaseItem("polished_gloomy_netherrack", Properties.POLISHED_GLOOMY_NETHERRACK);
    public static final RegistryObject<Block, FenceBlock> POLISHED_GLOOMY_NETHERRACK_FENCE = registerFenceBlock("polished_gloomy_netherrack_fence", Properties.POLISHED_GLOOMY_NETHERRACK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> POLISHED_GLOOMY_NETHERRACK_FENCE_GATE = registerFenceGateBlock("polished_gloomy_netherrack_fence_gate", Properties.POLISHED_GLOOMY_NETHERRACK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> POLISHED_GLOOMY_NETHERRACK_SLAB = registerSlabBlock("polished_gloomy_netherrack_slab", Properties.POLISHED_GLOOMY_NETHERRACK_SLAB);
    public static final RegistryObject<Block, StairBlock> POLISHED_GLOOMY_NETHERRACK_STAIRS = registerStairBlock("polished_gloomy_netherrack_stairs", () -> Blocks.NETHERRACK, Properties.POLISHED_GLOOMY_NETHERRACK_STAIRS);
    public static final RegistryObject<Block, WallBlock> POLISHED_GLOOMY_NETHERRACK_WALL = registerWallBlock("polished_gloomy_netherrack_wall", Properties.POLISHED_GLOOMY_NETHERRACK_WALL);
    public static final RegistryObject<Block, Block> CHISELED_POLISHED_GLOOMY_NETHERRACK = registerBaseBlockWithBaseItem("chiseled_polished_gloomy_netherrack", Properties.CHISELED_POLISHED_GLOOMY_NETHERRACK);

    public static final RegistryObject<Block, NEThornstalkBlock> THORNSTALK = registerBlockWithBaseItem("thornstalk", NEThornstalkBlock::new, Properties.THORNSTALK);

    public static final RegistryObject<Block, Block> FIERY_NETHERRACK = registerBaseBlockWithBaseItem("fiery_netherrack", Properties.FIERY_NETHERRACK);
    public static final RegistryObject<Block, Block> FIERY_QUARTZ_ORE = registerBlockWithBaseItem("fiery_quartz_ore", (properties) -> new DropExperienceBlock(UniformInt.of(2, 5), properties), Properties.FIERY_QUARTZ_ORE);
    public static final RegistryObject<Block, Block> FIERY_GOLD_ORE = registerBlockWithBaseItem("fiery_gold_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 1), properties), Properties.FIERY_GOLD_ORE);
    public static final RegistryObject<Block, Block> FIERY_NETHERRACK_PATH = registerBlockWithBaseItem("fiery_netherrack_path", (properties) -> new NEPathBlock(FIERY_NETHERRACK, properties), Properties.FIERY_NETHERRACK_PATH);
    public static final RegistryObject<Block, Block> FIERY_NETHER_BRICKS = registerBaseBlockWithBaseItem("fiery_nether_bricks", Properties.FIERY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CRACKED_FIERY_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_fiery_nether_bricks", Properties.CRACKED_FIERY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_FIERY_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_fiery_nether_bricks", Properties.CHISELED_FIERY_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> FIERY_NETHER_BRICK_FENCE = registerFenceBlock("fiery_nether_brick_fence", Properties.FIERY_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> FIERY_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("fiery_nether_brick_fence_gate", Properties.FIERY_NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> FIERY_NETHER_BRICK_SLAB = registerSlabBlock("fiery_nether_brick_slab", Properties.FIERY_NETHER_BRICK_SLAB);
    public static final RegistryObject<Block, StairBlock> FIERY_NETHER_BRICK_STAIRS = registerStairBlock("fiery_nether_brick_stairs", FIERY_NETHER_BRICKS, Properties.FIERY_NETHER_BRICK_STAIRS);
    public static final RegistryObject<Block, WallBlock> FIERY_NETHER_BRICK_WALL = registerWallBlock("fiery_nether_brick_wall", Properties.FIERY_NETHER_BRICK_WALL);
    public static final RegistryObject<Block, Block> POLISHED_FIERY_NETHERRACK = registerBaseBlockWithBaseItem("polished_fiery_netherrack", Properties.POLISHED_FIERY_NETHERRACK);
    public static final RegistryObject<Block, FenceBlock> POLISHED_FIERY_NETHERRACK_FENCE = registerFenceBlock("polished_fiery_netherrack_fence", Properties.POLISHED_FIERY_NETHERRACK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> POLISHED_FIERY_NETHERRACK_FENCE_GATE = registerFenceGateBlock("polished_fiery_netherrack_fence_gate", Properties.POLISHED_FIERY_NETHERRACK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> POLISHED_FIERY_NETHERRACK_SLAB = registerSlabBlock("polished_fiery_netherrack_slab", Properties.POLISHED_FIERY_NETHERRACK_SLAB);
    public static final RegistryObject<Block, StairBlock> POLISHED_FIERY_NETHERRACK_STAIRS = registerStairBlock("polished_fiery_netherrack_stairs", () -> Blocks.NETHERRACK, Properties.POLISHED_FIERY_NETHERRACK_STAIRS);
    public static final RegistryObject<Block, WallBlock> POLISHED_FIERY_NETHERRACK_WALL = registerWallBlock("polished_fiery_netherrack_wall", Properties.POLISHED_FIERY_NETHERRACK_WALL);
    public static final RegistryObject<Block, Block> CHISELED_POLISHED_FIERY_NETHERRACK = registerBaseBlockWithBaseItem("chiseled_polished_fiery_netherrack", Properties.CHISELED_POLISHED_FIERY_NETHERRACK);

    public static final RegistryObject<Block, Block> LIVELY_NETHERRACK = registerBaseBlockWithBaseItem("lively_netherrack", Properties.LIVELY_NETHERRACK);
    public static final RegistryObject<Block, Block> LIVELY_QUARTZ_ORE = registerBlockWithBaseItem("lively_quartz_ore", (properties) -> new DropExperienceBlock(UniformInt.of(2, 5), properties), Properties.LIVELY_QUARTZ_ORE);
    public static final RegistryObject<Block, Block> LIVELY_GOLD_ORE = registerBlockWithBaseItem("lively_gold_ore", (properties) -> new DropExperienceBlock(UniformInt.of(0, 1), properties), Properties.LIVELY_GOLD_ORE);
    public static final RegistryObject<Block, Block> LIVELY_NETHERRACK_PATH = registerBlockWithBaseItem("lively_netherrack_path", (properties) -> new NEPathBlock(LIVELY_NETHERRACK, properties), Properties.LIVELY_NETHERRACK_PATH);
    public static final RegistryObject<Block, Block> LIVELY_NETHER_BRICKS = registerBaseBlockWithBaseItem("lively_nether_bricks", Properties.LIVELY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CRACKED_LIVELY_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_lively_nether_bricks", Properties.CRACKED_LIVELY_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_LIVELY_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_lively_nether_bricks", Properties.CHISELED_LIVELY_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> LIVELY_NETHER_BRICK_FENCE = registerFenceBlock("lively_nether_brick_fence", Properties.LIVELY_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> LIVELY_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("lively_nether_brick_fence_gate", Properties.LIVELY_NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> LIVELY_NETHER_BRICK_SLAB = registerSlabBlock("lively_nether_brick_slab", Properties.LIVELY_NETHER_BRICK_SLAB);
    public static final RegistryObject<Block, StairBlock> LIVELY_NETHER_BRICK_STAIRS = registerStairBlock("lively_nether_brick_stairs", LIVELY_NETHER_BRICKS, Properties.LIVELY_NETHER_BRICK_STAIRS);
    public static final RegistryObject<Block, WallBlock> LIVELY_NETHER_BRICK_WALL = registerWallBlock("lively_nether_brick_wall", Properties.LIVELY_NETHER_BRICK_WALL);
    public static final RegistryObject<Block, Block> POLISHED_LIVELY_NETHERRACK = registerBaseBlockWithBaseItem("polished_lively_netherrack", Properties.POLISHED_LIVELY_NETHERRACK);
    public static final RegistryObject<Block, FenceBlock> POLISHED_LIVELY_NETHERRACK_FENCE = registerFenceBlock("polished_lively_netherrack_fence", Properties.POLISHED_LIVELY_NETHERRACK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> POLISHED_LIVELY_NETHERRACK_FENCE_GATE = registerFenceGateBlock("polished_lively_netherrack_fence_gate", Properties.POLISHED_LIVELY_NETHERRACK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> POLISHED_LIVELY_NETHERRACK_SLAB = registerSlabBlock("polished_lively_netherrack_slab", Properties.POLISHED_LIVELY_NETHERRACK_SLAB);
    public static final RegistryObject<Block, StairBlock> POLISHED_LIVELY_NETHERRACK_STAIRS = registerStairBlock("polished_lively_netherrack_stairs", () -> Blocks.NETHERRACK, Properties.POLISHED_LIVELY_NETHERRACK_STAIRS);
    public static final RegistryObject<Block, WallBlock> POLISHED_LIVELY_NETHERRACK_WALL = registerWallBlock("polished_lively_netherrack_wall", Properties.POLISHED_LIVELY_NETHERRACK_WALL);
    public static final RegistryObject<Block, Block> CHISELED_POLISHED_LIVELY_NETHERRACK = registerBaseBlockWithBaseItem("chiseled_polished_lively_netherrack", Properties.CHISELED_POLISHED_LIVELY_NETHERRACK);
    public static final RegistryObject<Block, NEElderNyliumBlock> ELDER_NYLIUM = registerBlockWithBaseItem("elder_nylium", NEElderNyliumBlock::new, Properties.HYPHAE);
    public static final RegistryObject<Block, MushroomBlock> BROWN_ELDER_MUSHROOM = registerBlockWithBaseItem("brown_elder_mushroom", (properties) -> new MushroomBlock(NetherExFeatureConfigs.HUGE_BROWN_ELDER_MUSHROOM, properties), Properties.BROWN_ELDER_MUSHROOM);
    public static final RegistryObject<Block, HugeMushroomBlock> BROWN_ELDER_MUSHROOM_BLOCK = registerBlockWithBaseItem("brown_elder_mushroom_block", HugeMushroomBlock::new, Properties.BROWN_ELDER_MUSHROOM_BLOCK);
    public static final RegistryObject<Block, MushroomBlock> RED_ELDER_MUSHROOM = registerBlockWithBaseItem("red_elder_mushroom", (properties) -> new MushroomBlock(NetherExFeatureConfigs.HUGE_RED_ELDER_MUSHROOM, properties), Properties.RED_ELDER_MUSHROOM);
    public static final RegistryObject<Block, HugeMushroomBlock> RED_ELDER_MUSHROOM_BLOCK = registerBlockWithBaseItem("red_elder_mushroom_block", HugeMushroomBlock::new, Properties.RED_ELDER_MUSHROOM_BLOCK);
    public static final RegistryObject<Block, HugeMushroomBlock> ELDER_MUSHROOM_STEM = registerBlockWithBaseItem("elder_mushroom_stem", HugeMushroomBlock::new, Properties.ELDER_MUSHROOM_STEM);

    public static final RegistryObject<Block, NEGlowingObsidianBlock> GLOWING_OBSIDIAN = registerBlockWithBaseItem("glowing_obsidian", NEGlowingObsidianBlock::new, Properties.GLOWING_OBSIDIAN);
    public static final RegistryObject<Block, Block> POLISHED_NETHERRACK = registerBaseBlockWithBaseItem("polished_netherrack", Properties.POLISHED_NETHERRACK);
    public static final RegistryObject<Block, Block> CHISELED_POLISHED_NETHERRACK = registerBaseBlockWithBaseItem("chiseled_polished_netherrack", Properties.CHISELED_POLISHED_NETHERRACK);
    public static final RegistryObject<Block, FenceBlock> POLISHED_NETHERRACK_FENCE = registerFenceBlock("polished_netherrack_fence", Properties.POLISHED_NETHERRACK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> POLISHED_NETHERRACK_FENCE_GATE = registerFenceGateBlock("polished_netherrack_fence_gate", Properties.POLISHED_NETHERRACK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> POLISHED_NETHERRACK_SLAB = registerSlabBlock("polished_netherrack_slab", Properties.POLISHED_NETHERRACK_SLAB);
    public static final RegistryObject<Block, StairBlock> POLISHED_NETHERRACK_STAIRS = registerStairBlock("polished_netherrack_stairs", () -> Blocks.NETHERRACK, Properties.POLISHED_NETHERRACK_STAIRS);
    public static final RegistryObject<Block, WallBlock> POLISHED_NETHERRACK_WALL = registerWallBlock("polished_netherrack_wall", Properties.POLISHED_NETHERRACK_WALL);

    public static final RegistryObject<Block, Block> POLISHED_GLOWSTONE = registerBaseBlockWithBaseItem("polished_glowstone", Properties.POLISHED_GLOWSTONE);
    public static final RegistryObject<Block, Block> CHISELED_POLISHED_GLOWSTONE = registerBaseBlockWithBaseItem("chiseled_polished_glowstone", Properties.CHISELED_POLISHED_GLOWSTONE);
    public static final RegistryObject<Block, FenceBlock> POLISHED_GLOWSTONE_FENCE = registerFenceBlock("polished_glowstone_fence", Properties.POLISHED_GLOWSTONE_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> POLISHED_GLOWSTONE_FENCE_GATE = registerFenceGateBlock("polished_glowstone_fence_gate", Properties.POLISHED_GLOWSTONE_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> POLISHED_GLOWSTONE_SLAB = registerSlabBlock("polished_glowstone_slab", Properties.POLISHED_GLOWSTONE_SLAB);
    public static final RegistryObject<Block, StairBlock> POLISHED_GLOWSTONE_STAIRS = registerStairBlock("polished_glowstone_stairs", () -> Blocks.NETHERRACK, Properties.POLISHED_GLOWSTONE_STAIRS);
    public static final RegistryObject<Block, WallBlock> POLISHED_GLOWSTONE_WALL = registerWallBlock("polished_glowstone_wall", Properties.POLISHED_GLOWSTONE_WALL);

    public static final RegistryObject<Block, FenceGateBlock> NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("nether_brick_fence_gate", Properties.NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, Block> CRACKED_CRIMSON_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_crimson_nether_bricks", Properties.CRACKED_CRIMSON_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_CRIMSON_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_crimson_nether_bricks", Properties.CHISELED_CRIMSON_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> CRIMSON_NETHER_BRICK_FENCE = registerFenceBlock("crimson_nether_brick_fence", Properties.CRIMSON_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> CRIMSON_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("crimson_nether_brick_fence_gate", Properties.CRIMSON_NETHER_BRICK_FENCE_GATE);

    public static final RegistryObject<Block, Block> WARPED_WART = registerBlockWithBaseItem("warped_wart", NEWarpedWartBlock::new, Properties.WARPED_WART);
    public static final RegistryObject<Block, NEPathBlock> CRIMSON_NYLIUM_PATH = registerBlockWithBaseItem("crimson_nylium_path", (properties) -> new NEPathBlock(() -> Blocks.CRIMSON_NYLIUM, properties), Properties.CRIMSON_NYLIUM_PATH);
    public static final RegistryObject<Block, NEPathBlock> WARPED_NYLIUM_PATH = registerBlockWithBaseItem("warped_nylium_path", (properties) -> new NEPathBlock(() -> Blocks.WARPED_NYLIUM, properties), Properties.WARPED_NYLIUM_PATH);
    public static final RegistryObject<Block, Block> CRIMSON_ROOT_THATCH = registerBaseBlockWithBaseItem("crimson_root_thatch", Properties.CRIMSON_ROOT_THATCH);
    public static final RegistryObject<Block, Block> WARPED_ROOT_THATCH = registerBaseBlockWithBaseItem("warped_root_thatch", Properties.WARPED_ROOT_THATCH);
    public static final RegistryObject<Block, Block> TWISTED_SHROOMLIGHT = registerBaseBlockWithBaseItem("twisted_shroomlight", Properties.TWISTED_SHROOMLIGHT);
    public static final RegistryObject<Block, NEHollowShroomlightBlock> HOLLOW_SHROOMLIGHT = registerBlockWithBaseItem("hollow_shroomlight", (properties) -> new NEHollowShroomlightBlock(() -> Blocks.SHROOMLIGHT, properties), Properties.HOLLOW_SHROOMLIGHT);
    public static final RegistryObject<Block, NEHollowShroomlightBlock> HOLLOW_TWISTED_SHROOMLIGHT = registerBlockWithBaseItem("hollow_twisted_shroomlight", (properties) -> new NEHollowShroomlightBlock(TWISTED_SHROOMLIGHT, properties), Properties.HOLLOW_TWISTED_SHROOMLIGHT);
    public static final RegistryObject<Block, NEShroomstemBlock> SHROOMSTEM = registerBlock("shroomstem", NEShroomstemBlock::new, Properties.SHROOMSTEM);
    public static final RegistryObject<Block, NETwistedShroomstemBlock> TWISTED_SHROOMSTEM = registerBlock("twisted_shroomstem", NETwistedShroomstemBlock::new, Properties.TWISTED_SHROOMSTEM);

    public static final RegistryObject<Block, Block> ASHEN_NETHER_BRICKS = registerBaseBlockWithBaseItem("ashen_nether_bricks", Properties.ASHEN_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CRACKED_ASHEN_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_ashen_nether_bricks", Properties.CRACKED_ASHEN_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_ASHEN_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_ashen_nether_bricks", Properties.CHISELED_ASHEN_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> ASHEN_NETHER_BRICK_FENCE = registerFenceBlock("ashen_nether_brick_fence", Properties.ASHEN_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> ASHEN_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("ashen_nether_brick_fence_gate", Properties.ASHEN_NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> ASHEN_NETHER_BRICK_SLAB = registerSlabBlock("ashen_nether_brick_slab", Properties.ASHEN_NETHER_BRICK_SLAB);
    public static final RegistryObject<Block, StairBlock> ASHEN_NETHER_BRICK_STAIRS = registerStairBlock("ashen_nether_brick_stairs", ASHEN_NETHER_BRICKS, Properties.ASHEN_NETHER_BRICK_STAIRS);
    public static final RegistryObject<Block, WallBlock> ASHEN_NETHER_BRICK_WALL = registerWallBlock("ashen_nether_brick_wall", Properties.ASHEN_NETHER_BRICK_WALL);

    public static final RegistryObject<Block, Block> WARPED_NETHER_BRICKS = registerBaseBlockWithBaseItem("warped_nether_bricks", Properties.WARPED_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CRACKED_WARPED_NETHER_BRICKS = registerBaseBlockWithBaseItem("cracked_warped_nether_bricks", Properties.CRACKED_WARPED_NETHER_BRICKS);
    public static final RegistryObject<Block, Block> CHISELED_WARPED_NETHER_BRICKS = registerBaseBlockWithBaseItem("chiseled_warped_nether_bricks", Properties.CHISELED_WARPED_NETHER_BRICKS);
    public static final RegistryObject<Block, FenceBlock> WARPED_NETHER_BRICK_FENCE = registerFenceBlock("warped_nether_brick_fence", Properties.WARPED_NETHER_BRICK_FENCE);
    public static final RegistryObject<Block, FenceGateBlock> WARPED_NETHER_BRICK_FENCE_GATE = registerFenceGateBlock("warped_nether_brick_fence_gate", Properties.WARPED_NETHER_BRICK_FENCE_GATE);
    public static final RegistryObject<Block, SlabBlock> WARPED_NETHER_BRICK_SLAB = registerSlabBlock("warped_nether_brick_slab", Properties.WARPED_NETHER_BRICK_SLAB);
    public static final RegistryObject<Block, StairBlock> WARPED_NETHER_BRICK_STAIRS = registerStairBlock("warped_nether_brick_stairs", WARPED_NETHER_BRICKS, Properties.WARPED_NETHER_BRICK_STAIRS);
    public static final RegistryObject<Block, WallBlock> WARPED_NETHER_BRICK_WALL = registerWallBlock("warped_nether_brick_wall", Properties.WARPED_NETHER_BRICK_WALL);

    public static final RegistryObject<Block, NEBoomstoneBlock> BOOMSTONE = registerBlockWithBaseItem("boomstone", NEBoomstoneBlock::new, Properties.BOOMSTONE);
    public static final RegistryObject<Block, Block> ASH_BLOCK = registerBaseBlockWithBaseItem("ash_block", Properties.ASH_BLOCK);
    public static final RegistryObject<Block, RotatedPillarBlock> WITHER_BONE_BLOCK = registerBlockWithBaseItem("wither_bone_block", RotatedPillarBlock::new, Properties.WITHER_BONE_BLOCK);
    public static final RegistryObject<Block, NEFumaroleBlock> BASALT_FUMAROLE = registerBlockWithBaseItem("basalt_fumarole", NEFumaroleBlock::new, Properties.BASALT_FUMAROLE);
    public static final RegistryObject<Block, NEFumaroleBlock> BLACKSTONE_FUMAROLE = registerBlockWithBaseItem("blackstone_fumarole", NEFumaroleBlock::new, Properties.BLACKSTONE_FUMAROLE);
    public static final RegistryObject<Block, NEKilnBlock> KILN = registerBlockWithBaseItem("kiln", NEKilnBlock::new, Properties.KILN);

    public static void initialize()
    {

    }

    private static <B extends Block> RegistryObject<Block, B> registerBlock(String blockName, Function<Block.Properties, B> newBlockFunc, Block.Properties blockProperties)
    {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(NetherExConstants.MOD_ID, blockName);
        ResourceKey<Block> resourceKey = ResourceKey.create(Registries.BLOCK, resourceLocation);
        blockProperties.setId(resourceKey);

        Supplier<B> blockSupplier = () -> newBlockFunc.apply(blockProperties);
        return BLOCKS.register(blockName, blockSupplier);
    }

    private static RegistryObject<Block, Block> registerBaseBlockWithBaseItem(String blockName, Block.Properties blockProperties)
    {
        RegistryObject<Block, Block> RegistryObject = registerBlock(blockName, Block::new, blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static <B extends Block> RegistryObject<Block, B> registerBlockWithBaseItem(String blockName, Function<Block.Properties, B> newBlockFunc, Block.Properties blockProperties)
    {
        RegistryObject<Block, B> RegistryObject = registerBlock(blockName, newBlockFunc, blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static RegistryObject<Block, FenceBlock> registerFenceBlock(String blockName, Block.Properties blockProperties)
    {
        RegistryObject<Block, FenceBlock> RegistryObject = registerBlock(blockName, FenceBlock::new, blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static RegistryObject<Block, FenceGateBlock> registerFenceGateBlock(String blockName, Block.Properties blockProperties)
    {
        RegistryObject<Block, FenceGateBlock> RegistryObject = registerBlock(blockName, (properties) -> new FenceGateBlock(WoodType.OAK, properties), blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static RegistryObject<Block, SlabBlock> registerSlabBlock(String blockName, Block.Properties blockProperties)
    {
        RegistryObject<Block, SlabBlock> RegistryObject = registerBlock(blockName, SlabBlock::new, blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static RegistryObject<Block, StairBlock> registerStairBlock(String blockName, Supplier<Block> blockSupplier, Block.Properties blockProperties)
    {
        RegistryObject<Block, StairBlock> RegistryObject = registerBlock(blockName, (properties) -> NEBlockHelper.INSTANCE.newStairBlock(blockSupplier.get().defaultBlockState(), properties), blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    private static RegistryObject<Block, WallBlock> registerWallBlock(String blockName, Block.Properties blockProperties)
    {
        RegistryObject<Block, WallBlock> RegistryObject = registerBlock(blockName, WallBlock::new, blockProperties);
        registerItemBlock(blockName, (properties) -> new BlockItem(RegistryObject.get(), properties));
        return RegistryObject;
    }

    public static <I extends Item> RegistryObject<Item, I> registerItemBlock(String itemName, Function<Item.Properties, I> newItemFunc)
    {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(NetherExConstants.MOD_ID, itemName);
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, resourceLocation);
        Item.Properties itemProperties = new Item.Properties();
        itemProperties.setId(resourceKey);

        Supplier<I> itemSupplier = () -> newItemFunc.apply(itemProperties);
        return ITEM_BLOCKS.register(itemName, itemSupplier);
    }

    static class Properties
    {
        static final BlockBehaviour.Properties GLOOMY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties GLOOMY_QUARTZ_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE);
        static final BlockBehaviour.Properties GLOOMY_GOLD_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_GOLD_ORE);
        static final BlockBehaviour.Properties GLOOMY_NETHERRACK_PATH = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK).isViewBlocking(Properties::always).isSuffocating(Properties::always);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_GLOOMY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_GLOOMY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties GLOOMY_NETHER_BRICK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOOMY_NETHERRACK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties CHISELED_POLISHED_GLOOMY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties THORNSTALK = BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ);

        static final BlockBehaviour.Properties FIERY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties FIERY_QUARTZ_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE);
        static final BlockBehaviour.Properties FIERY_GOLD_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_GOLD_ORE);
        static final BlockBehaviour.Properties FIERY_NETHERRACK_PATH = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK).isViewBlocking(Properties::always).isSuffocating(Properties::always);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_FIERY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_FIERY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties FIERY_NETHER_BRICK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_FIERY_NETHERRACK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties CHISELED_POLISHED_FIERY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);

        static final BlockBehaviour.Properties LIVELY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties LIVELY_QUARTZ_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE);
        static final BlockBehaviour.Properties LIVELY_GOLD_ORE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_GOLD_ORE);
        static final BlockBehaviour.Properties LIVELY_NETHERRACK_PATH = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK).isViewBlocking(Properties::always).isSuffocating(Properties::always);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_LIVELY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_LIVELY_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties LIVELY_NETHER_BRICK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_LIVELY_NETHERRACK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties CHISELED_POLISHED_LIVELY_NETHERRACK = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties HYPHAE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(0.4f).sound(SoundType.NETHERRACK);
        static final BlockBehaviour.Properties BROWN_ELDER_MUSHROOM = BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM);
        static final BlockBehaviour.Properties BROWN_ELDER_MUSHROOM_BLOCK = BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM_BLOCK);
        static final BlockBehaviour.Properties RED_ELDER_MUSHROOM = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM);
        static final BlockBehaviour.Properties RED_ELDER_MUSHROOM_BLOCK = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK);
        static final BlockBehaviour.Properties ELDER_MUSHROOM_STEM = BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM);

        static final BlockBehaviour.Properties GLOWING_OBSIDIAN = BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(DyeColor.RED);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties CHISELED_POLISHED_NETHERRACK = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK_FENCE = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK_FENCE_GATE = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK_SLAB = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK_STAIRS = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_NETHERRACK_WALL = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties CHISELED_POLISHED_GLOWSTONE = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE_FENCE = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE_FENCE_GATE = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE_SLAB = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE_STAIRS = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties POLISHED_GLOWSTONE_WALL = BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE);
        static final BlockBehaviour.Properties NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_CRIMSON_NETHER_BRICKS = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_CRIMSON_NETHER_BRICKS = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS);
        static final BlockBehaviour.Properties CRIMSON_NETHER_BRICK_FENCE = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS);
        static final BlockBehaviour.Properties CRIMSON_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS);

        static final BlockBehaviour.Properties WARPED_WART = BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.COLOR_CYAN);
        static final BlockBehaviour.Properties CRIMSON_NYLIUM_PATH = BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_NYLIUM).isViewBlocking(Properties::always).isSuffocating(Properties::always);
        static final BlockBehaviour.Properties WARPED_NYLIUM_PATH = BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_NYLIUM).isViewBlocking(Properties::always).isSuffocating(Properties::always);
        static final BlockBehaviour.Properties CRIMSON_ROOT_THATCH = BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.COLOR_RED);
        static final BlockBehaviour.Properties WARPED_ROOT_THATCH = BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.COLOR_CYAN);
        static final BlockBehaviour.Properties TWISTED_SHROOMLIGHT = BlockBehaviour.Properties.ofFullCopy(Blocks.SHROOMLIGHT).mapColor(MapColor.COLOR_PURPLE);
        static final BlockBehaviour.Properties HOLLOW_SHROOMLIGHT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(1.0f).sound(SoundType.SHROOMLIGHT).randomTicks();
        static final BlockBehaviour.Properties HOLLOW_TWISTED_SHROOMLIGHT = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.0f).sound(SoundType.SHROOMLIGHT).randomTicks();
        static final BlockBehaviour.Properties SHROOMSTEM = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).randomTicks().strength(0.5f).sound(SoundType.ROOTS).lightLevel((state) -> 12).noOcclusion().isValidSpawn(Properties::never);
        static final BlockBehaviour.Properties TWISTED_SHROOMSTEM = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(0.5f).sound(SoundType.ROOTS).lightLevel((state) -> 12).noOcclusion().isValidSpawn(Properties::never);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_ASHEN_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_ASHEN_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties ASHEN_NETHER_BRICK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CRACKED_WARPED_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties CHISELED_WARPED_NETHER_BRICKS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICK_FENCE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICK_FENCE_GATE = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICK_SLAB = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICK_STAIRS = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);
        static final BlockBehaviour.Properties WARPED_NETHER_BRICK_WALL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(2.0f, 6.0f).sound(SoundType.NETHER_BRICKS);

        static final BlockBehaviour.Properties BOOMSTONE = BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(5.0f, 1.0f).lightLevel(state -> 3).isValidSpawn((state, blockGetter, pos, entityType) -> entityType.fireImmune()).hasPostProcess(Properties::always).emissiveRendering(Properties::always);
        static final BlockBehaviour.Properties ASH_BLOCK = BlockBehaviour.Properties.ofFullCopy(Blocks.POWDER_SNOW).mapColor(MapColor.COLOR_GRAY);
        static final BlockBehaviour.Properties WITHER_BONE_BLOCK = BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK).mapColor(MapColor.COLOR_BLACK);
        static final BlockBehaviour.Properties BASALT_FUMAROLE = BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT);
        static final BlockBehaviour.Properties BLACKSTONE_FUMAROLE = BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE);
        static final BlockBehaviour.Properties KILN = BlockBehaviour.Properties.ofFullCopy(Blocks.FURNACE).mapColor(MapColor.NETHER);

        private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos)
        {
            return true;
        }

        private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType)
        {
            return false;
        }
    }
}
