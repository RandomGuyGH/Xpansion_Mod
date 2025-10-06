package net.randomguy.xpansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.randomguy.xpansion.item.ModItems;

public class PolarizerBlock extends Block {
    public PolarizerBlock (Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity){

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == ModItems.UNSTABLE_METAL.get()){
                itemEntity.setItem(new ItemStack(ModItems.LEAD.get(), itemEntity.getItem().getCount()));
            }
            if(itemEntity.getItem().getItem() == ModItems.UNSTABLE_FRUIT.get()){
                itemEntity.setItem(new ItemStack(ModItems.POLAR_POMEGRANATE.get(), itemEntity.getItem().getCount()));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
