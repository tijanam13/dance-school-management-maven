/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kvalifikacija;

import java.util.List;
import model.Kvalifikacija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuKvalifikacijaKvalifikacijaSO extends OpstaSO {

    private List<Kvalifikacija> lista;

    public List<Kvalifikacija> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit = "";
        if (((Kvalifikacija) uslov).getTip() == null && ((Kvalifikacija) uslov).getOrganizacija() != null) {

            upit += " WHERE kvalifikacija.organizacija = '" + ((Kvalifikacija) uslov).getOrganizacija() + "'";

        } else if (((Kvalifikacija) uslov).getTip() != null && ((Kvalifikacija) uslov).getOrganizacija() == null) {
            upit += " WHERE kvalifikacija.tip = '" + ((Kvalifikacija) uslov).getTip() + "'";

        } else if (((Kvalifikacija) uslov).getTip() != null && ((Kvalifikacija) uslov).getOrganizacija() != null) {

            upit += " WHERE kvalifikacija.tip = '" + ((Kvalifikacija) uslov).getTip() + "' AND kvalifikacija.organizacija = '" + ((Kvalifikacija) uslov).getOrganizacija() + "'";
        }
        List<Kvalifikacija> lista = broker.vratiSve((Kvalifikacija) parametar, upit);
        this.lista = lista;
    }
}
