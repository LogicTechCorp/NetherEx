package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.stream.Stream;

public class NetherExCreativeTabs
{
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_TABS = RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, NetherExConstants.MOD_ID);

    public static final RegistryObject<CreativeModeTab, CreativeModeTab> DEFAULT = CREATIVE_TABS.register("default", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(Items.NETHERRACK))
            .title(Component.translatable("creative_mode_tab.netherex.default"))
            .displayItems((itemDisplayParameters, output) -> getSortedItemEntries().forEach((itemRegistryObject -> output.accept(itemRegistryObject.get()))))
            .build()

    );

    public static void initialize()
    {

    }

    private static List<RegistryObject<Item, ? extends Item>> getSortedItemEntries()
    {
        return Stream.concat(NetherExBlocks.ITEM_BLOCKS.getEntries().stream(), NetherExItems.ITEMS.getEntries().stream()).toList();
    }
}
