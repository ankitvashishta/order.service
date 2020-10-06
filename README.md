# order.service
Order Service

### Annotations added to the main class.
<b>@SpringBootApplication</b> - same as combination of : <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@EnableAutoConfiguration` - enable Spring Boot’s auto-configuration mechanism,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@Configuration` - allow to register extra beans in the context or import additional configuration classes,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@ComponentScan` - enable component scan on the package where the application is located<br/>
<b>@EnableHystrix</b> - to implement the circuit breaker pattern specifically with Hystrix on the classpath.<br/>
<b>@EnableFeignClients</b> - we enable component scanning for interfaces that declare they are Feign clients(via `@FeignClient`).<br/>
<b>@EnableSwagger2</b> - is used to enable the Swagger2 for your Spring Boot application. Swagger2 is an open source project used to generate the REST API documents for RESTful web services. It provides a user interface to access our RESTful web services via the web browser.

### Configurations added to the pom.xml file
<b>com.h2database</b> - Dependency added for an in memory H2 database. The database properties are added to the `application.properties` file. Any SQL scripts are added to the `data.sql` file.<br>
<b>netflix-eureka-client</b> - This dependency registers the service as a Eureka Client. The server address is added to the `application.properties` file.<br/>

### To run the service
This is a maven module - a spring boot application, compiled with Java 11.<br/>
In the project directory, you can run: `mvn clean install` compiles the project and downloads the dependencies.<br />
<b>Run the main class</b> - com.ankit.order.service.OrderApplication as a `Java Application`
Open [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#) to view the swagger endpoint access in the browser.<br/>

The application uses Feign Client to access other microservice - `order.item.service`<br/>
The H2 database is configured with the application and can be accessed at [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)

The microservice is enabled to work as a `Eureka Client` for a `Eureka Server` to be found at https://github.com/ankitvashishta/spring.boot.eureka.server 

### Eureka Client
Eureka Client is any microservice enabled to be registered with the `Eureka Server`

For the @SpringBootApplication to be discovery-aware, we include `spring-cloud-starter-netflix-eureka-client` into our classpath.

Then we can annotate the application with either `@EnableDiscoveryClient` or `@EnableEurekaClient`.<br>
This annotation is optional if we have the `spring-cloud-starter-netflix-eureka-client` dependency on the classpath.
