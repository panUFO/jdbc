package com.example.jdbc;

import com.example.jdbc.domain.Klub;
import com.example.jdbc.domain.Koncert;
import com.example.jdbc.service.KoncertManager;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class KoncertManagerTest {

    KoncertManager koncertManager = new KoncertManager();
    Koncert koncert;

    private final static String nazwa_koncertu_1 = "Stonery";
    private final static String ceny_biletow_1 = "55zl";
//    private final static Date data_1 = 2015-12-05;


    @Test
    public void checkConnection()
    {
        assertNotNull(koncertManager.getConnection());
    }

    @Test
    public void deleteKoncertsTest()
    {
        koncertManager.clearKoncerts();
        assertEquals(koncertManager.getAllKoncerts().size(), 0);
    }

    @Test
    public void addTest(){
        koncertManager.clearKoncerts();
        koncert = new Koncert(1, nazwa_koncertu_1, ceny_biletow_1, Date.valueOf("2015-12-24") );
        assertEquals(koncertManager.addKoncert(koncert), 1);
        assertEquals(koncertManager.getAllKoncerts().size(), 1);
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

}
