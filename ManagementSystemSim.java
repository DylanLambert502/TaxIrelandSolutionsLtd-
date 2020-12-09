import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The class with the main method which the program will be ran from
 */
public class ManagementSystemSim {

    public static void main(String[] args ) throws IOException {
        Scanner keyboard = new Scanner( System.in );
        PropertyOwnerMenu ownerMenu = new PropertyOwnerMenu();
        DeptOfEnvironmentMenu deptMenu = new DeptOfEnvironmentMenu();

        System.out.println(" Would you like to use our 1) Command Line Interface or 2) GUI");
        int interfaceChoice = keyboard.nextInt();
        if (interfaceChoice == 1) {
            System.out.println("Are you a 1) Property Owner or a 2) Dept of Environment Manager");
            int menuChoice = keyboard.nextInt();
            if (menuChoice == 1) {
                ownerMenu.run();
            } else if (menuChoice == 2) {
                deptMenu.run();
            }
        } else if( interfaceChoice == 2){
                GUI.main(args);
            }
        }
}
