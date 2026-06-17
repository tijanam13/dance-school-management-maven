package so.instruktor;

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
 * Test klasa za sistemsku operaciju {@link PrijaviInstruktorSO}.
 * Testiraju se preduslovi sistemske operacije, pocetne vrednosti atributa,
 * kao i logika prijave instruktora uz pomoc mock objekata.
 *
 * @author Tijana
 * @version 1.0
 * @see PrijaviInstruktorSO
 */
@DisplayName("Testovi za SO PrijaviInstruktorSO")
public class PrijaviInstruktorSOTest {

    private PrijaviInstruktorSO so;

    /**
     * Priprema test okruzenja pre svakog testa.
     */
    @BeforeEach
    public void setUp() {
        so = new PrijaviInstruktorSO();
    }

    /**
     * Cisti test okruzenje posle svakog testa.
     */
    @AfterEach
    public void tearDown() {
        so = null;
    }

    /**
     * Test getInstruktor - pocetna vrednost mora biti null.
     */
    @Test
    public void testGetInstruktorPocetnaVrednost() {
        assertNull(so.getInstruktor(), "Početna vrednost instruktora mora biti null");
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
     * Test preduslova - parametar pogresnog tipa Kvalifikacija mora baciti izuzetak.
     */
    @Test
    public void testPredusloviPogresanTipKvalifikacija() {
        Exception ex = assertThrows(Exception.class,
                () -> so.izvrsiOperaciju(new Kvalifikacija("Sertifikat za trenera modernih plesova", "Plesni savez Srbije"), null));
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
    private PrijaviInstruktorSO napraviSOSaMockom(
            DBRepozitorijumGenericki mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        PrijaviInstruktorSO soSaMockom = new PrijaviInstruktorSO();
        java.lang.reflect.Field brokerField = so.OpstaSO.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    /**
     * Test prijave instruktora - instruktor sa ispravnim korisnickim imenom i sifrom
     * se pronalazi u listi i postavlja kao prijavljen.
     */
    @Test
    public void testIzvrsiInstruktorPronadjen() throws Exception {
        Instruktor parametar = new Instruktor("korisnik1", "sifra123");

        Instruktor instruktorIzBaze = new Instruktor(1, "korisnik1", "sifra123", "Ana", "Anić", "ana@gmail.com");
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktorIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Instruktor.class))).thenReturn((List) lista);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeUpdate(anyString())).thenReturn(1);
        when(mockConnection.getAutoCommit()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PrijaviInstruktorSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);
            assertEquals(instruktorIzBaze, soSaMockom.getInstruktor(),
                    "Instruktor mora biti pronađen i postavljen");
        }
    }

    /**
     * Test prijave instruktora - instruktor sa neispravnom sifrom nije pronadjen,
     * getInstruktor vraca null.
     */
    @Test
    public void testIzvrsiInstruktorNijePronadjenPogresnaSifra() throws Exception {
        Instruktor parametar = new Instruktor("korisnik1", "sifra321");

        Instruktor instruktorIzBaze = new Instruktor(1, "korisnik1", "sifra123", "Ana", "Anić", "ana@gmail.com");
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktorIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Instruktor.class))).thenReturn((List) lista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PrijaviInstruktorSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);
            assertNull(soSaMockom.getInstruktor(),
                    "Instruktor mora biti null kada šifra nije ispravna");
        }
    }

    /**
     * Test prijave instruktora - instruktor sa neispravnim korisnickim imenom
     * nije pronadjen, getInstruktor vraca null.
     */
    @Test
    public void testIzvrsiInstruktorNijePronadjenPogresnoKorisnickoIme() throws Exception {
        Instruktor parametar = new Instruktor("korisnik2", "sifra123");

        Instruktor instruktorIzBaze = new Instruktor(1, "korisnik1", "sifra123", "Ana", "Anić", "ana@gmail.com");
        List<Instruktor> lista = new ArrayList<>();
        lista.add(instruktorIzBaze);

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Instruktor.class))).thenReturn((List) lista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PrijaviInstruktorSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);
            assertNull(soSaMockom.getInstruktor(),
                    "Instruktor mora biti null kada korisničko ime nije ispravno");
        }
    }

    /**
     * Test prijave instruktora - prazna lista instruktora iz baze,
     * getInstruktor vraca null.
     */
    @Test
    public void testIzvrsiPraznaListaInstruktora() throws Exception {
        Instruktor parametar = new Instruktor("korisnik1", "sifra123");
        List<Instruktor> praznaLista = new ArrayList<>();

        DBRepozitorijumGenericki mockBroker = mock(DBRepozitorijumGenericki.class);
        when(mockBroker.vratiSve(any(Instruktor.class))).thenReturn((List) praznaLista);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            PrijaviInstruktorSO soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.izvrsiOperaciju(parametar, null);
            assertNull(soSaMockom.getInstruktor(),
                    "Instruktor mora biti null kada je lista prazna");
        }
    }
}
