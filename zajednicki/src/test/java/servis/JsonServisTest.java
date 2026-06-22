package servis;

import model.Instruktor;
import model.InstruktorKvalifikacija;
import model.Kvalifikacija;
import model.Nivo;
import model.Polaznik;
import model.Upisnica;
import model.UzrasnaKategorija;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za servisnu klasu {@link JsonServis}.
 * Testiraju se metode za serijalizaciju i deserijalizaciju
 * domenskih objekata u JSON fajlove, kao i konverzija valuta.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za servisnu klasu JsonServis")
public class JsonServisTest {

    private JsonServis servis;
    private static final String TEST_PUTANJA_INSTRUKTORI = "test_instruktori.json";
    private static final String TEST_PUTANJA_POLAZNICI = "test_polaznici.json";
    private static final String TEST_PUTANJA_UPISNICE = "test_upisnice.json";

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        servis = new JsonServis();
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        servis = null;
        new File(TEST_PUTANJA_INSTRUKTORI).delete();
        new File(TEST_PUTANJA_POLAZNICI).delete();
        new File(TEST_PUTANJA_UPISNICE).delete();
    }

    /**
     * Test serijalizacije i deserijalizacije liste instruktora.
     * Ocekuje se da deserijalizovana lista sadrzi iste podatke kao originalna.
     */
    @Test
    public void testSerijalizujDeserijalizujInstruktore() throws IOException {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(new Instruktor("korisnik1", "sifra1", "Ana", "Anić", "ana@gmail.com"));
        lista.add(new Instruktor("korisnik2", "sifra2", "Marko", "Marković", "marko@gmail.com"));

        servis.serijalizujInstruktore(lista, TEST_PUTANJA_INSTRUKTORI);
        List<Instruktor> rezultat = servis.deserijalizujInstruktore(TEST_PUTANJA_INSTRUKTORI);

        assertNotNull(rezultat, "Deserijalizovana lista ne sme biti null");
        assertEquals(2, rezultat.size(), "Lista mora imati 2 instruktora");
        assertEquals("Ana", rezultat.get(0).getIme());
        assertEquals("Marković", rezultat.get(1).getPrezime());
    }

    /**
     * Test serijalizacije prazne liste instruktora.
     * Ocekuje se da deserijalizovana lista bude prazna.
     */
    @Test
    public void testSerijalizujPraznaListaInstruktora() throws IOException {
        List<Instruktor> lista = new ArrayList<>();

        servis.serijalizujInstruktore(lista, TEST_PUTANJA_INSTRUKTORI);
        List<Instruktor> rezultat = servis.deserijalizujInstruktore(TEST_PUTANJA_INSTRUKTORI);

        assertNotNull(rezultat, "Rezultat ne sme biti null");
        assertTrue(rezultat.isEmpty(), "Lista mora biti prazna");
    }

    /**
     * Test serijalizacije instruktora - proverava da li je fajl kreiran.
     */
    @Test
    public void testSerijalizujInstruktoreFajlKreiran() throws IOException {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(new Instruktor("korisnik1", "sifra1", "Ana", "Anić", "ana@mail.com"));

        servis.serijalizujInstruktore(lista, TEST_PUTANJA_INSTRUKTORI);

        assertTrue(new File(TEST_PUTANJA_INSTRUKTORI).exists(), "JSON fajl mora biti kreiran");
    }

    /**
     * Test deserijalizacije instruktora sa nepostojecim fajlom.
     * Ocekuje se IOException.
     */
    @Test
    public void testDeserijalizujInstruktoreNepostojeciFajl() {
        assertThrows(IOException.class,
                () -> servis.deserijalizujInstruktore("nepostojeci_fajl.json"),
                "Mora se baciti IOException za nepostojeći fajl");
    }

    /**
     * Test serijalizacije i deserijalizacije liste polaznika.
     * Ocekuje se da deserijalizovana lista sadrzi iste podatke kao originalna.
     */
    @Test
    public void testSerijalizujDeserijalizujPolaznike() throws IOException {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        List<Polaznik> lista = new ArrayList<>();
        lista.add(new Polaznik("Mila", "Milić", "mila@gmail.com", uk));
        lista.add(new Polaznik("Ana", "Anić", "ana@gmail.com", uk));

        servis.serijalizujPolaznike(lista, TEST_PUTANJA_POLAZNICI);
        List<Polaznik> rezultat = servis.deserijalizujPolaznike(TEST_PUTANJA_POLAZNICI);

        assertNotNull(rezultat, "Deserijalizovana lista ne sme biti null");
        assertEquals(2, rezultat.size(), "Lista mora imati 2 polaznika");
        assertEquals("Mila", rezultat.get(0).getIme());
        assertEquals("Anić", rezultat.get(1).getPrezime());
    }

    /**
     * Test serijalizacije prazne liste polaznika.
     */
    @Test
    public void testSerijalizujPraznaListaPolaznika() throws IOException {
        List<Polaznik> lista = new ArrayList<>();

        servis.serijalizujPolaznike(lista, TEST_PUTANJA_POLAZNICI);
        List<Polaznik> rezultat = servis.deserijalizujPolaznike(TEST_PUTANJA_POLAZNICI);

        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());
    }

    /**
     * Test deserijalizacije polaznika sa nepostojecim fajlom.
     * Ocekuje se IOException.
     */
    @Test
    public void testDeserijalizujPolazniceNepostojeciFajl() {
        assertThrows(IOException.class,
                () -> servis.deserijalizujPolaznike("nepostojeci_fajl.json"),
                "Mora se baciti IOException za nepostojeći fajl");
    }

    /**
     * Test serijalizacije i deserijalizacije liste upisnica.
     * Ocekuje se da deserijalizovana lista sadrzi iste podatke kao originalna.
     */
    @Test
    public void testSerijalizujDeserijalizujUpisnice() throws IOException {
        Instruktor instruktor = new Instruktor(1, "korisnik1", "sifra1", "Ana", "Anić", "ana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        List<Upisnica> lista = new ArrayList<>();
        try {
            lista.add(new Upisnica(new Date(System.currentTimeMillis() - 86400000), 3000.0, instruktor, polaznik));
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice: " + e.getMessage());
        }
        servis.serijalizujUpisnice(lista, TEST_PUTANJA_UPISNICE);
        List<Upisnica> rezultat = servis.deserijalizujUpisnice(TEST_PUTANJA_UPISNICE);

        assertNotNull(rezultat, "Deserijalizovana lista ne sme biti null");
        assertEquals(1, rezultat.size(), "Lista mora imati 1 upisnicu");
        assertEquals(3000.0, rezultat.get(0).getUkupnaClanarina());
    }

    /**
     * Test serijalizacije prazne liste upisnica.
     * Ocekuje se da deserijalizovana lista bude prazna.
     */
    @Test
    public void testSerijalizujPraznaListaUpisnica() throws IOException {
        List<Upisnica> lista = new ArrayList<>();

        servis.serijalizujUpisnice(lista, TEST_PUTANJA_UPISNICE);
        List<Upisnica> rezultat = servis.deserijalizujUpisnice(TEST_PUTANJA_UPISNICE);

        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());
    }

    /**
     * Test deserijalizacije upisnica sa nepostojecim fajlom.
     * Ocekuje se IOException.
     */
    @Test
    public void testDeserijalizujUpisniceNepostojeciFajl() {
        assertThrows(IOException.class,
                () -> servis.deserijalizujUpisnice("nepostojeci_fajl.json"),
                "Mora se baciti IOException za nepostojeći fajl");
    }

    /**
     * Test rucne serijalizacije instruktora - proverava da li je fajl kreiran.
     */
    @Test
    public void testSerijalizujInstruktoreRucnoFajlKreiran() throws IOException {
        List<Instruktor> lista = new ArrayList<>();
        lista.add(new Instruktor("korisnik1", "sifra1", "Ana", "Anić", "ana@gmail.com"));

        servis.serijalizujInstruktoreRucno(lista, TEST_PUTANJA_INSTRUKTORI);

        assertTrue(new File(TEST_PUTANJA_INSTRUKTORI).exists(), "JSON fajl mora biti kreiran");
    }

    /**
     * Test rucne serijalizacije prazne liste instruktora.
     * Ocekuje se da fajl bude kreiran bez gresaka.
     */
    @Test
    public void testSerijalizujInstruktoreRucnoPraznaLista() throws IOException {
        List<Instruktor> lista = new ArrayList<>();

        assertDoesNotThrow(() -> servis.serijalizujInstruktoreRucno(lista, TEST_PUTANJA_INSTRUKTORI),
                "Ne sme biti greške pri serijalizaciji prazne liste");
        assertTrue(new File(TEST_PUTANJA_INSTRUKTORI).exists());
    }

    /**
     * Test rucne serijalizacije instruktora sa kvalifikacijama.
     * Proverava da se kvalifikacije upisuju u JSON fajl.
     */
    @Test
    public void testSerijalizujInstruktoreRucnoSaKvalifikacijama() throws IOException {
        Instruktor instruktor = new Instruktor(1, "korisnik1", "sifra1", "Ana", "Anić", "ana@gmail.com");
        Kvalifikacija k = new Kvalifikacija(1, "Kvalifikacija za izvođenje i podučavanje klasičnog baleta", "Plesni savez Srbije");
        instruktor.getInstruktorKvalifikacije().add(
                new InstruktorKvalifikacija(instruktor, k, new Date(System.currentTimeMillis() - 86400000), Nivo.osnovni));

        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktor);

        assertDoesNotThrow(() -> servis.serijalizujInstruktoreRucno(lista, TEST_PUTANJA_INSTRUKTORI),
                "Ne sme biti greške pri serijalizaciji instruktora sa kvalifikacijama");
        assertTrue(new File(TEST_PUTANJA_INSTRUKTORI).exists());
    }

    /**
     * Test konverzije iznosa iz dinara u evre.
     * Ocekuje se da je rezultat pozitivan broj.
     */
    @Test
    @Timeout(10)
    public void testKonvertujUEvrePositivnaVrednost() throws Exception {
        double rezultat = servis.konvertujUEvre(1000.0);
        assertTrue(rezultat > 0, "Konvertovana vrednost mora biti pozitivna");
    }

    /**
     * Test konverzije nula dinara u evre.
     * Ocekuje se da je rezultat nula.
     */
    @Test
    @Timeout(10)
    public void testKonvertujUEvreNula() throws Exception {
        double rezultat = servis.konvertujUEvre(0.0);
        assertEquals(0.0, rezultat, 0.0001, "Konverzija nule mora dati nulu");
    }

    /**
     * Test vratiKursRsdEur - ocekuje se pozitivan kurs.
     */
    @Test
    @Timeout(10)
    public void testVratiKursRsdEur() throws Exception {
        double kurs = servis.vratiKursRsdEur();
        assertTrue(kurs > 0, "Kurs mora biti pozitivan broj");
    }
}
