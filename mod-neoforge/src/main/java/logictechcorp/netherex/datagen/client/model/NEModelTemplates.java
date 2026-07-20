package logictechcorp.netherex.datagen.client.model;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;

import java.util.Optional;

public class NEModelTemplates
{
    public static final ModelTemplate PATH = create("path", TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM);
    public static final ExtendedModelTemplate THATCH = create("thatch", NETextureSlots.THATCH, NETextureSlots.THATCH_EXTRUDES).extend().build();
    public static final ExtendedModelTemplate CROSS_NOT_TINTED = BlockModelGenerators.PlantType.NOT_TINTED.getCross().extend().build();

    private static ModelTemplate create(String name, TextureSlot... textureSlots)
    {
        return new ModelTemplate(Optional.of(NetherExConstants.identifier(name).withPrefix("block/")), Optional.empty(), textureSlots);
    }
}
