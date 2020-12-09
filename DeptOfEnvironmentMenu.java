import java.io.*;
import java.util.Scanner;

/**
 * Menu which the Dept of Environment Employees will be using
 */
public class DeptOfEnvironmentMenu {

    private Scanner keyboard;

    public DeptOfEnvironmentMenu(){
        keyboard = new Scanner( System.in );
    }

    public void run() throws IOException {
        boolean more = true;
        ReadFromFileMethods readFromFileMethods = new ReadFromFileMethods();

        while(more){
            System.out.println("1) Payment Data for a Property, 2) Payments made by an Owner, 3) All Properties with OverDue Tax\n" +
                    "4) Get Property Tax Statistics");
            String command = keyboard.nextLine().toUpperCase();

            if (command.equalsIgnoreCase("1")) {
                System.out.print( readFromFileMethods.getPostCodes() );
                System.out.println("Select property by its Post Code as listed above (CASE SENSITIVE): ");
                String choice = keyboard.nextLine();
                System.out.println( readFromFileMethods.getPaymentsForProperty(choice) );
            }

            else if( command.equalsIgnoreCase("2")){
                System.out.print( readFromFileMethods.getOwnerNames() );
                System.out.println("Choose the owner whos payments you would like to view: ");
                System.out.println("BEWARE, THIS IS CASE SENSITIVE");
                String choice = keyboard.nextLine();
                System.out.println(readFromFileMethods.getPaymentsForAnOwner(choice));
            }

            else if(command.equalsIgnoreCase("3")){
                System.out.println("Would you like to select a specific area? Type 0 for no and 1 for yes");
                String choice = keyboard.nextLine();
                if( choice.equalsIgnoreCase("0")){
                    System.out.print( readFromFileMethods.getAllOverDueTax());
                } else if ( choice.equalsIgnoreCase("1")){
                    System.out.println( readFromFileMethods.getAllAreaCodes() );
                    System.out.println("Choose an area code (CASE SENSITIVE): ");
                    choice = keyboard.nextLine();
                    System.out.print( readFromFileMethods.getOverDueTaxWithinAnArea(choice) );
                }
            }

            else if(command.equalsIgnoreCase("4")){
                System.out.println( readFromFileMethods.getAllAreaCodes());
                System.out.println( "Choose an Area Code from List Above: ");
                String choice = keyboard.nextLine();
                System.out.print( readFromFileMethods.getTaxStatisticsWithinAnArea(choice) );

            }
        }
    }
}
