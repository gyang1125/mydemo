
#### Technologies used in the project

* Maven
* Spring Boot stack
    * Spring Data JPA (for persistence)
    * Spring MVC (for REST endpoints)
* Swagger UI (for API testing)

#### Prerequisites installed

* Oracle JDK 1.8
* Maven
* IDE or editor of your choice

#### Getting started

To start this web application just follow these steps:

1. Build the project via Maven:

    <code>$ mvn clean install</code>

2. Start the application:
    * In your IDE invoke the class method <code>com.audi.interview.booking.Application#main</code> to start the server , *or*
    * From command line execute:

    <code>$ java -jar target/booking-0.1-SNAPSHOT.jar</code>

3. Browse to the following URL for API documentation:

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Information

* Spring boot automatically provides an embedded servlet container and a persistence layer based on Hibernate (as JPA provider).
* Data are automatically stored in an in-memory database. Changes are lost after restarting the application.
* All REST endpoints can be tested locally with the Swagger UI frontend:

    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Tasks

1. The customer wants the functionality to search for users by last name:
    * Implement a new method in the <code>UserService</code> class that returns users by a given last name
    * Extend the existing API to expose the new functionality

2. The customer wants to be able to insert new vehicles into the system via the API
    * Implement an appropriate extension of the vehicle controller with suitable REST endpoint
    * Persist new data
    * (Optional) Validate the input data:
        * all vehicle fields should be mandatory
        * field <code>validTill</code> should be at least a future date

3. The customer wants a booking functionality for users to be able to book a car for a certain time period.
    * Implement a new JPA domain class and that represents a user booking.
        * An object of this class should hold all necessary relational information about a booking and:
            * status (<code>open</code>, <code>active</code>, <code>cancelled</code>)
            * start date
            * end date
    * Implement a new JPA repository to access entities of this booking class
    * Build a service bean to implement the business logic:
        * list all existing bookings
        * get one specific booking by id
        * create a new booking by giving the user and the vehicle reference, both should exist in the system:
            * the specified vehicle should be checked for active state
            * start date should set the current date, end date is 24 hours after the start date
            * initial status is <code>open</code>
        * activate an existing booking
            * status set to <code>active</code>
        * cancel an existing booking
            * status set to <code>cancelled</code>
    * Make sure that during booking creation race conditions are avoided.
    * Expose the booking service via a REST controller.
    * (Optional) More validation:
        * during booking creation there should be checked that there is no other active or open booking 
        bound to the vehicle in the period where the new booking will take place. Moreover, take into account that
        in future feature implementations bookings will be creatable with free start and end date.
