package so.upisnica;

import model.Instruktor;
import model.Polaznik;
import model.StavkaUpisnice;
import model.Upisnica;
import model.UzrasnaKategorija;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za sistemsku operaciju {@link StatistikaUpisnicaSO}.
 * Testira racunanje statistike (ukupna i prosecna clanarina, upisnice iznad
 * proseka, broj upisnica po instruktoru) koje se izvodi preko Stream API-ja.
 * Testovi ne koriste bazu podataka - lista upisnica se prosledjuje direktno
 * kroz testni konstruktor.
 *
 * @author Tijana
 * @version 1.0
 * @see StatistikaUpisnicaSO
 */
@DisplayName("Testovi za SO StatistikaUpisnicaSO")
public class StatistikaUpisnicaSOTest {

    private StatistikaUpisnicaSO so;
    private Instruktor instruktorAna;
    private Instruktor instruktorMarko;
    private Polaznik polaznik;

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new StatistikaUpisnicaSO();
        instruktorAna = new Instruktor(1, "ana", "ana123", "Ana", "Anić", "ana@gmail.com");
        instruktorMarko = new Instruktor(2, "marko", "marko123", "Marko", "Marković", "marko@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
        instruktorAna = null;
        instruktorMarko = null;
        polaznik = null;
        new File("upisnice.json").delete();
    }

    /**
     * Test pocetnih vrednosti - pre izvrsavanja sve liste/mape moraju biti null,
     * a numericke vrednosti 0.
     */
    @Test
    public void testPocetneVrednosti() {
        assertEquals(0.0, so.getUkupnaClanarina());
        assertEquals(0.0, so.getProsecnaClanarina());
        assertNull(so.getUpisniceIznadProseka());
        assertNull(so.getBrojUpisnicaPoInstruktoru());
    }

    /**
     * Test preduslova - operacija nema ogranicenja na tip parametra,
     * pa ne baca izuzetak ni za null parametar.
     */
    @Test
    public void testPredusloviProlazeZaNullParametar() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    /**
     * Test izvrsavanja - tri upisnice sa razlicitim clanarinama i instruktorima.
     * Provera ukupne i prosecne clanarine, liste iznad proseka (sortirane
     * opadajuce) i grupisanja broja upisnica po instruktoru.
     * Ne koristi bazu - lista se prosledjuje kroz testni konstruktor.
     */
    @Test
    public void testIzvrsiRacunaStatistiku() throws Exception {
        Upisnica u1 = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000),
                3000.0, instruktorAna, polaznik);
        Upisnica u2 = new Upisnica(2, new Date(System.currentTimeMillis() - 172800000),
                9000.0, instruktorAna, polaznik);
        Upisnica u3 = new Upisnica(3, new Date(System.currentTimeMillis() - 259200000),
                6000.0, instruktorMarko, polaznik);

        List<Upisnica> sveUpisnice = new ArrayList<>();
        sveUpisnice.add(u1);
        sveUpisnice.add(u2);
        sveUpisnice.add(u3);

        StatistikaUpisnicaSO soSaListom = new StatistikaUpisnicaSO(sveUpisnice);
        soSaListom.izvrsiOperaciju(null, null);

        assertEquals(18000.0, soSaListom.getUkupnaClanarina(), 0.001);
        assertEquals(6000.0, soSaListom.getProsecnaClanarina(), 0.001);
        assertEquals(1, soSaListom.getUpisniceIznadProseka().size());
        assertEquals(9000.0, soSaListom.getUpisniceIznadProseka().get(0).getUkupnaClanarina(), 0.001);
        assertEquals(2L, soSaListom.getBrojUpisnicaPoInstruktoru().get("ana"));
        assertEquals(1L, soSaListom.getBrojUpisnicaPoInstruktoru().get("marko"));
    }

    /**
     * Test izvrsavanja - prazna lista upisnica.
     * Ukupna i prosecna clanarina moraju biti 0, a liste/mape prazne (ne null).
     * Ne koristi bazu - lista se prosledjuje kroz testni konstruktor.
     */
    @Test
    public void testIzvrsiPraznaLista() throws Exception {
        StatistikaUpisnicaSO soSaListom = new StatistikaUpisnicaSO(new ArrayList<>());
        soSaListom.izvrsiOperaciju(null, null);

        assertEquals(0.0, soSaListom.getUkupnaClanarina(), 0.001);
        assertEquals(0.0, soSaListom.getProsecnaClanarina(), 0.001);
        assertTrue(soSaListom.getUpisniceIznadProseka().isEmpty());
        assertTrue(soSaListom.getBrojUpisnicaPoInstruktoru().isEmpty());
    }

    /**
     * Test izvrsavanja - sve upisnice imaju istu clanarinu.
     * Nijedna ne sme biti "iznad proseka" jer su sve jednake prosecnoj vrednosti.
     * Ne koristi bazu - lista se prosledjuje kroz testni konstruktor.
     */
    @Test
    public void testIzvrsiSveUpisniceJednakaClanarina() throws Exception {
        Upisnica u1 = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000),
                5000.0, instruktorAna, polaznik);
        Upisnica u2 = new Upisnica(2, new Date(System.currentTimeMillis() - 172800000),
                5000.0, instruktorMarko, polaznik);

        List<Upisnica> sveUpisnice = new ArrayList<>();
        sveUpisnice.add(u1);
        sveUpisnice.add(u2);

        StatistikaUpisnicaSO soSaListom = new StatistikaUpisnicaSO(sveUpisnice);
        soSaListom.izvrsiOperaciju(null, null);

        assertEquals(5000.0, soSaListom.getProsecnaClanarina(), 0.001);
        assertTrue(soSaListom.getUpisniceIznadProseka().isEmpty(),
                "Nijedna upisnica ne sme biti iznad proseka kada su sve jednake");
    }
}