package so.upisnica;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.StavkaUpisnice;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa za sistemsku operaciju {@link VratiListuSviUpisnicaSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika vracanja liste upisnica uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuSviUpisnicaSO
 */
@DisplayName("Testovi za SO VratiListuSviUpisnicaSO")
public class VratiListuSviUpisnicaSOTest {

    private VratiListuSviUpisnicaSO so;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private Upisnica parametar;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuSviUpisnicaSO();
        instruktor = new Instruktor(1, "ana", "ana123", "Ana", "Anić", "ana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        parametar = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000),
                3000.0, instruktor, polaznik);
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     * Brise JSON fajl koji se kreira tokom testova.
     */
    @AfterEach
    public void tearDown() {
        so = null;
        instruktor = null;
        polaznik = null;
        parametar = null;
        new File("upisnice.json").delete();
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
     * Pomocna metoda koja kreira SO objekat sa mock brokerom i konekcijom.
     *
     * @param mockBroker mock objekat brokera
     * @param mockConnection mock objekat konekcije
     * @param mockedStatic mock za staticku klasu DBKonekcija
     * @return SO objekat sa injektovanim mock brokerom
     * @throws Exception ako dodje do greske pri refleksiji
     */
    private VratiListuSviUpisnicaSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuSviUpisnicaSO soSaMockom = new VratiListuSviUpisnicaSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - vraca listu sa jednom upisnicom i kreira JSON fajl.
     */
    @Test
    public void testIzvrsiVracaJednuUpisnicu() throws Exception {
        List<Upisnica> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(parametar);

        List<StavkaUpisnice> stavke = new ArrayList<>();
        stavke.add(new StavkaUpisnice(1, 10, 1500.0, 15000.0,
                new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0), parametar));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) ocekivanaLista);
        when(mockBroker.vratiSve(any(StavkaUpisnice.class), anyString()))
                .thenReturn((List) stavke);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviUpisnicaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals(1, soSaMockom.getLista().get(0).getListaStavke().size());
        }
    }

    /**
     * Test izvrsavanja - vraca listu sa vise upisnica.
     */
    @Test
    public void testIzvrsiVracaViseUpisnica() throws Exception {
        Upisnica upisnica2 = new Upisnica(2, new Date(System.currentTimeMillis() - 172800000),
                5000.0, instruktor, polaznik);

        List<Upisnica> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(parametar);
        ocekivanaLista.add(upisnica2);

        List<StavkaUpisnice> prazneStavke = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) ocekivanaLista);
        when(mockBroker.vratiSve(any(StavkaUpisnice.class), anyString()))
                .thenReturn((List) prazneStavke);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviUpisnicaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(2, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - prazna lista upisnica.
     * Ocekuje se prazna lista i kreiranje praznog JSON fajla.
     */
    @Test
    public void testIzvrsiPraznaLista() throws Exception {
        List<Upisnica> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviUpisnicaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
            assertTrue(new File("upisnice.json").exists(),
                    "JSON fajl mora biti kreiran čak i za praznu listu");
        }
    }

    /**
     * Test izvrsavanja - proverava da se JSON fajl kreira nakon ucitavanja.
     */
    @Test
    public void testIzvrsiKreiraJsonFajl() throws Exception {
        List<Upisnica> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(parametar);

        List<StavkaUpisnice> prazneStavke = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) ocekivanaLista);
        when(mockBroker.vratiSve(any(StavkaUpisnice.class), anyString()))
                .thenReturn((List) prazneStavke);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSviUpisnicaSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertTrue(new File("upisnice.json").exists(),
                    "JSON fajl upisnice.json mora biti kreiran");
        }
    }
}
