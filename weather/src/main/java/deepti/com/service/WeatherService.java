package deepti.com.service;

import deepti.com.model.*;
import deepti.com.repository.DataRepository;
import deepti.com.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    DataRepository dataRepository;


    public List<Station> getWeatherStations1() {
        List<WeatherStation> all = weatherRepository.findAll();
        List<Station> stations = new ArrayList<>();
        all.forEach(weatherStation ->
        {
            stations.add(new Station(weatherStation.getId(), weatherStation.getLatitude(), weatherStation.getLongitude(), weatherStation.getWsName()));
        });
        return stations;
    }
    public List<WeatherStation> getWeatherStations() {
        return weatherRepository.findAll();
    }

    public WeatherInfo getWeatherInfo(String wsId) {
        WeatherStation weatherStation = weatherRepository.findById(Integer.parseInt(wsId)).get();
        List<Variable> variables = weatherStation.getVariable();
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(" timestamp, ");
        for (Variable var : variables) {
            String name = var.getName();
            sql.append(name);
            sql.append(",");
        }
        sql.replace(sql.lastIndexOf(","), sql.length(),"");
        sql.append(" FROM data_").append(weatherStation.getId());
        sql.append(" ORDER BY timestamp desc limit 1");

        System.out.println(sql.toString());
        List<VariableValueUnit> dataList = new ArrayList<>();
        Data data = dataRepository.getData(sql.toString()).isPresent()? dataRepository.getData(sql.toString()).get() : null;
        String time = "";
        if(data != null) {
            List<Double> values = data.getValues();
            int index = 0;
            if (variables.size() == values.size()) {
                for (Variable var : variables) {
                    dataList.add(new VariableValueUnit(var.getLongName(), var.getUnit(), values.get(index++)));
                }
            }
            time = formatDate(data.getTimestamp());
        }
        WeatherInfo weatherInfo = new WeatherInfo(weatherStation.getWsName(),
                weatherStation.getPortfolio(), weatherStation.getSite(),
                dataList, time);
        System.out.println(weatherInfo.toString());
        return weatherInfo;
    }
    private String formatDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
