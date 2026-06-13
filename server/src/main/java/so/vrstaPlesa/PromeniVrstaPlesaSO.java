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
public class PromeniVrstaPlesaSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof VrstaPlesa)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {

            String upit = "SELECT * FROM vrsta_plesa WHERE naziv='" + ((VrstaPlesa) parametar).getNaziv() + "' AND kategorija='" + ((VrstaPlesa) parametar).getKategorija() + "' AND cenaCasa=" + ((VrstaPlesa) parametar).getCenaCasa();
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
            broker.izmeni((VrstaPlesa) parametar);
            uspesno = true;
        }
    }
}
