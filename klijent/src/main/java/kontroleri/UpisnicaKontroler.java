/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajUpisnicu;
import forme.PretraziUpisnicu;
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
import model.ModelTabeleStavkaUpisnice;
import model.ModelTabeleUpisnice;
import model.Polaznik;
import model.StavkaUpisnice;
import model.Upisnica;
import model.VrstaPlesa;
import servis.JsonServis;

/**
 *
 * @author Tijana
 */
public class UpisnicaKontroler {

    private final KreirajUpisnicu ku;
    private final PretraziUpisnicu pu;

    public UpisnicaKontroler(KreirajUpisnicu ku, PretraziUpisnicu pu) {
        this.ku = ku;
        this.pu = pu;
        addActionListeners();

    }

    private void addActionListeners() {
        ku.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {

                String datum = null;
                Instruktor instruktor = null;
                Polaznik polaznik = null;
                double ukupnaClanarina = 0.0;
                List<StavkaUpisnice> stavke = new ArrayList<>();
                Date datumUpisa = null;
                String dan = null;
                String mesec = null;
                String godina = null;
                int danBr;
                int mesecBr;
                int godinaBr;

                try {

                    instruktor = (Instruktor) ku.getjComboBoxInstruktor().getSelectedItem();
                    polaznik = (Polaznik) ku.getjComboBoxPolaznik().getSelectedItem();
                    ukupnaClanarina = Double.parseDouble(ku.getjTextFieldUkupnaClanarina().getText().trim());
                    datum = ku.getjTextFieldDatumUpisa().getText().trim();

                    ModelTabeleStavkaUpisnice mt = (ModelTabeleStavkaUpisnice) ku.getjTableStavke().getModel();
                    stavke = mt.getStavke();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ku, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (instruktor == null || polaznik == null || stavke.isEmpty() || datum == null || datum.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(ku, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {

                    String[] datumNiz = datum.split("\\.");
                    dan = datumNiz[0];
                    mesec = datumNiz[1];
                    godina = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    danBr = Integer.parseInt(dan);
                    mesecBr = Integer.parseInt(mesec);
                    godinaBr = Integer.parseInt(godina);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ku, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (danBr < 1 || danBr > 31 || mesecBr < 1 || mesecBr > 12) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesecBr == 4 || mesecBr == 6 || mesecBr == 9 || mesecBr == 11) {
                    if (danBr > 30) {
                        JOptionPane.showMessageDialog(ku, "Uneti mesec ima 30 dana.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godinaBr % 4 == 0) {
                    if (mesecBr == 2) {
                        if (danBr > 29) {
                            JOptionPane.showMessageDialog(ku, "Februar ima 29 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesecBr == 2) {
                        if (danBr > 28) {
                            JOptionPane.showMessageDialog(ku, "Februar ima 28 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                datumUpisa = new Date();

                try {
                    if (!datum.trim().isEmpty()) {
                        datumUpisa = format.parse(datum);
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumUpisa.after(new Date())) {
                    JOptionPane.showMessageDialog(ku, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (ukupnaClanarina <= 0) {

                    JOptionPane.showMessageDialog(ku, "Članarina mora biti veća od 0.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }

                Date datumZaBazu = new java.sql.Date(datumUpisa.getTime());

                Upisnica upisnica = null;
                try {
                    upisnica = new Upisnica(datumZaBazu, ukupnaClanarina, instruktor, polaznik);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ku, "Greška pri kreiranju upisnice.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }                for (StavkaUpisnice stavka : stavke) {
                    stavka.setUpisnica(upisnica);

                }
                upisnica.setListaStavke(stavke);

                boolean uspesno = Komunikacija.getInstance().kreirajUpisnica(upisnica);

                if (!uspesno) {
                    JOptionPane.showMessageDialog(ku, "Sistem ne može da zapamti upisnicu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                } else {

                    JOptionPane.showMessageDialog(ku, "Sistem je zapamtio upisnicu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ku.dispose();
                }
            }

        });

        ku.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {

                String datum = null;
                Date datumUpisa = null;
                Instruktor instruktor = null;
                Polaznik polaznik = null;
                double ukupnaClanarina = 0.0;
                List<StavkaUpisnice> stavke = new ArrayList<>();
                String dan = null;
                String mesec = null;
                String godina = null;
                int danBr;
                int mesecBr;
                int godinaBr;

                try {

                    instruktor = (Instruktor) ku.getjComboBoxInstruktor().getSelectedItem();
                    polaznik = (Polaznik) ku.getjComboBoxPolaznik().getSelectedItem();
                    ukupnaClanarina = Double.parseDouble(ku.getjTextFieldUkupnaClanarina().getText().trim());
                    datum = ku.getjTextFieldDatumUpisa().getText().trim();

                    ModelTabeleStavkaUpisnice mt = (ModelTabeleStavkaUpisnice) ku.getjTableStavke().getModel();
                    stavke = mt.getStavke();

                    if (instruktor == null || polaznik == null || stavke == null || ukupnaClanarina == 0.0 || datum == null || datum.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(ku, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {

                        String[] datumNiz = datum.split("\\.");
                        dan = datumNiz[0];
                        mesec = datumNiz[1];
                        godina = datumNiz[2];
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        danBr = Integer.parseInt(dan);
                        mesecBr = Integer.parseInt(mesec);
                        godinaBr = Integer.parseInt(godina);
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(ku, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (danBr < 1 || danBr > 31 || mesecBr < 1 || mesecBr > 12) {
                        JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (mesecBr == 4 || mesecBr == 6 || mesecBr == 9 || mesecBr == 11) {
                        if (danBr > 30) {
                            JOptionPane.showMessageDialog(ku, "Uneti mesec ima 30 dana.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    if (godinaBr % 4 == 0) {
                        if (mesecBr == 2) {
                            if (danBr > 29) {
                                JOptionPane.showMessageDialog(ku, "Februar ima 29 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        }
                    } else {
                        if (mesecBr == 2) {
                            if (danBr > 28) {
                                JOptionPane.showMessageDialog(ku, "Februar ima 28 dana u unetoj godini.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        }

                    }

                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                    datumUpisa = new Date();

                    try {
                        if (!ku.getjTextFieldDatumUpisa().getText().trim().isEmpty()) {
                            datumUpisa = format.parse(datum);
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(ku, "Niste uneli datum u ispravnom formatu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(ku, "Greška pri unosu ili obradi podataka.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (datumUpisa != null && datumUpisa.after(new Date())) {
                    JOptionPane.showMessageDialog(ku, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ukupnaClanarina <= 0) {

                    JOptionPane.showMessageDialog(ku, "Članarina mora biti veća od 0.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }

                Date datumZaBazu = new java.sql.Date(datumUpisa.getTime());

                Upisnica u = null;
                try {
                    u = new Upisnica(ku.getUpisnica().getIdUpisnica(), datumZaBazu, ukupnaClanarina, instruktor, polaznik);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ku, "Greška pri kreiranju upisnice.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }                u.setListaStavke(stavke);

                int odluka = JOptionPane.showConfirmDialog(ku, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odluka == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniUpisnica(u);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ku, "Sistem ne može da zapamti upisnicu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(ku, "Sistem je zapamtio upisnicu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ku.dispose();

                    }
                }
            }
        });

        pu.VratiListuSvihUpisnicaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihUpisnica();

            }

            private void vratiListuSvihUpisnica() {
                List<Upisnica> listaUpisnica = Komunikacija.getInstance().vratiListuSviUpisnica();

                if (listaUpisnica == null || listaUpisnica.isEmpty()) {
                    JOptionPane.showMessageDialog(pu, "Sistem ne može da nađe sve upisnice.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pu, "Sistem je našao sve upisnice.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleUpisnice mt = (ModelTabeleUpisnice) pu.getjTableUpisnice().getModel();

                if (listaUpisnica.size() > 50) {
                    List<Upisnica> lista = listaUpisnica.subList(0, Math.min(50, listaUpisnica.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaUpisnica);
                    mt.refresujPodatke();
                }
            }

        });

        pu.VratiListuUpisnicaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuUpisnicaSaUslovom();

            }

            private void vratiListuUpisnicaSaUslovom() {
                List<Upisnica> listaUpisnica = new ArrayList<>();
                int ID = Integer.MIN_VALUE;
                String instruktor = null;
                String polaznik = null;
                String naziv = null;
                String kategorija = null;

                try {

                    if (!pu.getjTextFieldID().getText().trim().isEmpty()) {
                        ID = Integer.parseInt(pu.getjTextFieldID().getText().trim());
                    }

                    if (!pu.getjTextFieldInstruktor().getText().trim().isEmpty()) {
                        instruktor = pu.getjTextFieldInstruktor().getText().trim();
                    }
                    if (!pu.getjTextFieldPolaznik().getText().trim().isEmpty()) {
                        polaznik = pu.getjTextFieldPolaznik().getText().trim();
                    }
                    if (!pu.getjTextFieldNaziv().getText().trim().isEmpty()) {
                        naziv = pu.getjTextFieldNaziv().getText().trim();
                    }
                    if (!pu.getjTextFieldKategorija().getText().trim().isEmpty()) {
                        kategorija = pu.getjTextFieldKategorija().getText().trim();
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pu, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ID == Integer.MIN_VALUE && instruktor == null && polaznik == null && naziv == null && kategorija == null) {
                    JOptionPane.showMessageDialog(pu, "Morate uneti bar jedan kriterijum po kome želite da pretražite upisnice.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ID != Integer.MIN_VALUE) {

                    List<Upisnica> lista = Komunikacija.getInstance().vratiListuUpisnicaID(ID);

                    if (lista != null && !lista.isEmpty()) {
                        for (Upisnica upisnica : lista) {
                            if (!listaUpisnica.contains(upisnica)) {
                                listaUpisnica.add(upisnica);
                            }

                        }
                    }
                }

                if (instruktor != null && instruktor.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pu, "Možete uneti samo ime i prezime za instruktora.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (polaznik != null && polaznik.strip().split(" ").length >= 3) {
                    JOptionPane.showMessageDialog(pu, "Možete uneti samo ime i prezime za polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (instruktor != null) {
                    List<Upisnica> lista = Komunikacija.getInstance().vratiListuUpisnicaInstruktor(instruktor);

                    if (lista != null && !lista.isEmpty()) {
                        for (Upisnica upisnica : lista) {
                            if (!listaUpisnica.contains(upisnica)) {
                                listaUpisnica.add(upisnica);
                            }

                        }
                    }
                }
                if (polaznik != null) {
                    List<Upisnica> lista = Komunikacija.getInstance().vratiListuUpisnicaPolaznik(polaznik);

                    if (lista != null && !lista.isEmpty()) {
                        for (Upisnica upisnica : lista) {
                            if (!listaUpisnica.contains(upisnica)) {
                                listaUpisnica.add(upisnica);
                            }

                        }
                    }
                }
                if (naziv != null || kategorija != null) {
                    VrstaPlesa vp = new VrstaPlesa(naziv, kategorija);

                    List<Upisnica> lista = Komunikacija.getInstance().vratiListuUpisnicaVrstaPlesa(vp);

                    if (lista != null && !lista.isEmpty()) {
                        for (Upisnica upisnica : lista) {
                            if (!listaUpisnica.contains(upisnica)) {
                                listaUpisnica.add(upisnica);
                            }

                        }
                    }

                }

                if (listaUpisnica.isEmpty()) {
                    JOptionPane.showMessageDialog(pu, "Sistem ne može da nađe upisnice po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pu, "Sistem je našao upisnice po zadatim kriterijumima.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleUpisnice mt = (ModelTabeleUpisnice) pu.getjTableUpisnice().getModel();

                if (listaUpisnica.size() > 50) {
                    List<Upisnica> lista = listaUpisnica.subList(0, Math.min(50, listaUpisnica.size()));
                    mt.setLista(lista);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(listaUpisnica);
                    mt.refresujPodatke();
                }

            }

        });

        pu.PrikaziUpisnicuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziUpisnicu();

            }

            private void prikaziUpisnicu() {

                int selektovaniRed = pu.getjTableUpisnice().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pu, "Morate izabrati upisnicu iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleUpisnice mt = (ModelTabeleUpisnice) pu.getjTableUpisnice().getModel();
                Upisnica upisnica = mt.getLista().get(selektovaniRed);
                Upisnica upisnicaDetalji = Komunikacija.getInstance().pretraziUpisnica(upisnica);
                if (upisnicaDetalji == null) {
                    JOptionPane.showMessageDialog(pu, "Sistem ne može da nađe upisnicu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                }

                pu.dispose();
                try {
                    KontrolerGlavni.getInstance().pokreniKreirajUpisnicu(upisnicaDetalji);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pu, "Sistem ne može da nađe upisnicu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        ku.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {

                int izbor = JOptionPane.showConfirmDialog(ku, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    ku.dispose();
                }

            }
        });

        ku.PromeniUpisnicuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {

                ku.getjTextFieldDatumUpisa().setEditable(true);
                ku.getjComboBoxBrojCasova().setEnabled(true);
                ku.getjComboBoxInstruktor().setEnabled(true);
                ku.getjComboBoxPolaznik().setEnabled(true);
                ku.getjComboBoxVrstaPlesa().setEnabled(true);
                ku.getjButtonDodajStavku().setVisible(true);
                ku.getjButtonSacuvaj().setVisible(true);
                ku.getjButtonZapamti().setVisible(false);
                ku.getjButtonOdustani().setVisible(true);
                ku.getjButtonObrisiStavku().setVisible(true);
                ku.getjButtonPrikazi().setVisible(true);
                ku.getjButtonPromeni().setVisible(false);

            }
        });

        ku.PrikaziAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikazi();

            }

            private void prikazi() {
                String datumNovi = null;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                    datumNovi = format.format(ku.getUpisnica().getDatumUpisa());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ku, "Greška prilikom obrade datuma.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ku.getjTextFieldDatumUpisa().setText(datumNovi);
                ku.getjTextFieldUkupnaClanarina().setText(String.valueOf(ku.getUpisnica().getUkupnaClanarina()));
                ku.getjComboBoxInstruktor().setSelectedItem(ku.getUpisnica().getInstruktor());
                ku.getjComboBoxPolaznik().setSelectedItem(ku.getUpisnica().getPolaznik());
                ku.getjComboBoxVrstaPlesa().setSelectedItem(null);
                ku.getjTextFieldDatumUpisa().setEditable(false);
                ku.getjComboBoxBrojCasova().setEnabled(false);
                ku.getjComboBoxInstruktor().setEnabled(false);
                ku.getjComboBoxPolaznik().setEnabled(false);
                ku.getjComboBoxVrstaPlesa().setEnabled(false);
                ku.getjButtonDodajStavku().setVisible(false);
                ku.getjButtonSacuvaj().setVisible(false);
                ku.getjButtonZapamti().setVisible(false);
                ku.getjButtonOdustani().setVisible(false);
                ku.getjButtonObrisiStavku().setVisible(false);
                ku.getjButtonPrikazi().setVisible(false);
                ku.getjButtonPromeni().setVisible(true);

            }
        });

        ku.DodajStavkuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();

            }

            private void dodajStavku() {

                int brCasova = 0;
                double cena = 0.0;
                double clanarina = 0.0;
                VrstaPlesa vp = null;
                double clanarina2Decimale;
                String br;
                try {
                    vp = (VrstaPlesa) ku.getjComboBoxVrstaPlesa().getSelectedItem();

                    br = (String) ku.getjComboBoxBrojCasova().getSelectedItem();

                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(ku, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (vp == null && br == null) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (vp == null) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli vrstu plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else {

                    cena = vp.getCenaCasa();
                }

                if (br == null) {
                    JOptionPane.showMessageDialog(ku, "Niste uneli broj časova.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;

                } else {

                    try {
                        brCasova = Integer.parseInt(br);
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(ku, "Greška pri obradi cene časa. Proverite format u kome je uneta cena časa.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }

                clanarina = (double) brCasova * cena;
                clanarina2Decimale = Math.round(clanarina * 100.0) / 100.0;

                ModelTabeleStavkaUpisnice mt = (ModelTabeleStavkaUpisnice) ku.getjTableStavke().getModel();
                List<StavkaUpisnice> stavke = mt.getStavke();
                int redniBroj = stavke.size() + 1;

                StavkaUpisnice stavka = new StavkaUpisnice(redniBroj, brCasova, vp.getCenaCasa(), clanarina2Decimale, vp, null);
                if (stavke.contains(stavka)) {

                    JOptionPane.showMessageDialog(ku, "Ova stavka je već dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                } else {
                    mt.dodajElement(stavka);
                }
                double ukupno = 0;
                for (StavkaUpisnice s : mt.getStavke()) {
                    ukupno += s.getClanarina();

                }
                double ukupno2Decimale = Math.round(ukupno * 100.0) / 100.0;
                ku.getjTextFieldUkupnaClanarina().setText(String.valueOf(ukupno2Decimale));

            }
        });

        ku.ObrisiStavkuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();

            }

            private void obrisiStavku() {

                int selektovaniRed = ku.getjTableStavke().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(ku, "Morate izabrati stavku koju želite da obrišete.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleStavkaUpisnice mt = (ModelTabeleStavkaUpisnice) ku.getjTableStavke().getModel();
                mt.getStavke().remove(selektovaniRed);

                mt.refresujPodatke();

                int redniBroj = 0;
                for (StavkaUpisnice s : mt.getStavke()) {
                    redniBroj++;
                    s.setRb(redniBroj);
                }

                double ukupno = 0;
                for (StavkaUpisnice s : mt.getStavke()) {
                    ukupno += s.getClanarina();

                }
                ku.getjTextFieldUkupnaClanarina().setText(String.valueOf(ukupno));
            }

        });

        pu.RadioIDAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioID();

            }

            private void radioID() {
                pu.getjTextFieldID().setText("");
                pu.getjTextFieldInstruktor().setText("");
                pu.getjTextFieldPolaznik().setText("");
                pu.getjTextFieldNaziv().setText("");
                pu.getjTextFieldKategorija().setText("");
                pu.getjTextFieldID().setEditable(true);
                pu.getjTextFieldInstruktor().setEditable(false);
                pu.getjTextFieldPolaznik().setEditable(false);
                pu.getjTextFieldKategorija().setEditable(false);
                pu.getjTextFieldNaziv().setEditable(false);
            }
        });

        pu.RadioInstruktorAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioInstruktor();

            }

            private void radioInstruktor() {
                pu.getjTextFieldID().setText("");
                pu.getjTextFieldInstruktor().setText("");
                pu.getjTextFieldPolaznik().setText("");
                pu.getjTextFieldNaziv().setText("");
                pu.getjTextFieldKategorija().setText("");
                pu.getjTextFieldID().setEditable(false);
                pu.getjTextFieldInstruktor().setEditable(true);
                pu.getjTextFieldPolaznik().setEditable(false);
                pu.getjTextFieldKategorija().setEditable(false);
                pu.getjTextFieldNaziv().setEditable(false);
            }

        });

        pu.RadioPolaznikAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioPolaznik();

            }

            private void radioPolaznik() {
                pu.getjTextFieldID().setText("");
                pu.getjTextFieldInstruktor().setText("");
                pu.getjTextFieldPolaznik().setText("");
                pu.getjTextFieldNaziv().setText("");
                pu.getjTextFieldKategorija().setText("");
                pu.getjTextFieldID().setEditable(false);
                pu.getjTextFieldInstruktor().setEditable(false);
                pu.getjTextFieldPolaznik().setEditable(true);
                pu.getjTextFieldKategorija().setEditable(false);
                pu.getjTextFieldNaziv().setEditable(false);
            }
        });

        pu.RadioVrstaPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radioVrstaPlesa();

            }

            private void radioVrstaPlesa() {
                pu.getjTextFieldID().setText("");
                pu.getjTextFieldInstruktor().setText("");
                pu.getjTextFieldPolaznik().setText("");
                pu.getjTextFieldNaziv().setText("");
                pu.getjTextFieldKategorija().setText("");
                pu.getjTextFieldID().setEditable(false);
                pu.getjTextFieldInstruktor().setEditable(false);
                pu.getjTextFieldPolaznik().setEditable(false);
                pu.getjTextFieldKategorija().setEditable(true);
                pu.getjTextFieldNaziv().setEditable(true);
            }
        });

    }

    public void prikaziFormuKreirajUpisnicu(Upisnica upisnica) {

        if (upisnica == null) {
            ku.getjComboBoxBrojCasova().setSelectedItem(null);
            ku.getjButtonSacuvaj().setVisible(false);
            ku.getjTextFieldUkupnaClanarina().setEditable(false);
            ku.getjTextFieldUkupnaClanarina().setText(String.valueOf(0));
            popuniInstruktore();
            popuniPolaznike();
            popuniVrstaPlesa();
            popuniTabeluStavki(null);
            ku.getjComboBoxVrstaPlesa().setSelectedItem(null);
            ku.getjComboBoxInstruktor().setSelectedItem(null);
            ku.getjComboBoxPolaznik().setSelectedItem(null);
            ku.getjButtonPrikazi().setVisible(false);
            ku.getjButtonPromeni().setVisible(false);
            JOptionPane.showMessageDialog(ku, "Sistem je kreirao upisnicu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ku.setVisible(true);

        } else {

            ku.getjComboBoxBrojCasova().setSelectedItem(null);
            ku.getjTextFieldUkupnaClanarina().setEditable(false);
            ku.getjTextFieldDatumUpisa().setEditable(false);
            ku.getjComboBoxBrojCasova().setEnabled(false);
            ku.getjComboBoxInstruktor().setEnabled(false);
            ku.getjComboBoxPolaznik().setEnabled(false);
            ku.getjComboBoxVrstaPlesa().setEnabled(false);
            ku.getjButtonDodajStavku().setVisible(false);
            ku.getjButtonSacuvaj().setVisible(false);
            ku.getjButtonZapamti().setVisible(false);
            ku.getjButtonOdustani().setVisible(false);
            ku.getjButtonObrisiStavku().setVisible(false);
            ku.getjButtonPromeni().setVisible(true);
            popuniInstruktore();
            popuniPolaznike();
            popuniVrstaPlesa();

            List<StavkaUpisnice> listaStavki = upisnica.getListaStavke();
            popuniTabeluStavki(listaStavki);

            String datumNovi = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                datumNovi = format.format(upisnica.getDatumUpisa());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(ku, "Greška prilikom obrade datuma.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ku.getjTextFieldDatumUpisa().setText(datumNovi);
            ku.getjTextFieldUkupnaClanarina().setText(String.valueOf(upisnica.getUkupnaClanarina()));
            ku.getjComboBoxInstruktor().setSelectedItem(upisnica.getInstruktor());
            ku.getjComboBoxPolaznik().setSelectedItem(upisnica.getPolaznik());
            ku.getjComboBoxVrstaPlesa().setSelectedItem(null);

            try {
                JsonServis js = new JsonServis();
                double clanarinavEur = js.konvertujUEvre(upisnica.getUkupnaClanarina());
                String poruka = String.format("Sistem je našao upisnicu.\nUkupna članarina: %.2f RSD (%.2f EUR)",
                        upisnica.getUkupnaClanarina(), clanarinavEur);
                JOptionPane.showMessageDialog(ku, poruka, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ku, "Sistem je našao upisnicu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            }
            
            ku.setVisible(true);

        }

    }

    private void popuniInstruktore() {

        List<Instruktor> listaInstruktora = Komunikacija.getInstance().vratiListuSviInstruktor();

        ku.getjComboBoxInstruktor().removeAllItems();
        for (Instruktor instruktor : listaInstruktora) {
            ku.getjComboBoxInstruktor().addItem(instruktor);

        }
    }

    private void popuniPolaznike() {

        List<Polaznik> listaPolaznika = Komunikacija.getInstance().vratiListuSviPolaznik();

        ku.getjComboBoxPolaznik().removeAllItems();

        for (Polaznik polaznik : listaPolaznika) {
            ku.getjComboBoxPolaznik().addItem(polaznik);

        }
    }

    private void popuniVrstaPlesa() {

        List<VrstaPlesa> lista = Komunikacija.getInstance().vratiListuSviVrstaPlesa();

        ku.getjComboBoxVrstaPlesa().removeAllItems();

        for (VrstaPlesa vrsta : lista) {
            ku.getjComboBoxVrstaPlesa().addItem(vrsta);

        }
    }

    private void popuniTabeluStavki(List<StavkaUpisnice> stavke) {
        List<StavkaUpisnice> lista = new ArrayList<>();
        if (stavke != null) {
            lista = stavke;
        }
        ModelTabeleStavkaUpisnice modelTabele = new ModelTabeleStavkaUpisnice(lista);
        ku.getjTableStavke().setModel(modelTabele);
    }

    public void prikaziFormuPretraziUpisnicu() {

        pu.getjRadioButtonID().setSelected(true);
        pu.getjTextFieldID().setEditable(true);
        pu.getjTextFieldInstruktor().setEditable(false);
        pu.getjTextFieldPolaznik().setEditable(false);
        pu.getjTextFieldNaziv().setEditable(false);
        pu.getjTextFieldKategorija().setEditable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(pu.getjRadioButtonID());
        group.add(pu.getjRadioButtonInstruktor());
        group.add(pu.getjRadioButtonPolaznik());
        group.add(pu.getjRadioButtonVrstaPlesa());
        popuniTabeluUpisnica();
        pu.setVisible(true);
    }

    private void popuniTabeluUpisnica() {
        List<Upisnica> upisnice = new ArrayList<>();
        ModelTabeleUpisnice modelTabele = new ModelTabeleUpisnice(upisnice);
        pu.getjTableUpisnice().setModel(modelTabele);
    }
}
