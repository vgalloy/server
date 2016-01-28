package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.aspect.security.SecurityException;
import com.vgalloy.server.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Intercepte les erreurs de type SecurityException pour definir un code de réponse approprié.
     *
     * @param e L'exception interceptée
     * @return Le message d'erreur voulu
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SecurityException.class)
    public String handleException(SecurityException e) {
        LOGGER.error("SecurityException", e.toString());
        return e.getMessage();
    }

    /**
     * Intercepte les erreurs de type ServiceException pour definir un code de réponse approprié.
     *
     * @param e L'exception interceptée
     * @return Le message d'erreur voulu
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ServiceException.class)
    public String handleException(ServiceException e) {
        LOGGER.error("ServiceException", e.toString());
        return e.getMessage();
    }

    /**
     * Intercepte les erreurs de type Exception pour definir un code de réponse approprié.
     *
     * @param e L'exception interceptée
     * @return Le message d'erreur voulu
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        LOGGER.error("INTERNAL_SERVER_ERROR : {}", e.getMessage());
        return INTERNAL_SERVER_ERROR;
    }
}
