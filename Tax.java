import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Tax{

    protected double taxDue;
    public double annualTax;
    protected double taxOverDue;
    protected int year = Year.now().getValue();
    private int yearsOverDue;
    private ArrayList<BalancingStatement> statements; //1 balancing statement per year

    public Tax(double MarketValue, int Location, boolean ppr){
        taxDue = calcAnnualTax(MarketValue, Location, ppr);
        annualTax = calcAnnualTax(MarketValue, Location, ppr);
        statements = new ArrayList<BalancingStatement>();
        statements.add( new BalancingStatement( year, taxDue ) );
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

    public void payTaxDue(){
        if(taxDue == 0){
            System.out.println("You're tax is all paid up on this property");
        }else if (taxOverDue != 0){
            System.out.println("Press 0 if you would like to just pay your overdue tax and 1 if you wish to pay all due and overdue");
            Scanner keyboard = new Scanner( System.in);
            int choice = keyboard.nextInt();
            switch(choice){
                case 0: System.out.println("You have paid $" + taxOverDue + " worth of overdue tax on this property");
                    statements.get(statements.size() - 1).addPayment( new Payment(taxOverDue));
                    taxOverDue = 0;
                    yearsOverDue = 0;
                    break;
                case 1: System.out.println("You have paid $" + taxOverDue + " worth of overdue tax on this property");
                    System.out.println("You have also paid $" + taxDue + " worth of tax due on this property");
                    statements.get(statements.size() -1).addPayment( new Payment(taxOverDue + taxDue));
                    taxOverDue = 0;
                    yearsOverDue = 0;
                    taxDue = 0;
            }
        } else {
            System.out.println("You have paid $" + taxDue + " worth of tax on this property");
            statements.get(statements.size() -1).addPayment( new Payment(taxDue));
            taxDue = 0;
        }
    }

    public void taxDay(){
        LocalDate taxDay = LocalDate.of(year+1, 1, 1);
        LocalDate today = LocalDate.now();
        if( today.equals(taxDay)){
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
        String s = ", Annual Tax: " + annualTax + ", Tax Due: " + taxDue + ", Tax OverDue: " + taxOverDue;
        return s;
    }
}
