
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import simulator.domain.TuringMachine;

public class TuringMachineTest {
    
    private TuringMachine tm;
    
    @Before
    public void setUp() {
        ArrayList<String> inst = new ArrayList<>();
        inst.add("33L");
        inst.add("21L");
        inst.add("10R");
        inst.add("00R");
        tm = new TuringMachine();
        tm.create("name", "description", inst);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tmSetsNameCorrectly(){
        assertEquals(tm.getName(),"name");
    }
    
    @Test
    public void tmSetsDescriptionCorrectly(){
        assertEquals(tm.getDesc(),"description");
    }
    
    @Test
    public void tmSetsTableCorrectly(){
        ArrayList<String> inst = new ArrayList<>();
        inst.add("33L");
        inst.add("21L");
        inst.add("10R");
        inst.add("00R");
        assertEquals(tm.getTable(),inst);
    }
    
    @Test
    public void tmtoStringCorrect(){
        assertEquals(tm.toStringTable(),"33L\n21L\n10R\n00R");
    }
}
