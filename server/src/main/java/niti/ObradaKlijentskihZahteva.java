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
import model.Sertifikat;
import model.Upisnica;
import model.UzrasnaKategorija;
import model.VrstaPlesa;
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

        while (s != null && !s.isClosed()) {
            try {

                Zahtev kz = (Zahtev) primalac.primi();
                if (kz != null) {
                    switch (kz.operacija()) {
                        case PRIJAVI_INSTRUKTOR:

                            Instruktor i = (Instruktor) kz.param();
                            Instruktor instruktor = ServerKontroler.getInstance().prijaviInstruktor(i);
                            posiljalac.posalji(new Odgovor(instruktor));
                            break;

                        case KREIRAJ_UPISNICA:

                            boolean uspesnoUpisnica = ServerKontroler.getInstance().kreirajUpisnica((Upisnica) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoUpisnica));
                            break;

                        case PROMENI_UPISNICA:
                            boolean uspesnoPromeniUpisnica = ServerKontroler.getInstance().promeniUpisnica((Upisnica) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniUpisnica));
                            break;

                        case VRATI_LISTU_SVI_INSTRUKTOR:

                            List<Instruktor> listaInstruktora = ServerKontroler.getInstance().vratiListuSviInstruktor();
                            posiljalac.posalji(new Odgovor(listaInstruktora));
                            break;

                        case VRATI_LISTU_SVI_POLAZNIK:

                            List<Polaznik> listaPolaznika = ServerKontroler.getInstance().vratiListuSviPolaznik();
                            posiljalac.posalji(new Odgovor(listaPolaznika));
                            break;

                        case VRATI_LISTU_SVI_VRSTA_PLESA:

                            List<VrstaPlesa> listaVP = ServerKontroler.getInstance().vratiListuSviVrstaPlesa();
                            posiljalac.posalji(new Odgovor(listaVP));
                            break;

                        case VRATI_LISTU_UPISNICA_UPISNICA:
                            List<Upisnica> listaUpisnicaID = ServerKontroler.getInstance().vratiListuUpisnicaUpisnica((int) kz.param());
                            posiljalac.posalji(new Odgovor(listaUpisnicaID));
                            break;

                        case VRATI_LISTU_UPISNICA_INSTRUKTOR:
                            List<Upisnica> listaUpisnicaInstruktor = ServerKontroler.getInstance().vratiListuUpisnicaInstruktor((String) kz.param());
                            posiljalac.posalji(new Odgovor(listaUpisnicaInstruktor));
                            break;

                        case VRATI_LISTU_UPISNICA_POLAZNIK:
                            List<Upisnica> listaUpisnicaPolaznik = ServerKontroler.getInstance().vratiListuUpisnicaPolaznik((String) kz.param());
                            posiljalac.posalji(new Odgovor(listaUpisnicaPolaznik));
                            break;

                        case VRATI_LISTU_UPISNICA_VRSTA_PLESA:
                            List<Upisnica> listaUpisnicaVrstaPlesa = ServerKontroler.getInstance().vratiListuUpisnicaVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(listaUpisnicaVrstaPlesa));
                            break;

                        case VRATI_LISTU_SVI_UPISNICA:
                            List<Upisnica> listaSvihUpisnica = ServerKontroler.getInstance().vratiListuSviUpisnica();
                            posiljalac.posalji(new Odgovor(listaSvihUpisnica));
                            break;

                        case DODAJ_PRIJAVLJENOG:
                            boolean prijavljen = ServerKontroler.getInstance().dodajPrijavljenog((Instruktor) kz.param());
                            posiljalac.posalji(new Odgovor(prijavljen));
                            break;

                        case ODJAVI_SE:

                            ServerKontroler.getInstance().odjaviSe((Instruktor) kz.param());

                            break;

                        case KREIRAJ_POLAZNIK:
                            boolean uspesnoPolaznik = ServerKontroler.getInstance().kreirajPolaznik((Polaznik) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPolaznik));
                            break;

                        case PROMENI_POLAZNIK:
                            boolean uspesnoPromeniPolaznik = ServerKontroler.getInstance().promeniPolaznik((Polaznik) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniPolaznik));
                            break;

                        case OBRISI_POLAZNIK:
                            boolean uspesnoObrisiPolaznik = ServerKontroler.getInstance().obrisiPolaznik((Polaznik) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiPolaznik));
                            break;

                        case VRATI_LISTU_SVI_UZRASNA_KATEGORIJA:
                            List<UzrasnaKategorija> listaUzrasnihKategorija = ServerKontroler.getInstance().vratiListuSviUzrasnaKategorija();
                            posiljalac.posalji(new Odgovor(listaUzrasnihKategorija));
                            break;

                        case VRATI_LISTU_POLAZNIK_POLAZNIK:
                            List<Polaznik> listaPolaznikaPolaznik = ServerKontroler.getInstance().vratiListuPolaznikPolaznik((String) kz.param());
                            posiljalac.posalji(new Odgovor(listaPolaznikaPolaznik));
                            break;

                        case VRATI_LISTU_POLAZNIK_UZRASNA_KATEGORIJA:
                            List<Polaznik> listaPolaznikaUzrasnaKategorija = ServerKontroler.getInstance().vratiListuPolaznikUzrasnaKategorija((String) kz.param());
                            posiljalac.posalji(new Odgovor(listaPolaznikaUzrasnaKategorija));
                            break;

                        case PROMENI_INSTRUKTOR:
                            boolean uspesnoPromeniInstruktor = ServerKontroler.getInstance().promeniInstruktor((Instruktor) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniInstruktor));
                            break;

                        case KREIRAJ_INSTRUKTOR:
                            boolean uspesnoInstruktor = ServerKontroler.getInstance().kreirajInstruktor((Instruktor) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoInstruktor));
                            break;

                        case OBRISI_INSTRUKTOR:
                            boolean uspesnoObrisiInstruktor = ServerKontroler.getInstance().obrisiInstruktor((Instruktor) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiInstruktor));
                            break;

                        case VRATI_LISTU_INSTRUKTOR_INSTRUKTOR:
                            List<Instruktor> listaInstruktoraInstruktor = ServerKontroler.getInstance().vratiListuInstruktorInstruktor((String) kz.param());
                            posiljalac.posalji(new Odgovor(listaInstruktoraInstruktor));
                            break;

                        case VRATI_LISTU_INSTRUKTOR_KVALIFIKACIJA:
                            List<Instruktor> listaInstruktoraKvalifikacija = ServerKontroler.getInstance().vratiListuInstruktorKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(listaInstruktoraKvalifikacija));
                            break;

                        case VRATI_LISTU_SVI_KVALIFIKACIJA:
                            List<Kvalifikacija> listaInstruktorKvalifikacija = ServerKontroler.getInstance().vratiListuSviKvalifikacija();
                            posiljalac.posalji(new Odgovor(listaInstruktorKvalifikacija));
                            break;

                        case KREIRAJ_UZRASNA_KATEGORIJA:
                            boolean uspesnoUk = ServerKontroler.getInstance().kreirajUzrasnaKategorija((UzrasnaKategorija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoUk));
                            break;

                        case PROMENI_UZRASNA_KATEGORIJA:
                            boolean uspesnoPromeniUk = ServerKontroler.getInstance().promeniUzrasnaKategorija((UzrasnaKategorija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniUk));
                            break;

                        case OBRISI_UZRASNA_KATEGORIJA:
                            boolean uspesnoObrisiUk = ServerKontroler.getInstance().obrisiUzrasnaKategorija((UzrasnaKategorija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiUk));
                            break;

                        case VRATI_LISTU_UZRASNA_KATEGORIJA_UZRASNA_KATEGORIJA:
                            List<UzrasnaKategorija> listaUkID = ServerKontroler.getInstance().vratiListuUzrasnaKategorijaUzrasnaKategorija((UzrasnaKategorija) kz.param());
                            posiljalac.posalji(new Odgovor(listaUkID));
                            break;

                        case OBRISI_VRSTA_PLESA:
                            boolean uspesnoObrisiVrstaPlesa = ServerKontroler.getInstance().obrisiVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiVrstaPlesa));
                            break;

                        case PROMENI_VRSTA_PLESA:
                            boolean uspesnoPromeniVrstaPleaa = ServerKontroler.getInstance().promeniVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniVrstaPleaa));
                            break;

                        case KREIRAJ_VRSTA_PLESA:
                            boolean uspesnoVrstaPlesa = ServerKontroler.getInstance().kreirajVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoVrstaPlesa));
                            break;

                        case VRATI_LISTU_VRSTA_PLESA_VRSTA_PLESA:
                            List<VrstaPlesa> listaVrstaPlesaID = ServerKontroler.getInstance().vratiListuVrstaPlesaVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(listaVrstaPlesaID));
                            break;

                        case OBRISI_KVALIFIKACIJA:
                            boolean uspesnoObrisiKvalifikacija = ServerKontroler.getInstance().obrisiKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiKvalifikacija));
                            break;

                        case PROMENI_KVALIFIKACIJA:
                            boolean uspesnoPromeniKvalifikacija = ServerKontroler.getInstance().promeniKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniKvalifikacija));
                            break;

                        case UBACI_KVALIFIKACIJA:
                            boolean uspesnoKvalifikacija = ServerKontroler.getInstance().ubaciKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoKvalifikacija));
                            break;

                        case VRATI_LISTU_KVALIFIKACIJA_KVALIFIKACIJA:
                            List<Kvalifikacija> listaKvalifikacijaID = ServerKontroler.getInstance().vratiListuKvalifikacijaKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(listaKvalifikacijaID));
                            break;

                        case PRETRAZI_UPISNICA:
                            Upisnica upisnica = ServerKontroler.getInstance().pretraziUpisnica((Upisnica) kz.param());
                            posiljalac.posalji(new Odgovor(upisnica));
                            break;

                        case PRETRAZI_POLAZNIK:
                            Polaznik polaznik = ServerKontroler.getInstance().pretraziPolaznik((Polaznik) kz.param());
                            posiljalac.posalji(new Odgovor(polaznik));
                            break;

                        case PRETRAZI_INSTRUKTOR:
                            Instruktor inst = ServerKontroler.getInstance().pretraziInstruktor((Instruktor) kz.param());
                            posiljalac.posalji(new Odgovor(inst));
                            break;

                        case PRETRAZI_UZRASNA_KATEGORIJA:
                            UzrasnaKategorija uz = ServerKontroler.getInstance().pretraziUzrasnaKategorija((UzrasnaKategorija) kz.param());
                            posiljalac.posalji(new Odgovor(uz));
                            break;

                        case PRETRAZI_VRSTA_PLESA:
                            VrstaPlesa vp = ServerKontroler.getInstance().pretraziVrstaPlesa((VrstaPlesa) kz.param());
                            posiljalac.posalji(new Odgovor(vp));
                            break;

                        case PRETRAZI_KVALIFIKACIJA:
                            Kvalifikacija kv = ServerKontroler.getInstance().pretraziKvalifikacija((Kvalifikacija) kz.param());
                            posiljalac.posalji(new Odgovor(kv));
                            break;
                            
                        case KREIRAJ_SERTIFIKAT:
                            boolean uspesnoSertifikat = ServerKontroler.getInstance().kreirajSertifikat((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoSertifikat));
                            break;

                        case OBRISI_SERTIFIKAT:
                            boolean uspesnoObrisiSertifikat = ServerKontroler.getInstance().obrisiSertifikat((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoObrisiSertifikat));
                            break;

                        case PROMENI_SERTIFIKAT:
                            boolean uspesnoPromeniSertifikat = ServerKontroler.getInstance().promeniSertifikat((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(uspesnoPromeniSertifikat));
                            break;

                        case PRETRAZI_SERTIFIKAT:
                            Sertifikat sertifikat = ServerKontroler.getInstance().pretraziSertifikat((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(sertifikat));
                            break;

                        case VRATI_LISTU_SVI_SERTIFIKAT:
                            List<Sertifikat> listaSvihSertifikata = ServerKontroler.getInstance().vratiListuSviSertifikat();
                            posiljalac.posalji(new Odgovor(listaSvihSertifikata));
                            break;

                        case VRATI_LISTU_SERTIFIKAT_POLAZNIK:
                            List<Sertifikat> listaSertifikatPolaznik = ServerKontroler.getInstance().vratiListuSertifikatPolaznik((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(listaSertifikatPolaznik));
                            break;

                        case VRATI_LISTU_SERTIFIKAT_VRSTA_PLESA:
                            List<Sertifikat> listaSertifikatVrstaPlesa = ServerKontroler.getInstance().vratiListuSertifikatVrstaPlesa((Sertifikat) kz.param());
                            posiljalac.posalji(new Odgovor(listaSertifikatVrstaPlesa));
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
