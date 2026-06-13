/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tijana
 */
public class Upisnica extends OpstiDomenskiObjekat {

    private int idUpisnica;
    private Date datumUpisa;
    private double ukupnaClanarina;
    private List<StavkaUpisnice> listaStavke = new ArrayList<>();
    private Instruktor instruktor;
    private Polaznik polaznik;

    public Upisnica() {
    }

    public Upisnica(int idUpisnica, Date datumUpisa, double ukupnaClanarina, Instruktor instruktor, Polaznik polaznik) {
        this.idUpisnica = idUpisnica;
        this.datumUpisa = datumUpisa;
        this.ukupnaClanarina = ukupnaClanarina;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }

    public Upisnica(Date datumUpisa, double ukupnaClanarina, Instruktor instruktor, Polaznik polaznik) {
        this.datumUpisa = datumUpisa;
        this.ukupnaClanarina = ukupnaClanarina;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }

    public int getIdUpisnica() {
        return idUpisnica;
    }

    public void setIdUpisnica(int idUpisnica) {
        this.idUpisnica = idUpisnica;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) throws Exception {
        if (datumUpisa.after(new Date())) {
            throw new Exception("Datum se ne sme odnositi na budućnost");
        } else {
            this.datumUpisa = datumUpisa;
        }
        this.datumUpisa = datumUpisa;
    }

    public double getUkupnaClanarina() {
        return ukupnaClanarina;
    }

    public void setUkupnaClanarina(double ukupnaClanarina) {
        this.ukupnaClanarina = ukupnaClanarina;
    }

    public List<StavkaUpisnice> getListaStavke() {
        return listaStavke;
    }

    public void setListaStavke(List<StavkaUpisnice> listaStavke) {
        this.listaStavke = listaStavke;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
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
        final Upisnica other = (Upisnica) obj;
        if (this.idUpisnica != other.idUpisnica) {
            return false;
        }
        return Objects.equals(this.datumUpisa, other.datumUpisa);
    }

    @Override
    public String toString() {
        return "Datum upisa je " + datumUpisa + ", ukupna članarina je " + ukupnaClanarina + ", izabrane vrste plesa su:\n" + ispisiListu();
    }

    private String ispisiListu() {

        String lista = "";
        for (StavkaUpisnice stavkaUpisnice : listaStavke) {
            lista += stavkaUpisnice.toString() + "\n";
        }
        return lista;
    }

    @Override
    public String vratiImeTabele() {
        return "upisnica";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {

                int idUpisnica = rs.getInt("upisnica.idUpisnica");
                double ukupnaClanarina = rs.getDouble("upisnica.ukupnaClanarina");
                int idInstruktor = rs.getInt("upisnica.idInstruktor");
                int idPolaznik = rs.getInt("upisnica.idPolaznik");
                String korisnickoIme = rs.getString("instruktor.korisnickoIme");
                String sifra = rs.getString("instruktor.sifra");
                String imeInst = rs.getString("instruktor.ime");
                String prezimeInst = rs.getString("instruktor.prezime");
                String emailInst = rs.getString("instruktor.email");
                String imePol = rs.getString("polaznik.ime");
                String prezimePol = rs.getString("polaznik.prezime");
                String emailPol = rs.getString("polaznik.email");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String godine = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");

                Date datum = rs.getDate("upisnica.datumUpisa");
                Date datumUpisa = new Date(datum.getTime());

                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, godine, naziv);
                Instruktor i = new Instruktor(idInstruktor, korisnickoIme, sifra, imeInst, prezimeInst, emailInst);
                Polaznik p = new Polaznik(idPolaznik, imePol, prezimePol, emailPol, uk);
                Upisnica upisnica = new Upisnica(idUpisnica, datumUpisa, ukupnaClanarina, i, p);
                lista.add(upisnica);

            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste upisnica: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneUbaci() {
        return "datumUpisa, ukupnaClanarina, idInstruktor, idPolaznik";
    }

    @Override
    public String vratiVrednostiUbaci() {
        Date datum = new java.sql.Date(datumUpisa.getTime());
        return "'" + datum + "', " + ukupnaClanarina + ", " + instruktor.getIdInstruktor() + ", " + polaznik.getIdPolaznik();
    }

    @Override
    public String vratiKljucZaWhere() {
        return "upisnica.idUpisnica = " + idUpisnica;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        Date datum = new java.sql.Date(datumUpisa.getTime());
        return "datumUpisa = '" + datum + "', ukupnaClanarina = " + ukupnaClanarina + ", idInstruktor = " + instruktor.getIdInstruktor() + ", idPolaznik = " + polaznik.getIdPolaznik();
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            Upisnica u = null;
            while (rs.next()) {

                int idUpisnica = rs.getInt("upisnica.idUpisnica");
                double ukupnaClanarina = rs.getDouble("upisnica.ukupnaClanarina");
                int idInstruktor = rs.getInt("upisnica.idInstruktor");
                int idPolaznik = rs.getInt("upisnica.idPolaznik");
                String korisnickoIme = rs.getString("instruktor.korisnickoIme");
                String sifra = rs.getString("instruktor.sifra");
                String imeInst = rs.getString("instruktor.ime");
                String prezimeInst = rs.getString("instruktor.prezime");
                String emailInst = rs.getString("instruktor.email");
                String imePol = rs.getString("polaznik.ime");
                String prezimePol = rs.getString("polaznik.prezime");
                String emailPol = rs.getString("polaznik.email");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String godine = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");

                Date datum = rs.getDate("upisnica.datumUpisa");
                Date datumUpisa = new Date(datum.getTime());

                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, godine, naziv);
                Instruktor i = new Instruktor(idInstruktor, korisnickoIme, sifra, imeInst, prezimeInst, emailInst);
                Polaznik p = new Polaznik(idPolaznik, imePol, prezimePol, emailPol, uk);
                u = new Upisnica(idUpisnica, datumUpisa, ukupnaClanarina, i, p);

            }
            return u;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja upisnice: " + e.getMessage());
            return null;
        }
    }

}
