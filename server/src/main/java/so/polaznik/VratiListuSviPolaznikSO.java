/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import java.util.List;
import model.Polaznik;
import so.OpstaSO;
import servis.JsonServis;

/**
 *
 * @author Tijana
 */
public class VratiListuSviPolaznikSO extends OpstaSO {

    private List<Polaznik> lista;

    public List<Polaznik> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit = " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast";
        List<Polaznik> lista = broker.vratiSve((Polaznik) parametar, upit);
        this.lista = lista;
        
        JsonServis jsonServis = new JsonServis();
        jsonServis.serijalizujPolaznike(lista, "polaznici.json");
    }

}
