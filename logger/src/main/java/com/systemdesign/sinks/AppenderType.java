package com.systemdesign.sinks;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public enum AppenderType {
    CONSOLE("Console"),
    FILE_SYSTEM("FileSystem"),
    API("API");

    private static final Map<String, AppenderType> appenderTypeMap = new HashMap<>();

    static {
        Arrays.stream(AppenderType.values()).forEach(appenderType -> {
            appenderTypeMap.put(appenderType.type, appenderType);
        });
    }

    private String type;

    AppenderType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static AppenderType fromString(String appenderType) {
        AppenderType type = appenderTypeMap.get(appenderType);
        if (type == null) throw new RuntimeException("AppenderType: " + appenderType + " is not supported");
        return type;
    }
}