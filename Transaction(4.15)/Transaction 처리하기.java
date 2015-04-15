// mybatis���� xml���Ͽ� bean �߰�

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     <property name="dataSource" ref="dataSource"/>
    </bean>

// xml �±׶��̺귯�� �߰�

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/tx 
    http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
	>


// servlet-context.xml ������ ������ �߰�

<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>

// Ʈ������ ����

@Transactional 
@Transactional(propagation = Propagation.REQUIRED) //Ʈ������� �ִ»��¸� �ִ°� ��� ������ ���ο�Ʈ����� ����?
@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class}) //Exeption �߻��� Ŭ���� �ѹ� (������?)


