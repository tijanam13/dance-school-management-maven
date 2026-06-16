package so.uzrasnaKategorija;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.UzrasnaKategorija;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za izmenu podataka o uzrasnoj kategoriji.
 * Pre izmene proverava da li uzrasna kategorija sa istim opsegom godina
 * i nazivom vec postoji u bazi podataka kako bi se izbeglo dupliranje.
 *
 * @author Tijana
 * @version 1.0
 * @see UzrasnaKategorija
 * @see OpstaSO
 */
public class PromeniUzrasnaKategorijaSO extends OpstaSO {

    /** Indikator uspesnosti izmene uzrasne kategorije. */
    private boolean uspesno = false;

    /** Indikator da li uzrasna kategorija sa istim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene uzrasne kategorije.
     *
     * @return true ako je uzrasna kategorija uspesno izmenjena, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre izmene uzrasne kategorije.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li uzrasna kategorija sa istim opsegom godina i nazivom
     * vec postoji u bazi podataka.
     *
     * @param parametar objekat tipa {@link UzrasnaKategorija} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof UzrasnaKategorija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM uzrasna_kategorija WHERE opsegGodina = '"
                    + ((UzrasnaKategorija) parametar).getOpsegGodina()
                    + "' AND naziv = '" + ((UzrasnaKategorija) parametar).getNaziv() + "'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                postoji = true;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava izmenu podataka o uzrasnoj kategoriji u bazi podataka.
     * Uzrasna kategorija se menja samo ako ne postoji druga uzrasna kategorija
     * sa istim opsegom godina i nazivom.
     *
     * @param parametar objekat tipa {@link UzrasnaKategorija} sa novim podacima
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.izmeni((UzrasnaKategorija) parametar);
            uspesno = true;
        }
    }
}