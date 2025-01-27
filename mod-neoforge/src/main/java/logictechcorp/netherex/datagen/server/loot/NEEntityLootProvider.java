package logictechcorp.netherex.datagen.server.loot;

import logictechcorp.netherex.entity.animal.NEFlaemothVariant;
import logictechcorp.netherex.entity.animal.NEMogusVariant;
import logictechcorp.netherex.entity.animal.NESalamanderVariant;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.registry.*;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NEEntityLootProvider extends EntityLootSubProvider
{
    public NEEntityLootProvider(HolderLookup.Provider registries)
    {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate()
    {
        add(EntityType.HOGLIN, basicLootingDrop(NetherExItems.HOGLIN_TUSK.get()));

        add(NetherExEntityTypes.SPINOUT.get(), basicLootingDrop(Items.QUARTZ));
        add(NetherExEntityTypes.WISP.get(), LootTable.lootTable());

        HolderLookup.RegistryLookup<NESalamanderVariant> salamanderVarReg = registries.lookupOrThrow(NetherExRegistries.Keys.SALAMANDER_VARIANT);

        add(NetherExEntityTypes.SALAMANDER.get(), NetherExLootTables.SALAMANDER_ORANGE, basicLootingDrop(Items.FIRE_CHARGE));
        add(NetherExEntityTypes.SALAMANDER.get(), NetherExLootTables.SALAMANDER_BLACK, basicLootingDrop(Items.FIRE_CHARGE));
        add(NetherExEntityTypes.SALAMANDER.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(AlternativesEntry.alternatives(
                                NestedLootTable.lootTableReference(NetherExLootTables.SALAMANDER_ORANGE)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                        .subPredicate(NetherExEntitySubPredicates.SALAMANDER.createPredicate(HolderSet.direct(salamanderVarReg.getOrThrow(NetherExSalamanderVariants.ORANGE))))
                                        )),
                                NestedLootTable.lootTableReference(NetherExLootTables.SALAMANDER_BLACK)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity().subPredicate(NetherExEntitySubPredicates.SALAMANDER.createPredicate(HolderSet.direct(salamanderVarReg.getOrThrow(NetherExSalamanderVariants.BLACK))))
                                        ))
                        ))
                )
        );

        HolderLookup.RegistryLookup<NEMogusVariant> mogusVarReg = registries.lookupOrThrow(NetherExRegistries.Keys.MOGUS_VARIANT);

        add(NetherExEntityTypes.MOGUS.get(), NetherExLootTables.MOGUS_BROWN, basicLootingDrop(NetherExBlocks.BROWN_ELDER_MUSHROOM.get()));
        add(NetherExEntityTypes.MOGUS.get(), NetherExLootTables.MOGUS_RED, basicLootingDrop(NetherExBlocks.RED_ELDER_MUSHROOM.get()));
        add(NetherExEntityTypes.MOGUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(AlternativesEntry.alternatives(
                                NestedLootTable.lootTableReference(NetherExLootTables.MOGUS_BROWN)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                        .subPredicate(NetherExEntitySubPredicates.MOGUS.createPredicate(HolderSet.direct(mogusVarReg.getOrThrow(NetherExMogusVariants.BROWN))))
                                        )),
                                NestedLootTable.lootTableReference(NetherExLootTables.MOGUS_RED)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity().subPredicate(NetherExEntitySubPredicates.MOGUS.createPredicate(HolderSet.direct(mogusVarReg.getOrThrow(NetherExMogusVariants.RED))))
                                        ))
                        ))
                )
        );

        HolderLookup.RegistryLookup<NEFlaemothVariant> flaemothVarReg = registries.lookupOrThrow(NetherExRegistries.Keys.FLAEMOTH_VARIANT);

        add(NetherExEntityTypes.FLAEMOTH.get(), NetherExLootTables.FLAEMOTH_CRIMSON, basicLootingDrop(NetherExItems.SHROOMFRUIT.get()));
        add(NetherExEntityTypes.FLAEMOTH.get(), NetherExLootTables.FLAEMOTH_WARPED, basicLootingDrop(NetherExItems.TWISTED_SHROOMFRUIT.get()));
        add(NetherExEntityTypes.FLAEMOTH.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(AlternativesEntry.alternatives(
                                NestedLootTable.lootTableReference(NetherExLootTables.FLAEMOTH_CRIMSON)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                        .subPredicate(NetherExEntitySubPredicates.FLAEMOTH.createPredicate(HolderSet.direct(flaemothVarReg.getOrThrow(NetherExFlaemothVariants.CRIMSON))))
                                        )),
                                NestedLootTable.lootTableReference(NetherExLootTables.FLAEMOTH_WARPED)
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity().subPredicate(NetherExEntitySubPredicates.FLAEMOTH.createPredicate(HolderSet.direct(flaemothVarReg.getOrThrow(NetherExFlaemothVariants.WARPED))))
                                        ))
                        ))
                )
        );
    }

    private LootTable.Builder basicLootingDrop(ItemLike itemDropped)
    {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(itemDropped))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 1.0f)))
                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0f, 1.0f))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                )
        );
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes()
    {
        List<EntityType<?>> entityTypes = NetherExEntityTypes.ENTITY_TYPES.getEntries()
                .stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
        entityTypes.remove(NetherExEntityTypes.ASHEN_ARROW.get());
        entityTypes.add(EntityType.HOGLIN);
        return entityTypes.stream();
    }
}
