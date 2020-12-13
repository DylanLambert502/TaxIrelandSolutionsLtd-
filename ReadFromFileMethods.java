import java.io.*;
import java.util.ArrayList;

/**
 * ReadFromFileMethods Class
 */
public class ReadFromFileMethods {
    private File file1 = new File("Properties.csv");
    private BufferedReader br1 = new BufferedReader(new FileReader( file1));

    private File file2 = new File("Payments.csv");
    private BufferedReader br2 = new BufferedReader( new FileReader(file2) );

    /**
     * Default Constructor
     * @throws IOException
     */
    public ReadFromFileMethods() throws IOException {
    }

    /**
     * get the postcodes and return then as a string
     * @return
     * @throws IOException
     */
    public String getPostCodes() throws IOException {
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            s.append(tempArr[1]).append("\n");
        }
        br1.reset();
        return s.toString();
    }

    /**
     * get the payments for a given property and return them.
     * @param choice
     * @return
     * @throws IOException
     */
    public String getPaymentsForProperty(String choice ) throws IOException {
        br2.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while ((line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if (tempArr[1].contains(choice) && tempArr.length == 4){
                s.append(tempArr[2]).append(", ").append(tempArr[3]).append("\n");
            }
        }
        br2.reset();
        if ( s.toString().equals("")){
            return "no payments found";
        } else return s.toString();
    }

    /**
     * returns the owner names in a string
     * @return
     * @throws IOException
     */
    public String getOwnerNames() throws IOException {
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( !s.toString().contains( tempArr[0] ) ){
                s.append(tempArr[0]).append("\n");
            }
        }
        br1.reset();
        return s.toString();
    }

    /**
     * get the properties associated with an owner and return the result in a string
     * @param ownerName
     * @return
     * @throws IOException
     */
    public String getOwnersProperties(String ownerName) throws IOException {
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( line.contains(ownerName) ){
                s.append(line).append("\n");
            }
        }
        br1.reset();
        return s.toString();
    }

    /**
     *  gets payments made ba a owner and return result in a string
     * @param owner
     * @return
     * @throws IOException
     */
    public String getPaymentsForAnOwner( String owner) throws IOException {
        br2.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( line.contains(owner) && tempArr.length == 4 ){
                s.append(tempArr[1]).append(", ").append(tempArr[2]).append(", ").append(tempArr[3]).append("\n");
            }
        }
        br2.reset();
        return s.toString();
    }

    /**
     *  gets all the area codes and return result in a string
     * @return
     * @throws IOException
     */
    public String getAllAreaCodes() throws IOException{
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            String[] getAreaCodes = tempArr[1].split(":| ");
            if( !s.toString().contains( getAreaCodes[1] ) ){
                s.append(getAreaCodes[1]).append("\n");
            }
        }
        br1.reset();
        return s.toString();
    }

    /**
     * gets all the tax over due and return the result in a string
     * @return
     * @throws IOException
     */
    public String getAllOverDueTax() throws IOException {
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while ((line = br1.readLine()) != null) {
            String[] tempArr = line.split(",");
            if ( !line.contains("Tax OverDue:0.0") )
                s.append(line).append("\n");
        }
        br1.reset();
        return s.toString();
    }

    /**
     * Gets all the tax overdue within a set location and returns the result as a string
     * @param areaCode
     * @return
     * @throws IOException
     */
    public String getOverDueTaxWithinAnArea( String areaCode ) throws IOException {
        br1.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while ((line = br1.readLine()) != null) {
            String[] tempArr = line.split(",");
            if ( !line.contains("Tax OverDue:0.0") & line.contains(areaCode))
                s.append(line);
        }
        br1.reset();
        return s.toString();
    }

    /**
     * Calculates the average amount of property tax paid in a give area and returns a result
     * @param rCode
     * @return
     * @throws IOException
     */
    public String getTaxStatisticsWithinAnArea (String rCode ) throws IOException{
        br1.mark(100000);
        br2.mark(100000);
        int numberOfProperties = 0;
        double totalAmountPaid = 0;
        int totalTaxesPaid = 0;
        String line1 = "";
        String line2 = "";

        while( (line1 = br1.readLine()) != null ){
            String[] tempArr = line1.split(",");
            if( line1.contains( rCode ) ){
                numberOfProperties += 1;
            }
        }

        while ( (line2 = br2.readLine() ) != null ){
            String[] tempArr = line2.split(",");
            if( line2.contains( rCode ) ){
                totalTaxesPaid += 1;
                String[] gettingTotalAmountPaid = tempArr[2].split(":");
                totalAmountPaid += Double.parseDouble( gettingTotalAmountPaid[1] );
            }
        }

        String s = "Total Tax Paid: " + totalAmountPaid + "\nTotal properties that paid tax:  " + totalTaxesPaid +
                "\nAverage Tax Paid:  " + (totalAmountPaid/numberOfProperties) + "\n";
        br1.reset();
        br2.reset();
        return s;
    }

    /**
     * reads the properties from the file to the owners ArrayList
     * @param owner
     * @throws IOException
     */
    public void readPropertiesFromFileToOwnersArrayList( Owner owner ) throws IOException {
        br1.mark(100000);
        String line = "";
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( tempArr[0].contains(owner.getName())){
                String[] gettingOwnerName = tempArr[0].split(":");
                String ownerName = gettingOwnerName[1];
                String[] gettingPostCode = tempArr[1].split(":");
                String postCode = gettingPostCode[1];
                String[] gettingTaxDue = tempArr[2].split(":");
                double taxDue = Double.parseDouble(gettingTaxDue[1]);
                String[] gettingTaxOverDue = tempArr[3].split(":");
                double taxOverDue = Double.parseDouble(gettingTaxOverDue[1]);
                owner.addProperty( new Property(ownerName, postCode, taxDue, taxOverDue) );
            }
        }
        br1.reset();
    }

    /**
     * updaters method
     * @throws IOException
     */
    public void updateThePropertiesFileOnTaxDay() throws IOException{

    }


}
