package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.function.BiConsumer;

public class NEEquipmentAssetProvider extends EquipmentAssetProvider
{
    public NEEquipmentAssetProvider(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output)
    {
        output.accept(EquipmentAssets.NETHERITE, horse("netherite"));
    }

    public static EquipmentClientInfo horse(String name)
    {
        return EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(NetherExConstants.resource(name), false))
                .build();
    }
}
