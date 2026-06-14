package com.example.crystalautoclicker.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "crystalautoclicker")
public class CrystalConfig implements ConfigData {

    @Comment("How many end crystals to place per second (1-20)")
    public int crystalsPerSecond = 3;

    @Comment("Minimum distance from player to place crystal (blocks)")
    public double minPlaceDistance = 1.5;

    @Comment("Maximum distance from player to place crystal (blocks)")
    public double maxPlaceDistance = 5.0;

    @Comment("Require end crystal in hotbar/offhand to place")
    public boolean requireCrystalInHand = true;

    public static final String FILE_NAME = "crystalautoclicker";
    public static int CRYSTALS_PER_SECOND = 3;

    public static void load() {
        AutoConfig.register(CrystalConfig.class, GsonConfigSerializer::new);
        CrystalConfig config = AutoConfig.getConfigHolder(CrystalConfig.class).getConfig();
        CRYSTALS_PER_SECOND = Math.clamp(config.crystalsPerSecond, 1, 20);
    }
}
