package net.randomguy.xpansion.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

@Mod.EventBusSubscriber(modid = "xpansion", value = Dist.CLIENT)
public class AccoladeHandler {

    // DASH SETTINGS
    private static final int DASH_COOLDOWN_TICKS = 80; // 4 seconds
    private static final double DASH_STRENGTH = 1.0;

    // JUMP BOOST SETTINGS
    private static final int JUMPBOOST_COOLDOWN_TICKS = 60; // 3 seconds
    private static final double JUMPBOOST_STRENGTH = 0.8;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.ClientTickEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        // ---- CHECK SLOT 6 ----
        ItemStack slot6 = player.getInventory().getItem(5);
        if (slot6.isEmpty()) return;

        // =============================
        // SWIFT CLOAK (Dash Accolade)
        // =============================
        if (slot6.is(ModItems.SWIFT_CLOAK.get())) {
            if (!player.getPersistentData().contains("AccoladeDashCooldown")) {
                player.getPersistentData().putInt("AccoladeDashCooldown", 0);
            }

            int cooldown = player.getPersistentData().getInt("AccoladeDashCooldown");

            if (cooldown > 0) {
                player.getPersistentData().putInt("AccoladeDashCooldown", cooldown - 1);
            }

            boolean pressingJump = player.input != null && player.input.jumping;
            boolean dashReady = player.getPersistentData().getBoolean("AccoladeDashReady");

            if (pressingJump && !dashReady && cooldown <= 0) {
                // forward dash
                player.setDeltaMovement(
                        player.getDeltaMovement().add(
                                player.getLookAngle().x * DASH_STRENGTH,
                                0,
                                player.getLookAngle().z * DASH_STRENGTH
                        )
                );

                player.level().levelEvent(2001, player.blockPosition(), 0);

                player.getPersistentData().putBoolean("AccoladeDashReady", true);
                int baseCooldown = DASH_COOLDOWN_TICKS;

                // reduce cooldown by 25% if charm equipped
                if (CharmHandler.hasDashReductionCharmEquipped(player)) {
                    baseCooldown = (int) (baseCooldown * 0.5);
                }

                player.getPersistentData().putInt("AccoladeDashCooldown", baseCooldown);

            }

            if (!pressingJump) {
                player.getPersistentData().putBoolean("AccoladeDashReady", false);
            }
        }

        // =============================
// JUMP ACCOLADE (Jump Boost)
// =============================
        if (slot6.is(ModItems.GALE_WINGS.get())) {
            if (!player.getPersistentData().contains("AccoladeJumpCooldown")) {
                player.getPersistentData().putInt("AccoladeJumpCooldown", 0);
            }

            int cooldown = player.getPersistentData().getInt("AccoladeJumpCooldown");

            if (cooldown > 0) {
                player.getPersistentData().putInt("AccoladeJumpCooldown", cooldown - 1);
            }

            boolean pressingJump = player.input != null && player.input.jumping;
            boolean jumpReady = player.getPersistentData().getBoolean("AccoladeJumpReady");

            if (pressingJump && !jumpReady && cooldown <= 0) {
                // upward boost
                player.setDeltaMovement(
                        player.getDeltaMovement().x,
                        player.getDeltaMovement().y + JUMPBOOST_STRENGTH,
                        player.getDeltaMovement().z
                );

                player.level().levelEvent(2001, player.blockPosition(), 0);

                player.getPersistentData().putBoolean("AccoladeJumpReady", true);
                player.getPersistentData().putInt("AccoladeJumpCooldown", JUMPBOOST_COOLDOWN_TICKS);

            }

            if (!pressingJump) {
                player.getPersistentData().putBoolean("AccoladeJumpReady", false);
            }
        }

    }
}

