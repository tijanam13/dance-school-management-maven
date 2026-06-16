package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tijana
 */
public class ModelTabeleSertifikat extends AbstractTableModel {

    private List<Sertifikat> sertifikati = new ArrayList<>();
    private String[] kolone = {"Polaznik", "Vrsta plesa", "Nivo", "Datum izdavanja", "Mesto izdavanja", "Napomena"};

    public List<Sertifikat> getLista() {
        return sertifikati;
    }

    public void setLista(List<Sertifikat> sertifikati) {
        this.sertifikati = sertifikati;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleSertifikat(List<Sertifikat> sertifikati) {
        this.sertifikati = sertifikati;
    }

    @Override
    public int getRowCount() {
        if (sertifikati.isEmpty()) {
            return 0;
        } else {
            return sertifikati.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sertifikat sertifikat = sertifikati.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sertifikat.getPolaznik();
            case 1:
                return sertifikat.getVrstaPlesa();
            case 2:
                return sertifikat.getNivo();
            case 3:
                return sertifikat.getDatumIzdavanja();
            case 4:
                return sertifikat.getMestoIzdavanja();
            case 5:
                return sertifikat.getNapomena();
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