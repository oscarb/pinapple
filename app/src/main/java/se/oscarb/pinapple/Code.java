package se.oscarb.pinapple;

/*
 * Class representing any (PIN) code:
 */

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Code")
public class Code extends Model {

    /*
        Fields
     */
    @Column(name = "EncryptedValue")
    private int encryptedValue;
    @Column(name = "Label")
    private String label;

    // TODO: Add length for zero padding

    /*
        Constructors
     */
    public Code() {
        super();
        encryptedValue = -1;
        label = null;
    }

    public Code(String label, int encryptedValue) {
        this();
        this.encryptedValue = encryptedValue;
        this.label = label;
    }

    public Code(int encryptedValue) {
        this();
        this.encryptedValue = encryptedValue;
    }

    /*
     * Database Methods
     */
    public static List<Code> getAll() {
        return new Select().from(Code.class).execute();
    }

    /*
        Methods
     */
    public int getEncryptedValue() {
        return encryptedValue;
    }

    public void setEncryptedValue(int encryptedValue) {
        this.encryptedValue = encryptedValue;
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
                "encryptedValue='" + encryptedValue + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
