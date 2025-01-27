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

public class NEBigRedElderMushroomFeature extends NEBigMushroomFeature
{
    public NEBigRedElderMushroomFeature(Codec<NEBigMushroomFeatureConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected void randomizeMushroom(RandomSource random)
    {
        double uniform = random.nextDouble();
        double skew = Math.pow(uniform, 4);
        int minHeight = 4;
        int maxHeight = 10;
        stemHeight = (int) (minHeight + skew * (maxHeight - minHeight));
        stemThickness = stemHeight < 8 ? 1 : 2;
        capRadius = stemHeight / 2;
        capHeight = capRadius;
    }

    @Override
    protected void createCap()
    {
        boolean thickStem = stemThickness == 2;
        int adjustedCapRadius = capRadius + (thickStem ? 1 : 0);

        for (int y = 0; y <= capHeight + 1; y++)
        {
            for (int x = -capRadius; x <= adjustedCapRadius; x++)
            {
                for (int z = -capRadius; z <= adjustedCapRadius; z++)
                {
                    Vec3i localPos = new Vec3i(x, y, z);

                    if (!isInSphere(localPos, thickStem))
                    {
                        boolean down = isInSphere(localPos.below(), thickStem);
                        boolean north = isInSphere(localPos.north(), thickStem);
                        boolean south = isInSphere(localPos.south(), thickStem);
                        boolean west = isInSphere(localPos.west(), thickStem);
                        boolean east = isInSphere(localPos.east(), thickStem);

                        if (down || north || south || west || east)
                        {
                            MushroomPiece mushroomPiece = new MushroomPiece(localPos.above(stemHeight - capHeight - 1), true, false, true, !north, !south, !west, !east);
                            mushroomPieces.add(mushroomPiece);
                        }
                    }
                }
            }
        }
    }

    boolean isInSphere(Vec3i mushroomPos, boolean thickStem)
    {
        float horizontalOffset = thickStem ? 0.5f : 0.0f;
        float x = mushroomPos.getX() - horizontalOffset;
        float y = mushroomPos.getY() - 0.5f;
        float z = mushroomPos.getZ() - horizontalOffset;
        return (x * x) + (y * y) + (z * z) < (capRadius * capRadius);
    }
}