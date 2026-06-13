/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import kontroler.ServerKontroler;
import java.net.Socket;
import java.util.List;
import model.Instruktor;
import model.Kvalifikacija;
import model.Polaznik;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
import static komunikacija.Operacije.VRATI_LISTU_KVALIFIKACIJA_KVALIFIKACIJA;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import transfer.Zahtev;
import transfer.Odgovor;

/**
 *
 * @author Tijana
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;
    private Primalac primalac;
    private Posiljalac posiljalac;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String email;

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        this.primalac = new Primalac(s);
        this.posiljalac = new Posiljalac(s);
        this.ime = null;
        this.prezime = null;
        this.korisnickoIme = null;
        this.sifra = null;
        this.email = null;
    }

    @Override
    public void run() {
        Odgovor so = new Odgovor();

        while (s != null && !s.isClosed()) {
            try {

                Zahtev kz = (Zahtev) primalac.primi();
                if (kz != null) {
                    switch (kz.getOperacija()) {
                        case PRIJAVI_INSTRUKTOR:

                            Instruktor i = (Instruktor) kz.getParam();
                            Instruktor instruktor = ServerKontroler.getInstance().prijaviInstruktor(i);
                            so.setOdgovor(instruktor);
                            
                            posiljalac.posalji(so);
                            break;

                        case KREIRAJ_UPISNICA:

                            boolean uspesnoUpisnica = ServerKontroler.getInstance().kreirajUpisnica((Upisnica) kz.getParam());
                            so.setOdgovor(uspesnoUpisnica);

                            posiljalac.posalji(so);
                            break;

                        case PROMENI_UPISNICA:
                            boolean uspesnoPromeniUpisnica = ServerKontroler.getInstance().promeniUpisnica((Upisnica) kz.getParam());
                            so.setOdgovor(uspesnoPromeniUpisnica);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_INSTRUKTOR:

                            List<Instruktor> listaInstruktora = ServerKontroler.getInstance().vratiListuSviInstruktor();
                            so.setOdgovor(listaInstruktora);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_POLAZNIK:

                            List<Polaznik> listaPolaznika = ServerKontroler.getInstance().vratiListuSviPolaznik();
                            so.setOdgovor(listaPolaznika);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_VRSTA_PLESA:

                            List<VrstaPlesa> listaVP = ServerKontroler.getInstance().vratiListuSviVrstaPlesa();
                            so.setOdgovor(listaVP);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_UPISNICA_UPISNICA:
                            List<Upisnica> listaUpisnicaID = ServerKontroler.getInstance().vratiListuUpisnicaUpisnica((int) kz.getParam());
                            so.setOdgovor(listaUpisnicaID);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_UPISNICA_INSTRUKTOR:
                            List<Upisnica> listaUpisnicaInstruktor = ServerKontroler.getInstance().vratiListuUpisnicaInstruktor((String) kz.getParam());
                            so.setOdgovor(listaUpisnicaInstruktor);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_UPISNICA_POLAZNIK:
                            List<Upisnica> listaUpisnicaPolaznik = ServerKontroler.getInstance().vratiListuUpisnicaPolaznik((String) kz.getParam());
                            so.setOdgovor(listaUpisnicaPolaznik);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_UPISNICA_VRSTA_PLESA:
                            List<Upisnica> listaUpisnicaVrstaPlesa = ServerKontroler.getInstance().vratiListuUpisnicaVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(listaUpisnicaVrstaPlesa);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_UPISNICA:
                            List<Upisnica> listaSvihUpisnica = ServerKontroler.getInstance().vratiListuSviUpisnica();
                            so.setOdgovor(listaSvihUpisnica);
                            
                            posiljalac.posalji(so);
                            break;

                        case DODAJ_PRIJAVLJENOG:
                            boolean prijavljen = ServerKontroler.getInstance().dodajPrijavljenog((Instruktor) kz.getParam());
                            so.setOdgovor(prijavljen);
                            
                            posiljalac.posalji(so);
                            break;

                        case ODJAVI_SE:

                            ServerKontroler.getInstance().odjaviSe((Instruktor) kz.getParam());

                            break;

                        case KREIRAJ_POLAZNIK:
                            boolean uspesnoPolaznik = ServerKontroler.getInstance().kreirajPolaznik((Polaznik) kz.getParam());
                            so.setOdgovor(uspesnoPolaznik);
                            
                            posiljalac.posalji(so);
                            break;

                        case PROMENI_POLAZNIK:
                            boolean uspesnoPromeniPolaznik = ServerKontroler.getInstance().promeniPolaznik((Polaznik) kz.getParam());
                            so.setOdgovor(uspesnoPromeniPolaznik);
                            
                            posiljalac.posalji(so);
                            break;

                        case OBRISI_POLAZNIK:
                            boolean uspesnoObrisiPolaznik = ServerKontroler.getInstance().obrisiPolaznik((Polaznik) kz.getParam());
                            so.setOdgovor(uspesnoObrisiPolaznik);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_UZRASNA_KATEGORIJA:
                            List<UzrasnaKategorija> listaUzrasnihKategorija = ServerKontroler.getInstance().vratiListuSviUzrasnaKategorija();
                            so.setOdgovor(listaUzrasnihKategorija);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_POLAZNIK_POLAZNIK:
                            List<Polaznik> listaPolaznikaPolaznik = ServerKontroler.getInstance().vratiListuPolaznikPolaznik((String) kz.getParam());
                            so.setOdgovor(listaPolaznikaPolaznik);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_POLAZNIK_UZRASNA_KATEGORIJA:
                            List<Polaznik> listaPolaznikaUzrasnaKategorija = ServerKontroler.getInstance().vratiListuPolaznikUzrasnaKategorija((String) kz.getParam());
                            so.setOdgovor(listaPolaznikaUzrasnaKategorija);
                            
                            posiljalac.posalji(so);
                            break;

                        case PROMENI_INSTRUKTOR:
                            boolean uspesnoPromeniInstruktor = ServerKontroler.getInstance().promeniInstruktor((Instruktor) kz.getParam());
                            so.setOdgovor(uspesnoPromeniInstruktor);
                            
                            posiljalac.posalji(so);
                            break;

                        case KREIRAJ_INSTRUKTOR:
                            boolean uspesnoInstruktor = ServerKontroler.getInstance().kreirajInstruktor((Instruktor) kz.getParam());
                            so.setOdgovor(uspesnoInstruktor);
                            
                            posiljalac.posalji(so);
                            break;

                        case OBRISI_INSTRUKTOR:
                            boolean uspesnoObrisiInstruktor = ServerKontroler.getInstance().obrisiInstruktor((Instruktor) kz.getParam());
                            so.setOdgovor(uspesnoObrisiInstruktor);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_INSTRUKTOR_INSTRUKTOR:
                            List<Instruktor> listaInstruktoraInstruktor = ServerKontroler.getInstance().vratiListuInstruktorInstruktor((String) kz.getParam());
                            so.setOdgovor(listaInstruktoraInstruktor);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_INSTRUKTOR_KVALIFIKACIJA:
                            List<Instruktor> listaInstruktoraKvalifikacija = ServerKontroler.getInstance().vratiListuInstruktorKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(listaInstruktoraKvalifikacija);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_SVI_KVALIFIKACIJA:
                            List<Kvalifikacija> listaInstruktorKvalifikacija = ServerKontroler.getInstance().vratiListuSviKvalifikacija();
                            so.setOdgovor(listaInstruktorKvalifikacija);
                            
                            posiljalac.posalji(so);
                            break;

                        case KREIRAJ_UZRASNA_KATEGORIJA:
                            boolean uspesnoUk = ServerKontroler.getInstance().kreirajUzrasnaKategorija((UzrasnaKategorija) kz.getParam());
                            so.setOdgovor(uspesnoUk);
                            
                            posiljalac.posalji(so);
                            break;

                        case PROMENI_UZRASNA_KATEGORIJA:
                            boolean uspesnoPromeniUk = ServerKontroler.getInstance().promeniUzrasnaKategorija((UzrasnaKategorija) kz.getParam());
                            so.setOdgovor(uspesnoPromeniUk);
                            
                            posiljalac.posalji(so);
                            break;

                        case OBRISI_UZRASNA_KATEGORIJA:
                            boolean uspesnoObrisiUk = ServerKontroler.getInstance().obrisiUzrasnaKategorija((UzrasnaKategorija) kz.getParam());
                            so.setOdgovor(uspesnoObrisiUk);
                            
                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_UZRASNA_KATEGORIJA_UZRASNA_KATEGORIJA:
                            List<UzrasnaKategorija> listaUkID = ServerKontroler.getInstance().vratiListuUzrasnaKategorijaUzrasnaKategorija((UzrasnaKategorija) kz.getParam());
                            so.setOdgovor(listaUkID);
                            
                            posiljalac.posalji(so);
                            break;

                        case OBRISI_VRSTA_PLESA:
                            boolean uspesnoObrisiVrstaPlesa = ServerKontroler.getInstance().obrisiVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(uspesnoObrisiVrstaPlesa);
                            
                            posiljalac.posalji(so);
                            break;

                        case PROMENI_VRSTA_PLESA:
                            boolean uspesnoPromeniVrstaPleaa = ServerKontroler.getInstance().promeniVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(uspesnoPromeniVrstaPleaa);
                            
                            posiljalac.posalji(so);
                            break;

                        case KREIRAJ_VRSTA_PLESA:
                            boolean uspesnoVrstaPlesa = ServerKontroler.getInstance().kreirajVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(uspesnoVrstaPlesa);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_VRSTA_PLESA_VRSTA_PLESA:
                            List<VrstaPlesa> listaVrstaPlesaID = ServerKontroler.getInstance().vratiListuVrstaPlesaVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(listaVrstaPlesaID);
                            
                            posiljalac.posalji(so);
                            break;

                        case OBRISI_KVALIFIKACIJA:
                            boolean uspesnoObrisiKvalifikacija = ServerKontroler.getInstance().obrisiKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(uspesnoObrisiKvalifikacija);
                            
                            posiljalac.posalji(so);
                            break;

                        case PROMENI_KVALIFIKACIJA:
                            boolean uspesnoPromeniKvalifikacija = ServerKontroler.getInstance().promeniKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(uspesnoPromeniKvalifikacija);

                            posiljalac.posalji(so);
                            break;

                        case UBACI_KVALIFIKACIJA:
                            boolean uspesnoKvalifikacija = ServerKontroler.getInstance().ubaciKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(uspesnoKvalifikacija);

                            posiljalac.posalji(so);
                            break;

                        case VRATI_LISTU_KVALIFIKACIJA_KVALIFIKACIJA:
                            List<Kvalifikacija> listaKvalifikacijaID = ServerKontroler.getInstance().vratiListuKvalifikacijaKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(listaKvalifikacijaID);
                            
                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_UPISNICA:
                            Upisnica upisnica = ServerKontroler.getInstance().pretraziUpisnica((Upisnica) kz.getParam());
                            so.setOdgovor(upisnica);

                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_POLAZNIK:
                            Polaznik polaznik = ServerKontroler.getInstance().pretraziPolaznik((Polaznik) kz.getParam());
                            so.setOdgovor(polaznik);

                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_INSTRUKTOR:
                            Instruktor inst = ServerKontroler.getInstance().pretraziInstruktor((Instruktor) kz.getParam());
                            so.setOdgovor(inst);

                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_UZRASNA_KATEGORIJA:
                            UzrasnaKategorija uz = ServerKontroler.getInstance().pretraziUzrasnaKategorija((UzrasnaKategorija) kz.getParam());
                            so.setOdgovor(uz);

                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_VRSTA_PLESA:
                            VrstaPlesa vp = ServerKontroler.getInstance().pretraziVrstaPlesa((VrstaPlesa) kz.getParam());
                            so.setOdgovor(vp);

                            posiljalac.posalji(so);
                            break;

                        case PRETRAZI_KVALIFIKACIJA:
                            Kvalifikacija kv = ServerKontroler.getInstance().pretraziKvalifikacija((Kvalifikacija) kz.getParam());
                            so.setOdgovor(kv);

                            posiljalac.posalji(so);
                            break;

                        default:
                            System.err.println("GREŠKA: Nepoznata sistemska operacija.");
                    }
                }

            } catch (Exception e) {
                System.err.println("Greška pri izvršenju sistemske operacije: " + e);

            }

        }
    }

}
