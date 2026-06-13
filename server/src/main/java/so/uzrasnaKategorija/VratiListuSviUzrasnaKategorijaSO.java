/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.uzrasnaKategorija;

import java.util.List;
import model.UzrasnaKategorija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuSviUzrasnaKategorijaSO extends OpstaSO {

    private List<UzrasnaKategorija> lista;

    public List<UzrasnaKategorija> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof UzrasnaKategorija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        List<UzrasnaKategorija> lista = broker.vratiSve((UzrasnaKategorija) parametar);
        this.lista = lista;
    }

}
