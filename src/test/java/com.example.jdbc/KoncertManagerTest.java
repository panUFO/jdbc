package com.example.jdbc;

import com.example.jdbc.domain.Klub;
import com.example.jdbc.domain.Koncert;
import com.example.jdbc.domain.Zespol;
import com.example.jdbc.service.KlubManager;
import com.example.jdbc.service.KoncertManager;

import java.sql.Date;

import com.example.jdbc.service.ZespolManager;
import org.junit.Test;
import static org.junit.Assert.*;

public class KoncertManagerTest {

    KoncertManager koncertManager = new KoncertManager();
    Koncert koncert;

    ZespolManager zespolManager = new ZespolManager();
    Zespol zespol;

    KlubManager klubManager = new KlubManager();
    Klub klub;

    private final static String nazwa_koncertu_1 = "Stonery";
    private final static String ceny_biletow_1 = "55zl";
//    private final static Date data_1 = 2015-12-05;




    @Test
    public void checkConnection()
    {
        assertNotNull(koncertManager.getConnection());
    }


    @Test
    public void addTest(){
        koncertManager.clearKoncerts();
        klubManager.clearKlubs();
        //zespol = new Zespol("Kyuss", "USA");
        klub = new Klub("Gdynia", "Desdemona", 200);


        koncert = new Koncert(klub.getID(), nazwa_koncertu_1, ceny_biletow_1, Date.valueOf("2015-12-24"));
        assertEquals(klubManager.addKlub(klub), 1);
        assertEquals(klubManager.getAllKlubs().size(), 1);


        //   assertEquals(zespolManager.addZespol(zespol), 1);
     //   assertEquals(zespolManager.getAllZespols().size(), 1);

        assertEquals(koncertManager.addKoncert(koncert), 1);
        assertEquals(koncertManager.getAllKoncerts().size(), 1);
        koncertManager.clearKoncerts();
        klubManager.clearKlubs();
    }

    @Test
    public void getKoncertByIdTest()
    {

        Koncert koncertDB = null;
        koncertManager.addKoncert(new Koncert(1, nazwa_koncertu_1, ceny_biletow_1, Date.valueOf("2015-12-24")));
        koncert = koncertManager.getAllKoncerts().get(0);
        koncertDB = koncertManager.getKoncertById(koncert);

        assertEquals(koncert.getID(), koncertDB.getID());
        assertEquals(koncert.getKlub_id(), koncertDB.getKlub_id());
        assertEquals(koncert.getNazwa_koncertu(), koncertDB.getNazwa_koncertu());
        assertEquals(koncert.getCeny_biletow(), koncertDB.getCeny_biletow());
        assertEquals(koncert.getData(), koncertDB.getData());

    }


    @Test
    public void deleteKoncertsTest()
    {
        koncertManager.clearKoncerts();
        assertEquals(koncertManager.getAllKoncerts().size(), 0);
    }

}
