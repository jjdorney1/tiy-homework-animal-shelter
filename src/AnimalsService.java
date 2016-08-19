import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    HashMap<Integer, Animal> allAnimalsListed = new HashMap<>();

    public static String animalList(){

        System.out.println("\n-*- List of Animals -*-\n" +
                "\n" +
                "1) Name1 species1\n" +
                "2) Name2 species2\n" +
                "3) Name3 species3\n");

        return "(Pick option 3 for more details about an animal!)\n";
    }

    public ArrayList<String> addingAnimal() {
        ArrayList<String> newAnimal = new ArrayList<>();
        return newAnimal;
    }
}
