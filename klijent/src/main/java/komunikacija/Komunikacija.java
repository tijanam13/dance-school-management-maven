/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import kontrolerGlavni.KontrolerGlavni;
import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import transfer.Zahtev;
import transfer.Odgovor;

/**
 *
 * @author Tijana
 */
public class Komunikacija {

    private Socket s;
    private static Komunikacija instance;
    private int port;
    private Posiljalac posiljalac;
    private Primalac primalac;
    public static boolean uspeh;

    private Komunikacija() {

    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void povezivanje() throws Exception {

        ucitajBrojPorta();
        try {
            s = new Socket("localhost", port);
            posiljalac = new Posiljalac(s);
            primalac = new Primalac(s);
            uspeh = true;
        } catch (IOException ex) {
            System.err.println("Neuspešno povezivanje na server: " + ex.getMessage());
            uspeh = false;

        }

    }

    public void odjaviSe(Instruktor korisnik) {
        Zahtev zahtev = new Zahtev(Operacije.ODJAVI_SE, korisnik);

        posiljalac.posalji(zahtev);

        if (s != null && !s.isClosed()) {
            try {
                s.close();
                instance = null;

            } catch (IOException ex) {
                System.err.println("Greška prilikom odjavljivanja: " + ex.getMessage());
            }

        }
    }

    private void ucitajBrojPorta() throws Exception {
        String putanja = "konfiguracija/Konfiguracija_klijent.properties";

        Properties p = new Properties();

        try (FileInputStream fis = new FileInputStream(putanja)) {
            p.load(fis);

            try {
                port = Integer.parseInt(p.getProperty("Port"));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Broj porta nije dat u ispravnom formatu.");

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Greška pri učitavanju konfiguracionog fajla. Unesite broj porta.", "Greška", JOptionPane.ERROR_MESSAGE);
            KontrolerGlavni.getInstance().pokreniKreirajKonfiguraciju();
            throw e;

        }
    }

    public Instruktor login(String ime, String sifra) throws Exception {
        Instruktor i = new Instruktor(ime, sifra);
        Zahtev zahtev = new Zahtev(Operacije.PRIJAVI_INSTRUKTOR, i);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            throw new Exception("Server nije u funkciji.");
        }
        Instruktor instruktor = (Instruktor) so.getOdgovor();
        return instruktor;
    }

    public boolean kreirajUpisnica(Upisnica upisnica) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_UPISNICA, upisnica);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;

    }

    public List<Instruktor> vratiListuSviInstruktor() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_INSTRUKTOR, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Instruktor> lista = (List<Instruktor>) so.getOdgovor();
        return lista;

    }

    public List<Polaznik> vratiListuSviPolaznik() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_POLAZNIK, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Polaznik> lista = (List<Polaznik>) so.getOdgovor();
        return lista;
    }

    public List<VrstaPlesa> vratiListuSviVrstaPlesa() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_VRSTA_PLESA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<VrstaPlesa> lista = (List<VrstaPlesa>) so.getOdgovor();
        return lista;
    }

    public boolean promeniUpisnica(Upisnica u) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_UPISNICA, u);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Upisnica> vratiListuSviUpisnica() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_UPISNICA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();

        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Upisnica> lista = (List<Upisnica>) so.getOdgovor();
        return lista;
    }

    public List<Upisnica> vratiListuUpisnicaVrstaPlesa(VrstaPlesa vp) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_UPISNICA_VRSTA_PLESA, vp);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Upisnica> lista = (List<Upisnica>) so.getOdgovor();
        return lista;
    }

    public List<Upisnica> vratiListuUpisnicaPolaznik(String polaznik) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_UPISNICA_POLAZNIK, polaznik);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Upisnica> lista = (List<Upisnica>) so.getOdgovor();
        return lista;
    }

    public List<Upisnica> vratiListuUpisnicaInstruktor(String instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_UPISNICA_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Upisnica> lista = (List<Upisnica>) so.getOdgovor();
        return lista;
    }

    public List<Upisnica> vratiListuUpisnicaID(int ID) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_UPISNICA_UPISNICA, ID);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Upisnica> lista = (List<Upisnica>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajPolaznik(Polaznik polaznik) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_POLAZNIK, polaznik);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;

    }

    public boolean obrisiPolaznik(Polaznik p) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_POLAZNIK, p);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<UzrasnaKategorija> vratiListuSviUzrasnaKategorija() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_UZRASNA_KATEGORIJA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<UzrasnaKategorija> lista = (List<UzrasnaKategorija>) so.getOdgovor();
        return lista;
    }

    public List<Polaznik> vratiListuPolaznikPolaznik(String polaznik) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_POLAZNIK_POLAZNIK, polaznik);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Polaznik> lista = (List<Polaznik>) so.getOdgovor();
        return lista;
    }

    public List<Polaznik> vratiListuPolaznikUzrasnaKategorija(String uzrast) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_POLAZNIK_UZRASNA_KATEGORIJA, uzrast);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Polaznik> lista = (List<Polaznik>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajInstruktor(Instruktor instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniPolaznik(Polaznik p) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_POLAZNIK, p);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniInstruktor(Instruktor instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiInstruktor(Instruktor instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Kvalifikacija> vratiListuSviKvalifikacija() {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_SVI_KVALIFIKACIJA, null);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kvalifikacija> lista = (List<Kvalifikacija>) so.getOdgovor();
        return lista;
    }

    public List<Instruktor> vratiListuInstruktorInstruktor(String instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_INSTRUKTOR_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Instruktor> lista = (List<Instruktor>) so.getOdgovor();
        return lista;
    }

    public List<Instruktor> vratiListuInstruktorKvalifikacija(Kvalifikacija kvalifikacija) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_INSTRUKTOR_KVALIFIKACIJA, kvalifikacija);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Instruktor> lista = (List<Instruktor>) so.getOdgovor();
        return lista;
    }

    public boolean ubaciKvalifikacija(Kvalifikacija kv) {
        Zahtev zahtev = new Zahtev(Operacije.UBACI_KVALIFIKACIJA, kv);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniKvalifikacija(Kvalifikacija kv) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_KVALIFIKACIJA, kv);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiKvalifikacija(Kvalifikacija kv) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_KVALIFIKACIJA, kv);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<Kvalifikacija> vratiListuKvalifikacijaKvalifikacija(Kvalifikacija kvalifikacija) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_KVALIFIKACIJA_KVALIFIKACIJA, kvalifikacija);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kvalifikacija> lista = (List<Kvalifikacija>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajVrstaPlesa(VrstaPlesa vp) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_VRSTA_PLESA, vp);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniVrstaPlesa(VrstaPlesa vp) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_VRSTA_PLESA, vp);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiVrstaPlesa(VrstaPlesa vp) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_VRSTA_PLESA, vp);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<VrstaPlesa> vratiListuVrstaPlesaVrstaPlesa(VrstaPlesa vrstaPlesa) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_VRSTA_PLESA_VRSTA_PLESA, vrstaPlesa);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<VrstaPlesa> lista = (List<VrstaPlesa>) so.getOdgovor();
        return lista;
    }

    public boolean kreirajUzrasnaKategorija(UzrasnaKategorija uz) {
        Zahtev zahtev = new Zahtev(Operacije.KREIRAJ_UZRASNA_KATEGORIJA, uz);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean promeniUzrasnaKategorija(UzrasnaKategorija uz) {
        Zahtev zahtev = new Zahtev(Operacije.PROMENI_UZRASNA_KATEGORIJA, uz);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public boolean obrisiUzrasnaKategorija(UzrasnaKategorija uz) {
        Zahtev zahtev = new Zahtev(Operacije.OBRISI_UZRASNA_KATEGORIJA, uz);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean uspesno = (boolean) so.getOdgovor();
        return uspesno;
    }

    public List<UzrasnaKategorija> vratiListuUzrasnaKategorijaUzrasnaKategorija(UzrasnaKategorija uzrasnaKategorija) {
        Zahtev zahtev = new Zahtev(Operacije.VRATI_LISTU_UZRASNA_KATEGORIJA_UZRASNA_KATEGORIJA, uzrasnaKategorija);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<UzrasnaKategorija> lista = (List<UzrasnaKategorija>) so.getOdgovor();
        return lista;
    }

    public boolean dodajPrijavljenogKorisnika(Instruktor instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.DODAJ_PRIJAVLJENOG, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return false;
        }
        return (boolean) so.getOdgovor();

    }

    public Upisnica pretraziUpisnica(Upisnica upisnica) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_UPISNICA, upisnica);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Upisnica) so.getOdgovor();
    }

    public Instruktor pretraziInstruktor(Instruktor instruktor) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_INSTRUKTOR, instruktor);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Instruktor) so.getOdgovor();
    }

    public Kvalifikacija pretraziKvalifikacija(Kvalifikacija kvalifikacija) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_KVALIFIKACIJA, kvalifikacija);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Kvalifikacija) so.getOdgovor();
    }

    public Polaznik pretraziPolaznik(Polaznik polaznik) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_POLAZNIK, polaznik);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (Polaznik) so.getOdgovor();
    }

    public UzrasnaKategorija pretraziUzrasnaKategorija(UzrasnaKategorija uz) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_UZRASNA_KATEGORIJA, uz);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (UzrasnaKategorija) so.getOdgovor();
    }

    public VrstaPlesa pretraziVrstaPlesa(VrstaPlesa vp) {
        Zahtev zahtev = new Zahtev(Operacije.PRETRAZI_VRSTA_PLESA, vp);
        posiljalac.posalji(zahtev);
        Odgovor so = (Odgovor) primalac.primi();
        if (so == null) {
            JOptionPane.showMessageDialog(null, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return (VrstaPlesa) so.getOdgovor();
    }

}
