package logictechcorp.netherex.platform;

import logictechcorp.netherex.NetherExConstants;

import java.util.ServiceLoader;

public class Services
{
    public static <T> T load(Class<T> cls)
    {
        final T loadedService = ServiceLoader.load(cls)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + cls.getName()));

        NetherExConstants.LOG.debug("Loaded {} for service {}", loadedService, cls);
        return loadedService;
    }
}