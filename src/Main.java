import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jeffreydorney on 8/19/16.
 * Version 1.0 Completed 8/31/16 √
 * Version 1.1 Completed ?/?/16 x
 * Version 1.2 Completed ?/?/16 x
 * Version 1.3 Completed ?/?/16 x
 * Version 1.4 Completed ?/?/16 x
 */
public class Main {
    public static void main(String[] args) throws IOException {

        MenuService menuService = new MenuService();

        while(true) {
            int action = menuService.mainMenuPrompt();

            if(action == MenuService.LIST_ANIMALS){

                // creates new ArrayList of Animal, calls on listing animals in animal service
                ArrayList<Animal> animals = AnimalsService.listingAnimals();

                // calls on animalListingDisplay passing in the created ArrayList
                MenuService.animalListingDisplay(animals);

            } else if(action == MenuService.CREATE_ANIMAL){

                // creates new animal and calls on menu service to collect data
                Animal animal = MenuService.createAnAnimal();

                // passes data over to animalsservice to add to the arraylist
                AnimalsService.addingAnimal(animal);

            } else if(action == MenuService.VIEW_ANIMAL){
                int animalToReturn = MenuService.viewAnimalDetails();

                if(animalToReturn < 0){
                    menuService.noAnimals();
                } else if(animalToReturn >= AnimalsService.listingAnimals().size()) {
                    menuService.invalidSelection();
                } else {
                    AnimalsService.viewAnimalDetails(animalToReturn);
                }

            } else if(action == MenuService.EDIT_ANIMAL){
                int animalToEdit = MenuService.viewAnimalDetails();

                if(animalToEdit < 0) {
                    menuService.noAnimals();
                } else if(animalToEdit >= AnimalsService.listingAnimals().size()) {
                    menuService.invalidSelection();
                } else {
                    AnimalsService.editAnimalInformation(animalToEdit);
                }



            } else if(action == MenuService.DELETE_ANIMAL){
                int animalToDelete = MenuService.viewAnimalDetails();

                if(animalToDelete < 0){
                    menuService.noAnimals();
                } else if(animalToDelete >= AnimalsService.listingAnimals().size()) {
                    menuService.invalidSelection();
                } else {
                    AnimalsService.deleteAnimalFromMemory(animalToDelete);
                }

            } else if(action == MenuService.QUIT){
                if(MenuService.quitMenu("Are you sure you want to quit? (Yes/No)"))
                    break;

            } else {
                System.out.println("Not a valid response, please try again.");
            }
        }

    }
}
