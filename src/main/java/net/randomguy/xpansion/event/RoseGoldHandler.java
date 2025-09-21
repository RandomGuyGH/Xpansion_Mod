package net.randomguy.xpansion.event;

import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = "xpansion")
public class RoseGoldHandler {

    private static final int DELAY_TICKS = 200; // 10 seconds (20 ticks * 10)
    private static final Map<ChestBlockEntity, Integer> chestTimers = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if (event.getServer() == null) return;

        chestTimers.forEach((chest, timeLeft) -> {
            if (chest.isRemoved() || !containsRoseGold(chest)) {
                chestTimers.remove(chest);
                return;
            }

            int newTime = timeLeft - 1;
            if (newTime <= 0) {
                convertChest(chest);
                chestTimers.remove(chest);
            } else {
                chestTimers.put(chest, newTime);
            }
        });
    }

    @SubscribeEvent
    public static void onContainerOpened(PlayerContainerEvent.Open event) {
        AbstractContainerMenu menu = event.getContainer();
        if (menu instanceof ChestMenu chestMenu) {
            if (chestMenu.getContainer() instanceof ChestBlockEntity chest) {
                if (containsRoseGold(chest)) {
                    chestTimers.putIfAbsent(chest, DELAY_TICKS);
                } else {
                    chestTimers.remove(chest);
                }
            }
        }
    }

    private static boolean containsRoseGold(ChestBlockEntity chest) {
        for (int i = 0; i < chest.getContainerSize(); i++) {
            ItemStack stack = chest.getItem(i);
            if (!stack.isEmpty() && stack.is(ModItems.ROSE_GOLD.get())) {
                return true;
            }
        }
        return false;
    }

    private static void convertChest(ChestBlockEntity chest) {
        for (int i = 0; i < chest.getContainerSize(); i++) {
            ItemStack stack = chest.getItem(i);
            if (!stack.isEmpty() && !stack.is(ModItems.ROSE_GOLD.get())) {
                int count = stack.getCount();
                chest.setItem(i, new ItemStack(ModItems.ROSE_GOLD.get(), count));
            }
        }
        chest.setChanged();
    }
}
