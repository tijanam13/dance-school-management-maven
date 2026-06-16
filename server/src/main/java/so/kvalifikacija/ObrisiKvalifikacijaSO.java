package so.kvalifikacija;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Kvalifikacija;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za brisanje kvalifikacije.
 * Pre brisanja proverava da li je kvalifikacija u upotrebi,
 * tj. da li je dodeljena nekom instruktoru.
 * Kvalifikacija se ne moze obrisati ako je dodeljena bar jednom instruktoru.
 *
 * @author Tijana
 * @version 1.0
 * @see Kvalifikacija
 * @see OpstaSO
 */
public class ObrisiKvalifikacijaSO extends OpstaSO {

    /** Indikator uspesnosti brisanja kvalifikacije. */
    private boolean uspesno = false;

    /** Indikator da li je kvalifikacija dodeljena nekom instruktoru. */
    private boolean uUpotrebi = false;

    /**
     * Vraca indikator uspesnosti brisanja kvalifikacije.
     *
     * @return true ako je kvalifikacija uspesno obrisana, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre brisanja kvalifikacije.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li je kvalifikacija dodeljena nekom instruktoru u bazi podataka.
     *
     * @param parametar objekat tipa {@link Kvalifikacija} koja se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM instruktor_kvalifikacija ik JOIN kvalifikacija k"
                    + " ON k.idKvalifikacija = ik.idKvalifikacija"
                    + " WHERE k.idKvalifikacija = " + ((Kvalifikacija) parametar).getIdKvalifikacija();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                uUpotrebi = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava brisanje kvalifikacije iz baze podataka.
     * Kvalifikacija se brise samo ako nije dodeljena nijednom instruktoru.
     *
     * @param parametar objekat tipa {@link Kvalifikacija} koja se brise
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!uUpotrebi) {
            broker.obrisi((Kvalifikacija) parametar);
            uspesno = true;
        }
    }
}