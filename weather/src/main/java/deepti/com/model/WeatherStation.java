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
    @OneToMany(targetEntity = Variable.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    List<Variable> variable;

    public WeatherStation() {
    }

    public WeatherStation(int id, String wsName, String portfolio, String site, String state, double latitude, double longitude) {
        this.id = id;
        this.wsName = wsName;
        this.portfolio = portfolio;
        this.site = site;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

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



    public List<Variable> getVariable() {
        return variable;
    }

}
