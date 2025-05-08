package logictechcorp.netherex.datagen.server.tags;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBlockTags;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class NEBlockTagsProvider extends BlockTagsProvider
{
    public NEBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider, NetherExConstants.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider)
    {
        tag(BlockTags.MUSHROOM_GROW_BLOCK)
                .add(NetherExBlocks.ELDER_NYLIUM.get());
        tag(NetherExBlockTags.NETHERRACK)
                .add(Blocks.NETHERRACK)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.FIERY_NETHERRACK.get())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get());
        tag(Tags.Blocks.NETHERRACKS)
                .addTag(NetherExBlockTags.NETHERRACK);
        tag(BlockTags.INFINIBURN_OVERWORLD)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.FIERY_NETHERRACK.get())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get());
        tag(BlockTags.BASE_STONE_NETHER)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.FIERY_NETHERRACK.get())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get());
        tag(BlockTags.NETHER_CARVER_REPLACEABLES)
                .add(NetherExBlocks.ASH_BLOCK.get())
                .add(NetherExBlocks.ELDER_NYLIUM.get());
        tag(NetherExBlockTags.THORNSTALK_PLACEABLE_ON)
                .add(Blocks.SOUL_SAND)
                .add(Blocks.SOUL_SOIL);
        tag(BlockTags.NYLIUM)
                .add(NetherExBlocks.ELDER_NYLIUM.get());
        tag(NetherExBlockTags.OBSIDIAN_HEATER)
                .add(Blocks.LAVA)
                .add(Blocks.MAGMA_BLOCK)
                .addTag(BlockTags.FIRE);
        tag(NetherExBlockTags.OBSIDIAN_COOLER)
                .add(Blocks.WATER)
                .addTag(BlockTags.ICE)
                .addTag(BlockTags.SNOW);
        tag(NetherExBlockTags.LOW_FUMAROLE_HEATER)
                .add(Blocks.MAGMA_BLOCK);
        tag(NetherExBlockTags.HIGH_FUMAROLE_HEATER)
                .add(Blocks.LAVA);
        tag(BlockTags.GOLD_ORES)
                .add(NetherExBlocks.GLOOMY_GOLD_ORE.get())
                .add(NetherExBlocks.FIERY_GOLD_ORE.get())
                .add(NetherExBlocks.LIVELY_GOLD_ORE.get());
        tag(Tags.Blocks.ORES_QUARTZ)
                .add(NetherExBlocks.GLOOMY_QUARTZ_ORE.get())
                .add(NetherExBlocks.FIERY_QUARTZ_ORE.get())
                .add(NetherExBlocks.LIVELY_QUARTZ_ORE.get());
        tag(BlockTags.FENCES)
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_GLOWSTONE_FENCE.get())
                .add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_GLOWSTONE_FENCE_GATE.get())
                .add(NetherExBlocks.NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_FENCE_GATE.get());
        tag(BlockTags.SLABS)
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_GLOWSTONE_SLAB.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_SLAB.get());
        tag(BlockTags.STAIRS)
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_GLOWSTONE_STAIRS.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_STAIRS.get());
        tag(BlockTags.WALLS)
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_WALL.get())
                .add(NetherExBlocks.POLISHED_GLOWSTONE_WALL.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_WALL.get());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(NetherExBlocks.THORNSTALK.get())
                .add(NetherExBlocks.BROWN_ELDER_MUSHROOM.get())
                .add(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get())
                .add(NetherExBlocks.RED_ELDER_MUSHROOM.get())
                .add(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get())
                .add(NetherExBlocks.WARPED_WART.get())
                .add(NetherExBlocks.SHROOMSTEM.get())
                .add(NetherExBlocks.TWISTED_SHROOMSTEM.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(NetherExBlocks.TWISTED_SHROOMLIGHT.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.GLOOMY_QUARTZ_ORE.get())
                .add(NetherExBlocks.GLOOMY_GOLD_ORE.get())
                .add(NetherExBlocks.GLOOMY_NETHERRACK_PATH.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRACKED_GLOOMY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_GLOOMY_NETHER_BRICKS.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.GLOOMY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_GLOOMY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.CHISELED_POLISHED_GLOOMY_NETHERRACK.get())

                .add(NetherExBlocks.FIERY_NETHERRACK.get())
                .add(NetherExBlocks.FIERY_QUARTZ_ORE.get())
                .add(NetherExBlocks.FIERY_GOLD_ORE.get())
                .add(NetherExBlocks.FIERY_NETHERRACK_PATH.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRACKED_FIERY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_FIERY_NETHER_BRICKS.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.FIERY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_FIERY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.CHISELED_POLISHED_FIERY_NETHERRACK.get())

                .add(NetherExBlocks.LIVELY_NETHERRACK.get())
                .add(NetherExBlocks.LIVELY_QUARTZ_ORE.get())
                .add(NetherExBlocks.LIVELY_GOLD_ORE.get())
                .add(NetherExBlocks.LIVELY_NETHERRACK_PATH.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRACKED_LIVELY_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_LIVELY_NETHER_BRICKS.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.LIVELY_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_LIVELY_NETHERRACK_WALL.get())
                .add(NetherExBlocks.CHISELED_POLISHED_LIVELY_NETHERRACK.get())

                .add(NetherExBlocks.GLOWING_OBSIDIAN.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK.get())
                .add(NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_FENCE.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_FENCE_GATE.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_SLAB.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_STAIRS.get())
                .add(NetherExBlocks.POLISHED_NETHERRACK_WALL.get())
                .add(NetherExBlocks.NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.CRACKED_CRIMSON_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_CRIMSON_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get())

                .add(NetherExBlocks.ASHEN_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRACKED_ASHEN_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.ASHEN_NETHER_BRICK_WALL.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICKS.get())
                .add(NetherExBlocks.CRACKED_WARPED_NETHER_BRICKS.get())
                .add(NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_FENCE.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_FENCE_GATE.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_SLAB.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_STAIRS.get())
                .add(NetherExBlocks.WARPED_NETHER_BRICK_WALL.get())

                .add(NetherExBlocks.BOOMSTONE.get())
                .add(NetherExBlocks.WITHER_BONE_BLOCK.get())
                .add(NetherExBlocks.BASALT_FUMAROLE.get())
                .add(NetherExBlocks.BLACKSTONE_FUMAROLE.get())
                .add(NetherExBlocks.KILN.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(NetherExBlocks.ASH_BLOCK.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(NetherExBlocks.BROWN_ELDER_MUSHROOM.get())
                .add(NetherExBlocks.RED_ELDER_MUSHROOM.get())
                .add(NetherExBlocks.WARPED_WART.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(NetherExBlocks.GLOWING_OBSIDIAN.get());
    }
}
