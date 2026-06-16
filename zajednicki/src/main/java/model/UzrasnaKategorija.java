package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja uzrasnu kategoriju kojoj polaznik moze da pripada u skoli plesa.
 * Uzrasna kategorija definise opseg godina i naziv koji opisuje grupu polaznika.
 *
 * @author Tijana
 * @version 1.0
 * @see Polaznik
 * @see OpstiDomenskiObjekat
 */
public class UzrasnaKategorija extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator uzrasne kategorije u bazi podataka. */
    private int idUzrast;

    /** Opseg godina koji definise uzrasnu kategoriju, npr. "7-12". */
    private String opsegGodina;

    /** Naziv uzrasne kategorije, npr. "deca". */
    private String naziv;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public UzrasnaKategorija() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute uzrasne kategorije ukljucujuci i ID.
     *
     * @param idUzrast jedinstveni identifikator uzrasne kategorije
     * @param opsegGodina opseg godina koji definise kategoriju
     * @param naziv naziv uzrasne kategorije
     */
    public UzrasnaKategorija(int idUzrast, String opsegGodina, String naziv) {
        this.idUzrast = idUzrast;
        this.opsegGodina = opsegGodina;
        this.naziv = naziv;
    }

    /**
     * Konstruktor koji inicijalizuje atribute uzrasne kategorije bez ID-a.
     * Koristi se prilikom kreiranja nove uzrasne kategorije pre unosa u bazu podataka.
     *
     * @param opsegGodina opseg godina koji definise kategoriju
     * @param naziv naziv uzrasne kategorije
     */
    public UzrasnaKategorija(String opsegGodina, String naziv) {
        this.opsegGodina = opsegGodina;
        this.naziv = naziv;
    }

    /**
     * Vraca jedinstveni identifikator uzrasne kategorije.
     *
     * @return jedinstveni identifikator uzrasne kategorije
     */
    public int getIdUzrast() {
        return idUzrast;
    }

    /**
     * Postavlja jedinstveni identifikator uzrasne kategorije.
     *
     * @param idUzrast jedinstveni identifikator koji se postavlja
     */
    public void setIdUzrast(int idUzrast) {
        this.idUzrast = idUzrast;
    }

    /**
     * Vraca opseg godina koji definise uzrasnu kategoriju.
     *
     * @return opseg godina uzrasne kategorije
     */
    public String getOpsegGodina() {
        return opsegGodina;
    }

    /**
     * Postavlja opseg godina koji definise uzrasnu kategoriju.
     *
     * @param opsegGodina opseg godina koji se postavlja
     */
    public void setOpsegGodina(String opsegGodina) {
        this.opsegGodina = opsegGodina;
    }

    /**
     * Vraca naziv uzrasne kategorije.
     *
     * @return naziv uzrasne kategorije
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv uzrasne kategorije.
     *
     * @param naziv naziv koji se postavlja
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
     * Poredi dve uzrasne kategorije na osnovu jedinstvenog identifikatora.
     * Dve uzrasne kategorije su jednake ako imaju isti ID.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su uzrasne kategorije jednake, false u svim drugim slucajevima
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
        final UzrasnaKategorija other = (UzrasnaKategorija) obj;
        return this.idUzrast == other.idUzrast;
    }

    /**
     * Vraca tekstualnu reprezentaciju uzrasne kategorije sa opsegom godina i nazivom.
     *
     * @return string sa opsegom godina i nazivom kategorije
     */
    @Override
    public String toString() {
        return "Opseg godina: " + opsegGodina + ", naziv: " + naziv;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "uzrasna_kategorija"
     */
    @Override
    public String vratiImeTabele() {
        return "uzrasna_kategorija";
    }

    /**
     * Vraca listu uzrasnih kategorija kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista uzrasnih kategorija ili null ako dodje do greske
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                int idUzrast = rs.getInt("uzrasna_kategorija.idUzrast");
                UzrasnaKategorija u = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                lista.add(u);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste uzrasnih kategorija: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja uzrasne kategorije u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "opsegGodina, naziv";
    }

    /**
     * Vraca vrednosti atributa uzrasne kategorije koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + opsegGodina + "', '" + naziv + "'";
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju uzrasne kategorije u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a uzrasne kategorije
     */
    @Override
    public String vratiKljucZaWhere() {
        return "uzrasna_kategorija.idUzrast = " + idUzrast;
    }

    /**
     * Vraca string sa vrednostima atributa uzrasne kategorije za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "opsegGodina = '" + opsegGodina + "', naziv = '" + naziv + "'";
    }

    /**
     * Vraca jedan objekat uzrasne kategorije kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat uzrasne kategorije ili null ako se pojavi greska
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            UzrasnaKategorija uz = null;
            while (rs.next()) {
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                int idUzrast = rs.getInt("uzrasna_kategorija.idUzrast");
                uz = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
            }
            return uz;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja uzrasne kategorije: " + ex.getMessage());
            return null;
        }
    }
}