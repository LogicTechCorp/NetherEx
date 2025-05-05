package logictechcorp.netherex.datagen.server.recipes;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class NERecipeProvider extends RecipeProvider
{
    public NERecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput)
    {
        // Blocks
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            String bricksName = typeName + "_nether_bricks";
            Block blockNetherrack = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_netherrack"));
            Block blockQuartzOre = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_quartz_ore"));
            Block blockGoldOre = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_gold_ore"));
            Block blockNetherBricks = BuiltInRegistries.BLOCK.get(modLoc(bricksName));
            Block blockNetherBricksSlab = BuiltInRegistries.BLOCK.get(modLoc(typeName + "_nether_brick_slab"));
            Block blockCrackedNetherBricks = BuiltInRegistries.BLOCK.get(modLoc("cracked_" + bricksName));
            Block blockChiseledNetherBricks = BuiltInRegistries.BLOCK.get(modLoc("chiseled_" + bricksName));
            Item itemNetherBrick = BuiltInRegistries.ITEM.get(modLoc(typeName + "_nether_brick"));

            smeltingResultFromBase(recipeOutput, itemNetherBrick, blockNetherrack);
            oreSmelting(recipeOutput, List.of(blockQuartzOre), RecipeCategory.MISC, Items.QUARTZ, 0.2F, 200, "quartz");
            oreBlasting(recipeOutput, List.of(blockQuartzOre), RecipeCategory.MISC, Items.QUARTZ, 0.2F, 100, "quartz");
            oreSmelting(recipeOutput, List.of(blockGoldOre), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, 200, "gold_ingot");
            oreBlasting(recipeOutput, List.of(blockGoldOre), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, 100, "gold_ingot");
            oreBlasting(recipeOutput, List.of(blockNetherrack), RecipeCategory.MISC, itemNetherBrick, 0.1F, 100, typeName + "_nether_brick");
            twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, blockNetherBricks, itemNetherBrick);
            smeltingResultFromBase(recipeOutput, blockCrackedNetherBricks, blockNetherBricks);
            chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, blockChiseledNetherBricks, blockNetherBricksSlab);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, blockChiseledNetherBricks, blockNetherBricks);
            decorativeForBlock(recipeOutput, blockNetherBricks, itemNetherBrick, true, true);
        }

        oreBlasting(recipeOutput, List.of(Blocks.NETHERRACK), RecipeCategory.MISC, Items.NETHER_BRICK, 0.1F, 100, "nether_brick");
        polished(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.POLISHED_NETHERRACK.get(), Blocks.NETHERRACK);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.POLISHED_NETHERRACK.get(), Blocks.NETHERRACK);
        decorativeForBlock(recipeOutput, NetherExBlocks.POLISHED_NETHERRACK.get(), Items.NETHER_BRICK, true, false);
        chiseled(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get(), NetherExBlocks.POLISHED_NETHERRACK_SLAB.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get(), NetherExBlocks.POLISHED_NETHERRACK.get());

        polished(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.POLISHED_GLOWSTONE.get(), Blocks.GLOWSTONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.POLISHED_GLOWSTONE.get(), Blocks.GLOWSTONE);
        decorativeForBlock(recipeOutput, NetherExBlocks.POLISHED_GLOWSTONE.get(), Items.NETHER_BRICK, true, false);
        chiseled(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.CHISELED_POLISHED_GLOWSTONE.get(), NetherExBlocks.POLISHED_GLOWSTONE_SLAB.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.POLISHED_GLOWSTONE.get(), NetherExBlocks.CHISELED_POLISHED_GLOWSTONE.get());

        fenceGate(recipeOutput, NetherExBlocks.NETHER_BRICK_FENCE_GATE.get(), Items.NETHER_BRICK, Blocks.NETHER_BRICKS);
        fence(recipeOutput, NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get(), Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK, 6);
        fenceGate(recipeOutput, NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get(), Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS);

        smeltingResultFromBase(recipeOutput, NetherExBlocks.CRACKED_CRIMSON_NETHER_BRICKS.get(), Blocks.RED_NETHER_BRICKS);
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.CHISELED_CRIMSON_NETHER_BRICKS.get(), Blocks.RED_NETHER_BRICK_SLAB);
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.CRIMSON_ROOT_THATCH.get(), Blocks.CRIMSON_ROOTS);
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.WARPED_ROOT_THATCH.get(), Blocks.WARPED_ROOTS);

        twoByTwoCross(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.ASHEN_NETHER_BRICKS.get(), 2, Blocks.NETHER_BRICKS, NetherExItems.ASH.get());
        decorativeForBlock(recipeOutput, NetherExBlocks.ASHEN_NETHER_BRICKS.get(), Items.NETHER_BRICK, true, true);
        smeltingResultFromBase(recipeOutput, NetherExBlocks.CRACKED_ASHEN_NETHER_BRICKS.get(), NetherExBlocks.ASHEN_NETHER_BRICKS.get());
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get(), NetherExBlocks.ASHEN_NETHER_BRICK_SLAB.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get(), NetherExBlocks.ASHEN_NETHER_BRICKS.get());

        twoByTwoCross(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.WARPED_NETHER_BRICKS.get(), 2, Blocks.NETHER_BRICKS, NetherExBlocks.WARPED_WART.get());
        decorativeForBlock(recipeOutput, NetherExBlocks.WARPED_NETHER_BRICKS.get(), Items.NETHER_BRICK, true, true);
        smeltingResultFromBase(recipeOutput, NetherExBlocks.CRACKED_WARPED_NETHER_BRICKS.get(), NetherExBlocks.WARPED_NETHER_BRICKS.get());
        chiseled(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get(), NetherExBlocks.WARPED_NETHER_BRICK_SLAB.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get(), NetherExBlocks.WARPED_NETHER_BRICKS.get());

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.BOOMSTONE.get(), Items.FIRE_CHARGE);
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.ASH_BLOCK.get(), NetherExItems.ASH.get());

        shaped(RecipeCategory.DECORATIONS, NetherExBlocks.KILN.get())
                .define('#', Blocks.NETHER_BRICKS)
                .define('X', Blocks.FURNACE)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_nether_bricks", has(Blocks.NETHER_BRICKS))
                .save(recipeOutput);

        // Items
        nineBlockStorageRecipesWithCustomPacking(recipeOutput, RecipeCategory.MISC, NetherExItems.NETHERITE_NUGGET.get(), RecipeCategory.MISC, Items.NETHERITE_INGOT, "netherite_ingot_from_nuggets", "netherite_ingot");

        Ingredient netheriteMaterial = Ingredient.of(
                Items.NETHERITE_PICKAXE,
                Items.NETHERITE_SHOVEL,
                Items.NETHERITE_AXE,
                Items.NETHERITE_HOE,
                Items.NETHERITE_SWORD,
                Items.NETHERITE_HELMET,
                Items.NETHERITE_CHESTPLATE,
                Items.NETHERITE_LEGGINGS,
                Items.NETHERITE_BOOTS,
                NetherExItems.NETHERITE_HORSE_ARMOR.get()
        );

        SimpleCookingRecipeBuilder.smelting(netheriteMaterial, RecipeCategory.MISC, NetherExItems.NETHERITE_NUGGET.get(), 1.0f, 1600)
                .unlockedBy("has_netherite_pickaxe", has(Items.NETHERITE_PICKAXE))
                .unlockedBy("has_netherite_shovel", has(Items.NETHERITE_SHOVEL))
                .unlockedBy("has_netherite_axe", has(Items.NETHERITE_AXE))
                .unlockedBy("has_netherite_hoe", has(Items.NETHERITE_HOE))
                .unlockedBy("has_netherite_sword", has(Items.NETHERITE_SWORD))
                .unlockedBy("has_netherite_helmet", has(Items.NETHERITE_HELMET))
                .unlockedBy("has_netherite_chestplate", has(Items.NETHERITE_CHESTPLATE))
                .unlockedBy("has_netherite_leggings", has(Items.NETHERITE_LEGGINGS))
                .unlockedBy("has_netherite_boots", has(Items.NETHERITE_BOOTS))
                .unlockedBy("has_netherite_horse_armor", has(NetherExItems.NETHERITE_HORSE_ARMOR.get()))
                .save(recipeOutput, getSmeltingRecipeName(NetherExItems.NETHERITE_NUGGET.get()));

        SimpleCookingRecipeBuilder.blasting(netheriteMaterial, RecipeCategory.MISC, NetherExItems.NETHERITE_NUGGET.get(), 1.0f, 800)
                .unlockedBy("has_netherite_pickaxe", has(Items.NETHERITE_PICKAXE))
                .unlockedBy("has_netherite_shovel", has(Items.NETHERITE_SHOVEL))
                .unlockedBy("has_netherite_axe", has(Items.NETHERITE_AXE))
                .unlockedBy("has_netherite_hoe", has(Items.NETHERITE_HOE))
                .unlockedBy("has_netherite_sword", has(Items.NETHERITE_SWORD))
                .unlockedBy("has_netherite_helmet", has(Items.NETHERITE_HELMET))
                .unlockedBy("has_netherite_chestplate", has(Items.NETHERITE_CHESTPLATE))
                .unlockedBy("has_netherite_leggings", has(Items.NETHERITE_LEGGINGS))
                .unlockedBy("has_netherite_boots", has(Items.NETHERITE_BOOTS))
                .unlockedBy("has_netherite_horse_armor", has(NetherExItems.NETHERITE_HORSE_ARMOR.get()))
                .save(recipeOutput, getBlastingRecipeName(NetherExItems.NETHERITE_NUGGET.get()));

        shapeless(recipeOutput, NetherExItems.WITHER_BONE_MEAL.get(), 3, NetherExItems.WITHER_BONE.get());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeOutput, RecipeCategory.MISC, NetherExItems.WITHER_BONE_MEAL.get(), RecipeCategory.BUILDING_BLOCKS, NetherExBlocks.WITHER_BONE_BLOCK.get(), "wither_bone_meal_from_wither_bone_block", "bonemeal");
        cook(recipeOutput, NetherExItems.RIBS.get(), NetherExItems.COOKED_RIBS.get());
        smeltingResultFromBase(recipeOutput, NetherExItems.RIBS.get(), NetherExItems.COOKED_RIBS.get());
        shapeless(recipeOutput, NetherExItems.ASHEN_ARROW.get(), 1, Items.ARROW, NetherExItems.ASH.get());
    }

    protected void shapeless(RecipeOutput recipeOutput, ItemLike outputItem, int amount, ItemLike... inputItems)
    {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputItem, amount);
        Arrays.stream(inputItems).toList().forEach(inputItem -> builder.requires(inputItem).unlockedBy(getHasName(inputItem), has(inputItem)));
        builder.save(recipeOutput);
    }

    protected void twoByTwoCross(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, int amount, ItemLike itemA, ItemLike itemB)
    {
        shaped(category, result, amount)
                .define('#', itemA)
                .define('&', itemB)
                .pattern("#&")
                .pattern("&#")
                .unlockedBy(getHasName(itemA), has(itemA))
                .unlockedBy(getHasName(itemB), has(itemB))
                .save(recipeOutput);
    }

    protected void cook(RecipeOutput recipeOutput, ItemLike foodItem, ItemLike cookedFoodItem)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(foodItem), RecipeCategory.FOOD, cookedFoodItem, 0.35f, 200)
                .unlockedBy(getHasName(foodItem), has(foodItem))
                .save(recipeOutput);
        simpleCookingRecipe(recipeOutput, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, foodItem, cookedFoodItem, 0.35f);
        simpleCookingRecipe(recipeOutput, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, foodItem, cookedFoodItem, 0.35f);
    }

    protected void decorativeForBlock(RecipeOutput recipeOutput, Block baseBlock, Item fenceBarItem, boolean addStoneCuttingRecipes, boolean isBaseBlockNamePlural)
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

        fence(recipeOutput, baseBlockFence, baseBlock, fenceBarItem, 6);
        fenceGate(recipeOutput, baseBlockFenceGate, fenceBarItem, baseBlock);
        slab(recipeOutput, baseBlockSlab, baseBlock);
        stairs(recipeOutput, baseBlockStairs, baseBlock);
        wall(recipeOutput, baseBlockWall, baseBlock);

        if (addStoneCuttingRecipes)
        {
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlockFence, baseBlock);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlockFenceGate, baseBlock);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlockSlab, baseBlock, 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlockStairs, baseBlock);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, baseBlockWall, baseBlock);
        }
    }

    protected void fence(RecipeOutput recipeOutput, ItemLike fence, ItemLike sideMaterial, ItemLike barMaterial, int amount)
    {
        shaped(RecipeCategory.DECORATIONS, fence, amount)
                .define('W', sideMaterial)
                .define('#', barMaterial)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(sideMaterial), has(sideMaterial))
                .unlockedBy(getHasName(barMaterial), has(barMaterial))
                .save(recipeOutput);
    }

    protected void fenceGate(RecipeOutput recipeOutput, ItemLike fenceGate, ItemLike sideMaterial, ItemLike gateMaterial)
    {
        shaped(RecipeCategory.REDSTONE, fenceGate)
                .define('#', sideMaterial)
                .define('W', gateMaterial)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(sideMaterial), has(sideMaterial))
                .unlockedBy(getHasName(gateMaterial), has(gateMaterial))
                .save(recipeOutput);
    }

    protected void slab(RecipeOutput recipeOutput, ItemLike slab, ItemLike material)
    {
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected void stairs(RecipeOutput recipeOutput, ItemLike stairs, ItemLike material)
    {
        shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                .define('#', material)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected void wall(RecipeOutput recipeOutput, ItemLike wall, ItemLike material)
    {
        wallBuilder(RecipeCategory.DECORATIONS, wall, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    private String name(Block block)
    {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation modLoc(String name)
    {
        return NetherExConstants.resource(name);
    }
}
