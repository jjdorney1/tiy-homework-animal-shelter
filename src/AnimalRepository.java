// package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;

// import java.util.HashMap;
// import java.util.List;

/**
 * Manages persistance of animals and their data.
 * Created by jeffreydorney on 9/2/16.
 */
public class AnimalRepository {

    // creates a Path to access
    private Path filePath;

    // creates a Gson object
    private Gson gson;

    // new array list of animal class
    public ArrayList<Animal> allAnimalsListed = new ArrayList<>();

    // menu service constructor
    // private MenuService menuService;


    public AnimalRepository(String fileName) throws IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();

        // sets the filePath to the input fileName
        filePath = Paths.get(fileName);

        // collects json data if it exists and passes it into the allAnimals variable
        if (Files.exists(filePath)) {
            String json = new String(Files.readAllBytes(filePath));
            Type listType = new TypeToken<ArrayList<Animal>>() {}.getType();

            allAnimalsListed = gson.fromJson(json, listType);
        } else {
            File newFile = new File("animals.json");
            newFile.createNewFile();
        }
    }

    public void addingAnimal(Animal animalToAdd) throws IOException {
        allAnimalsListed.add(animalToAdd);

        save();

        // menuService.success();
    }

    private void save() throws IOException {
        String json = gson.toJson(allAnimalsListed);
        Files.write(filePath, json.getBytes());

    }

    public ArrayList<Animal> listingAnimals(){
        return allAnimalsListed;
    }

    public Animal viewAnimalDetails(int animalToView){
        return allAnimalsListed.get(animalToView);
    }

    public void deleteAnimal(int animalToDelete) throws IOException {
        allAnimalsListed.remove(animalToDelete);

        save();
    }
}
