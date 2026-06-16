/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.ServerskaForma;
import java.util.List;
import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import niti.ObradaKlijentskihZahteva;
import niti.ServerNit;
import so.instruktor.ObrisiInstruktorSO;
import so.instruktor.PromeniInstruktorSO;
import so.instruktor.VratiListuInstruktorInstruktorSO;
import so.instruktor.VratiListuInstruktorKvalifikacijaSO;
import so.instruktor.VratiListuSviInstruktorSO;
import so.instruktor.KreirajInstruktorSO;
import so.instruktor.PretraziInstruktorSO;
import so.instruktor.PrijaviInstruktorSO;
import so.kvalifikacija.ObrisiKvalifikacijaSO;
import so.kvalifikacija.PretraziKvalifikacijaSO;
import so.kvalifikacija.PromeniKvalifikacijaSO;
import so.kvalifikacija.VratiListuKvalifikacijaKvalifikacijaSO;
import so.kvalifikacija.VratiListuSviKvalifikacijaSO;
import so.kvalifikacija.UbaciKvalifikacijaSO;
import so.polaznik.ObrisiPolaznikSO;
import so.polaznik.PromeniPolaznikSO;
import so.polaznik.VratiListuPolaznikPolaznikSO;
import so.polaznik.VratiListuPolaznikUzrasnaKategorijaSO;
import so.polaznik.VratiListuSviPolaznikSO;
import so.polaznik.KreirajPolaznikSO;
import so.polaznik.PretraziPolaznikSO;
import so.upisnica.PromeniUpisnicaSO;
import so.upisnica.VratiListuUpisnicaInstruktorSO;
import so.upisnica.VratiListuUpisnicaPolaznikSO;
import so.upisnica.VratiListuUpisnicaUpisnicaSO;
import so.upisnica.VratiListuUpisnicaVrstaPlesaSO;
import so.upisnica.VratiListuSviUpisnicaSO;
import so.upisnica.KreirajUpisnicaSO;
import so.upisnica.PretraziUpisnicaSO;
import so.uzrasnaKategorija.ObrisiUzrasnaKategorijaSO;
import so.uzrasnaKategorija.PromeniUzrasnaKategorijaSO;
import so.uzrasnaKategorija.VratiListuUzrasnaKategorijaUzrasnaKategorijaSO;
import so.uzrasnaKategorija.VratiListuSviUzrasnaKategorijaSO;
import so.uzrasnaKategorija.KreirajUzrasnaKategorijaSO;
import so.uzrasnaKategorija.PretraziUzrasnaKategorijaSO;
import so.vrstaPlesa.ObrisiVrstaPlesaSO;
import so.vrstaPlesa.PromeniVrstaPlesaSO;
import so.vrstaPlesa.VratiListuVrstaPlesaVrstaPlesaSO;
import so.vrstaPlesa.VratiListuSviVrstaPlesaSO;
import so.vrstaPlesa.KreirajVrstaPlesaSO;
import so.vrstaPlesa.PretraziVrstaPlesaSO;
import model.Sertifikat;
import so.sertifikat.KreirajSertifikatSO;
import so.sertifikat.ObrisiSertifikatSO;
import so.sertifikat.PromeniSertifikatSO;
import so.sertifikat.PretraziSertifikatSO;
import so.sertifikat.VratiListuSviSertifikatSO;
import so.sertifikat.VratiListuSertifikatPolaznikSO;
import so.sertifikat.VratiListuSertifikatVrstaPlesaSO;

/**
 *
 * @author Tijana
 */
public class ServerKontroler {

    private static ServerKontroler instance;
    private Instruktor prijavljen = null;
    ServerskaForma sf = new ServerskaForma();

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public Instruktor getPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(Instruktor prijavljen) {
        this.prijavljen = prijavljen;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public List<Instruktor> vratiListuSviInstruktor() throws Exception {
        VratiListuSviInstruktorSO operacija = new VratiListuSviInstruktorSO();
        operacija.izvrsiOperaciju(new Instruktor(), null);
        return operacija.getLista();
    }

    public List<Polaznik> vratiListuSviPolaznik() throws Exception {
        VratiListuSviPolaznikSO operacija = new VratiListuSviPolaznikSO();
        operacija.izvrsiOperaciju(new Polaznik(), null);
        return operacija.getLista();
    }

    public List<VrstaPlesa> vratiListuSviVrstaPlesa() throws Exception {
        VratiListuSviVrstaPlesaSO operacija = new VratiListuSviVrstaPlesaSO();
        operacija.izvrsiOperaciju(new VrstaPlesa(), null);
        return operacija.getLista();
    }

    public boolean kreirajUpisnica(Upisnica upisnica) throws Exception {
        KreirajUpisnicaSO operacija = new KreirajUpisnicaSO();
        operacija.izvrsiOperaciju(upisnica, null);
        return operacija.getUspesno();

    }

    public List<Upisnica> vratiListuUpisnicaUpisnica(int ID) throws Exception {
        VratiListuUpisnicaUpisnicaSO operacija = new VratiListuUpisnicaUpisnicaSO();
        operacija.izvrsiOperaciju(new Upisnica(), ID);
        return operacija.getLista();
    }

    public List<Upisnica> vratiListuUpisnicaInstruktor(String instruktor) throws Exception {
        VratiListuUpisnicaInstruktorSO operacija = new VratiListuUpisnicaInstruktorSO();
        operacija.izvrsiOperaciju(new Upisnica(), instruktor);
        return operacija.getLista();
    }

    public List<Upisnica> vratiListuUpisnicaPolaznik(String polaznik) throws Exception {
        VratiListuUpisnicaPolaznikSO operacija = new VratiListuUpisnicaPolaznikSO();
        operacija.izvrsiOperaciju(new Upisnica(), polaznik);
        return operacija.getLista();
    }

    public boolean promeniUpisnica(Upisnica u) throws Exception {
        PromeniUpisnicaSO operacija = new PromeniUpisnicaSO();
        operacija.izvrsiOperaciju(u, null);
        return operacija.getUspesno();
    }

    public List<Upisnica> vratiListuSviUpisnica() throws Exception {
        VratiListuSviUpisnicaSO operacija = new VratiListuSviUpisnicaSO();
        operacija.izvrsiOperaciju(new Upisnica(), null);
        return operacija.getLista();
    }

    public List<Upisnica> vratiListuUpisnicaVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        VratiListuUpisnicaVrstaPlesaSO operacija = new VratiListuUpisnicaVrstaPlesaSO();
        operacija.izvrsiOperaciju(new Upisnica(), vrstaPlesa);
        return operacija.getLista();
    }

    public void odjaviSe(Instruktor instruktor) throws Exception {
        ServerNit.odjaviKlijenta(instruktor);
        ServerKontroler.getInstance().getSf().osveziTabele();

    }

    public Instruktor prijaviInstruktor(Instruktor i) throws Exception {
        PrijaviInstruktorSO operacija = new PrijaviInstruktorSO();
        operacija.izvrsiOperaciju(i, null);
        return operacija.getInstruktor();
    }

    public boolean kreirajPolaznik(Polaznik polaznik) throws Exception {
        KreirajPolaznikSO operacija = new KreirajPolaznikSO();
        operacija.izvrsiOperaciju(polaznik, null);
        return operacija.getUspesno();

    }

    public boolean promeniPolaznik(Polaznik polaznik) throws Exception {
        PromeniPolaznikSO operacija = new PromeniPolaznikSO();
        operacija.izvrsiOperaciju(polaznik, null);
        return operacija.getUspesno();
    }

    public boolean obrisiPolaznik(Polaznik polaznik) throws Exception {
        ObrisiPolaznikSO operacija = new ObrisiPolaznikSO();
        operacija.izvrsiOperaciju(polaznik, null);
        return operacija.getUspesno();
    }

    public List<UzrasnaKategorija> vratiListuSviUzrasnaKategorija() throws Exception {
        VratiListuSviUzrasnaKategorijaSO operacija = new VratiListuSviUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(new UzrasnaKategorija(), null);
        return operacija.getLista();

    }

    public List<Polaznik> vratiListuPolaznikPolaznik(String polaznik) throws Exception {
        VratiListuPolaznikPolaznikSO operacija = new VratiListuPolaznikPolaznikSO();
        operacija.izvrsiOperaciju(new Polaznik(), polaznik);
        return operacija.getLista();
    }

    public List<Polaznik> vratiListuPolaznikUzrasnaKategorija(String uzrast) throws Exception {
        VratiListuPolaznikUzrasnaKategorijaSO operacija = new VratiListuPolaznikUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(new Polaznik(), uzrast);
        return operacija.getLista();
    }

    public List<Kvalifikacija> vratiListuSviKvalifikacija() throws Exception {
        VratiListuSviKvalifikacijaSO operacija = new VratiListuSviKvalifikacijaSO();
        operacija.izvrsiOperaciju(new Kvalifikacija(), null);
        return operacija.getLista();

    }

    public List<Instruktor> vratiListuInstruktorKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        VratiListuInstruktorKvalifikacijaSO operacija = new VratiListuInstruktorKvalifikacijaSO();
        operacija.izvrsiOperaciju(new Instruktor(), kvalifikacija);
        return operacija.getLista();

    }

    public List<Instruktor> vratiListuInstruktorInstruktor(String instruktor) throws Exception {
        VratiListuInstruktorInstruktorSO operacija = new VratiListuInstruktorInstruktorSO();
        operacija.izvrsiOperaciju(new Instruktor(), instruktor);
        return operacija.getLista();

    }

    public boolean kreirajInstruktor(Instruktor instruktor) throws Exception {
        KreirajInstruktorSO operacija = new KreirajInstruktorSO();
        operacija.izvrsiOperaciju(instruktor, null);
        return operacija.getUspesno();
    }

    public boolean promeniInstruktor(Instruktor instruktor) throws Exception {
        PromeniInstruktorSO operacija = new PromeniInstruktorSO();
        operacija.izvrsiOperaciju(instruktor, null);
        return operacija.getUspesno();
    }

    public boolean obrisiInstruktor(Instruktor instruktor) throws Exception {
        ObrisiInstruktorSO operacija = new ObrisiInstruktorSO();
        operacija.izvrsiOperaciju(instruktor, null);
        return operacija.getUspesno();
    }

    public List<Kvalifikacija> vratiListuKvalifikacijaKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        VratiListuKvalifikacijaKvalifikacijaSO operacija = new VratiListuKvalifikacijaKvalifikacijaSO();
        operacija.izvrsiOperaciju(new Kvalifikacija(), kvalifikacija);
        return operacija.getLista();
    }

    public boolean ubaciKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        UbaciKvalifikacijaSO operacija = new UbaciKvalifikacijaSO();
        operacija.izvrsiOperaciju(kvalifikacija, null);
        return operacija.getUspesno();
    }

    public boolean promeniKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        PromeniKvalifikacijaSO operacija = new PromeniKvalifikacijaSO();
        operacija.izvrsiOperaciju(kvalifikacija, null);
        return operacija.getUspesno();
    }

    public boolean obrisiKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        ObrisiKvalifikacijaSO operacija = new ObrisiKvalifikacijaSO();
        operacija.izvrsiOperaciju(kvalifikacija, null);
        return operacija.getUspesno();
    }

    public boolean obrisiVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        ObrisiVrstaPlesaSO operacija = new ObrisiVrstaPlesaSO();
        operacija.izvrsiOperaciju(vrstaPlesa, null);
        return operacija.getUspesno();
    }

    public boolean promeniVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        PromeniVrstaPlesaSO operacija = new PromeniVrstaPlesaSO();
        operacija.izvrsiOperaciju(vrstaPlesa, null);
        return operacija.getUspesno();
    }

    public boolean kreirajVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        KreirajVrstaPlesaSO operacija = new KreirajVrstaPlesaSO();
        operacija.izvrsiOperaciju(vrstaPlesa, null);
        return operacija.getUspesno();
    }

    public List<VrstaPlesa> vratiListuVrstaPlesaVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        VratiListuVrstaPlesaVrstaPlesaSO operacija = new VratiListuVrstaPlesaVrstaPlesaSO();
        operacija.izvrsiOperaciju(new VrstaPlesa(), vrstaPlesa);
        return operacija.getLista();
    }

    public boolean kreirajUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) throws Exception {
        KreirajUzrasnaKategorijaSO operacija = new KreirajUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(uzrasnaKategorija, null);
        return operacija.getUspesno();
    }

    public boolean promeniUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) throws Exception {
        PromeniUzrasnaKategorijaSO operacija = new PromeniUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(uzrasnaKategorija, null);
        return operacija.getUspesno();

    }

    public boolean obrisiUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) throws Exception {
        ObrisiUzrasnaKategorijaSO operacija = new ObrisiUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(uzrasnaKategorija, null);
        return operacija.getUspesno();
    }

    public List<UzrasnaKategorija> vratiListuUzrasnaKategorijaUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) throws Exception {
        VratiListuUzrasnaKategorijaUzrasnaKategorijaSO operacija = new VratiListuUzrasnaKategorijaUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(new UzrasnaKategorija(), uzrasnaKategorija);
        return operacija.getLista();
    }

    public boolean dodajPrijavljenog(Instruktor instruktor) throws Exception {
        boolean prijavljen = false;

        for (ObradaKlijentskihZahteva korisnik : ServerNit.getPrijavljeniKorisnici()) {
            if (korisnik.getIme() != null && korisnik.getIme().equals(instruktor.getIme())
                    && korisnik.getPrezime() != null && korisnik.getPrezime().equals(instruktor.getPrezime())
                    && korisnik.getKorisnickoIme() != null && korisnik.getKorisnickoIme().equals(instruktor.getKorisnickoIme())
                    && korisnik.getSifra() != null && korisnik.getSifra().equals(instruktor.getSifra())
                    && korisnik.getEmail() != null && korisnik.getEmail().equals(instruktor.getEmail())) {
                prijavljen = true;

            }
        }

        if (!prijavljen) {
            for (ObradaKlijentskihZahteva korisnik : ServerNit.getPrijavljeniKorisnici()) {

                if (korisnik.getIme() == null
                        && korisnik.getPrezime() == null
                        && korisnik.getKorisnickoIme() == null
                        && korisnik.getSifra() == null
                        && korisnik.getEmail() == null) {
                    korisnik.setIme(instruktor.getIme());
                    korisnik.setPrezime(instruktor.getPrezime());
                    korisnik.setKorisnickoIme(instruktor.getKorisnickoIme());
                    korisnik.setSifra(instruktor.getSifra());
                    korisnik.setEmail(instruktor.getEmail());
                    break;

                }
            }

        }
        ServerKontroler.getInstance().getSf().osveziTabele();

        return prijavljen;

    }

    public void osveziTabele() {
        sf.osveziTabele();
    }

    public Upisnica pretraziUpisnica(Upisnica upisnica) throws Exception {
        PretraziUpisnicaSO operacija = new PretraziUpisnicaSO();
        operacija.izvrsiOperaciju(upisnica, null);
        return operacija.getUpisnica();
    }

    public Polaznik pretraziPolaznik(Polaznik polaznik) throws Exception {
        PretraziPolaznikSO operacija = new PretraziPolaznikSO();
        operacija.izvrsiOperaciju(polaznik, null);
        return operacija.getPolaznik();
    }

    public Instruktor pretraziInstruktor(Instruktor instruktor) throws Exception {
        PretraziInstruktorSO operacija = new PretraziInstruktorSO();
        operacija.izvrsiOperaciju(instruktor, null);
        return operacija.getInstruktor();
    }

    public UzrasnaKategorija pretraziUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) throws Exception {
        PretraziUzrasnaKategorijaSO operacija = new PretraziUzrasnaKategorijaSO();
        operacija.izvrsiOperaciju(uzrasnaKategorija, null);
        return operacija.getUzrasnaKategorija();
    }

    public VrstaPlesa pretraziVrstaPlesa(VrstaPlesa vrstaPlesa) throws Exception {
        PretraziVrstaPlesaSO operacija = new PretraziVrstaPlesaSO();
        operacija.izvrsiOperaciju(vrstaPlesa, null);
        return operacija.getVrstaPlesa();
    }

    public Kvalifikacija pretraziKvalifikacija(Kvalifikacija kvalifikacija) throws Exception {
        PretraziKvalifikacijaSO operacija = new PretraziKvalifikacijaSO();
        operacija.izvrsiOperaciju(kvalifikacija, null);
        return operacija.getKvalifikacija();
    }
    
    public boolean kreirajSertifikat(Sertifikat sertifikat) throws Exception {
        KreirajSertifikatSO operacija = new KreirajSertifikatSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getUspesno();
    }

    public boolean obrisiSertifikat(Sertifikat sertifikat) throws Exception {
        ObrisiSertifikatSO operacija = new ObrisiSertifikatSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getUspesno();
    }

    public boolean promeniSertifikat(Sertifikat sertifikat) throws Exception {
        PromeniSertifikatSO operacija = new PromeniSertifikatSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getUspesno();
    }

    public Sertifikat pretraziSertifikat(Sertifikat sertifikat) throws Exception {
        PretraziSertifikatSO operacija = new PretraziSertifikatSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getSertifikat();
    }

    public List<Sertifikat> vratiListuSviSertifikat() throws Exception {
        VratiListuSviSertifikatSO operacija = new VratiListuSviSertifikatSO();
        operacija.izvrsiOperaciju(new Sertifikat(), null);
        return operacija.getLista();
    }

    public List<Sertifikat> vratiListuSertifikatPolaznik(Sertifikat sertifikat) throws Exception {
        VratiListuSertifikatPolaznikSO operacija = new VratiListuSertifikatPolaznikSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getLista();
    }

    public List<Sertifikat> vratiListuSertifikatVrstaPlesa(Sertifikat sertifikat) throws Exception {
        VratiListuSertifikatVrstaPlesaSO operacija = new VratiListuSertifikatVrstaPlesaSO();
        operacija.izvrsiOperaciju(sertifikat, null);
        return operacija.getLista();
    }

}
