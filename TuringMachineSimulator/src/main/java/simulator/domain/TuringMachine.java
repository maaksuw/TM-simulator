
package simulator.domain;

/**
 * A class representing a Turing machine.
 */

public class TuringMachine {
    
    private String name;
    private String description;
    private Instruction[][] table;
    private char[] alphabet;
    private String[] states;
    
    public TuringMachine(String name, String description, Instruction[][] table, char[] alphabet, String[] states) {
        this.name = name;
        this.description = description;
        this.table = table;
        this.alphabet = alphabet;
        this.states = states;
    }
    
    /**
     * Returns the name of the Turing machine.
     * @return Name of the Turing machine.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the description of the Turing machine.
     * @return Description of the Turing machine.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the transition table of the Turing machine.
     * @return Transition table of the Turing Machine.
     */
    public Instruction[][] getTable() {
        return table;
    }
    
    /**
     * Returns the alphabet of the Turing machine.
     * @return Alphabet of the Turing machine.
     */
    public char[] getAlphabet() {
        return alphabet;
    }

    /**
     * Returns the state register of the Turing machine.
     * @return State register of the Turing machine.
     */
    public String[] getStates() {
        return states;
    }    
    
    /**
     * Returns the index of a character in the alphabet. 
     * The index is used by a simulator to identify the correct column in the transition table.
     * Method searches for the right index by going through the whole alphabet.
     * Returns -1 if the character is not in the alphabet.
     * @param a Character.
     * @return Index of the character.
     */
    public int searchCharacterIndex(char a) { //If there is need for more efficient searching later the alphabet should be stored in a hashmap
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == a) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns a character of the alphabet corresponding to the index.
     * Method is used by a simulator to retrieve a character indicated by the index.
     * Throws IllegalArgumentException if the parameter is outside the acceptable range.
     * @param i Index.
     * @return Character corresponding to the index.
     */
    public char searchCharacter(int i) {
        if (i >= alphabet.length || i < 0) {
            throw new IllegalArgumentException();
        }
        return alphabet[i];
    }
    
    /**
     * Returns the index of a state in the state register.
     * The index is used by a simulator to identify the correct row in the transition table.
     * Method searched for the right index by going through the whole alphabet.
     * Returns -1 if the state is not in the state register.
     * @param s State name.
     * @return Index of the state.
     */
    public int searchStateIndex(String s) { //If there is need for more efficient searching later the states should be stored in a hashmap
        if (s.equals("qa")) {
            return -2; 
        }
        if (s.equals("qr")) {
            return -3; 
        }
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns a state from the state register corresponding to the index.
     * Method is used by a simulator to retrieve a state indicated by the index.
     * Throws IllegalArgumentException if the parameter is outside the acceptable range.
     * @param i Index.
     * @return Name of the state.
     */
    public String searchState(int i) {
        if (i == -2) {
            return "qa";
        }
        if (i == -3) {
            return "qr";
        }
        if (i >= states.length || i < 0) {
            throw new IllegalArgumentException();
        }
        return states[i];
    }
    
    /**
     * Returns a string representation of the alphabet.
     * Method returns the alphabet ordered by their indexes and separated by a space.
     * @return String representation of the alphabet.
     */
    public String toStringAlphabet() {
        String s = "";
        for (int i = 0; i < alphabet.length; i++) {
            s += alphabet[i];
            if (i != alphabet.length - 1) {
                s += " ";
            }
        }
        return s;
    }
    
    /**
     * Returns a string representation of the state register.
     * Method returns the state register ordered by their indexes and separated by a space.
     * @return String representation of the state register.
     */
    public String toStringStates() {
        String s = "";
        for (int i = 0; i < states.length; i++) {
            s += states[i];
            if (i != states.length - 1) {
                s += " ";
            }
        }
        return s;
    }
    
    /**
     * Returns a string representation of the transition table.
     * Method returns the transition table as a string. Every cell on the same row is separated by a semicolon and a space.
     * Every row is separated by a new line character.
     * @return String representation of the transition table. 
     */
    public String toStringTable() {
        String ttable = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                ttable += table[i][j].toString();
                if (j != table[i].length - 1) {
                    ttable += "; ";
                }
            }
            if (i != table.length - 1) {
                ttable += "\n";
            }
        }
        return ttable;
    }
}
