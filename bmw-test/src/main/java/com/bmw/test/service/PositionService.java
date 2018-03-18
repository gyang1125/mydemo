package com.bmw.test.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.Vehicle;
import com.bmw.test.repository.PositionRepository;
import com.bmw.test.repository.VehicleRepository;

@Service
public class PositionService {
	private static final Logger log = LoggerFactory.getLogger(PositionService.class);

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	public List<Position> savePositions(List<Position> positions) {
		Assert.notEmpty(positions, "positions must not be empty");
		return positionRepository.saveAll(positions);
	}

	public void upload(MultipartFile file, HttpServletRequest request) {
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

}
