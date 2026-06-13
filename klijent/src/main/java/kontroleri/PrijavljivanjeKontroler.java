/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.Prijavljivanje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.Instruktor;

/**
 *
 * @author Tijana
 */
public class PrijavljivanjeKontroler {

    private final Prijavljivanje lf;
    private int brojac;
    private int brojacPrazno;
    private final int MAX_BROJ_POKUSAJA = 3;

    public PrijavljivanjeKontroler(Prijavljivanje lf) {
        this.lf = lf;
        brojac = 0;
        brojacPrazno = 0;

        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }

            private void login() {

                String ime = lf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = String.valueOf(lf.getjPasswordFieldSifra().getPassword());
                if (ime.trim().isEmpty() && sifra.trim().isEmpty()) {
                    brojacPrazno++;
                    if (brojacPrazno == 2) {
                        JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");
                        return;
                    }
                    JOptionPane.showMessageDialog(lf, "Morate uneti korisničko ime i šifru.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().isEmpty()) {
                    brojacPrazno++;
                    if (brojacPrazno == 2) {
                        JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");
                        return;
                    }
                    JOptionPane.showMessageDialog(lf, "Morate uneti korisničko ime.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (sifra.trim().isEmpty()) {
                    brojacPrazno++;
                    if (brojacPrazno == 2) {
                        JOptionPane.showMessageDialog(lf, "Nemate više pokušaja.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");
                        return;
                    }
                    JOptionPane.showMessageDialog(lf, "Morate uneti šifru.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                brojac++;
                try {
                    Komunikacija.getInstance().povezivanje();
                } catch (Exception ex) {
                    return;
                }
                boolean uspesno = Komunikacija.uspeh;
                if (!uspesno) {

                    JOptionPane.showMessageDialog(lf, "Server nije u funkciji.", "Greška", JOptionPane.ERROR_MESSAGE);

                    String putanja = "konfiguracija/Konfiguracija_klijent.properties";
                    String port = "";
                    Properties p = new Properties();

                    try (FileInputStream fis = new FileInputStream(putanja)) {
                        p.load(fis);

                        port = p.getProperty("Port");

                    } catch (IOException ex) {
                        System.err.println("Greška pri učitavanju konfiguracionog fajla. " + ex);

                    }
                    int izbor = JOptionPane.showConfirmDialog(lf, "Broj porta je " + port + ". Da li želite da promenite broj porta?", "Greška", JOptionPane.YES_NO_OPTION);
                    if (izbor == 0) {
                        KontrolerGlavni.getInstance().pokreniKreirajKonfiguraciju();

                    }

                    return;
                }

                Instruktor instruktor = null;
                try {
                    instruktor = Komunikacija.getInstance().login(ime, sifra);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(lf, "Server nije u funkciji", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (instruktor == null) {
                    int preostalo = MAX_BROJ_POKUSAJA - brojac;

                    if (preostalo == 0) {
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        lf.dispose();
                        System.out.println("Prekinuta veza sa serverom.");

                    } else {
                        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni, ostalo je još " + preostalo + " pokušaja.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }
                    Komunikacija.getInstance().odjaviSe(null);

                } else {

                    boolean prijavljen = Komunikacija.getInstance().dodajPrijavljenogKorisnika(instruktor);

                    if (prijavljen) {

                        JOptionPane.showMessageDialog(lf, "Već ste prijavljeni na sistem.", "Neuspešna prijava", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstance().odjaviSe(null);

                        lf.dispose();

                    } else {

                        KontrolerGlavni.getInstance().setPrijavljeni(instruktor);

                        JOptionPane.showMessageDialog(lf, "Uspešno ste se prijavili.", "Uspešna prijava", JOptionPane.INFORMATION_MESSAGE);

                        try {
                            KontrolerGlavni.getInstance().pokreniGlavnaForma();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(lf, "Ne može da se otvori glavna froma i meni.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                        }

                        lf.dispose();

                    }

                }
            }
        });

        lf.prikaziSifruAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziSifru();
            }

            private void prikaziSifru() {
                if (!lf.getPrikazi()) {
                    lf.getjButtonPrikazi().setText("Prikaži šifru");
                    lf.getjPasswordFieldSifra().setEchoChar('\u2022');

                } else {

                    lf.getjButtonPrikazi().setText("Sakrij šifru");
                    lf.getjPasswordFieldSifra().setEchoChar((char) 0);

                }
                boolean p = !lf.getPrikazi();
                lf.setPrikazi(p);
            }

        });

    }

    public void prikaziFormu() {
        lf.setVisible(true);
    }

}
