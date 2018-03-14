package com.audi.interview.booking.jpa.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.audi.interview.booking.jpa.domain.Booking.Status;

@Entity
public class Vehicle extends AbstractPersistable {

	@NotEmpty(message="Please enter a license plate")
    @Column(unique = true)
    private String licensePlate;
	@NotEmpty(message="Please enter a vin")
    private String vin;
	@NotEmpty(message="Please enter a model")
    private String model;
	@NotNull(message="Please make active")
    private Boolean active;
	@NotEmpty(message="Please set a color")
    private String color;
	@NotNull(message="Please enter a valid date in the future")
	@Future
    private Date validTill;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Booking booking;
	
	public boolean isBooked() {
		if(null != this.booking ) {
			if(isBookingExpired())
				return false;
			if(Status.ACTIVE.equals(booking.getStatus()) ||
					Status.OPEN.equals(booking.getStatus()) )
				return true;
		}
		return false;
	}
	
	private boolean isBookingExpired() {
		if(null != this.booking) {
			Date now = new Date();
			if(now.compareTo(this.booking.getEndDate()) > 0)
				return true;
		}
		return false;
	}

	public String getLicensePlate() {
        return licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    public Boolean getActive() {
        return active;
    }

    public String getColor() {
        return color;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
