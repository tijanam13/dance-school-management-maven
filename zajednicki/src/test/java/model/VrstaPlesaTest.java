package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

/**
 * Test klasa za domensku klasu {@link VrstaPlesa}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu VrstaPlesa")
public class VrstaPlesaTest extends OpstiDomenskiObjekatTest {

    private VrstaPlesa vrstaPlesa;
    private Validator validator;

    @Override
    protected OpstiDomenskiObjekat getInstance() {
        return new VrstaPlesa(1, "Tango", "Latinoamericki", 1500.0);
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        vrstaPlesa = new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        vrstaPlesa = null;
        validator = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, vrstaPlesa.getIdVrstaPlesa());
        assertEquals("Tango", vrstaPlesa.getNaziv());
        assertEquals("Latinoamerički", vrstaPlesa.getKategorija());
        assertEquals(1500.0, vrstaPlesa.getCenaCasa());
    }

    /**
     * Test konstruktora bez ID-a.
     */
    @Test
    public void testKonstruktorBezId() {
        VrstaPlesa vp = new VrstaPlesa("Valcer", "Standardni", 1200.0);
        assertEquals("Valcer", vp.getNaziv());
        assertEquals("Standardni", vp.getKategorija());
        assertEquals(1200.0, vp.getCenaCasa());
    }

    /**
     * Test konstruktora sa nazivom i kategorijom (bez cene).
     */
    @Test
    public void testKonstruktorNazivKategorija() {
        VrstaPlesa vp = new VrstaPlesa("Samba", "Latinoamerički");
        assertEquals("Samba", vp.getNaziv());
        assertEquals("Latinoamerički", vp.getKategorija());
    }

    /**
     * Test Lombok @NoArgsConstructor - podrazumevani konstruktor bez parametara
     * generisan od strane Lombok biblioteke.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        VrstaPlesa vp = new VrstaPlesa();
        assertNotNull(vp);
    }
    
    /**
     * Test validacije - objekat sa ispravnim vrednostima nema gresaka.
     */
    @Test
    public void testValidnaVrstaPlesa() {
        Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
        assertTrue(violations.isEmpty());
    }

    /**
     * Test Lombok @AllArgsConstructor - konstruktor sa svim parametrima
     * generisan od strane Lombok biblioteke.
     */
    @Test
    public void testAllArgsKonstruktor() {
        VrstaPlesa vp = new VrstaPlesa(3, "Samba", "Latinoamerički", 1800.0);
        assertEquals(3, vp.getIdVrstaPlesa());
        assertEquals("Samba", vp.getNaziv());
        assertEquals("Latinoamerički", vp.getKategorija());
        assertEquals(1800.0, vp.getCenaCasa());
    }

    /**
     * Test Lombok @Setter i @Getter - setIdVrstaPlesa sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "10", "99"})
    public void testSetIdVrstaPlesa(int id) {
        vrstaPlesa.setIdVrstaPlesa(id);
        assertEquals(id, vrstaPlesa.getIdVrstaPlesa());
    }

    /**
     * Test Lombok @Setter i @Getter - setNaziv sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Tango", "Valcer", "Samba"})
    public void testSetNaziv(String naziv) {
        vrstaPlesa.setNaziv(naziv);
        assertEquals(naziv, vrstaPlesa.getNaziv());
    }
    
    /**
     * Test validacije - naziv ne sme biti null.
     */
    @Test
    public void testNazivNull() {
        vrstaPlesa.setNaziv(null);
        Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
        assertFalse(violations.isEmpty());
    }

    /**
     * Test validacije - naziv ne sme biti prazan.
     */
    @Test
    public void testNazivPrazan() {
        vrstaPlesa.setNaziv("");
        Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
        assertFalse(violations.isEmpty());
    }

    /**
     * Test Lombok @Setter i @Getter - setKategorija sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Latinoamerički", "Standardni", "Hip-hop"})
    public void testSetKategorija(String kategorija) {
        vrstaPlesa.setKategorija(kategorija);
        assertEquals(kategorija, vrstaPlesa.getKategorija());
    }

    /**
     * Test validacije - kategorija ne sme biti null.
     */
    @Test
    public void testKategorijaNNull() {
    	vrstaPlesa.setKategorija(null);
    	Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
    	assertFalse(violations.isEmpty());
    }

    /**
     * Test validacije - kategorija ne sme biti prazna.
     */
    @Test
    public void testKategorijaPrazna() {
    	vrstaPlesa.setKategorija("");
    	Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
    	assertFalse(violations.isEmpty());
    }

    /**
     * Test Lombok @Setter i @Getter - setCenaCasa sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1000.0", "1500.0", "2000.0"})
    public void testSetCenaCasa(double cena) {
        vrstaPlesa.setCenaCasa(cena);
        assertEquals(cena, vrstaPlesa.getCenaCasa());
    } 

    /**
     * Test validacije - cena casa mora biti pozitivna.
     */
    @Test
    public void testCenaCasaNula() {
    	vrstaPlesa.setCenaCasa(0);
    	Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
    	assertFalse(violations.isEmpty());
    }

    /**
     * Test validacije - cena casa ne sme biti negativna.
     */
    @Test
    public void testCenaCasaNegativna() {
    	vrstaPlesa.setCenaCasa(-100.0);
    	Set<ConstraintViolation<VrstaPlesa>> violations = validator.validate(vrstaPlesa);
    	assertFalse(violations.isEmpty());
    }

    /**
     * Test equals - isti naziv, kategorija i cena => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        VrstaPlesa vp2 = new VrstaPlesa(2, "Tango", "Latinoamerički", 1500.0);
        assertEquals(vrstaPlesa, vp2);
    }

    /**
     * Test equals - razlicita cena => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaCena() {
        VrstaPlesa vp2 = new VrstaPlesa(1, "Tango", "Latinoamerički", 2000.0);
        assertNotEquals(vrstaPlesa, vp2);
    }

    /**
     * Test equals - razlicit naziv => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitNaziv() {
        VrstaPlesa vp2 = new VrstaPlesa(1, "Valcer", "Latinoamerički", 1500.0);
        assertNotEquals(vrstaPlesa, vp2);
    }

    /**
     * Test equals - razlicita kategorija => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaKategorija() {
        VrstaPlesa vp2 = new VrstaPlesa(1, "Tango", "Standardni", 1500.0);
        assertNotEquals(vrstaPlesa, vp2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, vrstaPlesa);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(vrstaPlesa, vrstaPlesa);
    }

    /**
     * Test equals - poredjenje sa objektom drugog tipa => nisu jednaki.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", vrstaPlesa);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        assertEquals("Tango - Latinoamerički", vrstaPlesa.toString());
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("vrsta_plesa", vrstaPlesa.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("naziv, kategorija, cenaCasa", vrstaPlesa.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        assertEquals("'Tango', 'Latinoamerički', 1500.0", vrstaPlesa.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("vrsta_plesa.idVrstaPlesa=1", vrstaPlesa.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        assertEquals("naziv = 'Tango', kategorija = 'Latinoamerički', cenaCasa = 1500.0",
                vrstaPlesa.vratiVrednostiIzmeni());
    }
}