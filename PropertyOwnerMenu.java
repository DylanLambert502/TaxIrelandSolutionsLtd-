import java.util.Scanner;

public class PropertyOwnerMenu {

    private Scanner keyboard;

    public PropertyOwnerMenu(){
        keyboard = new Scanner(System.in);
    }

    public void run(){
        boolean more = true;
        System.out.println("Owner's name: ");
        String ownerName = keyboard.nextLine();
        Owner owner = new Owner(ownerName);

        while (more){
            for ( Property p : owner.getProperties()){  //checks is it tax day.
                p.tax.taxDay();
            }

            System.out.println("A)dd Properties, S)how Properties, P)ay Tax, V)iew Balancing Statements, L)ook at Payment History, Q)uick add (for developer use)");
            String command = keyboard.nextLine().toUpperCase();

            if(command.equals("A")){
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

            }

            else if (command.equals("S")){
                for(Property p: owner.getProperties()){
                    System.out.println(p.toString());
                }
            }

            else if(command.equals("P")){
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

            else if(command.equals("V")){
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

            else if(command.equals("L")){
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

            else if(command.equals("Q")){
                owner.addProperty( new Property(owner.getName(), "Lisardboula", "v92 y20", 270000, 4, true));
                owner.addProperty( new Property(owner.getName(), "Limmers", "v95 Qk20", 400000, 0, false));
                owner.addProperty( new Property(owner.getName(), "Espana", "Spf50", 1000000, 3, false));
            }



        }
    }


}
