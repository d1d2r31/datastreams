=================xml (xml 설정을 이용한 AOP 구현) ==================================

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">

	<bean id="logAop" class="com.javalec.ex.LogAop" />
	
	<aop:config>
		<aop:aspect id="logger" ref="logAop">
			<aop:pointcut id="publicM" expression="within(com.javalec.ex.*)"  />
			<aop:around pointcut-ref="publicM" method="loggerAop" />
		</aop:aspect>
	</aop:config>
	
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

// 공통기능을 구현할 LogAop 클래스 객체 생성 
// 	<aop:config>
//		<aop:aspect id="logger" ref="logAop">
//			<aop:pointcut id="publicM" expression="within(com.javalec.ex.*)"  />
//			<aop:around pointcut-ref="publicM" method="loggerAop" />
//		</aop:aspect>
//	</aop:config> AOP 설정 
//  AspectJ Pointcut 표현식을 이용하여 해당범위 설정

================ LogAop 클래스 ================================================

package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {

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
	
}

// 기능이 수행되기 전에 LogAop 클래스가 가장 먼저 실행돈다
// joinpoint.proceed(); 를 로드하지 않으면 기능이 수행되지 않는다 . ( 프록시 이용)
