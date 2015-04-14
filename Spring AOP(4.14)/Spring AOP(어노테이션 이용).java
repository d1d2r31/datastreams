=================xml (@Aspect�� �̿��� AOP���� (������̼�)) =============================

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd">

	<aop:aspectj-autoproxy />
	<bean id="logAop" class="com.javalec.ex.LogAop" />

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

// <aop:aspectj-autoproxy /> �� �ۼ��Ͽ� ������̼� �̿��� �����Ѵ�
// <bean id="logAop" class="com.javalec.ex.LogAop" /> AOP������ Ŭ������ ��ü�� ����

/////////////////////////////////////////////////////////////////////////////
//  => advice : �������� ������ ��(Ŭ����)
//  => target : advice�� ����� ��ü
//  => joinpoint : advice�� ����� ����(target�� �޼ҵ�)
//  => pointcut : ������ advice�� ����� joinpoint (pointcut�� joinpoint�� �κ�����)
//  => advisor(= aspect) : advice + pointcut
//  => weaving : advice�� �ٽ� ���� �ڵ忡 �����ϴ� ��

//<aop:config> : AOP ���� �������� ��Ÿ��
//<aop:aspect> : Aspect�� ������
//<aop:pointcut> : PointCut�� ����
//<aop:before> : Advice�� ����(�پ��ϰ� ����)
/////////////////////////////////////////////////////////////////////////////
================ LogAop Ŭ���� ================================================


package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
//	@Pointcut("execution(public void get*(..))")	// public void�� ��� get�޼ҵ�
//	@Pointcut("execution(* com.javalec.ex.*.*())")	// com.javalec.ex ��Ű���� �Ķ���Ͱ� ���� ��� �޼ҵ�
//	@Pointcut("execution(* com.javalec.ex..*.*())")	// com.javalec.ex ��Ű�� & com.javalec.ex ���� ��Ű���� �Ķ���Ͱ� ���� ��� �޼ҵ�
//	@Pointcut("execution(* com.javalec.ex.Worker.*())")	// com.javalec.ex.Worker ���� ��� �޼ҵ�

//	@Pointcut("within(com.javalec.ex.*)")	//com.javalec.ex ��Ű�� �ȿ� �ִ� ��� �޼ҵ�
//	@Pointcut("within(com.javalec.ex..*)")  //com.javalec.ex ��Ű�� �� ���� ��Ű�� �ȿ� �ִ� ��� �޼ҵ�
//	@Pointcut("within(com.javalec.ex.Worker)") //com.javalec.ex.Worker ��� �޼ҵ�
	
//	@Pointcut("bean(student)")	//student �󿡸� ����
	@Pointcut("bean(*ker)")		//~ker�� ������ �󿡸� ����
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
			System.out.println( signatureStr + " ����ð� : " + (et - st));
		}
		
	}
	
	@Before("pointcutMethod()")
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
	
}



