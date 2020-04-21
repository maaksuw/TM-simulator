
package simulator.domain;

public class TuringMachine {
    
    private String name;
    private String d;
    private Instruction[][] table;
    private char[] alphab;
    private String[] states;
    
    public TuringMachine(String name, String desc, Instruction[][] t, char[] a, String[] s) {
        this.name = name;
        this.d = desc;
        this.table = t;
        this.alphab = a;
        this.states = s;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return d;
    }

    public Instruction[][] getTable() {
        return table;
    }

    public char[] getAlphabet() {
        return alphab;
    }

    public String[] getStates() {
        return states;
    }    
    
    public int searchCharacterIndex(char a) { //If there is need for more efficient searching later the alphabet should be stored in a hashmap
        for (int i = 0; i < alphab.length; i++) {
            if (alphab[i] == a) return i;
        }
        return -1;
    }
    
    public char searchCharacter(int i) {
        if (i >= alphab.length) throw new IllegalArgumentException();
        return alphab[i];
    }
    
    public int searchStateIndex(String s) { //If there is need for more efficient searching later the states should be stored in a hashmap
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(s)) return i;
        }
        return -1;
    }
    
    public String searchState(int i) {
        if (i >= states.length) throw new IllegalArgumentException();
        return states[i];
    }
    
    public String toStringAlphabet() {
        String s = "";
        for (int i = 0; i < alphab.length; i++) {
            s += alphab[i];
            if (i != alphab.length - 1) s += " ";
        }
        return s;
    }
    
    public String toStringStates() {
        String s = "";
        for (int i = 0; i < states.length; i++) {
            s += states[i];
            if (i != states.length - 1) s += " ";
        }
        return s;
    }
    
    public String toStringTable() {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                s += table[i][j].toString();
                if (j != table[i].length - 1) s += " ";
            }
            if (i != table.length - 1) s += "\n";
        }
        return s;
    }
}
