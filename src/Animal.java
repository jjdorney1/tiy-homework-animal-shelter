import java.util.ArrayList;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class Animal {

    private String name;
    private String species;
    private String breed;
    private String description;

    public Animal(String name, String species, String breed, String description){
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public String toString(){
        return "Name: " + this.name + "\nSpecies: " + this.species + "\nBreed: " + this.breed + "\nDescription: " + this.description;
    }
}

