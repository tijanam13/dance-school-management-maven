/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.uzrasnaKategorija;

import model.UzrasnaKategorija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PretraziUzrasnaKategorijaSO extends OpstaSO {

    private UzrasnaKategorija uzrasnaKategorija;

    public UzrasnaKategorija getUzrasnaKategorija() {
        return uzrasnaKategorija;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof UzrasnaKategorija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit = " WHERE idUzrast = " + ((UzrasnaKategorija) parametar).getIdUzrast();
        UzrasnaKategorija uzrasnaKategorija = (UzrasnaKategorija) broker.vratiObjekat((UzrasnaKategorija) parametar, upit);
        this.uzrasnaKategorija = uzrasnaKategorija;
    }
}
