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
                System.out.println("Choose owner of property in question: \n");
            }
        }
    }
}
