package logictechcorp.netherex;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetherExConstants
{
    public static final String MOD_ID = "netherex";
    public static final String MOD_NAME = "NetherEx";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

    public static ResourceLocation resource(String name)
    {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}