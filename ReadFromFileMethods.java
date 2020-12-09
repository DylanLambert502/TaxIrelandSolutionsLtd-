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
        StringBuilder s = new StringBuilder();
        while ((line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if (tempArr[1].contains(choice) && tempArr.length == 4){
                s.append(tempArr[2]).append(", ").append(tempArr[3]).append("\n");
            }
        }
        br2.reset();
        return s.toString();
    }

    public String getPaymentForAYear(String year){



        return year;
    }
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

    public String getPaymentsForAnOwner( String owner) throws IOException {
        br2.mark(100000);
        String line = "";
        StringBuilder s = new StringBuilder();
        while( (line = br2.readLine() ) != null){
            String[] tempArr = line.split(",");
            if( line.contains(owner) && tempArr.length == 4 ){
                s.append(tempArr[2]).append(", ").append(tempArr[3]).append("\n");
            }
        }
        br2.reset();
        return s.toString();
    }

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
