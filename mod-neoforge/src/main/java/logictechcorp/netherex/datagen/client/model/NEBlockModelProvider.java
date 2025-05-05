package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.NEWarpedWartBlock;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NEBlockModelProvider extends BlockStateProvider
{
    public NEBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, NetherExConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            String bricksName = typeName + "_nether_bricks";
            Block blockNetherrack = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_netherrack"));
            Block blockQuartzOre = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_quartz_ore"));
            Block blockGoldOre = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_gold_ore"));
            Block blockNetherrackPath = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_netherrack_path"));
            Block blockNetherBricks = BuiltInRegistries.BLOCK.get(modLoc(bricksName));
            Block blockCrackedNetherBricks = BuiltInRegistries.BLOCK.get(modLoc("cracked_" + bricksName));
            Block blockChiseledNetherBricks = BuiltInRegistries.BLOCK.get(modLoc("chiseled_" + bricksName));
            Block blockPolishedNetherrack = BuiltInRegistries.BLOCK.get(modLoc("polished_" + typeName + "_netherrack"));
            Block blockChiseledPolishedNetherrack = BuiltInRegistries.BLOCK.get(modLoc("chiseled_polished_" + typeName + "_netherrack"));

            simpleBlock(blockNetherrack);
            simpleBlock(blockQuartzOre);
            simpleBlock(blockGoldOre);
            path(blockNetherrackPath, modBlockLoc(typeName + "_netherrack_path"), modBlockLoc(typeName + "_netherrack"), modBlockLoc(typeName + "_netherrack"));
            blockWithDecorative(blockNetherBricks, modBlockLoc(bricksName), true);
            simpleBlock(blockCrackedNetherBricks);
            simpleBlock(blockChiseledNetherBricks);
            blockWithDecorative(blockPolishedNetherrack, modBlockLoc("polished_" + typeName + "_netherrack"), false);
            simpleBlock(blockChiseledPolishedNetherrack);
        }

        doublePlant(NetherExBlocks.THORNSTALK);
        simpleBlock(NetherExBlocks.ELDER_NYLIUM.get(), models().cubeBottomTop(name(NetherExBlocks.ELDER_NYLIUM.get()), modBlockLoc("elder_nylium_side"), modBlockLoc("lively_netherrack"), modBlockLoc("elder_nylium_top")));
        cross(NetherExBlocks.BROWN_ELDER_MUSHROOM, true);
        cross(NetherExBlocks.RED_ELDER_MUSHROOM, true);
        mushroomBlock(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get(), modBlockLoc("brown_elder_mushroom_cap"), modBlockLoc("elder_mushroom_flesh"));
        mushroomBlock(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get(), modBlockLoc("red_elder_mushroom_cap"), modBlockLoc("elder_mushroom_flesh"));
        mushroomBlock(NetherExBlocks.ELDER_MUSHROOM_STEM.get(), modBlockLoc("elder_mushroom_stem"), modBlockLoc("elder_mushroom_flesh"));

        simpleBlock(NetherExBlocks.GLOWING_OBSIDIAN.get());
        blockWithDecorative(NetherExBlocks.POLISHED_NETHERRACK.get(), modBlockLoc("polished_netherrack"), false);
        simpleBlock(NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get());
        blockWithDecorative(NetherExBlocks.POLISHED_GLOWSTONE.get(), modBlockLoc("polished_glowstone"), false);
        simpleBlock(NetherExBlocks.CHISELED_POLISHED_GLOWSTONE.get());
        fenceGateBlock(NetherExBlocks.NETHER_BRICK_FENCE_GATE.get(), mcBlockLoc("nether_bricks"));
        fenceBlock(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get(), mcBlockLoc("red_nether_bricks"));
        fenceGateBlock(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get(), mcBlockLoc("red_nether_bricks"));
        simpleBlock(NetherExBlocks.CRACKED_CRIMSON_NETHER_BRICKS.get());
        simpleBlock(NetherExBlocks.CHISELED_CRIMSON_NETHER_BRICKS.get());

        warpedWart();
        path(NetherExBlocks.CRIMSON_NYLIUM_PATH.get(), modBlockLoc("crimson_nylium_path_top"), modBlockLoc("crimson_nylium_path_side"), mcBlockLoc("netherrack"));
        path(NetherExBlocks.WARPED_NYLIUM_PATH.get(), modBlockLoc("warped_nylium_path_top"), modBlockLoc("warped_nylium_path_side"), mcBlockLoc("netherrack"));
        thatch(NetherExBlocks.CRIMSON_ROOT_THATCH.get(), modBlockLoc("crimson_root_thatch"), modBlockLoc("crimson_root_thatch_extrudes"));
        thatch(NetherExBlocks.WARPED_ROOT_THATCH.get(), modBlockLoc("warped_root_thatch"), modBlockLoc("warped_root_thatch_extrudes"));
        simpleBlock(NetherExBlocks.TWISTED_SHROOMLIGHT.get());
        simpleBlock(NetherExBlocks.HOLLOW_SHROOMLIGHT.get());
        simpleBlock(NetherExBlocks.HOLLOW_TWISTED_SHROOMLIGHT.get());
        cross(NetherExBlocks.SHROOMSTEM, false);
        cross(NetherExBlocks.TWISTED_SHROOMSTEM, false);

        blockWithDecorative(NetherExBlocks.ASHEN_NETHER_BRICKS.get(), modBlockLoc("ashen_nether_bricks"), true);
        simpleBlock(NetherExBlocks.CRACKED_ASHEN_NETHER_BRICKS.get());
        simpleBlock(NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get());
        blockWithDecorative(NetherExBlocks.WARPED_NETHER_BRICKS.get(), modBlockLoc("warped_nether_bricks"), true);
        simpleBlock(NetherExBlocks.CRACKED_WARPED_NETHER_BRICKS.get());
        simpleBlock(NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get());

        simpleBlock(NetherExBlocks.BOOMSTONE.get());
        simpleBlock(NetherExBlocks.ASH_BLOCK.get());
        rotatedPillar(NetherExBlocks.WITHER_BONE_BLOCK.get(), modBlockLoc("wither_bone_block_side"), modBlockLoc("wither_bone_block_top"));
        bottomSlab(NetherExBlocks.BASALT_FUMAROLE.get(), mcBlockLoc("basalt_side"), mcBlockLoc("basalt_top"), modBlockLoc("basalt_fumarole"));
        bottomSlab(NetherExBlocks.BLACKSTONE_FUMAROLE.get(), mcBlockLoc("blackstone"), mcBlockLoc("blackstone"), modBlockLoc("blackstone_fumarole"));
        furnace(NetherExBlocks.KILN.get());
    }

    @Override
    public void simpleBlock(Block block, ConfiguredModel... models)
    {
        super.simpleBlock(block, models);
        simpleBlockItem(block, models[0].model);
    }

    @Override
    public void fenceBlock(FenceBlock block, ResourceLocation texture)
    {
        super.fenceBlock(block, texture);
        String name = name(block);
        String inventoryName = name + "_inventory";
        models().fenceInventory(inventoryName, texture);
        itemFromBlock(name, inventoryName);
    }

    @Override
    public void fenceGateBlock(FenceGateBlock block, ResourceLocation texture)
    {
        super.fenceGateBlock(block, texture);
        String blockName = name(block);
        itemFromBlock(blockName, blockName);
    }

    @Override
    public void slabBlock(SlabBlock block, ResourceLocation doubleSlab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top)
    {
        super.slabBlock(block, doubleSlab, side, bottom, top);
        String blockName = name(block);
        itemFromBlock(blockName, blockName);
    }

    @Override
    public void stairsBlock(StairBlock block, ResourceLocation texture)
    {
        super.stairsBlock(block, texture);
        String blockName = name(block);
        itemFromBlock(blockName, blockName);
    }

    @Override
    public void wallBlock(WallBlock block, ResourceLocation texture)
    {
        super.wallBlock(block, texture);
        String name = name(block);
        String inventoryName = name + "_inventory";
        models().wallInventory(inventoryName, texture);
        itemFromBlock(name, inventoryName);
    }

    private void slabBlock(SlabBlock slabBlock, ResourceLocation texture)
    {
        slabBlock(slabBlock, texture, texture, texture, texture);
    }

    private void blockWithDecorative(Block baseBlock, ResourceLocation texture, boolean isBaseBlockNamePlural)
    {
        String baseBlockName = name(baseBlock);

        if (isBaseBlockNamePlural)
        {
            baseBlockName = baseBlockName.substring(0, baseBlockName.length() - 1);
        }

        Block baseBlockFence = BuiltInRegistries.BLOCK.get(modLoc(baseBlockName + "_fence"));
        Block baseBlockFenceGate = BuiltInRegistries.BLOCK.get(modLoc(baseBlockName + "_fence_gate"));
        Block baseBlockSlab = BuiltInRegistries.BLOCK.get(modLoc(baseBlockName + "_slab"));
        Block baseBlockStairs = BuiltInRegistries.BLOCK.get(modLoc(baseBlockName + "_stairs"));
        Block baseBlockWall = BuiltInRegistries.BLOCK.get(modLoc(baseBlockName + "_wall"));

        simpleBlock(baseBlock);
        fenceBlock((FenceBlock) baseBlockFence, texture);
        fenceGateBlock((FenceGateBlock) baseBlockFenceGate, texture);
        slabBlock((SlabBlock) baseBlockSlab, texture);
        stairsBlock((StairBlock) baseBlockStairs, texture);
        wallBlock((WallBlock) baseBlockWall, texture);
    }

    private void doublePlant(RegistryObject<Block, ?> blockRegistryObject)
    {
        Block block = blockRegistryObject.get();
        String blockName = name(block);
        ResourceLocation bottomTexture = modBlockLoc(blockRegistryObject.getResourceKey().location().getPath() + "_bottom");
        ResourceLocation topTexture = modBlockLoc(blockRegistryObject.getResourceKey().location().getPath() + "_top");
        ModelFile bottomModel = models().cross(blockName + "_bottom", bottomTexture).renderType("cutout");
        ModelFile topModel = models().cross(blockName + "_top", topTexture).renderType("cutout");
        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .setModels(new ConfiguredModel(bottomModel))

                .partialState()
                .with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .setModels(new ConfiguredModel(topModel));
        flatItem(block, topTexture);
    }


    private void cross(RegistryObject<Block, ?> blockRegistryObject, boolean createItemModel)
    {
        Block block = blockRegistryObject.get();
        String blockName = name(block);
        ResourceLocation texture = modBlockLoc(blockRegistryObject.getResourceKey().location().getPath());
        ModelFile crossModel = models()
                .cross(blockName, texture)
                .renderType("cutout");

        getVariantBuilder(block)
                .partialState()
                .setModels(new ConfiguredModel(crossModel));

        if (!createItemModel)
        {
            return;
        }

        flatItem(block, texture);
    }

    private void mushroomBlock(HugeMushroomBlock block, ResourceLocation outsideTexture, ResourceLocation fleshTexture)
    {
        String blockName = name(block);
        ModelFile capModel = models().singleTexture(blockName, mcBlockLoc("template_single_face"), outsideTexture);

        String fleshName = blockName + "_flesh";
        ModelFile fleshModel = models().singleTexture(fleshName, mcBlockLoc("template_single_face"), fleshTexture);

        MultiPartBlockStateBuilder stateBuilder = getMultipartBuilder(block);
        PipeBlock.PROPERTY_BY_DIRECTION.forEach((direction, property) ->
        {
            int rotationX = 0;
            int rotationY = 0;

            if (direction == Direction.UP)
            {
                rotationX = 270;
            }
            else if (direction == Direction.DOWN)
            {
                rotationX = 90;
            }
            else
            {
                rotationY = (int) ((direction.toYRot() + 180) % 360);
            }

            stateBuilder.part()
                    .modelFile(fleshModel)
                    .uvLock(false)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .addModel()
                    .condition(property, false)
                    .end();
            stateBuilder.part()
                    .modelFile(capModel)
                    .uvLock(true)
                    .rotationX(rotationX)
                    .rotationY(rotationY)
                    .addModel()
                    .condition(property, true)
                    .end();
        });

        String blockInventoryName = blockName + "_inventory";
        models().cubeAll(blockInventoryName, outsideTexture);
        itemFromBlock(blockName, blockInventoryName);
    }

    private void rotatedPillar(RotatedPillarBlock block, ResourceLocation sideTexture, ResourceLocation endTexture)
    {
        String blockName = name(block);
        axisBlock(block, sideTexture, endTexture);
        itemFromBlock(blockName, blockName);
    }

    public void bottomSlab(Block block, ResourceLocation sideTexture, ResourceLocation bottomTexture, ResourceLocation topTexture)
    {
        String blockName = name(block);
        ModelFile modelFile = models().slab(blockName, sideTexture, bottomTexture, topTexture);
        getVariantBuilder(block)
                .partialState()
                .setModels(new ConfiguredModel(modelFile));
        itemFromBlock(blockName, blockName);
    }

    public void path(Block block, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture)
    {
        String blockName = name(block);
        ModelFile modelFile = models()
                .withExistingParent(blockName, modBlockLoc("path"))
                .texture("top", topTexture)
                .texture("side", sideTexture)
                .texture("bottom", bottomTexture)
                .texture("particle", topTexture);
        getVariantBuilder(block)
                .partialState()
                .setModels(new ConfiguredModel(modelFile));
        itemFromBlock(blockName, blockName);
    }

    public void thatch(Block block, ResourceLocation thatchTexture, ResourceLocation thatchExtrudesTexture)
    {
        String blockName = name(block);
        ModelFile modelFile = models()
                .withExistingParent(blockName, modBlockLoc("thatch"))
                .texture("thatch", thatchTexture)
                .texture("thatch_extrudes", thatchExtrudesTexture)
                .renderType("cutout");
        getVariantBuilder(block)
                .partialState()
                .setModels(new ConfiguredModel(modelFile));
        itemFromBlock(blockName, blockName);
    }

    public void furnace(Block block)
    {
        String blockName = name(block);
        ResourceLocation sideTexture = modBlockLoc(blockName + "_side");
        ResourceLocation frontTexture = modBlockLoc(blockName + "_front");
        ResourceLocation frontOnTexture = modBlockLoc(blockName + "_front_on");
        ResourceLocation topTexture = modBlockLoc(blockName + "_top");

        ModelFile furnace = models().orientable(blockName, sideTexture, frontTexture, topTexture);
        ModelFile furnaceOn = models().orientable(blockName + "_on", sideTexture, frontOnTexture, topTexture);

        getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.LIT, false)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setModels(new ConfiguredModel(furnace))

                .partialState()
                .with(BlockStateProperties.LIT, false)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .setModels(new ConfiguredModel(furnace, 0, 90, false))

                .partialState()
                .with(BlockStateProperties.LIT, false)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .setModels(new ConfiguredModel(furnace, 0, 180, false))

                .partialState()
                .with(BlockStateProperties.LIT, false)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .setModels(new ConfiguredModel(furnace, 0, 270, false))

                .partialState()
                .with(BlockStateProperties.LIT, true)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .setModels(new ConfiguredModel(furnaceOn))

                .partialState()
                .with(BlockStateProperties.LIT, true)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .setModels(new ConfiguredModel(furnaceOn, 0, 90, false))

                .partialState()
                .with(BlockStateProperties.LIT, true)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .setModels(new ConfiguredModel(furnaceOn, 0, 180, false))

                .partialState()
                .with(BlockStateProperties.LIT, true)
                .with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .setModels(new ConfiguredModel(furnaceOn, 0, 270, false));

        itemFromBlock(blockName, blockName);
    }

    public void warpedWart()
    {
        String blockName = name(NetherExBlocks.WARPED_WART.get());
        ModelFile modelFileStage0 = models()
                .withExistingParent(blockName + "_stage0", modBlockLoc(blockName))
                .texture("warped_wart", modBlockLoc("warped_wart_stage0"))
                .renderType("cutout");
        ModelFile modelFileStage1 = models()
                .withExistingParent(blockName + "_stage1", modBlockLoc(blockName))
                .texture("warped_wart", modBlockLoc("warped_wart_stage1"))
                .renderType("cutout");
        ModelFile modelFileStage2 = models()
                .withExistingParent(blockName + "_stage2", modBlockLoc(blockName))
                .texture("warped_wart", modBlockLoc("warped_wart_stage2"))
                .renderType("cutout");

        getVariantBuilder(NetherExBlocks.WARPED_WART.get())
                .partialState()
                .with(NEWarpedWartBlock.AGE, 0)
                .setModels(new ConfiguredModel(modelFileStage0))

                .partialState()
                .with(NEWarpedWartBlock.AGE, 1)
                .setModels(new ConfiguredModel(modelFileStage1))

                .partialState()
                .with(NEWarpedWartBlock.AGE, 2)
                .setModels(new ConfiguredModel(modelFileStage1))

                .partialState()
                .with(NEWarpedWartBlock.AGE, 3)
                .setModels(new ConfiguredModel(modelFileStage2));
        flatItem(NetherExBlocks.WARPED_WART.get(), modItemLoc("warped_wart"));
    }

    private void itemFromBlock(String modelName, String existingBlockName)
    {
        itemModels().withExistingParent(modelName, modBlockLoc(existingBlockName));
    }

    private void flatItem(Block block, ResourceLocation texture)
    {
        itemModels().singleTexture(name(block), mcItemLoc("generated"), "layer0", texture);
    }

    private String name(Block block)
    {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private ResourceLocation mcBlockLoc(String name)
    {
        return mcLoc(ModelProvider.BLOCK_FOLDER + "/" + name);
    }

    private ResourceLocation mcItemLoc(String name)
    {
        return mcLoc(ModelProvider.ITEM_FOLDER + "/" + name);
    }

    private ResourceLocation modBlockLoc(String name)
    {
        return modLoc(ModelProvider.BLOCK_FOLDER + "/" + name);
    }

    private ResourceLocation modItemLoc(String name)
    {
        return modLoc(ModelProvider.ITEM_FOLDER + "/" + name);
    }
}
