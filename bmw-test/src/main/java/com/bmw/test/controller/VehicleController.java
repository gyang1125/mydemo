package com.bmw.test.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.Vehicle;
import com.bmw.test.service.VehicleService;

/**
 * Class <code>VehicleController</code>
 *
 * @author gyang
 * 
 */
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> index() {
		log.debug("Getting all vehicles");
		List<Vehicle> vehicles = vehicleService.findAll();
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}

	@RequestMapping(value = "{vin}/savePosition", method = RequestMethod.POST)
	public ResponseEntity<String> savePosition(@PathVariable("vin") String vin, @RequestBody Position position) {
		log.debug("Saving position with a certain vin");
		vehicleService.savePosition(vin, position);
		return new ResponseEntity<String>("save position successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/savePositions/csv", method = RequestMethod.POST)
	public ResponseEntity<String> savePositionsByCsv(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		log.debug("Uploading csv data");
		if (file.isEmpty()) {
			throw new Exception("uploaded file must be not empty");
		}
		vehicleService.savePositionsByCsv(file, request);
		return new ResponseEntity<String>("upload csv successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/{vin}/getAllSessions", method = RequestMethod.GET)
	public ResponseEntity<List<Position>> getAllSessions(@PathVariable("vin") String vin)
			throws InterruptedException, ExecutionException {
		log.debug("Getting all sessions of a vehicle");
		List<Position> positions = vehicleService.findPositionsByVin(vin).get();
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}

	@RequestMapping(value = "/{vin}/getSingleSession/session", method = RequestMethod.GET)
	public ResponseEntity<List<Position>> getSingleSession(@PathVariable("vin") String vin,
			@RequestParam("session") String session) throws ExecutionException, InterruptedException {
		log.debug("Assumption 1 : Getting single session passing by session id");
		List<Position> positions = vehicleService.findPositionsBySession(vin, session);
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}

	@RequestMapping(value = "/{vin}/getSingleSession/timestamp", method = RequestMethod.GET)
	public ResponseEntity<Position> getSingleSession(@PathVariable("vin") String vin,
			@RequestParam("timestamp") Long timestamp) throws InterruptedException, ExecutionException {
		log.debug("Assumption 2: Getting single session passing by timestamp");
		Position position = vehicleService.findPositionByTimestamp(vin, timestamp);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}

	@RequestMapping(value = "/{vin}/lastPosition/", method = RequestMethod.GET)
	public ResponseEntity<Position> getLastPosition(@PathVariable("vin") String vin)
			throws InterruptedException, ExecutionException {
		log.debug("Getting the last position of certain vehicle");
		List<Position> positions = vehicleService.findPositionsByVin(vin).get();
		Position lastPosition = positions.get(positions.size() - 1);
		return new ResponseEntity<Position>(lastPosition, HttpStatus.OK);
	}

}
