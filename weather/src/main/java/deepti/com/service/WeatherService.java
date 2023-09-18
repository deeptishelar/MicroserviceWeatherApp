package deepti.com.service;

import deepti.com.exception.DataNotFoundException;
import deepti.com.exception.EntityNotFoundException;
import deepti.com.model.*;
import deepti.com.repository.DataRepository;
import deepti.com.repository.WeatherRepository;
import deepti.com.util.Util;
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

    /**
     * fetches the weather stations for the given state
     * if not specified, then fetches all
     * @param state stae
     * @return List of weatherStation object
     */
    public List<WeatherStation> getWeatherStationsForState(String state) {
        if(state.isEmpty())
            return weatherRepository.findAll();
        else
            return weatherRepository.findByState(state);
    }
    /**
     * fetches all the weather stations
     * @return List of weatherStation object
     */
    public List<WeatherStation> getWeatherStations() {
            return weatherRepository.findAll();
    }
    /**
     * fetches the weather info for the given weather stationId
     * if not specified, then fetches all
     * @param weatherStationId weatherStationId
     * @return List of weatherInfo object
     * @throws DataNotFoundException if no data table found
     */
    public WeatherInfo getWeatherInfo(int weatherStationId) {
        WeatherStation weatherStation = getWeatherStation(weatherStationId);
        List<Variable> variables = weatherStation.getVariable();
        String sql = buildDataSql(variables, String.valueOf(weatherStation.getId()));
        List<VariableValueUnit> dataList = new ArrayList<>();
        String time;
        try {
            Data data = dataRepository.getData(sql).isPresent() ? dataRepository.getData(sql).get() : null;
            List<Double> values = data.getValues();
            int index = 0;
            if (variables.size() == values.size()) {
                for (Variable var : variables) {
                    dataList.add(new VariableValueUnit(var.getLongName(), var.getUnit(), values.get(index++)));
                }
            }
            time = Util.formatDate(data.getTimestamp());
        } catch (Exception e) {
            throw new DataNotFoundException("Error occurred while fetching weather data, please make sure the data is populated in the DB for weather station with id=" + weatherStationId);
        }
        return new WeatherInfo(weatherStation.getWsName(),
                weatherStation.getPortfolio(), weatherStation.getSite(),
                dataList, time);
    }

    /**
     * Fetches the weather station with given id
     * @param weatherStationId
     * @return the matching weatherStation
     * @throws EntityNotFoundException if no station found
     */
    public WeatherStation getWeatherStation(int weatherStationId) {
        WeatherStation weatherStation = weatherRepository.findById(weatherStationId).isPresent() ?
                weatherRepository.findById(weatherStationId).get() : null;
        if (weatherStation == null) {
            throw new EntityNotFoundException("Weather Station : " + weatherStationId);
        }
        return weatherStation;
    }

    /**
     * builds a dynamic SQL based on the variables data and the data tables
     * @param variables table data
     * @param weatherStationId weather station id
     * @return SQL on the data tables.
     * @throws EntityNotFoundException if Variable not found
     */

    private static String buildDataSql(List<Variable> variables, String weatherStationId) {
        if (!variables.isEmpty()) {
            StringBuilder sql = new StringBuilder("SELECT ");
            sql.append(" timestamp, ");
            for (Variable var : variables) {
                String name = var.getName();
                sql.append(name);
                sql.append(",");
            }
            sql.replace(sql.lastIndexOf(","), sql.length(), "");
            sql.append(" FROM data_").append(weatherStationId);
            sql.append(" ORDER BY timestamp desc limit 1");
            return sql.toString();
        } else {
            throw new EntityNotFoundException("Variable : " + weatherStationId);
        }
    }


}
