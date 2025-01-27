package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.world.level.levelgen.feature.NEBigBrownElderMushroomFeature;
import logictechcorp.netherex.world.level.levelgen.feature.NEBigRedElderMushroomFeature;
import logictechcorp.netherex.world.level.levelgen.feature.NEGlowstoneBlobFeature;
import logictechcorp.netherex.world.level.levelgen.feature.config.NEBigMushroomFeatureConfiguration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

public class NetherExFeatures
{
    private static final RegistrationProvider<Feature<?>> FEATURES = RegistrationProvider.get(BuiltInRegistries.FEATURE, NetherExConstants.MOD_ID);

    public static final RegistryObject<Feature<?>, Feature<NoneFeatureConfiguration>> GLOWSTONE_BLOB = register("glowstone_blob", () -> new NEGlowstoneBlobFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<?>, Feature<NEBigMushroomFeatureConfiguration>> HUGE_BROWN_ELDER_MUSHROOM = register("huge_brown_elder_mushroom", () -> new NEBigBrownElderMushroomFeature(NEBigMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<?>, Feature<NEBigMushroomFeatureConfiguration>> HUGE_RED_ELDER_MUSHROOM = register("huge_red_elder_mushroom", () -> new NEBigRedElderMushroomFeature(NEBigMushroomFeatureConfiguration.CODEC));

    public static void initialize()
    {
    }

    private static <FC extends FeatureConfiguration> RegistryObject<Feature<?>, Feature<FC>> register(String name, Supplier<Feature<FC>> featureSupplier)
    {
        return FEATURES.register(name, featureSupplier);
    }
}