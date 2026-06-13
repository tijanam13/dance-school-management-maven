/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import so.OpstaSO;
import java.util.ArrayList;
import java.util.List;
import model.Instruktor;

/**
 *
 * @author Tijana
 */
public class PrijaviInstruktorSO extends OpstaSO {

    private Instruktor instruktor = null;

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

        List<Instruktor> listaInstruktora = new ArrayList<>();

        listaInstruktora = broker.vratiSve((Instruktor) parametar);
        for (Instruktor i : listaInstruktora) {
            if (i.getKorisnickoIme().equals(((Instruktor) parametar).getKorisnickoIme()) && i.getSifra().equals(((Instruktor) parametar).getSifra())) {
                instruktor = i;
                return;
            }
        }
        instruktor = null;
    }
}
