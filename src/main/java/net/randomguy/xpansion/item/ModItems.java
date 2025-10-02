package net.randomguy.xpansion.item;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.randomguy.xpansion.XpansionMod;
import net.randomguy.xpansion.item.custom.HeartConsumableItem;


import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, XpansionMod.MOD_ID);

    public static final RegistryObject<Item> REFLEXIUM = ITEMS.register("reflexium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFLEXIUM_ORE = ITEMS.register("reflexium_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_BLOSSOM = ITEMS.register("cherry_blossom",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UPPER_HALF_COPPER_HEART = ITEMS.register("up_copper_heart",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DOWN_HALF_COPPER_HEART = ITEMS.register("down_copper_heart",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KINECTIC_ROSARIES = ITEMS.register("kinectic_rosaries",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSARY_STRING = ITEMS.register("rosary_string",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_GOLD = ITEMS.register("rose_gold",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_SILK = ITEMS.register("light_silk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SLIGHT_UNSTABLE_IRON = ITEMS.register("slight_unstable_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNSTABLE_IRON = ITEMS.register("unstable_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HIGH_UNSTABLE_IRON = ITEMS.register("high_unstable_iron",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KINECTITE_DROPS = ITEMS.register("kinectite_drops",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KINECTITE_SHARD = ITEMS.register("kinectite_shard",
            () -> new Item(new Item.Properties()));



    public static final RegistryObject<Item> SLIME_INJECTION = ITEMS.register("slime_injection",
            () -> new HeartConsumableItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BOTTLED_VOID = ITEMS.register("bottled_void",
            () -> new PotionItem(new Item.Properties()
            )
    );




    public static final RegistryObject<Item> SWIFT_CLOAK = ITEMS.register("swift_cloak",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.swift_cloak_description"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.swift_cloak_function"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.accolade"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> CLIMB_CLAW = ITEMS.register("climb_claw",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.climb_claw_description"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.climb_claw_function"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.accolade"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> GALE_WINGS = ITEMS.register("gale_wings",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.gale_wings_description"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.gale_wings_function"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.accolade"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
    });



    public static final RegistryObject<Item> IRON_LOTUS =
            ITEMS.register("iron_lotus",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.iron_lotus_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.iron_lotus_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> CHERRY_BOUQUET=
            ITEMS.register("cherry_bouquet",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.cherry_bouquet_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.cherry_bouquet_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> COPPER_HEART =
            ITEMS.register("copper_heart",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.copper_heart_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.copper_heart_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> QUICK_FEET =
            ITEMS.register("quick_feet",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.quick_feet_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.quick_feet_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> SWIFT_FEATHER =
            ITEMS.register("swift_feather",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.swift_feather_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.swift_feather_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> HUNGER_PIT =
            ITEMS.register("hunger_pit",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.hunger_pit_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.hunger_pit_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> RAGING_SOUL =
            ITEMS.register("raging_soul",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.raging_soul_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.raging_soul_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> FRAGILE_STRENGTH =
            ITEMS.register("fragile_strength",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.fragile_strength_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.fragile_strength_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> ENDURING_STRENGTH =
            ITEMS.register("enduring_strength",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.enduring_strength_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.enduring_strength_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> GOLDEN_CUP =
            ITEMS.register("golden_cup",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.golden_cup_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.golden_cup_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> MENDING_SILK =
            ITEMS.register("mending_silk",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.mending_silk_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.mending_silk_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> VOID_SKULL =
            ITEMS.register("void_skull",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.void_skull_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.void_skull_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> STORM_COMPASS =
            ITEMS.register("storm_compass",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.storm_compass_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.storm_compass_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> TOTEM_OF_SCURRY =
            ITEMS.register("totem_of_scurry",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_scurry_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_scurry_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> HUNTER_MASK =
            ITEMS.register("hunter_mask",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.hunter_mask_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.hunter_mask_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> RING_MAGNET =
            ITEMS.register("ring_magnet",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.ring_magnet_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.ring_magnet_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> TOTEM_OF_LEECHING =
            ITEMS.register("totem_of_leeching",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_leeching_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_leeching_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> TOTEM_OF_WITHERING =
            ITEMS.register("totem_of_withering",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_withering_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.totem_of_withering_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> ANCIENT_TOTEM =
            ITEMS.register("ancient_totem",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.ancient_totem_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.ancient_totem_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> CORAL_CROWN =
            ITEMS.register("coral_crown",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.coral_crown_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.coral_crown_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> BLAZEFLINT =
            ITEMS.register("blazeflint",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.blazeflint_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.blazeflint_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> EXPLOSIVE_PROPULSOR =
            ITEMS.register("explosive_propulsor",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.explosive_propulsor_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.explosive_propulsor_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> BLAZING_AMBER =
            ITEMS.register("blazing_amber",
                    () -> new Item(new Item.Properties().stacksTo(1).fireResistant()){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.blazing_amber_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.blazing_amber_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });
    public static final RegistryObject<Item> THIRD_EYE_BAND =
            ITEMS.register("third_eye_band",
                    () -> new Item(new Item.Properties().stacksTo(1)){
                        @Override
                        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.third_eye_band_description"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.third_eye_band_function"));
                            pTooltipComponents.add(Component.translatable("tooltip.xpansion.charm"));
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
