/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.upisnica;

import java.util.Date;
import model.Upisnica;
import so.OpstaSO;
import model.StavkaUpisnice;
import repozitorijum.db.DBKonekcija;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tijana
 */
public class PromeniUpisnicaSO extends OpstaSO {

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
            broker.izmeni((Upisnica) parametar);
            String upit = "DELETE FROM stavka_upisnice WHERE idUpisnica=" + ((Upisnica) parametar).getIdUpisnica();
            Statement s = DBKonekcija.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
            s.close();

            for (StavkaUpisnice stavka : ((Upisnica) parametar).getListaStavke()) {
                stavka.setUpisnica((Upisnica) parametar);
                broker.dodaj(stavka);
            }

            uspesno = true;

        }
    }
}
