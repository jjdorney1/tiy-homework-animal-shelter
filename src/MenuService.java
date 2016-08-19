import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class MenuService {

    final static int LIST_ANIMALS = 1;
    final static int CREATE_ANIMAL = 2;
    final static int VIEW_ANIMAL = 3;
    final static int EDIT_ANIMAL = 4;
    final static int DELETE_ANIMAL = 5;
    final static int QUIT = 6;
    final static int QUIT_CONFIRM = 1;
    final static int QUIT_CANCEL = 0;



    // prompt for the main menu
    public int mainMenuPrompt() {

        System.out.println("\n-*- Main Menu -*-\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");

        return waitForMenuInput("Please choose an option from the list above:"); // runs the waitForInput method to get selection
    }

    private int waitForMenuInput(String message) {

        System.out.println(message);        // prints out input message
        Scanner scanner = new Scanner(System.in); // new scanner for input
        String input = scanner.nextLine();  // sets the input to the number entered

        int value; // the input value
        try {
            value = Integer.parseInt(input); // checks the input to see if it's an int

        } catch(Exception e){
            System.out.println("\nPlease provide a valid menu selection.\n"); // runs if input isn't an int

            value = waitForMenuInput(message); // re-runs the method to check again for int
        }
        return value;
    }

    public static boolean quitMenu(String message){

        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();
        boolean command = false;

        if(inputValue.toUpperCase().equals("YES")) {
            System.out.println("\nThanks for using this program!");
            command = true;
        } else if(inputValue.toUpperCase().equals("NO")) {
            command = false;
        } else {
            System.out.println("\nInput Error: please enter a valid response.\n");
            quitMenu(message);
        }
        return command;
    }
}
