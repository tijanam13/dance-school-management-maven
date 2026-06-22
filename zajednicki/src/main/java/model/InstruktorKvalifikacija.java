package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja vezu izmedju instruktora i kvalifikacije koju instruktor poseduje.
 * Sadrzi informacije o nivou i datumu sticanja kvalifikacije.
 *
 * @author Tijana
 * @version 1.0
 * @see Instruktor
 * @see Kvalifikacija
 * @see Nivo
 */
public class InstruktorKvalifikacija extends OpstiDomenskiObjekat {

    /** Instruktor koji poseduje kvalifikaciju. */
    private Instruktor instruktor;

    /** Kvalifikacija koju instruktor poseduje. */
    private Kvalifikacija kvalifikacija;

    /** Datum kada je instruktor stekao kvalifikaciju. */
    private Date datumSticanja;

    /** Nivo kvalifikacije koju instruktor poseduje. */
    private Nivo nivo;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public InstruktorKvalifikacija() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute veze instruktor-kvalifikacija.
     *
     * @param instruktor instruktor koji poseduje kvalifikaciju
     * @param kvalifikacija kvalifikacija koju instruktor poseduje
     * @param datumSticanja datum kada je instruktor stekao kvalifikaciju
     * @param nivo nivo kvalifikacije
     * @throws IllegalArgumentException ako su instruktor, kvalifikacija, datumSticanja ili nivo null,
     *                                  ili ako je datum sticanja u buducnosti
     */
    public InstruktorKvalifikacija(Instruktor instruktor, Kvalifikacija kvalifikacija, Date datumSticanja, Nivo nivo) {
        setInstruktor(instruktor);
        setKvalifikacija(kvalifikacija);
        setDatumSticanja(datumSticanja);
        setNivo(nivo);
    }

    /**
     * Vraca instruktora koji poseduje kvalifikaciju.
     *
     * @return instruktor koji poseduje kvalifikaciju
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora koji poseduje kvalifikaciju.
     * Instruktor ne sme biti null.
     *
     * @param instruktor instruktor koji se postavlja
     * @throws IllegalArgumentException ako je instruktor null
     */
    public void setInstruktor(Instruktor instruktor) {
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor ne sme biti null.");
        }
        this.instruktor = instruktor;
    }

    /**
     * Vraca kvalifikaciju koju instruktor poseduje.
     *
     * @return kvalifikacija instruktora
     */
    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    /**
     * Postavlja kvalifikaciju instruktora.
     * Kvalifikacija ne sme biti null.
     *
     * @param kvalifikacija kvalifikacija koja se postavlja
     * @throws IllegalArgumentException ako je kvalifikacija null
     */
    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        if (kvalifikacija == null) {
            throw new IllegalArgumentException("Kvalifikacija ne sme biti null.");
        }
        this.kvalifikacija = kvalifikacija;
    }

    /**
     * Vraca datum sticanja kvalifikacije.
     *
     * @return datum sticanja kvalifikacije
     */
    public Date getDatumSticanja() {
        return datumSticanja;
    }

    /**
     * Postavlja datum sticanja kvalifikacije.
     * Datum ne sme biti null niti u buducnosti.
     *
     * @param datumSticanja datum koji se postavlja
     * @throws IllegalArgumentException ako je datum null ili u buducnosti
     */
    public void setDatumSticanja(Date datumSticanja) {
        if (datumSticanja == null) {
            throw new IllegalArgumentException("Datum sticanja kvalifikacije ne sme biti null.");
        }
        if (datumSticanja.after(new Date())) {
            throw new IllegalArgumentException("Datum sticanja kvalifikacije ne sme biti u budućnosti.");
        }
        this.datumSticanja = datumSticanja;
    }

    /**
     * Vraca nivo kvalifikacije instruktora.
     *
     * @return nivo kvalifikacije
     */
    public Nivo getNivo() {
        return nivo;
    }

    /**
     * Postavlja nivo kvalifikacije instruktora.
     * Nivo ne sme biti null.
     *
     * @param nivo nivo koji se postavlja
     * @throws IllegalArgumentException ako je nivo null
     */
    public void setNivo(Nivo nivo) {
        if (nivo == null) {
            throw new IllegalArgumentException("Nivo kvalifikacije ne sme biti null.");
        }
        this.nivo = nivo;
    }

    /**
     * Vraca hash kod objekta.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dve veze instruktor-kvalifikacija na osnovu instruktora, kvalifikacije i nivoa.
     * Dve veze su jednake ako imaju istog instruktora, istu kvalifikaciju i isti nivo.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su veze jednake, false u svim drugim slucajevima
     */
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

    /**
     * Vraca tekstualnu reprezentaciju veze instruktor-kvalifikacija.
     *
     * @return string sa podacima o instruktoru, kvalifikaciji, datumu sticanja i nivou
     */
    @Override
    public String toString() {
        return "InstruktorKvalifikacija{" + "instruktor=" + instruktor + ", kvalifikacija=" + kvalifikacija + ", datumSticanja=" + datumSticanja + ", nivo=" + nivo + '}';
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "instruktor_kvalifikacija"
     */
    @Override
    public String vratiImeTabele() {
        return "instruktor_kvalifikacija";
    }

    /**
     * Vraca listu veza instruktor-kvalifikacija kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista veza instruktor-kvalifikacija ili null ako dodje do greske
     */
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

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja veze instruktor-kvalifikacija u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "datumSticanja, nivo, idKvalifikacija, idInstruktor";
    }

    /**
     * Vraca vrednosti atributa veze instruktor-kvalifikacija koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + datumSticanja + "', '" + nivo + "', " + kvalifikacija.getIdKvalifikacija() + ", " + instruktor.getIdInstruktor();
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju veze instruktor-kvalifikacija u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a instruktora
     */
    @Override
    public String vratiKljucZaWhere() {
        return "instruktor_kvalifikacija.idInstruktor = " + instruktor.getIdInstruktor();
    }

    /**
     * Vraca string sa vrednostima atributa veze instruktor-kvalifikacija za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "nivo = '" + nivo + "', datumSticanja = '" + datumSticanja + "', idKvalifikacija = " + kvalifikacija.getIdKvalifikacija() + ", idInstruktor = " + instruktor.getIdInstruktor();
    }

    /**
     * Vraca jedan objekat veze instruktor-kvalifikacija kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat veze instruktor-kvalifikacija, ili null ako dodje do greske
     */
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