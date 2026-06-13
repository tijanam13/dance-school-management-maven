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
public class ModelTabeleUpisnice extends AbstractTableModel {

    private List<Upisnica> upisnice = new ArrayList<>();
    private String[] kolone = {"Datum upisa", "Instruktor", "Polaznik", "Ukupna članarina"};

    public List<Upisnica> getLista() {
        return upisnice;
    }

    public void setLista(List<Upisnica> stavke) {
        this.upisnice = stavke;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleUpisnice(List<Upisnica> upisnice) {
        this.upisnice = upisnice;
    }

    @Override
    public int getRowCount() {
        if (upisnice.isEmpty()) {
            return 0;
        } else {
            return upisnice.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Upisnica upisnica = upisnice.get(rowIndex);
        String datumNovi = "";
        String datum = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            datum = format.format(upisnica.getDatumUpisa());
            String[] elementi = datum.split("-");
            datumNovi = elementi[2] + "." + elementi[1] + "." + elementi[0] + ".";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška prilikom obrade datuma", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        switch (columnIndex) {
            case 0:
                return datumNovi;
            case 1:
                return upisnica.getInstruktor();
            case 2:
                return upisnica.getPolaznik();
            case 3:
                return upisnica.getUkupnaClanarina();
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

    public void dodajElemmt(Upisnica u) {
        upisnice.add(u);
        fireTableDataChanged();
    }

}
