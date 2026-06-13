/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import java.util.List;
import model.Polaznik;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuPolaznikPolaznikSO extends OpstaSO {

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
        String[] imePrezime = ((String) uslov).strip().split(" ");
        if (imePrezime.length == 2) {
            upit += " WHERE (ime='" + imePrezime[0] + "' AND prezime='" + imePrezime[1] + "') OR (ime='" + imePrezime[1] + "' AND prezime='" + imePrezime[0] + "') ";
        } else {
            upit += " WHERE ime='" + imePrezime[0] + "' OR prezime='" + imePrezime[0] + "'";
        }

        List<Polaznik> lista = broker.vratiSve((Polaznik) parametar, upit);
        this.lista = lista;
    }
}
