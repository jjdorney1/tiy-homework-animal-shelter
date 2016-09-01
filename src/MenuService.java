import java.util.ArrayList;
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
    private static int animalToView = 0;

    // command for quitMenu function
    private static boolean command = false;

    // called when no animals are present
    void noAnimals() {
        System.out.println("No animals entered.");
    }

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

        // runs the waitForInput method to get selection
        return waitForUserMenuInput("Please choose an option from the list above:");
    }

    public static int waitForUserMenuInput(String message) {

        System.out.println(message);        // prints out input message
        Scanner scanner = new Scanner(System.in); // new scanner for input
        String input = scanner.nextLine();  // sets the input to the number entered

        int value; // the input value
        try {
            value = Integer.parseInt(input); // checks the input to see if it's an int

        } catch(Exception e){
            System.out.println("\nPlease provide a valid menu selection.\n"); // runs if input isn't an int

            value = waitForUserMenuInput(message); // re-runs the method to check again for int
        }
        return value;
    }

    public static void success(){
        System.out.println("Success!");
    }

    public static boolean quitMenu(String message){

        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

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

    public static String hasStringCheck(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.isEmpty()){
            System.out.println("Invalid entry! Please enter requested information.\n");
            input = hasStringCheck(message);
        }

        return input;
    }

    public static int validNumberEnteredCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input) - 1;

        } catch (Exception e) {
            System.out.println("Invalid entry, please provide a valid selection.");

            value = validNumberEnteredCheck(message);
        }

        return value;
    }

    /**
     * Displays the animals that have been entered into the program.
     * @param animals
     */
    public static void animalListingDisplay(ArrayList<Animal> animals){

        if(animals.isEmpty()){
            System.out.println("No animals entered yet, please create an animal.");
        } else {
            System.out.println("\n-*- List of Animals -*-\n");

            for (Animal animal : animals) {
                System.out.println((animals.indexOf(animal)+1) + ".) " + animal.getName() + " " + animal.getSpecies());
            }
        }
    }

    public static Animal createAnAnimal(){

        // scanner to gather input
        Scanner scanner = new Scanner(System.in);

        // printed request for info
        System.out.println("\nPlease enter the following information:\n");

        // gathers the name, species, breed (optional), and description
        String name = hasStringCheck("Animal Name: ");
        String species = hasStringCheck("Species: ");
        System.out.println("Breed (optional): ");
        String breed = scanner.nextLine();
        String description = hasStringCheck("Description: ");

        // returns the animal while applying all the gathered data
        return new Animal(name, species, breed, description);

    }

    public static int viewAnimalDetails() {


        if(AnimalsService.listingAnimals().isEmpty()){
            animalToView = -1;
        } else {
            animalToView = validNumberEnteredCheck("What is the numeric ID of the animal you want to view?: ");
        }
        return animalToView;
    }
}
