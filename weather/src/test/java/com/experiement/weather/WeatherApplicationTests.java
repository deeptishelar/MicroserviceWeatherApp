package com.experiement.weather;

import deepti.com.model.WeatherStation;
import deepti.com.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes=WeatherApplicationTests.class)
class WeatherApplicationTests {
	@Autowired
	WeatherService service;
	@Test
	void contextLoads() {
	}
	@Test
	public void testGetWeatherStations()
	{
		List<WeatherStation> weatherStations = service.getWeatherStations();
		assertNotNull(weatherStations);

	}

}
