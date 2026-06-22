package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja kvalifikaciju koju instruktor moze da poseduje u skoli plesa.
 * Kvalifikacija se odnosi na odredjeni tip, odnosno vrstu kvalifikacije i organizaciju koja je izdala sertifikat.
 *
 * @author Tijana
 * @version 1.0
 * @see InstruktorKvalifikacija
 * @see OpstiDomenskiObjekat
 */
public class Kvalifikacija extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator kvalifikacije u bazi podataka. */
    private int idKvalifikacija;

    /** Tip kvalifikacije, npr. na koju vrstu plesa se odnosi. */
    private String tip;

    /** Naziv organizacije koja je izdala kvalifikaciju. */
    private String organizacija;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public Kvalifikacija() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute kvalifikacije ukljucujuci i ID.
     *
     * @param idKvalifikacija jedinstveni identifikator kvalifikacije
     * @param tip tip kvalifikacije
     * @param organizacija naziv organizacije koja je izdala kvalifikaciju
     * @throws IllegalArgumentException ako su tip ili organizacija null ili prazni
     */
    public Kvalifikacija(int idKvalifikacija, String tip, String organizacija) {
    	setIdKvalifikacija(idKvalifikacija);
        setTip(tip);
        setOrganizacija(organizacija);
    }

    /**
     * Konstruktor koji inicijalizuje atribute kvalifikacije bez ID-a.
     * Koristi se prilikom kreiranja nove kvalifikacije pre unosa u bazu podataka.
     *
     * @param tip tip kvalifikacije
     * @throws IllegalArgumentException ako su tip ili organizacija null ili prazni
     */
    public Kvalifikacija(String tip, String organizacija) {
    	  setTip(tip);
          setOrganizacija(organizacija);
    }
    
    /**
     * Konstruktor koji se koristi za pretragu kvalifikacija.
     * Dozvoljava null vrednosti kao kriterijume pretrage.
     *
     * @param tip tip kvalifikacije ili null
     * @param organizacija organizacija ili null
     * @param zaPretragu oznaka da je konstruktor za pretragu
     */
    public Kvalifikacija(String tip, String organizacija, boolean zaPretragu) {
        this.tip = tip;
        this.organizacija = organizacija;
    }

    /**
     * Vraca naziv organizacije koja je izdala kvalifikaciju.
     *
     * @return naziv organizacije
     */
    public String getOrganizacija() {
        return organizacija;
    }

    /**
     * Postavlja naziv organizacije koja je izdala kvalifikaciju.
     * Organizacija ne sme biti null niti prazan string.
     *
     * @param organizacija naziv organizacije koji se postavlja
     * @throws IllegalArgumentException ako je organizacija null ili prazna
     */

    public void setOrganizacija(String organizacija) {
        if (organizacija == null || organizacija.trim().isEmpty()) {
            throw new IllegalArgumentException("Organizacija ne sme biti null niti prazan string.");
        }
        this.organizacija = organizacija;
    }

    /**
     * Vraca jedinstveni identifikator kvalifikacije.
     *
     * @return jedinstveni identifikator kvalifikacije
     */
    public int getIdKvalifikacija() {
        return idKvalifikacija;
    }

    /**
     * Postavlja jedinstveni identifikator kvalifikacije.
     *
     * @param idKvalifikacija jedinstveni identifikator koji se postavlja
     */
    public void setIdKvalifikacija(int idKvalifikacija) {
        this.idKvalifikacija = idKvalifikacija;
    }

    /**
     * Vraca tip kvalifikacije.
     *
     * @return tip kvalifikacije
     */
    public String getTip() {
        return tip;
    }
    
    /**
     * Postavlja tip kvalifikacije.
     * Tip ne sme biti null niti prazan string.
     *
     * @param tip tip koji se postavlja
     * @throws IllegalArgumentException ako je tip null ili prazan
     */
    public void setTip(String tip) {
        if (tip == null || tip.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip kvalifikacije ne sme biti null niti prazan string.");
        }
        this.tip = tip;
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
     * Poredi dve kvalifikacije na osnovu tipa i organizacije.
     * Dve kvalifikacije su jednake ako imaju isti tip i istu organizaciju.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su kvalifikacije jednake, false u svim drugim slucajevima
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
        final Kvalifikacija other = (Kvalifikacija) obj;
        if (!Objects.equals(this.tip, other.tip)) {
            return false;
        }
        return Objects.equals(this.organizacija, other.organizacija);
    }

    /**
     * Vraca tekstualnu reprezentaciju kvalifikacije u obliku tipa i organizacije.
     *
     * @return string sa tipom i nazivom organizacije
     */
    @Override
    public String toString() {
        return "Tip: " + tip + ", organizacija: " + organizacija;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "kvalifikacija"
     */
    @Override
    public String vratiImeTabele() {
        return "kvalifikacija";
    }

    /**
     * Vraca listu kvalifikacija kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista kvalifikacija ili null ako nastane greska
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int idKvalifikacija = rs.getInt("kvalifikacija.idKvalifikacija");
                String tip = rs.getString("kvalifikacija.tip");
                String organizacija = rs.getString("kvalifikacija.organizacija");
                Kvalifikacija k = new Kvalifikacija(idKvalifikacija, tip, organizacija);
                lista.add(k);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste kvalifikacija: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja kvalifikacije u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "tip, organizacija";
    }

    /**
     * Vraca vrednosti atributa kvalifikacije koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + tip + "', '" + organizacija + "'";
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju kvalifikacije u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a kvalifikacije
     */
    @Override
    public String vratiKljucZaWhere() {
        return "kvalifikacija.idKvalifikacija = " + idKvalifikacija;
    }

    /**
     * Vraca string sa vrednostima atributa kvalifikacije za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "tip = '" + tip + "', organizacija = '" + organizacija + "'";
    }

    /**
     * Vraca jedan objekat kvalifikacije kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat kvalifikacije ili null ako dodje do greske
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            Kvalifikacija k = null;
            while (rs.next()) {
                int idKvalifikacija = rs.getInt("kvalifikacija.idKvalifikacija");
                String tip = rs.getString("kvalifikacija.tip");
                String organizacija = rs.getString("kvalifikacija.organizacija");
                k = new Kvalifikacija(idKvalifikacija, tip, organizacija);
            }
            return k;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja kvalifikacije: " + ex.getMessage());
            return null;
        }
    }
}