devuger
=======

개발자 SNS 오픈소스 프로젝트 입니다.<br/>
최종 목표는 아이폰, 안드로이드 앱 개발까지입니다.<br/>
<br/>
http://www.devuger.com<br/>
<br/>


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



