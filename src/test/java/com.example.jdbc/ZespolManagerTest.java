package com.example.jdbc;

import com.example.jdbc.domain.Zespol;
import com.example.jdbc.service.KlubManager;
import com.example.jdbc.service.ZespolManager;

import org.junit.Test;
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
    public void addCheck(){

        zespol = new Zespol(nazwa_1, kraj_1);


    }
}
