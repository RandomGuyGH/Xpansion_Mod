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

    public static final RegistryObject<CreativeModeTab> DISCS_TAB = CREATIVE_MODE_TABS.register("xpansion_discs_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REACH_MUSIC_DISC.get()))
                    .title(Component.translatable("creativetab.xpansion.xpansion_discs"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.WIND_MUSIC_DISC.get());
                        output.accept(ModItems.ENTER_HALLOWNEST_MUSIC_DISC.get());
                        output.accept(ModItems.REACH_MUSIC_DISC.get());
                        output.accept(ModItems.SHOVEL_MUSIC_DISC.get());
                        output.accept(ModItems.CUP_MUSIC_DISC.get());
                        output.accept(ModItems.SPIDER_MUSIC_DISC.get());
                        output.accept(ModItems.ALTERNATE_DAY_MUSIC_DISC.get());
                        output.accept(ModItems.AWAKENING_MUSIC_DISC.get());
                        output.accept(ModItems.SHORELINE_MUSIC_DISC.get());
                        output.accept(ModItems.BOILER_MUSIC_DISC.get());
                        output.accept(ModItems.PROSPECTOR_MUSIC_DISC.get());
                        output.accept(ModItems.OVERTURE_MUSIC_DISC.get());
                        output.accept(ModItems.PAPER_MUSIC_DISC.get());


                    })

                    .build());
    public static final RegistryObject<CreativeModeTab> XPANSION_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register("xpansion_ingredients_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REFLEXIUM.get()))
                    .withTabsBefore(XPANSION_BUILDING_BLOCKS_TAB.getId())
                    .title(Component.translatable("creativetab.xpansion.xpansion_ingredients"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHERRY_BLOSSOM.get());
                        output.accept(ModItems.ROSE_GOLD.get());
                        output.accept(ModItems.UPPER_HALF_COPPER_HEART.get());
                        output.accept(ModItems.DOWN_HALF_COPPER_HEART.get());
                        output.accept(ModItems.LIGHT_SILK.get());
                        output.accept(ModItems.LEAD.get());
                        output.accept(ModItems.REFLEXIUM_ORE.get());
                        output.accept(ModItems.REFLEXIUM.get());
                        output.accept(ModItems.KINECTIC_ROSARIES.get());
                        output.accept(ModItems.ROSARY_STRING.get());
                        output.accept(ModItems.STRANGE_CRYSTAL.get());
                    })

                    .build());

    public static final RegistryObject<CreativeModeTab> XPANSION_WEAPONS_TAB = CREATIVE_MODE_TABS.register("xpansion_weapons_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SLIME_INJECTION.get()))
                    .withTabsBefore(XPANSION_BUILDING_BLOCKS_TAB.getId())
                    .withTabsBefore(XPANSION_INGREDIENTS_TAB.getId())
                    .title(Component.translatable("creativetab.xpansion.xpansion_weapons"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SLIME_INJECTION.get());
                        output.accept(ModItems.BOTTLED_VOID.get());

                    })

                    .build());

    public static final RegistryObject<CreativeModeTab> XPANSION_ACCOLADES_TAB = CREATIVE_MODE_TABS.register("xpansion_accolades_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SWIFT_CLOAK.get()))
                    .withTabsBefore(XPANSION_BUILDING_BLOCKS_TAB.getId())
                    .withTabsBefore(XPANSION_INGREDIENTS_TAB.getId())
                    .withTabsBefore(XPANSION_WEAPONS_TAB.getId())
                    .title(Component.translatable("creativetab.xpansion.xpansion_accolades"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SWIFT_CLOAK.get());
                        output.accept(ModItems.GALE_WINGS.get());

                    })

                    .build());
    public static final RegistryObject<CreativeModeTab> XPANSION_CHARMS_TAB = CREATIVE_MODE_TABS.register("xpansion_charms_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IRON_LOTUS.get()))
                    .withTabsBefore(XPANSION_BUILDING_BLOCKS_TAB.getId())
                    .withTabsBefore(XPANSION_INGREDIENTS_TAB.getId())
                    .withTabsBefore(XPANSION_WEAPONS_TAB.getId())
                    .title(Component.translatable("creativetab.xpansion.xpansion_charms"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.IRON_LOTUS.get());
                        output.accept(ModItems.RAGING_SOUL.get());
                        output.accept(ModItems.TOTEM_OF_LEECHING.get());
                        output.accept(ModItems.TOTEM_OF_SHULKING.get());
                        output.accept(ModItems.BLAZEFLINT.get());
                        output.accept(ModItems.THIRD_EYE_BAND.get());
                        output.accept(ModItems.TOTEM_OF_WITHERING.get());
                        output.accept(ModItems.FRAGILE_STRENGTH.get());
                        output.accept(ModItems.ENDURING_STRENGTH.get());
                        output.accept(ModItems.VOID_SKULL.get());
                        output.accept(ModItems.QUICK_FEET.get());
                        output.accept(ModItems.TOTEM_OF_SCURRY.get());
                        output.accept(ModItems.CORAL_CROWN.get());
                        output.accept(ModItems.HUNTER_MASK.get());
                        output.accept(ModItems.SWIFT_FEATHER.get());
                        output.accept(ModItems.STORM_COMPASS.get());
                        output.accept(ModItems.EXPLOSIVE_PROPULSOR.get());
                        output.accept(ModItems.ANCIENT_TOTEM.get());
                        output.accept(ModItems.COPPER_HEART.get());
                        output.accept(ModItems.CHERRY_BOUQUET.get());
                        output.accept(ModItems.BLAZING_AMBER.get());
                        output.accept(ModItems.HUNGER_PIT.get());
                        output.accept(ModItems.MENDING_SILK.get());
                        output.accept(ModItems.RING_MAGNET.get());
                        output.accept(ModItems.GOLDEN_CUP.get());
                    })

                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
