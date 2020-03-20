
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein(){
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa",kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein(){
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa",kortti.toString());
    }
    
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi(){
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 2.0 euroa",kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi(){
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 2.0 euroa",kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa(){
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa",kortti.toString());
    }
    
    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa(){
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa",kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaSaldoa(){
        kortti.lataaRahaa(-20);
        assertEquals("Kortilla on rahaa 10.0 euroa",kortti.toString());
    }
    
    @Test
    public void kortillaVoiOstaaEdullisenTasarahalla(){
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa",kortti.toString());
    }
    
    @Test
    public void kortillaVoiOstaaMaukkaanTasarahalla(){
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.lataaRahaa(2);
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa",kortti.toString());
    }
}
