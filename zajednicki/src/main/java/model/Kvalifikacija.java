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
public class Kvalifikacija extends OpstiDomenskiObjekat {

    private int idKvalifikacija;
    private String tip;
    private String organizacija;

    public Kvalifikacija() {
    }

    public Kvalifikacija(int idKvalifikacija, String tip, String organizacija) {
        this.idKvalifikacija = idKvalifikacija;
        this.tip = tip;
        this.organizacija = organizacija;
    }

    public Kvalifikacija(String tip, String organizacija) {
        this.tip = tip;
        this.organizacija = organizacija;
    }

    public String getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(String organizacija) {
        this.organizacija = organizacija;
    }

    public int getIdKvalifikacija() {
        return idKvalifikacija;
    }

    public void setIdKvalifikacija(int idKvalifikacija) {
        this.idKvalifikacija = idKvalifikacija;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Kvalifikacija other = (Kvalifikacija) obj;
        if (!Objects.equals(this.tip, other.tip)) {
            return false;
        }
        return Objects.equals(this.organizacija, other.organizacija);
    }

 

    @Override
    public String toString() {
        return "Tip: " + tip + ", organizacija: " + organizacija;
    }

    @Override
    public String vratiImeTabele() {
        return "kvalifikacija";
    }

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

    @Override
    public String vratiKoloneUbaci() {
        return "tip, organizacija";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + tip + "', '" + organizacija + "'";
    }

    @Override
    public String vratiKljucZaWhere() {
        return "kvalifikacija.idKvalifikacija = " + idKvalifikacija;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "tip = '" + tip + "', organizacija = '" + organizacija + "'";
    }

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
