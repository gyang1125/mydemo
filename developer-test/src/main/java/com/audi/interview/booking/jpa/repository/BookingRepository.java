package com.audi.interview.booking.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audi.interview.booking.jpa.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
