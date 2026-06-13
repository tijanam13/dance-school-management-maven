/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import java.sql.ResultSet;
import model.Instruktor;
import model.InstruktorKvalifikacija;
import so.OpstaSO;
import java.sql.Statement;
import model.Kvalifikacija;
import model.Nivo;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author Tijana
 */
public class PromeniInstruktorSO extends OpstaSO {

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

        int brKval = ((Instruktor) parametar).getInstruktorKvalifikacije().size();

        int brIstih = 0;
        String upit = "SELECT * FROM instruktor JOIN instruktor_kvalifikacija ON instruktor.idInstruktor = instruktor_kvalifikacija.idInstruktor JOIN kvalifikacija ON kvalifikacija.idKvalifikacija= instruktor_kvalifikacija.idKvalifikacija WHERE instruktor.ime = '" + ((Instruktor) parametar).getIme() + "' AND instruktor.prezime = '" + ((Instruktor) parametar).getPrezime() + "' AND instruktor.email = '" + ((Instruktor) parametar).getEmail() + "' AND instruktor.korisnickoIme = '" + ((Instruktor) parametar).getKorisnickoIme() + "' AND instruktor.sifra = '" + ((Instruktor) parametar).getSifra() + "' ORDER BY instruktor.idInstruktor";
        System.out.println(upit);
        Statement s = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            System.out.println("hii");
            String tip = rs.getString("kvalifikacija.tip");
            String organizacija = rs.getString("kvalifikacija.organizacija");
            Kvalifikacija k = new Kvalifikacija(tip, organizacija);
            Nivo nivo = Nivo.valueOf(rs.getString("nivo"));

            InstruktorKvalifikacija ik = new InstruktorKvalifikacija(((Instruktor) parametar), k, null, nivo);
            System.out.println(ik);
            System.out.println(((Instruktor) parametar).getInstruktorKvalifikacije());
            if (((Instruktor) parametar).getInstruktorKvalifikacije().contains(ik)) {
                brIstih++;
            }
        }
        System.out.println("istii" + brIstih);
        System.out.println("bruk" + brKval);
        if (brIstih == brKval) {
            postoji = true;

        }

    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        if (!postoji) {
            broker.izmeni((Instruktor) parametar);
            String upit = "DELETE FROM instruktor_kvalifikacija WHERE idInstruktor=" + ((Instruktor) parametar).getIdInstruktor();
            Statement s = DBKonekcija.getInstance().getConnection().createStatement();
            s.executeUpdate(upit);
            s.close();

            for (InstruktorKvalifikacija ik : ((Instruktor) parametar).getInstruktorKvalifikacije()) {
                ik.setInstruktor((Instruktor) parametar);
                broker.dodaj(ik);
            }

            uspesno = true;

        }
    }
}
