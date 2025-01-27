package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.Supplier;

@AutoService(NEPotionHelper.class)
public class NEPotionHelperFabric implements NEPotionHelper
{
    @Override
    public void addMix(Holder<Potion> input, Supplier<Item> reagentSupplier, Holder<Potion> result)
    {
        FabricBrewingRecipeRegistryBuilder.BUILD.register((builder) -> builder.addMix(input, reagentSupplier.get(), result));
    }
}
