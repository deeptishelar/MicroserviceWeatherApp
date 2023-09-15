package deepti.com.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "weather_stations")
public class WeatherStation {
    @Id
    int id;
    @Column(name = "ws_name")
    String wsName;
    @Column(name = "portfolio")
    String portfolio;
    @Column(name = "site")
    String site;
    @Column(name = "state")
    String state;
    @Column(name = "latitude")
    double latitude;
    @Column(name = "longitude")
    double longitude;
    @OneToMany(targetEntity = Variable.class)
    @JoinColumn(name = "id")
    List<Variable> variable;


    public int getId() {
        return id;
    }


    public String getWsName() {
        return wsName;
    }


    public String getPortfolio() {
        return portfolio;
    }


    public String getSite() {
        return site;
    }


    public String getState() {
        return state;
    }


    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    public List<Variable> getVariable() {
        return variable;
    }

}
