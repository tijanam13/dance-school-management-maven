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
public class InstruktorKvalifikacija extends OpstiDomenskiObjekat {

    private Instruktor instruktor;
    private Kvalifikacija kvalifikacija;
    private Date datumSticanja;
    private Nivo nivo;

    public InstruktorKvalifikacija() {
    }

    public InstruktorKvalifikacija(Instruktor instruktor, Kvalifikacija kvalifikacija, Date datumSticanja, Nivo nivo) {
        this.instruktor = instruktor;
        this.kvalifikacija = kvalifikacija;
        this.datumSticanja = datumSticanja;
        this.nivo = nivo;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public void setNivo(Nivo nivo) {
        this.nivo = nivo;
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
        final InstruktorKvalifikacija other = (InstruktorKvalifikacija) obj;
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        if (!Objects.equals(this.kvalifikacija, other.kvalifikacija)) {
            return false;
        }
        return this.nivo == other.nivo;
    }

    @Override
    public String toString() {
        return "InstruktorKvalifikacija{" + "instruktor=" + instruktor + ", kvalifikacija=" + kvalifikacija + ", datumSticanja=" + datumSticanja + ", nivo=" + nivo + '}';
    }

    @Override
    public String vratiImeTabele() {
        return "instruktor_kvalifikacija";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {

                String nivo = rs.getString("instruktor_kvalifikacija.nivo");
                Date datum = rs.getDate("instruktor_kvalifikacija.datumSticanja");
                Date datumSticanja = new Date(datum.getTime());
                int idKvalifikacija = rs.getInt("instruktor_kvalifikacija.idKvalifikacija");
                int idInstruktor = rs.getInt("instruktor_kvalifikacija.idInstruktor");
                String organizacija = rs.getString("kvalifikacija.organizacija");
                String tip = rs.getString("kvalifikacija.tip");
                String korisnickoIme = rs.getString("instruktor.korisnickoIme");
                String sifra = rs.getString("instruktor.sifra");
                String ime = rs.getString("instruktor.ime");
                String prezime = rs.getString("instruktor.prezime");
                String email = rs.getString("instruktor.email");

                Instruktor i = new Instruktor(idInstruktor, korisnickoIme, sifra, ime, prezime, email);

                Kvalifikacija k = new Kvalifikacija(idKvalifikacija, tip, organizacija);
                InstruktorKvalifikacija ik = new InstruktorKvalifikacija(i, k, datumSticanja, Nivo.valueOf(nivo));
                lista.add(ik);
            }

            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste instruktor-kvalifikacija: " + ex.getMessage());
            return null;
        }

    }

    @Override
    public String vratiKoloneUbaci() {
        return "datumSticanja, nivo, idKvalifikacija, idInstruktor";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + datumSticanja + "', '" + nivo + "', " + kvalifikacija.getIdKvalifikacija() + ", " + instruktor.getIdInstruktor();
    }

    @Override
    public String vratiKljucZaWhere() {
        return "instruktor_kvalifikacija.idInstruktor = " + instruktor.getIdInstruktor();
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "nivo = '" + nivo + "', datumSticanja = '" + datumSticanja + "', idKvalifikacija = " + kvalifikacija.getIdKvalifikacija() + ", idInstruktor = " + instruktor.getIdInstruktor();

    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            InstruktorKvalifikacija ik = null;
            while (rs.next()) {

                String nivo = rs.getString("instruktor_kvalifikacija.nivo");
                Date datum = rs.getDate("instruktor_kvalifikacija.datumSticanja");
                Date datumSticanja = new Date(datum.getTime());
                int idKvalifikacija = rs.getInt("instruktor_kvalifikacija.idKvalifikacija");
                int idInstruktor = rs.getInt("instruktor_kvalifikacija.idInstruktor");
                String organizacija = rs.getString("kvalifikacija.organizacija");
                String tip = rs.getString("kvalifikacija.tip");
                String korisnickoIme = rs.getString("instruktor.korisnickoIme");
                String sifra = rs.getString("instruktor.sifra");
                String ime = rs.getString("instruktor.ime");
                String prezime = rs.getString("instruktor.prezime");
                String email = rs.getString("instruktor.email");

                Instruktor i = new Instruktor(idInstruktor, korisnickoIme, sifra, ime, prezime, email);

                Kvalifikacija k = new Kvalifikacija(idKvalifikacija, tip, organizacija);
                ik = new InstruktorKvalifikacija(i, k, datumSticanja, Nivo.valueOf(nivo));

            }

            return ik;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja instruktor-kvalifikacije: " + ex.getMessage());
            return null;
        }
    }

}
