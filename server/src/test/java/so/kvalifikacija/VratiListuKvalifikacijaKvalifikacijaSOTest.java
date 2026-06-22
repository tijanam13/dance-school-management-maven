package so.kvalifikacija;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
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
 * Test klasa za sistemsku operaciju {@link VratiListuKvalifikacijaKvalifikacijaSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika pretrage kvalifikacija uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuKvalifikacijaKvalifikacijaSO
 */
@DisplayName("Testovi za SO VratiListuKvalifikacijaKvalifikacijaSO")
public class VratiListuKvalifikacijaKvalifikacijaSOTest {

    private VratiListuKvalifikacijaKvalifikacijaSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuKvalifikacijaKvalifikacijaSO();
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
     * Pomocna metoda koja kreira SO objekat sa mock brokerom i konekcijom.
     *
     * @param mockBroker mock objekat brokera
     * @param mockConnection mock objekat konekcije
     * @param mockedStatic mock za staticku klasu DBKonekcija
     * @return SO objekat sa injektovanim mock brokerom
     * @throws Exception ako dodje do greske pri refleksiji
     */
    private VratiListuKvalifikacijaKvalifikacijaSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuKvalifikacijaKvalifikacijaSO soSaMockom = new VratiListuKvalifikacijaKvalifikacijaSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - pretraga samo po tipu kvalifikacije.
     * Ocekuje se lista sa odgovarajucim kvalifikacijama.
     */
    @Test
    public void testIzvrsiPretragaPoTipu() throws Exception {
        Kvalifikacija parametar = new Kvalifikacija("Sertifikat za trenera modernih plesova", null, true);
        Kvalifikacija uslov = new Kvalifikacija("Sertifikat za trenera modernih plesova", null, true);

        List<Kvalifikacija> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Kvalifikacija(1, "Sertifikat za trenera modernih plesova", "Plesni savez Srbije"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Kvalifikacija.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuKvalifikacijaKvalifikacijaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals("Sertifikat za trenera modernih plesova", soSaMockom.getLista().get(0).getTip());
        }
    }

    /**
     * Test izvrsavanja - pretraga samo po organizaciji.
     * Ocekuje se lista sa odgovarajucim kvalifikacijama.
     */
    @Test
    public void testIzvrsiPretragaPoOrganizaciji() throws Exception {
        Kvalifikacija parametar = new Kvalifikacija(null, "Plesni savez Srbije", true);
        Kvalifikacija uslov = new Kvalifikacija(null, "Plesni savez Srbije", true);

        List<Kvalifikacija> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Kvalifikacija(1, "Sertifikat za trenera modernih plesova", "Plesni savez Srbije"));
        ocekivanaLista.add(new Kvalifikacija(2, "Licenca za rad sa dečijim grupama", "Plesni savez Srbije"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Kvalifikacija.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuKvalifikacijaKvalifikacijaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(2, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - pretraga po tipu i organizaciji.
     * Ocekuje se lista sa odgovarajucim kvalifikacijama.
     */
    @Test
    public void testIzvrsiPretragaPoTipuIOrganizaciji() throws Exception {
        Kvalifikacija parametar = new Kvalifikacija("Sertifikat za trenera modernih plesova", "Plesni savez Srbije", true);
        Kvalifikacija uslov = new Kvalifikacija("Sertifikat za trenera modernih plesova", "Plesni savez Srbije", true);

        List<Kvalifikacija> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Kvalifikacija(1, "Sertifikat za trenera modernih plesova", "Plesni savez Srbije"));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Kvalifikacija.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuKvalifikacijaKvalifikacijaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals("Sertifikat za trenera modernih plesova", soSaMockom.getLista().get(0).getTip());
            assertEquals("Plesni savez Srbije", soSaMockom.getLista().get(0).getOrganizacija());
        }
    }

    /**
     * Test izvrsavanja - pretraga bez kriterijuma (tip i organizacija su null).
     * Ocekuje se prazna lista.
     */
    @Test
    public void testIzvrsiPretragaBezKriterijuma() throws Exception {
        Kvalifikacija parametar = new Kvalifikacija(null, null, true);
        Kvalifikacija uslov = new Kvalifikacija(null, null, true);

        List<Kvalifikacija> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Kvalifikacija.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuKvalifikacijaKvalifikacijaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }
}
