package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link Upisnica}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 2.0
 */
@DisplayName("Testovi za domensku klasu Upisnica")
public class UpisnicaTest extends OpstiDomenskiObjekatTest {

    private Upisnica upisnica;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private Date datum;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        Instruktor i = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik p = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        try {
            return new Upisnica(1, new Date(System.currentTimeMillis() - 86400000), 3000.0, i, p);
        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom kreiranja test instance Upisnica: " + e.getMessage());
        }
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        instruktor = new Instruktor(1, "tijana", "tijana*13", "Tijana", "Milosavljević", "tijana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        datum = new Date(System.currentTimeMillis() - 86400000);
        try {
            upisnica = new Upisnica(1, datum, 3000.0, instruktor, polaznik);
        } catch (Exception e) {
            fail("Greška prilikom inicijalizacije testnih podataka za Upisnica: " + e.getMessage());
        }
    }

    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        upisnica = null;
        instruktor = null;
        polaznik = null;
        datum = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     * Proverava da su svi atributi ispravno postavljeni i da lista stavki nije null.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, upisnica.getIdUpisnica());
        assertEquals(datum, upisnica.getDatumUpisa());
        assertEquals(3000.0, upisnica.getUkupnaClanarina());
        assertEquals(instruktor, upisnica.getInstruktor());
        assertEquals(polaznik, upisnica.getPolaznik());
        assertNotNull(upisnica.getListaStavke());
    }

    /**
     * Test konstruktora bez ID-a.
     * Proverava da su svi atributi ispravno postavljeni i da lista stavki nije null.
     */
    @Test
    public void testKonstruktorBezId() {
        try {
            Upisnica u = new Upisnica(datum, 2000.0, instruktor, polaznik);
            assertEquals(datum, u.getDatumUpisa());
            assertEquals(2000.0, u.getUkupnaClanarina());
            assertEquals(instruktor, u.getInstruktor());
            assertEquals(polaznik, u.getPolaznik());
            assertNotNull(u.getListaStavke());
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice bez ID-a: " + e.getMessage());
        }
    }

    /**
     * Test podrazumevanog konstruktora.
     * Proverava da kreirani objekat nije null.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        Upisnica u = new Upisnica();
        assertNotNull(u);
    }
    
    /**
     * Test konstruktora sa datumom u buducnosti - ocekuje se izuzetak.
     */
    @Test
    public void testKonstruktorDatumBuducnost() {
        assertThrows(Exception.class, () -> new Upisnica(1, new Date(System.currentTimeMillis() + 86400000), 3000.0, instruktor, polaznik));
    }

    /**
     * Test konstruktora sa null instruktorom - ocekuje se izuzetak.
     */
    @Test
    public void testKonstruktorNullInstruktor() {
        assertThrows(IllegalArgumentException.class, () -> new Upisnica(1, datum, 3000.0, null, polaznik));
    }

    /**
     * Test konstruktora sa null polaznikom - ocekuje se izuzetak.
     */
    @Test
    public void testKonstruktorNullPolaznik() {
        assertThrows(IllegalArgumentException.class, () -> new Upisnica(1, datum, 3000.0, instruktor, null));
    }

    /**
     * Test konstruktora sa neispravnom clanarinom - ocekuje se izuzetak.
     */
    @Test
    public void testKonstruktorNeispravnaClanarina() {
        assertThrows(IllegalArgumentException.class, () -> new Upisnica(1, datum, 0, instruktor, polaznik));
    }

    /**
     * Test setIdUpisnica sa ispravnim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "5", "100"})
    public void testSetIdUpisnica(int id) {
        upisnica.setIdUpisnica(id);
        assertEquals(id, upisnica.getIdUpisnica());
    }

    /**
     * Test setDatumUpisa sa datumom u proslosti - ocekuje se uspesno postavljanje.
     */
    @Test
    public void testSetDatumUpisaIspravan() throws Exception {
        Date noviDatum = new Date(datum.getTime() - 86400000);
        upisnica.setDatumUpisa(noviDatum);
        assertEquals(noviDatum, upisnica.getDatumUpisa(),
                "Datum u proslosti mora biti uspesno postavljen");
    }

    /**
     * Test setDatumUpisa sa datumom u buducnosti - ocekuje se izuzetak.
     */
    @Test
    public void testSetDatumUpisaBuducnost() {
        Date buduciDatum = new Date(System.currentTimeMillis() + 86400000);
        Exception ex = assertThrows(Exception.class,
                () -> upisnica.setDatumUpisa(buduciDatum));
        assertTrue(ex.getMessage().contains("budućnost"));
    }

    /**
     * Test setDatumUpisa sa datumom u buducnosti - proverava da datum ostaje nepromenjen
     * nakon sto je izuzetak bacen.
     */
    @Test
    public void testSetDatumUpisaBuducnostDatumOstaoIsti() {
        Date buduciDatum = new Date(System.currentTimeMillis() + 86400000);
        Date datumPre = upisnica.getDatumUpisa();

        assertThrows(Exception.class,
                () -> upisnica.setDatumUpisa(buduciDatum));

        assertEquals(datumPre, upisnica.getDatumUpisa(),
                "Datum ne sme biti promenjen kada je bacen izuzetak");
    }

    /**
     * Test setDatumUpisa sa trenutnim trenutkom kao granicnim slucajem.
     * Trenutni datum nije u buducnosti, pa se ne sme baciti izuzetak.
     */
    @Test
    public void testSetDatumUpisaTrenutniTrenutakIspravan() {
        Date sadasnji = new Date();
        assertDoesNotThrow(() -> upisnica.setDatumUpisa(sadasnji),
                "Trenutni datum ne sme izazvati izuzetak");
    }

    /**
     * Test setUkupnaClanarina sa ispravnim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"3000.0", "5000.50", "10000.99"})
    public void testSetUkupnaClanarina(double clanarina) {
        upisnica.setUkupnaClanarina(clanarina);
        assertEquals(clanarina, upisnica.getUkupnaClanarina());
    }

    /**
     * Test setUkupnaClanarina sa vrednoscu jednakom nuli - ocekuje se izuzetak.
     */
    @Test
    public void testSetUkupnaClanarínaNula() {
        assertThrows(IllegalArgumentException.class, () -> upisnica.setUkupnaClanarina(0));
    }

    /**
     * Test setUkupnaClanarina sa negativnom vrednoscu - ocekuje se izuzetak.
     */
    @Test
    public void testSetUkupnaClanarínaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> upisnica.setUkupnaClanarina(-100.0));
    }
    
    /**
     * Test setInstruktor sa ispravnim instruktorom.
     */
    @Test
    public void testSetInstruktor() {
        Instruktor noviInstruktor = new Instruktor(2, "marko", "marko123", "Marko", "Marković", "marko@gmail.com");
        upisnica.setInstruktor(noviInstruktor);
        assertEquals(noviInstruktor, upisnica.getInstruktor());
    }

    /**
     * Test setInstruktor sa null vrednoscu - ocekuje se izuzetak.
     */
    @Test
    public void testSetInstruktorNull() {
        assertThrows(IllegalArgumentException.class, () -> upisnica.setInstruktor(null));
    }
    
    /**
     * Test setPolaznik sa ispravnim polaznikom.
     */
    @Test
    public void testSetPolaznik() {
        UzrasnaKategorija uk = new UzrasnaKategorija(2, "13-18", "juniori");
        Polaznik noviPolaznik = new Polaznik(2, "Ana", "Anić", "ana@gmail.com", uk);
        upisnica.setPolaznik(noviPolaznik);
        assertEquals(noviPolaznik, upisnica.getPolaznik());
    }

    /**
     * Test setPolaznik sa null vrednoscu - ocekuje se izuzetak.
     */
    @Test
    public void testSetPolaznikNull() {
        assertThrows(IllegalArgumentException.class, () -> upisnica.setPolaznik(null));
    }
    
    /**
     * Test setListaStavke sa nepraznom listom.
     * Proverava velicinu liste i sadrzaj.
     */
    @Test
    public void testSetListaStavke() {
        List<StavkaUpisnice> lista = new ArrayList<>();
        lista.add(new StavkaUpisnice(1, 10, 1500.0, 15000.0,
                new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0), upisnica));
        upisnica.setListaStavke(lista);
        assertEquals(1, upisnica.getListaStavke().size());
        assertEquals(lista, upisnica.getListaStavke());
    }

    /**
     * Test setListaStavke sa praznom listom.
     * Proverava da lista nije null i da je prazna.
     */
    @Test
    public void testSetListaStavkePraznaLista() {
        upisnica.setListaStavke(new ArrayList<>());
        assertNotNull(upisnica.getListaStavke());
        assertTrue(upisnica.getListaStavke().isEmpty());
    }

    /**
     * Test setListaStavke sa null vrednoscu.
     * Proverava ponasanje kada se prosledi null lista.
     */
    @Test
    public void testSetListaStavkeNull() {
        upisnica.setListaStavke(null);
        assertNull(upisnica.getListaStavke());
    }

    /**
     * Test equals - isti ID i datum => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        try {
            Upisnica u2 = new Upisnica(1, datum, 5000.0, instruktor, polaznik);
            assertEquals(upisnica, u2);
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice u testEqualsIsti: " + e.getMessage());
        }
    }

    /**
     * Test equals - razlicit ID => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitId() {
        try {
            Upisnica u2 = new Upisnica(2, datum, 3000.0, instruktor, polaznik);
            assertNotEquals(upisnica, u2);
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice u testEqualsRazlicitId: " + e.getMessage());
        }
    }

    /**
     * Test equals - razlicit datum => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitDatum() {
        try {
            Date drugiDatum = new Date(datum.getTime() - 86400000);
            Upisnica u2 = new Upisnica(1, drugiDatum, 3000.0, instruktor, polaznik);
            assertNotEquals(upisnica, u2);
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice u testEqualsRazlicitDatum: " + e.getMessage());
        }
    }

    /**
     * Test equals - poredenje sa null => nisu jednaki.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, upisnica);
    }

    /**
     * Test equals - isti objekat => jednaki (refleksivnost).
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(upisnica, upisnica);
    }

    /**
     * Test equals - poredjenje sa objektom drugog tipa => nisu jednaki.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", upisnica);
    }

    /**
     * Parametrizovani test equals - proverava jednakost upisnica sa razlicitim ID-ovima.
     * ID=1 ocekuje jednakost (true), ID=2 ocekuje nejednakost (false).
     */
    @ParameterizedTest
    @CsvSource({
            "1, true",
            "2, false"
    })
    public void testEqualsRazlicitiIdParametrizovano(int id, boolean ocekivano) {
        try {
            Upisnica u2 = new Upisnica(id, datum, 3000.0, instruktor, polaznik);
            assertEquals(ocekivano, upisnica.equals(u2));
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice u testEqualsRazlicitiIdParametrizovano: " + e.getMessage());
        }
    }

    /**
     * Test toString - upisnica bez stavki.
     * Proverava prisustvo datuma i ukupne clanarine, kao i odsustvo stavki.
     */
    @Test
    public void testToStringBezStavki() {
        String rezultat = upisnica.toString();
        assertTrue(rezultat.contains("3000.0"),
                "toString mora sadržati ukupnu članarinu");
        assertTrue(rezultat.contains(datum.toString()),
                "toString mora sadržati datum upisa");
        assertFalse(rezultat.contains("vrsta plesa"),
                "toString ne sme sadržati stavke kada je lista prazna");
    }

    /**
     * Test toString - upisnica sa stavkama.
     * Proverava sve atribute koji se ispisuju kroz StavkaUpisnice.toString():
     * naziv vrste plesa, broj casova, cenu jednog casa i clanarinu stavke.
     * Takodje proverava i podatke same upisnice (datum i ukupna clanarina).
     */
    @Test
    public void testToStringSaStavkama() {
        try {
            List<StavkaUpisnice> lista = new ArrayList<>();
            lista.add(new StavkaUpisnice(1, 10, 1500.0, 15000.0,
                    new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0), upisnica));
            upisnica.setListaStavke(lista);
            String rezultat = upisnica.toString();
            assertTrue(rezultat.contains(datum.toString()));
            assertTrue(rezultat.contains("3000.0"));
            assertTrue(rezultat.contains("Tango"));
            assertTrue(rezultat.contains("10"));
            assertTrue(rezultat.contains("1500.0"));
            assertTrue(rezultat.contains("15000.0"));
        } catch (Exception e) {
            fail("Greška u testToStringSaStavkama: " + e.getMessage());
        }
    }

    /**
     * Test toString - upisnica sa vise stavki.
     * Proverava da se ispisuju podaci svih stavki.
     */
    @Test
    public void testToStringViseStavki() {
        try {
            List<StavkaUpisnice> lista = new ArrayList<>();
            lista.add(new StavkaUpisnice(1, 10, 1500.0, 15000.0,
                    new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0), upisnica));
            lista.add(new StavkaUpisnice(2, 5, 2000.0, 10000.0,
                    new VrstaPlesa(2, "Valcer", "Standardni", 2000.0), upisnica));
            upisnica.setListaStavke(lista);
            String rezultat = upisnica.toString();
            assertTrue(rezultat.contains("Tango"));
            assertTrue(rezultat.contains("Valcer"));
            assertTrue(rezultat.contains("15000.0"));
            assertTrue(rezultat.contains("10000.0"));
        } catch (Exception e) {
            fail("Greška u testToStringViseStavki: " + e.getMessage());
        }
    }

    /**
     * Test vratiImeTabele - proverava naziv tabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("upisnica", upisnica.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci - proverava nazive kolona za INSERT.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("datumUpisa, ukupnaClanarina, idInstruktor, idPolaznik",
                upisnica.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci - proverava vrednosti za INSERT.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        String ocekivano = "'" + sqlDatum + "', 3000.0, 1, 1";
        assertEquals(ocekivano, upisnica.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere - proverava WHERE uslov za identifikaciju upisnice.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("upisnica.idUpisnica = 1", upisnica.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni - proverava vrednosti za UPDATE.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        String ocekivano = "datumUpisa = '" + sqlDatum + "', ukupnaClanarina = 3000.0, idInstruktor = 1, idPolaznik = 1";
        assertEquals(ocekivano, upisnica.vratiVrednostiIzmeni());
    }
}
