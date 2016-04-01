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
     * Check if the user get the correct right.
     *
     * @param role The security annotation.
     */
    @Before("@annotation(role)")
    public final void logForClass(Security role) {
        boolean canAccess = false;
        for (SecurityLevel securityLevel : role.value()) {
            if (securityLevel.equals(securityApi.getCurrentUserRole())) {
                canAccess = true;
                break;
            }
        }
        if (!canAccess) {
            throw new SecurityException(SecurityException.UNAUTHORIZED);
        }
    }
}
