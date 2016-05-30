package se.oscarb.pinapple;

/*
 * Class representing any (PIN) code:
 */


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Code")
public class Code extends Model {

    /*
        Fields
     */
    @Column(name = "Value")
    private int value;
    @Column(name = "Label")
    private String label;

    /*
        Constructors
     */
    public Code() {
        super();
        value = -1;
        label = null;
    }

    public Code(String label, int value) {
        this();
        this.value = value;
        this.label = label;
    }

    public Code(int value) {
        this();
        this.value = value;
    }

    /*
        Methods
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Code{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
