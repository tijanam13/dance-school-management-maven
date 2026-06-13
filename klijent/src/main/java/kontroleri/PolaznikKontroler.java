/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajPolaznika;
import forme.PretraziPolaznika;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.ModelTabelePolaznici;
import model.Polaznik;
import model.UzrasnaKategorija;

/**
 *
 * @author Tijana
 */
public class PolaznikKontroler {

    private final KreirajPolaznika kp;
    private final PretraziPolaznika pp;

    public PolaznikKontroler(KreirajPolaznika kp, PretraziPolaznika pp) {
        this.kp = kp;
        this.pp = pp;
        addActionListeners();
    }

    private void addActionListeners() {
        kp.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                String ime = null;
                String prezime = null;
                String email = null;
                UzrasnaKategorija uzrast = null;

                try {

                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    email = kp.getjTextFieldEmail().getText().trim();
                    uzrast = (UzrasnaKategorija) kp.getjComboBoxUzrast().getSelectedItem();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || email.trim().isEmpty() || uzrast == null) {

                    JOptionPane.showMessageDialog(kp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || email.trim().length() > 50) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                if (!email.contains("@")) {

                    JOptionPane.showMessageDialog(kp, "E-mail mora da sadrži znak '@'.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }
                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                if (!ispravnoIme) {
                    JOptionPane.showMessageDialog(kp, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoPrezime) {
                    JOptionPane.showMessageDialog(kp, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Polaznik polaznik = new Polaznik(ime, prezime, email, uzrast);

                boolean uspesno = Komunikacija.getInstance().kreirajPolaznik(polaznik);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(kp, "Sistem ne može da zapamti polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kp, "Sistem je zapamtio polaznika.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kp.dispose();
                }
            }

        });

        kp.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                String ime = null;
                String prezime = null;
                String email = null;
                UzrasnaKategorija uzrast = null;

                try {

                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    email = kp.getjTextFieldEmail().getText().trim();
                    uzrast = (UzrasnaKategorija) kp.getjComboBoxUzrast().getSelectedItem();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.trim().isEmpty() || prezime.trim().isEmpty() || email.trim().isEmpty() || uzrast == null) {

                    JOptionPane.showMessageDialog(kp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ime.trim().length() > 30 || prezime.trim().length() > 30 || email.trim().length() > 50) {

                    if (ime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (prezime.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kp, "Prezime može sadržati maksimalno 30 slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (email.trim().length() > 50) {
                        JOptionPane.showMessageDialog(kp, "Ime može sadržati maksimalno 50 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                if (!email.contains("@")) {

                    JOptionPane.showMessageDialog(kp, "E-mail mora da sadrži znak '@'.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }
                boolean ispravnoIme = true;
                for (int i = 0; i < ime.length(); i++) {
                    if (!Character.isLetter(ime.charAt(i))) {
                        ispravnoIme = false;
                    }

                }

                if (!ispravnoIme) {
                    JOptionPane.showMessageDialog(kp, "Ime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean ispravnoPrezime = true;
                for (int i = 0; i < prezime.length(); i++) {
                    if (!Character.isLetter(prezime.charAt(i))) {
                        ispravnoPrezime = false;
                    }

                }

                if (!ispravnoPrezime) {
                    JOptionPane.showMessageDialog(kp, "Prezime može sadržati samo slova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Polaznik p = new Polaznik(kp.getPolaznik().getIdPolaznik(), ime, prezime, email, uzrast);

                int odgovor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniPolaznik(p);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kp, "Sistem ne može da zapamti polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kp, "Sistem je zapamtio polaznika.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kp.dispose();
                    }
                }
            }

        });

        kp.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da obrišete polaznika?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String ime = null;
                    String prezime = null;
                    String email = null;
                    UzrasnaKategorija uzrast = null;

                    ime = kp.getjTextFieldIme().getText().trim();
                    prezime = kp.getjTextFieldPrezime().getText().trim();
                    email = kp.getjTextFieldEmail().getText().trim();
                    uzrast = (UzrasnaKategorija) kp.getjComboBoxUzrast().getSelectedItem();

                    Polaznik p = new Polaznik(kp.getPolaznik().getIdPolaznik(), ime, prezime, email, uzrast);

                    boolean uspesno = Komunikacija.getInstance().obrisiPolaznik(p);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kp, "Sistem ne može da obriše polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kp, "Sistem je obrisao polaznika.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kp.dispose();
                    }
                }
            }
        });

        pp.VratiListuSvihPolaznikaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihPolaznika();

            }

            private void vratiListuSvihPolaznika() {
                List<Polaznik> listaPolaznika = Komunikacija.getInstance().vratiListuSviPolaznik();

                if (listaPolaznika == null || listaPolaznika.isEmpty()) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe sve polaznike.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pp, "Sistem je našao sve polaznike.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabelePolaznici mt = (ModelTabelePolaznici) pp.getjTablePolaznici().getModel();

                if (listaPolaznika.size() > 50) {
                    List<Polaznik> lista = listaPolaznika.subList(0, Math.min(50, listaPolaznika.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaPolaznika);
                    mt.refresujPodatke();
                }
            }

        });

        pp.VratiListuPolaznikaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuPolaznikaSaUslovom();

            }

            private void vratiListuPolaznikaSaUslovom() {
                List<Polaznik> listaPolaznika = new ArrayList<>();
                String polaznik = null;
                String uzrast = null;

                try {

                    if (!pp.getjTextFieldPolaznik().getText().trim().isEmpty()) {
                        polaznik = pp.getjTextFieldPolaznik().getText().trim();
                    }

                    if (!pp.getjTextFieldUzrast().getText().trim().isEmpty()) {
                        uzrast = pp.getjTextFieldUzrast().getText().trim();
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (polaznik == null && uzrast == null) {
                    JOptionPane.showMessageDialog(pp, "Morate uneti bar jedan kriterijum po kome želite da pretražite polaznike.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (polaznik != null) {

                    List<Polaznik> lista = Komunikacija.getInstance().vratiListuPolaznikPolaznik(polaznik);

                    for (Polaznik p : lista) {
                        if (!listaPolaznika.contains(p)) {
                            listaPolaznika.add(p);
                        }

                    }
                }

                if (uzrast != null && uzrast.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pp, "Možete uneti samo opseg godina i naziv kategorije za uzrasnu kategoriju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (uzrast != null) {

                    List<Polaznik> lista = Komunikacija.getInstance().vratiListuPolaznikUzrasnaKategorija(uzrast);

                    for (Polaznik p : lista) {
                        if (!listaPolaznika.contains(p)) {
                            listaPolaznika.add(p);
                        }

                    }
                }

                if (listaPolaznika == null || listaPolaznika.isEmpty()) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe polaznike po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pp, "Sistem je našao polaznike po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                ModelTabelePolaznici mt = (ModelTabelePolaznici) pp.getjTablePolaznici().getModel();

                if (listaPolaznika.size() > 50) {
                    List<Polaznik> lista = listaPolaznika.subList(0, Math.min(50, listaPolaznika.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaPolaznika);
                    mt.refresujPodatke();
                }
            }
        });

        pp.PrikaziPolaznikaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPolaznika();

            }

            private void prikaziPolaznika() {
                int selektovaniRed = pp.getjTablePolaznici().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pp, "Morate izabrati upisnicu iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabelePolaznici mt = (ModelTabelePolaznici) pp.getjTablePolaznici().getModel();
                Polaznik polaznik = mt.getLista().get(selektovaniRed);
                Polaznik polaznikDetalji = Komunikacija.getInstance().pretraziPolaznik(polaznik);
                if (polaznikDetalji == null) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pp.dispose();
                try {
                    KontrolerGlavni.getInstance().pokreniKreirajPolaznika(polaznikDetalji);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pp, "Sistem ne može da nađe polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        kp.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kp, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kp.dispose();
                }
            }

        });

        kp.PromeniPolaznikaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                kp.getjButtonSacuvaj().setVisible(true);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(true);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiPolaznika().setVisible(true);
                kp.getjButtonPromeniPolaznika().setVisible(false);
                kp.getjComboBoxUzrast().setSelectedItem(null);
                kp.getjTextFieldEmail().setText(kp.getPolaznik().getEmail());
                kp.getjTextFieldIme().setText(kp.getPolaznik().getIme());
                kp.getjTextFieldPrezime().setText(kp.getPolaznik().getPrezime());
                kp.getjComboBoxUzrast().setSelectedItem(kp.getPolaznik().getUzrasnaKategorija());
                kp.getjComboBoxUzrast().setEnabled(true);
                kp.getjTextFieldEmail().setEditable(true);
                kp.getjTextFieldIme().setEditable(true);
                kp.getjTextFieldPrezime().setEditable(true);
                kp.getjButtonPrikaziPromeni().setVisible(true);
                kp.getjButtonPrikaziObrisi().setVisible(false);
            }

        });

        kp.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(false);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiPolaznika().setVisible(true);
                kp.getjButtonPromeniPolaznika().setVisible(true);
                kp.getjComboBoxUzrast().setSelectedItem(null);
                kp.getjTextFieldEmail().setText(kp.getPolaznik().getEmail());
                kp.getjTextFieldIme().setText(kp.getPolaznik().getIme());
                kp.getjTextFieldPrezime().setText(kp.getPolaznik().getPrezime());
                kp.getjComboBoxUzrast().setSelectedItem(kp.getPolaznik().getUzrasnaKategorija());
                kp.getjComboBoxUzrast().setEnabled(false);
                kp.getjTextFieldEmail().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(false);
            }

        });

        kp.ObrisiPolaznikaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiPolaznika();

            }

            private void obrisiPolaznika() {
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(true);
                kp.getjButtonObrisi().setVisible(true);
                kp.getjButtonObrisiPolaznika().setVisible(false);
                kp.getjButtonPromeniPolaznika().setVisible(true);
                kp.getjComboBoxUzrast().setSelectedItem(null);
                kp.getjTextFieldEmail().setText(kp.getPolaznik().getEmail());
                kp.getjTextFieldIme().setText(kp.getPolaznik().getIme());
                kp.getjTextFieldPrezime().setText(kp.getPolaznik().getPrezime());
                kp.getjComboBoxUzrast().setSelectedItem(kp.getPolaznik().getUzrasnaKategorija());
                kp.getjComboBoxUzrast().setEnabled(false);
                kp.getjTextFieldEmail().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(true);
            }

        });

        kp.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kp.getjButtonSacuvaj().setVisible(false);
                kp.getjButtonZapamti().setVisible(false);
                kp.getjButtonOdustani().setVisible(false);
                kp.getjButtonObrisi().setVisible(false);
                kp.getjButtonObrisiPolaznika().setVisible(true);
                kp.getjButtonPromeniPolaznika().setVisible(true);
                kp.getjComboBoxUzrast().setSelectedItem(null);
                kp.getjTextFieldEmail().setText(kp.getPolaznik().getEmail());
                kp.getjTextFieldIme().setText(kp.getPolaznik().getIme());
                kp.getjTextFieldPrezime().setText(kp.getPolaznik().getPrezime());
                kp.getjComboBoxUzrast().setSelectedItem(kp.getPolaznik().getUzrasnaKategorija());
                kp.getjComboBoxUzrast().setEnabled(false);
                kp.getjTextFieldEmail().setEditable(false);
                kp.getjTextFieldIme().setEditable(false);
                kp.getjTextFieldPrezime().setEditable(false);
                kp.getjButtonPrikaziPromeni().setVisible(false);
                kp.getjButtonPrikaziObrisi().setVisible(false);
            }

        });

        pp.RadioUzrastAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioUzrast();

            }

            private void radioUzrast() {
                pp.getjTextFieldPolaznik().setText("");
                pp.getjTextFieldUzrast().setText("");
                pp.getjTextFieldUzrast().setEditable(true);
                pp.getjTextFieldPolaznik().setEditable(false);
            }
        });

        pp.RadioIDAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioID();

            }

            private void radioID() {
                pp.getjTextFieldPolaznik().setText("");
                pp.getjTextFieldUzrast().setText("");
                pp.getjTextFieldUzrast().setEditable(false);
                pp.getjTextFieldPolaznik().setEditable(true);
            }
        });
    }

    public void prikaziFormuKreirajPolaznika(Polaznik polaznik) {

        if (polaznik == null) {

            popuniUzrast();
            kp.getjComboBoxUzrast().setSelectedItem(null);
            kp.getjButtonSacuvaj().setVisible(false);
            kp.getjButtonObrisi().setVisible(false);
            kp.getjButtonZapamti().setVisible(true);
            kp.getjButtonOdustani().setVisible(true);
            kp.getjButtonObrisiPolaznika().setVisible(false);
            kp.getjButtonPromeniPolaznika().setVisible(false);
            kp.getjButtonPrikaziPromeni().setVisible(false);
            kp.getjButtonPrikaziObrisi().setVisible(false);
            JOptionPane.showMessageDialog(kp, "Sistem je kreirao polaznika.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kp.setVisible(true);

        } else {

            kp.getjButtonSacuvaj().setVisible(false);
            kp.getjButtonZapamti().setVisible(false);
            kp.getjButtonOdustani().setVisible(false);
            kp.getjButtonObrisi().setVisible(false);
            kp.getjButtonObrisiPolaznika().setVisible(true);
            kp.getjButtonPromeniPolaznika().setVisible(true);
            popuniUzrast();
            kp.getjComboBoxUzrast().setSelectedItem(null);
            kp.getjTextFieldEmail().setText(polaznik.getEmail());
            kp.getjTextFieldIme().setText(polaznik.getIme());
            kp.getjTextFieldPrezime().setText(polaznik.getPrezime());
            kp.getjComboBoxUzrast().setSelectedItem(polaznik.getUzrasnaKategorija());
            kp.getjComboBoxUzrast().setEnabled(false);
            kp.getjTextFieldEmail().setEditable(false);
            kp.getjTextFieldIme().setEditable(false);
            kp.getjTextFieldPrezime().setEditable(false);
            kp.getjButtonPrikaziPromeni().setVisible(false);
            JOptionPane.showMessageDialog(kp, "Sistem je našao polaznika.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kp.setVisible(true);
        }

    }

    private void popuniUzrast() {

        List<UzrasnaKategorija> listaUzrasnihKategorija = Komunikacija.getInstance().vratiListuSviUzrasnaKategorija();

        kp.getjComboBoxUzrast().removeAllItems();

        for (UzrasnaKategorija uzrast : listaUzrasnihKategorija) {
            kp.getjComboBoxUzrast().addItem(uzrast);

        }

    }

    public void prikaziFormuPretraziPolaznika() {
        pp.getjRadioButtonID().setSelected(true);
        pp.getjTextFieldPolaznik().setEditable(true);
        pp.getjTextFieldUzrast().setEditable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(pp.getjRadioButtonID());
        group.add(pp.getjRadioButtonUzrast());
        popuniTabelu();
        pp.setVisible(true);
    }

    private void popuniTabelu() {
        List<Polaznik> polaznici = new ArrayList<>();
        ModelTabelePolaznici modelTabele = new ModelTabelePolaznici(polaznici);
        pp.getjTablePolaznici().setModel(modelTabele);
    }

}
