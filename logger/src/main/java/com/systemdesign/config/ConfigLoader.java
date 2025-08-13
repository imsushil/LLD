package com.systemdesign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemdesign.logger.LogManager;

import java.io.IOException;

/**
 * @author sushil
 */
public class ConfigLoader {
    private static final class ConfigLoaderHolder {
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }
    public static final String CONFIG_FILE_NAME = "config.json";

    public Config readConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(LogManager.class.getClassLoader().getResource(CONFIG_FILE_NAME), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigLoader getInstance() {
        return ConfigLoaderHolder.INSTANCE;
    }
}