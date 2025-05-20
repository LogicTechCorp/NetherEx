package logictechcorp.netherex.mixin;

import logictechcorp.netherex.world.level.levelgen.structure.NEStructureLodestoneMarker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(NetherFortressPieces.CastleEntrance.class)
public abstract class NEFortressCastleEntranceMixin extends StructurePiece implements NEStructureLodestoneMarker
{
    @Unique
    private BlockPos netherEx$lodestonePos = null;

    public NEFortressCastleEntranceMixin(StructurePieceType type, CompoundTag compoundTag)
    {
        super(type, compoundTag);
        netherEx$loadFromSaveData(compoundTag);
    }

    @Inject(
            method = "postProcess",
            at = @At("TAIL")
    )
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos, CallbackInfo callback)
    {
        placeBlock(level, Blocks.LODESTONE.defaultBlockState(), 6, 4, 6, box);
        netherEx$lodestonePos = getWorldPos(6, 4, 6);
    }

    protected void addAdditionalSaveData(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag compoundTag)
    {
        if (netherEx$lodestonePos != null)
        {
            compoundTag.put("netherEx$lodestonePos", NbtUtils.writeBlockPos(netherEx$lodestonePos));
        }
    }

    @Unique
    private void netherEx$loadFromSaveData(CompoundTag compoundTag)
    {
        if (compoundTag.contains("netherEx$lodestonePos"))
        {
            Optional<BlockPos> pos = NbtUtils.readBlockPos(compoundTag, "netherEx$lodestonePos");
            pos.ifPresent(blockPos -> netherEx$lodestonePos = blockPos);
        }
    }

    @Override
    public BlockPos getStructureLodestonePos()
    {
        return netherEx$lodestonePos;
    }
}
