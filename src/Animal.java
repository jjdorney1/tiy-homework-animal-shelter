/**
 * The animal constructor, accessors and modifiers.
 * Created by jeffreydorney on 8/19/16.
 */
public class Animal {

    private String name;
    private String species;
    private String breed;
    private String description;
    private int id;
    // private ArrayList<String> notes;

    public Animal(String name, String species, String breed, String description) {
        setName(name);
        setSpecies(species);
        setBreed(breed);
        setDescription(description);
    }

    public String toString() {
        return "Name: " + this.name + "\nSpecies: " + this.species + "\nBreed: " + this.breed + "\nDescription: " + this.description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Name cannot be empty!");
        }

    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (!species.isEmpty()) {
            this.species = species;
        } else {
            System.out.println("Species cannot be empty!");
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            System.out.println("Description cannot be empty!");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
    */
}

