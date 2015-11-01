package test.java.com.example.service;

import main.java.com.example.jdbc.domain.Klub;
import main.java.com.example.jdbc.domain.KlubDAO;
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
public class KlubManager implements KlubDAO{


    private Connection connection;
    private String url = "jdbc:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=kliwinski" + ";user=kliwinski" + ";password=224657";
    private String createTableKlub = "CREATE TABLE [klub] (\n" +
            "  [klub_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
            "  [klub_miasto] VARCHAR(50)  NOT NULL,\n" +
            "  [klub_nazwa_klubu] VARCHAR(30) NOT NULL,\n" +
            "  [klub_ilosc_miejsc] VARCHAR(5) NOT NULL\n" +
            ")";

    private PreparedStatement addKlubStmt;
    private PreparedStatement deleteAllKlubsStmt;
    private PreparedStatement getAllKlubsStmt;

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
            deleteAllKlubsStmt = connection.prepareStatement("DELETE FROM klub");
            getAllKlubsStmt = connection.prepareStatement("SELECT klub_id, klub_miasto, klub_nazwa_klubu, klub_ilosc_miejsc FROM klub");


        }

        catch (SQLException e) {
            e.printStackTrace();
        }}

       public Connection getConnection(){
            return connection;
       }

        void clearKlubs() {
            try {
                deleteAllKlubsStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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


}








