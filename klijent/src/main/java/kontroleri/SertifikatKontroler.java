package kontroleri;

import forme.KreirajSertifikat;
import forme.PretraziSertifikat;
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
import model.ModelTabeleSertifikat;
import model.Nivo;
import model.Polaznik;
import model.Sertifikat;
import model.VrstaPlesa;

public class SertifikatKontroler {

    private final KreirajSertifikat ks;
    private final PretraziSertifikat ps;

    public SertifikatKontroler(KreirajSertifikat ks, PretraziSertifikat ps) {
        this.ks = ks;
        this.ps = ps;
        addActionListeners();
    }

    private void addActionListeners() {

        ks.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();
            }

            private void zapamti() {
                String datum = null;
                String mestoIzdavanja = null;
                String napomena = null;
                Nivo nivo = null;
                Polaznik polaznik = null;
                VrstaPlesa vrstaPlesa = null;
                Date datumIzdavanja = null;

                try {
                    datum = ks.getjTextFieldDatumIzdavanja().getText().trim();
                    mestoIzdavanja = ks.getjTextFieldMestoIzdavanja().getText().trim();
                    napomena = ks.getjTextFieldNapomena().getText().trim();
                    nivo = (Nivo) ks.getjComboBoxNivo().getSelectedItem();
                    polaznik = (Polaznik) ks.getjComboBoxPolaznik().getSelectedItem();
                    vrstaPlesa = (VrstaPlesa) ks.getjComboBoxVrstaPlesa().getSelectedItem();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ks, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (datum == null || datum.trim().isEmpty() || mestoIzdavanja == null || mestoIzdavanja.trim().isEmpty()
                        || nivo == null || polaznik == null || vrstaPlesa == null) {
                    JOptionPane.showMessageDialog(ks, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                try {
                    datumIzdavanja = format.parse(datum);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ks, "Niste uneli datum u ispravnom formatu (dd.MM.yyyy.).", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumIzdavanja.after(new Date())) {
                    JOptionPane.showMessageDialog(ks, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Sertifikat sertifikat = new Sertifikat(datumIzdavanja, mestoIzdavanja, nivo, napomena, polaznik, vrstaPlesa);

                boolean uspesno = Komunikacija.getInstance().kreirajSertifikat(sertifikat);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(ks, "Sistem ne može da zapamti sertifikat. Sertifikat možda već postoji.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ks, "Sistem je zapamtio sertifikat.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    ks.dispose();
                }
            }
        });

        ks.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();
            }

            private void promeni() {
                String datum = null;
                String mestoIzdavanja = null;
                String napomena = null;
                Nivo nivo = null;
                Polaznik polaznik = null;
                VrstaPlesa vrstaPlesa = null;
                Date datumIzdavanja = null;

                try {
                    datum = ks.getjTextFieldDatumIzdavanja().getText().trim();
                    mestoIzdavanja = ks.getjTextFieldMestoIzdavanja().getText().trim();
                    napomena = ks.getjTextFieldNapomena().getText().trim();
                    nivo = (Nivo) ks.getjComboBoxNivo().getSelectedItem();
                    polaznik = (Polaznik) ks.getjComboBoxPolaznik().getSelectedItem();
                    vrstaPlesa = (VrstaPlesa) ks.getjComboBoxVrstaPlesa().getSelectedItem();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ks, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (datum == null || datum.trim().isEmpty() || mestoIzdavanja == null || mestoIzdavanja.trim().isEmpty()
                        || nivo == null || polaznik == null || vrstaPlesa == null) {
                    JOptionPane.showMessageDialog(ks, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                try {
                    datumIzdavanja = format.parse(datum);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ks, "Niste uneli datum u ispravnom formatu (dd.MM.yyyy.).", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumIzdavanja.after(new Date())) {
                    JOptionPane.showMessageDialog(ks, "Datum se ne sme odnositi na budućnost.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Sertifikat sertifikat = new Sertifikat(ks.getSertifikat().getIdSertifikat(), datumIzdavanja, mestoIzdavanja, nivo, napomena, polaznik, vrstaPlesa);

                int odgovor = JOptionPane.showConfirmDialog(ks, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    boolean uspesno = Komunikacija.getInstance().promeniSertifikat(sertifikat);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ks, "Sistem ne može da zapamti sertifikat.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ks, "Sistem je zapamtio sertifikat.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ks.dispose();
                    }
                }
            }
        });

        ks.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();
            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(ks, "Da li ste sigurni da želite da obrišete sertifikat?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    boolean uspesno = Komunikacija.getInstance().obrisiSertifikat(ks.getSertifikat());
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(ks, "Sistem ne može da obriše sertifikat.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ks, "Sistem je obrisao sertifikat.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        ks.dispose();
                    }
                }
            }
        });

        ks.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int izbor = JOptionPane.showConfirmDialog(ks, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    ks.dispose();
                }
            }
        });

        ks.ObrisiSertifikatAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ks.getjButtonSacuvaj().setVisible(false);
                ks.getjButtonZapamti().setVisible(false);
                ks.getjButtonOdustani().setVisible(true);
                ks.getjButtonObrisi().setVisible(true);
                ks.getjButtonPromeniSertifikat().setVisible(true);
                ks.getjButtonObrisiSertifikat().setVisible(false);
                ks.getjButtonPrikaziObrisi().setVisible(true);
                ks.getjButtonPrikaziPromeni().setVisible(false);
                popuniPolja();
                ks.getjComboBoxNivo().setEnabled(false);
                ks.getjComboBoxPolaznik().setEnabled(false);
                ks.getjComboBoxVrstaPlesa().setEnabled(false);
                ks.getjTextFieldDatumIzdavanja().setEditable(false);
                ks.getjTextFieldMestoIzdavanja().setEditable(false);
                ks.getjTextFieldNapomena().setEditable(false);
            }
        });

        ks.PromeniSertifikatAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ks.getjButtonSacuvaj().setVisible(true);
                ks.getjButtonZapamti().setVisible(false);
                ks.getjButtonOdustani().setVisible(true);
                ks.getjButtonObrisi().setVisible(false);
                ks.getjButtonPromeniSertifikat().setVisible(false);
                ks.getjButtonObrisiSertifikat().setVisible(true);
                ks.getjButtonPrikaziObrisi().setVisible(false);
                ks.getjButtonPrikaziPromeni().setVisible(true);
                ks.getjComboBoxNivo().setEnabled(true);
                ks.getjComboBoxPolaznik().setEnabled(true);
                ks.getjComboBoxVrstaPlesa().setEnabled(true);
                ks.getjTextFieldDatumIzdavanja().setEditable(true);
                ks.getjTextFieldMestoIzdavanja().setEditable(true);
                ks.getjTextFieldNapomena().setEditable(true);
            }
        });

        ks.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ks.getjButtonSacuvaj().setVisible(false);
                ks.getjButtonZapamti().setVisible(false);
                ks.getjButtonOdustani().setVisible(false);
                ks.getjButtonObrisi().setVisible(false);
                ks.getjButtonPromeniSertifikat().setVisible(true);
                ks.getjButtonObrisiSertifikat().setVisible(true);
                ks.getjButtonPrikaziObrisi().setVisible(false);
                ks.getjButtonPrikaziPromeni().setVisible(false);
                popuniPolja();
                ks.getjComboBoxNivo().setEnabled(false);
                ks.getjComboBoxPolaznik().setEnabled(false);
                ks.getjComboBoxVrstaPlesa().setEnabled(false);
                ks.getjTextFieldDatumIzdavanja().setEditable(false);
                ks.getjTextFieldMestoIzdavanja().setEditable(false);
                ks.getjTextFieldNapomena().setEditable(false);
            }
        });

        ks.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ks.getjButtonSacuvaj().setVisible(false);
                ks.getjButtonZapamti().setVisible(false);
                ks.getjButtonOdustani().setVisible(false);
                ks.getjButtonObrisi().setVisible(false);
                ks.getjButtonPromeniSertifikat().setVisible(true);
                ks.getjButtonObrisiSertifikat().setVisible(true);
                ks.getjButtonPrikaziObrisi().setVisible(false);
                ks.getjButtonPrikaziPromeni().setVisible(false);
                popuniPolja();
                ks.getjComboBoxNivo().setEnabled(false);
                ks.getjComboBoxPolaznik().setEnabled(false);
                ks.getjComboBoxVrstaPlesa().setEnabled(false);
                ks.getjTextFieldDatumIzdavanja().setEditable(false);
                ks.getjTextFieldMestoIzdavanja().setEditable(false);
                ks.getjTextFieldNapomena().setEditable(false);
            }
        });

        ps.VratiListuSvihSertifikataAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihSertifikata();
            }

            private void vratiListuSvihSertifikata() {
                List<Sertifikat> lista = Komunikacija.getInstance().vratiListuSviSertifikat();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(ps, "Sistem ne može da nađe sve sertifikate.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(ps, "Sistem je našao sve sertifikate.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                }

                ModelTabeleSertifikat mt = (ModelTabeleSertifikat) ps.getjTable().getModel();
                if (lista.size() > 50) {
                    mt.setLista(lista.subList(0, Math.min(50, lista.size())));
                } else {
                    mt.setLista(lista);
                }
                mt.refresujPodatke();
            }
        });

        ps.PretraziAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }

            private void pretrazi() {
                List<Sertifikat> lista = new ArrayList<>();

                if (ps.getjRadioButtonPolaznik().isSelected()) {
                    Polaznik polaznik = (Polaznik) ps.getjComboBoxPolaznik().getSelectedItem();
                    if (polaznik == null) {
                        JOptionPane.showMessageDialog(ps, "Morate izabrati polaznika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Sertifikat s = new Sertifikat();
                    s.setPolaznik(polaznik);
                    lista = Komunikacija.getInstance().vratiListuSertifikatPolaznik(s);

                } else if (ps.getjRadioButtonVrstaPlesa().isSelected()) {
                    VrstaPlesa vrstaPlesa = (VrstaPlesa) ps.getjComboBoxVrstaPlesa().getSelectedItem();
                    if (vrstaPlesa == null) {
                        JOptionPane.showMessageDialog(ps, "Morate izabrati vrstu plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Sertifikat s = new Sertifikat();
                    s.setVrstaPlesa(vrstaPlesa);
                    lista = Komunikacija.getInstance().vratiListuSertifikatVrstaPlesa(s);
                } else {
                    JOptionPane.showMessageDialog(ps, "Morate izabrati kriterijum pretrage.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(ps, "Sistem ne može da nađe sertifikate po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(ps, "Sistem je našao sertifikate.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                }

                ModelTabeleSertifikat mt = (ModelTabeleSertifikat) ps.getjTable().getModel();
                if (lista.size() > 50) {
                    mt.setLista(lista.subList(0, Math.min(50, lista.size())));
                } else {
                    mt.setLista(lista);
                }
                mt.refresujPodatke();
            }
        });

        ps.PrikaziSertifikatAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziSertifikat();
            }

            private void prikaziSertifikat() {
                int selektovaniRed = ps.getjTable().getSelectedRow();
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(ps, "Morate izabrati sertifikat iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleSertifikat mt = (ModelTabeleSertifikat) ps.getjTable().getModel();
                Sertifikat sertifikat = mt.getLista().get(selektovaniRed);
                Sertifikat sertifikatDetalji = Komunikacija.getInstance().pretraziSertifikat(sertifikat);
                if (sertifikatDetalji == null) {
                    JOptionPane.showMessageDialog(ps, "Sistem ne može da nađe sertifikat.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ps.dispose();
                KontrolerGlavni.getInstance().pokreniKreirajSertifikat(sertifikatDetalji);
            }
        });

        ps.RadioPolaznikAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.getjComboBoxPolaznik().setEnabled(true);
                ps.getjComboBoxVrstaPlesa().setEnabled(false);
            }
        });

        ps.RadioVrstaPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ps.getjComboBoxPolaznik().setEnabled(false);
                ps.getjComboBoxVrstaPlesa().setEnabled(true);
            }
        });
    }

    private void popuniPolja() {
        if (ks.getSertifikat() != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
            ks.getjTextFieldDatumIzdavanja().setText(format.format(ks.getSertifikat().getDatumIzdavanja()));
            ks.getjTextFieldMestoIzdavanja().setText(ks.getSertifikat().getMestoIzdavanja());
            ks.getjTextFieldNapomena().setText(ks.getSertifikat().getNapomena());
            ks.getjComboBoxNivo().setSelectedItem(ks.getSertifikat().getNivo());
            ks.getjComboBoxPolaznik().setSelectedItem(ks.getSertifikat().getPolaznik());
            ks.getjComboBoxVrstaPlesa().setSelectedItem(ks.getSertifikat().getVrstaPlesa());
        }
    }

    public void prikaziFormuKreirajSertifikat(Sertifikat sertifikat) {
        popuniPolaznike();
        popuniVrstePlesa();

        if (sertifikat == null) {
            ks.getjButtonSacuvaj().setVisible(false);
            ks.getjButtonZapamti().setVisible(true);
            ks.getjButtonOdustani().setVisible(true);
            ks.getjButtonObrisi().setVisible(false);
            ks.getjButtonPromeniSertifikat().setVisible(false);
            ks.getjButtonObrisiSertifikat().setVisible(false);
            ks.getjButtonPrikaziObrisi().setVisible(false);
            ks.getjButtonPrikaziPromeni().setVisible(false);
            ks.getjComboBoxNivo().setEnabled(true);
            ks.getjComboBoxPolaznik().setEnabled(true);
            ks.getjComboBoxVrstaPlesa().setEnabled(true);
            ks.getjTextFieldDatumIzdavanja().setEditable(true);
            ks.getjTextFieldMestoIzdavanja().setEditable(true);
            ks.getjTextFieldNapomena().setEditable(true);
            JOptionPane.showMessageDialog(ks, "Sistem je kreirao sertifikat.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ks.setVisible(true);
        } else {
            ks.getjButtonSacuvaj().setVisible(false);
            ks.getjButtonZapamti().setVisible(false);
            ks.getjButtonOdustani().setVisible(false);
            ks.getjButtonObrisi().setVisible(false);
            ks.getjButtonPromeniSertifikat().setVisible(true);
            ks.getjButtonObrisiSertifikat().setVisible(true);
            ks.getjButtonPrikaziObrisi().setVisible(false);
            ks.getjButtonPrikaziPromeni().setVisible(false);
            ks.getjComboBoxNivo().setEnabled(false);
            ks.getjComboBoxPolaznik().setEnabled(false);
            ks.getjComboBoxVrstaPlesa().setEnabled(false);
            ks.getjTextFieldDatumIzdavanja().setEditable(false);
            ks.getjTextFieldMestoIzdavanja().setEditable(false);
            ks.getjTextFieldNapomena().setEditable(false);
            popuniPolja();
            JOptionPane.showMessageDialog(ks, "Sistem je našao sertifikat.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            ks.setVisible(true);
        }
    }

    public void prikaziFormuPretraziSertifikat() {
        ps.popuniTabelu();
        popuniPolaznike();
        popuniVrstePlesa();
        ps.getjComboBoxPolaznik().setEnabled(false);
        ps.getjComboBoxVrstaPlesa().setEnabled(false);
        ButtonGroup group = new ButtonGroup();
        group.add(ps.getjRadioButtonPolaznik());
        group.add(ps.getjRadioButtonVrstaPlesa());
        ps.setVisible(true);
    }

    private void popuniPolaznike() {
        List<model.Polaznik> listaPolaznika = Komunikacija.getInstance().vratiListuSviPolaznik();
        ks.getjComboBoxPolaznik().removeAllItems();
        ps.getjComboBoxPolaznik().removeAllItems();
        if (listaPolaznika != null) {
            for (Polaznik p : listaPolaznika) {
                ks.getjComboBoxPolaznik().addItem(p);
                ps.getjComboBoxPolaznik().addItem(p);
            }
        }
    }

    private void popuniVrstePlesa() {
        List<VrstaPlesa> listaVrstaPlesa = Komunikacija.getInstance().vratiListuSviVrstaPlesa();
        ks.getjComboBoxVrstaPlesa().removeAllItems();
        ps.getjComboBoxVrstaPlesa().removeAllItems();
        if (listaVrstaPlesa != null) {
            for (VrstaPlesa vp : listaVrstaPlesa) {
                ks.getjComboBoxVrstaPlesa().addItem(vp);
                ps.getjComboBoxVrstaPlesa().addItem(vp);
            }
        }
    }
}