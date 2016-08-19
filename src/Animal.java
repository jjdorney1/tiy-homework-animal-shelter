import java.util.ArrayList;

/**
 * Created by jeffreydorney on 8/19/16.
 */
public class Animal {

    String name;
    String species;
    String breed;
    String description;


    public Animal(){
        this.name = "Nikki";
        this.species = "Dog";
        this.breed = "Entlebucher";
        this.description = "Nervous cutie.";
    }

    public String toString(){
        return "Name: " + this.name + "\nSpecies: " + this.species + "\nBreed: " + this.breed + "\nDescription: " + this.description;
    }

}
