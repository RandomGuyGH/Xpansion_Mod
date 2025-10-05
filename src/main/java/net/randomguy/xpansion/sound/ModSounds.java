package net.randomguy.xpansion.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.randomguy.xpansion.XpansionMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, XpansionMod.MOD_ID);

    public static final RegistryObject<SoundEvent> WIND = registerSoundEvent("music.wind");
    public static final ResourceKey<JukeboxSong> WIND_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.wind"));
    public static final RegistryObject<SoundEvent> ENTER_HALLOWNEST = registerSoundEvent("music.enter_hallownest");
    public static final ResourceKey<JukeboxSong> ENTER_HALLOWNEST_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.enter_hallownest"));
    public static final RegistryObject<SoundEvent> REACH = registerSoundEvent("music.reach");
    public static final ResourceKey<JukeboxSong> REACH_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.reach"));
    public static final RegistryObject<SoundEvent> SHOVEL = registerSoundEvent("music.shovel");
    public static final ResourceKey<JukeboxSong> SHOVEL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.shovel"));
    public static final RegistryObject<SoundEvent> CUP = registerSoundEvent("music.cup");
    public static final ResourceKey<JukeboxSong> CUP_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.cup"));
    public static final RegistryObject<SoundEvent> SPIDER = registerSoundEvent("music.spider");
    public static final ResourceKey<JukeboxSong> SPIDER_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.spider"));
    public static final RegistryObject<SoundEvent> ALTERNATE_DAY = registerSoundEvent("music.alternate_day");
    public static final ResourceKey<JukeboxSong> ALTERNATE_DAY_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.alternate_day"));
    public static final RegistryObject<SoundEvent> SHORELINE = registerSoundEvent("music.shoreline");
    public static final ResourceKey<JukeboxSong> SHORELINE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.shoreline"));
    public static final RegistryObject<SoundEvent> AWAKENING = registerSoundEvent("music.awakening");
    public static final ResourceKey<JukeboxSong> AWAKENING_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.awakening"));
    public static final RegistryObject<SoundEvent> PAPER = registerSoundEvent("music.paper");
    public static final ResourceKey<JukeboxSong> PAPER_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.paper"));
    public static final RegistryObject<SoundEvent> OVERTURE = registerSoundEvent("music.overture");
    public static final ResourceKey<JukeboxSong> OVERTURE_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.overture"));
    public static final RegistryObject<SoundEvent> PROSPECTOR = registerSoundEvent("music.prospector");
    public static final ResourceKey<JukeboxSong> PROSPECTOR_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.prospector"));
    public static final RegistryObject<SoundEvent> BOILER = registerSoundEvent("music.boiler");
    public static final ResourceKey<JukeboxSong> BOILER_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, "music.boiler"));
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(XpansionMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
