package deepti.com.model;

import java.util.Date;
import java.util.List;
public class Data {

    List<Double> values;

    private Date timestamp;

    public Data() {

    }


    public Date getTimestamp() {
        return timestamp;
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
