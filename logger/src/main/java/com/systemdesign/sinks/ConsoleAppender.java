package com.systemdesign.sinks;

import com.systemdesign.logger.LogLevel;
import com.systemdesign.logger.LogManager;

import static com.systemdesign.sinks.AppenderType.CONSOLE;

/**
 * @author sushil
 */
public class ConsoleAppender implements Appender {

    @Override
    public void log(LogLevel logLevel, String message) {
        LogLevel logLevelSet = LogManager.getLogLevel(CONSOLE);
        if (logLevel.compareTo(logLevelSet) < 0) return;
        System.out.println(logLevel + ": " + message + " logged in Console.");
    }
}
