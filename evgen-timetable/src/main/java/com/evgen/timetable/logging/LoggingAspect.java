package com.evgen.timetable.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Around("execution(* com.evgen.timetable.service.api.*.*(..))")
  public Object logServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {

    logger.debug("Start SERVICE method: " + startNiceName(thisJoinPoint));

    Object result = thisJoinPoint.proceed();

    logger.debug("Finish SERVICE method: " + finishNiceName(thisJoinPoint, result));

    return result;
  }

  private String startNiceName(JoinPoint joinPoint) {
    return joinPoint.getTarget().getClass() + "#" + joinPoint.getSignature().getName() + "\targs:"
        + Arrays.toString(joinPoint.getArgs());
  }

  private String finishNiceName(JoinPoint joinPoint, Object result) {
    return joinPoint.getTarget().getClass() + "#" + joinPoint.getSignature().getName() + "\tresult:"
        + result;
  }

}
