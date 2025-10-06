package net.randomguy.xpansion.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.randomguy.xpansion.item.ModItems;

public class LiquefierBlock extends Block {
    public LiquefierBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity){
        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == ModItems.PRIMITIVE_CRUST.get()){
                itemEntity.setItem(new ItemStack(ModItems.PRIMORDIAL_LIQUID.get(), itemEntity.getItem().getCount()));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
