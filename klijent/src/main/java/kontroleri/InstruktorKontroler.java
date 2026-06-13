/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import forme.KreirajInstruktora;
import forme.KreirajKvalifikaciju;
import forme.PretraziInstruktora;
import forme.PretraziKvalifkaciju;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.Instruktor;
import model.InstruktorKvalifikacija;
import model.Kvalifikacija;
import model.ModelTabeleInstruktorKvalifikacije;
import model.ModelTabeleInstruktori;
import model.Nivo;

/**
 *
 * @author Tijana
 */
public class InstruktorKontroler {

    private final KreirajInstruktora ki;
    private final PretraziInstruktora pi;
    private KvalifikacijaKontroler kk;
    private List<InstruktorKvalifikacija> kvalifikacije = new ArrayList<>();

    public InstruktorKontroler(KreirajInstruktora ki, PretraziInstruktora pi) {
        this.ki = ki;
        this.pi = pi;
        kk = new KvalifikacijaKontroler(new KreirajKvalifikaciju(new GlavnaForma(), true, null), new PretraziKvalifkaciju(new GlavnaForma(), true));
        addActionListeners();
    }

    private void addActionListeners() {
        ki.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {

                String ime = null;
                String prezime = null;
                String email = null;
                String korisnickoIme = null;
                String sifra = null;
                List<InstruktorKvalifikacija> lista = new ArrayList<>();

                try {
                    ime = ki.getjTextFieldIme().getText().trim();
                    prezime = ki.getjTextFieldPrezime().getText().trim();
                    email = ki.getjTextFieldEmail().getText().trim();
                    korisnickoIme = ki.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(ki.getjPasswordField().getPassword()).trim();
                    ModelTabeleInstruktorKvalifikacije mt = (ModelTabeleInstruktorKvalifikacije) ki.getjTableKvalifikacija().getModel();
                    lista = mt.getKvalifikacije();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ki, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || email.trim().isEmpty() || korisnickoIme.trim().isEmpty() || sifra.trim().isEmpty() || lista == null || lista.isEmpty()) {

                    JOptionPane.showMessageDialog(ki, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || email.trim().length() > 50 || sifra.trim().length() > 20 || korisnickoIme.trim().length() > 20) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(ki, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (korisnickoIme.trim().length() > 20) {
                        JOptionPane.showMessageDialog(ki, "Korisničko ime može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (sifra.trim().length() > 20) {
                        JOptionPane.showMessageDialog(ki, "Šifra može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !email.contains("@")) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(ki, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!email.contains("@")) {
                        JOptionPane.showMessageDialog(ki, "E-mail mora da sadrži znak @.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }

                Instruktor instruktor = new Instruktor(korisnickoIme, sifra, ime, prezime, email);
                for (InstruktorKvalifikacija instruktorKvalifikacija : lista) {
                    Date datum = instruktorKvalifikacija.getDatumSticanja();
                    Date datumZaBazu = new java.sql.Date(datum.getTime());
                    instruktorKvalifikacija.setDatumSticanja(datumZaBazu);
                    instruktorKvalifikacija.setInstruktor(instruktor);
                }
                instruktor.setInstruktorKvalifikacije(lista);

                boolean uspesno = Komunikacija.getInstance().kreirajInstruktor(instruktor);

                if (!uspesno) {
                    JOptionPane.showMessageDialog(ki, "Sistem ne može da zapamti instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(ki, "Sistem je zapamtio instruktora.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ki.dispose();
                }
            }

        });

        ki.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                String ime = null;
                String prezime = null;
                String email = null;
                String korisnickoIme = null;
                String sifra = null;

                List<InstruktorKvalifikacija> lista = new ArrayList<>();

                try {

                    ime = ki.getjTextFieldIme().getText().trim();
                    prezime = ki.getjTextFieldPrezime().getText().trim();
                    email = ki.getjTextFieldEmail().getText().trim();
                    korisnickoIme = ki.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(ki.getjPasswordField().getPassword());
                    ModelTabeleInstruktorKvalifikacije mt = (ModelTabeleInstruktorKvalifikacije) ki.getjTableKvalifikacija().getModel();
                    lista = mt.getKvalifikacije();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ki, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || email.trim().isEmpty() || korisnickoIme.trim().isEmpty() || sifra.trim().isEmpty() || lista == null || lista.isEmpty()) {

                    JOptionPane.showMessageDialog(ki, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || email.trim().length() > 50 || sifra.trim().length() > 20 || korisnickoIme.trim().length() > 20) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(ki, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (korisnickoIme.trim().length() > 20) {
                        JOptionPane.showMessageDialog(ki, "Korisničko ime može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (sifra.trim().length() > 20) {
                        JOptionPane.showMessageDialog(ki, "Šifra može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoIme || !ispravnoPrezime || !email.contains("@")) {

                    if (!ispravnoIme) {
                        JOptionPane.showMessageDialog(ki, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!ispravnoPrezime) {
                        JOptionPane.showMessageDialog(ki, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    if (!email.contains("@")) {
                        JOptionPane.showMessageDialog(ki, "E-mail mora da sadrži znak @.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    }
                    return;
                }

                Instruktor i = new Instruktor(ki.getInstruktor().getIdInstruktor(), korisnickoIme, sifra, ime, prezime, email);

                for (InstruktorKvalifikacija instruktorKvalifikacija : lista) {
                    Date datum = instruktorKvalifikacija.getDatumSticanja();
                    Date datumZaBazu = new java.sql.Date(datum.getTime());
                    instruktorKvalifikacija.setDatumSticanja(datumZaBazu);
                    instruktorKvalifikacija.setInstruktor(i);
                }
                i.setInstruktorKvalifikacije(lista);

                int odgovor = JOptionPane.showConfirmDialog(ki, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniInstruktor(i);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ki, "Sistem ne može da zapamti instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(ki, "Sistem je zapamtio instruktora.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ki.dispose();
                    }
                }
            }

        });

        ki.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {

                int odgovor = JOptionPane.showConfirmDialog(ki, "Da li ste sigurni da želite da obrišete instruktora?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String ime = null;
                    String prezime = null;
                    String email = null;
                    String korisnickoIme = null;
                    String sifra = null;

                    ime = ki.getjTextFieldIme().getText().trim();
                    prezime = ki.getjTextFieldPrezime().getText().trim();
                    email = ki.getjTextFieldEmail().getText().trim();
                    korisnickoIme = ki.getjTextFieldKorisnickoIme().getText().trim();
                    sifra = String.valueOf(ki.getjPasswordField().getPassword()).trim();
                    ModelTabeleInstruktorKvalifikacije mt = (ModelTabeleInstruktorKvalifikacije) ki.getjTableKvalifikacija().getModel();
                    List<InstruktorKvalifikacija> lista = mt.getKvalifikacije();

                    Instruktor i = new Instruktor(ki.getInstruktor().getIdInstruktor(), korisnickoIme, sifra, ime, prezime, email);

                    for (InstruktorKvalifikacija instruktorKvalifikacija : lista) {
                        Date datum = instruktorKvalifikacija.getDatumSticanja();
                        Date datumZaBazu = new java.sql.Date(datum.getTime());
                        instruktorKvalifikacija.setDatumSticanja(datumZaBazu);
                        instruktorKvalifikacija.setInstruktor(i);
                    }
                    i.setInstruktorKvalifikacije(lista);

                    boolean uspesno = Komunikacija.getInstance().obrisiInstruktor(i);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ki, "Sistem ne može da obriše instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(ki, "Sistem je obrisao instruktora.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                        ki.dispose();
                    }
                }
            }

        });

        ki.ObrisiInstruktoraAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiInstruktora();

            }

            private void obrisiInstruktora() {
                ki.getjTextFieldEmail().setText(ki.getInstruktor().getEmail());
                ki.getjTextFieldIme().setText(ki.getInstruktor().getIme());
                ki.getjTextFieldPrezime().setText(ki.getInstruktor().getPrezime());
                ki.getjTextFieldKorisnickoIme().setText(ki.getInstruktor().getKorisnickoIme());
                ki.getjPasswordField().setText(ki.getInstruktor().getSifra());
                ki.getjTextFieldDatumSticanja().setEditable(false);
                ki.getjTextFieldEmail().setEditable(false);
                ki.getjTextFieldIme().setEditable(false);
                ki.getjTextFieldKorisnickoIme().setEditable(false);
                ki.getjComboBoxKvalifikacije().setEnabled(false);
                ki.getjTextFieldPrezime().setEditable(false);
                ki.getjPasswordField().setEditable(false);
                ki.getjComboBoxNivo().setEnabled(false);
                ki.getjButtonObrisiKvalifikaciju().setEnabled(false);
                ki.getjButtonDodajNovuKvalifikaciju().setEnabled(false);
                ki.getjButtonSacuvaj().setVisible(false);
                ki.getjButtonObrisi().setVisible(true);
                ki.getjButtonObrisiInstruktora().setVisible(false);
                ki.getjButtonPromeni().setVisible(true);
                ki.getjButtonPrikaziPromeni().setVisible(false);
                ki.getjButtonPrikaziObrisi().setVisible(true);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(true);
                ki.getjButtonDodaj().setVisible(false);
                ki.getjButtonObrisiKvalifikaciju().setVisible(false);

                List<InstruktorKvalifikacija> lista = ki.getInstruktor().getInstruktorKvalifikacije();
                popuniTabeluKvalifikacijama(lista);
            }

        });

        ki.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                ki.getjTextFieldEmail().setText(ki.getInstruktor().getEmail());
                ki.getjTextFieldIme().setText(ki.getInstruktor().getIme());
                ki.getjTextFieldPrezime().setText(ki.getInstruktor().getPrezime());
                ki.getjTextFieldKorisnickoIme().setText(ki.getInstruktor().getKorisnickoIme());
                ki.getjPasswordField().setText(ki.getInstruktor().getSifra());
                ki.getjTextFieldDatumSticanja().setText("");
                ki.getjTextFieldDatumSticanja().setEditable(false);
                ki.getjTextFieldEmail().setEditable(false);
                ki.getjTextFieldIme().setEditable(false);
                ki.getjTextFieldKorisnickoIme().setEditable(false);
                ki.getjComboBoxKvalifikacije().setEnabled(false);
                ki.getjTextFieldPrezime().setEditable(false);
                ki.getjPasswordField().setEditable(false);
                ki.getjComboBoxNivo().setEnabled(false);
                ki.getjButtonObrisiKvalifikaciju().setEnabled(false);
                ki.getjButtonDodajNovuKvalifikaciju().setEnabled(false);
                ki.getjButtonSacuvaj().setVisible(false);
                ki.getjButtonObrisi().setVisible(false);
                ki.getjButtonObrisiInstruktora().setVisible(true);
                ki.getjButtonPromeni().setVisible(true);
                ki.getjButtonPrikaziPromeni().setVisible(false);
                ki.getjButtonPrikaziObrisi().setVisible(false);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(false);
                ki.getjButtonDodaj().setVisible(false);
                ki.getjButtonObrisiKvalifikaciju().setVisible(false);

                List<InstruktorKvalifikacija> lista = ki.getInstruktor().getInstruktorKvalifikacije();
                popuniTabeluKvalifikacijama(lista);
            }

        });
        ki.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                ki.getjTextFieldEmail().setText(ki.getInstruktor().getEmail());
                ki.getjTextFieldIme().setText(ki.getInstruktor().getIme());
                ki.getjTextFieldPrezime().setText(ki.getInstruktor().getPrezime());
                ki.getjTextFieldKorisnickoIme().setText(ki.getInstruktor().getKorisnickoIme());
                ki.getjPasswordField().setText(ki.getInstruktor().getSifra());
                ki.getjTextFieldDatumSticanja().setText("");
                ki.getjTextFieldDatumSticanja().setEditable(false);
                ki.getjTextFieldEmail().setEditable(false);
                ki.getjTextFieldIme().setEditable(false);
                ki.getjTextFieldKorisnickoIme().setEditable(false);
                ki.getjComboBoxKvalifikacije().setEnabled(false);
                ki.getjTextFieldPrezime().setEditable(false);
                ki.getjPasswordField().setEditable(false);
                ki.getjComboBoxNivo().setEnabled(false);
                ki.getjButtonObrisiKvalifikaciju().setEnabled(false);
                ki.getjButtonDodajNovuKvalifikaciju().setEnabled(false);
                ki.getjButtonSacuvaj().setVisible(false);
                ki.getjButtonObrisi().setVisible(false);
                ki.getjButtonObrisiInstruktora().setVisible(true);
                ki.getjButtonPromeni().setVisible(true);
                ki.getjButtonPrikaziPromeni().setVisible(false);
                ki.getjButtonPrikaziObrisi().setVisible(false);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(false);
                ki.getjButtonDodaj().setVisible(false);
                ki.getjButtonObrisiKvalifikaciju().setVisible(false);

                List<InstruktorKvalifikacija> lista = ki.getInstruktor().getInstruktorKvalifikacije();
                popuniTabeluKvalifikacijama(lista);
            }

        });
        ki.PromeniInstruktoraAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniInstruktora();

            }

            private void promeniInstruktora() {
                ki.getjTextFieldDatumSticanja().setEditable(true);
                ki.getjTextFieldEmail().setEditable(true);
                ki.getjTextFieldIme().setEditable(true);
                ki.getjTextFieldKorisnickoIme().setEditable(true);
                ki.getjComboBoxKvalifikacije().setEnabled(true);
                ki.getjTextFieldPrezime().setEditable(true);
                ki.getjPasswordField().setEditable(true);
                ki.getjComboBoxNivo().setEnabled(true);
                ki.getjButtonObrisiKvalifikaciju().setEnabled(true);
                ki.getjButtonDodajNovuKvalifikaciju().setEnabled(true);
                ki.getjButtonSacuvaj().setVisible(true);
                ki.getjButtonObrisi().setVisible(false);
                ki.getjButtonObrisiInstruktora().setVisible(true);
                ki.getjButtonPromeni().setVisible(false);
                ki.getjButtonPrikaziPromeni().setVisible(true);
                ki.getjButtonPrikaziObrisi().setVisible(false);
                ki.getjButtonZapamti().setVisible(false);
                ki.getjButtonOdustani().setVisible(true);
                ki.getjButtonDodaj().setVisible(true);
                ki.getjButtonObrisiKvalifikaciju().setVisible(true);
            }
        });

        pi.VratiListuSvihInstruktoraAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihInstruktora();

            }

            private void vratiListuSvihInstruktora() {
                List<Instruktor> listaInstruktora = new ArrayList<>();

                listaInstruktora = Komunikacija.getInstance().vratiListuSviInstruktor();

                if (listaInstruktora == null || listaInstruktora.isEmpty()) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe sve instruktore.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pi, "Sistem je našao sve instruktore.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleInstruktori mt = (ModelTabeleInstruktori) pi.getjTableInstruktori().getModel();

                if (listaInstruktora.size() > 50) {
                    List<Instruktor> lista = listaInstruktora.subList(0, Math.min(50, listaInstruktora.size()));
                    mt.setInstruktori(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setInstruktori(listaInstruktora);
                    mt.refresujPodatke();
                }
            }

        });

        pi.VratiListuInstruktoraSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuInstruktoraSaUslovom();

            }

            private void vratiListuInstruktoraSaUslovom() {
                List<Instruktor> listaInstruktora = new ArrayList<>();
                String instruktor = null;
                Kvalifikacija kvalifikacija = null;

                if (!pi.getjTextFieldInstruktor().getText().trim().isEmpty()) {
                    instruktor = pi.getjTextFieldInstruktor().getText().trim();
                }

                kvalifikacija = (Kvalifikacija) pi.getjComboBoxKvalifikacija().getSelectedItem();

                if (instruktor == null && kvalifikacija == null) {
                    JOptionPane.showMessageDialog(pi, "Morate uneti bar jedan kriterijum po kome želite da pretražite instruktore.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (instruktor != null && instruktor.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pi, "Možete uneti samo ime i prezime za instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (instruktor != null) {

                    List<Instruktor> lista = Komunikacija.getInstance().vratiListuInstruktorInstruktor(instruktor);

                    for (Instruktor i : lista) {
                        if (!listaInstruktora.contains(i)) {
                            listaInstruktora.add(i);
                        }

                    }
                }

                if (kvalifikacija != null) {

                    List<Instruktor> lista = Komunikacija.getInstance().vratiListuInstruktorKvalifikacija(kvalifikacija);
                    for (Instruktor i : lista) {
                        if (!listaInstruktora.contains(i)) {
                            listaInstruktora.add(i);
                        }

                    }
                }

                if (listaInstruktora.isEmpty()) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe instruktore po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pi, "Sistem je našao instruktore po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleInstruktori mt = (ModelTabeleInstruktori) pi.getjTableInstruktori().getModel();

                if (listaInstruktora.size() > 50) {
                    List<Instruktor> lista = listaInstruktora.subList(0, Math.min(50, listaInstruktora.size()));
                    mt.setInstruktori(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setInstruktori(listaInstruktora);
                    mt.refresujPodatke();
                }
            }
        });

        pi.PrikaziInstruktoraAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziInstruktora();

            }

            private void prikaziInstruktora() {
                int selektovaniRed = pi.getjTableInstruktori().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pi, "Morate izabrati instruktora iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleInstruktori mt = (ModelTabeleInstruktori) pi.getjTableInstruktori().getModel();
                Instruktor instruktor = mt.getInstruktori().get(selektovaniRed);
                Instruktor instruktorDetalji = Komunikacija.getInstance().pretraziInstruktor(instruktor);
                if (instruktorDetalji == null) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne može da nađe instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pi.dispose();
                KontrolerGlavni.getInstance().pokreniKreirajInstruktora(instruktorDetalji);
            }
        });

        ki.DodajNovuKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajNovuKvalifikaciju();

            }

            private void dodajNovuKvalifikaciju() {
                kk = new KvalifikacijaKontroler(new KreirajKvalifikaciju(new GlavnaForma(), true, null), new PretraziKvalifkaciju(new GlavnaForma(), true));
                kk.prikaziFormuKreirajKvalifikaciju(null);
                popuniComboBoxKvalifikacija();
                ki.getjComboBoxKvalifikacije().setSelectedItem(null);

            }
        });

        ki.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(ki, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    ki.dispose();
                }
            }
        });

        ki.DodajKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKvalifikaciju();

            }

            private void dodajKvalifikaciju() {

                Kvalifikacija kvalifikacija = null;
                Nivo nivo = null;
                String datum = null;
                Date datumSticanja = null;
                String dan = null;
                String mesec = null;
                String godina = null;
                int danBr;
                int mesecBr;
                int godinaBr;

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                datumSticanja = new Date();
                try {
                    kvalifikacija = (Kvalifikacija) ki.getjComboBoxKvalifikacije().getSelectedItem();

                    nivo = (Nivo) ki.getjComboBoxNivo().getSelectedItem();
                    datum = ki.getjTextFieldDatumSticanja().getText().trim();

                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(ki, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kvalifikacija == null && nivo == null || datum.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (kvalifikacija == null) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli kvalifikaciju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else if (nivo == null) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli nivo kvalifikacije.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else if (datum.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli datum sticanja kvalifikacije.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }

                try {

                    String[] datumNiz = datum.split("\\.");
                    dan = datumNiz[0];
                    mesec = datumNiz[1];
                    godina = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    danBr = Integer.parseInt(dan);
                    mesecBr = Integer.parseInt(mesec);
                    godinaBr = Integer.parseInt(godina);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ki, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (danBr < 1 || danBr > 31 || mesecBr < 1 || mesecBr > 12) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesecBr == 4 || mesecBr == 6 || mesecBr == 9 || mesecBr == 11) {
                    if (danBr > 30) {
                        JOptionPane.showMessageDialog(ki, "Uneti mesec ima 30 dana.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godinaBr % 4 == 0) {
                    if (mesecBr == 2) {
                        if (danBr > 29) {
                            JOptionPane.showMessageDialog(ki, "Februar ima 29 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesecBr == 2) {
                        if (danBr > 28) {
                            JOptionPane.showMessageDialog(ki, "Februar ima 28 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                try {
                    if (!datum.trim().isEmpty()) {

                        datumSticanja = format.parse(datum);
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ki, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumSticanja.after(new Date())) {
                    JOptionPane.showMessageDialog(ki, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleInstruktorKvalifikacije mt = (ModelTabeleInstruktorKvalifikacije) ki.getjTableKvalifikacija().getModel();
                List<InstruktorKvalifikacija> lista = mt.getKvalifikacije();

                InstruktorKvalifikacija ik = new InstruktorKvalifikacija(null, kvalifikacija, datumSticanja, nivo);
                if (lista.contains(ik)) {

                    JOptionPane.showMessageDialog(ki, "Ova kvalifikacija je već dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                } else {
                    mt.dodajElement(ik);
                }

            }
        });

        ki.ObrisiKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKvalifikaciju();

            }

            private void obrisiKvalifikaciju() {
                int selektovaniRed = ki.getjTableKvalifikacija().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(ki, "Morate izabrati kvalifikaciju koju želite da obrišete.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleInstruktorKvalifikacije mt = (ModelTabeleInstruktorKvalifikacije) ki.getjTableKvalifikacija().getModel();
                mt.getKvalifikacije().remove(selektovaniRed);

                mt.refresujPodatke();
            }
        });

        ki.PrikaziSifruAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziSifru();

            }

            private void prikaziSifru() {

                if (!ki.getPrikazi()) {
                    ki.getjButtonPrikaziSifru().setText("Prikaži šifru");
                    ki.getjPasswordField().setEchoChar('\u2022');

                } else {

                    ki.getjButtonPrikaziSifru().setText("Sakrij šifru");

                    ki.getjPasswordField().setEchoChar((char) 0);

                }
                boolean p = !ki.getPrikazi();
                ki.setPrikazi(p);
            }
        });

        pi.RadioIDAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioID();

            }

            private void radioID() {
                pi.getjTextFieldInstruktor().setText("");
                pi.getjComboBoxKvalifikacija().setSelectedItem(null);
                pi.getjComboBoxKvalifikacija().setEnabled(false);
                pi.getjTextFieldInstruktor().setEditable(true);
            }

        });

        pi.RadioKvalifikacijaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioKvalifikacija();

            }

            private void radioKvalifikacija() {
                pi.getjTextFieldInstruktor().setText("");
                pi.getjComboBoxKvalifikacija().setSelectedItem(null);
                pi.getjComboBoxKvalifikacija().setEnabled(true);
                pi.getjTextFieldInstruktor().setEditable(false);
            }
        });

    }

    public void prikaziFormuKreirajInstruktora(Instruktor instruktor) {

        if (instruktor == null) {
            popuniComboBoxNivo();
            popuniComboBoxKvalifikacija();

            ki.getjButtonPrikaziSifru().setVisible(true);
            ki.getjComboBoxNivo().setSelectedItem(null);
            ki.getjComboBoxKvalifikacije().setSelectedItem(null);
            ki.getjButtonSacuvaj().setVisible(false);
            ki.getjButtonObrisi().setVisible(false);
            ki.getjButtonObrisiInstruktora().setVisible(false);
            ki.getjButtonPromeni().setVisible(false);
            ki.getjButtonPrikaziPromeni().setVisible(false);
            ki.getjButtonPrikaziObrisi().setVisible(false);
            ki.getjButtonZapamti().setVisible(true);
            ki.getjButtonOdustani().setVisible(true);
            ki.getjButtonDodaj().setVisible(true);
            ki.getjButtonObrisiKvalifikaciju().setVisible(true);
            popuniTabeluKvalifikacijama(new ArrayList<>());
            JOptionPane.showMessageDialog(ki, "Sistem je kreirao instruktora.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ki.setVisible(true);

        } else {
            popuniComboBoxNivo();
            popuniComboBoxKvalifikacija();

            ki.getjButtonPrikaziSifru().setVisible(true);
            ki.getjComboBoxNivo().setSelectedItem(null);
            ki.getjComboBoxKvalifikacije().setSelectedItem(null);
            ki.getjTextFieldEmail().setText(instruktor.getEmail());
            ki.getjTextFieldIme().setText(instruktor.getIme());
            ki.getjTextFieldPrezime().setText(instruktor.getPrezime());
            ki.getjTextFieldKorisnickoIme().setText(instruktor.getKorisnickoIme());
            ki.getjPasswordField().setText(instruktor.getSifra());
            ki.getjTextFieldDatumSticanja().setEditable(false);
            ki.getjTextFieldEmail().setEditable(false);
            ki.getjTextFieldIme().setEditable(false);
            ki.getjTextFieldKorisnickoIme().setEditable(false);
            ki.getjComboBoxKvalifikacije().setEnabled(false);
            ki.getjTextFieldPrezime().setEditable(false);
            ki.getjPasswordField().setEditable(false);
            ki.getjComboBoxNivo().setEnabled(false);
            ki.getjButtonObrisiKvalifikaciju().setEnabled(false);
            ki.getjButtonDodajNovuKvalifikaciju().setEnabled(false);
            ki.getjButtonSacuvaj().setVisible(false);
            ki.getjButtonObrisi().setVisible(false);
            ki.getjButtonObrisiInstruktora().setVisible(true);
            ki.getjButtonPromeni().setVisible(true);
            ki.getjButtonPrikaziPromeni().setVisible(false);
            ki.getjButtonPrikaziObrisi().setVisible(false);
            ki.getjButtonZapamti().setVisible(false);
            ki.getjButtonOdustani().setVisible(false);
            ki.getjButtonDodaj().setVisible(false);
            ki.getjButtonObrisiKvalifikaciju().setVisible(false);

            kvalifikacije = instruktor.getInstruktorKvalifikacije();
            popuniTabeluKvalifikacijama(kvalifikacije);
            JOptionPane.showMessageDialog(ki, "Sistem je našao instruktora.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ki.setVisible(true);

        }

    }

    private void popuniTabeluKvalifikacijama(List<InstruktorKvalifikacija> lista) {
        ModelTabeleInstruktorKvalifikacije modelTabele = new ModelTabeleInstruktorKvalifikacije(lista);
        modelTabele.refresujPodatke();
        ki.getjTableKvalifikacija().setModel(modelTabele);
    }

    private void popuniComboBoxKvalifikacija() {

        List<Kvalifikacija> listaKvalifikacija = Komunikacija.getInstance().vratiListuSviKvalifikacija();

        ki.getjComboBoxKvalifikacije().removeAllItems();

        for (Kvalifikacija kv : listaKvalifikacija) {
            ki.getjComboBoxKvalifikacije().addItem(kv);

        }
    }

    private void popuniComboBoxNivo() {
        for (Nivo vrednost : Nivo.values()) {
            ki.getjComboBoxNivo().addItem(vrednost);

        }
    }

    public void prikaziFormuPretraziInstruktora() {
        pi.getjRadioButtonID().setSelected(true);
        pi.getjTextFieldInstruktor().setEditable(true);
        popuniComboBox();
        pi.getjComboBoxKvalifikacija().setSelectedItem(null);
        pi.getjComboBoxKvalifikacija().setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(pi.getjRadioButtonID());
        group.add(pi.getjRadioButtonKvalifikacija());
        popuniTabeluInstruktora();
        pi.setVisible(true);
    }

    private void popuniComboBox() {

        List<Kvalifikacija> lista = Komunikacija.getInstance().vratiListuSviKvalifikacija();

        pi.getjComboBoxKvalifikacija().removeAllItems();

        for (Kvalifikacija kvalifikacija : lista) {
            pi.getjComboBoxKvalifikacija().addItem(kvalifikacija);

        }
    }

    private void popuniTabeluInstruktora() {
        List<Instruktor> instruktori = new ArrayList<>();
        ModelTabeleInstruktori modelTabele = new ModelTabeleInstruktori(instruktori);
        pi.getjTableInstruktori().setModel(modelTabele);

    }

}
