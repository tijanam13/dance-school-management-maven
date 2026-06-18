package so.upisnica;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Upisnica;
import so.OpstaSO;

/**
 * Sistemska operacija koja racuna statisticke podatke nad svim upisnicama
 * koristeci Java Stream API umesto rucnih for petlji.
 *
 * Operacija ucitava sve upisnice (preko {@link VratiListuSviUpisnicaSO}) i nad
 * dobijenom listom racuna:
 * <ul>
 *   <li>ukupnu i prosecnu clanarinu svih upisnica,</li>
 *   <li>listu upisnica cija je clanarina veca od prosecne, sortiranu opadajuce po clanarini,</li>
 *   <li>broj upisnica po instruktoru (grupisanje).</li>
 * </ul>
 *
 * @author Tijana
 * @version 1.0
 * @see Upisnica
 * @see OpstaSO
 */
public class StatistikaUpisnicaSO extends OpstaSO {

    /** Ukupna clanarina svih upisnica. */
    private double ukupnaClanarina;

    /** Prosecna clanarina po upisnici. */
    private double prosecnaClanarina;

    /** Upisnice cija je clanarina iznad proseka, sortirane opadajuce po clanarini. */
    private List<Upisnica> upisniceIznadProseka;

    /** Broj upisnica po instruktoru (korisnicko ime -> broj upisnica). */
    private Map<String, Long> brojUpisnicaPoInstruktoru;

    /**
     * Lista upisnica koja se koristi iskljucivo u testovima.
     * Ako nije null, preskace se poziv baze podataka.
     */
    private List<Upisnica> upisniceZaTest = null;

    /**
     * Podrazumevani konstruktor koji se koristi u produkciji.
     * Upisnice se ucitavaju iz baze podataka.
     */
    public StatistikaUpisnicaSO() {
    }

    /**
     * Konstruktor koji se koristi iskljucivo u testovima.
     * Omogucava ubacivanje liste upisnica bez poziva baze podataka.
     *
     * @param upisnice lista upisnica nad kojom se racuna statistika
     */
    StatistikaUpisnicaSO(List<Upisnica> upisnice) {
        this.upisniceZaTest = upisnice;
    }

    /**
     * @return ukupna clanarina svih upisnica
     */
    public double getUkupnaClanarina() {
        return ukupnaClanarina;
    }

    /**
     * @return prosecna clanarina po upisnici
     */
    public double getProsecnaClanarina() {
        return prosecnaClanarina;
    }

    /**
     * @return upisnice cija je clanarina iznad proseka, sortirane opadajuce po clanarini
     */
    public List<Upisnica> getUpisniceIznadProseka() {
        return upisniceIznadProseka;
    }

    /**
     * @return mapa broja upisnica po korisnickom imenu instruktora
     */
    public Map<String, Long> getBrojUpisnicaPoInstruktoru() {
        return brojUpisnicaPoInstruktoru;
    }

    /**
     * Proverava preduslove pre racunanja statistike.
     * Parametar nije obavezan, pa su preduslovi uvek ispunjeni.
     *
     * @param parametar nije korisceno
     * @throws Exception nikada se ne baca
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
    }

    /**
     * Izvrsava racunanje statistike nad svim upisnicama koristeci Stream API.
     * Ako je objekat kreiran testnim konstruktorom, koristi se prosledjena lista
     * umesto poziva baze podataka.
     *
     * @param parametar nije korisceno
     * @param uslov nije korisceno
     * @throws Exception ako dodje do greske pri ucitavanju upisnica iz baze
     */
    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        List<Upisnica> sveUpisnice;

        if (upisniceZaTest != null) {
            sveUpisnice = upisniceZaTest;
        } else {
            VratiListuSviUpisnicaSO vratiSve = new VratiListuSviUpisnicaSO();
            vratiSve.izvrsiOperaciju(new Upisnica(), null);
            sveUpisnice = vratiSve.getLista();
        }

        // mapToDouble + sum: ukupna clanarina svih upisnica.
        ukupnaClanarina = sveUpisnice.stream()
                .mapToDouble(Upisnica::getUkupnaClanarina)
                .sum();

        // mapToDouble + average: prosecna clanarina (0 ako nema upisnica).
        prosecnaClanarina = sveUpisnice.stream()
                .mapToDouble(Upisnica::getUkupnaClanarina)
                .average()
                .orElse(0.0);

        // filter (lambda Predicate) + sorted (Comparator, opadajuce) + collect(toList).
        upisniceIznadProseka = sveUpisnice.stream()
                .filter(u -> u.getUkupnaClanarina() > prosecnaClanarina)
                .sorted(Comparator.comparingDouble(Upisnica::getUkupnaClanarina).reversed())
                .collect(Collectors.toList());

        // groupingBy + counting: broj upisnica po korisnickom imenu instruktora.
        brojUpisnicaPoInstruktoru = sveUpisnice.stream()
                .collect(Collectors.groupingBy(
                        u -> u.getInstruktor().getKorisnickoIme(),
                        Collectors.counting()));
    }
}