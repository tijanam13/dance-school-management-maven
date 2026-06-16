package so.polaznik;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Polaznik;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za brisanje polaznika.
 * Pre brisanja proverava da li je polaznik u upotrebi,
 * tj. da li postoji upisnica koja je vezana za tog polaznika.
 * Polaznik se ne moze obrisati ako ima aktivne upisnice.
 *
 * @author Tijana
 * @version 1.0
 * @see Polaznik
 * @see OpstaSO
 */
public class ObrisiPolaznikSO extends OpstaSO {

    /** Indikator uspesnosti brisanja polaznika. */
    private boolean uspesno;

    /** Indikator da li je polaznik u upotrebi, tj. da li ima aktivne upisnice. */
    private boolean uUpotrebi = false;

    /**
     * Vraca indikator uspesnosti brisanja polaznika.
     *
     * @return true ako je polaznik uspesno obrisan, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre brisanja polaznika.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li polaznik ima aktivne upisnice u bazi podataka.
     *
     * @param parametar objekat tipa {@link Polaznik} koji se brise
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM upisnica WHERE idPolaznik = " + ((Polaznik) parametar).getIdPolaznik();
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
     * Izvrsava brisanje polaznika iz baze podataka.
     * Polaznik se brise samo ako nema aktivnih upisnica.
     *
     * @param parametar objekat tipa {@link Polaznik} koji se brise
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!uUpotrebi) {
            broker.obrisi((Polaznik) parametar);
            uspesno = true;
        }
    }
}