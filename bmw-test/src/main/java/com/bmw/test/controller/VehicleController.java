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

	/**
	 * Get all vehicles
	 * 
	 * @return list of vehicle
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> index() {
		log.debug("Getting all vehicles");
		List<Vehicle> vehicles = vehicleService.findAll();
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}

	/**
	 * Save positon for a certain vehicle
	 * 
	 * @param vin
	 *            vehicke identification number
	 * @param position
	 *            position of vehicle
	 * @return http status
	 */
	@RequestMapping(value = "{vin}/positions", method = RequestMethod.POST)
	public ResponseEntity<String> createPosition(@PathVariable("vin") String vin, @RequestBody Position position) {
		log.debug("Saving position with a certain vin");
		vehicleService.savePosition(vin, position);
		return new ResponseEntity<String>("save position successfully", HttpStatus.OK);
	}

	/**
	 * Upload csv file to save position in bulk
	 * 
	 * @param file
	 *            csv file
	 * @param request
	 *            http request
	 * @return http status
	 * @throws Exception
	 *             if uploaded file is empty
	 */
	@RequestMapping(value = "/positions", method = RequestMethod.POST)
	public ResponseEntity<String> createPosition(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		log.debug("Uploading csv data");
		if (file.isEmpty()) {
			throw new Exception("uploaded file must be not empty");
		}

		vehicleService.savePositionByCsv(file, request);
		return new ResponseEntity<String>("upload csv successfully", HttpStatus.OK);
	}

	/**
	 * Get all sessions for a certain vehicle
	 * 
	 * 
	 * @param vin
	 *            vehicle identification number
	 * @return list of position
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */

	@RequestMapping(value = "/{vin}/position_sessions", method = RequestMethod.GET)
	public ResponseEntity<List<Position>> getAllSessions(@PathVariable("vin") String vin)
			throws InterruptedException, ExecutionException {
		log.debug("Getting all sessions of a vehicle");
		List<Position> positions = vehicleService.findPositionsByVin(vin).get();
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}

	/**
	 * Get single session for a certain vehicle and the list of received positions
	 * is in descending order
	 * 
	 * @param vin
	 *            vehicle identification number
	 * @param session
	 *            session id
	 * @return list of position
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/{vin}/position_sessions/{session_id}", method = RequestMethod.GET)
	public ResponseEntity<List<Position>> getSingleSession(@PathVariable("vin") String vin,
			@PathVariable("session_id") String session) throws ExecutionException, InterruptedException {
		log.debug("Assumption 1 : Getting single session passing by session id");
		List<Position> positions = vehicleService.findPositionsBySession(vin, session);
		return new ResponseEntity<List<Position>>(positions, HttpStatus.OK);
	}

	/**
	 * Get single session for a certain vehicle by particular time stamp
	 * 
	 * @param vin
	 *            vehicle identificaiton number
	 * @param timestamp
	 *            time stamp as the position of vehicle is sent
	 * @return single position
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@RequestMapping(value = "/{vin}/sessions?timestamp={timestamp}", method = RequestMethod.GET)
	public ResponseEntity<Position> getSingleSession(@PathVariable("vin") String vin,
			@PathVariable("timestamp") Long timestamp) throws InterruptedException, ExecutionException {
		log.debug("Assumption 2: Getting single session passing by timestamp");
		Position position = vehicleService.findPositionByTimestamp(vin, timestamp);
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}

	/**
	 * Get last position for a certain vehicle
	 * 
	 * @param vin
	 *            vehicle identification number
	 * @return position
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@RequestMapping(value = "/{vin}/positions/last", method = RequestMethod.GET)
	public ResponseEntity<Position> getLastPosition(@PathVariable("vin") String vin)
			throws InterruptedException, ExecutionException {
		log.debug("Getting the last position of certain vehicle");
		List<Position> positions = vehicleService.findPositionsByVin(vin).get();
		Position lastPosition = positions.get(positions.size() - 1);
		return new ResponseEntity<Position>(lastPosition, HttpStatus.OK);
	}

}
