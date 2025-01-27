package logictechcorp.netherex.platform;

import com.google.auto.service.AutoService;
import logictechcorp.netherex.NetherExConstants;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@AutoService(NEPotionHelper.class)
public class NEPotionHelperNeoForge implements NEPotionHelper
{
    private static final List<PotionBrew> POTION_BREWS = new ArrayList<>();

    @Override
    public void addMix(Holder<Potion> input, Supplier<Item> reagentSupplier, Holder<Potion> result)
    {
        POTION_BREWS.add(new PotionBrew(input, reagentSupplier, result));
    }

    @EventBusSubscriber(modid = NetherExConstants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onRegisterSpawnPlacements(RegisterBrewingRecipesEvent event)
        {
            POTION_BREWS.forEach((potionBrew) -> event.getBuilder().addMix(potionBrew.input(), potionBrew.reagentSupplier().get(), potionBrew.result()));
        }
    }
}
