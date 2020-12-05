import java.util.Scanner;

/**
 * Menu which the Dept of Environment Employees will be using
 */
public class DeptOfEnvironmentMenu {

    private Scanner keyboard;

    public DeptOfEnvironmentMenu(){
        keyboard = new Scanner( System.in );
    }

    public void run(){
        boolean more = true;
        DeptOfEnvironmentManager manager = new DeptOfEnvironmentManager();
        /**
         * In the final version I think that
         */

        
        
        while(more){
            System.out.println("1) Get Tax Payment Data for a property, ");
            int command = keyboard.nextInt();
            if (command == 1){
                if(manager.getPropertyOwners().size()==0)
                    System.out.println("No Property Owners.");
                else {
                    System.out.println("Choose the owner of property in question: \n");
                    for(Owner o: manager.getPropertyOwners()){
                        System.out.println(o.getName());
                    }
                    String choice = keyboard.nextLine();
                    for (Owner o: manager.getPropertyOwners()){
                        if(choice.equalsIgnoreCase( o.getName() ) ){
                            System.out.println("Choose the property in question: \n");
                            for(Property p: o.getProperties()) {
                                System.out.println(p.getPostCode());
                            }
                            String choice2 = keyboard.nextLine();
                            for (Property p: o.getProperties()){
                                if(choice2.equalsIgnoreCase( p.getPostCode() ) ){
                                    System.out.print(p.tax.toString());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
