package logictechcorp.netherex.datagen.server.tags;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItemTags;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NEItemTagsProvider extends ItemTagsProvider
{
    public NEItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper)
    {
        super(packOutput, lookupProvider, blockTagProvider, NetherExConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider)
    {
        // Block Items
        tag(Tags.Items.NETHERRACKS)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get().asItem())
                .add(NetherExBlocks.FIERY_NETHERRACK.get().asItem())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get().asItem());
        tag(Tags.Items.ORE_BEARING_GROUND_NETHERRACK)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get().asItem())
                .add(NetherExBlocks.FIERY_NETHERRACK.get().asItem())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get().asItem());
        tag(ItemTags.GOLD_ORES)
                .add(NetherExBlocks.GLOOMY_GOLD_ORE.get().asItem())
                .add(NetherExBlocks.FIERY_GOLD_ORE.get().asItem())
                .add(NetherExBlocks.LIVELY_GOLD_ORE.get().asItem());
        tag(Tags.Items.ORES_QUARTZ)
                .add(NetherExBlocks.GLOOMY_QUARTZ_ORE.get().asItem())
                .add(NetherExBlocks.FIERY_QUARTZ_ORE.get().asItem())
                .add(NetherExBlocks.LIVELY_QUARTZ_ORE.get().asItem());
        tag(Tags.Items.ORES_IN_GROUND_NETHERRACK)
                .add(NetherExBlocks.GLOOMY_GOLD_ORE.get().asItem())
                .add(NetherExBlocks.FIERY_GOLD_ORE.get().asItem())
                .add(NetherExBlocks.LIVELY_GOLD_ORE.get().asItem())
                .add(NetherExBlocks.GLOOMY_QUARTZ_ORE.get().asItem())
                .add(NetherExBlocks.FIERY_QUARTZ_ORE.get().asItem())
                .add(NetherExBlocks.LIVELY_QUARTZ_ORE.get().asItem());

        // Items
        tag(Tags.Items.BRICKS_NETHER)
                .add(NetherExItems.GLOOMY_NETHER_BRICK.get())
                .add(NetherExItems.FIERY_NETHER_BRICK.get())
                .add(NetherExItems.LIVELY_NETHER_BRICK.get());
        tag(NetherExItemTags.NUGGETS_NETHERITE)
                .add(NetherExItems.NETHERITE_NUGGET.get());
        tag(Tags.Items.MUSHROOMS)
                .add(NetherExBlocks.BROWN_ELDER_MUSHROOM.get().asItem())
                .add(NetherExBlocks.RED_ELDER_MUSHROOM.get().asItem());
        tag(ItemTags.MEAT)
                .add(NetherExItems.RIBS.get())
                .add(NetherExItems.COOKED_RIBS.get());
        tag(Tags.Items.FOODS_RAW_MEAT)
                .add(NetherExItems.RIBS.get());
        tag(Tags.Items.FOODS_COOKED_MEAT)
                .add(NetherExItems.COOKED_RIBS.get());
        tag(Tags.Items.CROPS_NETHER_WART)
                .add(NetherExBlocks.WARPED_WART.get().asItem());

        tag(ItemTags.ARROWS)
                .add(NetherExItems.ASHEN_ARROW.get());
        tag(NetherExItemTags.SALAMANDER_FOOD)
                .add(Items.MAGMA_CREAM);
        tag(NetherExItemTags.MOGUS_FOOD)
                .addTag(Tags.Items.MUSHROOMS);
        tag(NetherExItemTags.FLAEMOTH_FOOD)
                .add(NetherExItems.SHROOMFRUIT.get())
                .add(NetherExItems.TWISTED_SHROOMFRUIT.get());
    }
}
