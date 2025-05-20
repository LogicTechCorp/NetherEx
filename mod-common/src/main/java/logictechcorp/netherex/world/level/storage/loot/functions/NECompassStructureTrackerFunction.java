package logictechcorp.netherex.world.level.storage.loot.functions;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import logictechcorp.netherex.item.component.NEStructureTracker;
import logictechcorp.netherex.registry.NetherExDataComponents;
import logictechcorp.netherex.registry.NetherExLootFunctions;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class NECompassStructureTrackerFunction extends LootItemConditionalFunction
{
    public static final MapCodec<NECompassStructureTrackerFunction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            NECompassStructureTrackerFunction.commonFields(instance).and(instance
                            .group(
                                    Structure.CODEC.fieldOf("structure")
                                            .forGetter(function -> function.structure),
                                    Codec.INT.optionalFieldOf("search_radius", 50)
                                            .forGetter(explorationMapFunction -> explorationMapFunction.searchRadius),
                                    Codec.BOOL.optionalFieldOf("skip_existing_chunks", true)
                                            .forGetter(explorationMapFunction -> explorationMapFunction.skipKnownStructures)
                            )
                    )
                    .apply(instance, NECompassStructureTrackerFunction::new)
    );

    private final Holder<Structure> structure;
    private final int searchRadius;
    private final boolean skipKnownStructures;

    public NECompassStructureTrackerFunction(List<LootItemCondition> conditions, Holder<Structure> inStructure, int inSearchRadius, boolean inSkipKnownStructures)
    {
        super(conditions);
        structure = inStructure;
        searchRadius = inSearchRadius;
        skipKnownStructures = inSkipKnownStructures;
    }

    public static NECompassStructureTrackerFunction.Builder makeCompassGlobalPosTracker()
    {
        return new NECompassStructureTrackerFunction.Builder();
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context)
    {
        if (!stack.is(Items.COMPASS))
        {
            return stack;
        }

        Vec3 vec3 = context.getOptionalParameter(LootContextParams.ORIGIN);

        if (vec3 != null)
        {
            ServerLevel level = context.getLevel();
            Registry<Structure> structures = level.registryAccess().lookupOrThrow(Registries.STRUCTURE);
            Optional<ResourceKey<Structure>> structureResourceKey = structure.unwrapKey();

            if (structureResourceKey.isPresent())
            {
                Optional<HolderSet<Structure>> mappedStructure = structureResourceKey.flatMap(structures::get).map(HolderSet::direct);

                if (mappedStructure.isPresent())
                {
                    Pair<BlockPos, Holder<Structure>> foundStructure = level
                            .getChunkSource()
                            .getGenerator()
                            .findNearestMapStructure(level, mappedStructure.get(), BlockPos.containing(vec3), searchRadius, skipKnownStructures);

                    if (foundStructure != null)
                    {
                        BlockPos structurePos = foundStructure.getFirst().atY(64);
                        ItemStack compassStack = new ItemStack(Items.COMPASS);
                        compassStack.set(NetherExDataComponents.STRUCTURE_TRACKER.get(), new NEStructureTracker(structureResourceKey.get(), GlobalPos.of(level.dimension(), structurePos)));
                        return compassStack;
                    }
                }
            }
        }

        return stack;
    }

    @Override
    public Set<ContextKey<?>> getReferencedContextParams()
    {
        return Set.of(LootContextParams.ORIGIN);
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType()
    {
        return NetherExLootFunctions.COMPASS_STRUCTURE_TRACKER_FUNCTION.get();
    }

    public static class Builder extends LootItemConditionalFunction.Builder<NECompassStructureTrackerFunction.Builder>
    {
        private Holder<Structure> structure;
        private int searchRadius;
        private boolean skipKnownStructures;

        public Builder structure(Holder<Structure> inStructure)
        {
            structure = inStructure;
            return this;
        }

        public Builder searchRadius(int inSearchRadius)
        {
            searchRadius = inSearchRadius;
            return this;
        }

        public Builder skipKnownStructures(boolean inSkipKnownStructures)
        {
            skipKnownStructures = inSkipKnownStructures;
            return this;
        }

        @Override
        public LootItemFunction build()
        {
            return new NECompassStructureTrackerFunction(getConditions(), structure, searchRadius, skipKnownStructures);
        }

        @Override
        protected NECompassStructureTrackerFunction.Builder getThis()
        {
            return this;
        }
    }
}
