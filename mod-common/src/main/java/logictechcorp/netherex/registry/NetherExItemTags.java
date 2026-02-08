package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class NetherExItemTags
{
    public static final TagKey<Item> SALAMANDER_FOOD = createTag("salamander_food");
    public static final TagKey<Item> MOGUS_FOOD = createTag("mogus_food");
    public static final TagKey<Item> FLAEMOTH_FOOD = createTag("flaemoth_food");

    public static final TagKey<Item> NUGGETS_NETHERITE = createTag("nuggets/netherite");

    public static TagKey<Item> createTag(String name)
    {
        return TagKey.create(Registries.ITEM, NetherExConstants.resource(name));
    }

    public static TagKey<Item> createCTag(String name)
    {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}