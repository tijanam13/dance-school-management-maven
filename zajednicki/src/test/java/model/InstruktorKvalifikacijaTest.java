package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link InstruktorKvalifikacija}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu InstruktorKvalifikacija")
public class InstruktorKvalifikacijaTest extends OpstiDomenskiObjekatTest {

    private InstruktorKvalifikacija ik;
    private Instruktor instruktor;
    private Kvalifikacija kvalifikacija;
    private Date datum;

    @Override
    protected OpstiDomenskiObjekat getInstance() {
        Instruktor i = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljevic", "tijana@gmail.com");
        Kvalifikacija k = new Kvalifikacija(1, "Kvalifikacija za izvodjenje klasicnog baleta", "Plesni savez Srbije");
        return new InstruktorKvalifikacija(i, k, Date.valueOf("2023-05-10"), Nivo.osnovni);
    }
    
    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
        kvalifikacija = new Kvalifikacija(1, "Kvalifikacija za izvođenje i podučavanje klasičnog baleta", "Plesni savez Srbije");
        datum = Date.valueOf("2023-05-10");
        ik = new InstruktorKvalifikacija(instruktor, kvalifikacija, datum, Nivo.osnovni);
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        ik = null;
        instruktor = null;
        kvalifikacija = null;
        datum = null;
    }

    /**
     * Test konstruktora sa svim parametrima.
     */
    @Test
    public void testKonstruktorSaParametrima() {
        assertEquals(instruktor, ik.getInstruktor());
        assertEquals(kvalifikacija, ik.getKvalifikacija());
        assertEquals(datum, ik.getDatumSticanja());
        assertEquals(Nivo.osnovni, ik.getNivo());
    }

    /**
     * Test podrazumevanog konstruktora.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        InstruktorKvalifikacija noviIk = new InstruktorKvalifikacija();
        assertNotNull(noviIk);
    }

    /**
     * Test setInstruktor.
     */
    @Test
    public void testSetInstruktor() {
        Instruktor noviInstruktor = new Instruktor(2, "ana", "ana123", "Ana", "Anić", "ana@gmail.com");
        ik.setInstruktor(noviInstruktor);
        assertEquals(noviInstruktor, ik.getInstruktor());
    }
    
    /**
     * Test setInstruktor sa null vrednoscu.
     */
    @Test
    public void testSetInstruktorNull() {
        assertThrows(IllegalArgumentException.class, () -> ik.setInstruktor(null));
    }
    
    /**
     * Test konstruktora sa null instruktorom.
     */
    @Test
    public void testKonstruktorNullInstruktor() {
        assertThrows(IllegalArgumentException.class, () -> new InstruktorKvalifikacija(null, kvalifikacija, datum, Nivo.osnovni));
    }

    /**
     * Test konstruktora sa null kvalifikacijom.
     */
    @Test
    public void testKonstruktorNullKvalifikacija() {
        assertThrows(IllegalArgumentException.class, () -> new InstruktorKvalifikacija(instruktor, null, datum, Nivo.osnovni));
    }

    /**
     * Test konstruktora sa null datumom.
     */
    @Test
    public void testKonstruktorNullDatum() {
        assertThrows(IllegalArgumentException.class, () -> new InstruktorKvalifikacija(instruktor, kvalifikacija, null, Nivo.osnovni));
    }

    /**
     * Test konstruktora sa datumom u buducnosti.
     */
    @Test
    public void testKonstruktorDatumBuducnost() {
        assertThrows(IllegalArgumentException.class, () -> new InstruktorKvalifikacija(instruktor, kvalifikacija, Date.valueOf("2099-01-01"), Nivo.osnovni));
    }

    /**
     * Test konstruktora sa null nivoom.
     */
    @Test
    public void testKonstruktorNullNivo() {
        assertThrows(IllegalArgumentException.class, () -> new InstruktorKvalifikacija(instruktor, kvalifikacija, datum, null));
    }

    /**
     * Test setKvalifikacija.
     */
    @Test
    public void testSetKvalifikacija() {
        Kvalifikacija novaKval = new Kvalifikacija(2, "Kvalifikacija za ocenjivanje plesnih takmičenja prema pravilima saveza", "Nacionalna plesna akademija");
        ik.setKvalifikacija(novaKval);
        assertEquals(novaKval, ik.getKvalifikacija());
    }
    
    /**
     * Test setKvalifikacija sa null vrednoscu.
     */
    @Test
    public void testSetKvalifikacijaNull() {
        assertThrows(IllegalArgumentException.class, () -> ik.setKvalifikacija(null));
    }

    /**
     * Test setDatumSticanja sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"2020-01-01", "2021-06-15", "2023-05-10"})
    public void testSetDatumSticanja(String datumString) {
        Date noviDatum = Date.valueOf(datumString);
        ik.setDatumSticanja(noviDatum);
        assertEquals(noviDatum, ik.getDatumSticanja());
    }
    
    /**
     * Test setDatumSticanja sa null vrednoscu.
     */
    @Test
    public void testSetDatumSticanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> ik.setDatumSticanja(null));
    }

    /**
     * Test setDatumSticanja sa datumom u buducnosti.
     */
    @Test
    public void testSetDatumSticanjaBuducnost() {
        assertThrows(IllegalArgumentException.class, () -> ik.setDatumSticanja(Date.valueOf("2099-01-01")));
    }

    /**
     * Test setNivo sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @EnumSource(Nivo.class)
    public void testSetNivo(Nivo nivo) {
        ik.setNivo(nivo);
        assertEquals(nivo, ik.getNivo());
    }
    
    /**
     * Test setNivo sa null vrednoscu.
     */
    @Test
    public void testSetNivoNull() {
        assertThrows(IllegalArgumentException.class, () -> ik.setNivo(null));
    }

    /**
     * Test equals - objekti su jednaki ako imaju istog instruktora, istu kvalifikaciju i isti nivo.
     */
    @Test
    public void testEqualsIsti() {
        InstruktorKvalifikacija ik2 = new InstruktorKvalifikacija(instruktor, kvalifikacija, datum, Nivo.osnovni);
        assertEquals(ik, ik2);
    }

    /**
     * Test equals - razlicit nivo => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitNivo() {
        InstruktorKvalifikacija ik2 = new InstruktorKvalifikacija(instruktor, kvalifikacija, datum, Nivo.napredni);
        assertNotEquals(ik, ik2);
    }

    /**
     * Test equals - razlicita kvalifikacija => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaKvalifikacija() {
        Kvalifikacija kvalifikacija2 = new Kvalifikacija(2, "Kvalifikacija za rad sa decom kroz plesnu edukaciju", "Međunarodna organizacija za ples");
        InstruktorKvalifikacija ik2 = new InstruktorKvalifikacija(instruktor, kvalifikacija2, datum, Nivo.osnovni);
        assertNotEquals(ik, ik2);
    }

    /**
     * Test equals - razlicit instruktor => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitInstruktor() {
        Instruktor drugiInstruktor = new Instruktor(2, "marko", "marko!456", "Marko", "Marković", "marko@gmail.com");
        InstruktorKvalifikacija ik2 = new InstruktorKvalifikacija(drugiInstruktor, kvalifikacija, datum, Nivo.osnovni);
        assertNotEquals(ik, ik2);
    }

    /**
     * Test equals - poredjenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, ik);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(ik, ik);
    }

    /**
     * Test equals - poredjenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", ik);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
    	String rezultat = ik.toString();

    	assertTrue(rezultat.contains("Tijana Milosavljević"));
    	assertTrue(rezultat.contains("Kvalifikacija za izvođenje i podučavanje klasičnog baleta"));
    	assertTrue(rezultat.contains("Plesni savez Srbije"));
    	assertTrue(rezultat.contains("osnovni"));
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("instruktor_kvalifikacija", ik.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("datumSticanja, nivo, idKvalifikacija, idInstruktor", ik.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        String ocekivano = "'" + datum + "', 'osnovni', 1, 1";
        assertEquals(ocekivano, ik.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("instruktor_kvalifikacija.idInstruktor = 1", ik.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        String ocekivano = "nivo = 'osnovni', datumSticanja = '" + datum + "', idKvalifikacija = 1, idInstruktor = 1";
        assertEquals(ocekivano, ik.vratiVrednostiIzmeni());
    }
}
