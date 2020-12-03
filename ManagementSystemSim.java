import java.time.LocalDate;
import java.util.Scanner;

public class ManagementSystemSim {

    public static void main(String[] args ){
        Scanner keyboard = new Scanner( System.in);
        PropertyOwnerMenu ownerMenu = new PropertyOwnerMenu();
        DeptOfEnvironmentMenu deptMenu = new DeptOfEnvironmentMenu();

        System.out.println("Are you a 1) Property Owner or a 2) Dept of Environment Manager");
        int menuChoice = keyboard.nextInt();
        if( menuChoice == 1){
            ownerMenu.run();
        } else if (menuChoice == 2){
            deptMenu.run();
        }
    }
}
