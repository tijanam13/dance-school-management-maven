/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Polaznik;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class ObrisiPolaznikSO extends OpstaSO {

    private boolean uspesno;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Polaznik)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM upisnica WHERE idPolaznik = " + ((Polaznik) parametar).getIdPolaznik();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                uUpotrebi = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!uUpotrebi) {
            broker.obrisi((Polaznik) parametar);
            uspesno = true;
        }
    }

}
