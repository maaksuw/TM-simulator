
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import simulator.dao.FileTMDao;
import simulator.dao.TMDao;
import simulator.domain.Instruction;
import simulator.domain.TuringMachine;

public class TMDaoTest {
    
    private TMDao tmdao;
    private TuringMachine tm;
    private Instruction[][] table;
    
    @Rule
    public TemporaryFolder tmpf = new TemporaryFolder();
    
    @Before
    public void setUp() {
        tmdao = new FileTMDao(tmpf.getRoot().getAbsolutePath());
        char[]
     alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        Instruction[][] instructions = new Instruction[3][2];
        instructions[0][0] = new Instruction('a','R',"qar");
        instructions[0][1] = new Instruction("qa");
        instructions[1][0] = new Instruction('b','R',"qb");
        instructions[1][1] = new Instruction('b','L',"qar");
        instructions[2][0] = new Instruction('a','L',"qb");
        instructions[2][1] = new Instruction("qr");
        tm = new TuringMachine("name", "description", instructions, alphabet, states);
        table = instructions;
    }
    
    @Test
    public void createTMReturnsTrueIfUniqueName() throws IOException {
        boolean b = false;
        b = tmdao.create(tm);
        assertTrue(b);
    }
    
    @Test
    public void createTMReturnsFalseIfNameAlreadyExists() throws IOException {
        tmdao.create(tm);
        boolean b = false;
        b = tmdao.create(tm);
        assertFalse(b);
    }
    
    @Test
    public void readNameWorks() throws IOException {
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        assertEquals(tmdao.readName(),"name");
    }
    
    @Test
    public void readDescriptionWorks() throws IOException {
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        tmdao.readName();
        assertEquals(tmdao.readDescription(),"description");
    }
    
    @Test
    public void readDescriptionWorksWithMultiLineDescription() throws IOException {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        Instruction[][] instructions = new Instruction[3][2];
        instructions[0][0] = new Instruction('a','R',"qar");
        instructions[0][1] = new Instruction("qa");
        instructions[1][0] = new Instruction('b','R',"qb");
        instructions[1][1] = new Instruction('b','L',"qar");
        instructions[2][0] = new Instruction('a','L',"qb");
        instructions[2][1] = new Instruction("qr");
        tm = new TuringMachine("name", "description\nFor testing!", instructions, alphabet, states);
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        tmdao.readName();
        assertEquals(tmdao.readDescription(),"description\nFor testing!");
    }
    
    @Test
    public void readAlphabetWorks() throws IOException {
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        tmdao.readName();
        tmdao.readDescription();
        assertEquals(tmdao.readAlphabet(),"a b");
    }
    
    @Test
    public void readStatesWorks() throws IOException {
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        tmdao.readName();
        tmdao.readDescription();
        tmdao.readAlphabet();
        assertEquals(tmdao.readStates(),"q0 qar qb");
    }
    
    @Test
    public void readTableWorks() throws IOException {
        File f = tmdao.createFile(tm);
        tmdao.prepareReader(f);
        tmdao.readName();
        tmdao.readDescription();
        tmdao.readAlphabet();
        tmdao.readStates();
        boolean b = true;
        String[][] table = tmdao.readTable(3, 2);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (!table[i][j].equals(this.table[i][j].toString())) {
                   b = false; 
                }
            }
        }
        assertTrue(b);
    }

}
