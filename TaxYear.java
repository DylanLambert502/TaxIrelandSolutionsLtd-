import java.util.ArrayList;

/**
 * Class to represent a full tax year. Not sure if we actually need this yet but it may be useful for giving Dept of
 * Environment people all of the payments that were made in a year.
 */

public class TaxYear {

    private int year;
    private ArrayList<Payment> payments;

    public TaxYear( int year ){
        this.year = year;
        this.payments = new ArrayList<Payment>();
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void addPayment( Payment p){
        payments.add(p);
    }

    public void nextYear(){
        year++;
    }
}
