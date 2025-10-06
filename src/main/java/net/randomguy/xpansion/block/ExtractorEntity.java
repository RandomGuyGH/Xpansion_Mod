package net.randomguy.xpansion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.randomguy.xpansion.item.ModItems;
import net.randomguy.xpansion.registry.ModBlockEntities;

import java.util.Map;
import java.util.Random;

public class ExtractorEntity extends BlockEntity {

    // --- Configuration ---
    // CHANGE 1: The map now uses Block as the key, not BlockState. This is more robust.
    private static final Map<Block, ItemStack> GENERATION_RECIPES = Map.of(
            Blocks.STONE, new ItemStack(ModItems.PRIMITIVE_CRUST.get(), 2),
            Blocks.SAND, new ItemStack(ModItems.PURE_SALT.get(), 3),
            Blocks.DEEPSLATE, new ItemStack(ModItems.RADIANT_ORE.get(), 1),
            Blocks.NETHERRACK, new ItemStack(ModItems.SUNSET_BERRIES.get(), 2)
    );

    // Generation Interval Range (in ticks: 20 ticks = 1 second)
    private static final int MIN_INTERVAL = 20 * 120; // 60 seconds
    private static final int MAX_INTERVAL = 20 * 180; // 3 minutes (180 seconds)

    // --- State Variables ---
    private int currentCooldown;
    private int maxCooldown;
    private final Random random = new Random();


    // Constructor
    public ExtractorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXTRACTOR_BE.get(), pos, state);
        this.maxCooldown = MIN_INTERVAL;
        this.currentCooldown = maxCooldown;
    }

    // --- NBT (Data Persistence) ---
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("CurrentCooldown", this.currentCooldown);
        tag.putInt("MaxCooldown", this.maxCooldown);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.currentCooldown = tag.getInt("CurrentCooldown");
        this.maxCooldown = tag.getInt("MaxCooldown");
    }

    // --- Ticking Logic (Called every game tick) ---
    public static void tick(Level level, BlockPos pos, BlockState state, ExtractorEntity entity) {
        if (level.isClientSide) {
            return;
        }

        // --- DEBUGGING TIP ---
        // If it's still not working, uncomment the line below to see the cooldown in the console.
        // System.out.println("Current Cooldown: " + entity.currentCooldown);

        entity.currentCooldown--;

        if (entity.currentCooldown <= 0) {
            // 1. Check Block Below for Generation
            // CHANGE 2: Get the Block from the BlockState.
            Block blockBelow = level.getBlockState(pos.below()).getBlock();

            // --- DEBUGGING TIP ---
            // Uncomment the line below to see what block the extractor is detecting.
            // System.out.println("Block below is: " + blockBelow.asItem().toString());

            // CHANGE 3: Check the map using the Block, not the BlockState.
            if (GENERATION_RECIPES.containsKey(blockBelow)) {
                // --- DEBUGGING TIP ---
                // If you see the "Block below" message but not this one, something is wrong with your map.
                // System.out.println("Recipe found! Spawning item.");

                ItemStack outputStack = GENERATION_RECIPES.get(blockBelow).copy();

                // 2. Spawn the Item
                if (!outputStack.isEmpty()) {
                    ItemEntity itemEntity = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
                            outputStack);
                    level.addFreshEntity(itemEntity);
                }
            }

            // 3. Reset Timer to a New Random Interval
            entity.maxCooldown = entity.random.nextInt(MAX_INTERVAL - MIN_INTERVAL + 1) + MIN_INTERVAL;
            entity.currentCooldown = entity.maxCooldown;

            // Mark for saving
            entity.setChanged();
        }
    }
}

