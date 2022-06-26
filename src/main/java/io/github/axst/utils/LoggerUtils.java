package io.github.axst.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {

    private static final Logger LOGGER = LogManager.getLogger("[RebelClient] > ");

    public static void info(Object message, Object... parameters) {
        LOGGER.info( String.valueOf( message ), parameters );
    }

    public static void error(Object message, Object... parameters) {
        LOGGER.error( String.valueOf( message ), parameters );
    }

}
