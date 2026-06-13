/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaPlesa;

import java.util.List;
import model.VrstaPlesa;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuVrstaPlesaVrstaPlesaSO extends OpstaSO {

    private List<VrstaPlesa> lista;

    public List<VrstaPlesa> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = "";
        if (((VrstaPlesa) uslov).getKategorija() == null && ((VrstaPlesa) uslov).getNaziv() != null) {

            upit += " WHERE vrsta_plesa.naziv = '" + ((VrstaPlesa) uslov).getNaziv() + "'";

        } else if (((VrstaPlesa) uslov).getKategorija() != null && ((VrstaPlesa) uslov).getNaziv() == null) {
            upit += " WHERE vrsta_plesa.kategorija = '" + ((VrstaPlesa) uslov).getKategorija() + "'";

        } else if (((VrstaPlesa) uslov).getKategorija() != null && ((VrstaPlesa) uslov).getNaziv() != null) {

            upit += " WHERE vrsta_plesa.naziv= '" + ((VrstaPlesa) uslov).getNaziv() + "' AND vrsta_plesa.kategorija  = '" + ((VrstaPlesa) uslov).getKategorija() + "'";
        }

        List<VrstaPlesa> lista = broker.vratiSve((VrstaPlesa) parametar, upit);
        this.lista = lista;
    }
}
