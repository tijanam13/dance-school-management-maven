/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.util.List;
import model.Instruktor;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuInstruktorInstruktorSO extends OpstaSO {

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

        String[] imePrezime = ((String) uslov).strip().split(" ");
        String upit = "";
        if (imePrezime.length == 2) {
            upit += " WHERE (ime='" + imePrezime[0] + "' AND prezime='" + imePrezime[1] + "') OR (ime='" + imePrezime[1] + "' AND prezime='" + imePrezime[0] + "') ORDER BY idInstruktor";
        } else {
            upit += " WHERE ime='" + imePrezime[0] + "' OR prezime='" + imePrezime[0] + "' ORDER BY idInstruktor";
        }

        List<Instruktor> lista = broker.vratiSve((Instruktor) parametar, upit);
        this.lista = lista;

    }
}
