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

    private List<Instruktor> instruktori = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "E-mail", "Korisničko ime", "Šifra"};

    public List<Instruktor> getInstruktori() {
        return instruktori;
    }

    public void setInstruktori(List<Instruktor> instruktori) {
        this.instruktori = instruktori;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleInstruktori(List<Instruktor> instruktori) {
        this.instruktori = instruktori;
    }

    @Override
    public int getRowCount() {
        if (instruktori.isEmpty()) {
            return 0;
        } else {
            return instruktori.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instruktor instruktor = instruktori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return instruktor.getIme();
            case 1:
                return instruktor.getPrezime();
            case 2:
                return instruktor.getEmail();
            case 3:
                return instruktor.getKorisnickoIme();
            case 4:
                return instruktor.getSifra();
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
