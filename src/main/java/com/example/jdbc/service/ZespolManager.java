package main.java.com.example.jdbc.service;



import main.java.com.example.jdbc.domain.Zespol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ZespolManager {



    private Connection connection;
    private String url = "jdbc:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=kliwinski" + ";user=kliwinski" + ";password=224657";
    private String createTableKlub = "CREATE TABLE [zespol] (\n" +
            "  [zespol_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,\n" +
            "  [zespol_nazwa_zespolu] VARCHAR(50) NOT NULL,\n" +
            "  [zespol_kraj] VARCHAR(30) NOT NULL\n" +
            ")";

    private PreparedStatement addZespolStmt;
    private PreparedStatement deleteAllZespolsStmt;
    private PreparedStatement getAllZespolsStmt;

    private Statement statement;


    public ZespolManager(){

        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()){
                if ("zespol".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists  = true;
                    break;
                }
            }
            if (!tableExists)
                statement.executeUpdate(createTableKlub);

            addZespolStmt = connection.prepareStatement("INSERT INTO zespol (zespol_nazwa_zespolu, zespol_kraj) VALUES (?, ?)");
            deleteAllZespolsStmt = connection.prepareStatement("DELETE FROM zespol");
            getAllZespolsStmt = connection.prepareStatement("SELECT zespol_id, zespol_nazwa_zespolu, zespol_kraj FROM zespol");


        }

        catch (SQLException e) {
            e.printStackTrace();
        }}

    public Connection getConnection(){
        return connection;
    }

    void clearZespols() {
        try {
            deleteAllZespolsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addZespol (Zespol zespol) {
        int count = 0;
        try {
            addZespolStmt.setString(1, zespol.getNazwa());
            addZespolStmt.setString(2, zespol.getKraj());

            count = addZespolStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Zespol> getAllZespols(){
        List<Zespol> zespol = new ArrayList<Zespol>();

        ResultSet rs = null;
        try {
            rs = getAllZespolsStmt.executeQuery();

            while (rs.next()) {
                Zespol z = new Zespol();
                z.setID(rs.getInt("zespol_id"));
                z.setNazwa(rs.getString("zespol_nazwa_zespolu"));
                z.setKraj(rs.getString("zespol_kraj"));
                zespol.add(z);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zespol;
    }





}
