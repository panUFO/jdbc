package com.example.jdbc.domain;

/**
 * Created by Ufo on 2015-10-29.
 */
public class Zespol {

    private int ID;
    private String nazwa;
    private String kraj;

    public Zespol() {
    }


    public Zespol(String nazwa, String kraj) {
        super();
        this.nazwa = nazwa;
        this.kraj = kraj;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }
}

