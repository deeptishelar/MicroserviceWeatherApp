package deepti.com.model;

public class Station {
    double lat;
    double lng;
    String name;
    int id;

    public Station(int id,double latitude, double longitude, String wsName) {
        this.lat = latitude;
        this.lng = longitude;
        this.name = wsName;
        this.id = id;
    }
}
