
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    static ArrayList<Integer> allAnimalsListedArray = new ArrayList<>();
    static HashMap<Integer, ArrayList<String>> allAnimalsListedMap = new HashMap<>();

    /**
     * FUNCTION 1:
     *
     * Lists animals when selected from the main menu.
     *
     */
    public static String listingAnimals(){

        System.out.println("\n-*- List of Animals -*-\n");

        if(allAnimalsListedMap.isEmpty()){
            System.out.println("No animals entered yet,\nplease create an animal.");
        } else {
            for (int x = 1; x <= allAnimalsListedMap.size(); x++) {
                ArrayList<String> currentAnimal = new ArrayList<>();
                currentAnimal = allAnimalsListedMap.get(x);
                System.out.print(x + ".) " + currentAnimal.get(0) + " " + currentAnimal.get(1) + "\n");

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
        "(Press enter to confirm your information.)\n\n");

        System.out.print("Animal Name: ");
        String animalName = scanner.nextLine();
        newAnimal.add(0, animalName);

        System.out.print("Species: ");
        String species = scanner.nextLine();
        newAnimal.add(1, species);

        System.out.print("Breed (optional): ");
        String breed = scanner.nextLine();
        newAnimal.add(2, breed);

        System.out.print("Description: ");
        String description = scanner.nextLine();
        newAnimal.add(3, description);

        //allAnimalsListedArray.add(allAnimalsListedArray.size()+1, animalName);
        allAnimalsListedMap.put(allAnimalsListedMap.size()+1, newAnimal);

        return "\n\nSuccess! The animal has been created,\n" +
                "and added to the database.\n";
    }

    /**
     * FUNCTION 3:
     *
     * Views animal details when selected from the main menu.
     *
     */
    public static ArrayList<String> viewAnimalDetails() {
        int inputOption = validNumberEnteredCheck("What is the numeric ID of the animal you want to view?: ");
        ArrayList<String> test = allAnimalsListedMap.get(inputOption);


        /*
        if(allAnimalsListedMap.get(inputOption)) {
            System.out.println(allAnimalsListedMap.get(inputOption));
        } else {
            System.out.println("Not a valid selection. Returning to menu.");
        }
        */

        return test;
    }


    /**
     * Function 4:
     *
     * Edit animal from the entered animals.
     */
    public static String editAnimalInformation(){

        // new scanner object
        Scanner scanner = new Scanner(System.in);

        // makes sure a valid number is entered
        int animalToChange =  validNumberEnteredCheck(scanner.toString());

        // test print of entered number
        System.out.println(animalToChange + " animal to change");
        return "Animal successfully edited.";
    }


    /**
     * Function 5:
     *
     * Delete animal from the entered animals.
     * @param animalToDelete
     * @return
     */
    public String deleteAnimalFromMemory(int animalToDelete){
        if(!allAnimalsListedMap.containsValue(animalToDelete)){
            return "Invalid selection, animal does not exist.";
        } else {
            allAnimalsListedMap.remove(animalToDelete);
            return "Animal has been deleted.";
        }

    }

    private static int validNumberEnteredCheck(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch(Exception e){
            System.out.println("\nPlease provide a valid animal selection.\n");

            value = validNumberEnteredCheck(message);
        }

        return value;

        /*
        if(allAnimalsListed.containsValue(value)) {
            System.out.println("The " + value + " number is valid.");
            return value;
        } else {
            System.out.println("The " + value + " number is NOT valid.");
            return value;
        }
        */
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