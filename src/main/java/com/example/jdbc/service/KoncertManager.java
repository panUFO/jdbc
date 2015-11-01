package main.java.com.example.jdbc.service;

import main.java.com.example.jdbc.domain.Koncert;

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
public class KoncertManager {

    private Connection connection;
    private String url = "jdbc:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=kliwinski" + ";user=kliwinski" + ";password=224657";
    private String createTableKoncert = "CREATE TABLE [koncert] (\n" +
            "  [koncert_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
            "  [koncert_klub_id] INTEGER  NOT NULL REFERENCES klub(klub_id) ,\n" +
            "  [nazwa_koncertu] VARCHAR(50) NOT NULL,\n" +
            "  [ceny_biletow] DECIMAL,\n" +
            "  [data] DATE NOT NULL\n" +
            ")";

    private PreparedStatement addKoncertStmt;
    private PreparedStatement deleteAllKoncertsStmt;
    private PreparedStatement getAllKoncertsStmt;

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

            addKoncertStmt = connection.prepareStatement("INSERT INTO koncert (koncert_klub_id, nazwa_koncertu, ceny_biletow, data) VALUES (?, ?, ?, ?)");
            deleteAllKoncertsStmt = connection.prepareStatement("DELETE FROM koncert");
            getAllKoncertsStmt = connection.prepareStatement("SELECT koncert_klub_id, nazwa_koncertu, ceny_biletow, data FROM koncert");


        }

        catch (SQLException e) {
            e.printStackTrace();
        }}

    public Connection getConnection(){
        return connection;
    }

    void clearKoncerts() {
        try {
            deleteAllKoncertsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addKoncert (Koncert koncert) {
        int count = 0;
        try {
            addKoncertStmt.setInt(1, koncert.getKlub_id());
            addKoncertStmt.setString(2, koncert.getNazwa_koncertu());
            addKoncertStmt.setString(3, koncert.getCeny_biletow());
            addKoncertStmt.setDate(4, koncert.getData());



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
                k.setData(rs.getDate("data"));
                koncert.add(k);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koncert;
    }


}



