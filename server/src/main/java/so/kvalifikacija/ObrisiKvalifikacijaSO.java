/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kvalifikacija;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Kvalifikacija;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class ObrisiKvalifikacijaSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM instruktor_kvalifikacija ik JOIN kvalifikacija k ON k.idKvalifikacija = ik.idKvalifikacija WHERE k.idKvalifikacija = " + ((Kvalifikacija) parametar).getIdKvalifikacija();
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
            broker.obrisi((Kvalifikacija) parametar);
            uspesno = true;
        }
    }
}
