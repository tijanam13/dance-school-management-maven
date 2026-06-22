package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link Polaznik}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu Polaznik")
public class PolaznikTest extends OpstiDomenskiObjekatTest {

    private Polaznik polaznik;
    private UzrasnaKategorija uzrasnaKategorija;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        return new Polaznik(1, "Mila", "Milić", "mila@gmail.com",
                new UzrasnaKategorija(1, "7-12", "deca"));
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        uzrasnaKategorija = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uzrasnaKategorija);
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        polaznik = null;
        uzrasnaKategorija = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, polaznik.getIdPolaznik());
        assertEquals("Mila", polaznik.getIme());
        assertEquals("Milić", polaznik.getPrezime());
        assertEquals("mila@gmail.com", polaznik.getEmail());
        assertEquals(uzrasnaKategorija, polaznik.getUzrasnaKategorija());
    }

    /**
     * Test konstruktora bez ID-a.
     */
    @Test
    public void testKonstruktorBezId() {
        Polaznik p = new Polaznik("Ana", "Anić", "ana@gmail.com", uzrasnaKategorija);
        assertEquals("Ana", p.getIme());
        assertEquals("Anić", p.getPrezime());
        assertEquals("ana@gmail.com", p.getEmail());
        assertEquals(uzrasnaKategorija, p.getUzrasnaKategorija());
    }

    /**
     * Test podrazumevanog konstruktora.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        Polaznik p = new Polaznik();
        assertNotNull(p);
    }
    
    /**
     * Test konstruktora sa null imenom.
     */
    @Test
    public void testKonstruktorNullIme() {
        assertThrows(IllegalArgumentException.class, () -> new Polaznik(1, null, "Milić", "mila@gmail.com", uzrasnaKategorija));
    }

    /**
     * Test konstruktora sa null prezimenom.
     */
    @Test
    public void testKonstruktorNullPrezime() {
        assertThrows(IllegalArgumentException.class, () -> new Polaznik(1, "Mila", null, "mila@gmail.com", uzrasnaKategorija));
    }

    /**
     * Test konstruktora sa emailom bez '@'.
     */
    @Test
    public void testKonstruktorEmailBezAt() {
        assertThrows(IllegalArgumentException.class, () -> new Polaznik(1, "Mila", "Milić", "emailbezat.com", uzrasnaKategorija));
    }

    /**
     * Test konstruktora sa null uzrasnom kategorijom.
     */
    @Test
    public void testKonstruktorNullUzrasnaKategorija() {
        assertThrows(IllegalArgumentException.class, () -> new Polaznik(1, "Mila", "Milić", "mila@gmail.com", null));
    }

    /**
     * Test setIdPolaznik sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "10", "50"})
    public void testSetIdPolaznik(int id) {
        polaznik.setIdPolaznik(id);
        assertEquals(id, polaznik.getIdPolaznik());
    }

    /**
     * Test setIme sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Mila", "Ana", "Jovana"})
    public void testSetIme(String ime) {
        polaznik.setIme(ime);
        assertEquals(ime, polaznik.getIme());
    }
    
    /**
     * Test setIme sa null vrednoscu.
     */
    @Test
    public void testSetImeNull() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setIme(null));
    }

    /**
     * Test setIme sa praznim stringom.
     */
    @Test
    public void testSetImePrazno() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setIme(""));
    }

    /**
     * Test setIme sa stringom koji sadrzi samo razmake.
     */
    @Test
    public void testSetImeSamoRazmaci() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setIme("   "));
    }

    /**
     * Test setPrezime sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Milić", "Anić", "Jović"})
    public void testSetPrezime(String prezime) {
        polaznik.setPrezime(prezime);
        assertEquals(prezime, polaznik.getPrezime());
    }

    /**
     * Test setPrezime sa null vrednoscu.
     */
    @Test
    public void testSetPrezimeNull() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setPrezime(null));
    }

    /**
     * Test setPrezime sa praznim stringom.
     */
    @Test
    public void testSetPrezimePrazno() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setPrezime(""));
    }

    /**
     * Test setPrezime sa stringom koji sadrzi samo razmake.
     */
    @Test
    public void testSetPrezimeSamoRazmaci() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setPrezime("   "));
    }
    
    /**
     * Test setEmail.
     */
    @Test
    public void testSetEmail() {
        polaznik.setEmail("tijana13@gmail.com");
        assertEquals("tijana13@gmail.com", polaznik.getEmail());
    }
    
    /**
     * Test setEmail sa null vrednoscu.
     */
    @Test
    public void testSetEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setEmail(null));
    }

    /**
     * Test setEmail sa praznim stringom.
     */
    @Test
    public void testSetEmailPrazan() {
    	assertThrows(IllegalArgumentException.class, () -> polaznik.setEmail(""));
    }

    /**
     * Test setEmail sa stringom koji sadrzi samo razmake.
     */
    @Test
    public void testSetEmailSamoRazmaci() {
    	assertThrows(IllegalArgumentException.class, () -> polaznik.setEmail("   "));
    }

    /**
     * Test setEmail sa emailom koji ne sadrzi '@'.
     */
    @Test
    public void testSetEmailBezAt() {
    	assertThrows(IllegalArgumentException.class, () -> polaznik.setEmail("emailbezat.com"));
    }
 
    /**
     * Test setUzrasnaKategorija.
     */
    @Test
    public void testSetUzrasnaKategorija() {
        UzrasnaKategorija novaKat = new UzrasnaKategorija(2, "13-18", "juniori");
        polaznik.setUzrasnaKategorija(novaKat);
        assertEquals(novaKat, polaznik.getUzrasnaKategorija());
    }

    /**
     * Test setUzrasnaKategorija sa null vrednoscu.
     */
    @Test
    public void testSetUzrasnaKategorijaNula() {
        assertThrows(IllegalArgumentException.class, () -> polaznik.setUzrasnaKategorija(null));
    }
    
    /**
     * Test equals - isti ID => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        Polaznik p2 = new Polaznik(1, "Sara", "Sarić", "sara@gmail.com", uzrasnaKategorija);
        assertEquals(polaznik, p2);
    }

    /**
     * Test equals - razlicit ID => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitId() {
        Polaznik p2 = new Polaznik(2, "Mila", "Milić", "mila@gmail.com", uzrasnaKategorija);
        assertNotEquals(polaznik, p2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, polaznik);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(polaznik, polaznik);
    }
    
    /**
     * Test equals - poredenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", polaznik);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        assertEquals("Mila Milić", polaznik.toString());
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("polaznik", polaznik.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("ime, prezime, email, idUzrast", polaznik.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("'Mila', 'Milić', 'mila@gmail.com', 1", polaznik.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("polaznik.idPolaznik = 1", polaznik.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("ime = 'Mila', prezime = 'Milić', email = 'mila@gmail.com', idUzrast = 1",
                polaznik.vratiVrednostiIzmeni());
    }
}
