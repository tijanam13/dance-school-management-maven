/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaPlesa;

import model.VrstaPlesa;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PretraziVrstaPlesaSO extends OpstaSO {

    private VrstaPlesa vrstaPlesa;

    public VrstaPlesa getVrstaPlesa() {
        return vrstaPlesa;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " WHERE idVrstaPlesa = " + ((VrstaPlesa) parametar).getIdVrstaPlesa();
        VrstaPlesa vrstaPlesa = (VrstaPlesa) broker.vratiObjekat((VrstaPlesa) parametar, upit);
        this.vrstaPlesa = vrstaPlesa;

    }
}
