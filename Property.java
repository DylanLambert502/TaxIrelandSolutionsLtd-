import java.io.IOException;
import java.util.*;

public class Property {
    /**
     * Variables declared
     */
    private String ownerName;
    private String address;
    private String postCode;
    private double marketValue;
    private int location;
    private boolean ppr;
    protected Tax tax;

    /**
     * Constructor method that sets parameters
     * @param ownerName
     * @param address
     * @param postCode
     * @param marketValue
     * @param location
     * @param ppr
     * @throws IOException
     */
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

    /**
     * constructor that sets the following parameters
     * @param ownerName
     * @param postCode
     * @param taxDue
     * @param taxOverDue
     * @throws IOException
     */
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

    /**
     * gets the owners name and returns it in a string
     * @return
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * gets the post code and returns it in a string
     * @return
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * get market value of a property and returns the value
     * @return
     */
    public double getMarketValue() {
        return marketValue;
    }

    /**
     * gets a location and returns the result
     * @return
     */
    public int getLocation() {
        return location;
    }

    /**
     * boolean used for PPR true owner occupier, false not owner occupier
     * @return
     */
    public boolean getPpr() {
        return ppr;
    }

    /**
     * a method that lets tax be paid and the result stored in the file
     * @throws IOException
     */
    public void payTax() throws IOException {
        WriteToFileMethods fileMethods = new WriteToFileMethods();
        fileMethods.writeToPaymentFile("Owner: " + this.getOwnerName() + ",PostCode: " + this.getPostCode() + ",");
        this.tax.payTax( this.postCode);
    }

    /**
     *  used to get the picked location converted to a string
     * @return
     */
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

    /**
     * toString, overRides main to string method
     * @return
     */
    public String toString(){
        String s = "Post Code: " + postCode + ", " + tax.toString();
        return s;
    }

    /**
     * CSV to string method
     * @return
     */
    public String toStringCSV(){
        String s =  "Owner:" + this.getOwnerName()  + ",PostCode:" + postCode + "," + tax.toStringCSV();
        return s;
    }

}
