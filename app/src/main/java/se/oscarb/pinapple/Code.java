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
    private long encryptedValue;
    @Column(name = "Label")
    private String label;

    @Column(name = "DateTimeAdded")
    private long dateTimeAdded;
    @Column(name = "Category")
    private int category;
    @Column(name = "SortOrder")
    private int sortOrder;
    @Column(name = "Archived")
    private boolean isArchived;
    @Column(name = "Pattern")
    private String pattern;


    /*
        Constructors
     */
    public Code() {
        super();
        encryptedValue = -1;
        label = null;
        dateTimeAdded = System.currentTimeMillis();
    }

    public Code(String label, long encryptedValue) {
        this();
        this.encryptedValue = encryptedValue;
        this.label = label;
    }

    public Code(String label, long encryptedValue, String pattern) {
        this(label, encryptedValue);
        this.pattern = pattern.trim();
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

    public static List<Code> getAll(boolean includeArchived) {
        if (includeArchived) {
            return getAll();
        } else {
            return new Select().from(Code.class).where("Archived = ?", false).execute();
        }
    }

    /*
        Methods
     */
    public long getEncryptedValue() {
        return encryptedValue;
    }

    public void setEncryptedValue(long encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPattern() {
        return pattern;
    }

    public void archive() {
        isArchived = true;
        save();
    }

    public int getNumberOfDigits() {
        return getPattern().length() - getPattern().replace("d", "").length();
    }

    @Override
    public String toString() {
        return "Code{" +
                "encryptedValue='" + encryptedValue + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
