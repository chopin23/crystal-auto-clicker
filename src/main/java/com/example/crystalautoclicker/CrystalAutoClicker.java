package com.example.crystalautoclicker;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalAutoClicker implements ModInitializer {
    public static final String MOD_ID = "crystalautoclicker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Crystal Auto Clicker initialized");
    }
}
