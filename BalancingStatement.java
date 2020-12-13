import java.util.ArrayList;

public class BalancingStatement {
    /**
     * variables declared
     */
    private int year;
    private double taxDueForYear;
    private double taxPaidThisYear;
    private ArrayList<Payment> payments;

    /**
     * constructor that take the following parameters
     * @param year
     * @param taxDueForYear
     */
    public BalancingStatement(int year, double taxDueForYear){
        this.year = year;
        this.taxDueForYear = taxDueForYear;
        this.payments = new ArrayList<Payment>();
    }

    /**
     * gets the year and return the value
     * @return
     */
    public int getYear() {

        return year;
    }

    /**
     * gets the payments from the array list and return the values
     * @return
     */
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    /**
     * takes a payment and adds it to the arraylist
     * @param payment
     */
    public void addPayment(Payment payment){
        payments.add(payment);
        taxPaidThisYear += payment.getAmount();
    }

    /**
     * to string overRide method
     * @return
     */
    @Override
    public String toString() {
        String s = "BalancingStatement for " + "Year:" + year + "\n Tax Due at 1/1: " + taxDueForYear + "\n                                " +
                "Payments made\n                                ---------------\n";
                for (Payment p: payments){
                    s += "                                " + p.toString() + "\n";
                }
                s += "\n                                -------------------------- \n";
                s += "                                Total Tax Paid: " + taxPaidThisYear +
                        "\n" + "Tax Due at 31/12: " + (taxDueForYear - taxPaidThisYear) + "\n\n";
        return s;
    }
}
