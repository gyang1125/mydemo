package com.bmw.test.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.Vehicle;
import com.bmw.test.exception.VehicleNotFoundException;
import com.bmw.test.repository.VehicleRepository;

/**
 * Class <code>VehicleService</code>
 *
 * @author gyang
 * 
 */
@Service
public class VehicleService {

	private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

	@Autowired
	private VehicleRepository vehicleRepository;

	/**
	 * Find all vehicles
	 * 
	 * @return list of vehicles
	 */
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	/**
	 * Find positions of a certain vehicle by its <code>vin</code>
	 * 
	 * @param vin
	 *            Vehicle Identification Number
	 * 
	 * @return list of position of a certain vehicle
	 * 
	 * @exception VehicleNotFoundException
	 *                if <code>vin</code> is not found
	 */
	public List<Position> findPositionsByVin(String vin) {
		Assert.notNull(vin, "VIN must not be null");
		Vehicle vehicle = vehicleRepository.findByVin(vin);
		if (null == vehicle)
			throw new VehicleNotFoundException("vin does not exit");
		return vehicle.getPositions();
	}

	/**
	 * Find all positions of a certain vehicle by <code>vin</code> and
	 * <code>session</code> and all found positions shall be listed in descending
	 * order by
	 * <code>timestamp</code>
	 * 
	 * @param vin
	 *            Vehicle Identification Number
	 * @param session
	 *            Session Identification String for vehicle
	 * @return List of positions of a certain vehicle by <code>session</code>
	 */
	public List<Position> findPositionsBySession(String vin, String session) {
		List<Position> resultPositions = new ArrayList<Position>();
		this.findPositionsByVin(vin).forEach(position -> {
			if (session.equals(position.getSession()))
				resultPositions.add(position);
		});
		descendByTimestamp(resultPositions);

		return resultPositions;
	}

	/**
	 * Find a position of a certain vehicle by <code>vin</code> and
	 * <code>timestamp</code>
	 * 
	 * @param vin
	 *            Vehicle Identification Number
	 * @param timestamp
	 *            date time
	 * @return a single position of a certain vehicle by <code>timestamp</code>
	 */
	public Position findPositionByTimestamp(String vin, Long timestamp) {
		List<Position> positions = this.findPositionsByVin(vin);
		for (Position position : positions) {
			if (timestamp.equals(position.getTimestamp()))
				return position;
		}
		return null;
	}

	/**
	 * Upload the position of a certain vehicle
	 * 
	 * @param vin
	 *            Vehicle Identification Number
	 * @param position
	 *            location of vehicle with a <code>timestamp</code>
	 * @return the updated vehicle
	 * 
	 * @exception VehicleNotFoundException
	 *                if <code>vin</code> is not found
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Vehicle savePosition(String vin, Position position) {
		Vehicle vehicle = vehicleRepository.findByVin(vin);
		if (null == vehicle)
			throw new VehicleNotFoundException("vin does not exit");
		vehicle.getPositions().add(position);
		return vehicle;
	}

	/**
	 * Upload positions in bulk.
	 * <p>
	 * The received position data shall be stored internally on the back-end server
	 * 
	 * @param file
	 *            position data in format of CSV
	 * @param request
	 *            HttpServletRequest
	 * 
	 */
	public void savePositionsByCsv(MultipartFile file, HttpServletRequest request) {
		String serverRoot = request.getSession().getServletContext().getRealPath("/");
		String filePath = serverRoot + "/" + file.getOriginalFilename();
		Path path = null;
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
			path = Paths.get(filePath);
			Files.write(path, bytes);
			csvReader(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void csvReader(String filePath) {
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		Position position = null;
		Vehicle vehicle = null;
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Map<String, Vehicle> vehiclesMap = new HashMap<String, Vehicle>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				vehicle = new Vehicle();
				position = new Position();
				// use comma as separator
				String[] data = line.split(cvsSplitBy);

				log.debug(count + " Position [timestamp= " + data[0] + " , vin=" + data[1] + " , session=" + data[2]
						+ " , latitude=" + data[3] + " , longitude=" + data[4] + " , heading=" + data[5] + "]");

				position.setTimestamp(Long.parseLong(data[0]));
				position.setSession(data[2]);
				position.setLatitude(Double.parseDouble(data[3]));
				position.setLongitude(Double.parseDouble(data[4]));
				position.setHeading(Integer.parseInt(data[5]));

				if (vehiclesMap.containsKey(data[1])) {
					vehiclesMap.get(data[1]).addPosition(position);
				} else {
					vehicle.setVin(data[1]);
					vehicle.addPosition(position);
					vehiclesMap.put(data[1], vehicle);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (Map.Entry<String, Vehicle> entry : vehiclesMap.entrySet()) {
			vehicles.add(entry.getValue());

		}
		vehicleRepository.saveAll(vehicles);
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
