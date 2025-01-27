package logictechcorp.netherex.registry;

import logictechcorp.netherex.NetherExConstants;
import logictechcorp.netherex.item.*;
import logictechcorp.netherex.platform.registration.RegistrationProvider;
import logictechcorp.netherex.platform.registration.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Function;
import java.util.function.Supplier;

public class NetherExItems
{
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, NetherExConstants.MOD_ID);

    public static final RegistryObject<Item, Item> GLOOMY_NETHER_BRICK = registerItem("gloomy_nether_brick");
    public static final RegistryObject<Item, Item> FIERY_NETHER_BRICK = registerItem("fiery_nether_brick");
    public static final RegistryObject<Item, Item> LIVELY_NETHER_BRICK = registerItem("lively_nether_brick");
    public static final RegistryObject<Item, Item> NETHERITE_NUGGET = registerItem("netherite_nugget");
    public static final RegistryObject<Item, NENetheriteHorseArmorItem> NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor", NENetheriteHorseArmorItem::new, NEItemProperties.NETHERITE_HORSE_ARMOR);
    public static final RegistryObject<Item, Item> WITHER_BONE = registerItem("wither_bone");
    public static final RegistryObject<Item, NEWitherBoneMealItem> WITHER_BONE_MEAL = registerItem("wither_bone_meal", NEWitherBoneMealItem::new, NEItemProperties.WITHER_BONE_MEAL);
    public static final RegistryObject<Item, Item> HOGLIN_TUSK = registerItem("hoglin_tusk");
    public static final RegistryObject<Item, NERibsItem> RIBS = registerItem("ribs", NERibsItem::new, NEItemProperties.RIBS);
    public static final RegistryObject<Item, NERibsItem> COOKED_RIBS = registerItem("cooked_ribs", NERibsItem::new, NEItemProperties.COOKED_RIBS);
    public static final RegistryObject<Item, NEShroomfruitItem> SHROOMFRUIT = registerItem("shroomfruit", (properties) -> new NEShroomfruitItem(NetherExBlocks.SHROOMSTEM.get(), () -> new MobEffectInstance(MobEffects.POISON, 100), properties), NEItemProperties.SHROOMFRUIT);
    public static final RegistryObject<Item, NEShroomfruitItem> TWISTED_SHROOMFRUIT = registerItem("twisted_shroomfruit", (properties) -> new NEShroomfruitItem(NetherExBlocks.TWISTED_SHROOMSTEM.get(), () -> new MobEffectInstance(MobEffects.POISON, 120), properties), NEItemProperties.TWISTED_SHROOMFRUIT);
    public static final RegistryObject<Item, MobBucketItem> STRIDER_BUCKET = registerItem("strider_bucket", (properties) -> new MobBucketItem(EntityType.STRIDER, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties), NEItemProperties.STRIDER_BUCKET);
    public static final RegistryObject<Item, Item> ASH = registerItem("ash");
    public static final RegistryObject<Item, NEAshenArrowItem> ASHEN_ARROW = registerItem("ashen_arrow", NEAshenArrowItem::new, NEItemProperties.ASHEN_ARROW);

    public static final RegistryObject<Item, SpawnEggItem> SPINOUT_SPAWN_EGG = registerItem("spinout_spawn_egg", (properties) -> new SpawnEggItem(NetherExEntityTypes.SPINOUT.get(), properties));
    public static final RegistryObject<Item, SpawnEggItem> WISP_SPAWN_EGG = registerItem("wisp_spawn_egg", (properties) -> new SpawnEggItem(NetherExEntityTypes.WISP.get(), properties));
    public static final RegistryObject<Item, SpawnEggItem> SALAMANDER_SPAWN_EGG = registerItem("salamander_spawn_egg", (properties -> new SpawnEggItem(NetherExEntityTypes.SALAMANDER.get(), properties)));
    public static final RegistryObject<Item, SpawnEggItem> MOGUS_SPAWN_EGG = registerItem("mogus_spawn_egg", (properties) -> new SpawnEggItem(NetherExEntityTypes.MOGUS.get(), properties));
    public static final RegistryObject<Item, SpawnEggItem> FLAEMOTH_SPAWN_EGG = registerItem("flaemoth_spawn_egg", (properties) -> new SpawnEggItem(NetherExEntityTypes.FLAEMOTH.get(), properties));

    public static void initialize()
    {
    }

    public static <I extends Item> RegistryObject<Item, I> registerItem(String itemName, Function<Item.Properties, I> newItemFunc, Item.Properties itemProperties)
    {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(NetherExConstants.MOD_ID, itemName);
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, resourceLocation);
        itemProperties.setId(resourceKey);

        Supplier<I> itemSupplier = () -> newItemFunc.apply(itemProperties);
        return ITEMS.register(itemName, itemSupplier);
    }

    public static <I extends Item> RegistryObject<Item, I> registerItem(String itemName, Function<Item.Properties, I> newItemFunc)
    {
        return registerItem(itemName, newItemFunc, new Item.Properties());
    }

    public static RegistryObject<Item, Item> registerItem(String itemName, Item.Properties itemProperties)
    {
        return registerItem(itemName, Item::new, itemProperties);
    }

    public static RegistryObject<Item, Item> registerItem(String itemName)
    {
        return registerItem(itemName, Item::new, new Item.Properties());
    }

    public static class NEItemProperties
    {
        public static final Item.Properties NETHERITE_HORSE_ARMOR = new Item.Properties().fireResistant();
        public static final Item.Properties WITHER_BONE_MEAL = new Item.Properties();
        public static final Item.Properties RIBS = new Item.Properties().food(NEFoods.RIBS);
        public static final Item.Properties COOKED_RIBS = new Item.Properties().food(NEFoods.COOKED_RIBS);
        public static final Item.Properties SHROOMFRUIT = new Item.Properties().food(NEFoods.SHROOMFRUIT);
        public static final Item.Properties TWISTED_SHROOMFRUIT = new Item.Properties().food(NEFoods.TWISTED_SHROOMFRUIT);
        public static final Item.Properties STRIDER_BUCKET = new Item.Properties().stacksTo(1);
        public static final Item.Properties ASHEN_ARROW = new Item.Properties();
    }

    public static class NEFoods
    {
        public static final FoodProperties RIBS = new FoodProperties.Builder().nutrition(6).saturationModifier(0.3f).build();
        public static final FoodProperties COOKED_RIBS = new FoodProperties.Builder().nutrition(11).saturationModifier(0.5f).build();
        public static final FoodProperties SHROOMFRUIT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();
        public static final FoodProperties TWISTED_SHROOMFRUIT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build();
    }
}
