import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Manages persistence of animals and their data.
 * Created by jeffreydorney on 9/2/16.
 */
public class AnimalRepository {

    // creates a Path to access
    private Path filePath;

    // creates a Gson object
    private Gson gson;
    private Connection connection;
    private String databaseAddress = "jdbc:postgresql://localhost/animal";

    // new array list of animal class
    private ArrayList<Animal> allAnimalsListed = new ArrayList<>();

    public AnimalRepository(String fileName) throws IOException, SQLException {
        this.connection = DriverManager.getConnection(databaseAddress);

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

    // save command to persist data to file
    private void save() throws IOException {
        String json = gson.toJson(allAnimalsListed);
        Files.write(filePath, json.getBytes());
    }

    // called when adding an animal - saves at end of function
    public void addingAnimal() throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("INSERT INTO animals (animalname, \"animaltype\", \"animalbreed\", \"animaldescription\") " +
                "   VALUES (?, ?, ?, ?) ");
    }

    public void addingAnimal(Animal animalToAdd) throws IOException {
        allAnimalsListed.add(animalToAdd);
        save();
    }

    // function to check if animals have been entered
    public boolean noAnimalsEntered(){
        if(allAnimalsListed.isEmpty()){
            return true;
        } else return false;
    }

    // function to check the size of animal data
    public int getAnimalsEnteredSize(){
        return allAnimalsListed.size();
    }

    // function to get animal data in a list
    public ArrayList<Animal> listingAnimals(){
        return allAnimalsListed;
    }

    // function to view the details of an animal
    public ResultSet viewAnimalDetails(int animalToView) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("SELECT a.animalid, a.animalname, at.animaltype, ab.animalbreed, a.animaldescription\n" +
                "    FROM animals AS a JOIN animaltypes as at\n" +
                "    ON a.animaltype = at.animaltypeid\n" +
                "    JOIN animalbreed as ab\n" +
                "    ON a.animalbreed = ab.animalbreedid\n" +
                "    WHERE a.animalid = " + animalToView + ";");
    }

    // function to delete a specific animal and save that change
    public void deleteAnimal(int animalToDelete) throws IOException, SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM animaltypelink " +
                "WHERE animalid = " + animalToDelete + ";");
        stmt.execute("DELETE FROM animalbreedlink " +
                "WHERE animalid = " + animalToDelete + ";");
        stmt.execute("DELETE FROM notes " +
                "WHERE animalid = " + animalToDelete + ";");
        stmt.execute("DELETE FROM animals " +
                "WHERE animalid = " + animalToDelete + ";");
    }

    // function to edit an animal's data and save that change
    public void editAnimalUpdate(Animal animalToAdd, int animalToRemove) throws IOException {
        allAnimalsListed.remove(animalToRemove);
        allAnimalsListed.add(animalToRemove, animalToAdd);
        save();
    }

    //function to check if valid animal
    public boolean validAnimalCheck(int animalEntered) throws SQLException {
        if(viewAnimalDetails(animalEntered) == null){
            return false;
        } else return true;
    }

    public boolean validAnimalCheck(ResultSet animalEntered) throws SQLException {
        int id = 0;

        while(animalEntered.next()){
            id = animalEntered.getInt("animalid");
        }

        if(id == 0 ){
            return false;
        } else return true;
    }

    /*
    public void addAnimalNote(int animalToAddNote, String note) throws NullPointerException, IOException {
        ArrayList<String> currentNotes = allAnimalsListed.get(animalToAddNote).getNotes();
        currentNotes.add(note);
        System.out.println(currentNotes);
        allAnimalsListed.get(animalToAddNote).setNotes(currentNotes);
        save();
    }
    */

    public ResultSet listAnimals() throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("SELECT a.animalid, a.animalname, at.animaltype, a.animaldescription, a.animalbreed\n" +
                "    FROM animals AS a JOIN animaltypes as at\n" +
                "    ON a.animaltype = at.animaltypeid\n" +
                "    ORDER BY a.animalid;");
    }


    public ResultSet findAnimalWithID(int animalid) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT a.animalid, a.animalname, at.animaltype, ab.animalbreed, a.animaldescription\n" +
                        "    FROM animals AS a JOIN animaltypes as at\n" +
                        "    ON a.animaltype = at.animaltypeid\n" +
                        "    JOIN animalbreed as ab\n" +
                        "    ON a.animalbreed = ab.animalbreedid\n" +
                        "    WHERE a.animalid = ? ");

        preparedStatement.setInt(1, animalid);
        return preparedStatement.executeQuery();
    }

}
