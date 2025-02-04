package de.catstorm.ninjakill.ninjakill.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This class will probably remain unused
public class NinjaKillClient implements ClientModInitializer {
    public static final String MOD_ID = "ninjakill";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("NinjaKill client Initialized!");
    }
}