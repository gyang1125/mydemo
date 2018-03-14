package com.audi.interview.booking.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.audi.interview.booking.jpa.domain.Booking;
import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.jpa.domain.Booking.Status;
import com.audi.interview.booking.jpa.repository.BookingRepository;

@Service
public class BookingService {
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private UserService userService;

	public Booking saveBooking(Booking booking) {
		Assert.notNull(booking, "booking must not be empty");
		return bookingRepository.saveAndFlush(booking);
	}

	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	public Booking findOne(Long id) {
		Assert.notNull(id, "id must not be empty");
		return bookingRepository.findOne(id);
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE) // Pessimistic lock avoids race conditions
	public Booking saveBooking(Long userId, Long vehicleId, Date startDate, Date endDate) throws Exception, IllegalArgumentException {
		User user = userService.findOne(userId);
		if(null == user) // check user exist
			throw new IllegalArgumentException("user does not exist");
		Vehicle vehicle = vehicleService.findOne(vehicleId);
		if(null == vehicle) { // check vehicle exist
			throw new IllegalArgumentException("vehicle does not exist");
		}
		else {
			// check vehicle active
			if(!vehicle.getActive()) 
				throw new Exception("vehicle is not active");
			if(vehicle.isBooked())
				throw new IllegalArgumentException("this vehicle can not be booked");
				
		}
		// booking status default
		Booking booking = new Booking(Status.OPEN, startDate, endDate);
		booking.setUser(user);
		vehicle.setBooking(booking);
		booking.addVehicle(vehicle);
		return bookingRepository.save(booking);
				
	}
	
	public Booking update(Long id, Status status) throws Exception {
		Booking booking = bookingRepository.findOne(id);
		if(Status.CANCELLED.equals(booking.getStatus()))
			throw new Exception("It is not allowed to be updated, please create a new booking");
		booking.setStatus(status);
		return bookingRepository.saveAndFlush(booking);
	}

}
