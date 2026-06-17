package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link StavkaUpisnice}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu StavkaUpisnice")
public class StavkaUpisniceTest extends OpstiDomenskiObjekatTest {

    private StavkaUpisnice stavka;
    private VrstaPlesa vrstaPlesa;
    private Upisnica upisnica;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        VrstaPlesa vp = new VrstaPlesa(1, "Tango", "Latinoamericki", 1500.0);
        Instruktor i = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljevic", "tijana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik p = new Polaznik(1, "Mila", "Milic", "mila@gmail.com", uk);
        Upisnica u = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000), 3000.0, i, p);
        return new StavkaUpisnice(1, 10, 1500.0, 15000.0, vp, u);
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        vrstaPlesa = new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0);
        Instruktor instruktor = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        upisnica = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000), 3000.0, instruktor, polaznik);
        stavka = new StavkaUpisnice(1, 10, 1500.0, 15000.0, vrstaPlesa, upisnica);
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        stavka = null;
        vrstaPlesa = null;
        upisnica = null;
    }

    /**
     * Test konstruktora sa svim parametrima.
     */
    @Test
    public void testKonstruktorSaParametrima() {
        assertEquals(1, stavka.getRb());
        assertEquals(10, stavka.getBrCasova());
        assertEquals(1500.0, stavka.getCena());
        assertEquals(15000.0, stavka.getClanarina());
        assertEquals(vrstaPlesa, stavka.getVrstaPlesa());
        assertEquals(upisnica, stavka.getUpisnica());
    }

    /**
     * Test podrazumevanog konstruktora.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        StavkaUpisnice s = new StavkaUpisnice();
        assertNotNull(s);
    }

    /**
     * Test setRb sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "2", "5"})
    public void testSetRb(int rb) {
        stavka.setRb(rb);
        assertEquals(rb, stavka.getRb());
    }

    /**
     * Test setBrCasova sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"10", "22", "30"})
    public void testSetBrCasova(int brCasova) {
        stavka.setBrCasova(brCasova);
        assertEquals(brCasova, stavka.getBrCasova());
    }

    /**
     * Test setCena sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1000.0", "1500.75", "2000.99"})
    public void testSetCena(double cena) {
        stavka.setCena(cena);
        assertEquals(cena, stavka.getCena());
    }

    /**
     * Test setClanarina sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"10000.0", "15000.75", "20000.99"})
    public void testSetClanarina(double clanarina) {
        stavka.setClanarina(clanarina);
        assertEquals(clanarina, stavka.getClanarina());
    }

    /**
     * Test setVrstaPlesa.
     */
    @Test
    public void testSetVrstaPlesa() {
        VrstaPlesa novaVrsta = new VrstaPlesa(2, "Valcer", "Standardni", 1200.0);
        stavka.setVrstaPlesa(novaVrsta);
        assertEquals(novaVrsta, stavka.getVrstaPlesa());
    }

    /**
     * Test setUpisnica.
     */
    @Test
    public void testSetUpisnica() {
        Instruktor noviInstruktor = new Instruktor(2, "marko", "marko123", "Marko", "Marković", "marko@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(2, "13-18", "juniori");
        Polaznik noviPolaznik = new Polaznik(2, "Ana", "Anić", "ana@gmail.com", uk);
        Upisnica novaUpisnica = new Upisnica(2, new Date(System.currentTimeMillis() - 86400000), 5000.0, noviInstruktor, noviPolaznik);
        stavka.setUpisnica(novaUpisnica);
        assertEquals(novaUpisnica, stavka.getUpisnica());
    }

    /**
     * Test equals - isti broj casova, cena, clanarina i vrsta plesa => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        StavkaUpisnice s2 = new StavkaUpisnice(2, 10, 1500.0, 15000.0, vrstaPlesa, upisnica);
        assertEquals(stavka, s2);
    }

    /**
     * Test equals - razlicit broj casova => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitBrCasova() {
        StavkaUpisnice s2 = new StavkaUpisnice(1, 20, 1500.0, 15000.0, vrstaPlesa, upisnica);
        assertNotEquals(stavka, s2);
    }

    /**
     * Test equals - razlicita cena => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaCena() {
        StavkaUpisnice s2 = new StavkaUpisnice(1, 10, 2000.0, 15000.0, vrstaPlesa, upisnica);
        assertNotEquals(stavka, s2);
    }

    /**
     * Test equals - razlicita clanarina => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaClanarina() {
        StavkaUpisnice s2 = new StavkaUpisnice(1, 10, 1500.0, 20000.0, vrstaPlesa, upisnica);
        assertNotEquals(stavka, s2);
    }

    /**
     * Test equals - razlicita vrsta plesa => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaVrstaPlesa() {
        VrstaPlesa drugarVrsta = new VrstaPlesa(2, "Valcer", "Standardni", 1200.0);
        StavkaUpisnice s2 = new StavkaUpisnice(1, 10, 1500.0, 15000.0, drugarVrsta, upisnica);
        assertNotEquals(stavka, s2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, stavka);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(stavka, stavka);
    }

    /**
     * Test equals - poredenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", stavka);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
    	String rezultat = stavka.toString();
        assertTrue(rezultat.contains("Tango - Latinoamerički"));
        assertTrue(rezultat.contains("broj časova: 10"));
        assertTrue(rezultat.contains("cena jednog časa: 1500.0"));
        assertTrue(rezultat.contains("članarina: 15000.0"));
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("stavka_upisnice", stavka.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("rb, brCasova, cena, clanarina, idVrstaPlesa, idUpisnica",
                stavka.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("1, 10, 1500.0, 15000.0, 1, 1", stavka.vratiVrednostiUbaci());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("rb = 1, brCasova = 10, cena = 1500.0, clanarina = 15000.0, idVrstaPlesa = 1, idUpisnica = 1",
                stavka.vratiVrednostiIzmeni());
    }
    
    /**
     * Test vratiKljucZaWhere - vraca null za StavkaUpisnice jer se
     * stavke identifikuju preko upisnice, a ne direktno.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertNull(stavka.vratiKljucZaWhere());
    }

}
