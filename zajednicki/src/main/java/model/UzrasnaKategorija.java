/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tijana
 */
public class UzrasnaKategorija extends OpstiDomenskiObjekat {

    private int idUzrast;
    private String opsegGodina;
    private String naziv;

    public UzrasnaKategorija() {
    }

    public UzrasnaKategorija(int idUzrast, String opsegGodina, String naziv) {
        this.idUzrast = idUzrast;
        this.opsegGodina = opsegGodina;
        this.naziv = naziv;
    }

    public UzrasnaKategorija(String opsegGodina, String naziv) {
        this.opsegGodina = opsegGodina;
        this.naziv = naziv;
    }

    public int getIdUzrast() {
        return idUzrast;
    }

    public void setIdUzrast(int idUzrast) {
        this.idUzrast = idUzrast;
    }

    public String getOpsegGodina() {
        return opsegGodina;
    }

    public void setOpsegGodina(String opsegGodina) {
        this.opsegGodina = opsegGodina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        final UzrasnaKategorija other = (UzrasnaKategorija) obj;
        return this.idUzrast == other.idUzrast;
    }

    @Override
    public String toString() {
        return "Opseg godina: " + opsegGodina + ", naziv: " + naziv;
    }

    @Override
    public String vratiImeTabele() {
        return "uzrasna_kategorija";
    }

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

    @Override
    public String vratiKoloneUbaci() {
        return "opsegGodina, naziv";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + opsegGodina + "', '" + naziv + "'";
    }

    @Override
    public String vratiKljucZaWhere() {
        return "uzrasna_kategorija.idUzrast = " + idUzrast;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "opsegGodina = '" + opsegGodina + "', naziv = '" + naziv + "'";
    }

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
