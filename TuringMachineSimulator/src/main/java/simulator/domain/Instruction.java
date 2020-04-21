
package simulator.domain;

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

    public char getCharacter() {
        return c;
    }

    public char getMovement() {
        return m;
    }

    public String getState() {
        return s;
    }

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

    @Override
    public String toString() {
        if (c == 0 && m == 0) {
            return "(" + s + ")";
        }
        return "(" + c + ", " + m + ", " + s + ")";
    }
    
}
