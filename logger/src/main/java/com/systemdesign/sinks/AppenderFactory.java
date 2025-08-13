package com.systemdesign.sinks;

import java.util.HashMap;
import java.util.Map;

import static com.systemdesign.sinks.AppenderType.*;

/**
 * @author sushil
 */
public class AppenderFactory {
    private static final Map<AppenderType, Appender> map = new HashMap<>();

    static {
        map.put(CONSOLE, new ConsoleAppender());
        map.put(FILE_SYSTEM, new FileSystemAppender());
        map.put(API, new APIAppender());
    }

    public static Appender get(AppenderType appenderType) {
        Appender appender = map.getOrDefault(appenderType, null);
        if (appender == null) throw new IllegalArgumentException("sink type: " + appenderType + " doesn't exist");
        return appender;
    }
}