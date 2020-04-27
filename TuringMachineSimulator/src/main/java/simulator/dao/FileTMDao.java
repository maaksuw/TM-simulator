
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
    
    
    public FileTMDao(String f) {
        folder = f;
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
        System.out.println("New project file: " + f.getAbsolutePath());
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
            System.out.println("create: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Sets the scanner of the class to read from the file given as a parameter.
     * @param f File to be read from.
     */
    @Override
    public void prepareReader(File f){
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException ex) {
            System.out.println("File " + f.getAbsolutePath() + " not found.");
        }
    }
    
    /**
     * Reads the name of a Turing machine from the project file.
     * The name is written on the first line.
     * @return Name of the Turing machine described in the file.
     */
    @Override
    public String readName(){
        String nimi = "";
        try {
            if(reader.hasNextLine()){
                nimi = reader.nextLine();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return nimi.substring(0, nimi.length()-1);
    }
    
    /**
     * Reads the description of a Turing machine from the project file.
     * The description is from the second line onward.
     * @return Description of the Turing machine described in the file.
     */
    @Override
    public String readDescription(){
        String desc = "";
        try {
            while(reader.hasNextLine()){
                String rivi = reader.nextLine();
                if(rivi.charAt(rivi.length()-1) == 58) {
                    desc += rivi;
                    break;
                }
                desc += rivi + "\n";
                
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return desc.substring(0, desc.length()-1);
    }
    
    /**
     * Reads the alphabet of a Turing machine from the project file.
     * The alphabet is written on the line after description.
     * @return Alphabet of the Turing machine described in the file.
     */
    @Override
    public String readAlphabet(){
        String alphabet = "";
        try {
            if(reader.hasNextLine()){
                alphabet = reader.nextLine();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return alphabet.substring(0, alphabet.length()-1);
    }
    
    /**
     * Reads the states of a Turing machine from the project file.
     * States are written on the line after alphabet.
     * @return States of the Turing machine described in the file.
     */
    @Override
    public String readStates(){
        String states = "";
        try {
            if(reader.hasNextLine()){
                states = reader.nextLine();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return states.substring(0, states.length()-1);
    }
    
    /**
     * Reads the transition table of a Turing machine from the project file.
     * The table is described in the lines following the states.
     * @param states The amount of states.
     * @param alphabets The size of the alphabet.
     * @return String table describing the instructions of the Turing machine.
     */
    @Override
    public String[][] readTable(int states, int alphabets){
        String[][] table = new String[states][alphabets];
        int row = 0;
        try {
            while(reader.hasNextLine()){
                String[] bits = reader.nextLine().split("; ");
                for(int i = 0; i < bits.length; i++){
                    table[row][i] = bits[i];
                }
                row++;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return table;
    }
}
