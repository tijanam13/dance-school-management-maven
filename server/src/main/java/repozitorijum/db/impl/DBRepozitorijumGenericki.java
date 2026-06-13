/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db.impl;

import java.util.List;
import repozitorijum.db.DBKonekcija;
import repozitorijum.db.DBRepozitorijum;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author Tijana
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat parametar) throws Exception {
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();

            String upit = "SELECT * FROM " + parametar.vratiImeTabele();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            lista = parametar.vratiListu(rs);
            rs.close();
            st.close();
            return lista;
        }
        return null;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat parametar, String uslov) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + parametar.vratiImeTabele();
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);

        lista = parametar.vratiListu(rs);
        rs.close();
        st.close();
        return lista;

    }

    @Override
    public void dodaj(OpstiDomenskiObjekat parametar) throws Exception {

        String upit = "INSERT INTO " + parametar.vratiImeTabele() + " (" + parametar.vratiKoloneUbaci() + ") VALUES (" + parametar.vratiVrednostiUbaci() + ")";
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public void izmeni(OpstiDomenskiObjekat parametar) throws Exception {

        String upit = "UPDATE " + parametar.vratiImeTabele() + " SET " + parametar.vratiVrednostiIzmeni() + " WHERE " + parametar.vratiKljucZaWhere();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public void obrisi(OpstiDomenskiObjekat parametar) throws Exception {

        String upit = "DELETE FROM " + parametar.vratiImeTabele() + " WHERE " + parametar.vratiKljucZaWhere();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.execute(upit);
        st.close();

    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(OpstiDomenskiObjekat parametar) throws Exception {

        OpstiDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + parametar.vratiImeTabele();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = parametar.vratiObjekat(rs);
        rs.close();
        st.close();
        return objekat;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(OpstiDomenskiObjekat parametar, String uslov) throws Exception {

        OpstiDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + parametar.vratiImeTabele();
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = parametar.vratiObjekat(rs);
        rs.close();
        st.close();
        return objekat;

    }

}
