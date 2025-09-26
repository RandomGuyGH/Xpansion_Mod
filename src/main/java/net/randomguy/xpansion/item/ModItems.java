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
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> CLIMB_CLAW = ITEMS.register("climb_claw",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.climb_claw_description"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.climb_claw_function"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> GALE_WINGS = ITEMS.register("gale_wings",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.gale_wings_description"));
                    pTooltipComponents.add(Component.translatable("tooltip.xpansion.gale_wings_function"));
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
                            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                        }
                    });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
