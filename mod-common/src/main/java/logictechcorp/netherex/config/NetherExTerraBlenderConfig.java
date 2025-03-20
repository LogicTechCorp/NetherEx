package logictechcorp.netherex.config;

import com.teamresourceful.resourcefulconfig.api.annotations.*;
import logictechcorp.netherex.NetherExConstants;

@ConfigInfo(
        icon = "planet",
        title = "Terra Blender",
        titleTranslation = "config." + NetherExConstants.MOD_ID + ".terra_blender.info.title",
        description = "Terra Blender config options for NetherEx",
        descriptionTranslation = "config." + NetherExConstants.MOD_ID + ".terra_blender.info.description"
)
@Category(NetherExConstants.MOD_ID + ":terra_blender")
public final class NetherExTerraBlenderConfig
{
    @ConfigEntry(id = "terraBlender_regionWeight", translation = "config." + NetherExConstants.MOD_ID + ".terra_blender.region_weight")
    @Comment(value = "How common NetherEx biomes are in the Nether", translation = "config." + NetherExConstants.MOD_ID + ".terra_blender.region_weight.comment")
    @ConfigOption.Range(min = 1, max = 64)
    public static int terraBlenderRegionWeight = 4;
}
