package logictechcorp.netherex.datagen.server.advancements;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NEAdvancements
{
    public NEAdvancements()
    {

    }

    public static AdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        return new AdvancementProvider(output, registries, List.of(
                new NENetherAdvancements(),
                new NEVanillaNetherAdvancements()
        ));
    }
}
