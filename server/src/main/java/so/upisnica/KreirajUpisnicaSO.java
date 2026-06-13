/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.upisnica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.StavkaUpisnice;
import model.Upisnica;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class KreirajUpisnicaSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Upisnica)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }

        int brStavki = ((Upisnica) parametar).getListaStavke().size();
        int brIstih = 0;

        try {
            Date datum = new java.sql.Date(((Upisnica) parametar).getDatumUpisa().getTime());

            String upit = "SELECT * FROM vrsta_plesa vp JOIN stavka_upisnice su ON vp.idVrstaPlesa=su.idVrstaPlesa JOIN upisnica u ON u.idUpisnica=su.idUpisnica JOIN instruktor i ON u.idInstruktor=i.idInstruktor JOIN polaznik p ON p.idPolaznik=u.idPolaznik WHERE u.datumUpisa='" + datum + "' AND u.ukupnaClanarina=" + ((Upisnica) parametar).getUkupnaClanarina() + " AND i.idInstruktor=" + ((Upisnica) parametar).getInstruktor().getIdInstruktor() + " AND p.idPolaznik=" + ((Upisnica) parametar).getPolaznik().getIdPolaznik();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                String naziv = rs.getString("vp.naziv");
                String kategorija = rs.getString("vp.kategorija");
                int brCasova = rs.getInt("su.brCasova");
                double cena = Double.parseDouble(rs.getString("su.cena"));
                double clanarina = Double.parseDouble(rs.getString("su.clanarina"));

                for (StavkaUpisnice stavka : ((Upisnica) parametar).getListaStavke()) {
                    if (naziv.equals(stavka.getVrstaPlesa().getNaziv())
                            && kategorija.equals(stavka.getVrstaPlesa().getKategorija())
                            && cena == stavka.getCena()
                            && brCasova == stavka.getBrCasova()
                            && clanarina == stavka.getClanarina()) {

                        brIstih++;
                    }
                }
            }
            if (brIstih == brStavki) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        if (!postoji) {
            broker.dodaj((Upisnica) parametar);
            int ID = -1;
            try {
                Date datum = new java.sql.Date(((Upisnica) parametar).getDatumUpisa().getTime());
                String upit = "SELECT * FROM upisnica WHERE idInstruktor = " + ((Upisnica) parametar).getInstruktor().getIdInstruktor() + " AND idPolaznik = " + ((Upisnica) parametar).getPolaznik().getIdPolaznik() + " AND ukupnaClanarina = " + ((Upisnica) parametar).getUkupnaClanarina() + " AND datumUpisa = '" + datum + "'";
                Statement st = DBKonekcija.getInstance().getConnection().createStatement();
                ResultSet rs = st.executeQuery(upit);

                while (rs.next()) {
                    ID = rs.getInt("idUpisnica");
                }
            } catch (SQLException ex) {

                throw ex;

            }

            ((Upisnica) parametar).setIdUpisnica(ID);

            for (StavkaUpisnice stavka : ((Upisnica) parametar).getListaStavke()) {
                stavka.setUpisnica((Upisnica) parametar);
                broker.dodaj(stavka);

            }

            uspesno = true;
        }
    }

}
