package logictechcorp.netherex.datagen.client.language;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.registry.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class NELanguageProviderEN_US extends LanguageProvider
{
    public NELanguageProviderEN_US(PackOutput output)
    {
        super(output, NetherExConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        // Creative Mode Tabs
        add("creative_mode_tab.netherex.default", "NetherEx");

        // Blocks
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            String bricksName = typeName + "_nether_bricks";

            Block blockNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_netherrack"));
            Block blockNetherrackPath = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_netherrack_path"));
            Block blockNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc(bricksName));
            Block blockCrackedNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc("cracked_" + bricksName));
            Block blockChiseledNetherBricks = BuiltInRegistries.BLOCK.getValue(modLoc("chiseled_" + bricksName));
            Block blockPolishedNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc("polished_" + typeName + "_netherrack"));
            Block blockChiseledPolishedNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc("chiseled_polished_" + typeName + "_netherrack"));

            Item itemNetherBrick = BuiltInRegistries.ITEM.getValue(modLoc(typeName + "_nether_brick"));

            String typeDisplayName = typeName.substring(0, 1).toUpperCase() + typeName.substring(1);

            add(blockNetherrack, typeDisplayName + " Netherrack");
            add(blockNetherrackPath, typeDisplayName + " Netherrack Path");
            addBlockWithDecorative(blockNetherBricks, typeDisplayName + " Nether Bricks", true);
            add(blockCrackedNetherBricks, "Cracked " + typeDisplayName + " Nether Bricks");
            add(blockChiseledNetherBricks, "Chiseled " + typeDisplayName + " Nether Bricks");
            addBlockWithDecorative(blockPolishedNetherrack, "Polished " + typeDisplayName + " Netherrack", false);
            add(blockChiseledPolishedNetherrack, "Chiseled Polished " + typeDisplayName + " Netherrack");
            add(itemNetherBrick, typeDisplayName + " Nether Brick");
        }

        add(NetherExBlocks.THORNSTALK.get(), "Thornstalk");

        add(NetherExBlocks.ELDER_NYLIUM.get(), "Elder Nylium");
        add(NetherExBlocks.BROWN_ELDER_MUSHROOM.get(), "Brown Elder Mushroom");
        add(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get(), "Brown Elder Mushroom Block");
        add(NetherExBlocks.RED_ELDER_MUSHROOM.get(), "Red Elder Mushroom");
        add(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get(), "Red Elder Mushroom Block");
        add(NetherExBlocks.ELDER_MUSHROOM_STEM.get(), "Elder Mushroom Stem");

        add(NetherExBlocks.GLOWING_OBSIDIAN.get(), "Glowing Obsidian");
        addBlockWithDecorative(NetherExBlocks.POLISHED_NETHERRACK.get(), "Polished Netherrack", false);
        add(NetherExBlocks.CHISELED_POLISHED_NETHERRACK.get(), "Chiseled Polished Netherrack");
        addBlockWithDecorative(NetherExBlocks.POLISHED_GLOWSTONE.get(), "Polished Glowstone", false);
        add(NetherExBlocks.CHISELED_POLISHED_GLOWSTONE.get(), "Chiseled Polished Glowstone");
        add(Blocks.RED_NETHER_BRICKS, "Crimson Nether Bricks");
        add(NetherExBlocks.CRACKED_CRIMSON_NETHER_BRICKS.get(), "Cracked Crimson Nether Bricks");
        add(NetherExBlocks.CHISELED_CRIMSON_NETHER_BRICKS.get(), "Chiseled Crimson Nether Bricks");
        add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE.get(), "Crimson Nether Brick Fence");
        add(NetherExBlocks.CRIMSON_NETHER_BRICK_FENCE_GATE.get(), "Crimson Nether Brick Fence Gate");
        add(Blocks.RED_NETHER_BRICK_SLAB, "Crimson Nether Brick Slab");
        add(Blocks.RED_NETHER_BRICK_STAIRS, "Crimson Nether Brick Stairs");
        add(Blocks.RED_NETHER_BRICK_WALL, "Crimson Nether Brick Wall");
        add(NetherExBlocks.NETHER_BRICK_FENCE_GATE.get(), "Nether Brick Fence Gate");
        add(NetherExBlocks.WARPED_WART.get(), "Warped Wart");
        add(NetherExBlocks.CRIMSON_NYLIUM_PATH.get(), "Crimson Nylium Path");
        add(NetherExBlocks.WARPED_NYLIUM_PATH.get(), "Warped Nylium Path");
        add(NetherExBlocks.CRIMSON_ROOT_THATCH.get(), "Crimson Root Thatch");
        add(NetherExBlocks.WARPED_ROOT_THATCH.get(), "Warped Root Thatch");
        add(NetherExBlocks.TWISTED_SHROOMLIGHT.get(), "Twisted Shroomlight");
        add(NetherExBlocks.HOLLOW_SHROOMLIGHT.get(), "Hollow Shroomlight");
        add(NetherExBlocks.HOLLOW_TWISTED_SHROOMLIGHT.get(), "Hollow Twisted Shroomlight");
        add(NetherExBlocks.SHROOMSTEM.get(), "Shroomstem");
        add(NetherExBlocks.TWISTED_SHROOMSTEM.get(), "Twisted Shroomstem");

        addBlockWithDecorative(NetherExBlocks.ASHEN_NETHER_BRICKS.get(), "Ashen Nether Bricks", true);
        add(NetherExBlocks.CRACKED_ASHEN_NETHER_BRICKS.get(), "Cracked Ashen Nether Bricks");
        add(NetherExBlocks.CHISELED_ASHEN_NETHER_BRICKS.get(), "Chiseled Ashen Nether Bricks");
        addBlockWithDecorative(NetherExBlocks.WARPED_NETHER_BRICKS.get(), "Warped Nether Bricks", true);
        add(NetherExBlocks.CRACKED_WARPED_NETHER_BRICKS.get(), "Cracked Warped Nether Bricks");
        add(NetherExBlocks.CHISELED_WARPED_NETHER_BRICKS.get(), "Chiseled Warped Nether Bricks");

        add(NetherExBlocks.BOOMSTONE.get(), "Boomstone");
        add(NetherExBlocks.ASH_BLOCK.get(), "Ash Block");
        add(NetherExBlocks.WITHER_BONE_BLOCK.get(), "Wither Bone Block");
        add(NetherExBlocks.BASALT_FUMAROLE.get(), "Basalt Fumarole");
        add(NetherExBlocks.BLACKSTONE_FUMAROLE.get(), "Blackstone Fumarole");

        // Items
        add(NetherExItems.NETHERITE_NUGGET.get(), "Netherite Nugget");
        add(NetherExItems.NETHERITE_HORSE_ARMOR.get(), "Netherite Horse Armor");
        add(NetherExItems.WITHER_BONE.get(), "Wither Bone");
        add(NetherExItems.WITHER_BONE_MEAL.get(), "Wither Bone Meal");
        add(NetherExItems.HOGLIN_TUSK.get(), "Hoglin Tusk");
        add(NetherExItems.RIBS.get(), "Ribs");
        add(NetherExItems.COOKED_RIBS.get(), "Cooked Ribs");
        add(NetherExItems.SHROOMFRUIT.get(), "Shroomfruit");
        add(NetherExItems.TWISTED_SHROOMFRUIT.get(), "Twisted Shroomfruit");
        add(NetherExItems.STRIDER_BUCKET.get(), "Strider Bucket");
        add(NetherExItems.ASH.get(), "Ash");
        add(NetherExItems.ASHEN_ARROW.get(), "Ashen Arrow");

        add(NetherExItems.SPINOUT_SPAWN_EGG.get(), "Spinout Spawn Egg");
        add(NetherExItems.WISP_SPAWN_EGG.get(), "Wisp Spawn Egg");
        add(NetherExItems.SALAMANDER_SPAWN_EGG.get(), "Salamander Spawn Egg");
        add(NetherExItems.MOGUS_SPAWN_EGG.get(), "Mogus Spawn Egg");
        add(NetherExItems.FLAEMOTH_SPAWN_EGG.get(), "Flaemoth Spawn Egg");

        // Entities
        add(NetherExEntityTypes.SPINOUT.get(), "Spinout");
        add(NetherExEntityTypes.SALAMANDER.get(), "Salamander");
        add(NetherExEntityTypes.MOGUS.get(), "Mogus");
        add(NetherExEntityTypes.FLAEMOTH.get(), "Flaemoth");

        add(NetherExEntityTypes.ASHEN_ARROW.get(), "Ashen Arrow");

        // Biomes
        add(NetherExBiomes.RUTHLESS_SANDS, "Ruthless Sands");
        add(NetherExBiomes.TORRID_WASTELAND, "Torrid Wasteland");
        add(NetherExBiomes.FUNGI_FOREST, "Fungi Forest");

        // Mob Effects
        add(NetherExMobEffects.GENTLE.get(), "Gentle");
        add(NetherExMobEffects.ROUGH.get(), "Rough");

        // Potions
        List<Pair<String, String>> potionItemNames = List.of(
                Pair.of(Items.TIPPED_ARROW.getDescriptionId(), "Arrow of"),
                Pair.of(Items.POTION.getDescriptionId(), "Potion of"),
                Pair.of(Items.SPLASH_POTION.getDescriptionId(), "Splash Potion of"),
                Pair.of(Items.LINGERING_POTION.getDescriptionId(), "Lingering Potion of")
        );

        List<Pair<String, String>> potionNames = List.of(
                Pair.of("shrugging", "Shrugging"),
                Pair.of("long_shrugging", "Long Shrugging"),
                Pair.of("strong_shrugging", "Strong Shrugging"),
                Pair.of("knocking", "Knocking"),
                Pair.of("long_knocking", "Long Knocking"),
                Pair.of("strong_knocking", "Strong Knocking")
        );

        potionItemNames.forEach(potionItemNamePair ->
        {
            potionNames.forEach(potionNamePair ->
            {
                add(potionItemNamePair.getLeft() + ".effect." + potionNamePair.getLeft(), potionItemNamePair.getRight() + " " + potionNamePair.getRight());
            });
        });
    }

    @Override
    public void add(Block key, String name)
    {
        add(key.getDescriptionId(), name);

        if (!key.getDescriptionId().contains("minecraft"))
        {
            Item item = BuiltInRegistries.ITEM.getValue(BuiltInRegistries.BLOCK.getKey(key));

            if (item != Items.AIR)
            {
                addItem(() -> item, name);
            }
        }
    }

    private void addBlockWithDecorative(Block baseBlock, String baseBlockDisplayName, boolean isBaseBlockNamePlural)
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

        String decorativeBlockDisplayName = baseBlockDisplayName;

        if (isBaseBlockNamePlural)
        {
            decorativeBlockDisplayName = decorativeBlockDisplayName.substring(0, decorativeBlockDisplayName.length() - 1);
        }

        add(baseBlock, baseBlockDisplayName);
        add(baseBlockFence, decorativeBlockDisplayName + " Fence");
        add(baseBlockFenceGate, decorativeBlockDisplayName + " Fence Gate");
        add(baseBlockSlab, decorativeBlockDisplayName + " Slab");
        add(baseBlockStairs, decorativeBlockDisplayName + " Stairs");
        add(baseBlockWall, decorativeBlockDisplayName + " Wall");
    }

    private void add(ResourceKey<Biome> biomeResourceKey, String displayName)
    {
        add(biomeResourceKey.location().toLanguageKey("biome"), displayName);
    }

    private void add(RegistryObject<Potion, Potion> potion, String displayName)
    {
        add(potion.getId().toLanguageKey("potion"), displayName);
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
