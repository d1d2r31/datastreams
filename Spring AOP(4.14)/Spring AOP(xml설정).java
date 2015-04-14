=================xml (xml ������ �̿��� AOP ����) ==================================

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
		<property name="name" value="ȫ�浿" />
		<property name="age" value="10" />
		<property name="gradeNum" value="3" />
		<property name="classNum" value="5" />
	</bean>
	
	<bean id="worker" class="com.javalec.ex.Worker" >
		<property name="name" value="ȫ���" />
		<property name="age" value="35" />
		<property name="job" value="������" />
	</bean>
	
</beans>

// �������� ������ LogAop Ŭ���� ��ü ���� 
// 	<aop:config>
//		<aop:aspect id="logger" ref="logAop">
//			<aop:pointcut id="publicM" expression="within(com.javalec.ex.*)"  />
//			<aop:around pointcut-ref="publicM" method="loggerAop" />
//		</aop:aspect>
//	</aop:config> AOP ���� 
//  AspectJ Pointcut ǥ������ �̿��Ͽ� �ش���� ����

================ LogAop Ŭ���� ================================================

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
			System.out.println( signatureStr + " ����ð� : " + (et - st));
		}
		
	}
	
}

// ����� ����Ǳ� ���� LogAop Ŭ������ ���� ���� ���൷��
// joinpoint.proceed(); �� �ε����� ������ ����� ������� �ʴ´� . ( ���Ͻ� �̿�)
