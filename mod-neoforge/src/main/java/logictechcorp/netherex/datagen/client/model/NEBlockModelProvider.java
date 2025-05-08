package logictechcorp.netherex.datagen.client.model;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class NEBlockModelProvider
{
    private final BlockModelGenerators blockModels;

    public NEBlockModelProvider(BlockModelGenerators inBlockModels)
    {
        blockModels = inBlockModels;
    }

    void registerModels()
    {
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            String bricksName = typeName + "_nether_bricks";
            Block blockNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_netherrack"));
            Block blockQuartzOre = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_quartz_ore"));
            Block blockGoldOre = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_gold_ore"));
            Block blockNetherrackPath = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_netherrack_path"));
            Block blockNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc(bricksName));
            Block blockCrackedNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc("cracked_" + bricksName));
            Block blockChiseledNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc("chiseled_" + bricksName));
            Block blockPolishedNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc("polished_" + typeName + "_netherrack"));
            Block blockChiseledPolishedNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc("chiseled_polished_" + typeName + "_netherrack"));

            simple(blockNetherrack);
            simple(blockQuartzOre);
            simple(blockGoldOre);
            path(blockNetherrackPath, modBlockLoc(typeName + "_netherrack_path"), modBlockLoc(typeName + "_netherrack_path_side"), modBlockLoc(typeName + "_netherrack"));
            familyDecorative(blockNetherBricks, true);
            simple(blockCrackedNetherBricks);
            simple(blockChiseledNetherBricks);
            familyDecorative(blockPolishedNetherrack, false);
            simple(blockChiseledPolishedNetherrack);
        }

        doublePlant(NetherExBlocks.THORNSTALK.get());
        cubeTopBottom(NetherExBlocks.ELDER_NYLIUM.get(), modBlockLoc("elder_nylium_top"), modBlockLoc("elder_nylium_side"), modBlockLoc("lively_netherrack"));
        crossNotTinted(NetherExBlocks.BROWN_ELDER_MUSHROOM.get());
        crossNotTinted(NetherExBlocks.RED_ELDER_MUSHROOM.get());

        ResourceLocation fleshTexture = modBlockLoc("elder_mushroom_flesh");
        TextureMapping fleshTextureMapping = new TextureMapping().put(TextureSlot.TEXTURE, fleshTexture);
        ResourceLocation fleshModel = ModelTemplates.SINGLE_FACE.create(fleshTexture, fleshTextureMapping, blockModels.modelOutput);
        mushroomBlock(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get(), modBlockLoc("brown_elder_mushroom_cap"), fleshModel);
        mushroomBlock(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get(), modBlockLoc("red_elder_mushroom_cap"), fleshModel);
        mushroomBlock(NetherExBlocks.ELDER_MUSHROOM_STEM.get(), modBlockLoc("elder_mushroom_stem"), fleshModel);

        simple(NetherExBlocks.GLOWING_OBSIDIAN.get());
        familyDecorative(NetherExBlocks.POLISHED_NETHERRACK.get(), false);
        simple(NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get());
        familyDecorative(NetherExBlocks.POLISHED_GLOWSTONE.get(), false);
        simple(NetherExBlocks.CHISELED_POLISHED_GLOWSTONE.get());
        family(Blocks.NETHER_BRICKS).fenceGate(NetherExBlocks.NETHER_BRICK_FENCE_GATE.get());
        family(Blocks.RED_NETHER_BRICKS).fence(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get()).fenceGate(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get());
        simple(NetherExBlocks.CRACKED_CRIMSON_NETHER_BRICKS.get());
        simple(NetherExBlocks.CHISELED_CRIMSON_NETHER_BRICKS.get());

        warpedWart();
        path(NetherExBlocks.CRIMSON_NYLIUM_PATH.get(), modBlockLoc("crimson_nylium_path_top"), modBlockLoc("crimson_nylium_path_side"), mcBlockLoc("netherrack"));
        path(NetherExBlocks.WARPED_NYLIUM_PATH.get(), modBlockLoc("warped_nylium_path_top"), modBlockLoc("warped_nylium_path_side"), mcBlockLoc("netherrack"));
        thatch(NetherExBlocks.CRIMSON_ROOT_THATCH.get(), modBlockLoc("crimson_root_thatch"), modBlockLoc("crimson_root_thatch_extrudes"));
        thatch(NetherExBlocks.WARPED_ROOT_THATCH.get(), modBlockLoc("warped_root_thatch"), modBlockLoc("warped_root_thatch_extrudes"));
        simple(NetherExBlocks.TWISTED_SHROOMLIGHT.get());
        simple(NetherExBlocks.HOLLOW_SHROOMLIGHT.get());
        simple(NetherExBlocks.HOLLOW_TWISTED_SHROOMLIGHT.get());
        crossNotTinted(NetherExBlocks.SHROOMSTEM.get());
        crossNotTinted(NetherExBlocks.TWISTED_SHROOMSTEM.get());

        familyDecorative(NetherExBlocks.ASHEN_NETHER_BRICKS.get(), true);
        simple(NetherExBlocks.CRACKED_ASHEN_NETHER_BRICKS.get());
        simple(NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get());
        familyDecorative(NetherExBlocks.WARPED_NETHER_BRICKS.get(), true);
        simple(NetherExBlocks.CRACKED_WARPED_NETHER_BRICKS.get());
        simple(NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get());

        simple(NetherExBlocks.BOOMSTONE.get());
        simple(NetherExBlocks.ASH_BLOCK.get());
        blockModels.createAxisAlignedPillarBlock(NetherExBlocks.WITHER_BONE_BLOCK.get(), TexturedModel.COLUMN);
        slabLike(NetherExBlocks.BASALT_FUMAROLE.get(), mcBlockLoc("basalt_side"), mcBlockLoc("basalt_top"), modBlockLoc("basalt_fumarole"));
        slabLike(NetherExBlocks.BLACKSTONE_FUMAROLE.get(), mcBlockLoc("blackstone"), mcBlockLoc("blackstone"), modBlockLoc("blackstone_fumarole"));
        blockModels.createFurnace(NetherExBlocks.KILN.get(), TexturedModel.ORIENTABLE_ONLY_TOP);
    }

    void simple(Block block)
    {
        blockModels.createTrivialCube(block);
    }

    private BlockModelGenerators.BlockFamilyProvider family(Block block)
    {
        return blockModels.family(block);
    }

    private void familyDecorative(Block baseBlock, boolean isBaseBlockNamePlural)
    {
        String baseBlockName = name(baseBlock);

        if (isBaseBlockNamePlural)
        {
            baseBlockName = baseBlockName.substring(0, baseBlockName.length() - 1);
        }

        Block baseBlockFence = BuiltInRegistries.BLOCK.getValue(modLoc(baseBlockName + "_fence"));
        Block baseBlockFenceGate = BuiltInRegistries.BLOCK.getValue(modLoc(baseBlockName + "_fence_gate"));
        Block baseBlockSlab = BuiltInRegistries.BLOCK.getValue(modLoc(baseBlockName + "_slab"));
        Block baseBlockStairs = BuiltInRegistries.BLOCK.getValue(modLoc(baseBlockName + "_stairs"));
        Block baseBlockWall = BuiltInRegistries.BLOCK.getValue(modLoc(baseBlockName + "_wall"));

        family(baseBlock)
                .fence(baseBlockFence)
                .fenceGate(baseBlockFenceGate)
                .slab(baseBlockSlab)
                .stairs(baseBlockStairs)
                .wall(baseBlockWall);
    }

    private void cubeTopBottom(Block block, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture)
    {
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.TOP, topTexture)
                .put(TextureSlot.SIDE, sideTexture)
                .put(TextureSlot.BOTTOM, bottomTexture)
                .copyForced(TextureSlot.TOP, TextureSlot.PARTICLE);
        ResourceLocation modelLocation = ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelLocation)));
    }

    private void slabLike(Block block, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture)
    {
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.TOP, topTexture)
                .put(TextureSlot.BOTTOM, sideTexture)
                .put(TextureSlot.SIDE, bottomTexture)
                .copyForced(TextureSlot.TOP, TextureSlot.PARTICLE);
        ResourceLocation modelLocation = ModelTemplates.SLAB_BOTTOM.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelLocation)));
    }

    private void path(Block block, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation bottomTexture)
    {
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.TOP, topTexture)
                .put(TextureSlot.SIDE, sideTexture)
                .put(TextureSlot.BOTTOM, bottomTexture)
                .copyForced(TextureSlot.TOP, TextureSlot.PARTICLE);
        ResourceLocation modelLocation = NEModelTemplates.PATH.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, modelLocation));
    }

    private void thatch(Block block, ResourceLocation thatch, ResourceLocation thatchExtrudes)
    {
        TextureMapping textureMapping = new TextureMapping()
                .put(NETextureSlots.THATCH, thatch)
                .put(NETextureSlots.THATCH_EXTRUDES, thatchExtrudes)
                .copyForced(NETextureSlots.THATCH, TextureSlot.PARTICLE);
        ResourceLocation modelLocation = NEModelTemplates.THATCH.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLocation));
    }

    private void crossNotTinted(Block block)
    {
        blockModels.registerSimpleFlatItemModel(block);

        TextureMapping textureMapping = BlockModelGenerators.PlantType.NOT_TINTED.getTextureMapping(block);
        ResourceLocation modelLocation = NEModelTemplates.CROSS_NOT_TINTED.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLocation));
    }

    private void doublePlant(Block block)
    {
        blockModels.registerSimpleFlatItemModel(block, "_top");

        ResourceLocation topModelLocation = blockModels.createSuffixedVariant(block, "_top", NEModelTemplates.CROSS_NOT_TINTED, TextureMapping::cross);
        ResourceLocation bottomModelLocation = blockModels.createSuffixedVariant(block, "_bottom", NEModelTemplates.CROSS_NOT_TINTED, TextureMapping::cross);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(BlockStateProperties.DOUBLE_BLOCK_HALF)
                        .select(DoubleBlockHalf.LOWER, Variant.variant().with(VariantProperties.MODEL, bottomModelLocation))
                        .select(DoubleBlockHalf.UPPER, Variant.variant().with(VariantProperties.MODEL, topModelLocation))));
    }

    private void mushroomBlock(Block block, ResourceLocation capTexture, ResourceLocation fleshModel)
    {
        ResourceLocation blockModel = ModelTemplates.SINGLE_FACE.create(block, TextureMapping.defaultTexture(capTexture), blockModels.modelOutput);

        blockModels.blockStateOutput
                .accept(MultiPartGenerator.multiPart(block)
                        .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, blockModel))
                        .with(Condition.condition().term(BlockStateProperties.EAST, true),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, blockModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                        .with(VariantProperties.UV_LOCK, true)
                        )
                        .with(Condition.condition().term(BlockStateProperties.SOUTH, true),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, blockModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                        .with(VariantProperties.UV_LOCK, true)
                        )
                        .with(Condition.condition().term(BlockStateProperties.WEST, true),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, blockModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                        .with(VariantProperties.UV_LOCK, true)
                        )
                        .with(Condition.condition().term(BlockStateProperties.UP, true),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, blockModel)
                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                                        .with(VariantProperties.UV_LOCK, true)
                        )
                        .with(Condition.condition().term(BlockStateProperties.DOWN, true),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, blockModel)
                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                        .with(VariantProperties.UV_LOCK, true)
                        )
                        .with(Condition.condition().term(BlockStateProperties.NORTH, false), Variant.variant().with(VariantProperties.MODEL, fleshModel))
                        .with(Condition.condition().term(BlockStateProperties.EAST, false),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, fleshModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                        .with(VariantProperties.UV_LOCK, false)
                        )
                        .with(Condition.condition().term(BlockStateProperties.SOUTH, false),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, fleshModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                        .with(VariantProperties.UV_LOCK, false)
                        )
                        .with(Condition.condition().term(BlockStateProperties.WEST, false),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, fleshModel)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                        .with(VariantProperties.UV_LOCK, false)
                        )
                        .with(Condition.condition().term(BlockStateProperties.UP, false),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, fleshModel)
                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)
                                        .with(VariantProperties.UV_LOCK, false)
                        )
                        .with(Condition.condition().term(BlockStateProperties.DOWN, false),
                                Variant.variant()
                                        .with(VariantProperties.MODEL, fleshModel)
                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                                        .with(VariantProperties.UV_LOCK, false)
                        )
                );
        blockModels.registerSimpleItemModel(block, TexturedModel.CUBE
                .updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, capTexture))
                .createWithSuffix(block, "_inventory", blockModels.modelOutput)
        );
    }

    private void warpedWart()
    {
        Block warpedWart = NetherExBlocks.WARPED_WART.get();
        int[] ages = {0, 1, 1, 2};
        Int2ObjectMap<ResourceLocation> objectMap = new Int2ObjectOpenHashMap<>();
        PropertyDispatch propertyDispatch = PropertyDispatch.property(BlockStateProperties.AGE_3).generate((index) ->
        {
            int age = ages[index];
            ResourceLocation resourcelocation = objectMap.computeIfAbsent(age, (unused) ->
                    blockModels.createSuffixedVariant(warpedWart, "_stage" + age, NEModelTemplates.WARPED_WART, resourceLocation ->
                            new TextureMapping().put(NETextureSlots.WARPED_WART, resourceLocation)));
            return Variant.variant().with(VariantProperties.MODEL, resourcelocation);
        });
        blockModels.registerSimpleFlatItemModel(warpedWart.asItem());
        blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(warpedWart).with(propertyDispatch));
    }

    private String name(Block block)
    {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    ResourceLocation modLoc(String modelPath)
    {
        return NetherExConstants.resource(modelPath);
    }

    ResourceLocation mcLoc(String modelPath)
    {
        return ResourceLocation.withDefaultNamespace(modelPath);
    }

    private ResourceLocation mcBlockLoc(String name)
    {
        return mcLoc(name).withPrefix("block/");
    }

    private ResourceLocation mcItemLoc(String name)
    {
        return mcLoc(name).withPrefix("item/");
    }

    private ResourceLocation modBlockLoc(String name)
    {
        return modLoc(name).withPrefix("block/");
    }

    private ResourceLocation modItemLoc(String name)
    {
        return modLoc(name).withPrefix("item/");
    }
}
