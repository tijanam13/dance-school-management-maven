/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tijana
 */
public class ModelTabeleInstruktori extends AbstractTableModel {

    private List<Instruktor> korisnici = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "E-mail", "Korisničko ime", "Šifra"};

    public List<Instruktor> getInstruktori() {
        return korisnici;
    }

    public void setInstruktori(List<Instruktor> korisnici) {
        this.korisnici = korisnici;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleInstruktori(List<Instruktor> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        if (korisnici.isEmpty()) {
            return 0;
        } else {
            return korisnici.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instruktor korisnik = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return korisnik.getIme();
            case 1:
                return korisnik.getPrezime();
            case 2:
                return korisnik.getEmail();
            case 3:
                return korisnik.getKorisnickoIme();
            case 4:
                return korisnik.getSifra();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }

}
