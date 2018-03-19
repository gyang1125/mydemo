package com.bmw.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmw.test.domain.Vehicle;

/**
 * Class <code>VehicleRepository</code>
 * 
 * @author gyang
 *
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	public Vehicle findByVin(String vin);
}
