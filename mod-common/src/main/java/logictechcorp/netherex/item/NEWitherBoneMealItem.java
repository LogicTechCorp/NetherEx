/*
 * MIT License
 *
 * Copyright (c) 2023,2024 OffsetMonkey538
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

// Modified from code by OffsetMonkey538 found here: https://github.com/OffsetMods538/withered-bone-meal/blob/f2aef1f9c06fe7f7ccc99e7b1e27073345fd1b16/src/main/java/top/offsetmonkey538/witheredbonemeal/item/WitheredBoneMealItem.java

package logictechcorp.netherex.item;

import logictechcorp.netherex.mixin.NECoralBlockAccessor;
import logictechcorp.netherex.mixin.NECoralFanBlockAccessor;
import logictechcorp.netherex.mixin.NECoralPlantBlockAccessor;
import logictechcorp.netherex.mixin.NECropBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

public class NEWitherBoneMealItem extends Item
{
    public NEWitherBoneMealItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (useOnGround(level, stack, pos, state))
        {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public static boolean useOnGround(Level level, ItemStack stack, BlockPos pos, BlockState state)
    {
        final Block block = state.getBlock();

        if (block instanceof WitherRoseBlock)
        {
            return false;
        }

        if (block instanceof RootsBlock)
        {
            return false;
        }

        if (block instanceof NetherSproutsBlock)
        {
            return false;
        }

        if (block instanceof NetherrackBlock netherrack)
        {
            return handleVanillaBonemeal(level, stack, pos, netherrack, state, 0.5d);
        }

        if (block instanceof NyliumBlock nylium)
        {
            return handleVanillaBonemeal(level, stack, pos, nylium, state, 0.5d);
        }

        if (block instanceof FungusBlock fungus)
        {
            return handleVanillaBonemeal(level, stack, pos, fungus, state);
        }

        if (block instanceof WeepingVinesBlock || block instanceof WeepingVinesPlantBlock || block instanceof TwistingVinesBlock || block instanceof TwistingVinesPlantBlock)
        {
            return handleVanillaBonemeal(level, stack, pos, (BonemealableBlock) block, state);
        }

        if (block instanceof TallFlowerBlock)
        {
            return replaceTallBlockWith(level, stack, pos, state, Blocks.WITHER_ROSE);
        }

        if (block instanceof FlowerBlock)
        {
            return replaceWith(level, stack, pos, Blocks.WITHER_ROSE);
        }

        if (block instanceof GrassBlock || block instanceof MyceliumBlock || block instanceof RootedDirtBlock || state.is(Blocks.PODZOL))
        {
            return replaceWith(level, stack, pos, Blocks.DIRT, 0.5d);
        }

        if (state.is(Blocks.MUDDY_MANGROVE_ROOTS))
        {
            return replaceWith(level, stack, pos, Blocks.MUD);
        }

        if (block instanceof NetherWartBlock)
        {
            return handleNetherWart(level, stack, pos, state);
        }

        if (block instanceof CropBlock cropBlock)
        {
            return handleAgedBlock(level, stack, pos, state, ((NECropBlockAccessor) cropBlock).invokeGetAgeProperty());
        }
        if (block instanceof StemBlock)
        {
            return handleAgedBlock(level, stack, pos, state, StemBlock.AGE);
        }

        if (block instanceof CoralFanBlock coralBlock)
        {
            return replaceWith(level, stack, pos, ((NECoralFanBlockAccessor) coralBlock).getDeadBlock().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
        }

        if (block instanceof CoralPlantBlock coralBlock)
        {
            return replaceWith(level, stack, pos, ((NECoralPlantBlockAccessor) coralBlock).getDeadBlock().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
        }

        if (block instanceof CoralBlock coralBlock)
        {
            return replaceWith(level, stack, pos, ((NECoralBlockAccessor) coralBlock).getDeadBlock().defaultBlockState());
        }

        if (block instanceof DoublePlantBlock)
        {
            return replaceTallBlockWith(level, stack, pos, state, Blocks.AIR);
        }

        if (block instanceof GrowingPlantBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof MangroveRootsBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof HangingRootsBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof SugarCaneBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof CactusBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof BigDripleafBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof BigDripleafStemBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof BambooStalkBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block == Blocks.BAMBOO_SAPLING)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof GlowLichenBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof VineBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        if (block instanceof BushBlock)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        return false;
    }

    private static boolean handleVanillaBonemeal(Level level, ItemStack stack, BlockPos pos, BonemealableBlock bonemealableBlock, BlockState state)
    {
        return handleVanillaBonemeal(level, stack, pos, bonemealableBlock, state, 0.0d);
    }

    private static boolean handleVanillaBonemeal(Level level, ItemStack stack, BlockPos pos, BonemealableBlock bonemealableBlock, BlockState state, double particleYOffset)
    {
        RandomSource random = level.getRandom();

        if (bonemealableBlock.isValidBonemealTarget(level, pos, state))
        {
            return false;
        }

        if (level instanceof ServerLevel serverLevel)
        {
            bonemealableBlock.performBonemeal(serverLevel, random, pos, state);
        }

        onSuccess(level, stack, pos, particleYOffset);
        return true;
    }

    private static boolean handleNetherWart(Level level, ItemStack stack, BlockPos pos, BlockState state)
    {
        int newAge = state.getValue(BlockStateProperties.AGE_3) + 1;

        if (newAge > BlockStateProperties.MAX_AGE_3)
        {
            return false;
        }

        return replaceWith(level, stack, pos, state.setValue(BlockStateProperties.AGE_3, newAge));
    }

    private static boolean handleAgedBlock(Level level, ItemStack stack, BlockPos pos, BlockState state, IntegerProperty ageProperty)
    {
        int newAge = state.getValue(ageProperty) - 1;

        if (newAge < 0)
        {
            return replaceWith(level, stack, pos, Blocks.AIR);
        }

        return replaceWith(level, stack, pos, state.setValue(ageProperty, newAge));
    }


    private static boolean replaceTallBlockWith(Level level, ItemStack stack, BlockPos pos, BlockState state, Block replaceWith)
    {
        if (state.getValue(DoublePlantBlock.HALF).equals(DoubleBlockHalf.UPPER))
        {
            pos = pos.below();
        }

        return replaceWith(level, stack, pos, replaceWith);
    }

    private static boolean replaceWith(Level level, ItemStack stack, BlockPos pos, Block replaceWith)
    {
        return replaceWith(level, stack, pos, replaceWith, 0);
    }

    private static boolean replaceWith(Level level, ItemStack stack, BlockPos pos, Block replaceWith, double particleYOffset)
    {
        return replaceWith(level, stack, pos, replaceWith.defaultBlockState(), particleYOffset);
    }

    private static boolean replaceWith(Level level, ItemStack stack, BlockPos pos, BlockState replaceWith)
    {
        return replaceWith(level, stack, pos, replaceWith, 0);
    }

    private static boolean replaceWith(Level level, ItemStack stack, BlockPos pos, BlockState replaceWith, double particleYOffset)
    {
        onSuccess(level, stack, pos, particleYOffset);
        setBlock(level, pos, replaceWith);
        return true;
    }

    private static void setBlock(Level level, BlockPos pos, BlockState newState)
    {
        if (level.isClientSide)
        {
            return;
        }

        level.setBlock(pos, newState, 3);
    }

    private static void onSuccess(Level level, ItemStack stack, BlockPos pos, double particleYOffset)
    {
        addSmokeParticles(level, Vec3.atCenterOf(pos).add(0, particleYOffset, 0));
        playUseSound(level, pos);
        stack.shrink(1);
    }

    private static void playUseSound(Level level, BlockPos pos)
    {
        if (level.isClientSide)
        {
            return;
        }

        level.playSound(null, pos, SoundEvents.WITHER_SHOOT, SoundSource.BLOCKS, 0.1f, 1.25f);
    }

    private static void addSmokeParticles(Level level, Vec3 pos)
    {
        if (!level.isClientSide)
        {
            return;
        }

        RandomSource random = level.getRandom();

        for (int i = 0; i < 45; i++)
        {
            double x = pos.x();
            double y = pos.y();
            double z = pos.z();
            double xSpeed = random.nextGaussian() * 0.02;
            double ySpeed = random.nextGaussian() * 0.02;
            double zSpeed = random.nextGaussian() * 0.02;
            level.addParticle(ParticleTypes.SMOKE, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
