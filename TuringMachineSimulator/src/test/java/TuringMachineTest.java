
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import simulator.domain.Instruction;
import simulator.domain.TuringMachine;

public class TuringMachineTest {
    
    private TuringMachine tm;
    
    @Before
    public void setUp() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        Instruction[][] instructions = new Instruction[3][2];
        instructions[0][0] = new Instruction('a','R',"qar");
        instructions[0][1] = new Instruction("qa");
        instructions[1][0] = new Instruction('b','R',"qb");
        instructions[1][1] = new Instruction('b','L',"qar");
        instructions[2][0] = new Instruction('a','L',"qb");
        instructions[2][1] = new Instruction("qr");
        tm = new TuringMachine("name", "description", instructions, alphabet, states);
    }
    
    @Test
    public void tmSetsNameCorrectly() {
        assertEquals(tm.getName(),"name");
    }
    
    @Test
    public void tmSetsDescriptionCorrectly() {
        assertEquals(tm.getDescription(),"description");
    }
    
    @Test
    public void tmSetsTableCorrectly() {
        Instruction[][] table = tm.getTable();
        
        Instruction[][] inst = new Instruction[3][2];
        inst[0][0] = new Instruction('a','R',"qar");
        inst[0][1] = new Instruction("qa");
        inst[1][0] = new Instruction('b','R',"qb");
        inst[1][1] = new Instruction('b','L',"qar");
        inst[2][0] = new Instruction('a','L',"qb");
        inst[2][1] = new Instruction("qr");
        
        boolean same = true;
        
        for (int i = 0; i < inst.length; i++) {
            for (int j = 0; j < inst[0].length; j++) {
                if (!table[i][j].equals(inst[i][j])) same = false;
            }
        }
        assertTrue(same);
    }
    
    @Test
    public void tmSetsAlphabetCorrectly() {
        char[] a = tm.getAlphabet();
        char[] b = new char[]{'a','b'};
        boolean same = true;
        for (int i = 0; i < b.length; i++) {
            if (a[i] != b[i]) same = false;
        }
        assertTrue(same);
    }
    
    @Test
    public void tmSetsStatesCorrectly() {
        String[] a = tm.getStates();
        String[] b = new String[]{"q0","qar","qb"};
        boolean same = true;
        for (int i = 0; i < b.length; i++) {
            if (!a[i].equals(b[i])) same = false;
        }
        assertTrue(same);
    }
    
    @Test
    public void searchReturnsCharacterIndexCorrectly() {
        assertEquals(tm.searchCharacterIndex('b'),1);
    }
    
    @Test
    public void searchReturnsFalseIfCharacterNotInAlphabet() {
        assertEquals(tm.searchCharacterIndex('g'),-1);
    }
    
    @Test
    public void searchCharacterReturnsCorrectCharacter() {
        assertEquals(tm.searchCharacter(0),'a');
    }
    
    @Test
    public void searchCharacterThrowsErrorIfIndexOutOfBoundsPositive() {
        boolean b = false;
        try {
            tm.searchCharacter(15);
        } catch(Exception e) {
            b = true;
        }
        assertTrue(b);
    }
    
    @Test
    public void searchCharacterThrowsErrorIfIndexOutOfBoundsNegative() {
        boolean b = false;
        try {
            tm.searchCharacter(-15);
        } catch(Exception e) {
            b = true;
        }
        assertTrue(b);
    }
    
    @Test
    public void searchReturnsStateIndexCorrectly() {
        assertEquals(tm.searchStateIndex("qar"),1);
    }
    
    @Test
    public void searchReturnsFalseIfStateNotInRegister() {
        assertEquals(tm.searchStateIndex("www"),-1);
    }
    
    @Test
    public void searchStateReturnsStateCorrectly() {
        assertEquals(tm.searchState(0),"q0");
    }
    
    @Test
    public void searchStateThrowsErrorIfIndexOutOfBoundsPositive() {
        boolean b = false;
        try {
            tm.searchState(100);
        } catch(Exception e) {
            b = true;
        }
        assertTrue(b);
    }
    
    @Test
    public void searchStateThrowsErrorIfIndexOutOfBoundsNegative() {
        boolean b = false;
        try {
            tm.searchState(-100);
        } catch(Exception e) {
            b = true;
        }
        assertTrue(b);
    }
    
    @Test
    public void toStringAlphabetCorrect() {
        assertEquals(tm.toStringAlphabet(),"a b");
    }
    
    @Test
    public void toStringStates() {
        assertEquals(tm.toStringStates(),"q0 qar qb");
    }
    
    @Test
    public void toStringTable() {
        assertEquals(tm.toStringTable(),"a R qar; qa\nb R qb; b L qar\na L qb; qr");
    }
}
