package com.systemdesign;

import com.systemdesign.logger.LogManager;
import com.systemdesign.logger.Logger;

/**
 * @author sushil
 */
public class Application {
    public static void main(String[] args) {
        Logger logger = LogManager.getInstance().getLogger();
        logger.debug("Hello world!!");
        logger.info("Hello world!!");
        logger.error("Hello world!!");
    }
}
