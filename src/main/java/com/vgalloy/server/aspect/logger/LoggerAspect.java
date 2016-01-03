package com.vgalloy.server.aspect.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 14/12/15.
 *         Le but de cette classe est de creer des pointsCuts et d'injecter les fonctions de log aux bons endroits. Toute
 *         methode annotée @Log doit être loggées (par defaut en trace). Afin de soulager son utilisation, l'annotation
 *         s'utilise aussi sur des classes. Au quel cas toute les methodes publics de la classe sont annotées au même
 *         niveau. En cas de double annotation (l'une venant de la méthode et l'autre de la classe) c'est la plus proche
 *         (celle de la methode) qui doit prendre le pas.
 */

@Aspect
@Component
public class LoggerAspect {
    /**
     * On crée un pointCut pour injecter l'aspect aux bons endroits.
     * 1. L'annotation @within permet de trouver les méthodes dont la classe est annoté par @Log
     * 2. L'annotation @annotation permet de trouver les méthodes directement annoté par @Log
     * <p>
     * Le principale problème consiste à gerer la double annotation. Puisque chaque pointcut est un proxy il faut eviter
     * que les logs s'affichent deux fois avec differents niveaux
     * Dans le cas d'un pointCut avec un OU ( || ) l'annotation est remplie avec le second terme même si celui-ci est vide.
     * Exemple avec : @within(methodLog) || @annotation(methodLog)
     * Si la classe est annotée mais pas la méthode, l'aspect sera bien appelé mais le methodLog sera vide.
     * <p>
     * Dans ce cas de figure évoqué precedement il faut donc retrouver avec la reflexion l'annotation sur la classe pour
     * utiliser sa value. Il est important de noter que l'inverse n'est pas possible puisque les annotations sur les
     * methodes ne peuvent pas être trouvées par reflexion.
     * <p>
     * La seconde méthode consiste à effectuer un double OU ( || ) et de les lier avec un ET ( && ). Comme expliqué, les
     * clause OU seront toujours valables et retourneront les deux annotations.
     *
     * @param joinPoint Le joinPoint servant de reference vers le file d'execution et la méthode encapsulée
     * @param methodLog L'annotation (lié à la méthode) qui a servie faire le lien.
     * @param classLog  L'annotation (lié à la classe) qui a servie faire le lien.
     * @return Le resultat de la methode encapsulée par l'aspect
     */
    @Around("(@within(methodLog) || @annotation(methodLog)) && (@annotation(classLog) || @within(classLog))")
    public final Object logForClass(ProceedingJoinPoint joinPoint, Log methodLog, Log classLog) throws Throwable {
        if (methodLog != null) {
            return displayLog(joinPoint, methodLog.value());
        }
        return displayLog(joinPoint, classLog.value());
    }

    /**
     * 1. Analise le point cut afin de trouver le nom et les arguments de la fonction appelé et les log en fonction du
     * niveau de log passé en paramètre.
     * 2. Execute la méthode
     * 3. Affiche le resultat
     *
     * @param joinPoint Le joinPoint servant de reference vers le file d'execution et la méthode encapsulée
     * @param logLevel  Le niveau de log attendu
     * @return Le resultat de la methode encapsulée par l'aspect
     */
    private Object displayLog(ProceedingJoinPoint joinPoint, LogLevel logLevel) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        StringBuilder stringBuilder = new StringBuilder("[ START ] : ")
                .append(joinPoint.getSignature().getName())
                .append("(");
        for (Object o : joinPoint.getArgs()) {
            stringBuilder.append(o.toString()).append("");
        }
        stringBuilder.append(")");
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());

        Object result = joinPoint.proceed();

        stringBuilder = new StringBuilder("[ END   ] : ")
                .append(joinPoint.getSignature().getName())
                .append(" ==> ")
                .append(result);
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());
        return result;
    }

}
