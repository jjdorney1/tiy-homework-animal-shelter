

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class Main {
    public static void main(String[] args){

        MenuService menuService = new MenuService();

        while(true) {
            int action = menuService.mainMenuPrompt();

            if(action == MenuService.LIST_ANIMALS){
                System.out.println(AnimalsService.listingAnimals());

            } else if(action == MenuService.CREATE_ANIMAL){
                System.out.println(AnimalsService.addingAnimal());

            } else if(action == MenuService.VIEW_ANIMAL){
                System.out.println(AnimalsService.viewAnimalDetails());

            } else if(action == MenuService.EDIT_ANIMAL){
                System.out.println(AnimalsService.editAnimalInformation());

            } else if(action == MenuService.DELETE_ANIMAL){
                System.out.println(AnimalsService.deleteAnimalFromMemory(MenuService.waitForUserMenuInput("Enter the animal you wish to delete.")));

            } else if(action == MenuService.QUIT){
                if(MenuService.quitMenu("Are you sure you want to quit? (\"Yes\"/\"No\")"))
                    break;

            } else {
                System.out.println("Not a valid response, please try again.");
            }
        }

    }
}
