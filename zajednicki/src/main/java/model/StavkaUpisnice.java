/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tijana
 */
public class StavkaUpisnice extends OpstiDomenskiObjekat {

    private int rb;
    private int brCasova;
    private double cena;
    private double clanarina;
    private VrstaPlesa vrstaPlesa;

    /**
     * Upisnica kojoj ova stavka pripada.
     * Polje je transient da bi Gson izbegao kruznu referencu
     * (Upisnica sadrzi listu StavkaUpisnice, a StavkaUpisnice referencu na Upisnicu),
     * jer bi inace serijalizacija u JSON izazvala StackOverflowError.
     */
    private transient Upisnica upisnica;

    public StavkaUpisnice() {
    }

    public StavkaUpisnice(int rb, int brCasova, double cena, double clanarina, VrstaPlesa vrstaPlesa, Upisnica upisnica) {
        this.rb = rb;
        this.brCasova = brCasova;
        this.cena = cena;
        this.clanarina = clanarina;
        this.vrstaPlesa = vrstaPlesa;
        this.upisnica = upisnica;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrCasova() {
        return brCasova;
    }

    public void setBrCasova(int brCasova) {
        this.brCasova = brCasova;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getClanarina() {
        return clanarina;
    }

    public void setClanarina(double clanarina) {
        this.clanarina = clanarina;
    }

    public VrstaPlesa getVrstaPlesa() {
        return vrstaPlesa;
    }

    public void setVrstaPlesa(VrstaPlesa vrstaPlesa) {
        this.vrstaPlesa = vrstaPlesa;
    }

    public Upisnica getUpisnica() {
        return upisnica;
    }

    public void setUpisnica(Upisnica upisnica) {
        this.upisnica = upisnica;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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

    @Override
    public String toString() {
        return "vrsta plesa: " + vrstaPlesa + ", broj časova: " + brCasova + ", cena jednog časa: " + cena + ", članarina: " + clanarina;
    }

    @Override
    public String vratiImeTabele() {
        return "stavka_upisnice";

    }

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

    @Override
    public String vratiKoloneUbaci() {
        return "rb, brCasova, cena, clanarina, idVrstaPlesa, idUpisnica";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return rb + ", " + brCasova + ", " + cena + ", " + clanarina + ", " + vrstaPlesa.getIdVrstaPlesa() + ", " + upisnica.getIdUpisnica();
    }

    @Override
    public String vratiKljucZaWhere() {
        return null;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "rb = " + rb + ", brCasova = " + brCasova + ", cena = " + cena + ", clanarina = " + clanarina + ", idVrstaPlesa = " + vrstaPlesa.getIdVrstaPlesa() + ", idUpisnica = " + upisnica.getIdUpisnica();

    }

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
