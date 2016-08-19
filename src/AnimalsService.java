import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    public static String animalList(){
        return "YES";
    }
    public int viewAnimalPrompt(){

        HashMap<String, String> animalsToView = new HashMap<>();

        System.out.println("\n-- Animals --\n" +
                "\n" +
                animalsToView
        );
        return 2; /*viewInput("Please enter the number of the animal to see more information.")*/
    }

}
