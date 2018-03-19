package com.bmw.test;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bmw.test.service.VehicleService;

/**
 * Class <code>TestData</code> is creating dummy data
 *
 * @author gyang
 * 
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TestData {
	private static final Logger log = LoggerFactory.getLogger(TestData.class);

	@Autowired
	private VehicleService vehicleService;

	@PostConstruct
	public void insertTestData() {

	}

}
