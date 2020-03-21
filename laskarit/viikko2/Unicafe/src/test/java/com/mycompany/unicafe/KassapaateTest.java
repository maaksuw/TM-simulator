
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate k;
    Maksukortti kortti;
    Maksukortti kortti2;
    
    @Before
    public void setUp(){
        k = new Kassapaate();
        kortti = new Maksukortti(1000);
        kortti2 = new Maksukortti(100);
    }
    
    @Test
    public void alussaRahamaaraOikea(){
        assertEquals(100000,k.kassassaRahaa());
    }
    
    @Test
    public void alussaMyytyjenEdullistenLounaidenMaaraOikea(){
        assertEquals(0,k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaMyytyjenMaukkaidenLounaidenMaaraOikea(){
        assertEquals(0,k.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoOnnistuu(){
        k.syoEdullisesti(250);
        assertEquals(100240, k.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoEiOnnistu(){
        k.syoEdullisesti(100);
        assertEquals(100000,k.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdullinen(){
        assertEquals(10,k.syoEdullisesti(250));
    }
    
    @Test
    public void palautetaanRahatEdullinen(){
        assertEquals(100,k.syoEdullisesti(100));
    }
    
    @Test
    public void lounaidenMaaraKasvaaEdullinen(){
        k.syoEdullisesti(240);
        assertEquals(1,k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void lounaidenMaaraEiKasvaEdullinen(){
        k.syoEdullisesti(100);
        assertEquals(0,k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoOnnistuu(){
        k.syoMaukkaasti(420);
        assertEquals(100400, k.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoEiOnnistu(){
        k.syoMaukkaasti(150);
        assertEquals(100000,k.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinMaukas(){
        assertEquals(20,k.syoMaukkaasti(420));
    }    

    @Test
    public void palautetaanRahatMaukas(){
        assertEquals(150,k.syoMaukkaasti(150));
    }
    
    @Test
    public void lounaidenMaaraKasvaaMaukas(){
        k.syoMaukkaasti(420);
        assertEquals(1,k.maukkaitaLounaitaMyyty());
    }

    @Test
    public void lounaidenMaaraEiKasvaMaukas(){
        k.syoMaukkaasti(150);
        assertEquals(0,k.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisenOstoOnnistuuKortilla(){
        assertEquals(true,k.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenOstoKortillaVahentaaSaldoa(){
        k.syoEdullisesti(kortti);
        assertEquals(760,kortti.saldo());
    }
    
    @Test
    public void edullisenOstoKortillaKasvattaaLounaidenMaaraa(){
        k.syoEdullisesti(kortti);
        assertEquals(1,k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josEiSaldoaEdullisenOstoEiOnnistuKortilla(){
        assertEquals(false,k.syoEdullisesti(kortti2));
    }
    
    @Test
    public void josEiSaldoaRahamaaraEiMuutuKortillaEdullinen(){
        k.syoEdullisesti(kortti2);
        assertEquals(100,kortti2.saldo());
    }
    
    @Test
    public void josEiSaldoaLounaidenMaaraEiMuutuKortillaEdullinen(){
        k.syoEdullisesti(kortti2);
        assertEquals(0,k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaEdullinen(){
        k.syoEdullisesti(kortti);
        assertEquals(100000,k.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoOnnistuuKortilla(){
        assertEquals(true,k.syoMaukkaasti(kortti));
    }    
    
    @Test
    public void maukkaanOstoKortillaVahentaaSaldoa(){
        k.syoMaukkaasti(kortti);
        assertEquals(600,kortti.saldo());
    }   
    
    @Test
    public void maukkaanOstoKortillaKasvattaaLounaidenMaaraa(){
        k.syoMaukkaasti(kortti);
        assertEquals(1,k.maukkaitaLounaitaMyyty());
    }    
    
    @Test
    public void josEiSaldoaMaukkaanOstoEiOnnistuKortilla(){
        assertEquals(false,k.syoMaukkaasti(kortti2));
    }    
    
    @Test
    public void josEiSaldoaRahamaaraEiMuutuKortillaMaukas(){
        k.syoMaukkaasti(kortti2);
        assertEquals(100,kortti2.saldo());
    }  
    
    @Test
    public void josEiSaldoaLounaidenMaaraEiMuutuKortillaMaukas(){
        k.syoMaukkaasti(kortti2);
        assertEquals(0,k.maukkaitaLounaitaMyyty());
    }   
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstaessaMaukas(){
        k.syoMaukkaasti(kortti);
        assertEquals(100000,k.kassassaRahaa());
    }   
    
    @Test
    public void lataaRahaaKortilleKasvattaaSaldoa(){
        k.lataaRahaaKortille(kortti, 300);
        assertEquals(1300,kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortilleKasvattaaKassanRahamaaraa(){
        k.lataaRahaaKortille(kortti, 300);
        assertEquals(100300,k.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleEiMuutaSaldoa(){
        k.lataaRahaaKortille(kortti, -100);
        assertEquals(1000,kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortilleEiMuutaKassanRahamaaraa(){
        k.lataaRahaaKortille(kortti, -100);
        assertEquals(100000,k.kassassaRahaa());
    }
}
