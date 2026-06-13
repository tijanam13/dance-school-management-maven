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
public class ModelTabeleVrstePlesa extends AbstractTableModel {

    private List<VrstaPlesa> vrste = new ArrayList<>();
    private String[] kolone = {"Naziv", "Kategorija", "Cena časa"};

    public List<VrstaPlesa> getLista() {
        return vrste;
    }

    public void setLista(List<VrstaPlesa> vrste) {
        this.vrste = vrste;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleVrstePlesa(List<VrstaPlesa> vrste) {
        this.vrste = vrste;
    }

    @Override
    public int getRowCount() {
        if (vrste.isEmpty()) {
            return 0;
        } else {
            return vrste.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VrstaPlesa vrsta = vrste.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return vrsta.getNaziv();
            case 1:
                return vrsta.getKategorija();
            case 2:
                return vrsta.getCenaCasa();
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
