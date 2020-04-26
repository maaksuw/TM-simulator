
package simulator.domain;

import java.util.Arrays;

public class Simulator {
    
    private TuringMachine tm;
    private int state;
    private int head;
    private char[] tape;
    final private int length;
            
    public Simulator() {
        this.state = 0;
        length = 34;
    }
    
    public int getState() {
        return this.state;
    }
    
    public TuringMachine getTuringMachine() {
        return this.tm;
    }

    public void setTm(TuringMachine tm) {
        this.tm = tm;
        this.state = 0;
    }
    
    public void setUpStepByStep(String input){
        initTape(input);
        this.state = 0;
    }
    
    public String simulateStep(){
        Instruction[][] table = tm.getTable();
        System.out.println("hello head: " + head);
            if (head - length < 0) {
                growTape(-1);
            } else if (head + length > (tape.length - 1)) {
                growTape(1);
            }
            Instruction i = table[state][tm.searchCharacterIndex(tape[head])];
            if (i == null || i.getState().equals("")) {
                return "Calculation halted";
            }
            if (i.getState().equals("qa")) {
                return "Accepted";
            }
            if (i.getState().equals("qr")) {
                return "Rejected";
            }
            tape[head] = i.getCharacter();
            char a = i.getMovement();
            if (a == 'L') {
                head--;
            }
            if (a == 'R') {
                head++;
            }
            state = tm.searchStateIndex(i.getState());
            return printTape();
    }
    
    public int simulate(String input) {
        initTape(input);
        this.state = 0;
        Instruction[][] table = tm.getTable();
        while (true) {
            if (head - length < 0) {
                growTape(-1);
            } else if (head + length > tape.length - 1) {
                growTape(1);
            }
            printTape();
            Instruction i = table[state][tm.searchCharacterIndex(tape[head])];
            if (i == null || i.getState().equals("")) {
                return 0;
            }
            if (i.getState().equals("qa")) {
                return 1;
            }
            if (i.getState().equals("qr")) {
                return 0;
            }
            tape[head] = i.getCharacter();
            char a = i.getMovement();
            if (a == 'L') {
                head--;
            }
            if (a == 'R') {
                head++;
            }
            state = tm.searchStateIndex(i.getState());
        }
    }
    
    private void initTape(String input) {
        int n = input.length();
        if(n <= length+1) {
            head = length;
            tape = new char[(length * 2) + 1];
        } else {
            head = n-1;
            tape = new char[n + (n-1)];
        }
        for (int i = 0; i < tape.length; i++) {
            tape[i] = '_';
        }
        int idx = head;
        for (int i = 0; i < n; i++) {
            tape[idx] = input.charAt(i);
            idx++;
        }
        System.out.println("init head: " + head);
        System.out.println("after initTape: " + Arrays.toString(tape));
    }
    
    private void growTape(int o) {
        int n = tape.length;
        System.out.println("tapelength: " + n);
        char[] t = new char[n + length];
        System.out.println("new length: " + (n + length));
        for(int i = 0; i < t.length; i++){
            t[i] = '_';
        }
        if(o < 0){
            head += length;
            for(int i = length; i < n; i++){
                t[i] = tape[i];
            }
        } else {
            for(int i = 0; i < n; i++){
                t[i] = tape[i];
            }
        }
        tape = t;
        System.out.println("gt head" + head);
        System.out.println("after grow tape: " + Arrays.toString(tape));
    }
    
    public String printTape() {
        StringBuilder situation = new StringBuilder();
        int alku = head - length;
        System.out.println("alku " + alku);
        int loppu = head + length;
        System.out.println("loppu " + loppu);
        for(int j = alku; j < loppu; j++){
                if(j == head){
                    situation.append("[").append(tape[j]).append("]");
                } else if(j == (head -1)) {
                    situation.append(tape[j]);
                } else {
                    situation.append(tape[j]).append(" ");
                }
            }
        System.out.println("after print tape: " + situation);
        return situation.toString();
    }
    
}
