package com.example.jdbc.domain;

/**
 * Created by Ufo on 2015-11-01.
 */
public class Zespol_has_koncert {

    private int ID;
    private int zespol_id;
    private int koncert_id;

    public Zespol_has_koncert() {
    }

    public Zespol_has_koncert(int zespol_id, int koncert_id) {
        super();
        this.zespol_id = zespol_id;
        this.koncert_id = koncert_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getZespol_id() {
        return zespol_id;
    }

    public void setZespol_id(int zespol_id) {
        this.zespol_id = zespol_id;
    }

    public int getKoncert_id() {
        return koncert_id;
    }

    public void setKoncert_id(int koncert_id) {
        this.koncert_id = koncert_id;
    }
}
