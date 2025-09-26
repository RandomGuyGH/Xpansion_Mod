package net.randomguy.xpansion.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

@Mod.EventBusSubscriber(modid = "xpansion")
public class PotionHandler {

    // Called when the player finishes drinking the potion
    @SubscribeEvent
    public static void onDrinkPotion(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;
        ItemStack stack = event.getItem();

        if (stack.is(ModItems.BOTTLED_VOID.get())) {
            // Apply effects: 10 seconds = 200 ticks
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));

            // Mark that the player consumed the elixir
            if (!player.level().isClientSide) {
                player.getPersistentData().putBoolean("RiskyElixirConsumed", true);
            }
        }
    }

    // Server-side tick event to handle the timer
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.level().isClientSide) return;

        // Check if player is under potion effects
        boolean hasSpeed = player.hasEffect(MobEffects.MOVEMENT_SPEED);
        boolean hasDamage = player.hasEffect(MobEffects.DAMAGE_BOOST);

        // If timer hasn't started yet and effects are gone
        if (!hasSpeed && !hasDamage && !player.getPersistentData().contains("RiskyElixirTimer") &&
                player.getPersistentData().getBoolean("RiskyElixirConsumed")) {

            // Start the 1-tick timer to apply health reduction immediately
            player.getPersistentData().putInt("RiskyElixirTimer", 1);
        }

        // Countdown for health reduction
        if (player.getPersistentData().contains("RiskyElixirTimer")) {
            int timer = player.getPersistentData().getInt("RiskyElixirTimer");
            timer--;
            if (timer <= 0) {
                // Reduce health to half max
                float newHealth = player.getMaxHealth() / 2f;
                player.setHealth(player.getHealth() - newHealth);
                player.getPersistentData().remove("RiskyElixirTimer");
                player.getPersistentData().remove("RiskyElixirConsumed");
            } else {
                player.getPersistentData().putInt("RiskyElixirTimer", timer);
            }
        }
    }

}
