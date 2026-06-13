/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tijana
 */
public class Instruktor extends OpstiDomenskiObjekat {

    private int idInstruktor;
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String email;
    private List<InstruktorKvalifikacija> instruktorKvalifikacije;

    public Instruktor() {
    }

    public Instruktor(int idInstruktor, String korisnickoIme, String sifra, String ime, String prezime, String email) {
        this.idInstruktor = idInstruktor;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.instruktorKvalifikacije = new ArrayList<>();
    }

    public Instruktor(String korisnickoIme, String sifra, String ime, String prezime, String email) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.instruktorKvalifikacije = new ArrayList<>();
    }

    public Instruktor(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.instruktorKvalifikacije = new ArrayList<>();
    }

    public List<InstruktorKvalifikacija> getInstruktorKvalifikacije() {
        return instruktorKvalifikacije;
    }

    public void setInstruktorKvalifikacije(List<InstruktorKvalifikacija> instruktorKvalifikacije) {
        this.instruktorKvalifikacije = instruktorKvalifikacije;
    }

    public int getIdInstruktor() {
        return idInstruktor;
    }

    public void setIdInstruktor(int idInstruktor) {
        this.idInstruktor = idInstruktor;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiImeTabele() {
        return "instruktor";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        try {
            while (rs.next()) {

                int idInstruktor = rs.getInt("idInstruktor");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                String korisnickoIme = rs.getString("korisnickoIme");
                String sifra = rs.getString("sifra");
                Instruktor i = new Instruktor(idInstruktor, korisnickoIme, sifra, ime, prezime, email);
                lista.add(i);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste instruktora: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneUbaci() {
        return "ime, prezime, email, sifra, korisnickoIme";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + sifra + "', '" + korisnickoIme + "'";
    }

    @Override
    public String vratiKljucZaWhere() {
        return "instruktor.idInstruktor = " + idInstruktor;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', korisnickoIme = '" + korisnickoIme + "', sifra = '" + sifra + "'";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        Instruktor i = null;
        try {

            while (rs.next()) {

                int idInstruktor = rs.getInt("idInstruktor");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                String korisnickoIme = rs.getString("korisnickoIme");
                String sifra = rs.getString("sifra");
                i = new Instruktor(idInstruktor, korisnickoIme, sifra, ime, prezime, email);
            }

            return i;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja instruktora: " + ex.getMessage());
            return null;
        }
    }

}
