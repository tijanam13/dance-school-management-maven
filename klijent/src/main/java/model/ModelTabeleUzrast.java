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
public class ModelTabeleUzrast extends AbstractTableModel {

    private List<UzrasnaKategorija> lista = new ArrayList<>();
    private String[] kolone = {"Opseg godina", "Naziv"};

    public List<UzrasnaKategorija> getLista() {
        return lista;
    }

    public void setLista(List<UzrasnaKategorija> lista) {
        this.lista = lista;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleUzrast(List<UzrasnaKategorija> lista) {
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
        UzrasnaKategorija uzrast = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return uzrast.getOpsegGodina();
            case 1:
                return uzrast.getNaziv();
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
