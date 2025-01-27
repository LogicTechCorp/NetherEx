package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public class NEModelProvider extends ModelProvider
{
    public NEModelProvider(PackOutput packOutput)
    {
        super(packOutput, NetherExConstants.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        new NEBlockModelGenerator(blockModels).registerModels();
        new NEItemModelProvider(itemModels).registerModels();
    }
}
