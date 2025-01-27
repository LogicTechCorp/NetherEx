package logictechcorp.netherex.mixin;

import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Strider.class)
public abstract class NEStriderMixin extends Animal implements ItemSteerable, Saddleable, Bucketable
{
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Strider.class, EntityDataSerializers.BOOLEAN);

    @Shadow
    @Final
    private ItemBasedSteering steering;

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

    @Inject(
            method = "defineSynchedData",
            at = @At("RETURN")
    )
    private void netherex$defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo callbackInfo)
    {
        builder.define(FROM_BUCKET, false);
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("RETURN")
    )
    private void netherex$addAdditionalSaveData(CompoundTag tag, CallbackInfo callbackInfo)
    {
        tag.putBoolean("FromBucket", fromBucket());
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("RETURN")
    )
    private void netherex$readAdditionalSaveData(CompoundTag tag, CallbackInfo callbackInfo)
    {
        setFromBucket(tag.getBoolean("FromBucket"));
    }

    @Override
    public void saveToBucketTag(ItemStack stack)
    {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (compoundTag) -> steering.addAdditionalSaveData(compoundTag));
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag)
    {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
        steering.readAdditionalSaveData(tag);
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
        return entityData.get(FROM_BUCKET);
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
        entityData.set(FROM_BUCKET, fromBucket);
    }
}