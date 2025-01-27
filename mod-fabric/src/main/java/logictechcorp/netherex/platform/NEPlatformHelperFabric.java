package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.fabricmc.loader.api.FabricLoader;

@AutoService(NEPlatformHelper.class)
public class NEPlatformHelperFabric implements NEPlatformHelper
{
    @Override
    public String getPlatformName()
    {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId)
    {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment()
    {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
