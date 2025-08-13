package com.systemdesign.config;

import com.systemdesign.logger.LogLevel;
import com.systemdesign.sinks.AppenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sushil
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppenderConfig {
    private AppenderType type;
    private LogLevel logLevel;
}