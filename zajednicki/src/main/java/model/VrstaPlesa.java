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
public class VrstaPlesa extends OpstiDomenskiObjekat {

    private int idVrstaPlesa;
    private String naziv;
    private String kategorija;
    private double cenaCasa;

    public VrstaPlesa() {
    }

    public VrstaPlesa(int idVrstaPlesa, String naziv, String kategorija, double cenaCasa) {
        this.idVrstaPlesa = idVrstaPlesa;
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cenaCasa = cenaCasa;
    }

    public VrstaPlesa(String naziv, String kategorija, double cenaCasa) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.cenaCasa = cenaCasa;
    }

    public VrstaPlesa(String naziv, String kategorija) {
        this.naziv = naziv;
        this.kategorija = kategorija;
    }

    public int getIdVrstaPlesa() {
        return idVrstaPlesa;
    }

    public void setIdVrstaPlesa(int idVrstaPlesa) {
        this.idVrstaPlesa = idVrstaPlesa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public double getCenaCasa() {
        return cenaCasa;
    }

    public void setCenaCasa(double cenaCasa) {
        this.cenaCasa = cenaCasa;
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
        final VrstaPlesa other = (VrstaPlesa) obj;
        if (Double.doubleToLongBits(this.cenaCasa) != Double.doubleToLongBits(other.cenaCasa)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.kategorija, other.kategorija);
    }

    @Override
    public String toString() {

        return naziv + " - " + kategorija;
    }

    @Override
    public String vratiImeTabele() {
        return "vrsta_plesa";
    }

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

    @Override
    public String vratiKoloneUbaci() {
        return "naziv, kategorija, cenaCasa";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + naziv + "', '" + kategorija + "', " + cenaCasa;
    }

    @Override
    public String vratiKljucZaWhere() {
        return "vrsta_plesa.idVrstaPlesa=" + idVrstaPlesa;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "naziv = '" + naziv + "', kategorija = '" + kategorija + "', cenaCasa = " + cenaCasa;
    }

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
