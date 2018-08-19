package com.nextneo.system.service.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLoggingAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeLoggingAdvice.class);
	
	private List<Long> times = new ArrayList<>();

	public List<Long> getTimes() {
		return times;
	}

	@Around("execution(* com.nextneo.system.service.repository.*.*(..))")
	public Object timeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		long initTime = System.currentTimeMillis();
		
		
		Object result = joinPoint.proceed();
		long time = System.currentTimeMillis() - initTime;
		
		getTimes().add(time);
		
		LOGGER.info("time to run: "+time+"ms");
		
		return result;

	}

}
