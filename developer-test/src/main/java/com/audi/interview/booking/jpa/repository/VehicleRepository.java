package com.audi.interview.booking.jpa.repository;

import com.audi.interview.booking.jpa.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * For Spring Data JPA query methods see:
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    public Vehicle findByLicensePlate(String licensePlate);

}
