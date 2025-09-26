package net.randomguy.xpansion.event;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.ItemCost;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

@Mod.EventBusSubscriber(modid = "xpansion", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onMobDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Zombie ||
                event.getEntity() instanceof Husk ||
                event.getEntity() instanceof Drowned) {

            if (event.getEntity().level().random.nextFloat() < 0.1f) {
                event.getDrops().add(new ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        new ItemStack(ModItems.HUNGER_PIT.get())
                ));
            }
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        // Example: Add trades to Cleric villagers
        if (event.getType() == VillagerProfession.CLERIC) {
            var trades = event.getTrades();

            // Level 1 trade: 5 Emeralds → 1 Fast Eat Charm
            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.HUNGER_PIT.get(), 1),
                    5, 10, 0.05f
            ));

            // Level 2 trade: 1 Diamond → 3 Golden Apples
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 1),
                    new ItemStack(Items.GOLDEN_APPLE, 3),
                    3, 20, 0.1f
            ));
        }
    }
}
