package logictechcorp.netherex.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigInfo;
import logictechcorp.netherex.NetherExConstants;

@Config(
        value = NetherExConstants.MOD_ID,
        categories = {
                NetherExTerraBlenderConfig.class
        }
)
@ConfigInfo(
        title = NetherExConstants.MOD_NAME + " Config",
        titleTranslation = "config." + NetherExConstants.MOD_ID + ".info.title",
        description = "Config options for " + NetherExConstants.MOD_NAME,
        descriptionTranslation = "config." + NetherExConstants.MOD_ID + ".info.description",
        icon = "settings",
        links = {
                @ConfigInfo.Link(text = "Discord", value = "https://discord.gg/gxvRzEY", icon = "creeper"),
                @ConfigInfo.Link(text = "GitHub", value = "https://github.com/LogicTechCorp/NetherEx", icon = "github"),
                @ConfigInfo.Link(text = "CurseForge", value = "https://www.curseforge.com/minecraft/mc-mods/netherex", icon = "curseforge"),
                @ConfigInfo.Link(text = "Modrinth", value = "https://modrinth.com/mod/netherex", icon = "modrinth")
        }
)
public final class NetherExConfig
{
        
}