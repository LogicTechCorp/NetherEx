package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NESalamander;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NESalamanderModel extends DefaultedEntityGeoModel<NESalamander>
{
    public NESalamanderModel()
    {
        super(NetherExConstants.resource("salamander"), true);
    }

    @Override
    public ResourceLocation getTextureResource(NESalamander salamander, GeoRenderer<NESalamander> renderer)
    {
        return salamander.getVariant().value().texture();
    }

    @Override
    public void setCustomAnimations(NESalamander animatable, long instanceId, AnimationState<NESalamander> animationState)
    {
        super.setCustomAnimations(animatable, instanceId, animationState);

        float rootScale = animatable.isBaby() ? 0.5f : 1.0f;
        getBone("salamander").ifPresent(bone -> bone.updateScale(rootScale, rootScale, rootScale));
    }
}