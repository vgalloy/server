package com.vgalloy.server.aspect.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

    @Around("@annotation(role)")
    public final Object logForClass(ProceedingJoinPoint joinPoint, Security role) throws Throwable {
        for (int i = 0; i < role.value().length; i++) {
            if (role.value()[i].equals(securityApi.getCurrentUserRole())) {
                return joinPoint.proceed();
            }
        }
        throw new SecurityException("Vous n'êtes pas authorisé à acceder à cette ressource");
    }
}
