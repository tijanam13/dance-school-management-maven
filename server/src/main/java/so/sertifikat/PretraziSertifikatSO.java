package so.sertifikat;

import model.Sertifikat;
import so.OpstaSO;

/**
 * Sistemska operacija za pretragu sertifikata po jedinstvenom identifikatoru.
 * Vraca jedan sertifikat sa svim povezanim podacima o polazniku i vrsti plesa.
 *
 * @author Tijana
 * @version 1.0
 * @see Sertifikat
 * @see OpstaSO
 */
public class PretraziSertifikatSO extends OpstaSO {

    /** Sertifikat pronadjen u bazi podataka. */
    private Sertifikat sertifikat;

    /**
     * Vraca sertifikat pronadjen u bazi podataka.
     *
     * @return pronadjen sertifikat, ili null ako sertifikat nije pronadjen
     */
    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    /**
     * Proverava preduslove pre pretrage sertifikata.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Sertifikat} koji se pretrazuje
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu sertifikata u bazi podataka na osnovu ID-a.
     * Koristi JOIN sa tabelama polaznik, vrsta_plesa i uzrasna_kategorija
     * kako bi vratio kompletan objekat sertifikata sa svim podacima.
     *
     * @param parametar objekat tipa {@link Sertifikat} ciji se ID koristi za pretragu
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " JOIN polaznik ON sertifikat.idPolaznik = polaznik.idPolaznik"
                + " JOIN vrsta_plesa ON sertifikat.idVrstaPlesa = vrsta_plesa.idVrstaPlesa"
                + " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast"
                + " WHERE sertifikat.idSertifikat = " + ((Sertifikat) parametar).getIdSertifikat();
        sertifikat = (Sertifikat) broker.vratiObjekat((Sertifikat) parametar, upit);
    }
}