### Vehicle Location API

------

#### Technologies used in the project

* Maven
* Spring Boot stack
    * Spring Data JPA (for persistence)
    * Spring MVC (for REST endpoints)
    * Spring Security (for Authentication)
* Swagger UI (for API testing)

#### Prerequisites installed

* Oracle JDK 1.8
* Maven
* cURL

#### Getting started

To start this web application just follow these steps:

1. Build the project via Maven:

    ```bash
    $ mvn clean install
    ```

2. Start the application:
    * In your IDE invoke the class method 

      ```
      com.bmw.test.Application#main
      ```

      to start the server , *or*

    * From command line execute:

    ```
    $ java -jar target\location-0.1-SNAPSHOT.jar
    ```

3. Browse to the following URL for API:

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

    ![swaggerui](src\main\resources\screenshots\swaggerui.PNG)

    Because of authorization, browser will be redirected to login page and then you will be asked for logging in with:

    ```
    username= bmw
    password= bmw
    ```

    ![](src\main\resources\screenshots\login.PNG)

4. Upload `data.csv` using bash script `uploadCSV.bat` to execute curl command automatically

    ​

#### Information

* Spring boot automatically provides an embedded servlet container and a persistence layer based on Hibernate (as JPA provider).

* Data are automatically stored in an in-memory database H2. Changes are lost after restarting the application.

* All REST endpoints can be tested locally with the Swagger UI frontend:

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Assumption

