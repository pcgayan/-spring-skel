# Item Repository Spring Boot Application #

### Project Overview ###

This project contains an implementation of a simple repository for Items with better intergration testing.


### Application structure ###

The application is implemented as a Spring Boot application using an in memory H2 Database Engine to store the data. 

The application is structured in a controller package with an ItemController that exposes a REST endpoint with one GET Items method. A model package with the data model. This data model currently only contains one entity Item. A repository package with an ItemRepository to access the database.

The main class in the application is com.cepheid.cloud.skel.SkelApplication. Running this class will start the application and insert four empty Item entities into the database.

There is also one unfinished JUnit test in com.cepheid.cloud.skel.ItemControllerTest

### UCs ###

* CRUD Rest API for Items.
* Add Unit Test cases with good coverage.  

Whatever code you produce, at the end of this exercise it should be of production quality, compile and run even if functionality is not complete.

### Installation ###

Clone or download the repository from https://github.com/pcgayan/-spring-skel/

The project is prepared to be used with Gradle and InteliJ.

Project is configured to use JavaSE-11. But it works with JavaSE-1.8 or greater, so you can either install Java 11 or change the project settings to your existing version of Java.


### Starting application/ Run Tests ###

Right click on com.cepheid.cloud.skel.SkelApplication and select Run As -> Java Application
Right click on com.cepheid.cloud.skel.ItemControllerTest and select Run As -> JUnit Test

### Useful Links ###

* https://spring.io/projects/spring-boot
* https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
* https://www.h2database.com/html/main.html
