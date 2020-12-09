import java.io.IOException;
import java.util.*;

public class Property {

    private String ownerName;
    private String address;
    private String postCode;
    private double marketValue;
    private int location;
    private boolean ppr;
    protected Tax tax;

    public Property(String ownerName, String address, String postCode,
                    double marketValue, int location, boolean ppr) throws IOException {
        this.ownerName = ownerName;
        this.address = address;
        this.postCode = postCode;
        this.marketValue = marketValue;
        this.location = location;
        this.ppr = ppr;
        this.tax = new Tax( marketValue, location, ppr);
    }

    public Property(String ownerName, String postCode, double taxDue, double taxOverDue) throws IOException {
        this.address = "";
        this.marketValue = 0;
        this.location = 0;
        this.ppr = true;

        this.ownerName = ownerName;
        this.postCode = postCode;
        this.tax = new Tax();
        this.tax.setTaxDue(taxDue);
        this.tax.setTaxOverDue(taxOverDue);
        this.tax.setAnnualTax(taxDue);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPostCode() {
        return postCode;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public int getLocation() {
        return location;
    }

    public boolean getPpr() {
        return ppr;
    }

    public void payTax() throws IOException {
        WriteToFileMethods fileMethods = new WriteToFileMethods();
        fileMethods.writeToPaymentFile("Owner: " + this.getOwnerName() + ",PostCode: " + this.getPostCode() + ",");
        this.tax.payTax( this.postCode);
    }

    public String locationToString(){
        String s = "";
        switch (location){
            case 0: s+= "City";
                break;
            case 1: s+= "Large Town";
                break;
            case 2: s+= "Small Town";
                break;
            case 3: s+= "Village";
                break;
            case 4: s+= "Countryside";
                break;

        }
        return s;
    }

    public String toString(){
        String s = "Post Code: " + postCode + ", " + tax.toString();
        return s;
    }
    public String toStringCSV(){
        String s =  "Owner:" + this.getOwnerName()  + ",PostCode:" + postCode + "," + tax.toStringCSV();
        return s;
    }

}
