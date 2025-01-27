package logictechcorp.netherex.datagen.server.tags;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.registry.NetherExBlockTags;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

        tag(NetherExBlockTags.NETHER_BASE_STONE)
                .add(Blocks.NETHERRACK)
                .add(Blocks.BASALT)
                .add(Blocks.BLACKSTONE)
                .add(NetherExBlocks.GLOOMY_NETHERRACK.get())
                .add(NetherExBlocks.FIERY_NETHERRACK.get())
                .add(NetherExBlocks.LIVELY_NETHERRACK.get());

        tag(NetherExBlockTags.THORNSTALK_PLACEABLE_ON)
                .add(Blocks.SOUL_SAND)
                .add(Blocks.SOUL_SOIL);

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

        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            Block blockNetherrack = BuiltInRegistries.BLOCK.getValue(modLoc(typeName + "_netherrack"));
            tag(BlockTags.INFINIBURN_OVERWORLD)
                    .add(blockNetherrack);
        }

        NetherExBlocks.BLOCKS.getEntries().forEach(blockRegistryObject ->
        {
            Block block = blockRegistryObject.get();
            String blockName = blockRegistryObject.getId().getPath();

            if (blockName.contains("fence"))
            {
                if (blockName.contains("gate"))
                {
                    tag(BlockTags.FENCE_GATES)
                            .add(block);
                }
                else
                {
                    tag(BlockTags.FENCES)
                            .add(block);
                }
            }
            else if (blockName.contains("slab"))
            {
                tag(BlockTags.SLABS)
                        .add(block);
            }
            else if (blockName.contains("stairs"))
            {
                tag(BlockTags.STAIRS)
                        .add(block);
            }
            else if (blockName.contains("wall"))
            {
                tag(BlockTags.WALLS)
                        .add(block);
            }
        });
    }

    public ResourceLocation modLoc(String name)
    {
        return NetherExConstants.resource(name);
    }
}
