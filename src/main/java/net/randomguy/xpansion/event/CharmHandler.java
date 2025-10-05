package net.randomguy.xpansion.event;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
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
    private static final ResourceLocation RISKY_POWER_CHARM_SPEED_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "risky_power_speed_boost");
    private static final ResourceLocation RISKY_POWER_CHARM_DAMAGE_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "risky_power_damage_boost");
    private static final ResourceLocation RAGE_SPEED_CHARM_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "rage_speed_charm");
    private static final ResourceLocation SPRINT_INVIS_CHARM_ID =
            ResourceLocation.fromNamespaceAndPath("xpansion", "sprint_invis_charm");


    // ---- Damage Nullify Charm ----
    public static final Map<Player, Double> nullifyChanceMap = new WeakHashMap<>();
    public static final Map<Player, Integer> nullifyEffectTicks = new WeakHashMap<>();
    private static final Random random = new Random();
    private static final Map<Player, Integer> rageSpeedTicks = new WeakHashMap<>();
    private static final Map<Player, Integer> blazeflintHitCount = new WeakHashMap<>();

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
                        slot.shrink(3); // consume 1 silk
                        held.setDamageValue(Math.max(0, held.getDamageValue() - 1)); // repair 1 durability
                        break; // only once per tick
                    }
                }
            }
        }
        // ---- Risky Power Charm (+75% speed, +75% attack damage, double damage taken) ----
        boolean hasRiskyCharm = hasRiskyPowerCharmEquipped(player);

        var riskySpeedInstance = attributes.getInstance(Attributes.MOVEMENT_SPEED);
        if (riskySpeedInstance != null) {
            riskySpeedInstance.removeModifier(RISKY_POWER_CHARM_SPEED_ID);
            if (hasRiskyCharm) {
                var modifier = new AttributeModifier(
                        RISKY_POWER_CHARM_SPEED_ID,
                        0.75,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                );
                riskySpeedInstance.addTransientModifier(modifier);
            }
        }

        var riskyDamageInstance = attributes.getInstance(Attributes.ATTACK_DAMAGE);
        if (riskyDamageInstance != null) {
            riskyDamageInstance.removeModifier(RISKY_POWER_CHARM_DAMAGE_ID);
            if (hasRiskyCharm) {
                var modifier = new AttributeModifier(
                        RISKY_POWER_CHARM_DAMAGE_ID,
                        0.75,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                );
                riskyDamageInstance.addTransientModifier(modifier);
            }
        }
        if (hasOceanCharmEquipped(player)) {
            if (player.isInWaterOrBubble()) {
                // Water breathing
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, true, false, false));

                // Speed boost while underwater
                var moveInstances = attributes.getInstance(Attributes.MOVEMENT_SPEED);
                if (moveInstances != null) {
                    var modifier = new AttributeModifier(
                            ResourceLocation.fromNamespaceAndPath("xpansion", "ocean_charm_speed"),
                            0.5, // +30% speed underwater
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    );

                    if (!moveInstances.hasModifier(modifier.id())) {
                        moveInstances.addTransientModifier(modifier);
                    }
                }
            } else {
                // Remove modifier when not underwater
                var moveInstances = attributes.getInstance(Attributes.MOVEMENT_SPEED);
                if (moveInstances != null) {
                    moveInstances.removeModifier(ResourceLocation.fromNamespaceAndPath("xpansion", "ocean_charm_speed"));
                }
            }
        } else {
            // If charm not equipped, ensure modifier is cleared
            var moveInstances = attributes.getInstance(Attributes.MOVEMENT_SPEED);
            if (moveInstances != null) {
                moveInstances.removeModifier(ResourceLocation.fromNamespaceAndPath("xpansion", "ocean_charm_speed"));
            }
        }
        // Fire Amber Charm //
        if (hasFireAmberEquipped(player)) {
            int cooldown = player.getPersistentData().getInt("FireTalismanCooldown");
            int active   = player.getPersistentData().getInt("FireTalismanActive");

            if (cooldown > 0) {
                player.getPersistentData().putInt("FireTalismanCooldown", cooldown - 1);
            }

            if (active > 0) {
                player.getPersistentData().putInt("FireTalismanActive", active - 1);

                // give fire resistance effect
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20, 0, false, false, true));
            }

            // trigger only when first touching fire/lava
            if (player.isOnFire() || player.isInLava()) {
                if (cooldown <= 0 && active <= 0) {
                    // activate fire immunity
                    player.getPersistentData().putInt("FireTalismanActive", 20 * 5); // 5s
                    player.getPersistentData().putInt("FireTalismanCooldown", 20 * 13); // 8s
                }
            }
        }
        // Check if the player has the Blindness Charm equipped
        if (hasBlindnessCurseCharmEquipped(player)) {
            // Apply Blindness continuously (10 seconds, refreshed each tick)
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0, false, false));
        } else {
            // Remove effect if the charm is removed
            if (player.hasEffect(MobEffects.BLINDNESS)) {
                player.removeEffect(MobEffects.BLINDNESS);
            }
        }
    }



    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (hasNullifyCharmEquipped(player)) {

            double chance = nullifyChanceMap.getOrDefault(player, 0.1); // start at 10%
            if (random.nextDouble() < chance) {
                event.setCanceled(true);
                nullifyChanceMap.put(player, 0.1); // reset chance
                nullifyEffectTicks.put(player, 10); // 10 ticks flash
            } else {
                nullifyChanceMap.put(player, Math.min(1.0, chance + 0.1));
            }
        }
        // Risky Power Charm → double damage taken
        if (hasRiskyPowerCharmEquipped(player)) {
            event.setAmount(event.getAmount() * 2f);
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
                    float boostedDamage = event.getAmount() * 1.65f;
                    event.setAmount(boostedDamage);
                }
            }
            if (fragile || enduring) {
                float boostedDamage = event.getAmount() * 1.4f;
                event.setAmount(boostedDamage);
            }
            if (hasLifeLeechCharmEquipped(player)) {
                float leechAmount = event.getAmount() * 0.4f;
                player.heal(leechAmount);
            }
            if (hasBlindnessCurseCharmEquipped(player)) {
                float boostedDamage = event.getAmount() * 2f;
                event.setAmount(boostedDamage);
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerDamaged(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (hasRageCharmEquipped(player)) {
            rageSpeedTicks.put(player, 60); // 10 seconds (200 ticks) of speed
        }
    }
    @SubscribeEvent
    public static void onPlayerTickRage(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (player.level().isClientSide) return;

        var moveInstance = player.getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
        if (moveInstance == null) return;

        int ticks = rageSpeedTicks.getOrDefault(player, 0);

        if (ticks > 0) {
            rageSpeedTicks.put(player, ticks - 1);

            var modifier = new AttributeModifier(
                    RAGE_SPEED_CHARM_ID,
                    0.5, // +50% movement speed
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );

            if (!moveInstance.hasModifier(RAGE_SPEED_CHARM_ID)) {
                moveInstance.addTransientModifier(modifier);
            }
        } else {
            moveInstance.removeModifier(RAGE_SPEED_CHARM_ID);
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
    @SubscribeEvent
    public static void onPlayerTickSprint(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (player.level().isClientSide) return;

        if (!hasSprintInvisCharmEquipped(player)) return;

        // Check if sprinting
        boolean sprinting = player.isSprinting();

        if (sprinting) {
            if (!player.hasEffect(MobEffects.INVISIBILITY)) {
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 210, 0, false, false));
                // 210 ticks so it doesn't immediately disappear; we'll refresh every tick
            }
        } else {
            // Remove invisibility if not sprinting
            if (player.hasEffect(MobEffects.INVISIBILITY)) {
                player.removeEffect(MobEffects.INVISIBILITY);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickMagnet(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (player.level().isClientSide) return;

        if (!hasMagnetCharmEquipped(player)) return;

        double radius = 8.0; // normal pickup is ~2 blocks, so this is a boost
        var items = player.level().getEntitiesOfClass(
                net.minecraft.world.entity.item.ItemEntity.class,
                player.getBoundingBox().inflate(radius)
        );

        for (var item : items) {
            if (item.isAlive() && !item.hasPickUpDelay()) {
                // Pull items toward the player
                double dx = player.getX() - item.getX();
                double dy = player.getY() + 1.0 - item.getY();
                double dz = player.getZ() - item.getZ();
                double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);

                if (dist < radius && dist > 0.25) {
                    double strength = 0.05; // how strong the pull is
                    item.setDeltaMovement(
                            item.getDeltaMovement().add(
                                    dx / dist * strength,
                                    dy / dist * strength,
                                    dz / dist * strength
                            )
                    );
                }
            }
        }
    }
    // Withering Charm: applies Wither to target when attacked
    @SubscribeEvent
    public static void onPlayerAttackWither(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        // Check if player has the Withering Charm equipped in slots 6-8
        if (!hasWitheringCharmEquipped(player)) return;

        // Only apply to living entities (exclude things like armor stands)
        if (event.getEntity() instanceof LivingEntity target) {
            // Apply Wither effect: duration 5 seconds (100 ticks), amplifier 1
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
        }
    }
    // Slow Falling Charm: gives Slow Falling while equipped
    @SubscribeEvent
    public static void onPlayerTickSlowFalling(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.level().isClientSide) return;

        // Check if player has the Slow Falling Charm equipped
        if (hasSlowFallingCharmEquipped(player)) {
            // Apply Slow Falling: duration 2 ticks, amplifier 0
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 2, 0, false, false));
        }
    }
    // ---- Hurt Event ----
    @SubscribeEvent
    public static void onPlayerAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        if (!hasBlazeflintCharmEquipped(player)) return;

        // Increment hit count for player
        int count = blazeflintHitCount.getOrDefault(player, 0) + 1;

        if (count >= 2) {
            // Apply bonus on every 2nd hit
            event.setAmount(event.getAmount() * 1.1f); // +10% damage
            event.getEntity().igniteForSeconds(2); // set target on fire
            count = 0; // reset after bonus
        }

        blazeflintHitCount.put(player, count);
    }
    // Levitation Charm
    @SubscribeEvent
    public static void onPlayerAttackLevitate(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        // Check if player has the Withering Charm equipped in slots 6-8
        if (!hasLevitationCharmEquipped(player)) return;

        // Only apply to living entities (exclude things like armor stands)
        if (event.getEntity() instanceof LivingEntity target) {
            target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 1));
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
    private static boolean hasRiskyPowerCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.VOID_SKULL.get())) return true; // example item
        }
        return false;
    }
    static boolean hasDirectionalCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.STORM_COMPASS.get())) return true;
        }
        return false;
    }
    private static boolean hasRageCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.TOTEM_OF_SCURRY.get())) return true; // <- replace with your charm item
        }
        return false;
    }
    private static boolean hasSprintInvisCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.HUNTER_MASK.get())) return true; // <- replace with your charm item
        }
        return false;
    }
    private static boolean hasMagnetCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.RING_MAGNET.get())) return true; // your custom charm item
        }
        return false;
    }
    private static boolean hasLifeLeechCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.TOTEM_OF_LEECHING.get())) return true;
        }
        return false;
    }
    private static boolean hasWitheringCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.TOTEM_OF_WITHERING.get())) return true;
        }
        return false;
    }
    private static boolean hasSlowFallingCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.ANCIENT_TOTEM.get())) return true;
        }
        return false;
    }
    private static boolean hasOceanCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.CORAL_CROWN.get())) return true;
        }
        return false;
    }
    private static boolean hasBlazeflintCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.BLAZEFLINT.get())) return true;
        }
        return false;
    }
    static boolean hasExplosivePropulsorCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.EXPLOSIVE_PROPULSOR.get())) return true;
        }
        return false;
    }
    private static boolean hasFireAmberEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.BLAZING_AMBER.get())) return true;
        }
        return false;
    }
    private static boolean hasBlindnessCurseCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.THIRD_EYE_BAND.get())) return true;
        }
        return false;
    }
    private static boolean hasLevitationCharmEquipped(Player player) {
        for (int slotIndex = 6; slotIndex <= 8; slotIndex++) {
            ItemStack stack = player.getInventory().getItem(slotIndex);
            if (stack.is(ModItems.TOTEM_OF_SHULKING.get())) return true;
        }
        return false;
    }
}
