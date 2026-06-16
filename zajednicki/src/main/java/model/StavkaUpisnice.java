package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja jednu stavku upisnice u skoli plesa.
 * Svaka stavka sadrzi informacije o vrsti plesa, broju casova, ceni i clanarini.
 *
 * @author Tijana
 * @version 1.0
 * @see Upisnica
 * @see VrstaPlesa
 * @see OpstiDomenskiObjekat
 */
public class StavkaUpisnice extends OpstiDomenskiObjekat {

    /** Redni broj stavke upisnice. */
    private int rb;

    /** Broj casova koji su ugovoreni stavkom upisnice. */
    private int brCasova;

    /** Cena jednog casa. */
    private double cena;

    /** Ukupna clanarina za ovu stavku upisnice. */
    private double clanarina;

    /** Vrsta plesa na koju se stavka odnosi. */
    private VrstaPlesa vrstaPlesa;

    /** Upisnica kojoj ova stavka pripada. */
    private Upisnica upisnica;

    /**
     * Podrazumevani konstruktor bez parametara.
     */
    public StavkaUpisnice() {
    }

    /**
     * Konstruktor koji inicijalizuje sve atribute stavke upisnice.
     *
     * @param rb redni broj stavke upisnice
     * @param brCasova broj casova ugovorenih stavkom
     * @param cena cena jednog casa
     * @param clanarina ukupna clanarina za ovu stavku
     * @param vrstaPlesa vrsta plesa na koju se stavka odnosi
     * @param upisnica upisnica kojoj stavka pripada
     */
    public StavkaUpisnice(int rb, int brCasova, double cena, double clanarina, VrstaPlesa vrstaPlesa, Upisnica upisnica) {
        this.rb = rb;
        this.brCasova = brCasova;
        this.cena = cena;
        this.clanarina = clanarina;
        this.vrstaPlesa = vrstaPlesa;
        this.upisnica = upisnica;
    }

    /**
     * Vraca redni broj stavke upisnice.
     *
     * @return redni broj stavke
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke upisnice.
     *
     * @param rb redni broj koji se postavlja
     */
    public void setRb(int rb) {
        this.rb = rb;
    }

    /**
     * Vraca broj casova ugovorenih stavkom upisnice.
     *
     * @return broj casova
     */
    public int getBrCasova() {
        return brCasova;
    }

    /**
     * Postavlja broj casova ugovorenih stavkom upisnice.
     *
     * @param brCasova broj casova koji se postavlja
     */
    public void setBrCasova(int brCasova) {
        this.brCasova = brCasova;
    }

    /**
     * Vraca cenu jednog casa.
     *
     * @return cena jednog casa
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu jednog casa.
     *
     * @param cena cena koja se postavlja
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Vraca ukupnu clanarinu za ovu stavku upisnice.
     *
     * @return ukupna clanarina
     */
    public double getClanarina() {
        return clanarina;
    }

    /**
     * Postavlja ukupnu clanarinu za ovu stavku upisnice.
     *
     * @param clanarina clanarina koja se postavlja
     */
    public void setClanarina(double clanarina) {
        this.clanarina = clanarina;
    }

    /**
     * Vraca vrstu plesa na koju se stavka odnosi.
     *
     * @return vrsta plesa
     */
    public VrstaPlesa getVrstaPlesa() {
        return vrstaPlesa;
    }

    /**
     * Postavlja vrstu plesa na koju se stavka odnosi.
     *
     * @param vrstaPlesa vrsta plesa koja se postavlja
     */
    public void setVrstaPlesa(VrstaPlesa vrstaPlesa) {
        this.vrstaPlesa = vrstaPlesa;
    }

    /**
     * Vraca upisnicu kojoj ova stavka pripada.
     *
     * @return upisnica kojoj stavka pripada
     */
    public Upisnica getUpisnica() {
        return upisnica;
    }

    /**
     * Postavlja upisnicu kojoj ova stavka pripada.
     *
     * @param upisnica upisnica koja se postavlja
     */
    public void setUpisnica(Upisnica upisnica) {
        this.upisnica = upisnica;
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
     * Poredi dve stavke upisnice na osnovu broja casova, cene, clanarine i vrste plesa.
     * Dve stavke su jednake ako imaju isti broj casova, istu cenu, istu clanarinu i istu vrstu plesa.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su stavke jednake, false u svim drugim slucajevima
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
        final StavkaUpisnice other = (StavkaUpisnice) obj;
        if (this.brCasova != other.brCasova) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (Double.doubleToLongBits(this.clanarina) != Double.doubleToLongBits(other.clanarina)) {
            return false;
        }
        return Objects.equals(this.vrstaPlesa, other.vrstaPlesa);
    }

    /**
     * Vraca tekstualnu reprezentaciju stavke upisnice sa svim relevantnim podacima.
     *
     * @return string sa vrstom plesa, brojem casova, cenom i clanarinom
     */
    @Override
    public String toString() {
        return "vrsta plesa: " + vrstaPlesa + ", broj časova: " + brCasova + ", cena jednog časa: " + cena + ", članarina: " + clanarina;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara ovoj klasi.
     *
     * @return naziv tabele "stavka_upisnice"
     */
    @Override
    public String vratiImeTabele() {
        return "stavka_upisnice";
    }

    /**
     * Vraca listu stavki upisnice kreiranih na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return lista stavki upisnice ili null ako dodje do greske
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int rb = rs.getInt("stavka_upisnice.rb");
                int brCasova = rs.getInt("stavka_upisnice.brCasova");
                double clanarina = rs.getDouble("stavka_upisnice.clanarina");
                int idVrstaPlesa = rs.getInt("stavka_upisnice.idVrstaPlesa");
                String naziv = rs.getString("vrsta_plesa.naziv");
                String kategorija = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, naziv, kategorija, cenaCasa);
                StavkaUpisnice su = new StavkaUpisnice(rb, brCasova, vp.getCenaCasa(), clanarina, vp, null);
                lista.add(su);
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste stavki upisnice: " + e.getMessage());
            return null;
        }
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom ubacivanja stavke upisnice u bazu podataka.
     *
     * @return string sa nazivima kolona odvojenim zarezom
     */
    @Override
    public String vratiKoloneUbaci() {
        return "rb, brCasova, cena, clanarina, idVrstaPlesa, idUpisnica";
    }

    /**
     * Vraca vrednosti atributa stavke upisnice koje se upisuju u bazu podataka.
     *
     * @return string sa vrednostima atributa odvojenim zarezom
     */
    @Override
    public String vratiVrednostiUbaci() {
        return rb + ", " + brCasova + ", " + cena + ", " + clanarina + ", " + vrstaPlesa.getIdVrstaPlesa() + ", " + upisnica.getIdUpisnica();
    }

    /**
     * Vraca uslov WHERE koji se koristi za identifikaciju stavke upisnice u bazi podataka.
     * U ovoj implementaciji metoda vraca null jer se stavke identifikuju preko upisnice.
     *
     * @return null
     */
    @Override
    public String vratiKljucZaWhere() {
        return null;
    }

    /**
     * Vraca string sa vrednostima atributa stavke upisnice za azuriranje u bazi podataka.
     *
     * @return string sa parovima kolona i vrednosti za UPDATE upit
     */
    @Override
    public String vratiVrednostiIzmeni() {
        return "rb = " + rb + ", brCasova = " + brCasova + ", cena = " + cena + ", clanarina = " + clanarina + ", idVrstaPlesa = " + vrstaPlesa.getIdVrstaPlesa() + ", idUpisnica = " + upisnica.getIdUpisnica();
    }

    /**
     * Vraca jedan objekat stavke upisnice kreiran na osnovu podataka iz ResultSet-a.
     *
     * @param rs ResultSet objekat koji sadrzi podatke iz baze
     * @return objekat stavke upisnice ili null ako dodje do greske
     */
    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            StavkaUpisnice su = null;
            while (rs.next()) {
                int rb = rs.getInt("stavka_upisnice.rb");
                int brCasova = rs.getInt("stavka_upisnice.brCasova");
                double clanarina = rs.getDouble("stavka_upisnice.clanarina");
                int idVrstaPlesa = rs.getInt("stavka_upisnice.idVrstaPlesa");
                String naziv = rs.getString("vrsta_plesa.naziv");
                String kategorija = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, naziv, kategorija, cenaCasa);
                su = new StavkaUpisnice(rb, brCasova, vp.getCenaCasa(), clanarina, vp, null);
            }
            return su;
        } catch (SQLException e) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja stavke upisnice: " + e.getMessage());
            return null;
        }
    }
}