/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import model.Instruktor;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author Tijana
 */
public class ServerNit extends Thread {

    private ServerSocket serverskiSoket;
    private static Socket s;
    private static boolean pokrenut = false;
    private static ObradaKlijentskihZahteva nit = null;
    private String port;
    private static List<ObradaKlijentskihZahteva> prijavljeniKorisnici = new ArrayList<>();

    public static List<ObradaKlijentskihZahteva> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    public static void setPrijavljeniKorisnici(List<ObradaKlijentskihZahteva> prijavljeniKorisnici) {
        ServerNit.prijavljeniKorisnici = prijavljeniKorisnici;
    }

    @Override
    public void run() {
        try {

            try {
                port = Konfiguracija.getInstance().ucitajPodatak("Port");

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }

            serverskiSoket = new ServerSocket(Integer.parseInt(port));

            pokrenut = true;

            System.out.println("Server je pokrenut na portu: " + port);
            while (pokrenut) {
                try {

                    s = serverskiSoket.accept();

                    System.out.println("Klijent je povezan.");
                    nit = new ObradaKlijentskihZahteva(s);
                    prijavljeniKorisnici.add(nit);
                    nit.start();
                } catch (SocketException e) {
                    if (nit != null) {
                        nit.interrupt();
                    }
                    if (!pokrenut) {
                        break;
                    }
                    System.err.println("Greška: " + e.getMessage());
                }

            }

        } catch (IOException ex) {
            pokrenut = false;
            System.err.println("Greška prilikom pokretanja servera.");

        }

    }

    public void zaustaviServer() {

        try {
            if (serverskiSoket != null && !serverskiSoket.isClosed()) {
                serverskiSoket.close();
                pokrenut = false;
                for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }

                }

                System.out.println("Server je zaustavljen.");

            }

            DBKonekcija.resetuj();

        } catch (IOException e) {
            System.err.println("Greška pri zaustavljanju servera: " + e.getMessage());
        }
    }

    public static void odjaviKlijenta(Instruktor instruktor) {

        if (instruktor != null) {
            for (ObradaKlijentskihZahteva nit : prijavljeniKorisnici) {
                if (nit.getIme().equals(instruktor.getIme())
                        && nit.getPrezime().equals(instruktor.getPrezime())
                        && nit.getEmail().equals(instruktor.getEmail())
                        && nit.getKorisnickoIme().equals(instruktor.getKorisnickoIme())
                        && nit.getSifra().equals(instruktor.getSifra())) {
                    try {
                        nit.interrupt();
                        nit.getS().close();
                        prijavljeniKorisnici.remove(nit);
                        System.out.println("Klijent " + instruktor.getIme() + " " + instruktor.getPrezime() + " se odjavio.");

                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", ex);
                    }
                }
            }
        }
        try {
            nit.interrupt();
            nit.getS().close();
            prijavljeniKorisnici.remove(nit);
        } catch (IOException e) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, "Greška prilikom odjavljivanja klijenta.", e);

        }
    }

    public static void dodajKorisnika() {
        prijavljeniKorisnici.add(nit);
    }

    public static boolean isPokrenut() {
        return pokrenut;
    }

    public static void setPokrenut(boolean pokrenut) {
        ServerNit.pokrenut = pokrenut;
    }

}
