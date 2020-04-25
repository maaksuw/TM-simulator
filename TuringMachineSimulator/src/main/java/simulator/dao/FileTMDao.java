
package simulator.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulator.domain.TuringMachine;

public class FileTMDao implements TMDao {
    
    private String folder;
    private Scanner reader;
    
    
    public FileTMDao(String f) {
        folder = f;
    }
    
    //creates a file which describes the tm
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
    
    @Override
    public void prepareReader(File f){
        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException ex) {
            System.out.println("File " + f.getAbsolutePath() + " not found.");
        }
    }
    
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
