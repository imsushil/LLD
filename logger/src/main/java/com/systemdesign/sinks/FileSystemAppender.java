package com.systemdesign.sinks;

import com.systemdesign.logger.LogLevel;
import com.systemdesign.logger.LogManager;

import static com.systemdesign.sinks.AppenderType.FILE_SYSTEM;

/**
 * @author sushil
 */
public class FileSystemAppender implements Appender {

    @Override
    public void log(LogLevel logLevel, String message) {
        LogLevel logLevelSet = LogManager.getLogLevel(FILE_SYSTEM);
        if (logLevel.compareTo(logLevelSet) < 0) return;
        System.out.println(logLevel + ": " + message + " logged in FS.");
    }
}
