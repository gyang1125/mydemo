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

1. **Build the project via Maven:**

    ```
    $ mvn clean install javadoc:javadoc
    ```

2. **APIs Reference**

    After building, the API reference will be automatically generated under the directory:

    > target\site\apidocs\index.html

3. **Start the application:**

    * In your IDE invoke the class method 

      ```
      com.bmw.test.Application#main
      ```

      to start the server , *or*

    * From command line execute:

    ```
    $ java -jar target\location-0.1-SNAPSHOT.jar
    ```

3. **Browse to the following URL for API:**

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

    ![swaggerui](src\main\resources\screenshots\swaggerui.PNG)

    Note that, because of authorization, browser will be redirected to login page and then you will be asked for logging in with:

    ```
    username= bmw
    password= bmw
    ```

    ![](src\main\resources\screenshots\login.PNG)

5. **Inject observation data**

    After application has been already started, to execute the bash file `uploadCSV.bat` being able to upload the `data.csv` into embedded database of this application. 

    ```
    uploadCSV.bat
    --------------
    @echo off
    curl -F file=@"./data.csv" -u bmw:bmw http://localhost:8080/api/v1/vehicles/savePositions/csv
    @pause
    ```

    ​

#### Information

* Spring boot automatically provides an embedded servlet container and a persistence layer based on Hibernate (as JPA provider).

* Data are automatically stored in an in-memory database H2. Changes are lost after restarting the application.

* All REST endpoints can be tested locally with the Swagger UI frontend:

    http://localhost:8080/swagger-ui.html

#### Assumption

Because of the ambiguous description:

> get a single session as an ordered list of the received positions by timestamp

I created two APIs to implement this requirement

1. ```
   getSingleSession(String vin, String sessionId) : List<Position>
   ```

   which passes `vin` and `sessionId` as request parameters to return a session contains a list of positions that are sorted in descending order by timestamp. 

2. ```
   getSingleSession(String vin, Long timestamp): Position
   ```

   which passes `vin` and `timestmap`  to get a single session contains only single position information.