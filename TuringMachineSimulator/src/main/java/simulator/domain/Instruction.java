
package simulator.domain;

/**
 * A class representing an instruction in the transition table.
 * An instruction consists of a character to be written on the tape, another character indicating where the head will move, and a string naming the state the simulator assume.
 */

public class Instruction {
    
    private char character;
    private char movement;
    private String state;
    
    public Instruction(char character, char movement, String state) {
        this.character = character;
        this.movement = movement;
        this.state = state;
    }
    
    public Instruction(String state) {
        character = 0;
        movement = 0;
        this.state = state;
    }

    /**
     * Returns the character of the instruction.
     * @return Character.
     */
    public char getCharacter() {
        return character;
    }
    
    /**
     * Returns the movement of the instruction.
     * @return A character representing a movement; L, R or N.
     */
    public char getMovement() {
        return movement;
    }
    
    /**
     * Returns the state of the instruction.
     * @return State name.
     */
    public String getState() {
        return state;
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
        if (character == 0 && movement == 0) {
            return state;
        }
        return character + " " + movement + " " + state;
    }
    
}
