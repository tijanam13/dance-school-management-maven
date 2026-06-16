package so.polaznik;

import java.util.List;
import model.Polaznik;
import so.OpstaSO;

/**
 * Sistemska operacija za vracanje liste polaznika po imenu i prezimenu.
 * Pretraga se vrsi po imenu, prezimenu ili kombinaciji imena i prezimena.
 * Ako se unese samo jedna rec, pretrazuje se i po imenu i po prezimenu.
 * Ako se unesu dve reci, pretrazuje se po kombinaciji ime-prezime i prezime-ime.
 *
 * @author Tijana
 * @version 1.0
 * @see Polaznik
 * @see OpstaSO
 */
public class VratiListuPolaznikPolaznikSO extends OpstaSO {

    /** Lista polaznika pronadjena u bazi podataka. */
    private List<Polaznik> lista;

    /**
     * Vraca listu polaznika pronadjenih u bazi podataka.
     *
     * @return lista polaznika koji odgovaraju kriterijumu pretrage, ili null ako dodje do greske
     */
    public List<Polaznik> getLista() {
        return lista;
    }

    /**
     * Proverava preduslove pre vracanja liste polaznika.
     * Proverava da li je prosleden parametar odgovarajuceg tipa.
     *
     * @param parametar objekat tipa {@link Polaznik} koji se koristi za pretragu
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu polaznika u bazi podataka po imenu i prezimenu.
     * Koristi JOIN sa tabelom uzrasna_kategorija kako bi vratio kompletne
     * objekte polaznika. Uslov pretrage se formira na osnovu broja unetih reci:
     * ako je uneta jedna rec, pretrazuje se i po imenu i po prezimenu,
     * a ako su unete dve reci, pretrazuje se po kombinaciji ime-prezime i prezime-ime.
     *
     * @param parametar objekat tipa {@link Polaznik} koji se koristi za pretragu
     * @param uslov string koji sadrzi ime i/ili prezime polaznika
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast";
        String[] imePrezime = ((String) uslov).strip().split(" ");
        if (imePrezime.length == 2) {
            upit += " WHERE (ime='" + imePrezime[0] + "' AND prezime='" + imePrezime[1]
                    + "') OR (ime='" + imePrezime[1] + "' AND prezime='" + imePrezime[0] + "') ";
        } else {
            upit += " WHERE ime='" + imePrezime[0] + "' OR prezime='" + imePrezime[0] + "'";
        }
        List<Polaznik> lista = broker.vratiSve((Polaznik) parametar, upit);
        this.lista = lista;
    }
}