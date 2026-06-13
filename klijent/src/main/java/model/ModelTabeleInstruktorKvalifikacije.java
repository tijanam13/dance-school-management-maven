/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tijana
 */
public class ModelTabeleInstruktorKvalifikacije extends AbstractTableModel {

    private List<InstruktorKvalifikacija> kvalifikacije = new ArrayList<>();
    private String[] kolone = {"Tip", "Nivo", "Organizacija", "Datum sticanja"};

    public List<InstruktorKvalifikacija> getKvalifikacije() {
        return kvalifikacije;
    }

    public void setKvalifikacije(List<InstruktorKvalifikacija> kvalifikacije) {
        this.kvalifikacije = kvalifikacije;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleInstruktorKvalifikacije(List<InstruktorKvalifikacija> kvalifikacije) {
        this.kvalifikacije = kvalifikacije;
    }

    @Override
    public int getRowCount() {
        if (kvalifikacije.isEmpty()) {
            return 0;
        } else {
            return kvalifikacije.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String datumNovi = null;
        InstruktorKvalifikacija kvalifikacija = kvalifikacije.get(rowIndex);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
            datumNovi = format.format(kvalifikacija.getDatumSticanja());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška prilikom obrade datuma", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        switch (columnIndex) {
            case 0:
                return kvalifikacija.getKvalifikacija().getTip();
            case 1:
                return kvalifikacija.getNivo();
            case 2:
                return kvalifikacija.getKvalifikacija().getOrganizacija();
            case 3:
                return datumNovi;
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

    public void dodajElement(InstruktorKvalifikacija ik) {
        kvalifikacije.add(ik);
        fireTableDataChanged();
    }

}
