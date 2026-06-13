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
public class VratiListuPolaznikUzrasnaKategorijaSO extends OpstaSO {

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

        String[] godNaziv = ((String) uslov).strip().split(" ");
        String upit = " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast";
        if (godNaziv.length == 2) {
            upit += " WHERE (uzrasna_kategorija.opsegGodina='" + godNaziv[0] + "' AND uzrasna_kategorija.naziv='" + godNaziv[1] + "') OR (uzrasna_kategorija.opsegGodina='" + godNaziv[1] + "' AND uzrasna_kategorija.naziv='" + godNaziv[0] + "') ";
        } else {
            upit += " WHERE uzrasna_kategorija.opsegGodina='" + godNaziv[0] + "' OR uzrasna_kategorija.naziv='" + godNaziv[0] + "'";
        }

        List<Polaznik> lista = broker.vratiSve((Polaznik) parametar, upit);
        this.lista = lista;
    }
}
