package so.instruktor;

import java.sql.ResultSet;
import java.sql.Statement;
import model.Instruktor;
import model.InstruktorKvalifikacija;
import model.Kvalifikacija;
import model.Nivo;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

/**
 * Sistemska operacija za izmenu podataka o instruktoru.
 * Pre izmene proverava da li instruktor sa istim podacima i kvalifikacijama
 * vec postoji u bazi podataka kako bi se izbeglo dupliranje.
 * Prilikom izmene azurira podatke instruktora i njegovih kvalifikacija.
 *
 * @author Tijana
 * @version 1.0
 * @see Instruktor
 * @see InstruktorKvalifikacija
 * @see OpstaSO
 */
public class PromeniInstruktorSO extends OpstaSO {

    /** Indikator uspesnosti izmene instruktora. */
    private boolean uspesno = false;

    /** Indikator da li instruktor sa istim podacima vec postoji u bazi podataka. */
    private boolean postoji = false;

    /**
     * Vraca indikator uspesnosti izmene instruktora.
     *
     * @return true ako je instruktor uspesno izmenjen, false inace
     */
    public boolean getUspesno() {
        return uspesno;
    }

    /**
     * Proverava preduslove pre izmene instruktora.
     * Proverava da li je prosleden parametar odgovarajuceg tipa i
     * da li instruktor sa istim podacima i kvalifikacijama vec postoji u bazi.
     * Poredi broj kvalifikacija instruktora sa brojem istih kvalifikacija u bazi.
     *
     * @param parametar objekat tipa {@link Instruktor} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa ili dodje do greske pri radu sa bazom
     */
    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Instruktor)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        int brKval = ((Instruktor) parametar).getInstruktorKvalifikacije().size();
        int brIstih = 0;
        String upit = "SELECT * FROM instruktor JOIN instruktor_kvalifikacija ON instruktor.idInstruktor = instruktor_kvalifikacija.idInstruktor"
                + " JOIN kvalifikacija ON kvalifikacija.idKvalifikacija= instruktor_kvalifikacija.idKvalifikacija"
                + " WHERE instruktor.ime = '" + ((Instruktor) parametar).getIme()
                + "' AND instruktor.prezime = '" + ((Instruktor) parametar).getPrezime()
                + "' AND instruktor.email = '" + ((Instruktor) parametar).getEmail()
                + "' AND instruktor.korisnickoIme = '" + ((Instruktor) parametar).getKorisnickoIme()
                + "' AND instruktor.sifra = '" + ((Instruktor) parametar).getSifra()
                + "' ORDER BY instruktor.idInstruktor";
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

    /**
     * Izvrsava izmenu podataka o instruktoru u bazi podataka.
     * Azurira osnovne podatke instruktora, brise sve postojece kvalifikacije
     * i dodaje nove kvalifikacije prosledjene kroz parametar.
     *
     * @param parametar objekat tipa {@link Instruktor} sa novim podacima
     * @param uslov uslov koji se koristi pri izvrsavanju operacije
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
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