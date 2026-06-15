/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.util.List;
import model.Instruktor;
import so.OpstaSO;
import servis.JsonServis;

/**
 *
 * @author Tijana
 */
public class VratiListuSviInstruktorSO extends OpstaSO {

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
        List<Instruktor> lista = broker.vratiSve((Instruktor) parametar);
        this.lista = lista;
        
        JsonServis jsonServis = new JsonServis();
        jsonServis.serijalizujInstruktore(lista, "instruktori.json");
    }
}
