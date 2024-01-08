package tn.esprit.spring.examen.Configurations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class AOP {

	@Before("execution(* tn.esprit.spring.examen.services.*.get*(..))")
	public void avant(JoinPoint JoinPoint) {
		log.info("In the method "+ JoinPoint.getSignature().getName() +" at " + new Date());
	}


}