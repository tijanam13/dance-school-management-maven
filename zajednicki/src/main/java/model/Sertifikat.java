package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tijana
 */
public class Sertifikat extends OpstiDomenskiObjekat {

    private int idSertifikat;
    private Date datumIzdavanja;
    private String mestoIzdavanja;
    private Nivo nivo;
    private String napomena;
    private Polaznik polaznik;
    private VrstaPlesa vrstaPlesa;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, Date datumIzdavanja, String mestoIzdavanja, Nivo nivo, String napomena, Polaznik polaznik, VrstaPlesa vrstaPlesa) {
        this.idSertifikat = idSertifikat;
        this.datumIzdavanja = datumIzdavanja;
        this.mestoIzdavanja = mestoIzdavanja;
        this.nivo = nivo;
        this.napomena = napomena;
        this.polaznik = polaznik;
        this.vrstaPlesa = vrstaPlesa;
    }

    public Sertifikat(Date datumIzdavanja, String mestoIzdavanja, Nivo nivo, String napomena, Polaznik polaznik, VrstaPlesa vrstaPlesa) {
        this.datumIzdavanja = datumIzdavanja;
        this.mestoIzdavanja = mestoIzdavanja;
        this.nivo = nivo;
        this.napomena = napomena;
        this.polaznik = polaznik;
        this.vrstaPlesa = vrstaPlesa;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getMestoIzdavanja() {
        return mestoIzdavanja;
    }

    public void setMestoIzdavanja(String mestoIzdavanja) {
        this.mestoIzdavanja = mestoIzdavanja;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public void setNivo(Nivo nivo) {
        this.nivo = nivo;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public VrstaPlesa getVrstaPlesa() {
        return vrstaPlesa;
    }

    public void setVrstaPlesa(VrstaPlesa vrstaPlesa) {
        this.vrstaPlesa = vrstaPlesa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.polaznik, other.polaznik)) {
            return false;
        }
        if (!Objects.equals(this.vrstaPlesa, other.vrstaPlesa)) {
            return false;
        }
        return this.nivo == other.nivo;
    }

    @Override
    public String toString() {
        return "Sertifikat{" + "polaznik=" + polaznik + ", vrstaPlesa=" + vrstaPlesa + ", nivo=" + nivo + ", mestoIzdavanja=" + mestoIzdavanja + '}';
    }

    @Override
    public String vratiImeTabele() {
        return "sertifikat";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int idSertifikat = rs.getInt("sertifikat.idSertifikat");
                Date datumIzdavanja = rs.getDate("sertifikat.datumIzdavanja");
                String mestoIzdavanja = rs.getString("sertifikat.mestoIzdavanja");
                String nivo = rs.getString("sertifikat.nivo");
                String napomena = rs.getString("sertifikat.napomena");
                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                String imePolaznik = rs.getString("polaznik.ime");
                String prezimePolaznik = rs.getString("polaznik.prezime");
                String emailPolaznik = rs.getString("polaznik.email");
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String nazivVrstaPlesa = rs.getString("vrsta_plesa.naziv");
                String kategorijaVrstaPlesa = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                Polaznik p = new Polaznik(idPolaznik, imePolaznik, prezimePolaznik, emailPolaznik, uk);
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, nazivVrstaPlesa, kategorijaVrstaPlesa, cenaCasa);
                Sertifikat s = new Sertifikat(idSertifikat, datumIzdavanja, mestoIzdavanja, Nivo.valueOf(nivo), napomena, p, vp);
                lista.add(s);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja liste sertifikata: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String vratiKoloneUbaci() {
        return "datumIzdavanja, mestoIzdavanja, nivo, napomena, idPolaznik, idVrstaPlesa";
    }

    @Override
    public String vratiVrednostiUbaci() {
        return "'" + datumIzdavanja + "', '" + mestoIzdavanja + "', '" + nivo + "', '" + napomena + "', " + polaznik.getIdPolaznik() + ", " + vrstaPlesa.getIdVrstaPlesa();
    }

    @Override
    public String vratiKljucZaWhere() {
        return "sertifikat.idSertifikat = " + idSertifikat;
    }

    @Override
    public String vratiVrednostiIzmeni() {
        return "datumIzdavanja = '" + datumIzdavanja + "', mestoIzdavanja = '" + mestoIzdavanja + "', nivo = '" + nivo + "', napomena = '" + napomena + "', idPolaznik = " + polaznik.getIdPolaznik() + ", idVrstaPlesa = " + vrstaPlesa.getIdVrstaPlesa();
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
        try {
            Sertifikat s = null;
            while (rs.next()) {
                int idSertifikat = rs.getInt("sertifikat.idSertifikat");
                Date datumIzdavanja = rs.getDate("sertifikat.datumIzdavanja");
                String mestoIzdavanja = rs.getString("sertifikat.mestoIzdavanja");
                String nivo = rs.getString("sertifikat.nivo");
                String napomena = rs.getString("sertifikat.napomena");
                int idPolaznik = rs.getInt("polaznik.idPolaznik");
                String imePolaznik = rs.getString("polaznik.ime");
                String prezimePolaznik = rs.getString("polaznik.prezime");
                String emailPolaznik = rs.getString("polaznik.email");
                int idVrstaPlesa = rs.getInt("vrsta_plesa.idVrstaPlesa");
                String nazivVrstaPlesa = rs.getString("vrsta_plesa.naziv");
                String kategorijaVrstaPlesa = rs.getString("vrsta_plesa.kategorija");
                double cenaCasa = rs.getDouble("vrsta_plesa.cenaCasa");
                int idUzrast = rs.getInt("polaznik.idUzrast");
                String opsegGodina = rs.getString("uzrasna_kategorija.opsegGodina");
                String naziv = rs.getString("uzrasna_kategorija.naziv");
                UzrasnaKategorija uk = new UzrasnaKategorija(idUzrast, opsegGodina, naziv);
                Polaznik p = new Polaznik(idPolaznik, imePolaznik, prezimePolaznik, emailPolaznik, uk);
                VrstaPlesa vp = new VrstaPlesa(idVrstaPlesa, nazivVrstaPlesa, kategorijaVrstaPlesa, cenaCasa);
                s = new Sertifikat(idSertifikat, datumIzdavanja, mestoIzdavanja, Nivo.valueOf(nivo), napomena, p, vp);
            }
            return s;
        } catch (SQLException ex) {
            System.err.println("Greška prilikom obrade podataka iz ResultSet-a kod vraćanja sertifikata: " + ex.getMessage());
            return null;
        }
    }
}