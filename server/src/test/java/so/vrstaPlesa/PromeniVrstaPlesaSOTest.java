package so.vrstaPlesa;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za sistemsku operaciju {@link PromeniVrstaPlesaSO}.
 * Testiraju se preduslovi sistemske operacije koji ne zahtevaju
 * konekciju na bazu podataka, kao i pocetne vrednosti atributa.
 *
 * @author Tijana
 * @version 1.0
 * @see PromeniVrstaPlesaSO
 */
@DisplayName("Testovi za SO PromeniVrstaPlesaSO")
public class PromeniVrstaPlesaSOTest {

    private PromeniVrstaPlesaSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new PromeniVrstaPlesaSO();
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
    }

    /**
     * Test getUspesno - pocetna vrednost mora biti false.
     */
    @Test
    public void testGetUspesnoPocetnaVrednost() {
        assertFalse(so.getUspesno(), "Početna vrednost atributa uspesno mora biti false");
    }

    /**
     * Test preduslova - null parametar mora baciti izuzetak.
     */
    @Test
    public void testPredusloviNullParametar() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(null, null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa String mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipString() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju("pogresanTip", null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa Integer mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipInteger() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(42, null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa Instruktor mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipInstruktor() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Instruktor(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa Polaznik mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipPolaznik() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Polaznik(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa Kvalifikacija mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipKvalifikacija() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Kvalifikacija(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Test preduslova - parametar pogresnog tipa UzrasnaKategorija mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipUzrasnaKategorija() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new UzrasnaKategorija(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }
}
