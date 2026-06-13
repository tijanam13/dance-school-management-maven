/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tijana
 */
public class Polaznik extends OpstiDomenskiObjekat {

    private int idPolaznik;
    private String ime;
    private String prezime;
    private String email;
    private UzrasnaKategorija uzrasnaKategorija;

    public Polaznik() {
    }

    public Polaznik(int idPolaznik, String ime, String prezime, String email, UzrasnaKategorija uzrasnaKategorija) {
        this.idPolaznik = idPolaznik;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.uzrasnaKategorija = uzrasnaKategorija;
    }

    public Polaznik(String ime, String prezime, String email, UzrasnaKategorija uzrasnaKategorija) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.uzrasnaKategorija = uzrasnaKategorija;
    }

    public int getIdPolaznik() {
        return idPolaznik;
    }

    public void setIdPolaznik(int idPolaznik) {
        this.idPolaznik = idPolaznik;
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

    public UzrasnaKategorija getUzrasnaKategorija() {
        return uzrasnaKategorija;
    }

    public void setUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) {
        this.uzrasnaKategorija = uzrasnaKategorija;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Polaznik other = (Polaznik) obj;
        return this.idPolaznik == other.idPolaznik;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiImeTabele() {
        return "polaznik";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {

                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                String ime = rs.getString("polaznik.ime");
                String prezime = rs.getString("polaznik.prezime");
                String email = rs.getString("polaznik.email");
                UzrasnaKategorija uzrast = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                Polaznik polaznik = new Polaznik(idPolaznik, ime, prezime, email, uzrast);
                lista.add(polaznik);
            }

            return lista;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste polaznika: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneUbaci() {
        return "ime, prezime, email, idUzrast";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', " + uzrasnaKategorija.getIdUzrast();
    }

    @Override
    public String vratiKljucZaWhere() {
        return "polaznik.idPolaznik = " + idPolaznik;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', idUzrast = " + uzrasnaKategorija.getIdUzrast();
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        Polaznik p = null;
        try {
            while (rs.next()) {

                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                String ime = rs.getString("polaznik.ime");
                String prezime = rs.getString("polaznik.prezime");
                String email = rs.getString("polaznik.email");
                UzrasnaKategorija uzrast = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                p = new Polaznik(idPolaznik, ime, prezime, email, uzrast);
            }

            return p;

        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja polaznika: " + ex.getMessage());
            return null;
        }
    }

}
