package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class NEItemModelProvider
{
    private final ItemModelGenerators itemModels;

    public NEItemModelProvider(ItemModelGenerators inItemModels)
    {
        itemModels = inItemModels;
    }

    protected void registerModels()
    {
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            Item itemNetherBrick = BuiltInRegistries.ITEM.getValue(modLoc(typeName + "_nether_brick"));
            basicItem(itemNetherBrick);
        }

        basicItem(NetherExItems.NETHERITE_NUGGET.get());
        basicItem(NetherExItems.NETHERITE_HORSE_ARMOR.get());
        basicItem(NetherExItems.WITHER_BONE.get());
        basicItem(NetherExItems.WITHER_BONE_MEAL.get());
        basicItem(NetherExItems.HOGLIN_TUSK.get());
        basicItem(NetherExItems.RIBS.get());
        basicItem(NetherExItems.COOKED_RIBS.get());
        basicItem(NetherExItems.STRIDER_BUCKET.get());
        basicItem(NetherExItems.ASH.get());
        basicItem(NetherExItems.ASHEN_ARROW.get());

        spawnEgg(NetherExItems.SPINOUT_SPAWN_EGG.get(), 5651507, 16382457);
        spawnEgg(NetherExItems.WISP_SPAWN_EGG.get(), 16777215, 2667468);
        spawnEgg(NetherExItems.SALAMANDER_SPAWN_EGG.get(), 15690005, 0);
        spawnEgg(NetherExItems.MOGUS_SPAWN_EGG.get(), 10051392, 10489616);
        spawnEgg(NetherExItems.FLAEMOTH_SPAWN_EGG.get(), 13184077, 2399861);
    }

    private void basicItem(Item item)
    {
        itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    private void spawnEgg(Item item, int primaryColor, int secondaryColor)
    {
        itemModels.generateSpawnEgg(item, primaryColor, secondaryColor);
    }

    ResourceLocation modLoc(String modelPath)
    {
        return NetherExConstants.resource(modelPath);
    }

    ResourceLocation mcLoc(String modelPath)
    {
        return ResourceLocation.withDefaultNamespace(modelPath);
    }

    private ResourceLocation mcBlockLoc(String name)
    {
        return mcLoc(name).withPrefix("block/");
    }

    private ResourceLocation mcItemLoc(String name)
    {
        return mcLoc(name).withPrefix("item/");
    }

    private ResourceLocation modBlockLoc(String name)
    {
        return modLoc(name).withPrefix("block/");
    }

    private ResourceLocation modItemLoc(String name)
    {
        return modLoc(name).withPrefix("item/");
    }
}
