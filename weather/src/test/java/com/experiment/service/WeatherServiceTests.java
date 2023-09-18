package com.experiment.service;

import deepti.com.model.WeatherStation;
import deepti.com.repository.WeatherRepository;
import deepti.com.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= WeatherServiceTests.class)
public class WeatherServiceTests {
    @Mock
    WeatherRepository weatherRepository;

    @InjectMocks
    WeatherService weatherService;
    @Test
    public void test_getWeatherStations()
    {
        List<WeatherStation> stationList = new ArrayList<>();
        stationList.add(new WeatherStation(1,"Cohuna North","Cohuna Solar Farm","Enel Green Power","VIC",-35.882762d,144.217208d));
        when(weatherRepository.findAll()).thenReturn(stationList);
        assertEquals(1,weatherService.getWeatherStations().size());
    }

    @Test
    public void test_getWeatherStationsForState()
    {
        List<WeatherStation> stationList = new ArrayList<>();
        stationList.add(new WeatherStation(1,"Cohuna North","Cohuna Solar Farm","Enel Green Power","VIC",-35.882762d,144.217208d));
        stationList.add(new WeatherStation(2,"Bulgana Mast","Bulgana Green Power Hub","NEOEN","VIC",-37.062474d,142.950079d));
        when(weatherRepository.findByState("VIC")).thenReturn(stationList);

        assertEquals(2,weatherService.getWeatherStationsForState("VIC").size());
    }
    @Test
    public void test_getWeatherStationById()
    {
        WeatherStation station = new WeatherStation(1, "Cohuna North", "Cohuna Solar Farm", "Enel Green Power", "VIC", -35.882762d, 144.217208d);
        when(weatherRepository.findById(1)).thenReturn(Optional.of(station));
        assertEquals(station,weatherService.getWeatherStation(1));
    }

    @Test
    public void test_getWeatherInfo()
    {
        List<WeatherStation> stationList = new ArrayList<>();
        stationList.add(new WeatherStation(1,"Cohuna North","Cohuna Solar Farm","Enel Green Power","VIC",-35.882762d,144.217208d));
        when(weatherRepository.findAll()).thenReturn(stationList);
        assertEquals(1,weatherService.getWeatherStations().size());
    }
}
