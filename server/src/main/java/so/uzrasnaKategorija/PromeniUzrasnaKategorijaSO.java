/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.uzrasnaKategorija;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.UzrasnaKategorija;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class PromeniUzrasnaKategorijaSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof UzrasnaKategorija)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        try {

            String upit = "SELECT * FROM uzrasna_kategorija WHERE opsegGodina = '" + ((UzrasnaKategorija) parametar).getOpsegGodina() +"' AND naziv = '"+((UzrasnaKategorija)parametar).getNaziv()+"'";
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
            broker.izmeni((UzrasnaKategorija) parametar);
            uspesno = true;
        }
    }
}
