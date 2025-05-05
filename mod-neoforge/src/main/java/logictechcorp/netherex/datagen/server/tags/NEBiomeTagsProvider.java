package logictechcorp.netherex.datagen.server.tags;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
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
                .addOptionalTag(NetherExBiomes.RUTHLESS_SANDS.location())
                .addOptionalTag(NetherExBiomes.TORRID_WASTELAND.location())
                .addOptionalTag(NetherExBiomes.FUNGI_FOREST.location());

        tag(BiomeTags.HAS_NETHER_FOSSIL)
                .addOptionalTag(NetherExBiomes.RUTHLESS_SANDS.location());

        tag(BiomeTags.HAS_BASTION_REMNANT)
                .addOptionalTag(NetherExBiomes.RUTHLESS_SANDS.location())
                .addOptionalTag(NetherExBiomes.TORRID_WASTELAND.location())
                .addOptionalTag(NetherExBiomes.FUNGI_FOREST.location());
    }
}
