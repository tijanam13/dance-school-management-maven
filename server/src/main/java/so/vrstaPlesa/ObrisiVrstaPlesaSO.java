/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vrstaPlesa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.VrstaPlesa;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class ObrisiVrstaPlesaSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM vrsta_plesa vp JOIN stavka_upisnice su ON su.idVrstaPlesa = vp.idVrstaPlesa WHERE vp.idVrstaPlesa = " + ((VrstaPlesa) parametar).getIdVrstaPlesa();
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
            broker.obrisi((VrstaPlesa) parametar);
            uspesno = true;
        }
    }
}
