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
public class ModelTabeleStavkaUpisnice extends AbstractTableModel {

    private List<StavkaUpisnice> stavke = new ArrayList<>();
    private String[] kolone = {"Redni broj", "Vrsta plesa", "Broj časova", "Cena časa", "Članarina"};

    public List<StavkaUpisnice> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaUpisnice> stavke) {
        this.stavke = stavke;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleStavkaUpisnice(List<StavkaUpisnice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        if (stavke.isEmpty()) {
            return 0;
        } else {
            return stavke.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaUpisnice stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getVrstaPlesa().toString();
            case 2:
                return stavka.getBrCasova();
            case 3:
                return stavka.getCena();
            case 4:
                return stavka.getClanarina();
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

    public void dodajElement(StavkaUpisnice sp) {
        stavke.add(sp);
        fireTableDataChanged();
    }

}
