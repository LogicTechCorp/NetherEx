package logictechcorp.netherex.mixin;

import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Strider.class)
public abstract class NEStriderMixin extends Animal implements ItemSteerable, Bucketable
{
    protected NEStriderMixin(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
    }

    @Inject(
            method = "finalizeSpawn",
            at = @At("HEAD"),
            cancellable = true
    )
    private void netherex$FinalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyInstance, EntitySpawnReason spawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> callbackInfoReturnable)
    {
        if (spawnReason == EntitySpawnReason.BUCKET)
        {
            callbackInfoReturnable.setReturnValue(spawnGroupData);
        }
    }

    @Inject(
            method = "mobInteract",
            at = @At("HEAD"),
            cancellable = true)
    private void netherex$MobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> callbackInfoReturnable)
    {
        if (isBaby())
        {
            callbackInfoReturnable.cancel();
        }

        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() == Items.LAVA_BUCKET && isAlive())
        {
            playSound(getPickupSound(), 1.0F, 1.0F);

            ItemStack bucketStack = getBucketItemStack();
            saveToBucketTag(bucketStack);
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, bucketStack, false));

            if (!level().isClientSide())
            {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucketStack);
            }

            discard();
            callbackInfoReturnable.setReturnValue(InteractionResult.SUCCESS_SERVER);
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output)
    {
        super.addAdditionalSaveData(output);
        output.putBoolean("FromBucket", fromBucket());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input)
    {
        super.readAdditionalSaveData(input);
        setFromBucket(input.getBooleanOr("FromBucket", false));
    }

    @Override
    public void saveToBucketTag(ItemStack stack)
    {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag)
    {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
    }

    @Override
    public boolean removeWhenFarAway(double distance)
    {
        return !hasCustomName() && !fromBucket();
    }

    @Override
    public boolean requiresCustomPersistence()
    {
        return super.requiresCustomPersistence() || fromBucket();
    }

    @Override
    public boolean fromBucket()
    {
        return get(DataComponents.CUSTOM_DATA).copyTag().getBooleanOr("FromBucket", false);
    }

    @Override
    public ItemStack getBucketItemStack()
    {
        return new ItemStack(NetherExItems.STRIDER_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound()
    {
        return SoundEvents.BUCKET_FILL_LAVA;
    }

    @Override
    public void setFromBucket(boolean fromBucket)
    {
        get(DataComponents.CUSTOM_DATA).update(compoundTag -> compoundTag.putBoolean("FromBucket", fromBucket));
    }
}