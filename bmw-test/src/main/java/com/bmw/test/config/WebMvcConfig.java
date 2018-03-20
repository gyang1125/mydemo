package com.bmw.test.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bmw.test.domain.Position;
import com.bmw.test.domain.PositionCustomSerializer;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * Class <code>WebMvcConfig</code>
 * 
 * @author gyang
 *
 */

@Configuration
@EnableAsync
public class WebMvcConfig {

	// Strict ISO 8601 date format with UTC offset
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("LocationLookup-");
		executor.initialize();
		return executor;
	}

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper mapper = new ObjectMapper();

		JodaModule jodaModule = new JodaModule();
		jodaModule.addSerializer(Position.class, new PositionCustomSerializer());
		mapper.registerModule(jodaModule);
		mapper.setTimeZone(TimeZone.getTimeZone("UTC"));

		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		SerializationConfig serConfig = mapper.getSerializationConfig();
		serConfig.with(dateFormat);
		DeserializationConfig deserConfig = mapper.getDeserializationConfig();
		deserConfig.with(dateFormat);

		return mapper;
	}

	@PostConstruct
	public void initialize() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
