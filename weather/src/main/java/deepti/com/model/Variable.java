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


    public String getName() {
        return name;
    }


    public String getUnit() {
        return unit;
    }

    public String getLongName() {
        return longName;
    }

}
