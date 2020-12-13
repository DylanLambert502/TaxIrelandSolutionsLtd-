import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent a payment made by a property owner
 */
public class Payment {
    /**
     * variables declared
     */
    private LocalDate date;
    private double amount;

    /**
     * constructor that take one input
     * @param amount
     */
    public Payment(double amount){
        this.date = LocalDate.now();
        this.amount = amount;
    }

    /**
     * gets the payment amount and return the value
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     * to string overRide method
     * @return
     */
    @Override
    public String toString() {
        return "Amount paid: " + amount + ", Date paid: " + java.time.LocalDate.now();
    }

    /**
     * CSV to string method
     * @return
     */
    public String CSVToString() {
        return "Amount paid:" + amount + ",Date paid:" + java.time.LocalDate.now();
    }
}
