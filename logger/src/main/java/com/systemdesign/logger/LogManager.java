package com.systemdesign.logger;

import com.systemdesign.config.Config;
import com.systemdesign.config.ConfigLoader;
import com.systemdesign.sinks.AppenderFactory;
import com.systemdesign.sinks.AppenderType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public class LogManager {
    private static final Map<AppenderType, LogLevel> appenderTypeLogLevelMap = new HashMap<>();

    private static final class LogManagerHolder {
        private static final LogManager INSTANCE = new LogManager();

        static {
            init();
        }
    }

    private static void init() {
        // add appenders based on external configuration file
        Config config = ConfigLoader.getInstance().readConfig();
        Logger logger = Logger.getInstance();
        config.getAppenders()
              .forEach(appenderConfig -> {
                  appenderTypeLogLevelMap.putIfAbsent(appenderConfig.getType(), appenderConfig.getLogLevel());
                  logger.addSink(AppenderFactory.get(appenderConfig.getType()));
              });
    }

    public static LogManager getInstance() {
        return LogManagerHolder.INSTANCE;
    }

    public static LogLevel getLogLevel(AppenderType appenderType) {
        return appenderTypeLogLevelMap.get(appenderType);
    }

    public Logger getLogger() {
        return Logger.getInstance();
    }
}