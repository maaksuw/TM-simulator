
package simulator.domain;

public class Simulator {
    
    private TuringMachine tm;
    private int state;
    private int head;
    
    public Simulator(TuringMachine tm) {
        this.tm = tm;
        this.state = 0;
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
    
    public int simulate(String input) {
        char[] tape = initTape(input);
        Instruction[][] table = tm.getTable();
        while (true) {
            if (head == 0 || head == tape.length - 1) tape = growTape(tape);
            //printTape(tape);
            Instruction i = table[state][tm.searchCharacterIndex(tape[head])];
            
            if (i == null || i.getState().equals("")) return 0;
            
            if (i.getState().equals("qa")) return 1;
            if (i.getState().equals("qr")) return 0;
            
            tape[head] = i.getCharacter();
            char a = i.getMovement();
            if (a == 'L') head--;
            if (a == 'R') head++;
            state = tm.searchStateIndex(i.getState());
        }
    }
    
    private char[] initTape(String input) {
        if (input.trim().length() == 0) {
            head = 0;
            return new char[]{'-'};
        }
        int n = input.length();
        int a = n * 2;
        if (n % 2 != 0) a = (n * 2) + 1;
        char[] tape = new char[a];
        for (int i = 0; i < tape.length; i++) {
            tape[i] = '-';
        }
        int idx = n / 2;
        if (n % 2 != 0) idx = (n + 1) / 2;
        head = idx;
        for (int i = 0; i < n; i++) {
            tape[idx] = input.charAt(i);
            idx++;
        }
        return tape;
    }
    
    private char[] growTape(char[] tape) {
        int n = tape.length;
        int a = n * 2;
        if (n % 2 != 0) a = (n * 2) + 1;
        char[] t = new char[a];
        for (int j = 0; j < a; j++) {
            t[j] = '-';
        }
        int idx = n / 2;
        if (n % 2 != 0) idx = (n + 1) / 2;
        head += idx;
        for (int i = 0; i < n; i++) {
            t[idx] = tape[i];
            idx++;
        }
        tape = t;
        return tape;
    }
    
//    private void printTape(char[] tape) {
//        for(int j = 0; j < tape.length; j++){
//                if(j == head){
//                    System.out.print("[" + tape[j] + "]");
//                } else if(j == (head -1)) {
//                    System.out.print(tape[j]);
//                } else {
//                    System.out.print(tape[j] + " ");
//                }
//            }
//            System.out.println("");
//    }
    
}
