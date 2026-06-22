package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja sertifikat koji polaznik dobija nakon zavrsetka odredjene vrste plesa.
 * Sertifikat je asocijativna klasa koja povezuje {@link Polaznik} i {@link VrstaPlesa}.
 *
 * @author Tijana
 * @version 1.0
 * @see Polaznik
 * @see VrstaPlesa
 * @see Nivo
 * @see OpstiDomenskiObjekat
 */
public class Sertifikat extends OpstiDomenskiObjekat {

    /** Jedinstveni identifikator sertifikata u bazi podataka. */
    private int idSertifikat;

    /** Datum kada je sertifikat izdat. */
    private Date datumIzdavanja;

    /** Mesto gde je sertifikat izdat. */
    private String mestoIzdavanja;

    /** Nivo sertifikata. */
    private Nivo nivo;

    /** Napomena vezana za sertifikat. */
    private String napomena;

    /** Polaznik koji je dobio sertifikat. */
    private Polaznik polaznik;

    /** Vrsta plesa za koju je sertifikat izdat. */
    private VrstaPlesa vrstaPlesa;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public Sertifikat() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute sertifikata ukljucujuci i ID.
     *
     * @param idSertifikat jedinstveni identifikator sertifikata
     * @param datumIzdavanja datum kada je sertifikat izdat
     * @param mestoIzdavanja mesto gde je sertifikat izdat
     * @param nivo nivo sertifikata
     * @param napomena napomena vezana za sertifikat
     * @param polaznik polaznik koji je dobio sertifikat
     * @param vrstaPlesa vrsta plesa za koju je sertifikat izdat
     * @throws IllegalArgumentException ako je datumIzdavanja null ili u buducnosti,
     *                                  ako je mestoIzdavanja null ili prazno,
     *                                  ako je nivo null,
     *                                  ako je polaznik null,
     *                                  ili ako je vrstaPlesa null
     */
    public Sertifikat(int idSertifikat, Date datumIzdavanja, String mestoIzdavanja, Nivo nivo, String napomena, Polaznik polaznik, VrstaPlesa vrstaPlesa) {
        setIdSertifikat(idSertifikat);
        setDatumIzdavanja(datumIzdavanja);
        setMestoIzdavanja(mestoIzdavanja);
        setNivo(nivo);
        setNapomena(napomena);
        setPolaznik(polaznik);
        setVrstaPlesa(vrstaPlesa);
    }

    /**
     * Konstruktor koji inicijalizuje atribute sertifikata bez ID-a.
     * Koristi se prilikom kreiranja novog sertifikata pre unosa u bazu podataka.
     *
     * @param datumIzdavanja datum kada je sertifikat izdat
     * @param mestoIzdavanja mesto gde je sertifikat izdat
     * @param nivo nivo sertifikata
     * @param napomena napomena vezana za sertifikat
     * @param polaznik polaznik koji je dobio sertifikat
     * @param vrstaPlesa vrsta plesa za koju je sertifikat izdat
     * @throws IllegalArgumentException ako je datumIzdavanja null ili u buducnosti,
     *                                  ako je mestoIzdavanja null ili prazno,
     *                                  ako je nivo null,
     *                                  ako je polaznik null,
     *                                  ili ako je vrstaPlesa null
     */
    public Sertifikat(Date datumIzdavanja, String mestoIzdavanja, Nivo nivo, String napomena, Polaznik polaznik, VrstaPlesa vrstaPlesa) {
        setDatumIzdavanja(datumIzdavanja);
        setMestoIzdavanja(mestoIzdavanja);
        setNivo(nivo);
        setNapomena(napomena);
        setPolaznik(polaznik);
        setVrstaPlesa(vrstaPlesa);
    }

    /**
     * Vraca jedinstveni identifikator sertifikata.
     *
     * @return jedinstveni identifikator sertifikata
     */
    public int getIdSertifikat() {
        return idSertifikat;
    }

    /**
     * Postavlja jedinstveni identifikator sertifikata.
     *
     * @param idSertifikat jedinstveni identifikator koji se postavlja
     */
    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    /**
     * Vraca datum izdavanja sertifikata.
     *
     * @return datum izdavanja sertifikata
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Postavlja datum izdavanja sertifikata.
     * Datum ne sme biti null niti u buducnosti.
     *
     * @param datumIzdavanja datum koji se postavlja
     * @throws IllegalArgumentException ako je datum null ili u buducnosti
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        if (datumIzdavanja == null) {
            throw new IllegalArgumentException("Datum izdavanja sertifikata ne sme biti null.");
        }
        if (datumIzdavanja.after(new Date())) {
            throw new IllegalArgumentException("Datum izdavanja sertifikata ne sme biti u budućnosti.");
        }
        this.datumIzdavanja = datumIzdavanja;
    }

    /**
     * Vraca mesto izdavanja sertifikata.
     *
     * @return mesto izdavanja sertifikata
     */
    public String getMestoIzdavanja() {
        return mestoIzdavanja;
    }

    /**
     * Postavlja mesto izdavanja sertifikata.
     * Mesto ne sme biti null niti prazan string.
     *
     * @param mestoIzdavanja mesto koje se postavlja
     * @throws IllegalArgumentException ako je mestoIzdavanja null ili prazno
     */
    public void setMestoIzdavanja(String mestoIzdavanja) {
        if (mestoIzdavanja == null || mestoIzdavanja.trim().isEmpty()) {
            throw new IllegalArgumentException("Mesto izdavanja sertifikata ne sme biti null niti prazan string.");
        }
        this.mestoIzdavanja = mestoIzdavanja;
    }

    /**
     * Vraca nivo sertifikata.
     *
     * @return nivo sertifikata
     */
    public Nivo getNivo() {
        return nivo;
    }

    /**
     * Postavlja nivo sertifikata.
     * Nivo ne sme biti null.
     *
     * @param nivo nivo koji se postavlja
     * @throws IllegalArgumentException ako je nivo null
     */
    public void setNivo(Nivo nivo) {
        if (nivo == null) {
            throw new IllegalArgumentException("Nivo sertifikata ne sme biti null.");
        }
        this.nivo = nivo;
    }

    /**
     * Vraca napomenu vezanu za sertifikat.
     *
     * @return napomena sertifikata
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja napomenu vezanu za sertifikat.
     * Napomena moze biti prazna ili null (opciono polje).
     *
     * @param napomena napomena koja se postavlja
     */
    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    /**
     * Vraca polaznika koji je dobio sertifikat.
     *
     * @return polaznik koji je dobio sertifikat
     */
    public Polaznik getPolaznik() {
        return polaznik;
    }

    /**
     * Postavlja polaznika koji je dobio sertifikat.
     * Polaznik ne sme biti null.
     *
     * @param polaznik polaznik koji se postavlja
     * @throws IllegalArgumentException ako je polaznik null
     */
    public void setPolaznik(Polaznik polaznik) {
        if (polaznik == null) {
            throw new IllegalArgumentException("Polaznik koji se odnosi na dati sertifikat ne sme biti null.");
        }
        this.polaznik = polaznik;
    }

    /**
     * Vraca vrstu plesa za koju je sertifikat izdat.
     *
     * @return vrsta plesa sertifikata
     */
    public VrstaPlesa getVrstaPlesa() {
        return vrstaPlesa;
    }

    /**
     * Postavlja vrstu plesa za koju je sertifikat izdat.
     * Vrsta plesa ne sme biti null.
     *
     * @param vrstaPlesa vrsta plesa koja se postavlja
     * @throws IllegalArgumentException ako je vrstaPlesa null
     */
    public void setVrstaPlesa(VrstaPlesa vrstaPlesa) {
        if (vrstaPlesa == null) {
            throw new IllegalArgumentException("Vrsta plesa koja se odnosi na dati sertifikat ne sme biti null.");
        }
        this.vrstaPlesa = vrstaPlesa;
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
     * Poredi dva sertifikata na osnovu polaznika, vrste plesa i nivoa.
     * Dva sertifikata su jednaka ako imaju istog polaznika, istu vrstu plesa i isti nivo.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su sertifikati jednaki, false u svim drugim slucajevima
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.polaznik, other.polaznik)) {
            return false;
        }
        if (!Objects.equals(this.vrstaPlesa, other.vrstaPlesa)) {
            return false;
        }
        return this.nivo == other.nivo;
    }

    /**
     * Vraca tekstualnu reprezentaciju sertifikata.
     *
     * @return string sa podacima o polazniku, vrsti plesa, nivou i mestu izdavanja
     */
    @Override
    public String toString() {
        return "Sertifikat{" + "polaznik=" + polaznik + ", vrstaPlesa=" + vrstaPlesa + ", nivo=" + nivo + ", mestoIzdavanja=" + mestoIzdavanja + '}';
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "sertifikat"
     */
    @Override
    public String vratiImeTabele() {
        return "sertifikat";
    }

    /**
     * Vraca listu sertifikata kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista sertifikata ili null ako dodje do greske
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int idSertifikat = rs.getInt("sertifikat.idSertifikat");
                Date datumIzdavanja = rs.getDate("sertifikat.datumIzdavanja");
                String mestoIzdavanja = rs.getString("sertifikat.mestoIzdavanja");
                String nivo = rs.getString("sertifikat.nivo");
                String napomena = rs.getString("sertifikat.napomena");
                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                String imePolaznik = rs.getString("polaznik.ime");
                String prezimePolaznik = rs.getString("polaznik.prezime");
                String emailPolaznik = rs.getString("polaznik.email");
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String nazivVrstaPlesa = rs.getString("vrsta_plesa.naziv");
                String kategorijaVrstaPlesa = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                Polaznik p = new Polaznik(idPolaznik, imePolaznik, prezimePolaznik, emailPolaznik, uk);
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, nazivVrstaPlesa, kategorijaVrstaPlesa, cenaCasa);
                Sertifikat s = new Sertifikat(idSertifikat, datumIzdavanja, mestoIzdavanja, Nivo.valueOf(nivo), napomena, p, vp);
                lista.add(s);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste sertifikata: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja sertifikata u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "datumIzdavanja, mestoIzdavanja, nivo, napomena, idPolaznik, idVrstaPlesa";
    }

    /**
     * Vraca vrednosti atributa sertifikata koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return "'" + datumIzdavanja + "', '" + mestoIzdavanja + "', '" + nivo + "', '" + napomena + "', " + polaznik.getIdPolaznik() + ", " + vrstaPlesa.getIdVrstaPlesa();
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju sertifikata u bazi podataka.
     *
     * @return string sa WHERE uslovom na osnovu ID-a sertifikata
     */
    @Override
    public String vratiKljucZaWhere() {
        return "sertifikat.idSertifikat = " + idSertifikat;
    }

    /**
     * Vraca string sa vrednostima atributa sertifikata za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "datumIzdavanja = '" + datumIzdavanja + "', mestoIzdavanja = '" + mestoIzdavanja + "', nivo = '" + nivo + "', napomena = '" + napomena + "', idPolaznik = " + polaznik.getIdPolaznik() + ", idVrstaPlesa = " + vrstaPlesa.getIdVrstaPlesa();
    }

    /**
     * Vraca jedan objekat sertifikata kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat sertifikata ili null ako dodje do greske
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            Sertifikat s = null;
            while (rs.next()) {
                int idSertifikat = rs.getInt("sertifikat.idSertifikat");
                Date datumIzdavanja = rs.getDate("sertifikat.datumIzdavanja");
                String mestoIzdavanja = rs.getString("sertifikat.mestoIzdavanja");
                String nivo = rs.getString("sertifikat.nivo");
                String napomena = rs.getString("sertifikat.napomena");
                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                String imePolaznik = rs.getString("polaznik.ime");
                String prezimePolaznik = rs.getString("polaznik.prezime");
                String emailPolaznik = rs.getString("polaznik.email");
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String nazivVrstaPlesa = rs.getString("vrsta_plesa.naziv");
                String kategorijaVrstaPlesa = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                Polaznik p = new Polaznik(idPolaznik, imePolaznik, prezimePolaznik, emailPolaznik, uk);
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, nazivVrstaPlesa, kategorijaVrstaPlesa, cenaCasa);
                s = new Sertifikat(idSertifikat, datumIzdavanja, mestoIzdavanja, Nivo.valueOf(nivo), napomena, p, vp);
            }
            return s;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja sertifikata: " + ex.getMessage());
            return null;
        }
    }
}