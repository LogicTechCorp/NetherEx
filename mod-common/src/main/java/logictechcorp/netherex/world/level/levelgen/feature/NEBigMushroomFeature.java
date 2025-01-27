/* MIT License
 *
 * Copyright (c) 2019 cech12
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package logictechcorp.netherex.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import logictechcorp.netherex.world.level.levelgen.feature.config.NEBigMushroomFeatureConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;

public abstract class NEBigMushroomFeature extends Feature<NEBigMushroomFeatureConfiguration>
{
    protected int stemHeight;
    protected int stemThickness;
    protected int capHeight;
    protected int capRadius;
    protected List<MushroomPiece> mushroomPieces = new ArrayList<>();

    public NEBigMushroomFeature(Codec<NEBigMushroomFeatureConfiguration> codec)
    {
        super(codec);
    }

    protected void createMushroom(RandomSource random)
    {
        mushroomPieces.clear();
        randomizeMushroom(random);
        createStem();
        createCap();
    }

    protected abstract void randomizeMushroom(RandomSource random);

    protected void createStem()
    {
        for (int y = 0; y < stemHeight; y++)
        {
            for (int x = 0; x < stemThickness; x++)
            {
                for (int z = 0; z < stemThickness; z++)
                {
                    Vec3i localPos = new Vec3i(x, y, z);

                    boolean thinStem = stemThickness != 2;
                    boolean north = thinStem || z == 0;
                    boolean south = thinStem || z == 1;
                    boolean west = thinStem || x == 0;
                    boolean east = thinStem || x == 1;
                    MushroomPiece stemPiece = new MushroomPiece(localPos, false, false, false, north, south, west, east);
                    mushroomPieces.add(stemPiece);
                }
            }
        }
    }

    protected abstract void createCap();

    protected void placeMushroom(LevelAccessor level, BlockPos mushroomPos, BlockPos.MutableBlockPos mutablePos, NEBigMushroomFeatureConfiguration config)
    {
        RandomSource random = level.getRandom();

        for (MushroomPiece mushroomPiece : mushroomPieces)
        {
            mutablePos.setWithOffset(mushroomPos, mushroomPiece.localPos);

            BlockState mushroomState = mushroomPiece.getState(mutablePos, random, config);
            level.setBlock(mutablePos, mushroomState, 2);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<NEBigMushroomFeatureConfiguration> context)
    {
        LevelAccessor level = context.level();
        BlockPos mushroomPos = context.origin();
        NEBigMushroomFeatureConfiguration config = context.config();
        RandomSource random = level.getRandom();

        createMushroom(random);

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        if (!canGrow(level, mushroomPos, mutablePos, config))
        {
            return false;
        }

        placeMushroom(level, mushroomPos, mutablePos, config);
        return true;
    }

    private boolean canGrow(LevelAccessor level, BlockPos mushroomPos, BlockPos.MutableBlockPos mutablePos, NEBigMushroomFeatureConfiguration config)
    {
        int mushroomPosY = mushroomPos.getY();

        if (mushroomPosY < level.getMinY() || mushroomPosY + stemHeight + 1 > level.getMaxY())
        {
            return false;
        }

        for (int x = 0; x < stemThickness; x++)
        {
            for (int z = 0; z < stemThickness; z++)
            {
                mutablePos.setWithOffset(mushroomPos, x, -1, z);
                BlockState state = level.getBlockState(mutablePos);

                if (!state.is(BlockTags.MUSHROOM_GROW_BLOCK))
                {
                    return false;
                }
            }
        }

        for (MushroomPiece mushroomPiece : mushroomPieces)
        {
            mutablePos.setWithOffset(mushroomPos, mushroomPiece.localPos);
            BlockState state = level.getBlockState(mutablePos);

            if (!state.canBeReplaced() || state.getFluidState() != Fluids.EMPTY.defaultFluidState())
            {
                return false;
            }
        }

        return true;
    }

    protected record MushroomPiece(Vec3i localPos, boolean cap, boolean down, boolean up, boolean north, boolean south, boolean west, boolean east)
    {
        public BlockState getState(BlockPos mushroomPos, RandomSource random, NEBigMushroomFeatureConfiguration config)
        {
            BlockStateProvider stateProvider = cap ? config.capProvider() : config.stemProvider();
            BlockState state = stateProvider.getState(random, mushroomPos);

            if (state.hasProperty(HugeMushroomBlock.DOWN) && state.hasProperty(HugeMushroomBlock.UP) && state.hasProperty(HugeMushroomBlock.NORTH) && state.hasProperty(HugeMushroomBlock.SOUTH) && state.hasProperty(HugeMushroomBlock.WEST) && state.hasProperty(HugeMushroomBlock.EAST))
            {
                state = state.setValue(HugeMushroomBlock.DOWN, down).setValue(HugeMushroomBlock.UP, up).setValue(HugeMushroomBlock.NORTH, north).setValue(HugeMushroomBlock.SOUTH, south).setValue(HugeMushroomBlock.WEST, west).setValue(HugeMushroomBlock.EAST, east);
            }

            return state;
        }
    }
}