
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    private static boolean command = false;
    private static ArrayList<Animal> allAnimalsListedArray = new ArrayList<>();

    /**
     * FUNCTION 1:
     *
     * Returns animals when called.
     *
     */
    public static ArrayList<Animal> listingAnimals() {
        return allAnimalsListedArray;
    }

    /**
     * FUNCTION 2:
     *
     * Adds an animal to the allAnimalsListedArray when called.
     *
     */
    public static void addingAnimal(Animal animalToAdd) {
        allAnimalsListedArray.add(allAnimalsListedArray.size(), animalToAdd);

        MenuService.success();
    }

    /*
     * FUNCTION 3:
     *
     * Views animal details when selected from the main menu.
     *
     */
    public static Animal viewAnimalDetails(int animalToView) {
        Animal animalToReturn = allAnimalsListedArray.get(animalToView);
        if(animalToView >= allAnimalsListedArray.size() || animalToView < 0) {
                System.out.println("Error, number invalid.");
            }else {
                System.out.println(allAnimalsListedArray.toString());
                //Animal animal = allAnimalsListedArray.get(animalToView);



                /*
                System.out.println("Name: " + animal.getName() +
                        "\nSpecies: " + animal.getSpecies() +
                        "\nBreed: " + animal.getBreed() +
                        "\nDescription: " + animal.getDescription());
                        */
            }
            return animalToReturn;
    }

    /**
     * Function 4:
     *
     * Edit animal from the entered animals.
     *
     */
    public static String editAnimalInformation(){

        if(!allAnimalsListedArray.isEmpty()) {

            // makes sure a valid number is entered assigns to animalToChange
            int animalToChange = MenuService.validNumberEnteredCheck("Please enter the number of animal to change: ");

            if (animalToChange >= allAnimalsListedArray.size() || animalToChange < 0) {
                System.out.println("Error, number invalid.");
                editAnimalInformation();
            } else {
                Animal animal = allAnimalsListedArray.get(animalToChange);

                String animalName = noChangeOfData("Animal Name: ", animal.getName());
                // newAnimal.add(0, animalName);

                String species = noChangeOfData("Species: ", animal.getSpecies());
                // newAnimal.add(1, species);

                String breed = noChangeOfData("Breed (optional): ", animal.getBreed());
                // newAnimal.add(2, breed);

                String description = noChangeOfData("Description: ", animal.getDescription());
                // newAnimal.add(3, description);

                Animal newAnimal = new Animal(animalName, species, breed, description);

                    allAnimalsListedArray.remove(animalToChange);
                    allAnimalsListedArray.add(animalToChange, newAnimal);

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
     *
     */
    public static String deleteAnimalFromMemory(String message) {

        if(!allAnimalsListedArray.isEmpty()) {
            // makes sure a valid number is entered assigns to animalToChange
            int animalToDelete = MenuService.validNumberEnteredCheck(message);

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



    public static void listingAnimalDetails(ArrayList<String> animals, int animalNumber) {
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