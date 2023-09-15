package deepti.com.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {
    String name;
    String site;
    String portfolio;
    String timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<VariableValueUnit> getDataList() {
        return dataList;
    }

    public void setDataList(List<VariableValueUnit> dataList) {
        this.dataList = dataList;
    }

    List<VariableValueUnit> dataList = new ArrayList<>();

    public WeatherInfo(String name, String site, String portfolio, List<VariableValueUnit> dataList, String timestamp) {
        this.name = name;
        this.site = site;
        this.portfolio = portfolio;
        this.timestamp = timestamp;
        this.dataList = dataList;
    }
}
