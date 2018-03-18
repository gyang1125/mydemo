package com.bmw.test.service;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.Vehicle;
import com.bmw.test.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Vehicle service
 * 
 * @author Guilin Yang
 *
 */
@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	public List<Vehicle> saveVehicles(List<Vehicle> vehicles) {
		Assert.notEmpty(vehicles, "vehicles must not be empty");
		return vehicleRepository.saveAll(vehicles);
	}

	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	public List<Position> findByVin(String vin) {
		Assert.notNull(vin, "VIN must not be null");
		return vehicleRepository.findByVin(vin).getPositions();
	}

	public List<Position> findByVin(String vin, String session) {
		List<Position> resultPositions = new ArrayList<Position>();
		this.findByVin(vin).forEach(position -> {
			if (session.equals(position.getSession()))
				resultPositions.add(position);
		});
		descendByTimestamp(resultPositions);

		return resultPositions;
	}

	private void descendByTimestamp(List<Position> positions) {
		// descending order by timestamp
		positions.sort((p1, p2) -> {
			if (p1.getTimestamp() == p2.getTimestamp())
				return 0;
			return p1.getTimestamp() > p2.getTimestamp() ? -1 : 1;
		});
	}

}
