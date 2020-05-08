
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import simulator.dao.FileTMDao;
import simulator.dao.TMDao;
import simulator.domain.Handler;

public class HandlerTest {
    
    private Handler handle;
    private TMDao tmdao;
    
    @Rule
    public TemporaryFolder tmpf = new TemporaryFolder();
    
    @Before
    public void setUp() {
        tmdao = new FileTMDao(tmpf.getRoot().getAbsolutePath());
        handle = new Handler(tmdao);
    }
    
    @Test
    public void createTMWorks() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        String[][] instructions = new String[3][2];
        instructions[0][0] = "a R qar";
        instructions[0][1] = "qa";
        instructions[1][0] = "b R qb";
        instructions[1][1] = "b L qar";
        instructions[2][0] = "a L qb";
        instructions[2][1] = "qr";
        boolean b = handle.createTM("Pupu", "A cute bunny.", instructions, alphabet, states);
        assertTrue(b);
    }
    
    @Test
    public void getCurrentTMNameWorks() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        String[][] instructions = new String[3][2];
        instructions[0][0] = "a R qar";
        instructions[0][1] = "qa";
        instructions[1][0] = "b R qb";
        instructions[1][1] = "b L qar";
        instructions[2][0] = "a L qb";
        instructions[2][1] = "qr";
        handle.createTM("Pupu", "A cute bunny.", instructions, alphabet, states);
        assertEquals(handle.getCurrentTMName(), "Pupu");
    }
    
    @Test
    public void getCurrentTMDescriptionWorks() {
        char[] alphabet = new char[]{'a','b'};
        String[] states = new String[]{"q0","qar","qb"};
        String[][] instructions = new String[3][2];
        instructions[0][0] = "a R qar";
        instructions[0][1] = "qa";
        instructions[1][0] = "b R qb";
        instructions[1][1] = "b L qar";
        instructions[2][0] = "a L qb";
        instructions[2][1] = "qr";
        handle.createTM("Pupu", "A cute bunny.", instructions, alphabet, states);
        assertEquals(handle.getCurrentTMDescription(), "A cute bunny.");
    }
    
    @Test
    public void setUpStepByStepWorks() {
        assertTrue(handle.setUpStepByStep("abababbaaaaaa", 3000000, 1000000));
    }
    
}
