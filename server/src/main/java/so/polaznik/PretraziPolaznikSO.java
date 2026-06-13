/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import model.Polaznik;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PretraziPolaznikSO extends OpstaSO {

    private Polaznik polaznik;

    public Polaznik getPolaznik() {
        return polaznik;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String upit = " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast WHERE polaznik.idPolaznik = " + ((Polaznik) parametar).getIdPolaznik();
        Polaznik polaznik = (Polaznik) broker.vratiObjekat((Polaznik) parametar, upit);
        this.polaznik = polaznik;
    }
}
