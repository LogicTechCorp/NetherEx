package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import logictechcorp.netherex.registry.NetherExCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;

@AutoService(NECreativeTabHelper.class)
public class NECreativeTabHelperFabric implements NECreativeTabHelper
{
    @Override
    public CreativeModeTab.DisplayItemsGenerator getDisplayItemsGenerator()
    {
        return (itemDisplayParameters, output) -> NetherExCreativeTabs.getSortedItemEntries().forEach((itemRegistryObject -> output.accept(itemRegistryObject.get())));
    }
}
