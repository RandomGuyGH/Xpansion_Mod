package net.randomguy.xpansion.registry;



import net.minecraft.world.level.block.entity.BlockEntityType;

import net.minecraftforge.registries.DeferredRegister;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegistryObject;



import net.randomguy.xpansion.XpansionMod;

import net.randomguy.xpansion.block.ExtractorEntity;

import net.randomguy.xpansion.block.ModBlocks;



public class ModBlockEntities {

// MOD_ID should be defined in XpansionMod.java

        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =

        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, XpansionMod.MOD_ID);



// --- Atmospheric Condenser Block Entity ---

        public static final RegistryObject<BlockEntityType<ExtractorEntity>> EXTRACTOR_BE =

        BLOCK_ENTITIES.register("atmospheric_condenser_be", () -> BlockEntityType.Builder.of(ExtractorEntity::new, ModBlocks.EXTRACTOR.get()).build(null));
}

