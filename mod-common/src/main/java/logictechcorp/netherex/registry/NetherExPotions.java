package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.platform.NEPotionHelper;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import java.util.function.Supplier;

public class NetherExPotions
{
    private static final RegistrationProvider<Potion> POTIONS = RegistrationProvider.get(BuiltInRegistries.POTION, NetherExConstants.MOD_ID);

    public static final RegistryObject<Potion, Potion> SHRUGGING = registerPotion("shrugging", () -> new MobEffectInstance(NetherExMobEffects.GENTLE.asHolder(), 3600));
    public static final RegistryObject<Potion, Potion> LONG_SHRUGGING = registerPotion("long_shrugging", () -> new MobEffectInstance(NetherExMobEffects.GENTLE.asHolder(), 6600));
    public static final RegistryObject<Potion, Potion> STRONG_SHRUGGING = registerPotion("strong_shrugging", () -> new MobEffectInstance(NetherExMobEffects.GENTLE.asHolder(), 1800, 1));

    public static final RegistryObject<Potion, Potion> KNOCKING = registerPotion("knocking", () -> new MobEffectInstance(NetherExMobEffects.ROUGH.asHolder(), 3600));
    public static final RegistryObject<Potion, Potion> LONG_KNOCKING = registerPotion("long_knocking", () -> new MobEffectInstance(NetherExMobEffects.ROUGH.asHolder(), 6600));
    public static final RegistryObject<Potion, Potion> STRONG_KNOCKING = registerPotion("strong_knocking", () -> new MobEffectInstance(NetherExMobEffects.ROUGH.asHolder(), 1800, 1));

    public static void initialize()
    {
        registerPotionBrews();
    }

    private static void registerPotionBrews()
    {
        NEPotionHelper.INSTANCE.addMix(Potions.AWKWARD, NetherExItems.HOGLIN_TUSK, KNOCKING.asHolder());
        NEPotionHelper.INSTANCE.addMix(KNOCKING.asHolder(), () -> Items.REDSTONE, LONG_KNOCKING.asHolder());
        NEPotionHelper.INSTANCE.addMix(KNOCKING.asHolder(), () -> Items.GLOWSTONE_DUST, STRONG_KNOCKING.asHolder());

        NEPotionHelper.INSTANCE.addMix(KNOCKING.asHolder(), () -> Items.FERMENTED_SPIDER_EYE, SHRUGGING.asHolder());
        NEPotionHelper.INSTANCE.addMix(SHRUGGING.asHolder(), () -> Items.REDSTONE, LONG_SHRUGGING.asHolder());
        NEPotionHelper.INSTANCE.addMix(LONG_KNOCKING.asHolder(), () -> Items.FERMENTED_SPIDER_EYE, LONG_SHRUGGING.asHolder());
        NEPotionHelper.INSTANCE.addMix(SHRUGGING.asHolder(), () -> Items.GLOWSTONE_DUST, STRONG_SHRUGGING.asHolder());
        NEPotionHelper.INSTANCE.addMix(STRONG_KNOCKING.asHolder(), () -> Items.FERMENTED_SPIDER_EYE, STRONG_SHRUGGING.asHolder());
    }

    private static RegistryObject<Potion, Potion> registerPotion(String name, Supplier<MobEffectInstance> mobEffectInstanceSupplier)
    {
        return POTIONS.register(name, () -> new Potion(name, mobEffectInstanceSupplier.get()));
    }
}