// mybatis설정 xml파일에 bean 추가

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     <property name="dataSource" ref="dataSource"/>
    </bean>

// xml 태그라이브러리 추가

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/tx 
    http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
	>


// servlet-context.xml 스프링 설정에 추가

<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>

// 트랜젝션 적용

@Transactional 
@Transactional(propagation = Propagation.REQUIRED) //트랜잭션이 있는상태면 있는거 사용 없으면 새로운트랜잭션 실행?
@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class}) //Exeption 발생한 클래스 롤백 (강제성?)


