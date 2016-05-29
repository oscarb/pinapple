package se.oscarb.pinapple;

/*
 * Class representing any (PIN) code:
 */

public class Code {

    /*
        Fields
     */
    private String value;
    private String label;

    /*
        Constructors
     */
    public Code() {
        value = null;
        label = null;
    }

    public Code(String label, String value) {
        this.value = value;
        this.label = label;
    }

    public Code(String value) {
        this();
        this.value = value;
    }

    /*
        Methods
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
