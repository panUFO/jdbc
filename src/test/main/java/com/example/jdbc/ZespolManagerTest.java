package test.main.java.com.example.jdbc;

import main.java.com.example.jdbc.domain.Zespol;
import main.java.com.example.jdbc.service.ZespolManager;

import org.junit.Test;
import static org.junit.Assert.*;


public class ZespolManagerTest {

    ZespolManager zespolManager = new ZespolManager();
    Zespol zespol;

    @Test
    public void checkConnection()
    {
        assertNotNull(zespolManager.getConnection());
    }





}
