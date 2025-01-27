package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

@AutoService(NEPlatformHelper.class)
public class NEPlatformHelperNeoForge implements NEPlatformHelper
{

    @Override
    public String getPlatformName()
    {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId)
    {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment()
    {

        return !FMLLoader.isProduction();
    }
}