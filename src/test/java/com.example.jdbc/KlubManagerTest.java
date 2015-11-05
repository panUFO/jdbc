package com.example.jdbc;

import com.example.jdbc.domain.Klub;
import com.example.jdbc.service.KlubManager;
import org.junit.Test;
import static org.junit.Assert.*;

public class KlubManagerTest {

    KlubManager klubManager = new KlubManager();
    Klub klub;

    private final static String miasto_1 = "Gdansk";
    private final static String nazwa_1 = "B90";
    private final static int ilosc_miejsc_1 = 200;

    @Test
    public void checkConnection()
    {
        assertNotNull(klubManager.getConnection());
    }

    @Test
    public void deleteKlubsTest()
    {
        klubManager.clearKlubs();
        assertEquals(klubManager.getAllKlubs().size(), 0);
    }

    @Test
    public void addTest(){
        klubManager.clearKlubs();
        klub = new Klub(miasto_1, nazwa_1, ilosc_miejsc_1);
        assertEquals(klubManager.addKlub(klub), 1);
        assertEquals(klubManager.getAllKlubs().size(), 1);
    }

    @Test
    public void getKlubByIdTest()
    {

        Klub klubDB = null;
        klubManager.addKlub(new Klub(miasto_1, nazwa_1, ilosc_miejsc_1));
        klub = klubManager.getAllKlubs().get(0);
        klubDB = klubManager.getKlubById(klub);


        assertEquals(klub.getID(), klubDB.getID());
        assertEquals(klub.getMiasto(), klubDB.getMiasto());
        assertEquals(klub.getNazwa(), klubDB.getNazwa());
        assertEquals(klub.getIlosc_miejsc(), klubDB.getIlosc_miejsc());


    }








}
