
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import simulator.domain.Instruction;
import simulator.domain.Simulator;
import simulator.domain.TuringMachine;

public class SimulatorTest {
    
    private Simulator sakke;
    private TuringMachine tm;
    
    @Before
    public void setUp() {
        char[] alphabet = new char[3];
        alphabet[0] = 'a';
        alphabet[1] = 'b';
        alphabet[2] = '-';
        String[] states = new String[7];
        states[0] = "qA";
        states[1] = "qar";
        states[2] = "qbr";
        states[3] = "qCa";
        states[4] = "qCb";
        states[5] = "qL";
        states[6] = "qB";
        Instruction[][] t = new Instruction[7][3];
        t[0][0] = new Instruction('-','R',"qar");
        t[0][1] = new Instruction('-','R',"qbr");
        t[0][2] = new Instruction("qa");
        t[1][0] = new Instruction('a','R',"qar");
        t[1][1] = new Instruction('b','R',"qar");
        t[1][2] = new Instruction('-','L',"qCa");
        t[2][0] = new Instruction('a','R',"qbr");
        t[2][1] = new Instruction('b','R',"qbr");
        t[2][2] = new Instruction('-','L',"qCb");
        t[3][0] = new Instruction('-','L',"qL");
        t[3][1] = new Instruction("qr");
        t[3][2] = new Instruction("qa");
        t[4][0] = new Instruction("qr");
        t[4][1] = new Instruction('-','L',"qL");
        t[4][2] = new Instruction("qa");
        t[5][0] = new Instruction('a','L',"qB");
        t[5][1]= new Instruction('b','L',"qB");
        t[5][2] = new Instruction("qa");
        t[6][0] = new Instruction('a','L',"qB");
        t[6][1] = new Instruction('b','L',"qB");
        t[6][2] = new Instruction('-','R',"qA");
        tm = new TuringMachine("Makke","Helps in testing.",t,alphabet,states);
        sakke = new Simulator(tm);
    }
    
    @Test
    public void constructorSetsTuringMachineCorrectly() {
        assertEquals(sakke.getTuringMachine(),tm);
    }
    
    @Test
    public void constructorSetsInitialStateCorrectly() {
        assertEquals(sakke.getState(),0);
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
        sakke.setTm(tm2);
        assertEquals(sakke.getTuringMachine(),tm2);
    }
    
    @Test
    public void setTmInitiatesStateCorrectly() {
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
        sakke.setTm(tm2);
        assertEquals(sakke.getState(), 0);
    }
    
    @Test
    public void simulatesCorreclty1() {
        String input = "abbaabaabba";
        assertEquals(sakke.simulate(input),1);
    }
    
    @Test
    public void simulatesCorrectly2() {
        String input = "abbaaaabba";
        assertEquals(sakke.simulate(input),1);
    }
    
    @Test
    public void simulatesCorrectly3() {
        String input = "abbaaabaabba";
        assertEquals(sakke.simulate(input),0);
    }
    
    @Test
    public void simulatesCorrectly4() {
        String input;
        String str = "babbabbbababbababbababbbaabbaaaa";
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str.charAt(i) + str2;
        }
        input = str + str2;
        assertEquals(sakke.simulate(input),1);
    }
    
    @Test
    public void simulatesCorrectly5() {
        String input = "a";
        assertEquals(sakke.simulate(input),1);
    }
    
    @Test
    public void simulatesCorrectly6() {
        String input = "";
        assertEquals(sakke.simulate(input),1);
    }
    
    @Test
    public void simulatesCorrectly7() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTm(tm2);
        String input = "aba";
        assertEquals(sakke.simulate(input),0);
    }
    
    @Test
    public void simulatesCorrectly8() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qA"};
        Instruction[][] inst = new Instruction[2][2];
        inst[0][0] = new Instruction("");
        TuringMachine tm2 = new TuringMachine("name", "description", inst, alphabet, states);
        sakke.setTm(tm2);
        String input = "aba";
        assertEquals(sakke.simulate(input),0);
    }
}
