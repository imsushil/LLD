package com.systemdesign.sinks;

import com.systemdesign.logger.LogLevel;

/**
 * @author sushil
 */
public interface Appender {
    void log(LogLevel logLevel, String message);
}