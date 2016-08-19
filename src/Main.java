import java.awt.*;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class Main {
    public static void main(String[] args){

        MenuService menuService = new MenuService();
        Animal animal = new Animal("Nikki", "Dog", "Entlebucher", "Nervous cutie");

        while(true) {
            int action = menuService.mainMenuPrompt();

            if(action == MenuService.LIST_ANIMALS){
                System.out.println(AnimalsService.animalList());
            } else if(action == MenuService.CREATE_ANIMAL){
                System.out.println("Create animal!");
            } else if(action == MenuService.VIEW_ANIMAL){
                System.out.println(animal.toString());
            } else if(action == MenuService.EDIT_ANIMAL){
                System.out.println("Edit animal!");
            } else if(action == MenuService.DELETE_ANIMAL){
                System.out.println("Delete animal!");
            } else if(action == MenuService.QUIT){
                if(MenuService.quitMenu("Are you sure you want to quit?")){
                    break;
                }

            } else {
                System.out.println("Not a valid response, please try again.");
            }
        }

    }
}
