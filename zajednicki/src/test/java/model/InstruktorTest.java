package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link Instruktor}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu Instruktor")
public class InstruktorTest extends OpstiDomenskiObjekatTest {

    private Instruktor instruktor;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        return new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljevic", "tijana@gmail.com");
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        instruktor = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     * Proverava da li su svi atributi ispravno postavljeni.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, instruktor.getIdInstruktor());
        assertEquals("tijana", instruktor.getKorisnickoIme());
        assertEquals("tijana*13", instruktor.getSifra());
        assertEquals("Tijana", instruktor.getIme());
        assertEquals("Milosavljević", instruktor.getPrezime());
        assertEquals("tijana@gmail.com", instruktor.getEmail());
        assertNotNull(instruktor.getInstruktorKvalifikacije());
    }

    /**
     * Test konstruktora bez ID-a.
     * Proverava da li su svi atributi, sem ID-a, ispravno postavljeni.
     */
    @Test
    public void testKonstruktorBezId() {
        Instruktor i = new Instruktor("marko", "marko123", "Marko", "Marković", "marko@gmail.com");
        assertEquals("marko", i.getKorisnickoIme());
        assertEquals("marko123", i.getSifra());
        assertEquals("Marko", i.getIme());
        assertEquals("Marković", i.getPrezime());
        assertEquals("marko@gmail.com", i.getEmail());
        assertNotNull(i.getInstruktorKvalifikacije());
    }

    /**
     * Test konstruktora sa korisnickim imenom i sifrom.
     * Proverava da li su dobro postavljeni samo atributi korisnicko ime i sifra,
     * a ostali atributi su null.
     */
    @Test
    public void testKonstruktorKorisnikSifra() {
        Instruktor i = new Instruktor("ana123", "ana*456");
        assertEquals("ana123", i.getKorisnickoIme());
        assertEquals("ana*456", i.getSifra());
        assertNull(i.getIme());
        assertNull(i.getPrezime());
        assertNull(i.getEmail());
        assertNotNull(i.getInstruktorKvalifikacije());
    }

    /**
     * Test podrazumevanog konstruktora bez parametara.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        Instruktor i = new Instruktor();
        assertNotNull(i);
    }

    /**
     * Test setIdInstruktor sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "100", "999"})
    public void testSetIdInstruktor(int id) {
        instruktor.setIdInstruktor(id);
        assertEquals(id, instruktor.getIdInstruktor());
    }

    /**
     * Test setKorisnickoIme sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"korisnik1", "ana", "test_korisnik"})
    public void testSetKorisnickoIme(String korisnickoIme) {
        instruktor.setKorisnickoIme(korisnickoIme);
        assertEquals(korisnickoIme, instruktor.getKorisnickoIme());
    }

    /**
     * Test setSifra.
     */
    @Test
    public void testSetSifra() {
        instruktor.setSifra("novaSifra");
        assertEquals("novaSifra", instruktor.getSifra());
    }

    /**
     * Test setIme sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Ana", "Marko", "Jovana"})
    public void testSetIme(String ime) {
        instruktor.setIme(ime);
        assertEquals(ime, instruktor.getIme());
    }

    /**
     * Test setPrezime sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Anić", "Marković", "Jovanović"})
    public void testSetPrezime(String prezime) {
        instruktor.setPrezime(prezime);
        assertEquals(prezime, instruktor.getPrezime());
    }

    /**
     * Test setEmail.
     */
    @Test
    public void testSetEmail() {
        instruktor.setEmail("tijana.m123@gmail.com");
        assertEquals("tijana.m123@gmail.com", instruktor.getEmail());
    }

    /**
     * Test setInstruktorKvalifikacije sa listom koja sadrzi elemente.
     */
    @Test
    public void testSetInstruktorKvalifikacije() {
        List<InstruktorKvalifikacija> lista = new ArrayList<>();
        Kvalifikacija k = new Kvalifikacija(1, "Licencirani trener sportskog plesa", "Savez sportskog plesa Srbije");
        lista.add(new InstruktorKvalifikacija(instruktor, k, null, Nivo.osnovni));
        instruktor.setInstruktorKvalifikacije(lista);
        assertEquals(1, instruktor.getInstruktorKvalifikacije().size());
        assertEquals(lista, instruktor.getInstruktorKvalifikacije());
    }

    /**
     * Test setInstruktorKvalifikacije sa praznom listom.
     */
    @Test
    public void testSetInstruktorKvalifikacijePraznaLista() {
        instruktor.setInstruktorKvalifikacije(new ArrayList<>());
        assertNotNull(instruktor.getInstruktorKvalifikacije());
        assertTrue(instruktor.getInstruktorKvalifikacije().isEmpty());
    }

    /**
     * Test equals - isto korisnicko ime i sifra => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        Instruktor i2 = new Instruktor(2, "tijana", "tijana*13", "Ana", "Anić", "ana@gmail.com");
        assertEquals(instruktor, i2);
    }

    /**
     * Test equals - razlicito korisnicko ime => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitoKorisnickoIme() {
        Instruktor i2 = new Instruktor(1, "tijana13", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
        assertNotEquals(instruktor, i2);
    }

    /**
     * Test equals - razlicita sifra => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaSifra() {
        Instruktor i2 = new Instruktor(1, "tijana", "sifra123", "Tijana", "Milosavljević", "tijana@gmail.com");
        assertNotEquals(instruktor, i2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, instruktor);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(instruktor, instruktor);
    }

    /**
     * Test equals - poredenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", instruktor);
    }

    /**
     * Test toString - vraca ime i prezime.
     */
    @Test
    public void testToString() {
        assertEquals("Tijana Milosavljević", instruktor.toString());
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("instruktor", instruktor.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("ime, prezime, email, sifra, korisnickoIme", instruktor.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("'Tijana', 'Milosavljević', 'tijana@gmail.com', 'tijana*13', 'tijana'",
                instruktor.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("instruktor.idInstruktor = 1", instruktor.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("ime = 'Tijana', prezime = 'Milosavljević', email = 'tijana@gmail.com', korisnickoIme = 'tijana', sifra = 'tijana*13'",
                instruktor.vratiVrednostiIzmeni());
    }
}
