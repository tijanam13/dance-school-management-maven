package so.vrstaPlesa;

import java.util.List;
import model.VrstaPlesa;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste svih vrsta plesa iz baze podataka.
 *
 * @author Tijana
 * @version 1.0
 * @see VrstaPlesa
 * @see OpstaSO
 */
public class VratiListuSviVrstaPlesaSO extends OpstaSO {

    /** Lista svih vrsta plesa pronadjena u bazi podataka. */
    private List<VrstaPlesa> lista;

    /**
     * Vraca listu svih vrsta plesa pronadjenih u bazi podataka.
     *
     * @return lista svih vrsta plesa, ili null ako dodje do greske
     */
    public List<VrstaPlesa> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste svih vrsta plesa.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link VrstaPlesa} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava ucitavanje svih vrsta plesa iz baze podataka.
     *
     * @param parametar objekat tipa {@link VrstaPlesa} koji se koristi za pretragu
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        List<VrstaPlesa> lista = broker.vratiSve((VrstaPlesa) parametar);
        this.lista = lista;
    }
}