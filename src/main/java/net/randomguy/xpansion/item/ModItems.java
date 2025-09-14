package net.randomguy.xpansion.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.randomguy.xpansion.XpansionMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, XpansionMod.MOD_ID);

    public static final RegistryObject<Item> REFLEXIUM = ITEMS.register("reflexium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFLEXIUM_ORE = ITEMS.register("reflexium_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_BLOSSOM = ITEMS.register("cherry_blossom",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KINECTIC_ROSARIES = ITEMS.register("kinectic_rosaries",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSARY_STRING = ITEMS.register("rosary_string",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
