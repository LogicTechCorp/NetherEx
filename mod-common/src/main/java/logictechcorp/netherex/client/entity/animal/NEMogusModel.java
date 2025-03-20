package logictechcorp.netherex.client.entity.animal;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.entity.animal.NEMogus;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class NEMogusModel extends DefaultedEntityGeoModel<NEMogus>
{
    public NEMogusModel()
    {
        super(NetherExConstants.resource("mogus"));
    }

    @Override
    public ResourceLocation getTextureResource(NEMogus mogus, GeoRenderer<NEMogus> renderer)
    {
        return mogus.getVariant().value().texture();
    }

    @Override
    public void setCustomAnimations(NEMogus animatable, long instanceId, AnimationState<NEMogus> animationState)
    {
        super.setCustomAnimations(animatable, instanceId, animationState);

        float rootScale = animatable.isBaby() ? 0.5f : 1.0f;
        getBone("mogus").ifPresent(bone -> bone.updateScale(rootScale, rootScale, rootScale));
    }
}