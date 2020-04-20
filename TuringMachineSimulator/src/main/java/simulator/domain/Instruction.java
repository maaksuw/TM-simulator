
package simulator.domain;

public class Instruction {
    
    private char c;
    private char m;
    private String s;
    
    public Instruction(char character, char movement, String state){
        c = character;
        m = movement;
        s = state;
    }
    
    public Instruction(String state){
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
    public String toString() {
        if(c == 0 && m == 0){
            return "(" + s + ")";
        }
        return "(" + c + ", " + m + ", " + s + ")";
    }
    
}
