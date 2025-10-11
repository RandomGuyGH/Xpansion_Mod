package net.randomguy.xpansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.randomguy.xpansion.block.ModBlocks;
import net.randomguy.xpansion.item.ModItems;

public class DuplicatorBlock extends Block {
    private int fuel;
    private int extra;

    public DuplicatorBlock(Properties properties) {
        super(properties);
    }

    public void setUp() {
        fuel = 0;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

        if (pEntity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == ModItems.ROSE_GOLD.get()) {
                if (fuel < 32) {
                    if (fuel + itemEntity.getItem().getCount() > 32){
                        extra = (itemEntity.getItem().getCount() + fuel) - 32;
                        fuel = 32;
                        itemEntity.setItem(new ItemStack(ModItems.ROSE_GOLD.get(), extra));
                    }
                    else {
                        fuel = fuel + itemEntity.getItem().getCount();
                        itemEntity.remove(Entity.RemovalReason.DISCARDED);
                    }
                }
            }
            else{
                if (fuel == 32){
                    fuel = 0;
                    itemEntity.setItem(new ItemStack(itemEntity.getItem().getItem(), itemEntity.getItem().getCount() + 1));
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}