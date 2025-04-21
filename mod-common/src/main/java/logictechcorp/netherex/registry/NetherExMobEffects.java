package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.effect.NEMobEffect;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public class NetherExMobEffects
{
    private static final RegistrationProvider<MobEffect> MOB_EFFECTS = RegistrationProvider.get(BuiltInRegistries.MOB_EFFECT, NetherExConstants.MOD_ID);

    public static final RegistryObject<MobEffect, MobEffect> GENTLE = registerMobEffect("gentle", () ->
            new NEMobEffect(MobEffectCategory.NEUTRAL, 15063701)
                    .addAttributeModifier(
                            Attributes.ARMOR_TOUGHNESS,
                            NetherExConstants.resource("effect.armor_toughness"),
                            1.0d,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            NetherExConstants.resource("effect.attack_damage"),
                            -1.0d,
                            AttributeModifier.Operation.ADD_VALUE
                    )
    );
    public static final RegistryObject<MobEffect, MobEffect> ROUGH = registerMobEffect("rough", () ->
            new NEMobEffect(MobEffectCategory.BENEFICIAL, 10251191)
                    .addAttributeModifier(
                            Attributes.ATTACK_KNOCKBACK,
                            NetherExConstants.resource("effect.attack_knockback"),
                            1.0d,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.KNOCKBACK_RESISTANCE,
                            NetherExConstants.resource("effect.knockback_resistance"),
                            -1.0d,
                            AttributeModifier.Operation.ADD_VALUE
                    )
    );

    public static void initialize()
    {
    }

    private static RegistryObject<MobEffect, MobEffect> registerMobEffect(String name, MobEffectCategory category, int color)
    {
        return MOB_EFFECTS.register(name, () -> new NEMobEffect(category, color));
    }

    private static RegistryObject<MobEffect, MobEffect> registerMobEffect(String name, Supplier<MobEffect> mobEffectSupplier)
    {
        return MOB_EFFECTS.register(name, mobEffectSupplier);
    }
}