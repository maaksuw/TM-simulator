
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import simulator.domain.Instruction;
import simulator.domain.Simulator;
import simulator.domain.TuringMachine;

public class SimulatorTest {
    
    private Simulator sakke;
    private int limit;
    private int tapeLimit;
    
    public SimulatorTest(){
        sakke = new Simulator();
        limit = 1000000;
        tapeLimit = 3000000;
    }
    
    @Before
    public void setUp() {
        char[] alphabet = new char[3];
        alphabet[0] = 'a';
        alphabet[1] = 'b';
        alphabet[2] = '_';
        String[] states = new String[7];
        states[0] = "qA";
        states[1] = "qar";
        states[2] = "qbr";
        states[3] = "qCa";
        states[4] = "qCb";
        states[5] = "qL";
        states[6] = "qB";
        Instruction[][] t = new Instruction[7][3];
        t[0][0] = new Instruction('_','R',"qar");
        t[0][1] = new Instruction('_','R',"qbr");
        t[0][2] = new Instruction("qa");
        t[1][0] = new Instruction('a','R',"qar");
        t[1][1] = new Instruction('b','R',"qar");
        t[1][2] = new Instruction('_','L',"qCa");
        t[2][0] = new Instruction('a','R',"qbr");
        t[2][1] = new Instruction('b','R',"qbr");
        t[2][2] = new Instruction('_','L',"qCb");
        t[3][0] = new Instruction('_','L',"qL");
        t[3][1] = new Instruction("qr");
        t[3][2] = new Instruction("qa");
        t[4][0] = new Instruction("qr");
        t[4][1] = new Instruction('_','L',"qL");
        t[4][2] = new Instruction("qa");
        t[5][0] = new Instruction('a','L',"qB");
        t[5][1]= new Instruction('b','L',"qB");
        t[5][2] = new Instruction("qa");
        t[6][0] = new Instruction('a','L',"qB");
        t[6][1] = new Instruction('b','L',"qB");
        t[6][2] = new Instruction('_','R',"qA");
        TuringMachine tm = new TuringMachine("Makke","Helps in testing.",t,alphabet,states);
        sakke.setTM(tm);
    }
    
    @Test
    public void constructorSetsInitialStateCorrectly() {
        Simulator sakari = new Simulator();
        assertEquals(sakari.getState(),0);
    }
    
    @Test
    public void setTmInitiatesStateCorrectly() {
        assertEquals(sakke.getState(), 0);
    }
    
    @Test
    public void setTmSetsTuringMachineCorrectly() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        Instruction[][] inst = new Instruction[3][2];
        inst[0][0] = new Instruction('a','R',"qar");
        inst[0][1] = new Instruction("qa");
        inst[1][0] = new Instruction('b','R',"qb");
        inst[1][1] = new Instruction('b','L',"qar");
        inst[2][0] = new Instruction('a','L',"qb");
        inst[2][1] = new Instruction("qr");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        assertEquals(sakke.getTuringMachine(),tm2);
    }
    
    @Test
    public void setUpSimulatorDoesntThrowError(){
        boolean error = false;
        try {
            sakke.setUpSimulator("abaaba", limit, tapeLimit);
        } catch (OutOfMemoryError e){
            error = true;
        }
        assertFalse(error);
    }
    
    @Test
    public void setUpSimulatorThrowsError(){
        boolean error = false;
        try {
            sakke.setUpSimulator("aaaaa", limit, 3);
        } catch (OutOfMemoryError e){
            error = true;
        }
        assertTrue(error);
    }
    
    @Test
    public void initTapeTapeLengthCorrect1(){
        sakke.setUpSimulator("", limit, tapeLimit);
        assertEquals(sakke.getTapeLength(),137);
    }
    
    @Test
    public void initTapeHeadPositionCorrect(){
        sakke.setUpSimulator("", limit, tapeLimit);
        assertEquals(sakke.getHeadPosition(),68);
    }
    
    @Test
    public void initTapeTapeLengthCorrect2(){
        sakke.setUpSimulator("a", limit, tapeLimit);
        assertEquals(sakke.getTapeLength(),137);
    }
    
    @Test
    public void initTapeHeadPositionCorrect2(){
        sakke.setUpSimulator("a", limit, tapeLimit);
        assertEquals(sakke.getHeadPosition(),68);
    }
    
    @Test
    public void initTapeTapeLengthCorrect3(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getTapeLength(),137);
    }
    
    @Test
    public void initTapeHeadPositionCorrect3(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getHeadPosition(),68);
    }
    
    @Test
    public void initTapeTapeLengthCorrect4(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getTapeLength(),172);
    }
    
    @Test
    public void initTapeHeadPositionCorrect4(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        input += input;
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getHeadPosition(),68);
    }
    
    @Test
    public void initTapeTapeLengthCorrect5(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaa";
        input += input;
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getTapeLength(),234);
    }
    
    @Test
    public void initTapeHeadPositionCorrect5(){
        String input = "aaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaa";
        input += input;
        sakke.setUpSimulator(input, limit, tapeLimit);
        assertEquals(sakke.getHeadPosition(),68);
    }
    
    @Test
    public void setUpSimulatorInitiatesTapeCorrectly(){
        sakke.setUpSimulator("aaabbb", limit, tapeLimit);
        String correct = sakke.printTape();
        StringBuilder tape = new StringBuilder();
        for(int i = 0; i < 34; i++){
            if(i == 33) tape.append("_");
            else {
                tape.append("_ ");
            }
        }
        tape.append("[a]a a b b b ");
        for(int i = 0; i < 29; i++){
            tape.append("_ ");
        }
        assertEquals(correct,tape.toString());
    }
    
    @Test
    public void simulateStepReturnsTerminatedAfterIfLimitExceeded(){
        sakke.setUpSimulator("aaaaaa", 5, tapeLimit);
        String result = "";
        for(int i = 0; i < 6; i++){
            result = sakke.simulateStep();
        }
        assertEquals(result,"Terminated after");
    }
    
    @Test
    public void simulateStepReturnsBadInput(){
        sakke.setUpSimulator("-----", limit, tapeLimit);
        assertEquals(sakke.simulateStep(),"Bad input for this machine.");
    }
    
    @Test
    public void simulateStepReturnsUndefinedCombination(){
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction("");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("aaabbaba", limit, tapeLimit);
        assertEquals(sakke.simulateStep(),"Undefined character and state combination.");
    }
    
    @Test
    public void simulateStepReturnsUndefinedCombination2(){
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("aaabbaba", limit, tapeLimit);
        assertEquals(sakke.simulateStep(),"Undefined character and state combination.");
    }
    
    @Test
    public void simulateStepReturnsAccepted(){
        sakke.setUpSimulator("aba", limit, tapeLimit);
        String result = "";
        for(int i = 0; i < 10; i++){
            result = sakke.simulateStep();
        }
        assertEquals(result, "Accepted");
    }
    
    @Test
    public void simulateStepReturnsRejected(){
        sakke.setUpSimulator("ab", limit, tapeLimit);
        String result = "";
        for(int i = 0; i < 4; i++){
            result = sakke.simulateStep();
        }
        assertEquals(result,"Rejected");
    }
    
    @Test
    public void simulateStepReturnsTapeLimitExceeded(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','L',"qA");
        inst[0][1] = new Instruction('_','L',"qA");
        inst[1][0] = new Instruction('a','L',"qA");
        inst[1][1] = new Instruction('_','L',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("a", limit, 140);
        boolean b = false;
        while(true){
            if(sakke.simulateStep().equals("Tape limit exceeded.")) {
                b = true;
                break;
            }
        }
        assertTrue(b);
    }
    
    @Test
    public void simulateStepGrowTapeWorks(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','L',"qA");
        inst[0][1] = new Instruction('_','L',"qA");
        inst[1][0] = new Instruction('a','L',"qA");
        inst[1][1] = new Instruction('_','L',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("a", limit, 300);
        boolean b = false;
        while(true){
            sakke.simulateStep();
            if(sakke.getTapeLength() > 137) {
                b = true;
                break;
            }
        }
        assertTrue(b);
    }
    
    @Test
    public void simulateStepReturnsTapeLimitExceeded2(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','R',"qA");
        inst[0][1] = new Instruction('_','R',"qA");
        inst[1][0] = new Instruction('a','R',"qA");
        inst[1][1] = new Instruction('_','R',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("a", limit, 140);
        boolean b = false;
        while(true){
            if(sakke.simulateStep().equals("Tape limit exceeded.")) {
                b = true;
                break;
            }
        }
        assertTrue(b);
    }
    
    @Test
    public void simulateStepGrowTapeWorks2(){
        System.out.println("simulateStepGrowTapeWorks2");
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','R',"qA");
        inst[0][1] = new Instruction('_','R',"qA");
        inst[1][0] = new Instruction('a','R',"qA");
        inst[1][1] = new Instruction('_','R',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        sakke.setUpSimulator("a", limit, 300);
        boolean b = false;
        while(true){
            sakke.simulateStep();
            if(sakke.getTapeLength() > 137) {
                b = true;
                break;
            }
        }
        assertTrue(b);
    }
    
    @Test
    public void simulateStepPrintsTapeCorrectly(){
        sakke.setUpSimulator("aaabbb", limit, tapeLimit);
        String bit = sakke.simulateStep();
        StringBuilder tape = new StringBuilder();
        for(int i = 0; i < 34; i++){
            if(i == 33) tape.append("_");
            else {
                tape.append("_ ");
            }
        }
        tape.append("[a]a b b b ");
        for(int i = 0; i < 30; i++){
            tape.append("_ ");
        }
        assertEquals(bit,tape.toString());
    }
    
    @Test
    public void simulateStepSimulatesCorrectly(){
        sakke.setUpSimulator("abba", limit, tapeLimit);
        String result = "";
        for(int i = 0; i < 14; i++){
            result = sakke.simulateStep();
        }
        assertEquals(result,"Accepted");
    }
    
    @Test
    public void simulateStepSimulatesCorrectly2(){
        sakke.setUpSimulator("abbababbba", limit, tapeLimit);
        boolean b = false;
        while(true){
            if(sakke.simulateStep().equals("Rejected")){
                b = true;
                break;
            }
        }
        assertTrue(b);
    }
    
    @Test
    public void simulateReturns888IfInputSizeOverTapeLimit(){
        assertEquals(sakke.simulate("abbabababababa", limit, 5),888);
    }
    
    @Test
    public void simulateCounterOverLimitReturnsNegative10(){
        assertEquals(sakke.simulate("babababaaaabbabbabb", 10, tapeLimit),-10);
    }
    
    @Test
    public void simulateReturns666ForBadInput(){
        assertEquals(sakke.simulate("cdcdcdcdcdcdc", limit, tapeLimit),666);
    }
    
    @Test
    public void simulateReturnsNegative1IfInstructionNull() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        String input = "aba";
        assertEquals(sakke.simulate(input, limit, tapeLimit),-1);
    }
    
    @Test
    public void simulateReturnsNegative1IfInstructionEmpty() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction("");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        String input = "aba";
        assertEquals(sakke.simulate(input, limit, tapeLimit),-1);
    }
    
    @Test
    public void simulateSimulatesCorrectlyIfInputSize1() {
        String input = "a";
        assertEquals(sakke.simulate(input, limit, tapeLimit),1);
    }
    
    @Test
    public void simulateSimulatesCorrectlyIfInputEmpty() {
        String input = "";
        assertEquals(sakke.simulate(input, limit, tapeLimit),1);
    }
    
    @Test
    public void simulateSimulatesCorrectlySmall1() {
        String input = "abbbbbbbba";
        assertEquals(sakke.simulate(input, limit, tapeLimit),1);
    }
    
    @Test
    public void simulateSimulatesCorrectlySmall2() {
        String input = "abbaaabaabba";
        assertEquals(sakke.simulate(input, limit, tapeLimit),0);
    }
    
    @Test
    public void simulateSimulatesCorrectlyLarge1() {
        String input;
        String str = "babbabbbababbababbababbbaabbaaaa";
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str.charAt(i) + str2;
        }
        input = str + str2;
        assertEquals(sakke.simulate(input, limit, tapeLimit),1);
    }
    
    @Test
    public void simulateSimulatesCorrectlyLarge2() {
        String input;
        String str = "babbabbbababbababbababbbaabbaaaa";
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str.charAt(i) + str2;
        }
        input = str + str2 + "b";
        assertEquals(sakke.simulate(input, limit, tapeLimit),0);
    }
    
    @Test
    public void simulateReturnsNegative13IfTapeLimitExceeded(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','L',"qA");
        inst[0][1] = new Instruction('_','L',"qA");
        inst[1][0] = new Instruction('a','L',"qA");
        inst[1][1] = new Instruction('_','L',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        int result = sakke.simulate("a", limit, 140);
        assertEquals(result,-13);
    }
    
    @Test
    public void simulateReturnsNegative13IfTapeLimitExceeded2(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','R',"qA");
        inst[0][1] = new Instruction('_','R',"qA");
        inst[1][0] = new Instruction('a','R',"qA");
        inst[1][1] = new Instruction('_','R',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        int result = sakke.simulate("_a_a___", limit, 140);
        assertEquals(result, -13);
    }
    
    @Test
    public void growTapeWorksPositiveSimulate(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','R',"qA");
        inst[0][1] = new Instruction('_','R',"qA");
        inst[1][0] = new Instruction('a','R',"qA");
        inst[1][1] = new Instruction('_','R',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        int result = sakke.simulate("_a_a___", limit, 600);
        assertEquals(result, -13);
    }
    
    @Test
    public void growTapeWorksNegativeSimulate(){
        char[] alphabet = new char[]{'a','_'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction('a','L',"qA");
        inst[0][1] = new Instruction('_','L',"qA");
        inst[1][0] = new Instruction('a','L',"qA");
        inst[1][1] = new Instruction('_','L',"qA");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTM(tm2);
        int result = sakke.simulate("_a_a_", limit, 600);
        assertEquals(result, -13);
    }
}
