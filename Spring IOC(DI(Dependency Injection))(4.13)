========================= XML 설정 ===============================================

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="bmiCalcaulator" class="com.javalec.ex.BMICalculator">
		<property name="lowWeight">   
			<value>18.5</value>
		</property>
		<property name="normal">
			<value>23</value>
		</property>
		<property name="overWeight">
			<value>25</value>
		</property>
		<property name="obesity">
			<value>30</value>
		</property>
	</bean>
	
	<bean id="myInfo" class="com.javalec.ex.MyInfo">
		<property name="name">
			<value>홍길동</value>
		</property>
		<property name="height">
			<value>187</value>
		</property>
		<property name="weight">
			<value>84</value>
		</property>
		<property name="hobbys">
			<list>
				<value>수영</value>
				<value>요리</value>
				<value>독서</value>
			</list>
		</property>
		<property name="bmiCalculator">
			<ref bean="bmiCalcaulator"/>
		</property>
	</bean>

</beans>

// bean 태그를 이용해 각 클래스 객체 생성
// property 설정으로 set함수를 이용해 값을 저장
// ref bean = "객체" 를 이용해 객체 전달

================= Main클래스 ================================
package com.javalec.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args) {
		
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		ctx.close();
		
		//스프링 설정파일에서 DI를 이용해 불러온 객체를 이용한다.
	}	
}
