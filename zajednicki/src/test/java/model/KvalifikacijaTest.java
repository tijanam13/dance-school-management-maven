package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link Kvalifikacija}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu Kvalifikacija")
public class KvalifikacijaTest extends OpstiDomenskiObjekatTest {

    private Kvalifikacija kvalifikacija;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        return new Kvalifikacija(1, "Kvalifikacija za izvodjenje klasicnog baleta", "Plesni savez Srbije");
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        kvalifikacija = new Kvalifikacija(1, "Kvalifikacija za izvođenje i podučavanje klasičnog baleta", "Plesni savez Srbije");
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        kvalifikacija = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, kvalifikacija.getIdKvalifikacija());
        assertEquals("Kvalifikacija za izvođenje i podučavanje klasičnog baleta", kvalifikacija.getTip());
        assertEquals("Plesni savez Srbije", kvalifikacija.getOrganizacija());
    }

    /**
     * Test konstruktora bez ID-a.
     */
    @Test
    public void testKonstruktorBezId() {
        Kvalifikacija k = new Kvalifikacija("Kvalifikacija za ocenjivanje plesnih takmičenja prema pravilima saveza", "Nacionalna plesna akademija");
        assertEquals("Kvalifikacija za ocenjivanje plesnih takmičenja prema pravilima saveza", k.getTip());
        assertEquals("Nacionalna plesna akademija", k.getOrganizacija());
    }

    /**
     * Test podrazumevanog konstruktora.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        Kvalifikacija k = new Kvalifikacija();
        assertNotNull(k);
    }

    /**
     * Test setIdKvalifikacija sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "5", "100"})
    public void testSetIdKvalifikacija(int id) {
        kvalifikacija.setIdKvalifikacija(id);
        assertEquals(id, kvalifikacija.getIdKvalifikacija());
    }

    /**
     * Test setTip sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Kvalifikacija za podučavanje tehnika savremenog i modernog plesa", "Kvalifikacija za kreiranje plesnih koreografija i pripremu nastupa", "Kvalifikacija za rad sa decom i odraslima kroz plesnu edukaciju"})
    public void testSetTip(String tip) {
        kvalifikacija.setTip(tip);
        assertEquals(tip, kvalifikacija.getTip());
    }

    /**
     * Test setOrganizacija sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Plesni savez Srbije", "Savez sportskog plesa Srbije", "Centar za edukaciju plesnih instruktora"})
    public void testSetOrganizacija(String org) {
        kvalifikacija.setOrganizacija(org);
        assertEquals(org, kvalifikacija.getOrganizacija());
    }

    /**
     * Test equals - isti tip i organizacija => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        Kvalifikacija k2 = new Kvalifikacija(2, "Kvalifikacija za izvođenje i podučavanje klasičnog baleta", "Plesni savez Srbije");
        assertEquals(kvalifikacija, k2);
    }

    /**
     * Test equals - razlicit tip => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitTip() {
        Kvalifikacija k2 = new Kvalifikacija(1, "Koreograf sportskih plesova", "Plesni savez Srbije");
        assertNotEquals(kvalifikacija, k2);
    }

    /**
     * Test equals - razlicita organizacija => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaOrganizacija() {
        Kvalifikacija k2 = new Kvalifikacija(1, "Kvalifikacija za izvođenje i podučavanje klasičnog baleta", "Nacionalna plesna akademija");
        assertNotEquals(kvalifikacija, k2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, kvalifikacija);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(kvalifikacija, kvalifikacija);
    }
    

    /**
     * Test equals - poredenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", kvalifikacija);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        String rezultat = kvalifikacija.toString();
        assertTrue(rezultat.contains("Kvalifikacija za izvođenje i podučavanje klasičnog baleta"));
        assertTrue(rezultat.contains("Plesni savez Srbije"));
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("kvalifikacija", kvalifikacija.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("tip, organizacija", kvalifikacija.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("'Kvalifikacija za izvođenje i podučavanje klasičnog baleta', 'Plesni savez Srbije'", kvalifikacija.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("kvalifikacija.idKvalifikacija = 1", kvalifikacija.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("tip = 'Kvalifikacija za izvođenje i podučavanje klasičnog baleta', organizacija = 'Plesni savez Srbije'", kvalifikacija.vratiVrednostiIzmeni());
    }
}
