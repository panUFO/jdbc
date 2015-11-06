package com.example.jdbc.service;

import com.example.jdbc.domain.Koncert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class KoncertManager {

    private Connection connection;
    private String url = "jdbc:jtds:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=kliwinski" + ";user=kliwinski" + ";password=224657";
    private String createTableKoncert = "CREATE TABLE [koncert] (\n" +
            "  [koncert_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
            "  [koncert_klub_id] INTEGER  NOT NULL REFERENCES klub(klub_id) ,\n" +
            "  [nazwa_koncertu] VARCHAR(50) NOT NULL,\n" +
            "  [ceny_biletow] DECIMAL\n" +
            ")";


    private PreparedStatement addKoncertStmt;
    private PreparedStatement getAllKoncertsStmt;
    private PreparedStatement getKoncertByIdStmt;
    private PreparedStatement getKoncertByKlubIdStmt;
    private PreparedStatement getKoncertByNazwaKoncetuStmt;
    private PreparedStatement getKoncertByCenyBiletowStmt;
    private PreparedStatement deleteKoncertStmt;
    private PreparedStatement deleteAllKoncertsStmt;
    private PreparedStatement updateKoncertStmt;


    private Statement statement;


    public KoncertManager(){

        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()){
                if ("koncert".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists  = true;
                    break;
                }
            }
            if (!tableExists)
                statement.executeUpdate(createTableKoncert);


            addKoncertStmt = connection.prepareStatement("INSERT INTO koncert (koncert_klub_id, nazwa_koncertu, ceny_biletow) VALUES (?, ?, ?)");
            getAllKoncertsStmt = connection.prepareStatement("SELECT koncert_klub_id, nazwa_koncertu, ceny_biletow FROM koncert");
            getKoncertByIdStmt = connection.prepareStatement("SELECT * FROM koncert WHERE koncert_id = ?");
            getKoncertByKlubIdStmt = connection.prepareStatement("SELECT * FROM koncert WHERE koncert_klub_id = ?");
            getKoncertByNazwaKoncetuStmt = connection.prepareStatement("SELECT * FROM koncert WHERE nazwa_koncertu = ?");
            getKoncertByCenyBiletowStmt = connection.prepareStatement("SELECT * FROM koncert WHERE ceny_biletow = ?");
            deleteKoncertStmt = connection.prepareStatement("DELETE FROM koncert WHERE koncert_id = ?");
            deleteAllKoncertsStmt = connection.prepareStatement("DELETE FROM koncert");
            updateKoncertStmt = connection.prepareStatement("UPDATE koncert SET koncert_klub_id = ?, nazwa_koncertu = ?, ceny_biletow = ? WHERE koncert_id = ?");


        }

        catch (SQLException e) {
            e.printStackTrace();
        }}

    public Connection getConnection(){
        return connection;
    }


    public int addKoncert (Koncert koncert) {
        int count = 0;
        try {
            addKoncertStmt.setInt(1, koncert.getKlub_id());
            addKoncertStmt.setString(2, koncert.getNazwa_koncertu());
            addKoncertStmt.setString(3, koncert.getCeny_biletow());


            count = addKoncertStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Koncert> getAllKoncerts(){
        List<Koncert> koncert = new ArrayList<Koncert>();

        ResultSet rs = null;
        try {
            rs = getAllKoncertsStmt.executeQuery();

            while (rs.next()) {
                Koncert k = new Koncert();
                k.setID(rs.getInt("koncert_id"));
                k.setKlub_id(rs.getInt("koncert_klub_id"));
                k.setNazwa_koncertu(rs.getString("nazwa_koncertu"));
                k.setCeny_biletow(rs.getString("ceny_biletow"));
                koncert.add(k);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koncert;
    }

    public Koncert getKoncertById (Koncert koncert) {

        try {
            getKoncertByIdStmt.setInt(1, koncert.getID());
            ResultSet rs = getKoncertByIdStmt.executeQuery();

            while (rs.next()){
                koncert = new Koncert(rs.getInt("klub_id"), rs.getString("nazwa_koncertu"), rs.getString("ceny_biletow"));
                koncert.setID(rs.getInt("koncert_id"));
                return koncert;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Koncert> getKoncertByKlubId (Koncert koncert) {
        List<Koncert> koncerty = new ArrayList<Koncert>();

        try {
            ResultSet rs = getKoncertByKlubIdStmt.executeQuery();
            while (rs.next()) {
                Koncert k = new Koncert();
                k.setID(rs.getInt("koncert_id"));
                k.setKlub_id(rs.getInt("koncert_klub_id"));
                k.setNazwa_koncertu(rs.getString("nazwa_koncertu"));
                k.setCeny_biletow(rs.getString("ceny_biletow"));
                koncerty.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koncerty;
    }

    public List<Koncert> getKoncertByNazwaKoncetu (Koncert koncert) {
        List<Koncert> koncerty = new ArrayList<Koncert>();

        try {
            ResultSet rs = getKoncertByNazwaKoncetuStmt.executeQuery();
            while (rs.next()) {
                Koncert k = new Koncert();
                k.setID(rs.getInt("koncert_id"));
                k.setKlub_id(rs.getInt("koncert_klub_id"));
                k.setNazwa_koncertu(rs.getString("nazwa_koncertu"));
                k.setCeny_biletow(rs.getString("ceny_biletow"));
                koncerty.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koncerty;
    }

    public List<Koncert> getKoncertByCenyBiletowStmt (Koncert koncert) {
        List<Koncert> koncerty = new ArrayList<Koncert>();

        try {
            ResultSet rs = getKoncertByNazwaKoncetuStmt.executeQuery();
            while (rs.next()) {
                Koncert k = new Koncert();
                k.setID(rs.getInt("koncert_id"));
                k.setKlub_id(rs.getInt("koncert_klub_id"));
                k.setNazwa_koncertu(rs.getString("nazwa_koncertu"));
                k.setCeny_biletow(rs.getString("ceny_biletow"));
                koncerty.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koncerty;
    }


    public int deleteKlub (Koncert koncert) {
        int count = 0;
        try {

            deleteKoncertStmt.setInt(1, koncert.getID());
            count = addKoncertStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void clearKoncerts() {
        try {
            deleteAllKoncertsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateKlub (Koncert koncert) {
        int count = 0;
        try {
            updateKoncertStmt.setInt(1, koncert.getKlub_id());
            updateKoncertStmt.setString(2, koncert.getNazwa_koncertu());
            updateKoncertStmt.setString(3, koncert.getCeny_biletow());

            count = updateKoncertStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



}



