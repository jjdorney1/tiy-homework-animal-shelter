import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class MenuService {

    final static int QUICK_EXIT = 0;
    final static int LIST_ANIMALS = 1;
    final static int CREATE_ANIMAL = 2;
    final static int VIEW_ANIMAL = 3;
    final static int EDIT_ANIMAL = 4;
    final static int DELETE_ANIMAL = 5;
    final static int QUIT = 6;
    private static int animalToView = 0;

    // command for quitMenu function
    private static boolean command = false;

    private AnimalRepository animalRepository = new AnimalRepository("animals.json");
    private AnimalsService animalsService = new AnimalsService(animalRepository);

    public MenuService() throws IOException {
    }

    // called when no animals are present
    void noAnimals() {
        System.out.println("No animals entered.");
    }

    // called when the selection is invalid
    void invalidSelection() {
        System.out.println("Invalid selection.");
    }

    // prompt for the main menu
    public int mainMenuPrompt() {

        System.out.println("\n-*- Main Menu -*-\n" +
                "\n" +
                "1.)\tList animals\n" +
                "2.)\tCreate an animal\n" +
                "3.)\tView animal details\n" +
                "4.)\tEdit an animal\n" +
                "5.)\tDelete an animal\n" +
                "6.)\tQuit\n");

        // runs the waitForInput method to get selection
        return waitForUserMenuInput("Please choose an option from the list above:");
    }

    public int waitForUserMenuInput(String message) {

        System.out.println(message);        // prints out input message
        Scanner scanner = new Scanner(System.in); // new scanner for input
        String input = scanner.nextLine();  // sets the input to the number entered

        int value; // the input value
        try {
            value = Integer.parseInt(input); // checks the input to see if it's an int

        } catch (Exception e) {
            System.out.println("Please provide a valid menu selection."); // runs if input isn't an int

            value = waitForUserMenuInput(message); // re-runs the method to check again for int
        }
        return value;
    }

    public void success() {
        System.out.println("Success!");
    }

    public boolean quitMenu(String message) {

        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();

        if (inputValue.toUpperCase().equals("YES")) {
            System.out.println("\nThanks for using this program!");
            command = true;
        } else if (inputValue.toUpperCase().equals("NO")) {
            command = false;
        } else {
            System.out.println("\nInput Error: please enter a valid response.\n");
            quitMenu(message);
        }

        return command;
    }

    String hasStringCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Invalid entry! Please enter requested information.\n");
            input = hasStringCheck(message);
        }
        return input;
    }

    int validNumberEnteredCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int value;

        try {
            value = Integer.parseInt(input) - 1;
        } catch (Exception e) {
            System.out.println("Invalid entry, please provide valid input.");
            value = validNumberEnteredCheck(message);
        }
        return value;
    }

    /**
     * Displays the animals that have been entered into the program.
     *
     * @param animals
     */
    public void animalListingDisplay(ArrayList<Animal> animals) {

        if (animals.isEmpty()) {
            System.out.println("No animals entered yet, please create an animal.");
        } else {
            System.out.println("\n-*- List of Animals -*-\n");

            for (Animal animal : animals) {
                System.out.println((animals.indexOf(animal) + 1) + ".)\t" + animal.getName() + "\t" + animal.getSpecies());
            }
        }
    }

    public Animal createAnAnimal() {

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

    public int viewAnimalDetails() {
        if (animalRepository.listingAnimals().isEmpty()) {
            animalToView = -1;
        } else {
            animalToView = validNumberEnteredCheck("What is the numeric ID of the animal? ");
        }
        return animalToView;
    }

    public void printAnimalDetails(Animal animal){
        System.out.println(animal.toString());
    }

    public static boolean checkYesNoInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.toUpperCase().equals("YES")) {
            command = true;
            return command;
        } else if (input.toUpperCase().equals("NO")) {
            command = false;
            return command;
        } else {
            //invalidSelection();
            checkYesNoInput(message);
        }
        return command;
    }
}
