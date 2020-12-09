import java.io.*;
import java.util.ArrayList;

public class ReadFromFileMethods {
    private File file1 = new File("Properties.csv");
    private BufferedReader br1 = new BufferedReader(new FileReader( file1));

    private File file2 = new File("Payments.csv");
    private BufferedReader br2 = new BufferedReader( new FileReader(file2) );


    public ReadFromFileMethods() throws IOException {
    }

    public String getPostCodes() throws IOException {
        br1.mark(100000);
        String line = "";
        String s = "";
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            s += tempArr[1] + "\n";
        }
        br1.reset();
        return s;
    }

    public String getPaymentsForProperty(String choice ) throws IOException {
        br2.mark(100000);
        String line = "";
        String s = "";
        while ((line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if (tempArr[1].contains(choice) && tempArr.length == 4){
                s += tempArr[2] + ", " + tempArr[3] + "\n" ;
            }
        }
        br2.reset();
        if ( s.equals("")){
            return "no payments found";
        } else return s;
    }

    public String getOwnerNames() throws IOException {
        br1.mark(100000);
        String line = "";
        String s = "";
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( !s.contains( tempArr[0] ) ){
                s += tempArr[0] + "\n";
            }
        }
        br1.reset();
        return s;
    }

    public String getOwnersProperties(String ownerName) throws IOException {
        br1.mark(100000);
        String line = "";
        String s = "";
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( line.contains(ownerName) ){
                s += line + "\n";
            }
        }
        br1.reset();
        return s;
    }

    public String getPaymentsForAnOwner( String owner) throws IOException {
        br2.mark(100000);
        String line = "";
        String s = "";
        while( (line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( line.contains(owner) && tempArr.length == 4 ){
                s += tempArr[1] + ", " + tempArr[2] + ", " + tempArr[3] + "\n";
            }
        }
        br2.reset();
        return s;
    }

    public String getAllAreaCodes() throws IOException{
        br1.mark(100000);
        String line = "";
        String s = "";
        while( (line = br1.readLine() ) != null){
            String[] tempArr = line.split(",");
            String[] getAreaCodes = tempArr[1].split(":| ");
            if( !s.contains( getAreaCodes[1] ) ){
                s += getAreaCodes[1] + "\n";
            }
        }
        br1.reset();
        return s;
    }

    public String getAllOverDueTax() throws IOException {
        br1.mark(100000);
        String line = "";
        String s = "";
        while ((line = br1.readLine()) != null) {
            String[] tempArr = line.split(",");
            if ( !line.contains("Tax OverDue:0.0") )
                s += line + "\n";
        }
        br1.reset();
        return s;
    }

    public String getOverDueTaxWithinAnArea( String areaCode ) throws IOException {
        br1.mark(100000);
        String line = "";
        String s = "";
        while ((line = br1.readLine()) != null) {
            String[] tempArr = line.split(",");
            if ( !line.contains("Tax OverDue:0.0") & line.contains(areaCode))
                s += line;
        }
        br1.reset();
        return s;
    }

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

    public void updateThePropertiesFileOnTaxDay() throws IOException{

    }


}
