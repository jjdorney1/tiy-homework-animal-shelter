import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manages animal data and some functions.
 * Created by jeffreydorney on 8/19/16.
 */
public class AnimalsService {

    // creates a new animal repository object to hold animals
    private AnimalRepository animalRepository = new AnimalRepository("animals.json");

    public AnimalsService(AnimalRepository animalRepository) throws IOException, SQLException {
        this.animalRepository = animalRepository;
    }

    // Returns animals when called.
    public ArrayList<Animal> listingAnimals() {
        return animalRepository.listingAnimals();
    }

    // Adds an animal to the allAnimalsListedArray when called.
    public void addingAnimal(Animal animalToAdd) throws IOException {
        animalRepository.addingAnimal(animalToAdd);
    }

    // Delete animal from the entered animals.
    public boolean deleteAnimalFromMemory(int animalToDelete) throws IOException, SQLException {

        if (MenuService.checkYesNoInput("Are you sure you want to delete this animal? (Yes/No) ")) {
            animalRepository.deleteAnimal(animalToDelete);
            return true;
        } else return false;
    }

    public boolean deletingAnAnimal(int animalToDelete) throws IOException, SQLException {

         if (MenuService.checkYesNoInput("Are you sure you want to delete this animal? (Yes/No) ")) {
            animalRepository.deleteAnimal(animalToDelete);
            return true;
        } else return false;
    }

    public Animal viewAnimalDetails(ResultSet animalResult) throws SQLException {
        int id;
        String name = null;
        String species = null;
        String breed = null;
        String description = null;

        while(animalResult.next()){
            id = animalResult.getInt("animalid");
            name = animalResult.getString("animalname");
            species = animalResult.getString("animaltype");
            breed = animalResult.getString("animalbreed");
            description = animalResult.getString("animaldescription");

        }

        return new Animal(name, species, breed, description);
    }
}