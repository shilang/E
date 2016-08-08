package com.cloud.erp.logging;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
	
/*	@Autowired
	private BLogger bLogger;
	
	@Pointcut("execution(* com.cloud.erp.service.impl.*.persistence*(..))")
	private void persistencePointCut(){}
	
	@Pointcut("execution(* com.cloud.erp.service.impl.*.delete*(..))")
	private void deletePointCut(){}
	
	@AfterReturning(pointcut = "persistencePointCut()")
	public void persistenceAdvice(JoinPoint joinPoint){
		bLogger.write(joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	}
	
	@AfterReturning(pointcut = "deletePointCut()")
	public void deleteAdvice(JoinPoint joinPoint){
		bLogger.write(joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	}*/
}

