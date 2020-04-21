
package simulator.domain;

import java.io.IOException;
import simulator.dao.ManagerDao;
import simulator.dao.TMDao;

public class Handler {
    
    private ManagerDao mao;
    private TMDao tmdao;
    
    public Handler(ManagerDao mao, TMDao tmdao) {
        this.mao = mao;
        this.tmdao = tmdao;
    }
    
    public boolean createTM(String name, String description, String[][] table, char[] alphabet, String[] states) {
        Instruction[][] t = createInstructionTable(table);
        TuringMachine tm = new TuringMachine(name, description, t, alphabet, states);
        try {
            return tmdao.create(tm);
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
    
    public void simulate() {
        
    }
}
