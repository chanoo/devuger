devuger
=======

개발자 SNS 오픈소스 프로젝트 입니다.<br/>
최종 목표는 아이폰, 안드로이드 앱 개발까지입니다.<br/>
<br/>
홈페이지 : http://www.devuger.com<br/>
메일 : x64core@gmail.com<br/>
<br/>

# Framework

- Spring Framework 4.1.1
- Spring Data 1.6.4 + Hibernate 4.3.6
- Maven

# Tomcat JNDI 설정 

## server.xml

    <GlobalNamingResources>

        <Resource name="jdbc/RemoteDB"
		      global="jdbc/RemoteDB"
		      auth="Container"
		      type="javax.sql.DataSource"
		      driverClassName="com.mysql.jdbc.Driver"
		      url="jdbc:mysql://127.0.0.1/test"
		      username="test"
		      password="test"
		      maxActive="100"
		      maxIdle="20"
		      minIdle="5"
		      maxWait="10000"/>
              ...
              ...
              ...

    </GlobalNamingResources>


## context.xml

    <Context>
        ...
        <ResourceLink name="jdbc/Devuger"
                    global="jdbc/RemoteDB"
                    auth="Container"
                    type="javax.sql.DataSource" />
    </Context>

