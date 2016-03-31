package com.vgalloy.server.aspect.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/12/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Security {

    /**
     * Array of allowed role for method.
     *
     * @return The Array of security role.
     */
    SecurityLevel[] value() default SecurityLevel.ANONYMOUS;
}
