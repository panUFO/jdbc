package com.example.jdbc;

import com.example.jdbc.domain.Zespol;
import com.example.jdbc.service.KlubManager;
import com.example.jdbc.service.ZespolManager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZespolManagerTest {

    ZespolManager zespolManager = new ZespolManager();
    Zespol zespol;

    private final static String nazwa_1 = "Ufomammut";
    private final static String kraj_1 = "Italy";



    @Test
    public void checkConnection()
    {
        assertNotNull(zespolManager.getConnection());
    }


    @Test
    public void deleteZespolsTest()
    {
    	zespolManager.clearZespols();
        assertEquals(zespolManager.getAllZespols().size(), 0);
    }


    @Test
    public void addTest(){
        zespolManager.clearZespols();
        zespol = new Zespol(nazwa_1, kraj_1);
        assertEquals(zespolManager.addZespol(zespol), 1);
        assertEquals(zespolManager.getAllZespols().size(), 1);
    }

    /*
    @Test
    public void deleteZespolTest()
    {
        zespol = new Zespol(nazwa_1, kraj_1);

        zespol = zespolManager.getAllZespols().get(0);
        assertEquals(zespolManager.deleteZespol(zespol) , 1);
        assertFalse(zespolManager.getAllZespols().contains(zespol));

    }
*/

    @Test
    public void getZespolByIdTest()
    {

        Zespol zespolDB = null;
        zespolManager.addZespol(new Zespol(nazwa_1, kraj_1));
        zespol = zespolManager.getAllZespols().get(0);
        zespolDB = zespolManager.getZespolById(zespol);


        assertEquals(zespol.getID(), zespolDB.getID());
        assertEquals(zespol.getNazwa(), zespolDB.getNazwa());
        assertEquals(zespol.getKraj(), zespolDB.getKraj());

    }

    @Test
    public void getZespolByNazwaTest()
    {
        zespolManager.clearZespols();
        List<Zespol> zespoly = new ArrayList<Zespol>();
        Zespol zespol = new Zespol(nazwa_1, kraj_1);
        zespolManager.addZespol(zespol);
        zespolManager.addZespol(new Zespol("Czarny", "Lewy"));
        zespolManager.addZespol(new Zespol("Czerwony", "Lewy"));
        zespolManager.addZespol(new Zespol("Czerwony", "Czerwony"));


        zespoly = zespolManager.getZespolByNazwa(zespol);

        assertEquals(zespoly.size(), 3);

        for(int i= 0; i<zespoly.size(); i++)
            assertEquals(zespoly.get(i).getNazwa(), "Lewy");

        zespolManager.clearZespols();
    }



    @Test
    public void updateZespolTest()
    {
        zespolManager.clearZespols();
        zespolManager.addZespol(new Zespol("Kylesa", "USA"));
        zespol = zespolManager.getAllZespols().get(0);

        zespol.setNazwa("Kyuss");
        zespol.setKraj("USA");

        assertEquals(zespolManager.updateZespol(zespol), 1);
        assertEquals(zespolManager.getAllZespols().get(0).getNazwa(), zespol.getNazwa());
        zespolManager.clearZespols();
    }



}

