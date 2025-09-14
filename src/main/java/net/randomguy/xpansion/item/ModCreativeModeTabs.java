package net.randomguy.xpansion.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.randomguy.xpansion.XpansionMod;
import net.randomguy.xpansion.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, XpansionMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> XPANSION_BUILDING_BLOCKS_TAB = CREATIVE_MODE_TABS.register("xpansion_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.REFLEXIUM_BLOCK.get()))
                    .title(Component.translatable("creativetab.xpansion.xpansion_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.REFLEXIUM_ORE_BLOCK.get());
                        output.accept(ModBlocks.REFLEXIUM_BLOCK.get());
                        output.accept(ModBlocks.KINECTIC_GLASS.get());


                    })

                    .build());

    public static final RegistryObject<CreativeModeTab> XPANSION_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register("xpansion_ingredients_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REFLEXIUM.get()))
                    .withTabsBefore(XPANSION_BUILDING_BLOCKS_TAB.getId())
                    .title(Component.translatable("creativetab.xpansion.xpansion_ingredients"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHERRY_BLOSSOM.get());
                        output.accept(ModItems.REFLEXIUM_ORE.get());
                        output.accept(ModItems.REFLEXIUM.get());
                        output.accept(ModItems.KINECTIC_ROSARIES.get());
                        output.accept(ModItems.ROSARY_STRING.get());


                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
