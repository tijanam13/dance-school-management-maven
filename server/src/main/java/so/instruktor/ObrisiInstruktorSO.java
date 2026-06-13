/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import model.Instruktor;
import model.InstruktorKvalifikacija;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class ObrisiInstruktorSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {
            String upit = "SELECT * FROM upisnica WHERE idInstruktor = " + ((Instruktor) parametar).getIdInstruktor();
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
            for (InstruktorKvalifikacija ik : ((Instruktor) parametar).getInstruktorKvalifikacije()) {
                broker.obrisi(ik);
            }
            broker.obrisi((Instruktor) parametar);
            uspesno = true;
        }

    }
}
