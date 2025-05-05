package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.block.state.properties.NENetherrackType;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NEItemModelProvider extends ItemModelProvider
{
    public NEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, NetherExConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        for (NENetherrackType netherrackType : NENetherrackType.values())
        {
            String typeName = netherrackType.getSerializedName();
            Item itemNetherBrick = BuiltInRegistries.ITEM.get(modLoc(typeName + "_nether_brick"));
            basicItem(itemNetherBrick);
        }

        basicItem(NetherExItems.NETHERITE_NUGGET.get());
        basicItem(NetherExItems.NETHERITE_HORSE_ARMOR.get());
        basicItem(NetherExItems.WITHER_BONE.get());
        basicItem(NetherExItems.WITHER_BONE_MEAL.get());
        basicItem(NetherExItems.HOGLIN_TUSK.get());
        basicItem(NetherExItems.RIBS.get());
        basicItem(NetherExItems.COOKED_RIBS.get());
        basicItem(NetherExItems.SHROOMFRUIT.get());
        basicItem(NetherExItems.TWISTED_SHROOMFRUIT.get());
        basicItem(NetherExItems.STRIDER_BUCKET.get());
        basicItem(NetherExItems.ASH.get());
        basicItem(NetherExItems.ASHEN_ARROW.get());

        spawnEgg(NetherExItems.SPINOUT_SPAWN_EGG);
        spawnEgg(NetherExItems.SALAMANDER_SPAWN_EGG);
        spawnEgg(NetherExItems.MOGUS_SPAWN_EGG);
        spawnEgg(NetherExItems.FLAEMOTH_SPAWN_EGG);
        spawnEgg(NetherExItems.WISP_SPAWN_EGG);
    }

    private void spawnEgg(RegistryObject<Item, SpawnEggItem> spawnEggItemRegistryObject)
    {
        withExistingParent(spawnEggItemRegistryObject.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}