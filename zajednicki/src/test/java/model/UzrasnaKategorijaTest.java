package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link UzrasnaKategorija}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu UzrasnaKategorija")
public class UzrasnaKategorijaTest extends OpstiDomenskiObjekatTest {

    private UzrasnaKategorija uzrasnaKategorija;

    @Override
    protected OpstiDomenskiObjekat getInstance() {
        return new UzrasnaKategorija(1, "7-12", "deca");
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        uzrasnaKategorija = new UzrasnaKategorija(1, "7-12", "deca");
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        uzrasnaKategorija = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, uzrasnaKategorija.getIdUzrast());
        assertEquals("7-12", uzrasnaKategorija.getOpsegGodina());
        assertEquals("deca", uzrasnaKategorija.getNaziv());
    }

    /**
     * Test konstruktora bez ID-a.
     */
    @Test
    public void testKonstruktorBezId() {
        UzrasnaKategorija uk = new UzrasnaKategorija("13-18", "juniori");
        assertEquals("13-18", uk.getOpsegGodina());
        assertEquals("juniori", uk.getNaziv());
    }

    /**
     * Test Lombok @NoArgsConstructor - podrazumevani konstruktor bez parametara
     * generisan od strane Lombok biblioteke.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        UzrasnaKategorija uk = new UzrasnaKategorija();
        assertNotNull(uk);
    }

    /**
     * Test Lombok @AllArgsConstructor - konstruktor sa svim parametrima
     * generisan od strane Lombok biblioteke.
     */
    @Test
    public void testAllArgsKonstruktor() {
        UzrasnaKategorija uk = new UzrasnaKategorija(5, "19-30", "odrasli");
        assertEquals(5, uk.getIdUzrast());
        assertEquals("19-30", uk.getOpsegGodina());
        assertEquals("odrasli", uk.getNaziv());
    }

    /**
     * Test Lombok @Setter i @Getter - setIdUzrast sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "5", "20"})
    public void testSetIdUzrast(int id) {
        uzrasnaKategorija.setIdUzrast(id);
        assertEquals(id, uzrasnaKategorija.getIdUzrast());
    }

    /**
     * Test Lombok @Setter i @Getter - setOpsegGodina sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"7-12", "13-18", "18-25"})
    public void testSetOpsegGodina(String opseg) {
        uzrasnaKategorija.setOpsegGodina(opseg);
        assertEquals(opseg, uzrasnaKategorija.getOpsegGodina());
    }

    /**
     * Test Lombok @Setter i @Getter - setNaziv sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"deca", "juniori", "odrasli"})
    public void testSetNaziv(String naziv) {
        uzrasnaKategorija.setNaziv(naziv);
        assertEquals(naziv, uzrasnaKategorija.getNaziv());
    }

    /**
     * Test equals - isti ID => jednaki (equals poredi po ID-u).
     */
    @Test
    public void testEqualsIsti() {
        UzrasnaKategorija uk2 = new UzrasnaKategorija(1, "13-18", "juniori");
        assertEquals(uzrasnaKategorija, uk2);
    }

    /**
     * Test equals - razlicit ID => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitId() {
        UzrasnaKategorija uk2 = new UzrasnaKategorija(2, "7-12", "deca");
        assertNotEquals(uzrasnaKategorija, uk2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, uzrasnaKategorija);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(uzrasnaKategorija, uzrasnaKategorija);
    }

    /**
     * Test equals - poredjenje sa objektom drugog tipa => nisu jednaki.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", uzrasnaKategorija);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        assertEquals("Opseg godina: 7-12, naziv: deca", uzrasnaKategorija.toString());
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("uzrasna_kategorija", uzrasnaKategorija.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("opsegGodina, naziv", uzrasnaKategorija.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("'7-12', 'deca'", uzrasnaKategorija.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("uzrasna_kategorija.idUzrast = 1", uzrasnaKategorija.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("opsegGodina = '7-12', naziv = 'deca'", uzrasnaKategorija.vratiVrednostiIzmeni());
    }
}