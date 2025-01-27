package logictechcorp.netherex.platform;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.Supplier;

public interface NEPotionHelper
{
    NEPotionHelper INSTANCE = Services.load(NEPotionHelper.class);

    void addMix(Holder<Potion> input, Supplier<Item> reagentSupplier, Holder<Potion> result);

    record PotionBrew(Holder<Potion> input, Supplier<Item> reagentSupplier, Holder<Potion> result)
    {

    }
}
