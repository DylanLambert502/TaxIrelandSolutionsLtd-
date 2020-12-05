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
    double marketValue, int location, boolean ppr) {
        this.ownerName = ownerName;
        this.address = address;
        this.postCode = postCode;
        this.marketValue = marketValue;
        this.location = location;
        this.ppr = ppr;
        this.tax = new Tax( marketValue, location, ppr);
    }

    public String getOwner() {
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
        String s = "Address: " + address + ", Post Code: " + postCode + ", Market Value: €" + marketValue + "," +
            " Location: " + this.locationToString() + ", Principal Private Residence? " + ppr +",\n" + tax.toString();
        return s;
    }

}
