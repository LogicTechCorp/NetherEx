package logictechcorp.netherex.world.level.levelgen;

import logictechcorp.netherex.registry.NetherExBiomes;
import logictechcorp.netherex.registry.NetherExBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class NESurfaceRuleData
{
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final SurfaceRules.RuleSource GLOOMY_NETHERRACK = makeStateRule(NetherExBlocks.GLOOMY_NETHERRACK.get());
    private static final SurfaceRules.RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource FIERY_NETHERRACK = makeStateRule(NetherExBlocks.FIERY_NETHERRACK.get());
    private static final SurfaceRules.RuleSource ASH_BLOCK = makeStateRule(NetherExBlocks.ASH_BLOCK.get());
    private static final SurfaceRules.RuleSource LIVELY_NETHERRACK = makeStateRule(NetherExBlocks.LIVELY_NETHERRACK.get());
    private static final SurfaceRules.RuleSource ELDER_NYLIUM = makeStateRule(NetherExBlocks.ELDER_NYLIUM.get());

    public static SurfaceRules.RuleSource nether()
    {
        SurfaceRules.ConditionSource blockIsY32 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        SurfaceRules.ConditionSource surfaceIsY30 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource surfaceIsBelowY35 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource blockIsBelowY5 = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        SurfaceRules.ConditionSource surfaceIsHole = SurfaceRules.hole();
        SurfaceRules.ConditionSource patchNoise = SurfaceRules.noiseCondition(Noises.PATCH, -0.012d);
        SurfaceRules.ConditionSource stateSelectorNoise = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0d);
        SurfaceRules.ConditionSource biomeIsRuthlessSands = SurfaceRules.isBiome(NetherExBiomes.RUTHLESS_SANDS);
        SurfaceRules.ConditionSource biomeIsTorridWasteland = SurfaceRules.isBiome(NetherExBiomes.TORRID_WASTELAND);
        SurfaceRules.ConditionSource biomeIsFungiForest = SurfaceRules.isBiome(NetherExBiomes.FUNGI_FOREST);
        SurfaceRules.RuleSource placeGravel = SurfaceRules.ifTrue(patchNoise, SurfaceRules.ifTrue(surfaceIsY30, SurfaceRules.ifTrue(surfaceIsBelowY35, GRAVEL)));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                SurfaceRules.ifTrue(blockIsBelowY5, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(biomeIsRuthlessSands, GLOOMY_NETHERRACK),
                        SurfaceRules.ifTrue(biomeIsTorridWasteland, FIERY_NETHERRACK),
                        SurfaceRules.ifTrue(biomeIsFungiForest, LIVELY_NETHERRACK)
                )),
                SurfaceRules.ifTrue(biomeIsRuthlessSands, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, SurfaceRules.sequence(
                                SurfaceRules.ifTrue(stateSelectorNoise, SOUL_SAND), SOUL_SOIL
                        )),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(
                                placeGravel, SurfaceRules.ifTrue(stateSelectorNoise, SOUL_SAND), SOUL_SOIL
                        ))
                )),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.not(blockIsY32), SurfaceRules.ifTrue(surfaceIsHole, LAVA))
                )),
                SurfaceRules.ifTrue(biomeIsTorridWasteland, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, SurfaceRules.sequence(
                                SurfaceRules.ifTrue(stateSelectorNoise, FIERY_NETHERRACK), ASH_BLOCK
                        )),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(
                                placeGravel, SurfaceRules.ifTrue(stateSelectorNoise, FIERY_NETHERRACK), ASH_BLOCK
                        ))
                )),
                SurfaceRules.ifTrue(biomeIsFungiForest, SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ELDER_NYLIUM)
                )),
                SurfaceRules.ifTrue(biomeIsRuthlessSands, GLOOMY_NETHERRACK),
                SurfaceRules.ifTrue(biomeIsTorridWasteland, FIERY_NETHERRACK),
                SurfaceRules.ifTrue(biomeIsFungiForest, LIVELY_NETHERRACK)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}