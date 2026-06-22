package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja polaznika skole plesa.
 * Polaznik pripada odredjenoj uzrasnoj kategoriji i moze biti upisan na vise kurseva.
 *
 * @author Tijana
 * @version 1.0
 * @see UzrasnaKategorija
 * @see OpstiDomenskiObjekat
 */
public class Polaznik extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator polaznika u bazi podataka. */
    private int idPolaznik;

    /** Ime polaznika. */
    private String ime;

    /** Prezime polaznika. */
    private String prezime;

    /** Email adresa polaznika. */
    private String email;

    /** Uzrasna kategorija kojoj polaznik pripada. */
    private UzrasnaKategorija uzrasnaKategorija;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public Polaznik() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute polaznika ukljucujuci i ID.
     *
     * @param idPolaznik jedinstveni identifikator polaznika
     * @param ime ime polaznika
     * @param prezime prezime polaznika
     * @param email email adresa polaznika
     * @param uzrasnaKategorija uzrasna kategorija kojoj polaznik pripada
     * @throws IllegalArgumentException ako su ime, prezime ili email null ili prazni,
     *                                  ako email ne sadrzi karakter '@',
     *                                  ili ako je uzrasnaKategorija null
     */
    public Polaznik(int idPolaznik, String ime, String prezime, String email, UzrasnaKategorija uzrasnaKategorija) {
        setIdPolaznik(idPolaznik);
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setUzrasnaKategorija(uzrasnaKategorija);
    }

    /**
     * Konstruktor koji inicijalizuje atribute polaznika bez ID-a.
     * Koristi se prilikom kreiranja novog polaznika pre unosa u bazu podataka.
     *
     * @param ime ime polaznika
     * @param prezime prezime polaznika
     * @param email email adresa polaznika
     * @param uzrasnaKategorija uzrasna kategorija kojoj polaznik pripada
     * @throws IllegalArgumentException ako su ime, prezime ili email null ili prazni,
     *                                  ako email ne sadrzi karakter '@',
     *                                  ili ako je uzrasnaKategorija null
     */
    public Polaznik(String ime, String prezime, String email, UzrasnaKategorija uzrasnaKategorija) {
        setIme(ime);
        setPrezime(prezime);
        setEmail(email);
        setUzrasnaKategorija(uzrasnaKategorija);
    }

    /**
     * Vraca jedinstveni identifikator polaznika.
     *
     * @return jedinstveni identifikator polaznika
     */
    public int getIdPolaznik() {
        return idPolaznik;
    }

    /**
     * Postavlja jedinstveni identifikator polaznika.
     *
     * @param idPolaznik jedinstveni identifikator koji se postavlja
     */
    public void setIdPolaznik(int idPolaznik) {
        this.idPolaznik = idPolaznik;
    }

    /**
     * Vraca ime polaznika.
     *
     * @return ime polaznika
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime polaznika.
     * Ime ne sme biti null niti prazan string.
     *
     * @param ime ime koje se postavlja
     * @throws IllegalArgumentException ako je ime null ili prazno
     */
    public void setIme(String ime) {
        if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime polaznika ne sme biti null niti prazan string.");
        }
        this.ime = ime;
    }

    /**
     * Vraca prezime polaznika.
     *
     * @return prezime polaznika
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime polaznika.
     * Prezime ne sme biti null niti prazan string.
     *
     * @param prezime prezime koje se postavlja
     * @throws IllegalArgumentException ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) {
        if (prezime == null || prezime.trim().isEmpty()) {
            throw new IllegalArgumentException("Prezime polaznika ne sme biti null niti prazan string.");
        }
        this.prezime = prezime;
    }

    /**
     * Vraca email adresu polaznika.
     *
     * @return email adresa polaznika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu polaznika.
     * Email ne sme biti null niti prazan string, i mora sadrzati karakter '@'.
     *
     * @param email email adresa koja se postavlja
     * @throws IllegalArgumentException ako je email null, prazan ili ne sadrzi '@'
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email polaznika ne sme biti null niti prazan string.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email polaznika mora sadržati karakter '@'.");
        }
        this.email = email;
    }

    /**
     * Vraca uzrasnu kategoriju kojoj polaznik pripada.
     *
     * @return uzrasna kategorija polaznika
     */
    public UzrasnaKategorija getUzrasnaKategorija() {
        return uzrasnaKategorija;
    }

    /**
     * Postavlja uzrasnu kategoriju kojoj polaznik pripada.
     * Uzrasna kategorija ne sme biti null.
     *
     * @param uzrasnaKategorija uzrasna kategorija koja se postavlja
     * @throws IllegalArgumentException ako je uzrasnaKategorija null
     */
    public void setUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) {
        if (uzrasnaKategorija == null) {
            throw new IllegalArgumentException("Uzrasna kategorija polaznika ne sme biti null.");
        }
        this.uzrasnaKategorija = uzrasnaKategorija;
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
     * Poredi dva polaznika na osnovu jedinstvenog identifikatora.
     * Dva polaznika su jednaka ako imaju isti ID.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su polaznici jednaki, false u svim drugim slucajevima
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
        final Polaznik other = (Polaznik) obj;
        return this.idPolaznik == other.idPolaznik;
    }

    /**
     * Vraca tekstualnu reprezentaciju polaznika u obliku ime i prezime.
     *
     * @return ime i prezime polaznika
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "polaznik"
     */
    @Override
    public String vratiImeTabele() {
        return "polaznik";
    }

    /**
     * Vraca listu polaznika kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista polaznika ili null ako dodje do greske
     */
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

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja polaznika u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "ime, prezime, email, idUzrast";
    }

    /**
     * Vraca vrednosti atributa polaznika koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', " + uzrasnaKategorija.getIdUzrast();
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju polaznika u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a polaznika
     */
    @Override
    public String vratiKljucZaWhere() {
        return "polaznik.idPolaznik = " + idPolaznik;
    }

    /**
     * Vraca string sa vrednostima atributa polaznika za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', idUzrast = " + uzrasnaKategorija.getIdUzrast();
    }

    /**
     * Vraca jedan objekat polaznika kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat polaznika ili null ako dodje do greske
     */
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