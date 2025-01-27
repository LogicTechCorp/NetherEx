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
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;

public class NEBigBrownElderMushroomFeature extends NEBigMushroomFeature
{
    public NEBigBrownElderMushroomFeature(Codec<NEBigMushroomFeatureConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected void randomizeMushroom(RandomSource random)
    {
        double uniform = random.nextDouble();
        double skew = Math.pow(uniform, 4);
        int minHeight = 3;
        int maxHeight = 9;
        stemHeight = (int) (minHeight + skew * (maxHeight - minHeight));
        stemThickness = stemHeight < 7 ? 1 : 2;
        capRadius = stemHeight / 2;
        capHeight = 2;
    }

    @Override
    protected void createCap()
    {
        boolean thickStem = stemThickness == 2;
        int adjustedStemRadius = capRadius + (thickStem ? 1 : 0);

        for (int x = -capRadius; x <= adjustedStemRadius; x++)
        {
            for (int z = -capRadius; z <= adjustedStemRadius; z++)
            {
                boolean corner1 = x == -capRadius && z == -capRadius;
                boolean corner2 = x == -capRadius && z == adjustedStemRadius;
                boolean corner3 = x == adjustedStemRadius && z == -capRadius;
                boolean corner4 = x == adjustedStemRadius && z == adjustedStemRadius;

                if (corner1 || corner2 || corner3 || corner4)
                {
                    continue;
                }

                Vec3i localPos = new Vec3i(x, stemHeight, z);
                MushroomPiece capPiece = new MushroomPiece(localPos, true, false, true, true, true, true, true);
                mushroomPieces.add(capPiece);
            }
        }

        for (int x = -capRadius - 1; x <= adjustedStemRadius + 1; x++)
        {
            for (int z = -capRadius - 1; z <= adjustedStemRadius + 1; z++)
            {
                boolean corner1 = x == -capRadius && z == -capRadius;
                boolean corner2 = x == -capRadius && z == adjustedStemRadius;
                boolean corner3 = x == adjustedStemRadius && z == -capRadius;
                boolean corner4 = x == adjustedStemRadius && z == adjustedStemRadius;

                boolean side1 = x == -capRadius - 1 && (z > -capRadius - 1 && z < adjustedStemRadius + 1);
                boolean side2 = x == adjustedStemRadius + 1 && (z > -capRadius - 1 && z < adjustedStemRadius + 1);
                boolean side3 = z == -capRadius - 1 && (x > -capRadius - 1 && x < adjustedStemRadius + 1);
                boolean side4 = z == adjustedStemRadius + 1 && (x > -capRadius - 1 && x < adjustedStemRadius + 1);

                if (!corner1 && !corner2 && !corner3 && !corner4 && !side1 && !side2 && !side3 && !side4)
                {
                    continue;
                }

                boolean north = side3 || x == -capRadius - 1 || x == adjustedStemRadius + 1;
                boolean south = side4 || x == -capRadius - 1 || x == adjustedStemRadius + 1;
                boolean west = side1 || z == -capRadius - 1 || z == adjustedStemRadius + 1;
                boolean east = side2 || z == -capRadius - 1 || z == adjustedStemRadius + 1;

                Vec3i localPos = new Vec3i(x, stemHeight - 1, z);
                MushroomPiece capPiece = new MushroomPiece(localPos, true, false, true, north, south, west, east);
                mushroomPieces.add(capPiece);
            }
        }
    }
}