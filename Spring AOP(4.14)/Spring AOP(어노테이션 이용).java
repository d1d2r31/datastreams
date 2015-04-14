=================xml (@Aspect를 이용한 AOP구현 (어노테이션)) =============================

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">

	<aop:aspectj-autoproxy />
	<bean id="logAop" class="com.javalec.ex.LogAop" />

	<bean id="student" class="com.javalec.ex.Student" >
		<property name="name" value="홍길동" />
		<property name="age" value="10" />
		<property name="gradeNum" value="3" />
		<property name="classNum" value="5" />
	</bean>
	
	<bean id="worker" class="com.javalec.ex.Worker" >
		<property name="name" value="홍길순" />
		<property name="age" value="35" />
		<property name="job" value="개발자" />
	</bean>
	
</beans>

// <aop:aspectj-autoproxy /> 를 작성하여 어노테이션 이용을 정의한다
// <bean id="logAop" class="com.javalec.ex.LogAop" /> AOP구현할 클래스의 객체를 생성

/////////////////////////////////////////////////////////////////////////////
//  => advice : 공통기능을 구현한 것(클래스)
//  => target : advice가 적용될 객체
//  => joinpoint : advice가 적용될 지점(target의 메소드)
//  => pointcut : 실제로 advice가 적용된 joinpoint (pointcut은 joinpoint의 부분집합)
//  => advisor(= aspect) : advice + pointcut
//  => weaving : advice를 핵심 로직 코드에 적용하는 것

//<aop:config> : AOP 설정 정보임을 나타냄
//<aop:aspect> : Aspect를 설정함
//<aop:pointcut> : PointCut을 설정
//<aop:before> : Advice를 설정(다양하게 존재)
/////////////////////////////////////////////////////////////////////////////
================ LogAop 클래스 ================================================


package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
//	@Pointcut("execution(public void get*(..))")	// public void인 모든 get메소드
//	@Pointcut("execution(* com.javalec.ex.*.*())")	// com.javalec.ex 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* com.javalec.ex..*.*())")	// com.javalec.ex 패키지 & com.javalec.ex 하위 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* com.javalec.ex.Worker.*())")	// com.javalec.ex.Worker 안의 모든 메소드

//	@Pointcut("within(com.javalec.ex.*)")	//com.javalec.ex 패키지 안에 있는 모든 메소드
//	@Pointcut("within(com.javalec.ex..*)")  //com.javalec.ex 패키지 및 하위 패키지 안에 있는 모든 메소드
//	@Pointcut("within(com.javalec.ex.Worker)") //com.javalec.ex.Worker 모든 메소드
	
//	@Pointcut("bean(student)")	//student 빈에만 적용
	@Pointcut("bean(*ker)")		//~ker로 끝나는 빈에만 적용
	private void pointcutMethod() {
	}
	
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : " + (et - st));
		}
		
	}
	
	@Before("pointcutMethod()")
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
	
}



