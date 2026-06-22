package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test klasa za domensku klasu {@link Sertifikat}.
 * Testiraju se konstruktori, set metode, equals, toString
 * i metode nasledjene od {@link OpstiDomenskiObjekat}.
 *
 * @author Tijana
 * @version 1.0
 */
@DisplayName("Testovi za domensku klasu Sertifikat")
public class SertifikatTest extends OpstiDomenskiObjekatTest{

    private Sertifikat sertifikat;
    private Polaznik polaznik;
    private VrstaPlesa vrstaPlesa;
    private Date datum;
    
    @Override
    protected OpstiDomenskiObjekat getInstance() {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        Polaznik p = new Polaznik(1, "Mila", "Milic", "mila@gmail.com", uk);
        VrstaPlesa vp = new VrstaPlesa(1, "Tango", "Latinoamericki", 1500.0);
        return new Sertifikat(1, Date.valueOf("2024-01-15"), "Beograd", Nivo.osnovni, "Napomena", p, vp);
    }

    /**
     * Inicijalizuje testne podatke pre izvrsavanja svakog testa.
     */
    @BeforeEach
    public void setUp() {
        UzrasnaKategorija uk = new UzrasnaKategorija(1, "7-12", "deca");
        polaznik = new Polaznik(1, "Mila", "Milić", "mila@gmail.com", uk);
        vrstaPlesa = new VrstaPlesa(1, "Tango", "Latinoamerički", 1500.0);
        datum = Date.valueOf("2024-01-15");
        sertifikat = new Sertifikat(1, datum, "Beograd", Nivo.osnovni, "Polaznik pokazao odličan napredak tokom kursa.", polaznik, vrstaPlesa);
    }


    /**
     * Oslobadja reference na testne objekte nakon izvrsavanja svakog testa.
     */
    @AfterEach
    public void tearDown() {
        sertifikat = null;
        polaznik = null;
        vrstaPlesa = null;
        datum = null;
    }

    /**
     * Test konstruktora sa svim parametrima ukljucujuci ID.
     */
    @Test
    public void testKonstruktorSaId() {
        assertEquals(1, sertifikat.getIdSertifikat());
        assertEquals(datum, sertifikat.getDatumIzdavanja());
        assertEquals("Beograd", sertifikat.getMestoIzdavanja());
        assertEquals(Nivo.osnovni, sertifikat.getNivo());
        assertEquals("Polaznik pokazao odličan napredak tokom kursa.", sertifikat.getNapomena());
        assertEquals(polaznik, sertifikat.getPolaznik());
        assertEquals(vrstaPlesa, sertifikat.getVrstaPlesa());
    }

    /**
     * Test konstruktora bez ID-a.
     */
    @Test
    public void testKonstruktorBezId() {
        Sertifikat s = new Sertifikat(datum, "Novi Sad", Nivo.srednji, "", polaznik, vrstaPlesa);
        assertEquals(datum, s.getDatumIzdavanja());
        assertEquals("Novi Sad", s.getMestoIzdavanja());
        assertEquals(Nivo.srednji, s.getNivo());
        assertEquals("", s.getNapomena());
        assertEquals(polaznik, s.getPolaznik());
        assertEquals(vrstaPlesa, s.getVrstaPlesa());
    }

    /**
     * Test podrazumevanog konstruktora.
     */
    @Test
    public void testPodrazumevaniKonstruktor() {
        Sertifikat s = new Sertifikat();
        assertNotNull(s);
    }
    
    /**
     * Test konstruktora sa null datumom.
     */
    @Test
    public void testKonstruktorNullDatum() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, null, "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa));
    }

    /**
     * Test konstruktora sa datumom u buducnosti.
     */
    @Test
    public void testKonstruktorDatumBuducnost() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, Date.valueOf("2099-01-01"), "Beograd", Nivo.osnovni, "Napomena", polaznik, vrstaPlesa));
    }

    /**
     * Test konstruktora sa null mestom izdavanja.
     */
    @Test
    public void testKonstruktorNullMesto() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, datum, null, Nivo.osnovni, "Napomena", polaznik, vrstaPlesa));
    }

    /**
     * Test konstruktora sa null nivoom.
     */
    @Test
    public void testKonstruktorNullNivo() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, datum, "Beograd", null, "Napomena", polaznik, vrstaPlesa));
    }

    /**
     * Test konstruktora sa null polaznikom.
     */
    @Test
    public void testKonstruktorNullPolaznik() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, datum, "Beograd", Nivo.osnovni, "Napomena", null, vrstaPlesa));
    }

    /**
     * Test konstruktora sa null vrstom plesa.
     */
    @Test
    public void testKonstruktorNullVrstaPlesa() {
        assertThrows(IllegalArgumentException.class, () -> new Sertifikat(1, datum, "Beograd", Nivo.osnovni, "Napomena", polaznik, null));
    }

    /**
     * Test setIdSertifikat sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"1", "5", "100"})
    public void testSetIdSertifikat(int id) {
        sertifikat.setIdSertifikat(id);
        assertEquals(id, sertifikat.getIdSertifikat());
    }

    /**
     * Test setDatumIzdavanja sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"2020-01-01", "2021-06-15", "2023-05-10"})
    public void testSetDatumIzdavanja(String datumString) {
        Date noviDatum = Date.valueOf(datumString);
        sertifikat.setDatumIzdavanja(noviDatum);
        assertEquals(noviDatum, sertifikat.getDatumIzdavanja());
    }

    /**
     * Test setDatumIzdavanja sa null vrednoscu.
     */
    @Test
    public void testSetDatumIzdavanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setDatumIzdavanja(null));
    }

    /**
     * Test setDatumIzdavanja sa datumom u buducnosti.
     */
    @Test
    public void testSetDatumIzdavanjaBuducnost() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setDatumIzdavanja(Date.valueOf("2099-01-01")));
    }
    
    /**
     * Test setMestoIzdavanja sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @CsvSource({"Beograd", "Novi Sad", "Nis"})
    public void testSetMestoIzdavanja(String mesto) {
        sertifikat.setMestoIzdavanja(mesto);
        assertEquals(mesto, sertifikat.getMestoIzdavanja());
    }

    /**
     * Test setMestoIzdavanja sa null vrednoscu.
     */
    @Test
    public void testSetMestoIzdavanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setMestoIzdavanja(null));
    }

    /**
     * Test setMestoIzdavanja sa praznim stringom.
     */
    @Test
    public void testSetMestoIzdavanjaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setMestoIzdavanja(""));
    }

    /**
     * Test setMestoIzdavanja sa stringom koji sadrzi samo razmake.
     */
    @Test
    public void testSetMestoIzdavanjaSamoRazmaci() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setMestoIzdavanja("   "));
    }
    
    /**
     * Test setNivo sa razlicitim vrednostima.
     */
    @ParameterizedTest
    @EnumSource(Nivo.class)
    public void testSetNivo(Nivo nivo) {
        sertifikat.setNivo(nivo);
        assertEquals(nivo, sertifikat.getNivo());
    }

    /**
     * Test setNivo sa null vrednoscu.
     */
    @Test
    public void testSetNivoNull() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setNivo(null));
    }
    
    /**
     * Test setNapomena.
     */
    @Test
    public void testSetNapomena() {
        sertifikat.setNapomena("Nova napomena");
        assertEquals("Nova napomena", sertifikat.getNapomena());
    }

    /**
     * Test setPolaznik.
     */
    @Test
    public void testSetPolaznik() {
        UzrasnaKategorija uk = new UzrasnaKategorija(2, "13-18", "juniori");
        Polaznik noviPolaznik = new Polaznik(2, "Ana", "Anić", "ana@gmail.com", uk);
        sertifikat.setPolaznik(noviPolaznik);
        assertEquals(noviPolaznik, sertifikat.getPolaznik());
    }
    
    /**
     * Test setPolaznik sa null vrednoscu.
     */
    @Test
    public void testSetPolaznikNull() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setPolaznik(null));
    }

    /**
     * Test setVrstaPlesa.
     */
    @Test
    public void testSetVrstaPlesa() {
        VrstaPlesa novaVrsta = new VrstaPlesa(2, "Valcer", "Standardni", 1200.0);
        sertifikat.setVrstaPlesa(novaVrsta);
        assertEquals(novaVrsta, sertifikat.getVrstaPlesa());
    }
    
    /**
     * Test setVrstaPlesa sa null vrednoscu.
     */
    @Test
    public void testSetVrstaPlesaNull() {
        assertThrows(IllegalArgumentException.class, () -> sertifikat.setVrstaPlesa(null));
    }

    /**
     * Test equals - isti polaznik, vrsta plesa i nivo => jednaki.
     */
    @Test
    public void testEqualsIsti() {
        Sertifikat s2 = new Sertifikat(2, datum, "Novi Sad", Nivo.osnovni, "Polaznik uspešno položio završni ispit osnovnog nivoa.", polaznik, vrstaPlesa);
        assertEquals(sertifikat, s2);
    }

    /**
     * Test equals - razlicit nivo => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitNivo() {
        Sertifikat s2 = new Sertifikat(1, datum, "Beograd", Nivo.napredni, "Polaznik pokazao odličan napredak tokom kursa.", polaznik, vrstaPlesa);
        assertNotEquals(sertifikat, s2);
    }

    /**
     * Test equals - razlicit polaznik => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitPolaznik() {
        UzrasnaKategorija uk = new UzrasnaKategorija(2, "13-18", "juniori");
        Polaznik drugiPolaznik = new Polaznik(2, "Ana", "Anić", "ana@gmail.com", uk);
        Sertifikat s2 = new Sertifikat(1, datum, "Beograd", Nivo.osnovni, "Polaznik pokazao odličan napredak tokom kursa.", drugiPolaznik, vrstaPlesa);
        assertNotEquals(sertifikat, s2);
    }
    
    /**
     * Test equals - razlicita vrsta plesa => nisu jednaki.
     */
    @Test
    public void testEqualsRazlicitaVrstaPlesa() {
        VrstaPlesa drugarVrsta = new VrstaPlesa(2, "Valcer", "Standardni", 1200.0);
        Sertifikat s2 = new Sertifikat(1, datum, "Beograd", Nivo.osnovni, "Polaznik pokazao odlican napredak.", polaznik, drugarVrsta);
        assertNotEquals(sertifikat, s2);
    }

    /**
     * Test equals - poredenje sa null.
     */
    @Test
    public void testEqualsNull() {
        assertNotEquals(null, sertifikat);
    }

    /**
     * Test equals - isti objekat.
     */
    @Test
    public void testEqualsSamSaSobom() {
        assertEquals(sertifikat, sertifikat);
    }

    /**
     * Test equals - poredenje sa objektom drugog tipa.
     */
    @Test
    public void testEqualsDrugiTip() {
        assertNotEquals("string", sertifikat);
    }
    
    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        String rezultat = sertifikat.toString();
        assertTrue(rezultat.contains("Mila Milić"));
        assertTrue(rezultat.contains("Tango - Latinoamerički"));
        assertTrue(rezultat.contains("osnovni"));
        assertTrue(rezultat.contains("Beograd"));
    }

    /**
     * Test vratiImeTabele.
     */
    @Test
    public void testVratiImeTabele() {
        assertEquals("sertifikat", sertifikat.vratiImeTabele());
    }

    /**
     * Test vratiKoloneUbaci.
     */
    @Test
    public void testVratiKoloneUbaci() {
        assertEquals("datumIzdavanja, mestoIzdavanja, nivo, napomena, idPolaznik, idVrstaPlesa",
                sertifikat.vratiKoloneUbaci());
    }

    /**
     * Test vratiVrednostiUbaci.
     */
    @Test
    public void testVratiVrednostiUbaci() {
        String ocekivano = "'" + datum + "', 'Beograd', 'osnovni', 'Polaznik pokazao odličan napredak tokom kursa.', 1, 1";
        assertEquals(ocekivano, sertifikat.vratiVrednostiUbaci());
    }

    /**
     * Test vratiKljucZaWhere.
     */
    @Test
    public void testVratiKljucZaWhere() {
        assertEquals("sertifikat.idSertifikat = 1", sertifikat.vratiKljucZaWhere());
    }

    /**
     * Test vratiVrednostiIzmeni.
     */
    @Test
    public void testVratiVrednostiIzmeni() {
        String ocekivano = "datumIzdavanja = '" + datum + "', mestoIzdavanja = 'Beograd', nivo = 'osnovni', napomena = 'Polaznik pokazao odličan napredak tokom kursa.', idPolaznik = 1, idVrstaPlesa = 1";
        assertEquals(ocekivano, sertifikat.vratiVrednostiIzmeni());
    }
}
