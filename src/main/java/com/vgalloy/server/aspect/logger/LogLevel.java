package com.vgalloy.server.aspect.logger;

import org.slf4j.Logger;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 14/12/15.
 */
public enum LogLevel {
    OFF, TRACE, DEBUG, INFO, WARNING, ERROR;

    /**
     * Permet l'affichage du message passer en paramètre dans le logger en fonction du niveau de log specifié.
     *
     * @param logger   Le logger permettant d'afficher le message
     * @param logLevel Le niveau de log pour le message
     * @param message  Le message à afficher
     */
    public static void printLog(Logger logger, LogLevel logLevel, String message) {
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
