package so.sertifikat;

import java.util.List;
import model.Sertifikat;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste sertifikata za odredjenog polaznika.
 * Vraca sve sertifikate koje je odredjeni polaznik dobio, sa svim
 * povezanim podacima o vrsti plesa i uzrasnoj kategoriji.
 *
 * @author Tijana
 * @version 1.0
 * @see Sertifikat
 * @see OpstaSO
 */
public class VratiListuSertifikatPolaznikSO extends OpstaSO {

    /** Lista sertifikata pronadjena u bazi podataka. */
    private List<Sertifikat> lista;

    /**
     * Vraca listu sertifikata pronadjenih u bazi podataka.
     *
     * @return lista sertifikata za odredjenog polaznika, ili null ako dodje do greske
     */
    public List<Sertifikat> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste sertifikata.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Sertifikat} koji sadrzi polaznika
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu sertifikata u bazi podataka po ID-u polaznika.
     * Koristi JOIN sa tabelama polaznik, vrsta_plesa i uzrasna_kategorija
     * kako bi vratio kompletne objekte sertifikata sa svim podacima.
     *
     * @param parametar objekat tipa {@link Sertifikat} ciji se polaznik koristi za pretragu
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " JOIN polaznik ON sertifikat.idPolaznik = polaznik.idPolaznik"
                + " JOIN vrsta_plesa ON sertifikat.idVrstaPlesa = vrsta_plesa.idVrstaPlesa"
                + " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast"
                + " WHERE sertifikat.idPolaznik = " + ((Sertifikat) parametar).getPolaznik().getIdPolaznik();
        lista = broker.vratiSve((Sertifikat) parametar, upit);
    }
}