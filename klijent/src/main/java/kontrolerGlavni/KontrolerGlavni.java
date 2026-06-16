/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontrolerGlavni;

import forme.GlavnaForma;
import forme.KreirajInstruktora;
import forme.KreirajKonfiguraciju;
import forme.KreirajKvalifikaciju;
import forme.KreirajPolaznika;
import forme.KreirajUpisnicu;
import forme.KreirajUzrasnuKategoriju;
import forme.KreirajVrstuPlesa;
import forme.Prijavljivanje;
import forme.PretraziInstruktora;
import forme.PretraziKvalifkaciju;
import forme.PretraziPolaznika;
import forme.PretraziUpisnicu;
import forme.PretraziUzrasnuKategoriju;
import forme.PretraziVrstuPlesa;
import kontroleri.GlavnaFormaKontroler;
import kontroleri.InstruktorKontroler;
import kontroleri.KreirajKonfiguracijuKontroler;
import kontroleri.KvalifikacijaKontroler;
import kontroleri.PrijavljivanjeKontroler;
import kontroleri.PolaznikKontroler;
import kontroleri.UpisnicaKontroler;
import kontroleri.UzrasnaKategorijaKontroler;
import kontroleri.VrstaPlesaKontroler;
import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import forme.KreirajSertifikat;
import forme.PretraziSertifikat;
import kontroleri.SertifikatKontroler;
import model.Sertifikat;
/**
 *
 * @author Tijana
 */
public class KontrolerGlavni {

    private static KontrolerGlavni instance;
    private Instruktor prijavljeni = null;
    private PrijavljivanjeKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private UpisnicaKontroler upisnicaKontroler;
    private PolaznikKontroler polaznikKontroler;
    private InstruktorKontroler instruktorKontroler;
    private KvalifikacijaKontroler kvalifikacijaKontroler;
    private VrstaPlesaKontroler vrstaPlesaKontroler;
    private UzrasnaKategorijaKontroler uzrasnaKategorijaKontroler;
    private KreirajKonfiguracijuKontroler kreirajKonfiguracijuKontroler;
    private SertifikatKontroler sertifikatKontroler;

    public Instruktor getPrijavljeni() {
        return prijavljeni;
    }

    public void setPrijavljeni(Instruktor prijavljeni) {
        this.prijavljeni = prijavljeni;
    }

    public GlavnaFormaKontroler getGlavnaFormaKontroler() {
        return glavnaFormaKontroler;
    }

    public void setGlavnaFormaKontroler(GlavnaFormaKontroler glavnaFormaKontroler) {
        this.glavnaFormaKontroler = glavnaFormaKontroler;
    }

    private KontrolerGlavni() {
    }

    public static KontrolerGlavni getInstance() {
        if (instance == null) {
            instance = new KontrolerGlavni();
        }
        return instance;
    }

    public void pokreniLoginForma() {
        loginKontroler = new PrijavljivanjeKontroler(new Prijavljivanje());
        loginKontroler.prikaziFormu();
    }

    public void pokreniGlavnaForma() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.prikaziFormu();
    }

    public void pokreniKreirajUpisnicu(Upisnica upisnica) {
        upisnicaKontroler = new UpisnicaKontroler(new KreirajUpisnicu(new GlavnaForma(), true, upisnica), new PretraziUpisnicu(new GlavnaForma(), true));
        upisnicaKontroler.prikaziFormuKreirajUpisnicu(upisnica);
    }

    public void pokreniPretraziUpisnicu() {
        upisnicaKontroler = new UpisnicaKontroler(new KreirajUpisnicu(new GlavnaForma(), true, null), new PretraziUpisnicu(new GlavnaForma(), true));
        upisnicaKontroler.prikaziFormuPretraziUpisnicu();
    }

    public void pokreniKreirajPolaznika(Polaznik polaznik) {
        polaznikKontroler = new PolaznikKontroler(new KreirajPolaznika(new GlavnaForma(), true, polaznik), new PretraziPolaznika(new GlavnaForma(), true));
        polaznikKontroler.prikaziFormuKreirajPolaznika(polaznik);
    }

    public void pokreniPretraziPolaznika() {
        polaznikKontroler = new PolaznikKontroler(new KreirajPolaznika(new GlavnaForma(), true, null), new PretraziPolaznika(new GlavnaForma(), true));
        polaznikKontroler.prikaziFormuPretraziPolaznika();
    }

    public void pokreniKreirajInstruktora(Instruktor instruktor) {
        instruktorKontroler = new InstruktorKontroler(new KreirajInstruktora(new GlavnaForma(), true, instruktor), new PretraziInstruktora(new GlavnaForma(), true));
        instruktorKontroler.prikaziFormuKreirajInstruktora(instruktor);
    }

    public void pokreniPretrazinstruktora() {
        instruktorKontroler = new InstruktorKontroler(new KreirajInstruktora(new GlavnaForma(), true, null), new PretraziInstruktora(new GlavnaForma(), true));
        instruktorKontroler.prikaziFormuPretraziInstruktora();
    }

    public void pokreniKreirajKvalifikaciju(Kvalifikacija kvalifikacija) {
        kvalifikacijaKontroler = new KvalifikacijaKontroler(new KreirajKvalifikaciju(new GlavnaForma(), true, kvalifikacija), new PretraziKvalifkaciju(new GlavnaForma(), true));
        kvalifikacijaKontroler.prikaziFormuKreirajKvalifikaciju(kvalifikacija);
    }

    public void pokreniPretraziKvalifikaciju() {
        kvalifikacijaKontroler = new KvalifikacijaKontroler(new KreirajKvalifikaciju(new GlavnaForma(), true, null), new PretraziKvalifkaciju(new GlavnaForma(), true));
        kvalifikacijaKontroler.prikaziFormuPretraziKvalifikaciju();
    }

    public void pokreniKreirajVrstuPlesa(VrstaPlesa vrstaPlesa) {
        vrstaPlesaKontroler = new VrstaPlesaKontroler(new KreirajVrstuPlesa(new GlavnaForma(), true, vrstaPlesa), new PretraziVrstuPlesa(new GlavnaForma(), true));
        vrstaPlesaKontroler.prikaziFormuKreirajVrstuPlesa(vrstaPlesa);
    }

    public void pokreniPretraziVrstuPlesa() {
        vrstaPlesaKontroler = new VrstaPlesaKontroler(new KreirajVrstuPlesa(new GlavnaForma(), true, null), new PretraziVrstuPlesa(new GlavnaForma(), true));
        vrstaPlesaKontroler.prikaziFormuPretraziVrstuPlesa();
    }

    public void pokreniKreirajUzrasnuKategoriju(UzrasnaKategorija uzrasnaKategorija) {
        uzrasnaKategorijaKontroler = new UzrasnaKategorijaKontroler(new KreirajUzrasnuKategoriju(new GlavnaForma(), true, uzrasnaKategorija), new PretraziUzrasnuKategoriju(new GlavnaForma(), true));
        uzrasnaKategorijaKontroler.prikaziFormuKreirajUzrasnuKategoriju(uzrasnaKategorija);
    }

    public void pokreniPretraziUzrasnuKategoriju() {
        uzrasnaKategorijaKontroler = new UzrasnaKategorijaKontroler(new KreirajUzrasnuKategoriju(new GlavnaForma(), true, null), new PretraziUzrasnuKategoriju(new GlavnaForma(), true));
        uzrasnaKategorijaKontroler.prikaziFormuPretraziUzrasnuKategoriju();
    }

    public void pokreniKreirajKonfiguraciju() {
        kreirajKonfiguracijuKontroler = new KreirajKonfiguracijuKontroler(new KreirajKonfiguraciju(new Prijavljivanje(), true));
        kreirajKonfiguracijuKontroler.prikaziFormu();
    }
    
    public void pokreniKreirajSertifikat(Sertifikat sertifikat) {
        sertifikatKontroler = new SertifikatKontroler(
            new KreirajSertifikat(new GlavnaForma(), true, sertifikat),
            new PretraziSertifikat(new GlavnaForma(), true)
        );
        sertifikatKontroler.prikaziFormuKreirajSertifikat(sertifikat);
    }

    public void pokreniPretraziSertifikat() {
        sertifikatKontroler = new SertifikatKontroler(
            new KreirajSertifikat(new GlavnaForma(), true, null),
            new PretraziSertifikat(new GlavnaForma(), true)
        );
        sertifikatKontroler.prikaziFormuPretraziSertifikat();
    }

}
