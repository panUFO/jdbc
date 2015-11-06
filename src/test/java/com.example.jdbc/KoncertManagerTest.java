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





    @Test
    public void checkConnection()
    {
        assertNotNull(koncertManager.getConnection());
    }



    @Test
    public void addTest(){

        assertEquals(klubManager.addKlub(new Klub("Gdynia", "Desdemona", 200)) , 1);
        klub = klubManager.getAllKlubs().get(0);

        koncert= new Koncert(klub.getID(), nazwa_koncertu_1, ceny_biletow_1);

        assertEquals(koncertManager.addKoncert(koncert), 1);

        assertEquals(koncertManager.getAllKoncerts().get(0).getKlub_id(), klub.getID());
     //   assertEquals(koncertManager.getAllKoncerts().get(0).getNazwa_koncertu(), koncert.getNazwa_koncertu());
     //   assertEquals(koncertManager.getAllKoncerts().get(0).getCeny_biletow(), koncert.getCeny_biletow());

        koncertManager.clearKoncerts();
        klubManager.clearKlubs();

    }



  /*


    @Test
    public void addTest(){
        koncertManager.clearKoncerts();
        klubManager.clearKlubs();
        //zespol = new Zespol("Kyuss", "USA");
        klub = new Klub("Gdynia", "Desdemona", 200);


        koncert = new Koncert(klub.getID(), nazwa_koncertu_1, ceny_biletow_1);
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

        Koncert koncertDB;
        koncertManager.addKoncert(new Koncert(1, nazwa_koncertu_1, ceny_biletow_1));
        koncert = koncertManager.getAllKoncerts().get(0);
        koncertDB = koncertManager.getKoncertById(koncert);

        assertEquals(koncert.getID(), koncertDB.getID());
        assertEquals(koncert.getKlub_id(), koncertDB.getKlub_id());
        assertEquals(koncert.getNazwa_koncertu(), koncertDB.getNazwa_koncertu());
        assertEquals(koncert.getCeny_biletow(), koncertDB.getCeny_biletow());

    }
*/

    @Test
    public void deleteKoncertsTest()
    {
        koncertManager.clearKoncerts();
        assertEquals(koncertManager.getAllKoncerts().size(), 0);

        klubManager.clearKlubs();
        assertEquals(klubManager.getAllKlubs().size(), 0);

    }

}
