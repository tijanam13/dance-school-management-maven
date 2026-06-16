package so.sertifikat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Sertifikat;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za kreiranje novog sertifikata.
 * Pre kreiranja proverava da li sertifikat vec postoji u bazi podataka
 * za istog polaznika, istu vrstu plesa i isti nivo.
 *
 * @author Tijana
 * @version 1.0
 * @see Sertifikat
 * @see OpstaSO
 */
public class KreirajSertifikatSO extends OpstaSO {

    /** Indikator uspesnosti kreiranja sertifikata. */
    private boolean uspesno = false;

    /** Indikator da li sertifikat vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti kreiranja sertifikata.
     *
     * @return true ako je sertifikat uspesno kreiran, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre kreiranja sertifikata.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li sertifikat vec postoji u bazi podataka za istog polaznika,
     * istu vrstu plesa i isti nivo.
     *
     * @param parametar objekat tipa {@link Sertifikat} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM sertifikat WHERE idPolaznik = "
                    + ((Sertifikat) parametar).getPolaznik().getIdPolaznik()
                    + " AND idVrstaPlesa = " + ((Sertifikat) parametar).getVrstaPlesa().getIdVrstaPlesa()
                    + " AND nivo = '" + ((Sertifikat) parametar).getNivo() + "'";
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
     * Izvrsava kreiranje sertifikata u bazi podataka.
     * Sertifikat se kreira samo ako ne postoji u bazi podataka.
     *
     * @param parametar objekat tipa {@link Sertifikat} koji se kreira
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.dodaj((Sertifikat) parametar);
            uspesno = true;
        }
    }
}