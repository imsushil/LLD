package com.systemdesign.logger;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public enum LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    private static final Map<String, LogLevel> logLevelMap = new HashMap<>();

    static {
        Arrays.stream(LogLevel.values())
                .forEach(logLevel -> logLevelMap.put(logLevel.name(), logLevel));
    }

    @JsonCreator
    public static LogLevel fromString(String logLevel) {
        LogLevel level = logLevelMap.get(logLevel.toUpperCase());
        if (level == null) throw new RuntimeException("LogLevel: " + logLevel + " is not supported");
        return level;
    }
}