package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alussaSaldoOikein(){
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(5);
        assertEquals(15,kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikein(){
        kortti.otaRahaa(3);
        assertEquals(7,kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaLiianVahan(){
        kortti.otaRahaa(15);
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosOnnistuu(){
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosEpaonnistuu(){
        assertEquals(false, kortti.otaRahaa(30));
    }
}
