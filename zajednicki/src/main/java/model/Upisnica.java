package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja upisnicu polaznika u skoli plesa.
 * Upisnica sadrzi informacije o datumu upisa, ukupnoj clanarini,
 * listi stavki, instruktoru i polazniku.
 *
 * @author Tijana
 * @version 1.0
 * @see StavkaUpisnice
 * @see Instruktor
 * @see Polaznik
 * @see OpstiDomenskiObjekat
 */
public class Upisnica extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator upisnice u bazi podataka. */
    private int idUpisnica;

    /** Datum kada je polaznik upisan. */
    private Date datumUpisa;

    /** Ukupna clanarina za sve stavke upisnice. */
    private double ukupnaClanarina;

    /** Lista stavki upisnice koje sadrze detalje o vrstama plesa. */
    private List<StavkaUpisnice> listaStavke = new ArrayList<>();

    /** Instruktor koji vodi polaznika. */
    private Instruktor instruktor;

    /** Polaznik koji je upisan. */
    private Polaznik polaznik;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public Upisnica() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute upisnice ukljucujuci i ID.
     *
     * @param idUpisnica jedinstveni identifikator upisnice
     * @param datumUpisa datum upisa polaznika
     * @param ukupnaClanarina ukupna clanarina za sve stavke upisnice
     * @param instruktor instruktor koji vodi polaznika
     * @param polaznik polaznik koji je upisan
     * @throws Exception ako je datum upisa u buducnosti
     * @throws IllegalArgumentException ako su instruktor ili polaznik null,
     *                                  ili ako je ukupnaClanarina manja od ili jednaka nuli
     */
    public Upisnica(int idUpisnica, Date datumUpisa, double ukupnaClanarina, Instruktor instruktor, Polaznik polaznik) throws Exception {
        setIdUpisnica(idUpisnica);
        setDatumUpisa(datumUpisa);
        setUkupnaClanarina(ukupnaClanarina);
        setInstruktor(instruktor);
        setPolaznik(polaznik);
    }

    /**
     * Konstruktor koji inicijalizuje atribute upisnice bez ID-a.
     * Koristi se prilikom kreiranja nove upisnice pre unosa u bazu podataka.
     *
     * @param datumUpisa datum upisa polaznika
     * @param ukupnaClanarina ukupna clanarina za sve stavke upisnice
     * @param instruktor instruktor koji vodi polaznika
     * @param polaznik polaznik koji je upisan
     * @throws Exception ako je datum upisa u buducnosti
     * @throws IllegalArgumentException ako su instruktor ili polaznik null,
     *                                  ili ako je ukupnaClanarina manja od ili jednaka nuli
     */
    public Upisnica(Date datumUpisa, double ukupnaClanarina, Instruktor instruktor, Polaznik polaznik) throws Exception {
        setDatumUpisa(datumUpisa);
        setUkupnaClanarina(ukupnaClanarina);
        setInstruktor(instruktor);
        setPolaznik(polaznik);
    }


    /**
     * Vraca jedinstveni identifikator upisnice.
     *
     * @return jedinstveni identifikator upisnice
     */
    public int getIdUpisnica() {
        return idUpisnica;
    }

    /**
     * Postavlja jedinstveni identifikator upisnice.
     *
     * @param idUpisnica jedinstveni identifikator koji se postavlja
     */
    public void setIdUpisnica(int idUpisnica) {
        this.idUpisnica = idUpisnica;
    }

    /**
     * Vraca datum upisa polaznika.
     *
     * @return datum upisa
     */
    public Date getDatumUpisa() {
        return datumUpisa;
    }

    /**
     * Postavlja datum upisa polaznika.
     * Datum ne sme biti u buducnosti.
     *
     * @param datumUpisa datum upisa koji se postavlja
     * @throws Exception ako je datum upisa u buducnosti
     */
    public void setDatumUpisa(Date datumUpisa) throws Exception {
        if (datumUpisa.after(new Date())) {
            throw new Exception("Datum se ne sme odnositi na budućnost");
        } else {
            this.datumUpisa = datumUpisa;
        }
    }

    /**
     * Vraca ukupnu clanarinu za sve stavke upisnice.
     *
     * @return ukupna clanarina
     */
    public double getUkupnaClanarina() {
        return ukupnaClanarina;
    }

    /**
     * Postavlja ukupnu clanarinu za sve stavke upisnice.
     * Ukupna clanarina mora biti pozitivna vrednost.
     *
     * @param ukupnaClanarina ukupna clanarina koja se postavlja
     * @throws IllegalArgumentException ako je ukupnaClanarina manja od ili jednaka nuli
     */
    public void setUkupnaClanarina(double ukupnaClanarina) {
        if (ukupnaClanarina <= 0) {
            throw new IllegalArgumentException("Ukupna članarina mora biti pozitivna vrednost.");
        }
        this.ukupnaClanarina = ukupnaClanarina;
    }

    /**
     * Vraca listu stavki upisnice.
     *
     * @return lista stavki upisnice
     */
    public List<StavkaUpisnice> getListaStavke() {
        return listaStavke;
    }

    /**
     * Postavlja listu stavki upisnice.
     *
     * @param listaStavke lista stavki koja se postavlja
     */
    public void setListaStavke(List<StavkaUpisnice> listaStavke) {
        this.listaStavke = listaStavke;
    }

    /**
     * Vraca instruktora koji vodi polaznika.
     *
     * @return instruktor upisnice
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Postavlja instruktora koji vodi polaznika.
     * Instruktor ne sme biti null.
     *
     * @param instruktor instruktor koji se postavlja
     * @throws IllegalArgumentException ako je instruktor null
     */
    public void setInstruktor(Instruktor instruktor) {
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor koji se odnosi na datu upisnicu ne sme biti null.");
        }
        this.instruktor = instruktor;
    }

    /**
     * Vraca polaznika koji je upisan.
     *
     * @return polaznik upisnice
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }

    /**
     * Postavlja polaznika koji je upisan.
     * Polaznik ne sme biti null.
     *
     * @param polaznik polaznik koji se postavlja
     * @throws IllegalArgumentException ako je polaznik null
     */
    public void setPolaznik(Polaznik polaznik) {
        if (polaznik == null) {
            throw new IllegalArgumentException("Polaznik koji se odnosi na datu upisnicu ne sme biti null.");
        }
        this.polaznik = polaznik;
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
     * Poredi dve upisnice na osnovu ID-a i datuma upisa.
     * Dve upisnice su jednake ako imaju isti ID i isti datum upisa.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su upisnice jednake, false u svim drugim slucajevima
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
        final Upisnica other = (Upisnica) obj;
        if (this.idUpisnica != other.idUpisnica) {
            return false;
        }
        return Objects.equals(this.datumUpisa, other.datumUpisa);
    }

    /**
     * Vraca tekstualnu reprezentaciju upisnice sa datumom upisa, ukupnom clanarinom i listom stavki.
     *
     * @return string sa podacima o upisnici
     */
    @Override
    public String toString() {
        return "Datum upisa je " + datumUpisa + ", ukupna članarina je " + ukupnaClanarina + ", izabrane vrste plesa su:\n" + ispisiListu();
    }

    /**
     * Pomocna metoda koja vraca tekstualnu reprezentaciju svih stavki upisnice.
     *
     * @return string sa svim stavkama upisnice
     */
    private String ispisiListu() {
        String lista = "";
        for (StavkaUpisnice stavkaUpisnice : listaStavke) {
            lista += stavkaUpisnice.toString() + "\n";
        }
        return lista;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "upisnica"
     */
    @Override
    public String vratiImeTabele() {
        return "upisnica";
    }

    /**
     * Vraca listu upisnica kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista upisnica ili null ako se pojavi greska
     */
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
        } catch (Exception e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste upisnica: " + e.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja upisnice u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "datumUpisa, ukupnaClanarina, idInstruktor, idPolaznik";
    }

    /**
     * Vraca vrednosti atributa upisnice koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        Date datum = new java.sql.Date(datumUpisa.getTime());
        return "'" + datum + "', " + ukupnaClanarina + ", " + instruktor.getIdInstruktor() + ", " + polaznik.getIdPolaznik();
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju upisnice u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a upisnice
     */
    @Override
    public String vratiKljucZaWhere() {
        return "upisnica.idUpisnica = " + idUpisnica;
    }

    /**
     * Vraca string sa vrednostima atributa upisnice za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        Date datum = new java.sql.Date(datumUpisa.getTime());
        return "datumUpisa = '" + datum + "', ukupnaClanarina = " + ukupnaClanarina + ", idInstruktor = " + instruktor.getIdInstruktor() + ", idPolaznik = " + polaznik.getIdPolaznik();
    }

    /**
     * Vraca jedan objekat upisnice kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat upisnice ili null ako dodje do greske
     */
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
        } catch (Exception e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja upisnice: " + e.getMessage());
            return null;
        }
    }
}