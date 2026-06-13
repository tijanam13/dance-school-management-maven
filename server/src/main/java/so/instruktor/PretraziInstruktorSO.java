/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.util.List;
import model.Instruktor;
import model.InstruktorKvalifikacija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PretraziInstruktorSO extends OpstaSO {

    private Instruktor instruktor;

    public Instruktor getInstruktor() {
        return instruktor;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit1 = " WHERE idInstruktor = " + ((Instruktor) parametar).getIdInstruktor();
        Instruktor instruktor = (Instruktor) broker.vratiObjekat(parametar, upit1);

        if (instruktor != null) {
            String upit2 = " JOIN instruktor ON instruktor.idInstruktor = instruktor_kvalifikacija.idInstruktor JOIN kvalifikacija ON kvalifikacija.idKvalifikacija = instruktor_kvalifikacija.idKvalifikacija WHERE instruktor.idInstruktor = " + ((Instruktor) parametar).getIdInstruktor();
            List<InstruktorKvalifikacija> instruktorKvalifikacije = (List<InstruktorKvalifikacija>) broker.vratiSve(new InstruktorKvalifikacija(), upit2);
            instruktor.setInstruktorKvalifikacije(instruktorKvalifikacije);
        }
        this.instruktor = instruktor;
    }

}
