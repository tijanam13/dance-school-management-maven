package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja vrstu plesa koja se izvodi u skoli plesa.
 * Svaka vrsta plesa ima naziv, kategoriju i cenu casa.
 *
 * @author Tijana
 * @version 1.0
 * @see StavkaUpisnice
 * @see OpstiDomenskiObjekat
 */
public class VrstaPlesa extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator vrste plesa u bazi podataka. */
    private int idVrstaPlesa;

    /** Naziv vrste plesa. */
    private String naziv;

    /** Kategorija kojoj vrsta plesa pripada. */
    private String kategorija;

    /** Cena jednog casa te vrste plesa. */
    private double cenaCasa;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public VrstaPlesa() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute vrste plesa ukljucujuci i ID.
     *
     * @param idVrstaPlesa jedinstveni identifikator vrste plesa
     * @param naziv naziv vrste plesa
     * @param kategorija kategorija vrste plesa
     * @param cenaCasa cena jednog casa
     */
    public VrstaPlesa(int idVrstaPlesa, String naziv, String kategorija, double cenaCasa) {
        this.idVrstaPlesa = idVrstaPlesa;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cenaCasa = cenaCasa;
    }

    /**
     * Konstruktor koji inicijalizuje atribute vrste plesa bez ID-a.
     * Koristi se prilikom kreiranja nove vrste plesa pre unosa u bazu podataka.
     *
     * @param naziv naziv vrste plesa
     * @param kategorija kategorija vrste plesa
     * @param cenaCasa cena jednog casa
     */
    public VrstaPlesa(String naziv, String kategorija, double cenaCasa) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cenaCasa = cenaCasa;
    }

    /**
     * Konstruktor koji inicijalizuje samo naziv i kategoriju vrste plesa.
     * Koristi se prilikom pretrage vrste plesa.
     *
     * @param naziv naziv vrste plesa
     * @param kategorija kategorija vrste plesa
     */
    public VrstaPlesa(String naziv, String kategorija) {
        this.naziv = naziv;
        this.kategorija = kategorija;
    }

    /**
     * Vraca jedinstveni identifikator vrste plesa.
     *
     * @return jedinstveni identifikator vrste plesa
     */
    public int getIdVrstaPlesa() {
        return idVrstaPlesa;
    }

    /**
     * Postavlja jedinstveni identifikator vrste plesa.
     *
     * @param idVrstaPlesa jedinstveni identifikator koji se postavlja
     */
    public void setIdVrstaPlesa(int idVrstaPlesa) {
        this.idVrstaPlesa = idVrstaPlesa;
    }

    /**
     * Vraca naziv vrste plesa.
     *
     * @return naziv vrste plesa
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv vrste plesa.
     *
     * @param naziv naziv koji se postavlja
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca kategoriju vrste plesa.
     *
     * @return kategorija vrste plesa
     */
    public String getKategorija() {
        return kategorija;
    }

    /**
     * Postavlja kategoriju vrste plesa.
     *
     * @param kategorija kategorija koja se postavlja
     */
    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    /**
     * Vraca cenu jednog casa te vrste plesa.
     *
     * @return cena jednog casa
     */
    public double getCenaCasa() {
        return cenaCasa;
    }

    /**
     * Postavlja cenu jednog casa te vrste plesa.
     *
     * @param cenaCasa cena koja se postavlja
     */
    public void setCenaCasa(double cenaCasa) {
        this.cenaCasa = cenaCasa;
    }

    /**
     * Vraca hash kod objekta.
     *
     * @return hash kod
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Poredi dve vrste plesa na osnovu cene casa, naziva i kategorije.
     * Dve vrste plesa su jednake ako imaju istu cenu casa, isti naziv i istu kategoriju.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su vrste plesa jednake, false u svim drugim slucajevima
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
        final VrstaPlesa other = (VrstaPlesa) obj;
        if (Double.doubleToLongBits(this.cenaCasa) != Double.doubleToLongBits(other.cenaCasa)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.kategorija, other.kategorija);
    }

    /**
     * Vraca tekstualnu reprezentaciju vrste plesa u obliku naziva i kategorije.
     *
     * @return string sa nazivom i kategorijom vrste plesa
     */
    @Override
    public String toString() {
        return naziv + " - " + kategorija;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "vrsta_plesa"
     */
    @Override
    public String vratiImeTabele() {
        return "vrsta_plesa";
    }

    /**
     * Vraca listu vrsta plesa kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista vrsta plesa ili null ako dodje do greske
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String naziv = rs.getString("vrsta_plesa.naziv");
                String kategorija = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                VrstaPlesa vrstaPlesa = new VrstaPlesa(idVrstaPlesa, naziv, kategorija, cenaCasa);
                lista.add(vrstaPlesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste vrsta plesa: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja vrste plesa u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "naziv, kategorija, cenaCasa";
    }

    /**
     * Vraca vrednosti atributa vrste plesa koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + naziv + "', '" + kategorija + "', " + cenaCasa;
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju vrste plesa u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a vrste plesa
     */
    @Override
    public String vratiKljucZaWhere() {
        return "vrsta_plesa.idVrstaPlesa=" + idVrstaPlesa;
    }

    /**
     * Vraca string sa vrednostima atributa vrste plesa za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "naziv = '" + naziv + "', kategorija = '" + kategorija + "', cenaCasa = " + cenaCasa;
    }

    /**
     * Vraca jedan objekat vrste plesa kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat vrste plesa ili null ako se pojavi greska
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        VrstaPlesa vp = null;
        try {
            while (rs.next()) {
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String naziv = rs.getString("vrsta_plesa.naziv");
                String kategorija = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                vp = new VrstaPlesa(idVrstaPlesa, naziv, kategorija, cenaCasa);
            }
            return vp;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanje vrste plesa: " + ex.getMessage());
            return null;
        }
    }
}