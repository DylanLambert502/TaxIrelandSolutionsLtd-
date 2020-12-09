import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menu which will run when the property owner is using the system
 */

public class PropertyOwnerMenu {

    private Scanner keyboard;

    public PropertyOwnerMenu(){
        keyboard = new Scanner(System.in);
    }

    public void run() throws IOException {
        boolean more = true;
        System.out.println("Owner's name: ");
        String ownerName = keyboard.nextLine();
        Owner owner = new Owner(ownerName);
        WriteToFileMethods writeToFileMethods = new WriteToFileMethods();
        ReadFromFileMethods readFromFileMethods = new ReadFromFileMethods();
        readFromFileMethods.readPropertiesFromFileToOwnersArrayList( owner);

        while (more){
            for ( Property p : owner.getProperties()){  //checks is it tax day.
                p.tax.taxDay();
            }

            System.out.println("1) Register a Properties, 2) Show Properties, 3) Pay Tax, 4) View Balancing Statements, 5) Look at Payment History, 6) Quick add (for developer use), Q)uit");
            String command = keyboard.nextLine().toUpperCase();

            if(command.equals("1")){ //Adding a Property
                System.out.println("Address: ");
                String address = keyboard.nextLine();
                System.out.println("PostCode: ");
                String postCode = keyboard.nextLine();
                System.out.println("Market Value: ");
                double marketValue = keyboard.nextDouble();
                System.out.println("Location Category 0- City, 1- Large Town, 2- Small Town: 3- Village, 4- Countryside");
                int locationCategory = keyboard.nextInt();
                System.out.println("Is this your principal private residence?: Enter 1 if it is and enter 0 if it isn'nt");
                boolean ppr = false;
                if (keyboard.nextInt() == 1){
                    ppr = true;
                }
                owner.addProperty( new Property(owner.getName(), address, postCode, marketValue, locationCategory, ppr));
                writeToFileMethods.writePropertyToFile(new Property(owner.getName(), address, postCode, marketValue, locationCategory, ppr));
            }

            else if (command.equals("2")){
                for(Property p: owner.getProperties()){
                    System.out.println(p.toString());
                }
            }

            else if(command.equals("3")){
                System.out.print("Choose property from Postcode: " + "\n");
                for(Property p: owner.getProperties()) {
                    System.out.println(p.getPostCode());
                }
                String choice = keyboard.nextLine();
                for (Property p: owner.getProperties()){
                    if(choice.equalsIgnoreCase( p.getPostCode() ) ){
                        p.payTax();
                    }
                }
            }

            else if(command.equals("4")){
                System.out.println("Which property's balancing statements would you like to view?");
                for(Property p: owner.getProperties()) {
                    System.out.println(p.getPostCode());
                }
                String pChoice = keyboard.nextLine();
                System.out.println("For what year would you like to view the balancing statement?");
                int yChoice = keyboard.nextInt();

                for(Property p: owner.getProperties()){
                    if( pChoice.equalsIgnoreCase( p.getPostCode())){
                        for (BalancingStatement s : p.tax.getStatements()){
                            if (yChoice == s.getYear()){
                                System.out.print( s.toString() );
                            }
                        }
                    }
                }

            }

            else if(command.equals("5")){
                System.out.println("All Payments");
                System.out.println("-------------");
                for (Property p: owner.getProperties() ){
                    for(BalancingStatement b: p.tax.getStatements()){
                        for( Payment p1: b.getPayments()){
                            System.out.println(p.getPostCode() + ": " + p1.toString());
                        }
                    }
                }
            }

            else if(command.equals("6")){
                owner.addProperty( new Property(owner.getName(), "Lisardboula", "V92 Y20", 270000, 4, true));
                writeToFileMethods.writePropertyToFile(new Property(owner.getName(), "Lisardboula", "V92 Y20", 270000, 4, true));
                owner.addProperty( new Property(owner.getName(), "Limmers", "V95 QK20", 400000, 0, false));
                writeToFileMethods.writePropertyToFile(new Property(owner.getName(), "Limmers", "V95 QK20", 400000, 0, false));
                owner.addProperty( new Property(owner.getName(), "Cork", "V91 FK82", 1000000, 3, false));
                writeToFileMethods.writePropertyToFile(new Property(owner.getName(), "Cork", "V91 FK82", 1000000, 3, false));
            }

            else if(command.equalsIgnoreCase("Q")) {
                writeToFileMethods.PropertiesFR.close();
                writeToFileMethods.PropertiesFR.close();
                more = false;
            }
        }
    }


}
