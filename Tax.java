import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Tax{

    protected double taxDue;
    public double annualTax;
    protected double taxOverDue;
    protected int year;
    private int yearsOverDue;
    private ArrayList<BalancingStatement> statements; //1 balancing statement per year
    private WriteToFileMethods writeToFileMethods = new WriteToFileMethods();
    private ReadFromFileMethods readFromFileMethods = new ReadFromFileMethods();

    public Tax() throws IOException {
        statements = new ArrayList<BalancingStatement>();
        statements.add( new BalancingStatement( year, taxDue ) );
    }

    public Tax(double MarketValue, int Location, boolean ppr) throws IOException {
        this.taxDue = calcAnnualTax(MarketValue, Location, ppr);
        this.annualTax = calcAnnualTax(MarketValue, Location, ppr);
        this.statements = new ArrayList<BalancingStatement>();
        this.statements.add( new BalancingStatement( year, taxDue ) );
        this.year =Year.now().getValue();
    }

    public void setTaxDue(double taxDue) {
        this.taxDue = taxDue;
    }

    public void setTaxOverDue(double taxOverDue) {
        this.taxOverDue = taxOverDue;
    }

    public void setAnnualTax(double annualTax) {
        this.annualTax = annualTax;
    }

    public double calcAnnualTax(double MarketValue, int Location, boolean ppr) {
        double annualTax = 100;

        if ( MarketValue <= 150000) {
            annualTax += 0;
        } else if  ( MarketValue > 150000 && MarketValue <= 400000) {
            annualTax += MarketValue * .0001;
        } else if  ( MarketValue > 400000 && MarketValue <= 650000) {
            annualTax += MarketValue * .0002;
        } else if  ( MarketValue > 650000) {
            annualTax += MarketValue * .0004;
        }

        switch (Location) {
            case 0: annualTax += 100;
                break;
            case 1: annualTax += 80;
                break;
            case 2: annualTax += 60;
                break;
            case 3: annualTax += 50;
                break;
            case 4: annualTax += 25;
                break;
        }

        if ( ppr == false ) {
            annualTax += 100;
        }
        return annualTax;
    }

    public double getTaxDue() {
        return taxDue;
    }

    public double getTaxOverDue(){
        return taxOverDue;
    }

    public ArrayList<BalancingStatement> getStatements() {
        return statements;
    }

    public void payTax( String postCode) throws IOException {
        if(taxDue == 0){
            System.out.println("You're tax is all paid up on this property");
            writeToFileMethods.writeToPaymentFile("\n");
        }else if (taxOverDue != 0){
            System.out.println("Press 0 if you would like to just pay your overdue tax and 1 if you wish to pay all due and overdue");
            Scanner keyboard = new Scanner( System.in);
            int choice = keyboard.nextInt();
            switch(choice){
                case 0: System.out.println("You have paid €" + taxOverDue + " worth of overdue tax on this property");
                    statements.get(statements.size() - 1).addPayment( new Payment(taxOverDue));
                    writeToFileMethods.writeToPaymentFile(new Payment(taxOverDue).CSVToString() + "\n");
                    taxOverDue = 0;
                    yearsOverDue = 0;
                    break;
                case 1: System.out.println("You have paid €" + taxOverDue + " worth of overdue tax on this property");
                    System.out.println("You have also paid €" + taxDue + " worth of tax due on this property");
                    statements.get(statements.size() -1).addPayment( new Payment(taxOverDue + taxDue));
                    writeToFileMethods.writeToPaymentFile(new Payment(taxOverDue + taxDue).CSVToString() + "\n");
                    taxOverDue = 0;
                    yearsOverDue = 0;
                    taxDue = 0;
            }
        } else {
            System.out.println("You have paid €" + taxDue + " worth of tax on this property");
            statements.get(statements.size() -1).addPayment( new Payment(taxDue));
            writeToFileMethods.writeToPaymentFile(new Payment(taxDue).CSVToString() + "\n");
            taxDue = 0;
        }
    }

    public void taxDay(){
        if( LocalDate.now().getYear() > this.year){
            year++;
            if( taxDue == 0 ){
                taxOverDue = 0;
                taxDue = annualTax;
            } else if( taxDue !=0 && taxOverDue == 0) {
                yearsOverDue++;
                taxOverDue = taxDue;
                taxDue = annualTax * 1.07;
            } else if( taxDue !=0 && taxOverDue !=0 ){
                yearsOverDue++;
                taxOverDue = taxOverDue + taxDue;
                taxDue = annualTax * Math.pow(1.07, yearsOverDue);
            }
            statements.add( new BalancingStatement( year, taxDue ));
        }
    }

    public String toString(){
        return String.format( "Tax Due: €%.2f, Tax OverDue: €%.2f",
                taxDue, taxOverDue  );
    }
    public String toStringCSV(){
        String csvTax = "Tax Due:" +taxDue +",Tax OverDue:" + taxOverDue;
        return csvTax;

    }
}
