package so.sertifikat;

import java.util.List;
import model.Sertifikat;
import so.OpstaSO;

public class VratiListuSviSertifikatSO extends OpstaSO {

    private List<Sertifikat> lista;

    public List<Sertifikat> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {
        if (parametar == null || !(parametar instanceof Sertifikat)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {
        String upit = " JOIN polaznik ON sertifikat.idPolaznik = polaznik.idPolaznik"
                + " JOIN vrsta_plesa ON sertifikat.idVrstaPlesa = vrsta_plesa.idVrstaPlesa"
                + " JOIN uzrasna_kategorija ON polaznik.idUzrast = uzrasna_kategorija.idUzrast";
        lista = broker.vratiSve((Sertifikat) parametar, upit);
    }
}