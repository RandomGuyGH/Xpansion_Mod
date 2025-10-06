package net.randomguy.xpansion.block;



import net.minecraft.core.BlockPos;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.EntityBlock;

import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityTicker;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;



import net.randomguy.xpansion.registry.ModBlockEntities; // Assuming your registration class is here



// Renamed class to ExtractorBlock

public class ExtractorBlock extends Block implements EntityBlock {
    public ExtractorBlock(Properties properties) {
        super(properties);
    }



// Links the block to its Block Entity (required for EntityBlock)
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

// Updated to use the ExtractorEntity
        return new ExtractorEntity(pos, state);
    }



// --- Helper method to safely cast the ticker and avoid generic type errors ---

// This method resolves the "incompatible types" compiler error by leveraging the type check.
    @SuppressWarnings("unchecked")
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> typeIn, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == typeIn ? (BlockEntityTicker<A>)ticker : null;
    }



// CRITICAL: Sets up the ticking mechanism
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null; // Ticking only happens server-side
        }



// We check that the type matches our registered Block Entity type
        if (type == ModBlockEntities.EXTRACTOR_BE.get()) { // Updated to EXTRACTOR_BE

// Now, we use the helper method to safely return the correctly cast ticker,

// resolving the compilation error.
            return createTickerHelper(type, ModBlockEntities.EXTRACTOR_BE.get(), ExtractorEntity::tick);
        }


        return null;
    }
}