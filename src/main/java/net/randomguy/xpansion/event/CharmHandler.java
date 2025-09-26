package net.randomguy.xpansion.event;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.randomguy.xpansion.item.ModItems;

import java.util.List;
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

        // ---- Silkweaver Charm (repairs tools with Silk) ----
        if (hasSilkweaverCharmEquipped(player)) {
            ItemStack held = player.getMainHandItem();
            if (!held.isEmpty() && held.isDamageableItem() && held.getDamageValue() > 0) {
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack slot = player.getInventory().getItem(i);
                    if (slot.is(ModItems.LIGHT_SILK.get())) {
                        slot.shrink(1); // consume 1 silk
                        held.setDamageValue(Math.max(0, held.getDamageValue() - 1)); // repair 1 durability
                        break; // only once per tick
                    }
                }
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

    @SubscribeEvent
    public static void onPlayerDealDamage(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            boolean fragile = hasFragilePowerCharmEquipped(player);
            boolean enduring = hasEnduringPowerCharmEquipped(player);
            if (hasBerserkerCharmEquipped(player)) {
                float maxHealth = player.getMaxHealth();
                float currentHealth = player.getHealth();

                // 25% HP or lower → damage boost
                if (currentHealth <= (maxHealth * 0.25f)) {
                    float boostedDamage = event.getAmount() * 2f; // +50%
                    event.setAmount(boostedDamage);
                }
            }
            if (fragile || enduring) {
                float boostedDamage = event.getAmount() * 1.5f; // +50% damage
                event.setAmount(boostedDamage);
            }
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

    @SubscribeEvent
    public static void onItemUseStart(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // Check if player has the Fast-Eat Charm equipped
        if (hasFastEatCharmEquipped(player)) {
            // Make eating 50% faster
            int duration = event.getDuration();
            event.setDuration(Math.max(1, duration / 2));
        }
    }
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!player.level().isClientSide) {
                for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
                    ItemStack stack = player.getInventory().getItem(slotIndex);
                    if (stack.is(ModItems.FRAGILE_STRENGTH.get())) {
                        player.getInventory().removeItem(slotIndex, 1); // remove the charm
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player == null || player.level().isClientSide) return;

        if (!hasMinerCharmEquipped(player)) return;

        // Check if holding a pickaxe
        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof PickaxeItem)) return;

        BlockState state = event.getState();
        Block block = state.getBlock();
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        // Drop original loot
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), player, held);

        for (ItemStack drop : drops) {
            // Extra drop like Fortune I
            ItemStack extra = drop.copy();
            if (!extra.isEmpty()) {
                extra.setCount(1); // only one extra per drop
                Block.popResource(level, pos, extra);
            }
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
    private static boolean hasFastEatCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.HUNGER_PIT.get())) return true;
        }
        return false;
    }
    private static boolean hasBerserkerCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) { // slots for charms
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.RAGING_SOUL.get())) return true;
        }
        return false;
    }
    private static boolean hasFragilePowerCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.FRAGILE_STRENGTH.get())) return true;
        }
        return false;
    }

    private static boolean hasEnduringPowerCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.ENDURING_STRENGTH.get())) return true;
        }
        return false;
    }
    private static boolean hasMinerCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.GOLDEN_CUP.get())) return true;
        }
        return false;
    }
    private static boolean hasSilkweaverCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.MENDING_SILK.get())) return true;
        }
        return false;
    }

}
