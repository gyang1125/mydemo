package com.audi.interview.booking.controller;

import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> index() {
        log.debug("Getting all vehicles");
        return vehicleService.findAll();
    }
    
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<String> saveVehicle(@RequestBody List<Vehicle> vehicles) throws Exception {
		log.debug("Saving all vehicles");
		if (null == vehicleService.saveVehicle(vehicles)) throw new Exception();
		return new ResponseEntity<String>("vehicles have been successfully saved", HttpStatus.OK);
	}
}
