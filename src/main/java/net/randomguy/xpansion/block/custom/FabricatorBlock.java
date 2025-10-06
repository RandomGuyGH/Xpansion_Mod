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

public class FabricatorBlock extends Block {
    private int radiant_ore;
    private int primordiaL_liquid;
    private int sunset_berries;
    private int iron_ingot;
    private int diamond;
    private int bottled_void;
    private int fire_charge;

    public FabricatorBlock(Properties properties) {
        super(properties);
    }
    public void setUp(){
        radiant_ore = 0;
        primordiaL_liquid = 0;
        sunset_berries = 0;
        iron_ingot = 0;
        diamond = 0;
        bottled_void = 0;
        fire_charge = 0;
    }
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity){
        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == ModItems.RADIANT_ORE.get()){
                radiant_ore += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == ModItems.PRIMORDIAL_LIQUID.get()){
                primordiaL_liquid += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == ModItems.SUNSET_BERRIES.get()){
                sunset_berries += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == Items.IRON_INGOT){
                iron_ingot += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == Items.DIAMOND){
                diamond += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == ModItems.BOTTLED_VOID.get()){
                bottled_void += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == Items.FIRE_CHARGE){
                fire_charge += itemEntity.getItem().getCount();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
            if(itemEntity.getItem().getItem() == ModItems.POLARIZER_BLUEPRINT.get()){
                if(radiant_ore >= 1 && primordiaL_liquid >=1){
                    radiant_ore -= 1;
                    primordiaL_liquid -= 1;
                    itemEntity.setItem(new ItemStack(ModBlocks.POLARIZER.get(), itemEntity.getItem().getCount()));
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
