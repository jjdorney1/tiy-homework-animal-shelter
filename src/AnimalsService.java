
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    private static boolean command = false;
    static ArrayList<ArrayList<String>> allAnimalsListedArray = new ArrayList<>();
    //static HashMap<Integer, ArrayList<String>> allAnimalsListedMap = new HashMap<>();


    /**
     * FUNCTION 1:
     *
     * Lists animals when selected from the main menu.
     *
     */
    public static String listingAnimals() throws IOException {

        System.out.println("\n-*- List of Animals -*-\n");


        if(allAnimalsListedArray.isEmpty()){
            System.out.println("No animals entered yet please create an animal.");
        } else {
            for (ArrayList animal : allAnimalsListedArray) {

                System.out.println((allAnimalsListedArray.indexOf(animal)+1) + ".) " + animal.get(0) + " " + animal.get(1));
            }
        }
        return "\n(Pick option 3 for more details about an animal!)\n";
    }

    /**
     * FUNCTION 2:
     *
     * Creates an animal when selected from the main menu.
     *
     */
    public static String addingAnimal() {
        ArrayList<String> newAnimal = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPlease enter the following information:\n" +
        "(Press enter to confirm the information.)\n\n");

        String animalName = hasStringCheck("Animal Name: ");
        newAnimal.add(0, animalName);

        String species = hasStringCheck("Species: ");
        newAnimal.add(1, species);

        System.out.println("Breed (optional): ");
        String breed = scanner.nextLine();
        newAnimal.add(2, breed);

        String description = hasStringCheck("Description: ");
        newAnimal.add(3, description);

        //allAnimalsListedArray.add(allAnimalsListedArray.size()+1, animalName);
        allAnimalsListedArray.add(allAnimalsListedArray.size(), newAnimal);

        return "\n\nSuccess! The animal has been created,\n" +
                "and added to the database.\n";
    }

    /**
     * FUNCTION 3:
     *
     * Views animal details when selected from the main menu.
     *
     */
    public static void viewAnimalDetails() {
        if(AnimalsService.allAnimalsListedArray.isEmpty()) {
            System.out.println("\nNo animals entered, choose another option.");
        } else {
            int inputOption = validNumberEnteredCheck("What is the numeric ID of the animal you want to view?: ");

            if(inputOption >= allAnimalsListedArray.size() || inputOption < 0) {
                System.out.println("Error, number invalid.");
                viewAnimalDetails();
            }else {
                ArrayList<String> animal = allAnimalsListedArray.get(inputOption);


                System.out.println("Name: " + animal.get(0) +
                        "\nSpecies: " + animal.get(1) +
                        "\nBreed: " + animal.get(2) +
                        "\nDescription: " + animal.get(3));
            }
        }
    }

    /**
     * Function 4:
     *
     * Edit animal from the entered animals.
     */
    public static String editAnimalInformation(){

        if(!allAnimalsListedArray.isEmpty()) {

            ArrayList<String> newAnimal = new ArrayList<>();

            // makes sure a valid number is entered assigns to animalToChange
            int animalToChange = validNumberEnteredCheck("Please enter the number of animal to change: ");

            if (animalToChange >= allAnimalsListedArray.size() || animalToChange < 0) {
                System.out.println("Error, number invalid.");
                editAnimalInformation();
            } else {
                ArrayList<String> animal = allAnimalsListedArray.get(animalToChange);

                String animalName = noChangeOfData("Animal Name: ", animal.get(0));
                newAnimal.add(0, animalName);

                String species = noChangeOfData("Species: ", animal.get(1));
                newAnimal.add(1, species);

                String breed = noChangeOfData("Breed (optional): ", animal.get(2));
                newAnimal.add(2, breed);

                String description = noChangeOfData("Description: ", animal.get(3));
                newAnimal.add(3, description);

                if (newAnimal != animal) {
                    allAnimalsListedArray.remove(animalToChange);
                    allAnimalsListedArray.add(animalToChange, newAnimal);
                }
            }

            return "Animal successfully edited.";
        } else return "No animals present to edit.";
    }


    /**
     * Function 5:
     *
     * Delete animal from the entered animals.
     * @param animalToDelete
     * @return
     */
    public static String deleteAnimalFromMemory(String message) {

        if(!allAnimalsListedArray.isEmpty()) {
            // makes sure a valid number is entered assigns to animalToChange
            int animalToDelete = validNumberEnteredCheck(message);

            // checks to see if the animal to be deleted is NOT in the range of animals in the list
            if (animalToDelete >= allAnimalsListedArray.size() || animalToDelete < 0) {
                System.out.println("Error, number invalid.");
                deleteAnimalFromMemory(message);

            } else {

                if(checkYesNoInput("Are you sure you want to delete this animal? (Yes/No) ")){
                    System.out.print("Animal successfully deleted.");
                    allAnimalsListedArray.remove(animalToDelete);
                } else System.out.print("Animal deletion canceled. Returning to the main menu.");

            }
            return "";
        } else return "Sorry, no animals are present to be deleted.";
    }

    private static int validNumberEnteredCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input) - 1;

        } catch(Exception e){
            System.out.println("\nPlease provide a valid animal selection.\n");

            value = validNumberEnteredCheck(message);
        }

        return value;
    }


    public static void listingAnimalDetails(ArrayList<String> animals, int animalNumber) {
        System.out.println(animals.get(animalNumber));

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

    public static String noChangeOfData(String message, String currentString){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.isEmpty()){
            input = currentString;
        }

        return input;

    }

    public static boolean checkYesNoInput(String message){
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
            System.out.println("Sorry, not a valid choice.");
            checkYesNoInput(message);
        }
        return command;
    }

}

/**
 * To remember:
 * Given x,
 * When y,
 * Then z
 */


/**
* TEST FORMAT:
*
* Arrange
*
* Act
*
* Assert
*/