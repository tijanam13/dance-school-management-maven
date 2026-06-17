package so.sertifikat;

import model.Instruktor;
import model.Kvalifikacija;
import model.Nivo;
import model.Polaznik;
import model.Sertifikat;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa za sistemsku operaciju {@link VratiListuSertifikatPolaznikSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika vracanja liste sertifikata uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see VratiListuSertifikatPolaznikSO
 */
@DisplayName("Testovi za SO VratiListuSertifikatPolaznikSO")
public class VratiListuSertifikatPolaznikSOTest {

    private VratiListuSertifikatPolaznikSO so;
    private Polaznik polaznik;
    private VrstaPlesa vrstaPlesa;
    private Sertifikat parametar;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new VratiListuSertifikatPolaznikSO();
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        vrstaPlesa = new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0);
        parametar = new Sertifikat(1, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa);
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
        polaznik = null;
        vrstaPlesa = null;
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
    private VratiListuSertifikatPolaznikSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        VratiListuSertifikatPolaznikSO soSaMockom = new VratiListuSertifikatPolaznikSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - vraca listu sa jednim sertifikatom za polaznika.
     */
    @Test
    public void testIzvrsiVracaJedanSertifikat() throws Exception {
        List<Sertifikat> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Sertifikat(1, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Sertifikat.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSertifikatPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(1, soSaMockom.getLista().size());
            assertEquals(polaznik, soSaMockom.getLista().get(0).getPolaznik());
        }
    }

    /**
     * Test izvrsavanja - vraca listu sa vise sertifikata za polaznika.
     */
    @Test
    public void testIzvrsiVracaVisceSertifikata() throws Exception {
        VrstaPlesa vrstaPlesa2 = new VrstaPlesa(2, "Valcer", "Standardni", 1200.0);
        List<Sertifikat> ocekivanaLista = new ArrayList<>();
        ocekivanaLista.add(new Sertifikat(1, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa));
        ocekivanaLista.add(new Sertifikat(2, Date.valueOf("2024-06-01"),
                "Novi Sad", Nivo.srednji, "Napomena2", polaznik, vrstaPlesa2));

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Sertifikat.class), anyString()))
                .thenReturn((List) ocekivanaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSertifikatPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertEquals(2, soSaMockom.getLista().size());
        }
    }

    /**
     * Test izvrsavanja - polaznik nema sertifikata, vraca praznu listu.
     */
    @Test
    public void testIzvrsiPraznaLista() throws Exception {
        List<Sertifikat> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Sertifikat.class), anyString()))
                .thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            VratiListuSertifikatPolaznikSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getLista());
            assertTrue(soSaMockom.getLista().isEmpty());
        }
    }
}
