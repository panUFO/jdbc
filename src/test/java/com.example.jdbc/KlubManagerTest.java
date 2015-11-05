package com.example.jdbc;

import com.example.jdbc.domain.Klub;
import com.example.jdbc.service.KlubManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getKlubByMiastoTest()
    {
        klubManager.clearKlubs();
        Klub klub = new Klub(miasto_1, nazwa_1, ilosc_miejsc_1);
        klubManager.addKlub(klub);
        List<Klub> kluby = new ArrayList<Klub>();
        kluby = klubManager.getKlubByMiasto(klub);


        //  assertEquals(zespoly.size(), 1);

        for(int i= 0; i<kluby.size(); i++)
            assertEquals(kluby.get(i).getMiasto(), "Gdansk");

    }

    @Test
    public void getKlubByNazwaTest()
    {
        klubManager.clearKlubs();
        Klub klub = new Klub(miasto_1, nazwa_1, ilosc_miejsc_1);
        klubManager.addKlub(klub);
        List<Klub> kluby = new ArrayList<Klub>();
        kluby = klubManager.getKlubByNazwa(klub);


        //  assertEquals(zespoly.size(), 1);

        for(int i= 0; i<kluby.size(); i++)
            assertEquals(kluby.get(i).getNazwa(), "B90");

    }

    @Test
    public void getKlubByIloscMiejscTest()
    {
        klubManager.clearKlubs();
        Klub klub = new Klub(miasto_1, nazwa_1, ilosc_miejsc_1);
        klubManager.addKlub(klub);
        List<Klub> kluby = new ArrayList<Klub>();
        kluby = klubManager.getKlubByIMiejsc(klub);


        //  assertEquals(zespoly.size(), 1);

        for(int i= 0; i<kluby.size(); i++)
            assertEquals(kluby.get(i).getIlosc_miejsc(), 200);

    }

    @Test
    public void deleteKlubTest()
    {

        assertEquals(klubManager.addKlub(new Klub(miasto_1, nazwa_1, ilosc_miejsc_1)), 1);
        klub = klubManager.getAllKlubs().get(0);
        assertEquals(klubManager.deleteKlub(klub) , 1);
        assertFalse(klubManager.getAllKlubs().contains(klub));

    }


    @Test
    public void deleteKlubsTest()
    {
        klubManager.clearKlubs();
        assertEquals(klubManager.getAllKlubs().size(), 0);
    }



    @Test
    public void updateKlubTest()
    {
        klubManager.clearKlubs();
        klubManager.addKlub(new Klub("Warszawa", "Progresja", 1000));
        klub = klubManager.getAllKlubs().get(0);

        klub.setMiasto("Poznan");
        klub.setNazwa("ABC");
        klub.setIlosc_miejsc(200);

        assertEquals(klubManager.updateKlub(klub), 1);

        assertEquals(klubManager.getAllKlubs().get(0).getMiasto(), "Poznan");
        assertEquals(klubManager.getAllKlubs().get(0).getNazwa(), "ABC");
        assertEquals(klubManager.getAllKlubs().get(0).getIlosc_miejsc(), 200);

    }







}
