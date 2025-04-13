package logictechcorp.netherex.datagen.server.advancements;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.registry.NetherExEntityTypes;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;

import java.util.function.Consumer;

public class NEVanillaNetherAdvancements implements AdvancementSubProvider
{
    public NEVanillaNetherAdvancements()
    {

    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer)
    {
        HolderGetter<EntityType<?>> entities = registries.lookupOrThrow(Registries.ENTITY_TYPE);

        AdvancementHolder netherRoot = AdvancementSubProvider.createPlaceholder("nether/root");
        AdvancementHolder rideStrider = AdvancementSubProvider.createPlaceholder("nether/ride_strider");

        Advancement.Builder.advancement().parent(rideStrider)
                .display(
                        NetherExItems.STRIDER_BUCKET.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".vanilla_nether.bucket_strider.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".vanilla_nether.bucket_strider.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_strider_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(NetherExItems.STRIDER_BUCKET.get()))
                .save(consumer, NetherExConstants.MOD_ID + ":vanilla_nether/bucket_strider");

        Advancement.Builder.advancement().parent(netherRoot)
                .display(
                        NetherExItems.FLAEMOTH_SPAWN_EGG.get(),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".vanilla_nether.killed_flaemoth.title"),
                        Component.translatable("advancements." + NetherExConstants.MOD_ID + ".vanilla_nether.killed_flaemoth.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_flaemoth", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entities, NetherExEntityTypes.FLAEMOTH.get())))
                .save(consumer, NetherExConstants.MOD_ID + ":vanilla_nether/killed_flaemoth");
    }
}
