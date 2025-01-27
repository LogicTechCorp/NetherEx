package logictechcorp.netherex.datagen.server.loot;

import logictechcorp.netherex.block.NEWarpedWartBlock;
import logictechcorp.netherex.platform.registration.RegistryObject;
import logictechcorp.netherex.registry.NetherExBlocks;
import logictechcorp.netherex.registry.NetherExItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

public class NEBlockLootProvider extends BlockLootSubProvider
{
    public NEBlockLootProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(NetherExBlocks.BROWN_ELDER_MUSHROOM_BLOCK.get(), (block) -> createMushroomBlockDrop(block, NetherExBlocks.BROWN_ELDER_MUSHROOM.get()));
        add(NetherExBlocks.RED_ELDER_MUSHROOM_BLOCK.get(), (block) -> createMushroomBlockDrop(block, NetherExBlocks.RED_ELDER_MUSHROOM.get()));
        dropWhenSilkTouch(NetherExBlocks.ELDER_MUSHROOM_STEM.get());

        add(NetherExBlocks.WARPED_WART.get(), (block) ->
        {
            return LootTable.lootTable().withPool(applyExplosionDecay(block, LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(NetherExBlocks.WARPED_WART.get().asItem())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f))
                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                                    .hasProperty(NEWarpedWartBlock.AGE, 3)
                                            )
                                    )
                            )
                            .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE))
                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                                    .hasProperty(NEWarpedWartBlock.AGE, 3)
                                            )
                                    )
                            )
                    )
            ));
        });

        dropOther(NetherExBlocks.SHROOMSTEM.get(), NetherExItems.SHROOMFRUIT.get());
        dropOther(NetherExBlocks.TWISTED_SHROOMSTEM.get(), NetherExItems.TWISTED_SHROOMFRUIT.get());

        dropOther(NetherExBlocks.GLOOMY_NETHERRACK_PATH.get(), NetherExBlocks.GLOOMY_NETHERRACK.get());
        dropOther(NetherExBlocks.FIERY_NETHERRACK_PATH.get(), NetherExBlocks.GLOOMY_NETHERRACK.get());
        dropOther(NetherExBlocks.LIVELY_NETHERRACK_PATH.get(), NetherExBlocks.GLOOMY_NETHERRACK.get());
        dropOther(NetherExBlocks.CRIMSON_NYLIUM_PATH.get(), Blocks.CRIMSON_NYLIUM);
        dropOther(NetherExBlocks.WARPED_NYLIUM_PATH.get(), Blocks.WARPED_NYLIUM);

        dropWhenSilkTouch(NetherExBlocks.BOOMSTONE.get());
        add(NetherExBlocks.ASH_BLOCK.get(), (block) -> createSingleItemTableWithSilkTouch(block, NetherExItems.ASH.get(), ConstantValue.exactly(4.0f)));

        List<Block> blocks = StreamSupport.stream(getKnownBlocks().spliterator(), false).toList();
        blocks.forEach(this::dropSelf);
    }

    @Override
    protected void add(Block block, LootTable.Builder builder)
    {
        if (!map.containsKey(block.getLootTable()))
        {
            super.add(block, builder);
        }
    }

    @Override
    protected void dropSelf(Block block)
    {
        Item item = BuiltInRegistries.ITEM.getValue(BuiltInRegistries.BLOCK.getKey(block));

        if (item != Items.AIR)
        {
            dropOther(block, item);
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return NetherExBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).map(block -> (Block) block)::iterator;
    }
}
