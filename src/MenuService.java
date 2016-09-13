import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

/**
 * Menu Service to output data and collect information from user.
 * Created by jeffreydorney on 8/19/16.
 */

public class MenuService {

    final static int QUICK_EXIT = 0;
    final static int CREATE_ANIMAL = 1;
    final static int MANAGE_ANIMAL = 2;
    final static int LIST_ANIMALS = 4;
    final static int VIEW_ANIMAL = 5;
    final static int MANAGE_ANIMAL_TYPE = 3;
    final static int EDIT_ANIMAL = 1;
    final static int DELETE_ANIMAL = 2;
    final static int MAIN_MENU_QUIT = 4;
    final static int QUIT = 6;
    final static int ADD_NOTE = 3; // temporary object for notes

    private static int animalToView = 0;

    private static boolean command = false; // command for quitMenu function

    private AnimalRepository animalRepository = new AnimalRepository("animals.json");
    // private AnimalsService animalsService = new AnimalsService(animalRepository);

    public MenuService() throws IOException, SQLException {}

    // prompt for the main menu
    public int mainMenuPrompt() {

        System.out.println("\n-*- Main Menu -*-\n" +
                "\n" +
                "1.)\tCreate An Animal\n" +
                "2.)\tManage An Animal\n" +
                "3.)\tManage Animal Types\n" +
                "4.)\tQuit\n");

        // runs the waitForInput method to get selection
        return waitForUserMenuInput("Please choose an option from the list above:");
    }

    public int manageAnimalMenuPrompt() {

        System.out.println("\n-*- Manage Animal Menu -*-\n" +
                "\n" +
                "1.)\tEdit An Animal\n" +
                "2.)\tDelete An Animal\n" +
                "3.)\tAdd A Note\n*\n" +
                "4.)\tList Animals\n" +
                "5.)\tView Animal Details\n" +
                "6.)\tMain Menu\n");

        // runs the waitForUserMenuInput method to get selection
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

    String hasStringCheck(String message, boolean required) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.isEmpty() && required) {
            System.out.println("Invalid entry! Please enter requested information.\n");
            input = hasStringCheck(message, required);
        }
        return input;
    }

    String hasStringCheck(String message, String previousData) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return previousData;
        } else return input;
    }

    int validNumberEnteredCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int value;

        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Invalid entry, please provide valid input.");
            value = validNumberEnteredCheck(message);
        }
        return value;
    }

    public Animal createAnAnimal() throws IOException {

        // printed request for info
        System.out.println("\n-*- Animal Creation -*-\n");
        System.out.println("Please enter the following information:\n");

        // gathers the name, species, breed (optional), and description
        String name = hasStringCheck("Animal Name: ", true);
        String species = hasStringCheck("Species: ", true);
        String breed = hasStringCheck("Breed (optional): ", false);
        String description = hasStringCheck("Description: ", true);
        // animalRepository.addAnimalNote(0, date());

        // returns the animal while applying all the gathered data
        return new Animal(name, species, breed, description);

    }

    // collects the number of the animal to view
    public int viewAnimalDetails() {
        if (animalRepository.noAnimalsEntered()) {
            animalToView = -1;
        } else {
            animalToView = validNumberEnteredCheck("What is the numeric ID of the animal? ");
        }
        return animalToView;
    }

    // outputs animal details
    public void printAnimalDetails(Animal animal){
        System.out.println(animal.toString());
    }

    // checks to see if the input is YES or NO
    public static boolean checkYesNoInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.toUpperCase().equals("YES")) {
            command = true;
        } else if (input.toUpperCase().equals("NO")) {
            command = false;
        } else {
            //invalidSelection();
            System.out.println("Input Error: please enter a valid response.");
            checkYesNoInput(message);
        }
        return command;
    }

    public Animal editAnimal(Animal animal){
        boolean changeAnimalConfirm = checkYesNoInput("Are you sure you want to edit this animal?");

        System.out.println("\n-*- Edit An Animal -*-\n");

        if (changeAnimalConfirm){

            String newAnimalName = hasStringCheck(String.format("Name [%s]:", animal.getName()), animal.getName());
            String newSpecies = hasStringCheck(String.format("Species [%s]:", animal.getSpecies()), animal.getSpecies());
            String newBreed = hasStringCheck(String.format("Breed (Optional) [%s]:", animal.getBreed()), animal.getBreed());
            String newDescription = hasStringCheck(String.format("Description (Optional) [%s]:", animal.getDescription()), animal.getDescription());

            success();

            return new Animal(newAnimalName, newSpecies, newBreed, newDescription);

        } else {
            returningToMainMenu();
            return animal;
        }

    }

    public void listingAllAnimals() throws SQLException {
        ResultSet resultSet = this.animalRepository.listAnimals();

        System.out.println("\n-*- All Animals -*-\n");

        while(resultSet.next()){
            System.out.printf("%s.)\t%s\t%s\n",
                    resultSet.getString("animalid"),
                    resultSet.getString("animalname"),
                    resultSet.getString("animaltype"));
        }
    }

    //                                     //
    // *** Basic Error/Status Messages *** //
    //                                     //

    // called when no animals are present
    void noAnimalsEnteredError(){
        System.out.println("No animals entered yet.");
    }

    // called when returning to main menu
    void returningToMainMenu(){
        System.out.println("Returning you to the menu.");
    }

    // called when process completes successfully
    void success() {
        System.out.println("Success!");
    }

    // called when the selection is invalid
    void invalidSelection() {
        System.out.println("Invalid selection.");
    }

    // called when operation is canceled
    public void cancelled() {
        System.out.println("Operation canceled.");
    }
}
