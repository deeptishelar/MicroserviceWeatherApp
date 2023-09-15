package deepti.com.model;

public class VariableValueUnit {
    private String name;
    private double value;
    private String unit;

    public VariableValueUnit(String longName, String unit, Double value) {
        this.name = longName;
        this.unit = unit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
