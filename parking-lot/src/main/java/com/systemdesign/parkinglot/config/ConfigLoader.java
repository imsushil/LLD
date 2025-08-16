package com.systemdesign.parkinglot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;

/**
 * @author sushil
 */
public class ConfigLoader {
    private static final String CONFIG_FILE = "config.json";
    @Getter
    private static Config config;

    public static void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            config = objectMapper.readValue(ConfigLoader.class.getClassLoader().getResource(CONFIG_FILE), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}