import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

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
        FileMethods fileMethods = new FileMethods();

        while (more){
            for ( Property p : owner.getProperties()){  //checks is it tax day.
                p.tax.taxDay();
            }

            System.out.println("1) Register a Property, 2) View Properties and Tax Due, 3) Pay Tax, 4) View Balancing Statements, 5) View Payment History, 6) Quick add (for developer use), Q)uit");
            String command = keyboard.nextLine().toUpperCase();

            if(command.equals("1")){ //Adding a Property
                System.out.println("Address: ");
                String address = keyboard.nextLine();
                System.out.println("PostCode: ");
                String postCode = keyboard.nextLine();
                double marketValue=0;
                do {
                    System.out.println("Estimated Market Value (€): ");
                    marketValue = keyboard.nextDouble();
                    if( marketValue < 0 )
                        System.out.println("Market Value must be Positive Number");
                } while (marketValue < 0);
                System.out.println("Location Category: 0) City, 1) Large Town, 2) Small Town, 3) Village, 4) Countryside");
                int locationCategory = keyboard.nextInt();
                System.out.println("Is this your principal private residence?  1) Yes      0) No");
                boolean ppr = false;
                if (keyboard.nextInt() == 1){
                    ppr = true;
                }
                owner.addProperty( new Property(owner.getName(), address, postCode, marketValue, locationCategory, ppr));
            }

            else if (command.equals("2")){
                if(owner.getProperties().size()==0)
                    System.out.println("No Properties to View.");
                else 
                    for(Property p: owner.getProperties()){
                        System.out.println(p.toString());
                    }
            }

            else if(command.equals("3")){
                if(owner.getProperties().size()==0)
                    System.out.println("No Property Tax to Pay.");
                else
                {
                    System.out.print("Choose property from Postcode: " + "\n");
                    for(Property p: owner.getProperties()) {
                        System.out.println(p.getPostCode());
                    }
                    String choice = keyboard.nextLine();
                    for (Property p: owner.getProperties()){
                        if(choice.equalsIgnoreCase( p.getPostCode() ) ){
                            p.tax.payTaxDue();
                        }
                    }
                }
            }

            else if(command.equals("4")){
                if(owner.getProperties().size()==0)
                    System.out.println("No Property Balancing Statements to View.");
                else
                {
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

            }

            else if(command.equals("5")){
                {
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
            }

            else if(command.equals("6")){
                owner.addProperty( new Property(owner.getName(), "Lisardboula", "v92 y20", 270000, 4, true));
                owner.addProperty( new Property(owner.getName(), "Limmers", "v95 Qk20", 400000, 0, false));
                owner.addProperty( new Property(owner.getName(), "Espana", "Spf50", 1000000, 3, false));
            }

            else if(command.equalsIgnoreCase("Q")) {
                fileMethods.writeAnOwnersPropertiesToFile(owner);
                more = false;
            }
        }
    }

}
