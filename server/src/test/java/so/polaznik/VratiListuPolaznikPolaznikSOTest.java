package so.polaznik;

import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.UzrasnaKategorija;
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
 * Test klasa za sistemsku operaciju {@link VratiListuPolaznikPolaznikSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika pretrage polaznika uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuPolaznikPolaznikSO
 */
@DisplayName("Testovi za SO VratiListuPolaznikPolaznikSO")
public class VratiListuPolaznikPolaznikSOTest {

    private VratiListuPolaznikPolaznikSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuPolaznikPolaznikSO();
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
     * Test preduslova - parametar pogresnog tipa Kvalifikacija mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipKvalifikacija() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Kvalifikacija(), null));
        assertTrue(ex.getMessage().contains("odgovarajućeg tipa"));
    }

    /**
     * Pomocna metoda koja kreira SO sa mock brokerom i konekcijom.
     */
    private VratiListuPolaznikPolaznikSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuPolaznikPolaznikSO soSaMockom = new VratiListuPolaznikPolaznikSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - pretraga po jednoj reci (samo ime ili samo prezime).
     * Ocekuje se lista polaznika cije se ime ili prezime poklapa.
     */
    @Test
    public void testIzvrsiPretragaPoJednojReci() throws Exception {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik parametar = new Polaznik();
        String uslov = "Mila";

        List<Polaznik> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Polaznik.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuPolaznikPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals("Mila", soSaMockom.getLista().get(0).getIme());
        }
    }

    /**
     * Test izvrsavanja - pretraga po imenu i prezimenu (dve reci).
     * Ocekuje se lista polaznika cije se ime i prezime poklapaju.
     */
    @Test
    public void testIzvrsiPretragaPoImenuIPrezimenu() throws Exception {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik parametar = new Polaznik();
        String uslov = "Mila Milic";

        List<Polaznik> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Polaznik.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuPolaznikPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals("Mila", soSaMockom.getLista().get(0).getIme());
            assertEquals("Milić", soSaMockom.getLista().get(0).getPrezime());
        }
    }

    /**
     * Test izvrsavanja - pretraga po prezimenu i imenu (obrnuti redosled).
     * Ocekuje se ista lista kao pri normalnom redosledu.
     */
    @Test
    public void testIzvrsiPretragaPoPrezimenIIme() throws Exception {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik parametar = new Polaznik();
        String uslov = "Milić Mila";

        List<Polaznik> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Polaznik.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuPolaznikPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - pretraga koja ne vraca rezultate.
     * Ocekuje se prazna lista.
     */
    @Test
    public void testIzvrsiPretragaBezRezultata() throws Exception {
        Polaznik parametar = new Polaznik();
        String uslov = "Nepostojece";

        List<Polaznik> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Polaznik.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuPolaznikPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }

    /**
     * Test izvrsavanja - pretraga vraca vise polaznika.
     * Ocekuje se lista sa vise polaznika.
     */
    @Test
    public void testIzvrsiPretragaVishePolaznika() throws Exception {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik parametar = new Polaznik();
        String uslov = "Mila";

        List<Polaznik> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk));
        ocekivanaLista.add(new Polaznik(2, "Mila", "Jović", "mila2@gmail.com", uk));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Polaznik.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuPolaznikPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, uslov);

            assertNotNull(soSaMockom.getLista());
            assertEquals(2, soSaMockom.getLista().size());
        }
    }
}
