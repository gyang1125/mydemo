package com.audi.interview.booking.service;

import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.jpa.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        Assert.notNull(vehicle, "Vehicle must not be null");
        return vehicleRepository.saveAndFlush(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findOne(Long id) {
        Assert.notNull(id, "Id must not be null");
        return vehicleRepository.findOne(id);
    }

    public Vehicle findByLicensePlate(String licensePlate) {
        Assert.hasLength(licensePlate, "License plaete must not be empty");
        return vehicleRepository.findByLicensePlate(licensePlate);
    }
    
	public List<Vehicle> saveVehicle(List<Vehicle> vehicles) {
		Assert.notEmpty(vehicles, "vehicles must not be empty");
		return vehicleRepository.save(vehicles);
	}
}
