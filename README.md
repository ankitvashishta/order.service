# order.service
Order Service For DBS

This is a maven module - a spring boot application, compiled with Java 11.

In the project directory, you can run: ### `mvn clean install`.

Compiles the project and downloads the dependencies.<br />

Run the main class - com.dbs.order.service.OrderApplication as a ### `Java Application`.

Open [http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#) to view the swagger endpoint access in the browser.

The application uses Feign Client to access other microservice - ### `order.item.service`

The H2 database is configured with the application and can be accessed in the browser.

Open [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) to access the database.
