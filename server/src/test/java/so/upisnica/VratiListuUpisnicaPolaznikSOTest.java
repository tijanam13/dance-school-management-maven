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

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa za sistemsku operaciju {@link VratiListuUpisnicaPolaznikSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika pretrage upisnica po polazniku uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuUpisnicaPolaznikSO
 */
@DisplayName("Testovi za SO VratiListuUpisnicaPolaznikSO")
public class VratiListuUpisnicaPolaznikSOTest {

    private VratiListuUpisnicaPolaznikSO so;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private Upisnica parametar;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuUpisnicaPolaznikSO();
        instruktor = new Instruktor(1, "ana", "ana123", "Ana", "Anić", "ana@gmail.com");
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        try {
            parametar = new Upisnica(1, new Date(System.currentTimeMillis() - 86400000),
                    3000.0, instruktor, polaznik);
        } catch (Exception e) {
            fail("Greška prilikom kreiranja upisnice u setUp: " + e.getMessage());
        }
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
        instruktor = null;
        polaznik = null;
        parametar = null;
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
    private VratiListuUpisnicaPolaznikSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuUpisnicaPolaznikSO soSaMockom = new VratiListuUpisnicaPolaznikSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - pretraga po jednoj reci (ime ili prezime).
     * Ocekuje se lista upisnica sa ucitanim stavkama.
     */
    @Test
    public void testIzvrsiPretragaPoJednojReci() throws Exception {
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
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Mila");

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals(1, soSaMockom.getLista().get(0).getListaStavke().size());
        }
    }

    /**
     * Test izvrsavanja - pretraga po imenu i prezimenu (dve reci).
     * Ocekuje se lista upisnica za polaznika sa tim imenom i prezimenom.
     */
    @Test
    public void testIzvrsiPretragaPoImenuIPrezimenu() throws Exception {
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
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Mila Milić");

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - pretraga po prezimenu i imenu (obrnuti redosled).
     */
    @Test
    public void testIzvrsiPretragaPoPrezimenuIIme() throws Exception {
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
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Milić Mila");

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - pretraga bez rezultata.
     * Ocekuje se prazna lista.
     */
    @Test
    public void testIzvrsiPretragaBezRezultata() throws Exception {
        List<Upisnica> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Nepostojece");

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }

    /**
     * Test izvrsavanja - pretraga vraca vise upisnica.
     */
    @Test
    public void testIzvrsiPretragaViseUpisnica() throws Exception {
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
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Mila");

            assertNotNull(soSaMockom.getLista());
            assertEquals(2, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - uslov sadrzi tri reci.
     * Kod ne pokriva slucaj kada ima vise od dve reci, te se ponasa
     * kao da je uneta samo jedna rec (koristi se samo imePrezime[0]).
     */
    @Test
    public void testIzvrsiPretragaTriReci() throws Exception {
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
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, "Mila Anđela Milić");

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - uslov je prazan string.
     * Ocekuje se da se ne baci izuzetak (split prazno stringa daje niz
     * dimenzije 1 sa praznim elementom), vec da se izvrsi pretraga
     * sa praznim kriterijumom.
     */
    @Test
    public void testIzvrsiPretragaPrazanString() throws Exception {
        List<Upisnica> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Upisnica.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuUpisnicaPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            assertDoesNotThrow(() -> soSaMockom.izvrsiOperaciju(parametar, ""));

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }
}
