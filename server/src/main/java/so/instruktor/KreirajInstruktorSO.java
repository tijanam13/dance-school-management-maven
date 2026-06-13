/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Instruktor;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class KreirajInstruktorSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM instruktor WHERE ime = '" + ((Instruktor) parametar).getIme() + "' AND prezime = '" + ((Instruktor) parametar).getPrezime() + "' AND korisnickoIme = '" + ((Instruktor) parametar).getKorisnickoIme() + "' AND sifra = '" + ((Instruktor) parametar).getSifra() + "' AND email = '" + ((Instruktor) parametar).getEmail() + "'";
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
            broker.dodaj((Instruktor) parametar);

            uspesno = true;
        }

    }
}
