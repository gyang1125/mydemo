package com.audi.interview.booking.controller;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audi.interview.booking.jpa.domain.Booking;
import com.audi.interview.booking.jpa.domain.Booking.Status;
import com.audi.interview.booking.service.BookingService;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
	private static final Logger log = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	/**
	 * list all existing bookings
	 * 
	 * @return existing bookings
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Booking> index() {
		log.debug("Getting all bookings");
		return bookingService.findAll();
	}
	
	/**
	 * get one specific booking by id
	 * 
	 * @param booking id
	 * @return specific booking
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Booking get(@PathVariable("id") Long id) {
        return bookingService.findOne(id);
    }
    
	/**
	 * create booking with user, vehicle, startDate and endDate
	 * 
	 * @param userId
	 * @param vehicleId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<String> save(@RequestParam(value="userId", required=true) long userId,
			@RequestParam(value="vehicleId", required=true) long vehicleId,
			@RequestParam(value="startDate", required=true) @DateTimeFormat(pattern="dd-MM-yyyy") @Future Date startDate,
			@RequestParam(value="endDate", required=false) @DateTimeFormat(pattern="dd-MM-yyyy") @Future Date endDate) throws Exception{
		log.info("startDate: " + startDate + ", endDate: " + endDate);
		if(null != endDate && endDate.compareTo(startDate) <= 0)
			throw new Exception("end date can not be before start date");
		if(null == bookingService.saveBooking(userId, vehicleId, startDate, endDate)) 
			throw new Exception();
		return  new ResponseEntity<String>("booking has been created", HttpStatus.OK);
	}
	
	/**
	 * update booking status 
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> update(@PathVariable("id") Long id, 
			@RequestParam(value="status", required=true) Status status) throws Exception{
		if(status.ACTIVE.equals(status)) {
			if(null == bookingService.update(id, status)) 
				throw new Exception();
			return  new ResponseEntity<String>("booking has been set active", HttpStatus.OK);			
		}
		if(status.CANCELLED.equals(status)) {
			if(null == bookingService.update(id, status)) 
				throw new Exception();
			return  new ResponseEntity<String>("booking has been set cancelled", HttpStatus.OK);			
		}
		if(status.OPEN.equals(status)) {
			if(null == bookingService.update(id, status)) 
				throw new Exception();
			return  new ResponseEntity<String>("booking has been set open", HttpStatus.OK);			
		}
		return  new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
}
