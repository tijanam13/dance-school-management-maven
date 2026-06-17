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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test klasa za sistemsku operaciju {@link PretraziSertifikatSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika pretrage sertifikata uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see PretraziSertifikatSO
 */
@DisplayName("Testovi za SO PretraziSertifikatSO")
public class PretraziSertifikatSOTest {

    private PretraziSertifikatSO so;
    private Polaznik polaznik;
    private VrstaPlesa vrstaPlesa;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new PretraziSertifikatSO();
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        vrstaPlesa = new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0);
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
        polaznik = null;
        vrstaPlesa = null;
    }

    /**
     * Test getSertifikat - pocetna vrednost mora biti null.
     */
    @Test
    public void testGetSertifikatPocetnaVrednost() {
        assertNull(so.getSertifikat(), "Početna vrednost sertifikata mora biti null");
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
    private PretraziSertifikatSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        PretraziSertifikatSO soSaMockom = new PretraziSertifikatSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test izvrsavanja - sertifikat je pronadjen u bazi.
     * Ocekuje se da getSertifikat vraca pronadjen sertifikat.
     */
    @Test
    public void testIzvrsiSertifikatPronadjen() throws Exception {
        Sertifikat parametar = new Sertifikat(1, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa);

        Sertifikat ocekivaniSertifikat = new Sertifikat(1, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiObjekat(any(Sertifikat.class), anyString()))
                .thenReturn(ocekivaniSertifikat);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PretraziSertifikatSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNotNull(soSaMockom.getSertifikat(), "Sertifikat mora biti pronađen");
            assertEquals(ocekivaniSertifikat, soSaMockom.getSertifikat());
        }
    }

    /**
     * Test izvrsavanja - sertifikat nije pronadjen u bazi.
     * Ocekuje se da getSertifikat vraca null.
     */
    @Test
    public void testIzvrsiSertifikatNijePronadjen() throws Exception {
        Sertifikat parametar = new Sertifikat(99, Date.valueOf("2024-01-15"),
                "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiObjekat(any(Sertifikat.class), anyString()))
                .thenReturn(null);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PretraziSertifikatSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);

            assertNull(soSaMockom.getSertifikat(), "Sertifikat mora biti null kada nije pronađen");
        }
    }
}
