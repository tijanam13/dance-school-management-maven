package so.vrstaPlesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.VrstaPlesa;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za izmenu podataka o vrsti plesa.
 * Pre izmene proverava da li vrsta plesa sa istim nazivom, kategorijom
 * i cenom casa vec postoji u bazi podataka kako bi se izbeglo dupliranje.
 *
 * @author Tijana
 * @version 1.0
 * @see VrstaPlesa
 * @see OpstaSO
 */
public class PromeniVrstaPlesaSO extends OpstaSO {

    /** Indikator uspesnosti izmene vrste plesa. */
    private boolean uspesno = false;

    /** Indikator da li vrsta plesa sa istim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene vrste plesa.
     *
     * @return true ako je vrsta plesa uspesno izmenjena, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre izmene vrste plesa.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li vrsta plesa sa istim nazivom, kategorijom i cenom casa
     * vec postoji u bazi podataka.
     *
     * @param parametar objekat tipa {@link VrstaPlesa} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM vrsta_plesa WHERE naziv='" + ((VrstaPlesa) parametar).getNaziv()
                    + "' AND kategorija='" + ((VrstaPlesa) parametar).getKategorija()
                    + "' AND cenaCasa=" + ((VrstaPlesa) parametar).getCenaCasa();
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
     * Izvrsava izmenu podataka o vrsti plesa u bazi podataka.
     * Vrsta plesa se menja samo ako ne postoji druga vrsta plesa
     * sa istim nazivom, kategorijom i cenom casa.
     *
     * @param parametar objekat tipa {@link VrstaPlesa} sa novim podacima
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.izmeni((VrstaPlesa) parametar);
            uspesno = true;
        }
    }
}