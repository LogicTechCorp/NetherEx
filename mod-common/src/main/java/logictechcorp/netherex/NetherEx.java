package logictechcorp.netherex;

import logictechcorp.netherex.config.NetherExConfig;
import logictechcorp.netherex.mixin.NEShovelItemAccessor;
import logictechcorp.netherex.registry.*;
import logictechcorp.netherex.world.level.levelgen.NESurfaceRuleData;
import logictechcorp.netherex.world.level.levelgen.region.NENetherRegion;
import net.minecraft.world.level.block.Blocks;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class NetherEx
{
    public static void onLoad()
    {
        registerConfig();
        initializeContent();
    }

    private static void registerConfig()
    {
        NetherExConstants.CONFIGURATOR.register(NetherExConfig.class);
    }

    private static void initializeContent()
    {
        NetherExFeatures.initialize();
        NetherExFeatureConfigs.initialize();
        NetherExFeaturePlacements.initialize();
        NetherExBlocks.initialize();
        NetherExItems.initialize();
        NetherExCreativeTabs.initialize();
        NetherExEntityTypes.initialize();
        NetherExEntityDataSerializers.initialize();
        NetherExEntitySubPredicates.initialize();
        NetherExMobEffects.initialize();
        NetherExPotions.initialize();
        NetherExRegistries.initialize();
        NetherExSalamanderVariants.initialize();
    }

    public static void onInitializeTerraBlender()
    {
        Regions.register(new NENetherRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, NetherExConstants.MOD_ID, NESurfaceRuleData.nether());
    }

    public static void onCommonSetup()
    {
        registerFlattenableBlocks();
    }

    private static void registerFlattenableBlocks()
    {
        NEShovelItemAccessor.getFlattenables().put(NetherExBlocks.GLOOMY_NETHERRACK.get(), NetherExBlocks.GLOOMY_NETHERRACK_PATH.get().defaultBlockState());
        NEShovelItemAccessor.getFlattenables().put(NetherExBlocks.FIERY_NETHERRACK.get(), NetherExBlocks.FIERY_NETHERRACK_PATH.get().defaultBlockState());
        NEShovelItemAccessor.getFlattenables().put(NetherExBlocks.LIVELY_NETHERRACK.get(), NetherExBlocks.LIVELY_NETHERRACK_PATH.get().defaultBlockState());
        NEShovelItemAccessor.getFlattenables().put(Blocks.CRIMSON_NYLIUM, NetherExBlocks.CRIMSON_NYLIUM_PATH.get().defaultBlockState());
        NEShovelItemAccessor.getFlattenables().put(Blocks.WARPED_NYLIUM, NetherExBlocks.WARPED_NYLIUM_PATH.get().defaultBlockState());
    }
}