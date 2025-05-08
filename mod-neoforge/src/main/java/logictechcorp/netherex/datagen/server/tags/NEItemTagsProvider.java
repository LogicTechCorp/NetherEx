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

import java.util.concurrent.CompletableFuture;

public class NEItemTagsProvider extends ItemTagsProvider
{
    public NEItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider)
    {
        super(packOutput, lookupProvider, blockTagProvider, NetherExConstants.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider)
    {
        tag(ItemTags.ARROWS)
                .add(NetherExItems.ASHEN_ARROW.get());

        tag(NetherExItemTags.SALAMANDER_FOOD)
                .add(Items.MAGMA_CREAM);

        tag(NetherExItemTags.MOGUS_FOOD)
                .add(Items.BROWN_MUSHROOM)
                .add(Items.RED_MUSHROOM)
                .add(NetherExBlocks.BROWN_ELDER_MUSHROOM.get().asItem())
                .add(NetherExBlocks.RED_ELDER_MUSHROOM.get().asItem())
                .addTag(Tags.Items.MUSHROOMS);

        tag(NetherExItemTags.FLAEMOTH_FOOD)
                .add(NetherExItems.SHROOMFRUIT.get())
                .add(NetherExItems.TWISTED_SHROOMFRUIT.get());
    }
}
