import java.util.ArrayList;

public class BalancingStatement {

    private int year;
    private double taxDueForYear;
    private double taxPaidThisYear;
    private ArrayList<Payment> payments;

    public BalancingStatement(int year, double taxDueForYear){
        this.year = year;
        this.taxDueForYear = taxDueForYear;
        this.payments = new ArrayList<Payment>();
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void addPayment(Payment payment){
        payments.add(payment);
        taxPaidThisYear += payment.getAmount();
    }

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
