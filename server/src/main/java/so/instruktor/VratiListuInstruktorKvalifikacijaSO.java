/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.util.List;
import model.Instruktor;
import model.Kvalifikacija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuInstruktorKvalifikacijaSO extends OpstaSO {

    private List<Instruktor> lista;

    public List<Instruktor> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " JOIN instruktor_kvalifikacija ON instruktor.idInstruktor = instruktor_kvalifikacija.idInstruktor JOIN kvalifikacija ON kvalifikacija.idKvalifikacija= instruktor_kvalifikacija.idKvalifikacija WHERE kvalifikacija.idKvalifikacija = " + ((Kvalifikacija) uslov).getIdKvalifikacija() + " ORDER BY instruktor.idInstruktor";
        List<Instruktor> listaSvihInstruktora = broker.vratiSve((Instruktor) parametar, upit);
        this.lista = listaSvihInstruktora;
    }
}
