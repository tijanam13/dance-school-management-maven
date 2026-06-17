package so.vrstaPlesa;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa za sistemsku operaciju {@link VratiListuSviVrstaPlesaSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika vracanja liste vrsta plesa uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuSviVrstaPlesaSO
 */
@DisplayName("Testovi za SO VratiListuSviVrstaPlesaSO")
public class VratiListuSviVrstaPlesaSOTest {

    private VratiListuSviVrstaPlesaSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuSviVrstaPlesaSO();
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
    }

    /**
     * Test getLista - pocetna vrednost mora biti null.
     */
    @Test
    public void testGetListaPocetnaVrednost() {
        assertNull(so.getLista(), "Početna vrednost liste mora biti null");
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

    /**
     * Pomocna metoda koja kreira SO objekat sa mock brokerom i konekcijom.
     *
     * @param mockBroker mock objekat brokera
     * @param mockConnection mock objekat konekcije
     * @param mockedStatic mock za staticku klasu DBKonekcija
     * @return SO objekat sa injektovanim mock brokerom
     * @throws Exception ako dodje do greske pri refleksiji
     */
    private VratiListuSviVrstaPlesaSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuSviVrstaPlesaSO soSaMockom = new VratiListuSviVrstaPlesaSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - vraca listu sa vise vrsta plesa.
     */
    @Test
    public void testIzvrsiVracaListuVrstePlesa() throws Exception {
        List<VrstaPlesa> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new VrstaPlesa(1, "Tango", "Latinoamerički", 1549.99));
        ocekivanaLista.add(new VrstaPlesa(2, "Valcer", "Standardni", 1234.50));
        ocekivanaLista.add(new VrstaPlesa(3, "Samba", "Latinoamerički", 1287.75));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(VrstaPlesa.class)))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviVrstaPlesaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(new VrstaPlesa(), null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(3, soSaMockom.getLista().size());
            assertEquals("Tango", soSaMockom.getLista().get(0).getNaziv());
            assertEquals("Valcer", soSaMockom.getLista().get(1).getNaziv());
        }
    }

    /**
     * Test izvrsavanja - vraca jednu vrstu plesa.
     */
    @Test
    public void testIzvrsiVracaJednuVrstuPlesa() throws Exception {
        List<VrstaPlesa> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new VrstaPlesa(1, "Tango", "Latinoamerički", 1549.99));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(VrstaPlesa.class)))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviVrstaPlesaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(new VrstaPlesa(), null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - prazna lista vrsta plesa.
     */
    @Test
    public void testIzvrsiPraznaLista() throws Exception {
        List<VrstaPlesa> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(VrstaPlesa.class)))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviVrstaPlesaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(new VrstaPlesa(), null);

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }
}
