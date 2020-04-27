
package simulator.domain;

/**
 * A class representing an instruction in the transition table.
 * An instruction consists of a character to be written on the tape, another character indicating where the head will move, and a string naming the state the simulator assume.
 */

public class Instruction {
    
    private char c;
    private char m;
    private String s;
    
    public Instruction(char character, char movement, String state) {
        c = character;
        m = movement;
        s = state;
    }
    
    public Instruction(String state) {
        c = 0;
        m = 0;
        s = state;
    }

    /**
     * Returns the character of the instruction.
     * @return Character.
     */
    public char getCharacter() {
        return c;
    }
    
    /**
     * Returns the movement of the instruction.
     * @return A character representing a movement; L, R or N.
     */
    public char getMovement() {
        return m;
    }
    
    /**
     * Returns the state of the instruction.
     * @return State name.
     */
    public String getState() {
        return s;
    }
    
    /**
     * Checks if the object given as a parameter is the same as this instance of the class.
     * Two instructions are the same if their characters, movements and states are the same.
     * @param o Object of any kind.
     * @return True if the object was the same as this instance of the class, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Instruction i = (Instruction) o;
        if (i.getState().equals(this.getState()) && i.getCharacter() == this.getCharacter() && i.getMovement() == this.getMovement()) {
            return true;
        }
        return false;
    }
    
    /**
     * Returns a string representation of the instruction.
     * The string format is: character movement state, in the aforementioned order and separated by space.
     * @return String representing the instruction. 
     */
    @Override
    public String toString() {
        if (c == 0 && m == 0) {
            return s;
        }
        return c + " " + m + " " + s;
    }
    
}
