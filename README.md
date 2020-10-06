# order.service
Order Service For DBS

### Configurations Used
<b>@SpringBootApplication</b> - same as combination of : <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@EnableAutoConfiguration` - enable Spring Boot’s auto-configuration mechanism,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@Configuration` - allow to register extra beans in the context or import additional configuration classes,<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`@ComponentScan` - enable component scan on the package where the application is located<br/>
<br/>
<b>@EnableHystrix</b> - to implement the circuit breaker pattern specifically with Hystrix on the classpath.<br/>
<b>@EnableFeignClients</b> - we enable component scanning for interfaces that declare they are Feign clients(via `@FeignClient`).<br/>
<b>@EnableSwagger2</b> - is used to enable the Swagger2 for your Spring Boot application. Swagger2 is an open source project used to generate the REST API documents for RESTful web services. It provides a user interface to access our RESTful web services via the web browser.

This is a maven module - a spring boot application, compiled with Java 11.

In the project directory, you can run: `mvn clean install`
Compiles the project and downloads the dependencies.<br />

Run the main class - com.dbs.order.service.OrderApplication as a `Java Application`
Open [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#) to view the swagger endpoint access in the browser.

The application uses Feign Client to access other microservice - `order.item.service`

The H2 database is configured with the application and can be accessed in the browser.
Open [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) to access the database.

The microservice is enable to work as a `Eureka Client` for a `Eureka Server` to be found at https://github.com/ankitvashishta/spring.boot.eureka.server 

### Eureka Client
Eureka Client is any microservice enabled to be registered with the `Eureka Server`

For the @SpringBootApplication to be discovery-aware, we include `spring-cloud-starter-netflix-eureka-client` into our classpath.

Then we can annotate the application with either `@EnableDiscoveryClient` or `@EnableEurekaClient`.<br>
This annotation is optional if we have the `spring-cloud-starter-netflix-eureka-client` dependency on the classpath.
