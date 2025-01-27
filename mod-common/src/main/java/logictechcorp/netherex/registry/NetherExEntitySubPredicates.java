package logictechcorp.netherex.registry;

import com.mojang.serialization.MapCodec;
import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.advancement.criterion.NEEntityHolderVariantPredicateType;
import logictechcorp.netherex.entity.animal.*;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Optional;

public class NetherExEntitySubPredicates
{
    public static final RegistrationProvider<MapCodec<? extends EntitySubPredicate>> ENTITY_SUB_PREDICATES = RegistrationProvider.get(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, NetherExConstants.MOD_ID);

    public static final NEEntityHolderVariantPredicateType<NESalamanderVariant> SALAMANDER = register(
            "salamander",
            NEEntityHolderVariantPredicateType.create(NetherExRegistries.Keys.SALAMANDER_VARIANT, entity ->
            {
                Optional<Holder<NESalamanderVariant>> optionalHolder = Optional.empty();

                if (entity instanceof NESalamander salamander)
                {
                    optionalHolder = Optional.of(salamander.getVariant());
                }

                return optionalHolder;
            })
    );
    public static final NEEntityHolderVariantPredicateType<NEMogusVariant> MOGUS = register(
            "mogus",
            NEEntityHolderVariantPredicateType.create(NetherExRegistries.Keys.MOGUS_VARIANT, entity ->
            {
                Optional<Holder<NEMogusVariant>> optionalHolder = Optional.empty();

                if (entity instanceof NEMogus mogus)
                {
                    optionalHolder = Optional.of(mogus.getVariant());
                }

                return optionalHolder;
            })
    );
    public static final NEEntityHolderVariantPredicateType<NEFlaemothVariant> FLAEMOTH = register(
            "flaemoth",
            NEEntityHolderVariantPredicateType.create(NetherExRegistries.Keys.FLAEMOTH_VARIANT, entity ->
            {
                Optional<Holder<NEFlaemothVariant>> optionalHolder = Optional.empty();

                if (entity instanceof NEFlaemoth flaemoth)
                {
                    optionalHolder = Optional.of(flaemoth.getVariant());
                }

                return optionalHolder;
            })
    );

    public static void initialize()
    {

    }

    private static <V> NEEntityHolderVariantPredicateType<V> register(String name, NEEntityHolderVariantPredicateType<V> predicateType)
    {
        ENTITY_SUB_PREDICATES.register(name, predicateType::codec);
        return predicateType;
    }
}
