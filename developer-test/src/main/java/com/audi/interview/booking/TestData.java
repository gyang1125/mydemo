package com.audi.interview.booking;

import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.jpa.domain.Vehicle;
import com.audi.interview.booking.service.UserService;
import com.audi.interview.booking.service.VehicleService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TestData {

    private static final Logger log = LoggerFactory.getLogger(TestData.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @PostConstruct
    public void insertTestData() {
        insertTestUsers();
        insertTestVehicles();
    }

    private void insertTestUsers() {
        log.debug("Inserting test users");
        if (userService.findByEmail("test@user.com") == null) {
            User testUser1 = new User();
            testUser1.setEmail("test@user.com");
            testUser1.setFirstName("Max");
            testUser1.setLastName("Power");
            try {
                testUser1.setBirthday(DateUtils.parseDate("12/12/1980", new String[]{"dd/MM/yyyy"}));
            } catch (ParseException e) {
                // ignoring
            }

            userService.saveUser(testUser1);
        }

        if (userService.findByEmail("test2@user.com") == null) {
            User testUser2 = new User();
            testUser2.setEmail("test2@user.com");
            testUser2.setFirstName("James");
            testUser2.setLastName("Bond");
            try {
                testUser2.setBirthday(DateUtils.parseDate("12/12/1985", new String[]{"dd/MM/yyyy"}));
            } catch (ParseException e) {
                // ignoring
            }

            userService.saveUser(testUser2);
        }
        
        if (userService.findByEmail("test3@user.com") == null) {
        	User testUser3 = new User();
        	testUser3.setEmail("test3@user.com");
        	testUser3.setFirstName("Daniel");
        	testUser3.setLastName("Bond");
        	try {
        		testUser3.setBirthday(DateUtils.parseDate("11/11/1990", new String[]{"dd/MM/yyyy"}));
        	} catch (ParseException e) {
        		// ignoring
        	}
        	
        	userService.saveUser(testUser3);
        }
    }

    private void insertTestVehicles() {
        log.debug("Inserting test vehicles");
        if (vehicleService.findByLicensePlate("IN-1234") == null) {
            Vehicle testVehicle1 = new Vehicle();
            testVehicle1.setLicensePlate("IN-1234");
            testVehicle1.setModel("S4");
            testVehicle1.setColor("red");
            testVehicle1.setActive(true);
            testVehicle1.setVin("8765-4321");
            testVehicle1.setValidTill(DateUtils.addYears(new Date(), 1));
            
            vehicleService.saveVehicle(testVehicle1);
        }

        if (vehicleService.findByLicensePlate("IN-5678") == null) {
            Vehicle testVehicle2 = new Vehicle();
            testVehicle2.setLicensePlate("IN-5678");
            testVehicle2.setModel("Q3");
            testVehicle2.setColor("black");
            testVehicle2.setActive(true);
            testVehicle2.setVin("1234-4321");
            testVehicle2.setValidTill(DateUtils.addYears(new Date(), 1));

            vehicleService.saveVehicle(testVehicle2);
        }
        // test date to reset active to false
        if (vehicleService.findByLicensePlate("M-8888") == null) {
        	Vehicle testVehicle3 = new Vehicle();
        	testVehicle3.setLicensePlate("M-8888");
        	testVehicle3.setModel("A3 Sport");
        	testVehicle3.setColor("blue");
        	testVehicle3.setActive(false);
        	testVehicle3.setVin("6666-6666");
        	testVehicle3.setValidTill(DateUtils.addYears(new Date(), 1));
        	
        	vehicleService.saveVehicle(testVehicle3);
        }
    }
}

