
package simulator.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import simulator.domain.TuringMachine;

/**
 * Class that manages creating and reading the files describing a Turing machine.
 */

public class FileTMDao implements TMDao {
    
    private String folder;
    private Scanner reader;
    
    
    public FileTMDao() {
        File file = new File("TMSimulator");
        file.mkdir();
        folder = System.getProperty("user.dir") + File.separator + "TMSimulator";
    }
    
    public FileTMDao(String f) {
        folder = f;
    }
    /**
     * Returns the absolute path of the project folder.
     * @return Absolute path of the project folder. 
     */
    @Override
    public String getProjectFolder() {
        return folder;
    }
    
    /**
     * Creates a Turing machine.
     * Method creates a file and writes it with information describing the Turing Machine given as a parameter.
     * @param tm Turing machine to be saved as a file.
     * @return True if creation of the file was successful and false otherwise.
     */
    @Override
    public boolean create(TuringMachine tm) {
        File f = new File(folder + File.separator + tm.getName() + ".txt");
        try {
            if (f.createNewFile()) {
                FileWriter writer = new FileWriter(f);
                writer.write(tm.getName() + ":\n");
                writer.write(tm.getDescription() + ":\n");
                writer.write(tm.toStringAlphabet() + ":\n");
                writer.write(tm.toStringStates() + ":\n");
                writer.write(tm.toStringTable());
                writer.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * Returns the created Turing machine file.
     * Method is currently only used in testing but it could be useful later on.
     * @param tm
     * @return 
     */
    @Override
    public File createFile(TuringMachine tm) {
        File f = new File(folder + File.separator + tm.getName() + ".txt");
        try {
            if (f.createNewFile()) {
                FileWriter writer = new FileWriter(f);
                writer.write(tm.getName() + ":\n");
                writer.write(tm.getDescription() + ":\n");
                writer.write(tm.toStringAlphabet() + ":\n");
                writer.write(tm.toStringStates() + ":\n");
                writer.write(tm.toStringTable());
                writer.close();
                return f;
            } else {
                return null;
            }
        } catch (IOException ex) {
            return null;
        }
    }
    
    /**
     * Sets the scanner of the class to read from the file given as a parameter.
     * @param f File to be read from.
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void prepareReader(File f) throws FileNotFoundException {
        reader = new Scanner(f);
    }
    
    /**
     * Reads the name of a Turing machine from the project file.
     * The name is written on the first line.
     * @return Name of the Turing machine described in the file.
     */
    @Override
    public String readName() {
        String name = "";
        if (reader.hasNextLine()) {
            name = reader.nextLine();
        }
        return name.substring(0, name.length() - 1);
    }
    
    /**
     * Reads the description of a Turing machine from the project file.
     * The description is from the second line onward.
     * @return Description of the Turing machine described in the file.
     */
    @Override
    public String readDescription() {
        String description = "";
        while (reader.hasNextLine()) {
            String row = reader.nextLine();
            if (row.charAt(row.length() - 1) == 58) {
                description += row;
                break;
            }
            description += row + "\n";

        }
        return description.substring(0, description.length() - 1);
    }
    
    /**
     * Reads the alphabet of a Turing machine from the project file.
     * The alphabet is written on the line after description.
     * @return Alphabet of the Turing machine described in the file.
     */
    @Override
    public String readAlphabet() {
        String alphabet = "";
        if (reader.hasNextLine()) {
            alphabet = reader.nextLine();
        }
        return alphabet.substring(0, alphabet.length() - 1);
    }
    
    /**
     * Reads the states of a Turing machine from the project file.
     * States are written on the line after alphabet.
     * @return States of the Turing machine described in the file.
     */
    @Override
    public String readStates() {
        String states = "";
        if (reader.hasNextLine()) {
            states = reader.nextLine();
        }
        return states.substring(0, states.length() - 1);
    }
    
    /**
     * Reads the transition table of a Turing machine from the project file.
     * The table is described in the lines following the states.
     * @param states The amount of states.
     * @param alphabetSize The size of the alphabet.
     * @return String table describing the instructions of the Turing machine.
     */
    @Override
    public String[][] readTable(int states, int alphabetSize) {
        String[][] table = new String[states][alphabetSize];
        int row = 0;
        while (reader.hasNextLine()) {
            String[] bits = reader.nextLine().split("; ");
            for (int i = 0; i < bits.length; i++) {
                table[row][i] = bits[i];
            }
            row++;
        }
        return table;
    }
}
