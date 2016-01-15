package com.vgalloy.server.aspect.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/12/15.
 */
@Aspect
@Component
public class SecurityAspect {
    @Autowired
    private SecurityApi securityApi;

    /**
     * Verifie que l'utilisateur à le niveau d'habilitation demander par la méthode.
     *
     * @param role L'annotation de sécurité liée à la méthode
     */
    @Before("@annotation(role)")
    public final void logForClass(Security role) {
        boolean canAcces = false;
        for (SecurityLevel securityLevel : role.value()) {
            if (securityLevel.equals(securityApi.getCurrentUserRole())) {
                canAcces = true;
            }
        }
        if (!canAcces) {
            throw new SecurityException(SecurityException.UNAUTHORIZED);
        }
    }
}
