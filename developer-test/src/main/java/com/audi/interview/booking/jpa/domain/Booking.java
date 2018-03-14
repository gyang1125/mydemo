package com.audi.interview.booking.jpa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking extends AbstractPersistable {

	public enum Status {OPEN, ACTIVE, CANCELLED};
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Future
	private Date startDate;
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Future
	private Date endDate;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToMany(mappedBy = "booking")
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public Booking() {
	}

	public Booking(Status status, Date startDate, Date endDate) {
		this.status = status;
		if(null != startDate)
			this.startDate = startDate;
		else
			this.startDate = new Date();
		if(null != endDate)
			this.endDate = endDate;
		else
			this.endDate = DateUtils.addHours(this.startDate, 24);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
		vehicle.setBooking(this);
	}
}
