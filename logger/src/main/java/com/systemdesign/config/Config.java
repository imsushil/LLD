package com.systemdesign.config;

import lombok.*;

import java.util.List;

/**
 * @author sushil
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    private List<AppenderConfig> appenders;
}
