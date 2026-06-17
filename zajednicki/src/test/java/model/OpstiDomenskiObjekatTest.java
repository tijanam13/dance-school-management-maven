package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public abstract class OpstiDomenskiObjekatTest {

    protected abstract OpstiDomenskiObjekat getInstance();

    @Test
    @DisplayName("vratiImeTabele: ne sme biti null niti prazan string")
    public void testVratiImeTabeleNijeNull() {
        OpstiDomenskiObjekat obj = getInstance();
        assertNotNull(obj.vratiImeTabele());
        assertFalse(obj.vratiImeTabele().isEmpty());
    }

    @Test
    @DisplayName("vratiKoloneUbaci: ne sme biti null niti prazan string")
    public void testVratiKoloneUbaciNijeNull() {
        OpstiDomenskiObjekat obj = getInstance();
        assertNotNull(obj.vratiKoloneUbaci());
        assertFalse(obj.vratiKoloneUbaci().isEmpty());
    }

    @Test
    @DisplayName("vratiVrednostiUbaci: ne sme biti null niti prazan string")
    public void testVratiVrednostiUbaciNijeNull() {
        OpstiDomenskiObjekat obj = getInstance();
        assertNotNull(obj.vratiVrednostiUbaci());
        assertFalse(obj.vratiVrednostiUbaci().isEmpty());
    }

    @Test
    @DisplayName("vratiVrednostiIzmeni: ne sme biti null niti prazan string")
    public void testVratiVrednostiIzmeniNijeNull() {
        OpstiDomenskiObjekat obj = getInstance();
        assertNotNull(obj.vratiVrednostiIzmeni());
        assertFalse(obj.vratiVrednostiIzmeni().isEmpty());
    }
}