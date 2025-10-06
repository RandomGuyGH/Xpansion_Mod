package net.randomguy.xpansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.randomguy.xpansion.block.ModBlocks;
import net.randomguy.xpansion.item.ModItems;

public class FabricatorBlock extends Block {
    private int radiant_ore;
    private int primordiaL_liquid;
    private int sunset_berries;
    private int iron_ingot;
    private int diamond;
    private int bottled_void;
    private int fire_charge;
    private int pure_salt;

    public FabricatorBlock(Properties properties) {
        super(properties);
    }

    public void setUp() {
        radiant_ore = 0;
        primordiaL_liquid = 0;
        sunset_berries = 0;
        iron_ingot = 0;
        diamond = 0;
        bottled_void = 0;
        fire_charge = 0;
        pure_salt = 0;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

        if (pEntity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == ModItems.RADIANT_ORE.get()) {
                radiant_ore += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == ModItems.PURE_SALT.get()) {
                pure_salt += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == ModItems.PRIMORDIAL_LIQUID.get()) {
                primordiaL_liquid += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == ModItems.SUNSET_BERRIES.get()) {
                sunset_berries += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == Items.DIAMOND) {
                diamond += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == Items.FIRE_CHARGE) {
                fire_charge += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == ModItems.BOTTLED_VOID.get()) {
                bottled_void += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if (itemEntity.getItem().getItem() == Items.IRON_INGOT) {
                iron_ingot += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }


            if (itemEntity.getItem().getItem() == ModItems.POLARIZER_BLUEPRINT.get()) {
                if (radiant_ore >= 1 && primordiaL_liquid >= 2) {
                    primordiaL_liquid -= 2;
                    radiant_ore -= 1;
                    itemEntity.setItem(new ItemStack(ModBlocks.POLARIZER.get(), itemEntity.getItem().getCount()));
                    dropExtras(pLevel, pPos);
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void dropExtras(Level level, BlockPos pos) {
        if (radiant_ore > 0) {
            ItemEntity radiantDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.RADIANT_ORE.get(), radiant_ore)
            );
            level.addFreshEntity(radiantDrop);
            radiant_ore = 0;
        }

        if (primordiaL_liquid > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            primordiaL_liquid = 0;
        }
        if (bottled_void > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            bottled_void = 0;
        }
        if (sunset_berries > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            sunset_berries = 0;
        }
        if (fire_charge > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            fire_charge = 0;
        }
        if (diamond > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            diamond = 0;
        }
        if (pure_salt > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            pure_salt = 0;
        }
        if (iron_ingot > 0) {
            ItemEntity liquidDrop = new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), primordiaL_liquid)
            );
            level.addFreshEntity(liquidDrop);
            iron_ingot = 0;
        }
    }
}