package so.sertifikat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Sertifikat;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

public class PromeniSertifikatSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean postoji = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM sertifikat WHERE idPolaznik = "
                    + ((Sertifikat) parametar).getPolaznik().getIdPolaznik()
                    + " AND idVrstaPlesa = " + ((Sertifikat) parametar).getVrstaPlesa().getIdVrstaPlesa()
                    + " AND nivo = '" + ((Sertifikat) parametar).getNivo() + "'"
                    + " AND idSertifikat != " + ((Sertifikat) parametar).getIdSertifikat();
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
            broker.izmeni((Sertifikat) parametar);
            uspesno = true;
        }
    }
}