package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
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
        basicItem(NetherExItems.WITHER_BONE.get());
        basicItem(NetherExItems.WITHER_BONE_MEAL.get());
        basicItem(NetherExItems.HOGLIN_TUSK.get());
        basicItem(NetherExItems.RIBS.get());
        basicItem(NetherExItems.COOKED_RIBS.get());
        basicItem(NetherExItems.STRIDER_BUCKET.get());
        basicItem(NetherExItems.ASH.get());
        basicItem(NetherExItems.ASHEN_ARROW.get());

        basicItem(NetherExItems.SPINOUT_SPAWN_EGG.get());
        basicItem(NetherExItems.WISP_SPAWN_EGG.get());
        basicItem(NetherExItems.SALAMANDER_SPAWN_EGG.get());
        basicItem(NetherExItems.MOGUS_SPAWN_EGG.get());
        basicItem(NetherExItems.FLAEMOTH_SPAWN_EGG.get());
    }

    private void basicItem(Item item)
    {
        itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    Identifier modLoc(String modelPath)
    {
        return NetherExConstants.identifier(modelPath);
    }

    Identifier mcLoc(String modelPath)
    {
        return Identifier.withDefaultNamespace(modelPath);
    }

    private Identifier mcBlockLoc(String name)
    {
        return mcLoc(name).withPrefix("block/");
    }

    private Identifier mcItemLoc(String name)
    {
        return mcLoc(name).withPrefix("item/");
    }

    private Identifier modBlockLoc(String name)
    {
        return modLoc(name).withPrefix("block/");
    }

    private Identifier modItemLoc(String name)
    {
        return modLoc(name).withPrefix("item/");
    }
}
