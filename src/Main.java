// import java.io.File;
// import java.nio.file.*;
// import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jeffreydorney on 8/19/16.
 * Version 1.0 Completed 8/31/16 √
 * Version 1.0.1 - some progress made to 1.1
 * Version 1.1 Completed 9/1/16 √
 * Version 1.1.x - some tweaks and progress on 1.2
 * Version 1.2 Completed 9/7/16 √
 * Version 1.2.x - streamlining code for use with 1.4
 * Version 1.3 Completed ?/?/16 x
 * Version 1.4 Completed ?/?/16 x
 */

public class Main {
    public static void main(String[] args) throws IOException {

        // creates a new animal repository from "animals.json"
        AnimalRepository animalRepository = new AnimalRepository("animals.json");

        // creates a new animal service from animal repository
        AnimalsService animalsService = new AnimalsService(animalRepository);

        // create a new menu service for functions
        MenuService menuService = new MenuService();

        while (true) {
            int action = menuService.mainMenuPrompt();

            // CREATE ANIMAL SELECTION
            if (action == MenuService.CREATE_ANIMAL /* Option 1 */) {

                // creates new animal and calls on menu service to collect data
                Animal animal = menuService.createAnAnimal();

                // passes data over to animalRepository to add to the array list
                animalsService.addingAnimal(animal);
                menuService.success();

                // MANAGE ANIMAL TYPE SELECTION
            } else if (action == MenuService.MANAGE_ANIMAL_TYPE /* Option 3 */) {

                // *** TEMPORARY OUTPUT *** //
                menuService.invalidSelection();

                // EDIT ANIMAL SELECTION
            } else if (action == MenuService.MAIN_MENU_QUIT /* Option 4 */) {

                // checks whether user wants to quit or continue running program
                if (MenuService.checkYesNoInput("Are you sure you want to quit? (Yes/No)")) {
                    break;
                } else menuService.returningToMainMenu();

                // QUICK EXIT FOR EASIER CODING
            } else if (action == MenuService.QUICK_EXIT /* Option 0 */) {
                break;

                //                               //
                // *** MANAGE ANIMAL SECTION *** //
                //                               //

            } else if (action == MenuService.MANAGE_ANIMAL /* Option 2 */) {

                while(true) {

                    int animalAction = menuService.manageAnimalMenuPrompt();

                    if (animalAction == MenuService.LIST_ANIMALS /* Sub Option 2 */) {
                        // creates new ArrayList of Animal, calls on listing animals in animal service
                        ArrayList<Animal> animals = animalsService.listingAnimals();

                        // calls on animalListingDisplay passing in the created ArrayList
                        menuService.animalListingDisplay(animals);

                    } else if (animalAction == MenuService.VIEW_ANIMAL /* Sub Option 3 */) {

                        // retrieves the number to be VIEWED
                        int animalToReturn = menuService.viewAnimalDetails();

                        // sets variable for range check
                        int animalsAvailable = animalRepository.getAnimalsEnteredSize();

                        if (animalRepository.noAnimalsEntered()) {

                            // IF no animals THEN it calls no animals method
                            menuService.noAnimalsEnteredError();

                        } else if (animalToReturn >= animalsAvailable || animalToReturn < 0) {

                            // ELSE IF range is wrong THEN it calls invalid selection method
                            menuService.invalidSelection();

                        } else {

                            // ELSE number is valid it will call VIEW method
                            Animal animalToView = animalRepository.viewAnimalDetails(animalToReturn);
                            menuService.printAnimalDetails(animalToView);
                        }


                        // EDIT FUNCTION DOESN'T PERSIST UNLESS ANOTHER ANIMAL ADDED
                    } else if (animalAction == MenuService.EDIT_ANIMAL /* Sub Option 4 */) {

                        // retrieves the number to be EDITED
                        int animalToEditNumber = menuService.viewAnimalDetails();
                        Animal animalToEdit = animalRepository.viewAnimalDetails(animalToEditNumber);

                        if (animalRepository.noAnimalsEntered()) {

                            // IF no animals THEN it calls no animals method
                            menuService.noAnimalsEnteredError();

                        } else if (animalToEditNumber >= animalsService.listingAnimals().size()) {

                            // ELSE IF range is wrong THEN it calls invalid selection method
                            menuService.invalidSelection();

                        } else {

                            // ELSE number is valid it will call EDIT method
                            animalRepository.editAnimalUpdate(menuService.editAnimal(animalToEdit), animalToEditNumber);
                        }

                        // DELETE ANIMAL SELECTION
                    } else if (animalAction == MenuService.DELETE_ANIMAL /* Sub Option 5 */) {

                        // retrieves the number to be DELETED
                        int animalToDelete = menuService.viewAnimalDetails();

                        if (animalToDelete < 0) {

                            // IF no animals THEN it calls no animals method
                            menuService.noAnimalsEnteredError();

                        } else if (animalToDelete >= animalsService.listingAnimals().size()) {

                            // ELSE IF range is wrong THEN it calls invalid selection method
                            menuService.invalidSelection();

                        } else {

                            // ELSE number is valid it will call DELETE method
                            animalsService.deleteAnimalFromMemory(animalToDelete);
                        }

                    } else if (animalAction == MenuService.ADD_NOTE /* Sub Option 1 */) {
                        System.out.println("Sorry, function not implemented yet.");

                    } else if (animalAction == MenuService.QUIT /* Sub Option 6 */) {

                        // returns you to the main menu
                        menuService.returningToMainMenu();
                        break;

                    } else {

                        // ELSE invalid selection and exits to main menu
                        menuService.invalidSelection();
                        menuService.returningToMainMenu();
                    }
                }
            } else {

                // ELSE invalid selection returned
                menuService.invalidSelection();
            }
        }
    }
}
