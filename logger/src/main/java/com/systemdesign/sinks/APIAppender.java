package com.systemdesign.sinks;

import com.systemdesign.logger.LogLevel;
import com.systemdesign.logger.LogManager;

/**
 * @author sushil
 */
public class APIAppender implements Appender {

    @Override
    public void log(LogLevel logLevel, String message) {
        LogLevel logLevelSet = LogManager.getLogLevel(AppenderType.API);
        if (logLevel.compareTo(logLevelSet) < 0) return;
        System.out.println(logLevel + ": " + message + " logged in API.");
    }
}