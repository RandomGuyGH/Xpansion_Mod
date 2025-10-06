package net.randomguy.xpansion.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.randomguy.xpansion.XpansionMod;
import net.randomguy.xpansion.block.custom.FabricatorBlock;
import net.randomguy.xpansion.block.custom.LiquefierBlock;
import net.randomguy.xpansion.block.custom.PolarizerBlock;
import net.randomguy.xpansion.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, XpansionMod.MOD_ID);


    public static final RegistryObject<Block> REFLEXIUM_ORE_BLOCK = registryBlock("reflexium_ore_block",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> REFLEXIUM_BLOCK = registryBlock("reflexium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> KINECTIC_GLASS = registryBlock("kinectic_glass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).requiresCorrectToolForDrops().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> FABRICATOR = registryBlock("fabricator",
            () -> new FabricatorBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> POLARIZER = registryBlock("polarizer",
            () -> new PolarizerBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> EXTRACTOR = registryBlock("extractor",
            () -> new ExtractorBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> LIQUEFIER = registryBlock("liquefier",
            () -> new LiquefierBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
