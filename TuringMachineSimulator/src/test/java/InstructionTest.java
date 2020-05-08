
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import simulator.domain.Instruction;

public class InstructionTest {
    
    private Instruction inis1;
    private Instruction inis2;
    
    public InstructionTest() {
        inis1 = new Instruction('c','R',"qt");
        inis2  = new Instruction("carrot");
    }
    
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void constructor1setsCharacter() {
        assertEquals(inis1.getCharacter(),'c');
    }
    
    @Test
    public void constructor1SetsMovement() {
        assertEquals(inis1.getMovement(),'R');
    }
    
    @Test
    public void constructor1SetsState() {
        assertEquals(inis1.getState(),"qt");
    }
    
    @Test
    public void constructor2SetsCharacterNull() {
        assertEquals(inis2.getCharacter(),0);
    }
    
    @Test
    public void constructor2setsMovementNull() {
        assertEquals(inis2.getMovement(),0);
    }
    
    @Test
    public void constructor2SetsState() {
        assertEquals(inis2.getState(),"carrot");
    }
    
    @Test
    public void equalsWorks1() {
        boolean b = inis1.equals(inis2);
        assertFalse(b);
    }
    
    @Test
    public void equalWorks2() {
        Instruction inis3 = new Instruction('c','R',"qt");
        boolean b = inis3.equals(inis1);
        assertTrue(b);
    }
    
    @Test
    public void equalsWorks3() {
        Instruction inis4 = new Instruction("carrot");
        boolean b = inis4.equals(inis2);
        assertTrue(b);
    }
    
    @Test
    public void equalWorks4() {
        assertTrue(inis1.equals(inis1));
    }
    
     @Test
    public void equalWorks5() {
        assertFalse(inis1.equals("carrot"));
    }
    
    @Test
    public void equalWorks6() {
        String cake = null;
        assertFalse(inis1.equals(cake));
    }
    
    @Test
    public void equalWorks7() {
        assertFalse(inis1.equals(new Instruction('d','R',"qt")));
    }
    
    @Test
    public void equalWorks8() {
        assertFalse(inis1.equals(new Instruction('c','L',"qt")));
    }
    
    @Test
    public void equalWorks9() {
        assertFalse(inis1.equals(new Instruction('c','R',"carrot")));
    }
    
    @Test
    public void toStringWorks() {
        assertEquals(inis1.toString(), "c R qt");
    }
    
    @Test
    public void toStringWorks2() {
        assertEquals(inis2.toString(), "carrot");
    }
}
