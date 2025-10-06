package net.randomguy.xpansion.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JumpingItem extends Item {
    private int tickCounter = 0;

    public JumpingItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (level.isClientSide() || !(entity instanceof Player player)) return;

        tickCounter++;

        if (tickCounter >= 200) { // 10 seconds = 200 ticks
            tickCounter = 0;

            // Remove from inventory
            player.getInventory().removeItem(stack);

            // Drop into the world
            ItemEntity droppedItem = new ItemEntity(level, player.getX(), player.getY() + 1, player.getZ(), stack.copy());
            droppedItem.setPickUpDelay(40); // 2 seconds delay before pickup
            level.addFreshEntity(droppedItem);
        }
    }
}
