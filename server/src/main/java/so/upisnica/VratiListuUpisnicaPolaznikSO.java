/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.upisnica;

import java.util.List;
import model.StavkaUpisnice;
import model.Upisnica;
import so.OpstaSO;

/**
 *
 * @author Tijana
 */
public class VratiListuUpisnicaPolaznikSO extends OpstaSO {

    private List<Upisnica> lista;

    public List<Upisnica> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object parametar) throws Exception {

        if (parametar == null || !(parametar instanceof Upisnica)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    @Override
    protected void izvrsi(Object parametar, Object uslov) throws Exception {

        String[] imePrezime = ((String) uslov).strip().split(" ");
        String upit1 = " JOIN instruktor ON instruktor.idInstruktor = upisnica.idInstruktor JOIN polaznik ON polaznik.idPolaznik=upisnica.idPolaznik JOIN uzrasna_kategorija ON uzrasna_kategorija.idUzrast=polaznik.idUzrast JOIN stavka_upisnice ON stavka_upisnice.idUpisnica=upisnica.idUpisnica JOIN vrsta_plesa ON vrsta_plesa.idVrstaPlesa=stavka_upisnice.idVrstaPlesa";
        if (imePrezime.length == 2) {
            upit1 += " WHERE (polaznik.ime='" + imePrezime[0] + "' AND polaznik.prezime='" + imePrezime[1] + "') OR (polaznik.ime='" + imePrezime[1] + "' AND polaznik.prezime='" + imePrezime[0] + "') ";
        } else {
            upit1 += " WHERE polaznik.ime='" + imePrezime[0] + "' OR polaznik.prezime='" + imePrezime[0] + "'";
        }

        List<Upisnica> lista = broker.vratiSve((Upisnica) parametar, upit1);
        for (Upisnica upisnica : lista) {
            String upit2 = " JOIN vrsta_plesa ON vrsta_plesa.idVrstaPlesa=stavka_upisnice.idVrstaPlesa WHERE idUpisnica=" + upisnica.getIdUpisnica();
            List<StavkaUpisnice> stavke = broker.vratiSve(new StavkaUpisnice(), upit2);
            upisnica.setListaStavke(stavke);
        }

        this.lista = lista;
    }

}
