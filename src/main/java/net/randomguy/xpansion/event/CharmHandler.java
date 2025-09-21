package net.randomguy.xpansion.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = "xpansion")
public class CharmHandler {

    // ---- ResourceLocations ----
    private static final ResourceLocation CRIT_SPEED_CHARM_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "crit_speed_charm_boost");

    private static final ResourceLocation MOMENTUM_CHARM_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "momentum_charm_boost");

    // ---- Eating Speed Charm ----
    private static final ResourceLocation FAST_EAT_CHARM_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "fast_eat_charm");
    // ---- Damage Nullify Charm ----
    public static final Map<Player, Double> nullifyChanceMap = new WeakHashMap<>();
    public static final Map<Player, Integer> nullifyEffectTicks = new WeakHashMap<>();
    private static final Random random = new Random();

    // ---- Momentum Charm ----
    // No need to track gradual speed anymore

    // ---- Player tick event ----
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (player.level().isClientSide) return;

        var attributes = player.getAttributes();

        // ---- Crit Speed Charm ----
        var attackSpeedInstance = attributes.getInstance(Attributes.ATTACK_SPEED);
        if (attackSpeedInstance != null) {
            boolean hasSpeedCharm = hasCritCharmEquipped(player);

            var modifier = new AttributeModifier(
                    CRIT_SPEED_CHARM_ID,
                    0.5, // +50% attack speed
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );

            if (hasSpeedCharm) {
                if (!attackSpeedInstance.hasModifier(CRIT_SPEED_CHARM_ID)) {
                    attackSpeedInstance.addTransientModifier(modifier);
                }
            } else {
                attackSpeedInstance.removeModifier(CRIT_SPEED_CHARM_ID);
            }
        }

        // ---- Momentum Charm (flat +50% movement speed) ----
        var moveInstance = attributes.getInstance(Attributes.MOVEMENT_SPEED);
        if (moveInstance != null) {
            boolean hasCharm = hasMomentumCharmEquipped(player);

            if (!hasCharm) {
                moveInstance.removeModifier(MOMENTUM_CHARM_ID);
            } else {
                var modifier = new AttributeModifier(
                        MOMENTUM_CHARM_ID,
                        0.5, // 50% increase
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                );

                moveInstance.removeModifier(MOMENTUM_CHARM_ID);
                moveInstance.addTransientModifier(modifier);
            }
        }
    }

    // ---- Damage Nullify Charm ----
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!hasNullifyCharmEquipped(player)) return;

        double chance = nullifyChanceMap.getOrDefault(player, 0.1); // start at 10%
        if (random.nextDouble() < chance) {
            event.setCanceled(true);
            nullifyChanceMap.put(player, 0.1); // reset chance
            nullifyEffectTicks.put(player, 10); // 10 ticks flash
        } else {
            nullifyChanceMap.put(player, Math.min(1.0, chance + 0.1));
        }
    }

    // Damage Reduction Charm: server-side hurt event
    @SubscribeEvent
    public static void onPlayerHurtReduction(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!hasHalfHeartCharmEquipped(player)) return;

        // Only trigger if health <= 50%
        if (player.getHealth() <= player.getMaxHealth() / 2f) {
            event.setAmount((float) (event.getAmount() * 0.5)); // reduce damage by half
        }
    }

    private static boolean hasCritCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.IRON_LOTUS.get())) return true;
        }
        return false;
    }

    private static boolean hasNullifyCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.CHERRY_BOUQUET.get())) return true;
        }
        return false;
    }

    private static boolean hasMomentumCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.QUICK_FEET.get())) return true;
        }
        return false;
    }

    private static boolean hasHalfHeartCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.COPPER_HEART.get())) return true;
        }
        return false;
    }
    public static boolean hasDashReductionCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.SWIFT_FEATHER.get())) return true;
        }
        return false;
    }

}
