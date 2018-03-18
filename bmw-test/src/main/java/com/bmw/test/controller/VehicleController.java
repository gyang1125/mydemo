package com.bmw.test.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.Vehicle;
import com.bmw.test.service.PositionService;
import com.bmw.test.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private PositionService positionService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		log.debug("Upload test data");
		if (file.isEmpty()) {
            throw new Exception("uploaded file is empty");
        }
		positionService.upload(file, request);
		return new ResponseEntity<String>("successfully", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> index() {
		log.debug("Getting all vehicles");
		return vehicleService.findAll();
	}

	@RequestMapping(value = "/{vin}", method = RequestMethod.GET)
	public List<Position> getAllSessions(@PathVariable("vin") String vin) {
		log.debug("Getting all sessions of a vehicle");
		return vehicleService.findByVin(vin);
	}

	@RequestMapping(value = "/{vin}/{session}", method = RequestMethod.GET)
	public List<Position> getSingleSession(@PathVariable("vin") String vin, @PathVariable("session") String session) {
		log.debug("Getting single session as the ordered list by timestamp");
		return vehicleService.findByVin(vin, session);
	}

	@RequestMapping(value = "/{vin}/lastPosition/", method = RequestMethod.GET)
	public Position getLastPosition(@PathVariable("vin") String vin) {
		log.debug("Getting the last position of certain vehicle");
		List<Position> positions = vehicleService.findByVin(vin);
		return positions.get(positions.size() - 1);
	}

}
