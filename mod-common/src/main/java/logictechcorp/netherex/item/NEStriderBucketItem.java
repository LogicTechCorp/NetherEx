package logictechcorp.netherex.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class NEStriderBucketItem extends MobBucketItem
{
    public NEStriderBucketItem(Properties properties)
    {
        super(EntityType.STRIDER, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties);
    }

    @Override
    public void checkExtraContent(@Nullable Player player, Level level, ItemStack bucketStack, BlockPos pos)
    {
        if (level instanceof ServerLevel)
        {
            spawnStrider((ServerLevel) level, bucketStack, pos);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    protected void spawnStrider(ServerLevel serverLevel, ItemStack bucketStack, BlockPos pos)
    {
        EntityType.STRIDER.spawn(serverLevel, bucketStack, null, pos, MobSpawnType.BUCKET, true, false);
    }
}
