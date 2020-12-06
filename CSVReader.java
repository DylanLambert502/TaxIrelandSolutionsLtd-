import java.io.*;
import java.util.Scanner;

public class CSVReader {

    public static final String delimiter = ",";


    public static void read(String csvFile){
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Scanner keyboard = new Scanner(System.in);
            System.out.println("1.) Property by Post Code 2.)Properties by Owner 3.)Over due Properties 4.)Property stats by code.");
            System.out.println("Option: ");
            String optionChoice = keyboard.nextLine();
            String line = "";

            if (optionChoice.equals("1")) {
                System.out.println("Post Code: ");
                String pC = keyboard.nextLine();
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(delimiter);
                    if (tempArr[2].equals(pC)) {
                        System.out.print(tempArr[1] +" "+ tempArr[6] +" "+ tempArr[7] +" "+ tempArr[8] + "\n");
                    }
                }
            } else if (optionChoice.equals("2")) {
                System.out.println("Owner Name: ");
                String name = keyboard.nextLine();
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(delimiter);
                    if (tempArr[0].equals(name)) {
                        System.out.print(tempArr[1] +" "+ tempArr[6] +" "+ tempArr[7] +" "+ tempArr[8] +"\n");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

         //Test for file read
    public static void main(String[] args) {
        // csv file to read
        String csvFile = "/home/default/Documents/cs4013/TaxSolutionsIreland/AllProperties.csv";
        CSVReader.read(csvFile);
    }}
