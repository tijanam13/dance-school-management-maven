package so.sertifikat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Sertifikat;
import repozitorijum.db.DBKonekcija;
import so.OpstaSO;

public class ObrisiSertifikatSO extends OpstaSO {

    private boolean uspesno = false;
    private boolean uUpotrebi = false;

    public boolean getUspesno() {
        return uspesno;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        try {
            String upit = "SELECT * FROM sertifikat WHERE idSertifikat = "
                    + ((Sertifikat) parametar).getIdSertifikat();
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
        if (uUpotrebi) {
            broker.obrisi((Sertifikat) parametar);
            uspesno = true;
        }
    }
}