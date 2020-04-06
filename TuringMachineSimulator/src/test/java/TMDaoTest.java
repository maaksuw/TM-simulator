
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import simulator.dao.FileDao;
import simulator.dao.TMDao;
import simulator.domain.Handler;

public class TMDaoTest {
    
    private TMDao tmdao;
    private File tempFile;
    
    @Before
    public void setUp() throws IOException{
        tmdao = new TMDao();
        tempFile = File.createTempFile("temp", null);
    }
    
    @After
    public void tearDown(){
        tempFile.delete();
    }
    
    @Test
    public void setsProjectFolderCorrectly(){
        tmdao.setProjectFolder(tempFile.getAbsolutePath());
        assertEquals(tmdao.getProjectFolder().getAbsolutePath(), tempFile.getAbsolutePath());
    }
}
