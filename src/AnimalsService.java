
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    // unused COMMAND object
    // private static boolean command = false;

    // private MenuService menuService;

    // creates a new animal repository object to hold animals
    private AnimalRepository animalRepository = new AnimalRepository("animals.json");

    // creates a new array list of animal to store animals
    private ArrayList<Animal> allAnimalsListedArray = new ArrayList<>();

    public AnimalsService(AnimalRepository animalRepository) throws IOException {
        this.animalRepository = animalRepository;
    }

    /**
     * FUNCTION 1:
     * <p>
     * Returns animals when called.
     */
    public ArrayList<Animal> listingAnimals() {
        return animalRepository.listingAnimals();
    }

    /**
     * FUNCTION 2:
     * <p>
     * Adds an animal to the allAnimalsListedArray when called.
     */
    public void addingAnimal(Animal animalToAdd) {
        allAnimalsListedArray.add(animalToAdd);

        // menuService.success();
    }

    /*
     * FUNCTION 3:
     *
     * Views animal details when selected from the main menu.
     *
     */
    public Animal viewAnimalDetails(int animalToView) {
        Animal animalToReturn = allAnimalsListedArray.get(animalToView);
        if (animalToView >= allAnimalsListedArray.size() || animalToView < 0) {
            System.out.println("Error, number invalid.");
        } else {
            System.out.println(allAnimalsListedArray.get(animalToView).toString());
        }
        return animalToReturn;
    }

    /**
     * Function 4:
     * <p>
     * Edit animal from the entered animals.
     */
    public String editAnimalInformation(int animalToEdit) {

        Animal animal = animalRepository.allAnimalsListed.get(animalToEdit);

        String animalName = noChangeOfData("Animal Name: ", animal.getName());
        // newAnimal.add(0, animalName);

        String species = noChangeOfData("Species: ", animal.getSpecies());
        // newAnimal.add(1, species);

        String breed = noChangeOfData("Breed (optional): ", animal.getBreed());
        // newAnimal.add(2, breed);

        String description = noChangeOfData("Description: ", animal.getDescription());
        // newAnimal.add(3, description);

        Animal newAnimal = new Animal(animalName, species, breed, description);

        animalRepository.allAnimalsListed.remove(animalToEdit);
        animalRepository.allAnimalsListed.add(animalToEdit, newAnimal);

        return "Animal successfully edited.";
    }


    /**
     * Function 5:
     * <p>
     * Delete animal from the entered animals.
     *
     * @param animalToDelete
     * @return
     */
    public void deleteAnimalFromMemory(int animalToDelete) throws IOException {

        if (MenuService.checkYesNoInput("Are you sure you want to delete this animal? (Yes/No) ")) {
            System.out.println("Animal successfully deleted.");
            animalRepository.deleteAnimal(animalToDelete);
        } else System.out.println("Animal deletion canceled. Returning to the main menu.");

    }


    /*private static int validNumberEnteredCheck(String message) {
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
   }*/


    public void listingAnimalDetails(ArrayList<String> animals, int animalNumber) {
        System.out.println(animals.get(animalNumber));

    }

    /*
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
    */

    public String noChangeOfData(String message, String currentString) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            input = currentString;
        }
        return input;
    }

}

/**
 * To remember:
 * Given x,
 * When y,
 * Then z
 * <p>
 * TEST FORMAT:
 * <p>
 * Arrange
 * <p>
 * Act
 * <p>
 * Assert
 * <p>
 * TEST FORMAT:
 * <p>
 * Arrange
 * <p>
 * Act
 * <p>
 * Assert
 * <p>
 * TEST FORMAT:
 * <p>
 * Arrange
 * <p>
 * Act
 * <p>
 * Assert
 * <p>
 * TEST FORMAT:
 * <p>
 * Arrange
 * <p>
 * Act
 * <p>
 * Assert
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