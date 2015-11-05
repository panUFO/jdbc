package com.example.jdbc.domain;

/**
 * Created by Ufo on 2015-10-29.
 */
public class Klub {

    private int ID;
    private String miasto;
    private String nazwa;
    private int ilosc_miejsc;

    public Klub() {
    }

    public Klub(String miasto, String nazwa, int ilosc_miejsc) {
        super();
        this.miasto = miasto;
        this.nazwa = nazwa;
        this.ilosc_miejsc = ilosc_miejsc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIlosc_miejsc() {
        return ilosc_miejsc;
    }

    public void setIlosc_miejsc(int ilosc_miejsc) {
        this.ilosc_miejsc = ilosc_miejsc;
    }
}
