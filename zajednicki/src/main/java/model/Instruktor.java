package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

/**
 * Predstavlja instruktora u skoli plesa.
 * Instruktor moze da ima vise {@link InstruktorKvalifikacija} i moze da vodi vise upisnica.
 * Za prijavu na sistem koristi se korisnicko ime i sifra.
 *
 * @author Tijana
 * @version 1.0
 * @see InstruktorKvalifikacija
 * @see OpstiDomenskiObjekat
 */
@Getter
@Setter
@NoArgsConstructor
public class Instruktor extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator instruktora u bazi podataka. */
    private int idInstruktor;


    /** Korisnicko ime instruktora koje se koristi za prijavu na sistem.
     * Ne sme biti null niti prazan string. */
    @NotBlank(message = "Korisničko ime ne sme biti null niti prazno.")
    private String korisnickoIme;

    /** Sifra instruktora koja se koristi za prijavu na sistem.
     * Ne sme biti null niti prazan string. */
    @NotBlank(message = "Šifra ne sme biti null niti prazna.")
    private String sifra;

    /** Ime instruktora.
     * Ne sme biti null niti prazan string. */
    @NotBlank(message = "Ime instruktora ne sme biti null niti prazno.")
    private String ime;

    /** Prezime instruktora.
     * Ne sme biti null niti prazan string. */
    @NotBlank(message = "Prezime instruktora ne sme biti null niti prazno.")
    private String prezime;

    /** Email adresa instruktora.
     * Ne sme biti null niti prazna, i mora biti ispravnog formata. */
    @NotBlank(message = "Email instruktora ne sme biti null niti prazan.")
    @Email(message = "Email instruktora mora biti ispravnog formata.")
    private String email;

    /** Lista kvalifikacija koje instruktor poseduje. */
    private List<InstruktorKvalifikacija> instruktorKvalifikacije;

    /**
     * Konstruktor koji inicijalizuje sve atribute instruktora ukljucujuci i ID.
     *
     * @param idInstruktor jedinstveni identifikator instruktora
     * @param korisnickoIme korisnicko ime instruktora
     * @param sifra sifra instruktora
     * @param ime ime instruktora
     * @param prezime prezime instruktora
     * @param email email adresa instruktora
     */
    public Instruktor(int idInstruktor, String korisnickoIme, String sifra, String ime, String prezime, String email) {
        this.idInstruktor = idInstruktor;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.instruktorKvalifikacije = new ArrayList<>();
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute instruktora bez ID-a.
     * Koristi se prilikom kreiranja novog instruktora pre unosa u bazu podataka.
     *
     * @param korisnickoIme korisnicko ime instruktora
     * @param sifra sifra instruktora
     * @param ime ime instruktora
     * @param prezime prezime instruktora
     * @param email email adresa instruktora
     */
    public Instruktor(String korisnickoIme, String sifra, String ime, String prezime, String email) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.instruktorKvalifikacije = new ArrayList<>();
    }

    /**
     * Konstruktor koji inicijalizuje samo korisnicko ime i sifru.
     * Koristi se prilikom prijave instruktora na sistem.
     *
     * @param korisnickoIme korisnicko ime instruktora
     * @param sifra sifra instruktora
     */
    public Instruktor(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.instruktorKvalifikacije = new ArrayList<>();
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
     * Poredi dva instruktora na osnovu korisnickog imena i sifre.
     * Dva instruktora su jednaka ako imaju isto korisnicko ime i istu sifru.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su instruktori jednaki, false u svim drugim slucajevima
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
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    /**
     * Vraca tekstualnu reprezentaciju instruktora u obliku ime i prezime.
     *
     * @return ime i prezime instruktora
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "instruktor"
     */
    @Override
    public String vratiImeTabele() {
        return "instruktor";
    }

    /**
     * Vraca listu instruktora kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista instruktora ili null ako postoji neka greska
     */
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

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja instruktora u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "ime, prezime, email, sifra, korisnickoIme";
    }

    /**
     * Vraca vrednosti atributa instruktora koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + sifra + "', '" + korisnickoIme + "'";
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju instruktora u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a instruktora
     */
    @Override
    public String vratiKljucZaWhere() {
        return "instruktor.idInstruktor = " + idInstruktor;
    }

    /**
     * Vraca string sa vrednostima atributa instruktora za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', korisnickoIme = '" + korisnickoIme + "', sifra = '" + sifra + "'";
    }

    /**
     * Vraca jedan objekat instruktora kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat instruktora, ili null ako dodje do greske
     */
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