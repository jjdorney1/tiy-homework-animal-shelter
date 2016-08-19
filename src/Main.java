import java.awt.*;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class Main {
    public static void main(String[] args){

        MenuService menuService = new MenuService();
        Animal animal = new Animal();

        while(true) {
            int action = menuService.mainMenuPrompt();

            if(action == MenuService.LIST_ANIMALS){
                System.out.println("List animal!");
            } else if(action == MenuService.CREATE_ANIMAL){
                System.out.println("Create animal!");
            } else if(action == MenuService.VIEW_ANIMAL){
                System.out.println(animal.toString());
            } else if(action == MenuService.EDIT_ANIMAL){
                System.out.println("Edit animal!");
            } else if(action == MenuService.DELETE_ANIMAL){
                System.out.println("Delete animal!");
            } else if(action == MenuService.QUIT){
                System.out.println("Have a nice day!");
                break;
            } else {
                System.out.println("Not a valid response, please try again.");
            }
        }

    }
}
