package deepti.com.model;



import java.util.Date;
import java.util.List;
import java.util.Map;


public class Data {

    private int id;
    List<Double> values;

    private Date timestamp;
    public Data()
    {

    }

    public int getId() {
        return id;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setValues(List<Double> valuesList) {
        this.values = valuesList;
    }

    public List<Double> getValues() {
        return values;
    }
}
