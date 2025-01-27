package logictechcorp.netherex.advancement.criterion;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class NEEntityHolderVariantPredicateType<V>
{
    private final MapCodec<VariantPredicate> codec;
    private final Function<Entity, Optional<Holder<V>>> variantGetter;

    public static <V> NEEntityHolderVariantPredicateType<V> create(ResourceKey<? extends Registry<V>> registryKey, Function<Entity, Optional<Holder<V>>> variantGetter)
    {
        return new NEEntityHolderVariantPredicateType<>(registryKey, variantGetter);
    }

    public NEEntityHolderVariantPredicateType(ResourceKey<? extends Registry<V>> registryKey, Function<Entity, Optional<Holder<V>>> inVariantGetter)
    {
        variantGetter = inVariantGetter;
        codec = RecordCodecBuilder.mapCodec((instance) -> instance
                .group(RegistryCodecs.homogeneousList(registryKey)
                        .fieldOf("variant")
                        .forGetter((predicate) -> predicate.variants)
                )
                .apply(instance, VariantPredicate::new)
        );
    }

    public EntitySubPredicate createPredicate(HolderSet<V> variants)
    {
        return new VariantPredicate(variants);
    }

    public MapCodec<? extends EntitySubPredicate> codec()
    {
        return codec;
    }

    public class VariantPredicate implements EntitySubPredicate
    {
        final HolderSet<V> variants;

        VariantPredicate(HolderSet<V> variants)
        {
            this.variants = variants;
        }

        @Override
        public MapCodec<VariantPredicate> codec()
        {
            return codec;
        }

        @Override
        public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 position)
        {
            return variants != null && variantGetter.apply(entity).filter(variants::contains).isPresent();
        }
    }
}
