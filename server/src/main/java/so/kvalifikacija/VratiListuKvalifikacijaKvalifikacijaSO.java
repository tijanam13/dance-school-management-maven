package so.kvalifikacija;

import java.util.List;
import model.Kvalifikacija;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste kvalifikacija po zadatim kriterijumima.
 * Pretraga se moze vrsiti po tipu kvalifikacije, organizaciji koja je izdala
 * kvalifikaciju, ili kombinaciji oba kriterijuma.
 *
 * @author Tijana
 * @version 1.0
 * @see Kvalifikacija
 * @see OpstaSO
 */
public class VratiListuKvalifikacijaKvalifikacijaSO extends OpstaSO {

    /** Lista kvalifikacija pronadjena u bazi podataka. */
    private List<Kvalifikacija> lista;

    /**
     * Vraca listu kvalifikacija pronadjenih u bazi podataka.
     *
     * @return lista kvalifikacija koje odgovaraju kriterijumu pretrage, ili null ako dodje do greske
     */
    public List<Kvalifikacija> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste kvalifikacija.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Kvalifikacija} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu kvalifikacija u bazi podataka po zadatim kriterijumima.
     * Uslov pretrage se formira na osnovu prosledjenih vrednosti:
     * ako je zadat samo tip, pretrazuje se po tipu,
     * ako je zadata samo organizacija, pretrazuje se po organizaciji,
     * a ako su zadate obe vrednosti, pretrazuje se po oba kriterijuma.
     *
     * @param parametar objekat tipa {@link Kvalifikacija} koji se koristi za pretragu
     * @param uslov objekat tipa {@link Kvalifikacija} koji sadrzi kriterijume pretrage
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = "";
        if (((Kvalifikacija) uslov).getTip() == null && ((Kvalifikacija) uslov).getOrganizacija() != null) {
            upit += " WHERE kvalifikacija.organizacija = '" + ((Kvalifikacija) uslov).getOrganizacija() + "'";
        } else if (((Kvalifikacija) uslov).getTip() != null && ((Kvalifikacija) uslov).getOrganizacija() == null) {
            upit += " WHERE kvalifikacija.tip = '" + ((Kvalifikacija) uslov).getTip() + "'";
        } else if (((Kvalifikacija) uslov).getTip() != null && ((Kvalifikacija) uslov).getOrganizacija() != null) {
            upit += " WHERE kvalifikacija.tip = '" + ((Kvalifikacija) uslov).getTip()
                    + "' AND kvalifikacija.organizacija = '" + ((Kvalifikacija) uslov).getOrganizacija() + "'";
        }
        List<Kvalifikacija> lista = broker.vratiSve((Kvalifikacija) parametar, upit);
        this.lista = lista;
    }
}