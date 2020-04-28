
package simulator.domain;

/**
 * A class representing a simulator used for simulating a Turing machine.
 */

public class Simulator {
    
    private TuringMachine tm;
    private int state;
    private int head;
    private char[] tape;
    final private int length;
    private int limit;
    private int counter;
    private int tapeLimit;
            
    public Simulator() {
        this.state = 0;
        this.length = 34;
        this.limit = 0;
        this.counter = 0;
        this.tapeLimit = 3000000;
    }
    
    /**
     * Returns the current state of the simulator.
     * @return State of the simulator
     */
    public int getState() {
        return this.state;
    }
    
    /**
     * Returns the Turing machine currently attached to the simulator.
     * @return Turing machine currently attached to the simulator.
     */
    public TuringMachine getTuringMachine() {
        return this.tm;
    }
    
    /**
     * Returns the tape length.
     * A helper method for testing.
     * @return Tape length.
     */
    public int getTapeLength(){
        return tape.length;
    }
    
    /**
     * Returns the head position.
     * A helper method for testing.
     * @return Integer indicating the heads position on the tape.
     */
    public int getHeadPosition(){
        return head;
    }
    
    /**
     * Sets the Turing machine as the current Turing machine of the simulator.
     * @param tm Turing machine to be attached to the simulator.
     */
    public void setTM(TuringMachine tm) {
        this.tm = tm;
        this.state = 0;
    }
    
    /*
     * Sets the amount of steps after which the simulator will halt the calculation.
     * @param limit Step limit.
     */
    private void setLimit(int limit){
        this.limit = limit;
    }
    
    /*
     * Sets the maximum length of the tape.
     * If the simulator tries to grow the tape beyond this limit, the calculation will halt.
     * @param tapeLimit Maximum tape length.
     */
    private void setTapeLimit(int tapeLimit){
        this.tapeLimit = tapeLimit;
    }
    
    /*
     * Resets the counter value to 0.
     */
    private void resetCounter(){
        this.counter = 0;
    }
    
    /**
     * Sets up the simulator before simulation.
     * Method is used to reset the counter and state of the simulator, set the limits to steps and tape length and initialize the tape with input.
     * @param input String input.
     * @param limit Step limit.
     * @param tapeLimit Maximum tape length
     * @throws OutOfMemoryError 
     */
    public void setUpSimulator(String input, int limit, int tapeLimit) throws OutOfMemoryError {
        resetCounter();
        setLimit(limit);
        setTapeLimit(tapeLimit);
        this.state = 0;
        initTape(input);
    }
    
    /**
     * Simulates one step with the current Turing Machine attached to the simulator.
     * @return String representation of the tape after this step.
     */
    public String simulateStep() {
        Instruction[][] table = tm.getTable();
        if(counter >= limit) {
            return "Terminated after";
        }
        if(tm.searchCharacterIndex(tape[head]) == -1){
            return "Bad input for this machine.";
        }
        Instruction i = table[state][tm.searchCharacterIndex(tape[head])];
        if (i == null || i.getState().equals("")) {
            return "Undefined character and state combination.";
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
            try {
                if (head - length < 0) {
                    growTape(-1);
                }
            } catch(OutOfMemoryError e) {
                return "Tape limit exceeded.";
            }
        }
        if (a == 'R') {
            head++;
            try {
                if (head + length > (tape.length - 1)) {
                    growTape(1);
                }
            } catch(OutOfMemoryError e) {
                return "Tape limit exceeded.";
            }
        }
        state = tm.searchStateIndex(i.getState());
        counter++;
        return printTape();
    }
    
    /**
     * Simulates with the current Turing machine attached to the simulator and the given input.
     * Method returns 1 if the simulation ended in an accepting state,
     * 0 if the simulation ended in a rejecting state,
     * -1 if the simulation halted due to an undefined combination of character and state.
     * -10 if the simulation had not halted before reaching the step limit,
     * -13 if the tape length is exceeded,
     * 888 if the input size is longer than the maximum tape length.
     * @param input String input.
     * @param limit Step limit.
     * @param tapeLimit Maximum tape length.
     * @return An integer indicating the outcome of the simulation.
     */
    public int simulate(String input, int limit, int tapeLimit) {
        try {
            setUpSimulator(input, limit, tapeLimit);
        } catch (OutOfMemoryError e){
            return 888;
        }
        Instruction[][] table = tm.getTable();
        while (true) {
            if(counter >= limit){
                return -10;
            }
            if(tm.searchCharacterIndex(tape[head]) == -1){
                return 666;
            }
            Instruction i = table[state][tm.searchCharacterIndex(tape[head])];
            if (i == null || i.getState().equals("")) {
                return -1;
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
                try {
                    if (head - length < 0) {
                        growTape(-1);
                    }
                } catch(OutOfMemoryError e){
                    return -13;
                }
            }
            if (a == 'R') {
                head++;
                try {
                    if (head + length > (tape.length - 1)) {
                        growTape(1);
                    }
                } catch(OutOfMemoryError e){
                    return -13;
                } 
            }
            state = tm.searchStateIndex(i.getState());
            counter++;
        }
    }
    
    /*
     * Initiates the tape with the given input.
     * Method creates a char array of a sufficient size, writes the input on it and places the head on the first character.
     * @param input String input.
     */
    private void initTape(String input) {
        int n = input.length();
        if(n >= tapeLimit){
            throw new OutOfMemoryError();
        }
        head = length * 2;
        if(n <= length) {
            tape = new char[(length * 4) + 1];
        } else {
            tape = new char[n + (length * 4)];
        }
        for (int i = 0; i < tape.length; i++) {
            tape[i] = '_';
        }
        int idx = head;
        for (int i = 0; i < n; i++) {
            tape[idx] = input.charAt(i);
            idx++;
        }
    }
    
    /*
     * Lengthens the tape.
     * Creates a new char array double the size of the current tape and copies everything on the previous tape on the new one.
     * Sets the new array as the tape.
     * @param o A negative integer indicating the tape will be lengthened to the left or a non negative integer indicating the tape will be lengthened to the right.
     */
    private void growTape(int o) {
        int n = tape.length;
        if(n + n >= tapeLimit){
            throw new OutOfMemoryError();
        }
        char[] t = new char[n + n];
        for(int i = 0; i < t.length; i++){
            t[i] = '_';
        }
        if(o < 0){
            head += n;
            int idx = n;
            for(int i = 0; i < n; i++){
                t[idx] = tape[i];
                idx++;
            }
        } else {
            for(int i = 0; i < n; i++){
                t[i] = tape[i];
            }
        }
        tape = t;
    }
    
    /**
     * Returns a string representing the tape.
     * Method will create a string representing the area around the head in a radius of [-34,34] characters.
     * @return String representation of the current situation on the tape near head.
     */
    public String printTape() {
        StringBuilder situation = new StringBuilder();
        int alku = head - length;
        int loppu = head + length;
        for(int j = alku; j <= loppu; j++){
                if(j == head){
                    situation.append("[").append(tape[j]).append("]");
                } else if(j == (head -1)) {
                    situation.append(tape[j]);
                } else {
                    situation.append(tape[j]).append(" ");
                }
            }
        return situation.toString();
    }
    
}
