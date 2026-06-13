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
public class ModelTabeleKvalifikacija extends AbstractTableModel {

    private List<Kvalifikacija> lista = new ArrayList<>();
    private String[] kolone = {"Tip", "Organizacija"};

    public List<Kvalifikacija> getLista() {
        return lista;
    }

    public void setLista(List<Kvalifikacija> lista) {
        this.lista = lista;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleKvalifikacija(List<Kvalifikacija> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista.isEmpty()) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kvalifikacija kvalifikacija = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kvalifikacija.getTip();
            case 1:
                return kvalifikacija.getOrganizacija();
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
