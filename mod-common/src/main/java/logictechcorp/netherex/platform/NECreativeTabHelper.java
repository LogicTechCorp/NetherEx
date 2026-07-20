package logictechcorp.netherex.platform;

import net.minecraft.world.item.CreativeModeTab;

public interface NECreativeTabHelper
{
    NECreativeTabHelper INSTANCE = Services.load(NECreativeTabHelper.class);

    CreativeModeTab.DisplayItemsGenerator getDisplayItemsGenerator();
}
