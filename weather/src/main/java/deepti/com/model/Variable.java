package deepti.com.model;

import jakarta.persistence.*;

@Entity
@Table(name= "variables")
public class Variable {
    @Id
    @Column(name = "var_id")
    private int varId;

    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="unit")
    private String unit;
    @Column(name="long_name")
    private String longName;


    public int getVarId() {
        return varId;
    }

    public void setVarId(int varId) {
        this.varId = varId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
