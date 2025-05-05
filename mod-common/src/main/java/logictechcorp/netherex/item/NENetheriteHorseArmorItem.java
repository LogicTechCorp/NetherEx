package logictechcorp.netherex.item;

import logictechcorp.netherex.NetherExConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public class NENetheriteHorseArmorItem extends AnimalArmorItem
{
    public static final ResourceLocation TEXTURE = NetherExConstants.resource("textures/entity/equipment/horse_body/netherite.png");

    public NENetheriteHorseArmorItem(Properties properties)
    {
        super(ArmorMaterials.NETHERITE, BodyType.EQUESTRIAN, false, properties);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return TEXTURE;
    }
}
