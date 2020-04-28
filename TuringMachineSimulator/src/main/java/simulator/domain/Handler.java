
package simulator.domain;

import java.io.File;
import java.io.IOException;
import simulator.dao.TMDao;

/**
 * Handler presents methods for the UI to interact with the other classes.
 * Handler has methods for all main functions of the program and some helper methods to pass information from the domain and DAO classes to the UI.
 * @author vwpinja
 */

public class Handler {
    
    private TMDao tmdao;
    private Simulator sakke;
    private TuringMachine currentTM;
    
    public Handler(TMDao tmdao) {
        this.tmdao = tmdao;
        sakke = new Simulator();
    }
    
    /**
     * Returns the absolute path of the current program folder.
     * @see simulator.dao.TMDao#getProjectFolder() 
     * @return Absolute path of the current program folder where the Turing machine files are located.
     */
    public String getProjectFolder() {
        return tmdao.getProjectFolder();
    }
    
    /**
     * Creates a Turing machine.
     * Method creates a Turing machine object and invokes a DAO class's method for creating a project file describing the created machine.
     * Method sets the created Turing machine as the current Turing machine of the handler and simulator.
     * @param name Name of the Turing machine.
     * @param description Description of the Turing machine.
     * @param table String table describing the transition table of the Turing machine.
     * @param alphabet Char array describing the alphabet of the Turing machine.
     * @param states String array describing the state register.
     * @see simulator.dao.TMDao#create(simulator.domain.TuringMachine) 
     * @return True if the project file was created successfully, false otherwise.
     */
    public boolean createTM(String name, String description, String[][] table, char[] alphabet, String[] states) {
        Instruction[][] t = createInstructionTable(table);
        TuringMachine tm = new TuringMachine(name, description, t, alphabet, states);
        try {
            if (tmdao.create(tm)) {
                currentTM = tm;
                sakke.setTM(tm);
                return true;
            }
            return false;
        } catch (IOException ex) {
            System.out.println("createTM: " + ex.getMessage());
            return false;
        }
    }
    
    /*
     * Creates an instruction table from the given string table.
     * Method is used when creating a new Turing machine.
     * @param t String table.
     * @return Instruction table.
     */
    private Instruction[][] createInstructionTable(String[][] t) {
        int n = t.length;
        int m = t[0].length;
        Instruction[][] table = new Instruction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String[] inst = t[i][j].split(" ");
                if (inst.length == 1) {
                    table[i][j] = new Instruction(inst[0]);
                } else {
                    char character = inst[0].charAt(0);
                    char movement = inst[1].charAt(0);
                    String state = inst[2];
                    table[i][j] = new Instruction(character, movement, state);
                }
            }
        }
        return table;
    }
    
    /**
     * Creates a Turing machine described in the file given as a parameter.
     * Method invokes a DAO class's method for reading the file and receives the necessary components to create a Turing machine.
     * Sets the created Turing machine as the current Turing machine of the handler and simulator.
     * @see simulator.dao.TMDao#prepareReader(java.io.File) 
     * @see simulator.dao.TMDao#readName() 
     * @see simulator.dao.TMDao#readDescription() 
     * @see simulator.dao.TMDao#readAlphabet() 
     * @see simulator.dao.TMDao#readStates() 
     * @see simulator.dao.TMDao#readTable(int, int) 
     * @param f File describing the Turing machine in the accepted format. 
     */
    public void setUpTM(File f) {
        tmdao.prepareReader(f);
        String name = tmdao.readName();
        String description = tmdao.readDescription();
        char[] alphabet = createAlphabet(tmdao.readAlphabet());
        String[] states = createStates(tmdao.readStates());
        String[][] table = tmdao.readTable(states.length, alphabet.length);
        Instruction[][] transitionTable = createInstructionTable(table);
        TuringMachine tm = new TuringMachine(name, description, transitionTable, alphabet, states);
        sakke.setTM(tm);
        currentTM = tm;
    }
    
    /*
     * Creates a char array representing the alphabet of a Turing machine.
     * A helper method for creating a new Turing machine.
     * @param characters String of the characters separated by space.
     * @return Char array.
     */
    private char[] createAlphabet(String characters) {
        String[] bits = characters.split(" ");
        char[] alphabet = new char[bits.length];
        for (int i = 0; i < bits.length; i++) {
            alphabet[i] = bits[i].charAt(0);
        }
        return alphabet;
    }
    
    /*
     * Creates a string array representing the states of a Turing machine.
     * A helped method for creating a new Turing machine.
     * @param states String of the state names separated by space.
     * @return 
     */
    private String[] createStates(String states) {
        String[] bits = states.split(" ");
        return bits;
    }
    
    /**
     * Returns the name of the Turing machine currently attached to the simulator.
     * @return Name of the Turing machine.
     */
    public String getCurrentTMName() {
        return currentTM.getName();
    }
    
    /**
     * Returns the description of the Turing machine currently attached to the simulator.
     * @return Description of the Turing machine.
     */
    public String getCurrentTMDescription() {
        return currentTM.getDescription();
    }
    
    /**
     * Returns the alphabet of the Turing machine currently attached to the simulator.
     * @return Alphabet of the Turing machine.
     */
    public String getCurrentTMAlphabet() {
        return currentTM.toStringAlphabet();
    }
    
    /**
     * Returns the current situation on the tape.
     * A helper method used in drawing the initial situation on the canvas. 
     * @see simulator.domain.Simulator#printTape() 
     * @return String representing the tape.
     */
    public String getTape() {
        return sakke.printTape();
    }
    
    /**
     * Simulates the Turing machine currently attached to the simulator.
     * Invokes simulators method simulate().
     * @param input
     * @param limit
     * @param tapeLimit
     * @see simulator.domain.Simulator#simulate(java.lang.String, int, int) 
     * @return A string representing the result of the simulation.
     */
    public String simulate(String input, int limit, int tapeLimit) {
        int result = sakke.simulate(input, limit, tapeLimit);
        if (result == 1) {
            return "Accepted";
        } else if (result == 0) {
            return "Rejected";
        } else if (result == -1) {
            return "Undefined character and state combination.";
        } else if (result == -10) {
            return "Terminated after";
        } else if (result == -13) {
            return "Tape limit exceeded.";
        } else if (result == 888) {
            return "Input size exceeds tape limit.";
        } else {
            return "Bad input for this machine.";
        }
    }
    
    /**
     * Sets up the simulator before step-by-step simulation.
     * @param input
     * @param limit
     * @param tapeLimit
     * @see simulator.domain.Simulator#setUpSimulator(java.lang.String, int, int) 
     * @return True if the set up was completed and false if the set up caused an error.
     */
    public boolean setUpStepByStep(String input, int limit, int tapeLimit) {
        try {
            sakke.setUpSimulator(input, limit, tapeLimit);
            return true;
        } catch (OutOfMemoryError e) {
            return false;
        }
    }
    
    /**
     * Simulates one step with the current Turing machine.
     * @see simulator.domain.Simulator#simulateStep() 
     * @return String representation of the tape after simulation of this step.
     */
    public String simulateStep() {
        return sakke.simulateStep();
    }
}
