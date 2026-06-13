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
public class ModelTabelePolaznici extends AbstractTableModel {

    private List<Polaznik> polaznici = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "E-mail", "Uzrasna kategorija"};

    public List<Polaznik> getLista() {
        return polaznici;
    }

    public void setLista(List<Polaznik> polaznici) {
        this.polaznici = polaznici;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabelePolaznici(List<Polaznik> polaznici) {
        this.polaznici = polaznici;
    }

    @Override
    public int getRowCount() {
        if (polaznici.isEmpty()) {
            return 0;
        } else {
            return polaznici.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polaznik polaznik = polaznici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return polaznik.getIme();
            case 1:
                return polaznik.getPrezime();
            case 2:
                return polaznik.getEmail();
            case 3:
                return polaznik.getUzrasnaKategorija();
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
