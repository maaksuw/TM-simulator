
package simulator.domain;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import simulator.dao.ManagerDao;
import simulator.dao.TMDao;

public class Handler {
    
    private ManagerDao mao;
    private TMDao tmdao;
    private Simulator sakke;
    private TuringMachine currentTM;
    
    public Handler(ManagerDao mao, TMDao tmdao) {
        this.mao = mao;
        this.tmdao = tmdao;
        sakke = new Simulator();
    }
    
    public String getProjectFolder(){
        return mao.getProjectFolder();
    }
    
    public boolean createTM(String name, String description, String[][] table, char[] alphabet, String[] states) {
        Instruction[][] t = createInstructionTable(table);
        TuringMachine tm = new TuringMachine(name, description, t, alphabet, states);
        try {
            if(tmdao.create(tm)){
                currentTM = tm;
                return true;
            }
            return false;
        } catch (IOException ex) {
            System.out.println("createTM: " + ex.getMessage());
            return false;
        }
    }
    
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
    
    public void setUpTM(File f){
        tmdao.prepareReader(f);
        String name = tmdao.readName();
        String description = tmdao.readDescription();
        char[] alphabet = createAlphabet(tmdao.readAlphabet());
        String[] states = createStates(tmdao.readStates());
        String[][] table = tmdao.readTable(states.length, alphabet.length);
        Instruction[][] transitionTable = createInstructionTable(table);
        TuringMachine tm = new TuringMachine(name, description, transitionTable, alphabet, states);
        sakke.setTm(tm);
        currentTM = tm;
    }
    
    private char[] createAlphabet(String characters){
        String[] bits = characters.split(" ");
        char[] alphabet = new char[bits.length];
        for(int i = 0; i < bits.length; i++){
            alphabet[i] = bits[i].charAt(0);
        }
        return alphabet;
    }
    
    private String[] createStates(String states){
        String[] bits = states.split(" ");
        return bits;
    }
    
    public String getCurrentTMName(){
        return currentTM.getName();
    }
    
    public String getCurrentTMDescription(){
        return currentTM.getDescription();
    }
    
    public String getCurrentTMAlphabet(){
        return currentTM.toStringAlphabet();
    }
    
    public void simulate() {
        
    }
}
