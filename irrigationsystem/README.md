# Automatic Irrigation System

Automatic irrigation System, providing an interface for IoT device to interact with the sensor and managing plots processes

### Technology
For further reference, please consider the following sections:

* [Spring Boot 3.0.2](https://spring.io/projects/spring-boot) - Inversion of Control Framework
* [Lombok](https://projectlombok.org/)
* **MySql Database**
### How To Use
To clone and run this application, you'll need Git and Maven

### The documentation for the rest api using Swagger UI:
http://localhost:8080/swagger-ui/swagger-ui/index.html

### Working flow

- to start irrigation when we create the **plot** of land automatically attached by **sensor**
- call the api that responsible to irrigate the **plot,** and we have 2 cases 
- first case the **sensor** start the irrigation immediately and tells us when will the end time
- second case when the **sensor** is already irrigating the **plot** it will not start a new irrigation to this **plot** till it finish, and it will show when will be the end time
