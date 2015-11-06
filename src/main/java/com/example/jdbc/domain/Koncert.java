package com.example.jdbc.domain;


/**
 * Created by Ufo on 2015-10-29.
 */
public class Koncert {

    private int ID;
    private int klub_id;
    private String nazwa_koncertu;
    private String ceny_biletow;


    public Koncert() {
    }

    public Koncert(int klub_id, String nazwa_koncertu, String ceny_biletow) {
            super();
            this.klub_id = klub_id;
        this.nazwa_koncertu = nazwa_koncertu;
        this.ceny_biletow = ceny_biletow;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getKlub_id() {
        return klub_id;
    }

    public void setKlub_id(int klub_id) {
        this.klub_id = klub_id;
    }

    public String getNazwa_koncertu() {
        return nazwa_koncertu;
    }

    public void setNazwa_koncertu(String nazwa_koncertu) {
        this.nazwa_koncertu = nazwa_koncertu;
    }

    public String getCeny_biletow() {
        return ceny_biletow;
    }

    public void setCeny_biletow(String ceny_biletow) {
        this.ceny_biletow = ceny_biletow;
    }

}