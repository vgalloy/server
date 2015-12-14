package com.vgalloy.server.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 14/12/15.
 */
@Aspect
@Component
public class LoggerAspect {

    @Around("@annotation(methodLog)")
    public final Object logForMethodAndClass(ProceedingJoinPoint joinPoint, Log methodLog) throws Throwable {
        return displayLog(joinPoint, methodLog);
    }

    private Object displayLog(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        StringBuilder stringBuilder = new StringBuilder("[ START ] : ")
                .append(joinPoint.getSignature().getName())
                .append("(");
        for(Object o : joinPoint.getArgs()){
            stringBuilder.append(o.toString()).append("");
        }
        stringBuilder.append(")");
        LogLevel.printLog(logger, log.value(), stringBuilder.toString());

        Object result = joinPoint.proceed();

        stringBuilder = new StringBuilder("[ END   ] : ")
                .append(joinPoint.getSignature().getName())
                .append(" ==> ")
                .append(result);
        LogLevel.printLog(logger, log.value(), stringBuilder.toString());
        return result;
    }

}
