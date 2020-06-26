package com.example.demo.model;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Field
   @NotNull
    private String name;

    @Field
    @NotNull
    private String vorname;

    @Field(store = Store.NO)
    @NotNull
    private String email;

    @Field
    @NotNull
    private String telefon;

    @Field
    @NotNull
    private String strasse;

    @Field
    @NotNull
    private String ort;

    @Field
    @NotNull
    private String plz;

    @Field
    @NotNull
    private String sex;

    @Field
    @NotNull
    private String geburtstag;

    @Field
    @NotNull
    private String spitzname;



    public User(int id, String name, String vorname, String email, String telefon, String strasse, String ort, String plz, String sex, String geburtstag, String spitzname) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.strasse = strasse;
        this.ort = ort;
        this.plz = plz;
        this.sex = sex;
        this.geburtstag = geburtstag;
        this.spitzname = spitzname;
    }

    public User(String name) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getSpitzname() {
        return spitzname;
    }

    public void setSpitzname(String spitzname) {
        this.spitzname = spitzname;
    }
}
