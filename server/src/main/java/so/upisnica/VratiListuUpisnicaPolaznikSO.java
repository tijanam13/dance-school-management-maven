package so.upisnica;

import java.util.List;
import model.StavkaUpisnice;
import model.Upisnica;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste upisnica po imenu i prezimenu polaznika.
 * Pretraga se vrsi po imenu, prezimenu ili kombinaciji imena i prezimena polaznika.
 * Za svaku pronadjenu upisnicu ucitava se i lista stavki upisnice.
 *
 * @author Tijana
 * @version 1.0
 * @see Upisnica
 * @see StavkaUpisnice
 * @see OpstaSO
 */
public class VratiListuUpisnicaPolaznikSO extends OpstaSO {

    /** Lista upisnica pronadjena u bazi podataka. */
    private List<Upisnica> lista;

    /**
     * Vraca listu upisnica pronadjenih u bazi podataka.
     *
     * @return lista upisnica koje odgovaraju kriterijumu pretrage, ili null ako dodje do greske
     */
    public List<Upisnica> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste upisnica.
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
     * Izvrsava pretragu upisnica u bazi podataka po imenu i prezimenu polaznika.
     * Koristi JOIN sa tabelama instruktor, polaznik, uzrasna_kategorija,
     * stavka_upisnice i vrsta_plesa kako bi vratio kompletne objekte upisnica.
     * Za svaku pronadjenu upisnicu dodatno ucitava listu stavki upisnice.
     * Ako je uneta jedna rec, pretrazuje se i po imenu i po prezimenu polaznika,
     * a ako su unete dve reci, pretrazuje se po kombinaciji ime-prezime i prezime-ime.
     *
     * @param parametar objekat tipa {@link Upisnica} koji se koristi za pretragu
     * @param uslov string koji sadrzi ime i/ili prezime polaznika
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String[] imePrezime = ((String) uslov).strip().split(" ");
        String upit1 = " JOIN instruktor ON instruktor.idInstruktor = upisnica.idInstruktor"
                + " JOIN polaznik ON polaznik.idPolaznik=upisnica.idPolaznik"
                + " JOIN uzrasna_kategorija ON uzrasna_kategorija.idUzrast=polaznik.idUzrast"
                + " JOIN stavka_upisnice ON stavka_upisnice.idUpisnica=upisnica.idUpisnica"
                + " JOIN vrsta_plesa ON vrsta_plesa.idVrstaPlesa=stavka_upisnice.idVrstaPlesa";
        if (imePrezime.length == 2) {
            upit1 += " WHERE (polaznik.ime='" + imePrezime[0] + "' AND polaznik.prezime='" + imePrezime[1]
                    + "') OR (polaznik.ime='" + imePrezime[1] + "' AND polaznik.prezime='" + imePrezime[0] + "') ";
        } else {
            upit1 += " WHERE polaznik.ime='" + imePrezime[0] + "' OR polaznik.prezime='" + imePrezime[0] + "'";
        }
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
    }
}