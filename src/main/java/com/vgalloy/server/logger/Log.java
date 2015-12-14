package com.vgalloy.server.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 14/12/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    LogLevel value() default LogLevel.TRACE;
}
