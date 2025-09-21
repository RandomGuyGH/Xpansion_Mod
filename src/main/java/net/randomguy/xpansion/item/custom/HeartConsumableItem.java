package net.randomguy.xpansion.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class HeartConsumableItem extends Item {

    public HeartConsumableItem(Properties properties) {
        super(properties);
    }

    // Called when the player starts using the item (right-click)
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand); // start the charging animation
        return InteractionResultHolder.consume(stack);
    }

    // Called when the player releases the use (after charging)
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!level.isClientSide && entity instanceof Player player) {
            int charge = this.getUseDuration(stack, entity) - timeLeft;

            if (charge >= 20) {
                // Check if player has absorption from other sources
                MobEffectInstance current = player.getEffect(MobEffects.ABSORPTION);
                int currentAmp = current != null ? current.getAmplifier() : -1;

                // Only count your item's stacks up to 3
                int itemAmp = Math.min(currentAmp + 1, 3);

                // If the player already has higher absorption (e.g. golden apple),
                // don’t downgrade it – only add if it’s lower.
                if (itemAmp > currentAmp) {
                    player.addEffect(new MobEffectInstance(
                            MobEffects.ABSORPTION,
                            20 * 60 * 10,  // 10 minutes
                            itemAmp,
                            false,
                            false,
                            true
                    ));
                    stack.shrink(1);
                }
            }
        }
    }


    // Tell Minecraft which animation to use (trident)
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    // Duration of use (how long the player can hold right-click)
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }
}
