package net.randomguy.xpansion.event;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.ItemCost;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

import static net.minecraft.world.item.Items.*;

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
        if (event.getEntity() instanceof Blaze ) {

            if (event.getEntity().level().random.nextFloat() < 0.1f) {
                event.getDrops().add(new ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX(),
                        event.getEntity().getY(),
                        event.getEntity().getZ(),
                        new ItemStack(ModItems.BLAZEFLINT.get())
                ));
            }
        }
    }

    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
        // Clear existing trades
        event.getGenericTrades().clear();
        event.getRareTrades().clear();

        // Add custom generic trade
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 15),
                new ItemStack(ModItems.QUICK_FEET.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 22),
                new ItemStack(ModItems.RING_MAGNET.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 10),
                new ItemStack(ModItems.TOTEM_OF_SCURRY.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 8),
                new ItemStack(BLACK_DYE, 5),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                new ItemStack(ModItems.SWIFT_FEATHER.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                new ItemStack(ModItems.LIGHT_SILK.get(), 2),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                new ItemStack(COPPER_INGOT, 10),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(ModItems.SPEND_O_COIN.get(), 1),
                new ItemStack(Items.EMERALD, 5),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 13),
                new ItemStack(ModItems.RAGING_SOUL.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 15),
                new ItemStack(ModItems.FRAGILE_STRENGTH.get(), 1),
                5, 10, 0.05f
        ));
        event.getGenericTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                new ItemStack(ModItems.CHERRY_BLOSSOM.get(), 5),
                5, 10, 0.05f
        ));

        // Add custom rare trade
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 40),
                new ItemStack(ModItems.ANCIENT_TOTEM.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 35),
                new ItemStack(ModItems.HUNTER_MASK.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 30),
                new ItemStack(ModItems.THIRD_EYE_BAND.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                new ItemStack(WIND_CHARGE, 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 25),
                new ItemStack(ModItems.CORAL_CROWN.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 8),
                new ItemStack(ModItems.BOTTLED_VOID.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 20),
                new ItemStack(ModItems.TOTEM_OF_LEECHING.get(), 1),
                5, 10, 0.05f
        ));
        event.getRareTrades().add((trader, rand) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 64),
                new ItemStack(ModItems.STRANGE_CRYSTAL.get(), 1),
                5, 10, 0.05f
        ));
    }
}
