# order.service
Order Service For DBS

This is a maven module - a spring boot application, compiled with Java 11.

In the project directory, you can run:

### `mvn clean install`

Compiles the project and downloads the dependencies.<br />

Run the main class - com.dbs.order.service.OrderApplication as a ### 'Java Application'.

Open [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#) to view the swagger endpoint access in the browser.

The application uses Feign Client to access other microservice - ###'order.item.service'
