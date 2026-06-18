package so.upisnica;

import java.util.List;
import model.StavkaUpisnice;
import model.Upisnica;
import servis.JsonServis;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste svih upisnica iz baze podataka.
 * Za svaku pronadjenu upisnicu ucitava se i lista stavki upisnice.
 * Nakon ucitavanja, lista upisnica se serijalizuje u JSON fajl "upisnice.json".
 *
 * @author Tijana
 * @version 1.0
 * @see Upisnica
 * @see StavkaUpisnice
 * @see JsonServis
 * @see OpstaSO
 */
public class VratiListuSviUpisnicaSO extends OpstaSO {

    /** Lista svih upisnica pronadjena u bazi podataka. */
    private List<Upisnica> lista;

    /**
     * Vraca listu svih upisnica pronadjenih u bazi podataka.
     *
     * @return lista svih upisnica, ili null ako dodje do greske
     */
    public List<Upisnica> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste svih upisnica.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Upisnica} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Upisnica)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava ucitavanje svih upisnica iz baze podataka.
     * Koristi JOIN sa tabelama instruktor, polaznik i uzrasna_kategorija.
     * Za svaku pronadjenu upisnicu dodatno ucitava listu stavki upisnice
     * koristeci JOIN sa tabelom vrsta_plesa.
     * Nakon ucitavanja serijalizuje listu upisnica u JSON fajl "upisnice.json".
     *
     * @param parametar objekat tipa {@link Upisnica} koji se koristi za pretragu
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka ili JSON fajlom
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit1 = " JOIN instruktor ON instruktor.idInstruktor = upisnica.idInstruktor"
                + " JOIN polaznik ON upisnica.idPolaznik=polaznik.idPolaznik"
                + " JOIN uzrasna_kategorija ON uzrasna_kategorija.idUzrast = polaznik.idUzrast";
        List<Upisnica> lista = broker.vratiSve((Upisnica) parametar, upit1);
        // Lambda izraz prosledjen forEach metodi Stream API-ja umesto for petlje.
        lista.forEach(upisnica -> {
            String upit2 = " JOIN vrsta_plesa ON vrsta_plesa.idVrstaPlesa=stavka_upisnice.idVrstaPlesa"
                    + " WHERE idUpisnica=" + upisnica.getIdUpisnica();
            try {
                List<StavkaUpisnice> stavke = broker.vratiSve(new StavkaUpisnice(), upit2);
                upisnica.setListaStavke(stavke);
            } catch (Exception e) {
                throw new RuntimeException("Greška pri učitavanju stavki upisnice.", e);
            }
        });
        this.lista = lista;

        JsonServis jsonServis = new JsonServis();
        jsonServis.serijalizujUpisnice(lista, "upisnice.json");
    }
}