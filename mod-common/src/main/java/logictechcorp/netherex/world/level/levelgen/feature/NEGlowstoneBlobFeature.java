package logictechcorp.netherex.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NEGlowstoneBlobFeature extends Feature<NoneFeatureConfiguration>
{
    public NEGlowstoneBlobFeature(Codec<NoneFeatureConfiguration> codec)
    {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        final WorldGenLevel level = context.level();
        final BlockPos pos = context.origin();
        final RandomSource random = context.random();

        if (!level.isEmptyBlock(pos))
        {
            return false;
        }
        else
        {
            BlockState aboveState = level.getBlockState(pos.above());

            if (!aboveState.is(BlockTags.BASE_STONE_NETHER))
            {
                return false;
            }
            else
            {
                level.setBlock(pos, Blocks.GLOWSTONE.defaultBlockState(), 2);

                for (int i = 0; i < 1500; i++)
                {
                    BlockPos randomPos = pos.offset(random.nextInt(8) - random.nextInt(8), -random.nextInt(12), random.nextInt(8) - random.nextInt(8));

                    if (level.getBlockState(randomPos).isAir())
                    {
                        int surroundingGlowstone = 0;

                        for (Direction direction : Direction.values())
                        {
                            if (level.getBlockState(randomPos.relative(direction)).is(Blocks.GLOWSTONE))
                            {
                                surroundingGlowstone++;
                            }

                            if (surroundingGlowstone > 1)
                            {
                                break;
                            }
                        }

                        if (surroundingGlowstone == 1)
                        {
                            level.setBlock(randomPos, Blocks.GLOWSTONE.defaultBlockState(), 2);
                        }
                    }
                }

                return true;
            }
        }
    }
}