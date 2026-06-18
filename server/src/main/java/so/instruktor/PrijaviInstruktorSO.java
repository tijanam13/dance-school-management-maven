package so.instruktor;

import java.util.List;
import java.util.Optional;
import model.Instruktor;
import so.OpstaSO;

/**
 * Sistemska operacija za prijavu instruktora na sistem.
 * Proverava da li instruktor sa prosledjenim korisnickim imenom i sifrom
 * postoji u bazi podataka i vraca odgovarajuci objekat instruktora.
 *
 * @author Tijana
 * @version 1.0
 * @see Instruktor
 * @see OpstaSO
 */
public class PrijaviInstruktorSO extends OpstaSO {

    /** Instruktor koji je uspesno prijavljen na sistem, ili null ako prijava nije uspela. */
    private Instruktor instruktor = null;

    /**
     * Vraca instruktora koji je uspesno prijavljen na sistem.
     *
     * @return prijavljen instruktor, ili null ako prijava nije uspela
     */
    public Instruktor getInstruktor() {
        return instruktor;
    }

    /**
     * Proverava preduslove pre prijave instruktora.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Instruktor} koji se prijavljuje
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava prijavu instruktora na sistem.
     * Ucitava sve instruktore iz baze i preko Stream API-ja (filter, findFirst)
     * pronalazi onoga cije se korisnicko ime i sifra poklapaju sa prosledjenim
     * parametrom. Ako instruktor nije pronadjen, postavlja vrednost na null.
     *
     * @param parametar objekat tipa {@link Instruktor} sa korisnickim imenom i sifrom
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        List<Instruktor> listaInstruktora = broker.vratiSve((Instruktor) parametar);
        Instruktor trazeni = (Instruktor) parametar;

        // Lambda izraz
        Optional<Instruktor> pronadjen = listaInstruktora.stream()
                .filter(i -> i.getKorisnickoIme().equals(trazeni.getKorisnickoIme())
                        && i.getSifra().equals(trazeni.getSifra()))
                .findFirst();

        instruktor = pronadjen.orElse(null);
    }
}