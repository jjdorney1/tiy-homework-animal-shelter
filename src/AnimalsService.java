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
    public boolean deleteAnimalFromMemory(int animalToDelete) throws IOException {

        if (MenuService.checkYesNoInput("Are you sure you want to delete this animal? (Yes/No) ")) {
            animalRepository.deleteAnimal(animalToDelete);
            return true;
        } else return false;
    }

    public void listingAllAnimals() throws SQLException {
        ResultSet resultSet = this.animalRepository.listAnimals();

        while(resultSet.next()){
            System.out.printf("%s.)\t%s\t%s\n",
                    resultSet.getString("animalid"),
                    resultSet.getString("animalname"),
                    resultSet.getString("animaltype"));
        }
    }
}