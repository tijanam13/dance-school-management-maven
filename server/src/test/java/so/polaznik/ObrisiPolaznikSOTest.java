package so.polaznik;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za sistemsku operaciju {@link ObrisiPolaznikSO}.
 * Testiraju se preduslovi sistemske operacije koji ne zahtevaju
 * konekciju na bazu podataka, kao i pocetne vrednosti atributa.
 *
 * @author Tijana
 * @version 1.0
 * @see ObrisiPolaznikSO
 */
@DisplayName("Testovi za SO ObrisiPolaznikSO")
public class ObrisiPolaznikSOTest {

    private ObrisiPolaznikSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new ObrisiPolaznikSO();
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
     * Test preduslova - parametar pogresnog tipa Kvalifikacija mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipKvalifikacija() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Kvalifikacija(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }
}
