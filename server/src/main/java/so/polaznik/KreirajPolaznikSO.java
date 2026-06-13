/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import model.Polaznik;
import so.OpstaSO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author Tijana
 */
public class KreirajPolaznikSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM polaznik p JOIN uzrasna_kategorija u ON p.idUzrast = u.idUzrast WHERE p.ime='" + ((Polaznik) parametar).getIme() + "' AND p.prezime='" + ((Polaznik) parametar).getPrezime() + "' AND p.email='" + ((Polaznik) parametar).getEmail() + "' AND u.opsegGodina='" + ((Polaznik) parametar).getUzrasnaKategorija().getOpsegGodina() + "' AND u.naziv='" + ((Polaznik) parametar).getUzrasnaKategorija().getNaziv() + "'";
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }

    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.dodaj((Polaznik) parametar);
            uspesno = true;
        }
    }

}
