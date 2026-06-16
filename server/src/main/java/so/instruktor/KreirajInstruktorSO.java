package so.instruktor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Instruktor;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za kreiranje novog instruktora.
 * Pre kreiranja proverava da li instruktor vec postoji u bazi podataka
 * na osnovu imena, prezimena, korisnickog imena, sifre i email adrese.
 *
 * @author Tijana
 * @version 1.0
 * @see Instruktor
 * @see OpstaSO
 */
public class KreirajInstruktorSO extends OpstaSO {

    /** Indikator uspesnosti kreiranja instruktora. */
    private boolean uspesno = false;

    /** Indikator da li instruktor vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti kreiranja instruktora.
     *
     * @return true ako je instruktor uspesno kreiran, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre kreiranja instruktora.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li instruktor vec postoji u bazi podataka.
     *
     * @param parametar objekat tipa {@link Instruktor} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM instruktor WHERE ime = '" + ((Instruktor) parametar).getIme()
                    + "' AND prezime = '" + ((Instruktor) parametar).getPrezime()
                    + "' AND korisnickoIme = '" + ((Instruktor) parametar).getKorisnickoIme()
                    + "' AND sifra = '" + ((Instruktor) parametar).getSifra()
                    + "' AND email = '" + ((Instruktor) parametar).getEmail() + "'";
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
     * Izvrsava kreiranje instruktora u bazi podataka.
     * Instruktor se kreira samo ako ne postoji u bazi podataka.
     *
     * @param parametar objekat tipa {@link Instruktor} koji se kreira
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.dodaj((Instruktor) parametar);
            uspesno = true;
        }
    }
}