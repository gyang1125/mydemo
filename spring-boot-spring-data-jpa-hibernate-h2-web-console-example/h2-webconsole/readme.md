mvn spring-boot:run
http://localhost:8080/console

Note:
To make sure the H2 Web Console can access the database we’ll have to append DB_CLOSE_DELAY=-1 and DB_CLOSE_ON_EXIT=FALSE to the spring.datasource.url property.

spring.h2.console.enabled enable the console.
spring.h2.console.path path at which the console will be available.
spring.h2.console.settings.trace enable trace output.
spring.h2.console.settings.web-allow-others enable remote access.