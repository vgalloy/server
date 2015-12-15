package com.vgalloy.server.aspect.logger;

import org.slf4j.Logger;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 14/12/15.
 */
public enum LogLevel {
    OFF, TRACE, DEBUG, INFO, WARNING, ERROR;

    public static final void printLog(Logger logger, LogLevel logLevel, String message) {
        switch (logLevel) {
            case OFF:
                break;
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARNING:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            default:
                break;
        }
    }
}
