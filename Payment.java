import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent a payment made by a property owner
 */
public class Payment {

    private LocalDate date;
    private double amount;

    public Payment(double amount){
        this.date = LocalDate.now();
        this.amount = amount;
    }


    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Amount paid: " + amount + ", Date paid: " + java.time.LocalDate.now();
    }

    public String CSVToString() {
        return "Amount paid:" + amount + ",Date paid:" + java.time.LocalDate.now();
    }
}
