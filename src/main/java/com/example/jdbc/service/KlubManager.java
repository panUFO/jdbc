package com.example.jdbc.service;

import com.example.jdbc.domain.Klub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ufo on 2015-11-01.
 */
public class KlubManager {


    private Connection connection;
    private String url = "jdbc:jtds:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=kliwinski" + ";user=kliwinski" + ";password=224657";
    private String createTableKlub = "CREATE TABLE [klub] (\n" +
            "  [klub_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
            "  [klub_miasto] VARCHAR(50)  NOT NULL,\n" +
            "  [klub_nazwa_klubu] VARCHAR(30) NOT NULL,\n" +
            "  [klub_ilosc_miejsc] VARCHAR(5) NOT NULL\n" +
            ")";

    private PreparedStatement addKlubStmt;
    private PreparedStatement getAllKlubsStmt;
    private PreparedStatement getKlubByIdStmt;
    private PreparedStatement getKlubByMiastoStmt;
    private PreparedStatement getKlubByNazwaStmt;
    private PreparedStatement getKlubByIMiejscStmt;
    private PreparedStatement deleteKlubStmt;
    private PreparedStatement deleteAllKlubsStmt;
    private PreparedStatement updateKlubStmt;


    private Statement statement;



    public KlubManager(){

        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()){
                if ("klub".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists  = true;
                    break;
                }
            }
            if (!tableExists)
                statement.executeUpdate(createTableKlub);

            addKlubStmt = connection.prepareStatement("INSERT INTO klub (klub_miasto, klub_nazwa_klubu, klub_ilosc_miejsc) VALUES (?, ?, ?)");
            getAllKlubsStmt = connection.prepareStatement("SELECT klub_id, klub_miasto, klub_nazwa_klubu, klub_ilosc_miejsc FROM klub");
            getKlubByIdStmt = connection.prepareStatement("SELECT * FROM klub WHERE klub_id = ?");
            getKlubByMiastoStmt = connection.prepareStatement("SELECT * FROM klub WHERE klub_miasto = ?");
            getKlubByNazwaStmt = connection.prepareStatement("SELECT * FROM klub WHERE klub_nazwa_klubu = ?");
            getKlubByIMiejscStmt = connection.prepareStatement("SELECT * FROM klub WHERE klub_ilosc_miejsc = ?");
            deleteKlubStmt = connection.prepareStatement("DELETE FROM klub WHERE klub_id = ?");
            deleteAllKlubsStmt = connection.prepareStatement("DELETE FROM klub");
            updateKlubStmt = connection.prepareStatement("UPDATE klub SET klub_miasto = ?, klub_nazwa_klubu = ?, klub_ilosc_miejsc = ? WHERE klub_id = ?");

        }

        catch (SQLException e) {
            e.printStackTrace();
        }}

       public Connection getConnection(){
            return connection;
       }


    public int addKlub (Klub klub) {
        int count = 0;
        try {
            addKlubStmt.setString(1, klub.getMiasto());
            addKlubStmt.setString(2, klub.getNazwa());
            addKlubStmt.setInt(3, klub.getIlosc_miejsc());

            count = addKlubStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Klub> getAllKlubs(){
        List<Klub> klub = new ArrayList<Klub>();

        ResultSet rs = null;
        try {
            rs = getAllKlubsStmt.executeQuery();

            while (rs.next()) {
                Klub k = new Klub();
                k.setID(rs.getInt("klub_id"));
                k.setMiasto(rs.getString("klub_miasto"));
                k.setNazwa(rs.getString("klub_nazwa_klubu"));
                k.setIlosc_miejsc(rs.getInt("klub_ilosc_miejsc"));
                klub.add(k);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return klub;
    }

    public Klub getKlubById (Klub klub) {

        try {
            getKlubByIdStmt.setInt(1, klub.getID());
            ResultSet rs = getKlubByIdStmt.executeQuery();

            while (rs.next()){
                klub = new Klub(rs.getString("klub_miasto"), rs.getString("klub_nazwa_klubu"), rs.getInt("klub_ilosc_miejsc"));
                klub.setID(rs.getInt("klub_id"));
                return klub;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Klub> getKlubByMiasto (Klub klub) {
        List<Klub> kluby = new ArrayList<Klub>();

        try {
            ResultSet rs = getKlubByMiastoStmt.executeQuery();
            while (rs.next()) {
                Klub k = new Klub();
                k.setID(rs.getInt("klub_id"));
                k.setMiasto(rs.getString("klub_miasto"));
                k.setNazwa(rs.getString("klub_nazwa_klubu"));
                k.setIlosc_miejsc(rs.getInt("klub_ilosc_miejsc"));
                kluby.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kluby;
    }

    public List<Klub> getKlubByNazwa (Klub klub) {
        List<Klub> kluby = new ArrayList<Klub>();

        try {
            ResultSet rs = getKlubByNazwaStmt.executeQuery();
            while (rs.next()) {
                Klub k = new Klub();
                k.setID(rs.getInt("klub_id"));
                k.setMiasto(rs.getString("klub_miasto"));
                k.setNazwa(rs.getString("klub_nazwa_klubu"));
                k.setIlosc_miejsc(rs.getInt("klub_ilosc_miejsc"));
                kluby.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kluby;
    }

    public List<Klub> getKlubByIMiejsc (Klub klub) {
        List<Klub> kluby = new ArrayList<Klub>();

        try {
            ResultSet rs = getKlubByIMiejscStmt.executeQuery();
            while (rs.next()) {
                Klub k = new Klub();
                k.setID(rs.getInt("klub_id"));
                k.setMiasto(rs.getString("klub_miasto"));
                k.setNazwa(rs.getString("klub_nazwa_klubu"));
                k.setIlosc_miejsc(rs.getInt("klub_ilosc_miejsc"));
                kluby.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kluby;
    }

    public int deleteKlub (Klub klub) {
        int count = 0;
        try {

            deleteKlubStmt.setInt(1, klub.getID());
            count = addKlubStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void clearKlubs() {
        try {
            deleteAllKlubsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateKlub (Klub klub) {
        int count = 0;
        try {
            updateKlubStmt.setString(1, klub.getMiasto());
            updateKlubStmt.setString(2, klub.getNazwa());
            updateKlubStmt.setInt(3, klub.getIlosc_miejsc());
            updateKlubStmt.setInt(4, klub.getID());

            count = updateKlubStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}








