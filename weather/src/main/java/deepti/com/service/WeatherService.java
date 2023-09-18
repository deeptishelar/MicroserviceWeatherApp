package deepti.com.service;

import deepti.com.exception.DataNotFoundException;
import deepti.com.exception.EntityNotFoundException;
import deepti.com.model.*;
import deepti.com.repository.DataRepository;
import deepti.com.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WeatherService {

    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    DataRepository dataRepository;

    public List<WeatherStation> getWeatherStationsForState(String state) {
        if(state.isEmpty())
            return weatherRepository.findAll();
        else
            return weatherRepository.findByState(state);
    }
    public List<WeatherStation> getWeatherStations() {
            return weatherRepository.findAll();
    }

    public WeatherInfo getWeatherInfo(int wsId) {
        WeatherStation weatherStation = getWeatherStation(wsId);
        List<Variable> variables = weatherStation.getVariable();
        StringBuilder sql = buildDataSql(variables, String.valueOf(weatherStation.getId()));
        List<VariableValueUnit> dataList = new ArrayList<>();
        String time;
        try {
            Data data = dataRepository.getData(sql.toString()).isPresent() ? dataRepository.getData(sql.toString()).get() : null;
            List<Double> values = data.getValues();
            int index = 0;
            if (variables.size() == values.size()) {
                for (Variable var : variables) {
                    dataList.add(new VariableValueUnit(var.getLongName(), var.getUnit(), values.get(index++)));
                }
            }
            time = formatDate(data.getTimestamp());

        } catch (Exception e) {
            throw new DataNotFoundException("Error occurred while fetching weather data, please make sure the data is populated in the DB for weather station with id=" + wsId);
        }
        WeatherInfo weatherInfo = new WeatherInfo(weatherStation.getWsName(),
                weatherStation.getPortfolio(), weatherStation.getSite(),
                dataList, time);
        System.out.println(weatherInfo);
        return weatherInfo;
    }

    public WeatherStation getWeatherStation(int wsId) {
        WeatherStation weatherStation = weatherRepository.findById(wsId).isPresent() ?
                weatherRepository.findById(wsId).get() : null;
        if (weatherStation == null) {
            throw new EntityNotFoundException("Weather Station : " + wsId);
        }
        return weatherStation;
    }

    private static StringBuilder buildDataSql(List<Variable> variables, String wsId) {
        if (!variables.isEmpty()) {
            StringBuilder sql = new StringBuilder("SELECT ");
            sql.append(" timestamp, ");
            for (Variable var : variables) {
                String name = var.getName();
                sql.append(name);
                sql.append(",");
            }
            sql.replace(sql.lastIndexOf(","), sql.length(), "");
            sql.append(" FROM data_").append(wsId);
            sql.append(" ORDER BY timestamp desc limit 1");
            System.out.println("SQL : "+sql);
            return sql;
        } else {
            throw new EntityNotFoundException("Variable : " + wsId);
        }

    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
