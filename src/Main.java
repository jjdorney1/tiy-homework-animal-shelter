// import java.io.File;
import java.io.IOException;
// import java.nio.file.*;
import java.util.ArrayList;

// import com.google.gson.Gson;

/**
 * Created by jeffreydorney on 8/19/16.
 * Version 1.0 Completed 8/31/16 √
 * Version 1.0.1 - some progress made to 1.1
 * Version 1.1 Completed 9/1/16 √
 * Version 1.1.x - some tweaks and progress on 1.2
 * Version 1.2 Completed ?/?/16 x
 * Version 1.3 Completed ?/?/16 x
 * Version 1.4 Completed ?/?/16 x
 */
public class Main {
    public static void main(String[] args) throws IOException {

        // creates a new animal repository from "animals.json"
        AnimalRepository animalRepository = new AnimalRepository("animals.json");

        AnimalsService animalsService = new AnimalsService(animalRepository);

        // create a new menu service
        MenuService menuService = new MenuService();


        while (true) {
            int action = menuService.mainMenuPrompt();

            if (action == MenuService.LIST_ANIMALS) {

                // creates new ArrayList of Animal, calls on listing animals in animal service
                ArrayList<Animal> animals = animalsService.listingAnimals();

                // calls on animalListingDisplay passing in the created ArrayList
                menuService.animalListingDisplay(animals);

            } else if (action == MenuService.CREATE_ANIMAL) {

                // creates new animal and calls on menu service to collect data
                Animal animal = menuService.createAnAnimal();

                // passes data over to animalRepository to add to the arraylist
                animalRepository.addingAnimal(animal);


                // VIEW ANIMAL SELECTION
            } else if (action == MenuService.VIEW_ANIMAL) {

                // retrieves the number to be VIEWED
                int animalToReturn = menuService.viewAnimalDetails();

                // sets variable for range check
                int animalsAvailable = animalRepository.allAnimalsListed.size();

                // checks to see if there are any animals
                if (animalsAvailable == 0){
                    menuService.noAnimals();

                    // checks to make sure it's in range
                } else if (animalToReturn >= animalsAvailable || animalToReturn < 0 ) {
                    menuService.invalidSelection();

                } else {

                    // if valid it will return it
                    Animal animalToView = animalRepository.viewAnimalDetails(animalToReturn);
                    menuService.printAnimalDetails(animalToView);
                }

                // EDIT ANIMAL SELECTION
            } else if (action == MenuService.EDIT_ANIMAL) {

                // retrieves the number to be EDITED
                int animalToEdit = menuService.viewAnimalDetails();

                // checks that number to make sure it's valid
                if (animalRepository.allAnimalsListed.isEmpty()) {
                    menuService.noAnimals();
                } else if (animalToEdit >= animalsService.listingAnimals().size()) {
                    menuService.invalidSelection();
                } else {

                    // if valid it will edit it
                    animalsService.editAnimalInformation(animalToEdit);
                }


                // DELETE ANIMAL SELECTION
            } else if (action == MenuService.DELETE_ANIMAL) {

                // retrieves the number to be DELETED
                int animalToDelete = menuService.viewAnimalDetails();

                // checks that number to make sure it's valid
                if (animalToDelete < 0) {
                    menuService.noAnimals();
                } else if (animalToDelete >= animalsService.listingAnimals().size()) {
                    menuService.invalidSelection();
                } else {

                    // if valid it will edit it
                    animalsService.deleteAnimalFromMemory(animalToDelete);
                }

            } else if (action == MenuService.QUIT) {
                if (menuService.quitMenu("Are you sure you want to quit? (Yes/No)"))
                    break;

            } else if (action == MenuService.QUICK_EXIT) {
                break;
            } else {
                menuService.invalidSelection();
            }
        }

    }

}
