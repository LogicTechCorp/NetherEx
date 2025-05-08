package logictechcorp.netherex.datagen.server.tags;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class NEBiomeTagsProvider extends BiomeTagsProvider
{
    public NEBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper)
    {
        super(output, provider, NetherExConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        tag(BiomeTags.IS_NETHER)
                .add(NetherExBiomes.RUTHLESS_SANDS)
                .add(NetherExBiomes.TORRID_WASTELAND)
                .add(NetherExBiomes.FUNGI_FOREST);

        tag(Tags.Biomes.IS_MUSHROOM)
                .add(NetherExBiomes.FUNGI_FOREST);

        tag(Tags.Biomes.IS_HOT)
                .add(NetherExBiomes.TORRID_WASTELAND);

        tag(Tags.Biomes.IS_HOT_NETHER)
                .add(NetherExBiomes.TORRID_WASTELAND);

        tag(BiomeTags.HAS_NETHER_FOSSIL)
                .add(NetherExBiomes.RUTHLESS_SANDS);

        tag(BiomeTags.HAS_BASTION_REMNANT)
                .add(NetherExBiomes.RUTHLESS_SANDS)
                .add(NetherExBiomes.TORRID_WASTELAND)
                .add(NetherExBiomes.FUNGI_FOREST);
    }
}
