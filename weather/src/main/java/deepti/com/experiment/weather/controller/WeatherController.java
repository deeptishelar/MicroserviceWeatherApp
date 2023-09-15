package deepti.com.experiment.weather.controller;

import deepti.com.model.WeatherInfo;
import deepti.com.model.WeatherStation;
import deepti.com.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class WeatherController {

    @Autowired
    WeatherService service;
    @GetMapping(path="/getWeatherInfo")
    public WeatherInfo getWeatherInfo(@RequestParam String wsId)
    {
        return service.getWeatherInfo(wsId);
    }

    @GetMapping(path="/getWeatherStations")
    public List<WeatherStation> getWeatherStations()
    {
        return service.getWeatherStations();
    }
}
