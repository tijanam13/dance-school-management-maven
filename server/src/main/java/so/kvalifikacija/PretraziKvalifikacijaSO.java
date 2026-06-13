/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kvalifikacija;

import model.Kvalifikacija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PretraziKvalifikacijaSO extends OpstaSO {

    private Kvalifikacija kvalifikacija;

    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit = " WHERE idKvalifikacija = " + ((Kvalifikacija) parametar).getIdKvalifikacija();
        Kvalifikacija kvalifikacija = (Kvalifikacija) broker.vratiObjekat((Kvalifikacija) parametar, upit);
        this.kvalifikacija = kvalifikacija;
    }
}
