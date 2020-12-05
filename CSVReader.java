import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CSVReader {

    public static final String delimiter = ",";

    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for (String tempStr : tempArr) {
                    System.out.print(tempStr + " ");
                }
                System.out.println();
            }
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void readTax(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Post Code: ");
            String pC = keyboard.nextLine();
            String line = "";

            String[] tempArr;
            while ((line = br.readLine()) != null)
            {
                tempArr = line.split(delimiter);
                if (tempArr[2].equals(" Post Code: " + pC)) {
                        System.out.print(tempArr[1]+tempArr[6]+tempArr[7]+tempArr[8]); }
                    System.out.println();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void readOwnerName(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Owner Name: ");
            String name = keyboard.nextLine();
            String line = "";

            String[] tempArr;
            while ((line = br.readLine()) != null)
            {
                tempArr = line.split(delimiter);
                if (tempArr[0].equals(name)) {
                    System.out.print(tempArr[1]+tempArr[6]+tempArr[7]+tempArr[8]); }
                System.out.println();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // csv file to read
        String csvFile = "/home/default/Documents/cs4013/TaxSolutionsIreland/AllProperties.csv";
        //CSVReader.read(csvFile);
        //CSVReader.readTax(csvFile);
        CSVReader.readOwnerName(csvFile);
    }

}