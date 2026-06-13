/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajKvalifikaciju;
import forme.PretraziKvalifkaciju;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.Kvalifikacija;
import model.ModelTabeleKvalifikacija;

/**
 *
 * @author Tijana
 */
public class KvalifikacijaKontroler {

    private final KreirajKvalifikaciju kk;
    private final PretraziKvalifkaciju pk;

    public KvalifikacijaKontroler(KreirajKvalifikaciju kk, PretraziKvalifkaciju pk) {
        this.kk = kk;
        this.pk = pk;
        addActionListeners();
    }

    private void addActionListeners() {
        kk.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                String tip = null;
                String organizacija = null;

                try {

                    tip = kk.getjTextFieldTip().getText().trim();
                    organizacija = kk.getjTextFieldOrganizacija().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (tip.trim().isEmpty() || organizacija.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kk, "Niste uneli sve podatke", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (tip.trim().length() > 100 || organizacija.trim().length() > 100) {

                    if (tip.trim().length() > 100) {
                        JOptionPane.showMessageDialog(kk, "Tip kvalifikacije može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (organizacija.trim().length() > 100) {
                        JOptionPane.showMessageDialog(kk, "Organizacija može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                }

                Kvalifikacija kv = new Kvalifikacija(tip, organizacija);

                boolean uspesno = Komunikacija.getInstance().ubaciKvalifikacija(kv);

                if (!uspesno) {
                    JOptionPane.showMessageDialog(kk, "Sistem ne može da zapamti kvalifikaciju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kk, "Sistem je zapamtio kvalifikaciju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kk.dispose();
                }
            }

        });

        kk.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {

                String tip = null;
                String organizacija = null;

                try {

                    tip = kk.getjTextFieldTip().getText().trim();
                    organizacija = kk.getjTextFieldOrganizacija().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                if (tip.trim().isEmpty() || organizacija.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kk, "Niste uneli sve podatke", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tip.trim().length() > 100 || organizacija.trim().length() > 100) {

                    if (tip.trim().length() > 100) {
                        JOptionPane.showMessageDialog(kk, "Tip kvalifikacije može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (organizacija.trim().length() > 100) {
                        JOptionPane.showMessageDialog(kk, "Organizacija može sadržati maksimalno 100 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                }

                Kvalifikacija kv = new Kvalifikacija(kk.getKvalifikacija().getIdKvalifikacija(), tip, organizacija);

                int odgovor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniKvalifikacija(kv);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kk, "Sistem ne može da zapamti kvalifikaciju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kk, "Sistem je zapamtio kvalifikaciju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kk.dispose();
                    }
                }
            }

        });

        kk.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da obrišete kvalifikaciju?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String tip = null;
                    String organizacija = null;

                    tip = kk.getjTextFieldTip().getText().trim();
                    organizacija = kk.getjTextFieldOrganizacija().getText().trim();

                    Kvalifikacija kv = new Kvalifikacija(kk.getKvalifikacija().getIdKvalifikacija(), tip, organizacija);

                    boolean uspesno = Komunikacija.getInstance().obrisiKvalifikacija(kv);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kk, "Sistem ne može da obriše kvalifikaciju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kk, "Sistem je obrisao kvalifikaciju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kk.dispose();
                    }
                }
            }
        });

        pk.VratiListuSvihKvalifikacijaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihKvalifikacija();

            }

            private void vratiListuSvihKvalifikacija() {

                List<Kvalifikacija> lista = Komunikacija.getInstance().vratiListuSviKvalifikacija();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe sve kvalifikacije.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pk, "Sistem je našao sve kvalifikacije.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleKvalifikacija mt = (ModelTabeleKvalifikacija) pk.getjTable().getModel();

                if (lista.size() > 50) {
                    List<Kvalifikacija> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }
            }
        });

        pk.VratiListuKvalifikacijaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuKvalifikacijaSaUslovom();

            }

            private void vratiListuKvalifikacijaSaUslovom() {
                List<Kvalifikacija> lista = new ArrayList<>();
                String tip = null;
                String organizacija = null;

                try {

                    if (!pk.getjTextFieldTip().getText().trim().isEmpty()) {
                        tip = pk.getjTextFieldTip().getText().trim();
                    }

                    if (!pk.getjTextFieldOrganizacija().getText().trim().isEmpty()) {
                        organizacija = pk.getjTextFieldOrganizacija().getText().trim();
                    }

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ((tip == null || tip.trim().isEmpty()) && (organizacija == null || organizacija.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(pk, "Morate uneti bar jedan kriterijum za pretraživanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (tip != null || organizacija != null) {
                    Kvalifikacija kval = new Kvalifikacija(tip, organizacija);

                    List<Kvalifikacija> lista1 = Komunikacija.getInstance().vratiListuKvalifikacijaKvalifikacija(kval);

                    for (Kvalifikacija kv : lista1) {
                        if (!lista.contains(kv)) {
                            lista.add(kv);
                        }

                    }
                }

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe kvalifikacije po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pk, "Sistem je našao kvalifikacije po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleKvalifikacija mt = (ModelTabeleKvalifikacija) pk.getjTable().getModel();

                if (lista.size() > 50) {
                    List<Kvalifikacija> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }
            }
        });

        pk.PrikaziKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziKvalifikaciju();

            }

            private void prikaziKvalifikaciju() {
                int selektovaniRed = pk.getjTable().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pk, "Morate izabrati kvalifikaciju iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleKvalifikacija mt = (ModelTabeleKvalifikacija) pk.getjTable().getModel();
                Kvalifikacija kv = mt.getLista().get(selektovaniRed);
                Kvalifikacija kvDetalji = Komunikacija.getInstance().pretraziKvalifikacija(kv);
                if (kvDetalji == null) {
                    JOptionPane.showMessageDialog(pk, "Sistem ne može da nađe kvalifikaciju.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pk.dispose();
                KontrolerGlavni.getInstance().pokreniKreirajKvalifikaciju(kvDetalji);
            }

        });

        kk.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kk, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kk.dispose();
                }

            }

        });

        kk.PromeniKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                kk.getjButtonSacuvaj().setVisible(true);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(true);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonPrikaziObrisi().setVisible(false);
                kk.getjButtonPrikaziPromeni().setVisible(true);
                kk.getjButtonObrisiKvalifikaciju().setVisible(true);
                kk.getjButtonPromeni().setVisible(false);
                kk.getjTextFieldTip().setText(kk.getKvalifikacija().getTip());
                kk.getjTextFieldOrganizacija().setText(kk.getKvalifikacija().getOrganizacija());
                kk.getjTextFieldTip().setEditable(true);
                kk.getjTextFieldOrganizacija().setEditable(true);
            }

        });

        kk.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(false);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonPrikaziObrisi().setVisible(false);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                kk.getjButtonObrisiKvalifikaciju().setVisible(true);
                kk.getjButtonPromeni().setVisible(true);
                kk.getjTextFieldTip().setText(kk.getKvalifikacija().getTip());
                kk.getjTextFieldOrganizacija().setText(kk.getKvalifikacija().getOrganizacija());
                kk.getjTextFieldTip().setEditable(false);
                kk.getjTextFieldOrganizacija().setEditable(false);
            }
        });

        kk.ObrisiKvalifikacijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiKvalifikaciju();

            }

            private void obrisiKvalifikaciju() {
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(true);
                kk.getjButtonObrisi().setVisible(true);
                kk.getjButtonPrikaziObrisi().setVisible(true);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                kk.getjButtonObrisiKvalifikaciju().setVisible(false);
                kk.getjButtonPromeni().setVisible(true);
                kk.getjTextFieldTip().setText(kk.getKvalifikacija().getTip());
                kk.getjTextFieldOrganizacija().setText(kk.getKvalifikacija().getOrganizacija());
                kk.getjTextFieldTip().setEditable(false);
                kk.getjTextFieldOrganizacija().setEditable(false);
            }

        });

        kk.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kk.getjButtonSacuvaj().setVisible(false);
                kk.getjButtonZapamti().setVisible(false);
                kk.getjButtonOdustani().setVisible(false);
                kk.getjButtonObrisi().setVisible(false);
                kk.getjButtonPrikaziObrisi().setVisible(false);
                kk.getjButtonPrikaziPromeni().setVisible(false);
                kk.getjButtonObrisiKvalifikaciju().setVisible(true);
                kk.getjButtonPromeni().setVisible(true);

                kk.getjTextFieldTip().setText(kk.getKvalifikacija().getTip());
                kk.getjTextFieldOrganizacija().setText(kk.getKvalifikacija().getOrganizacija());
                kk.getjTextFieldTip().setEditable(false);
                kk.getjTextFieldOrganizacija().setEditable(false);
            }

        });

    }

    public void prikaziFormuKreirajKvalifikaciju(Kvalifikacija kvalifikacija) {

        if (kvalifikacija == null) {

            kk.getjButtonSacuvaj().setVisible(false);
            kk.getjButtonZapamti().setVisible(true);
            kk.getjButtonOdustani().setVisible(true);
            kk.getjButtonObrisi().setVisible(false);
            kk.getjButtonPrikaziObrisi().setVisible(false);
            kk.getjButtonPrikaziPromeni().setVisible(false);
            kk.getjButtonObrisiKvalifikaciju().setVisible(false);
            kk.getjButtonPromeni().setVisible(false);
            JOptionPane.showMessageDialog(kk, "Sistem je kreirao kvalifikaciju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            kk.setVisible(true);

        } else {

            kk.getjButtonSacuvaj().setVisible(false);
            kk.getjButtonZapamti().setVisible(false);
            kk.getjButtonOdustani().setVisible(false);
            kk.getjButtonObrisi().setVisible(false);
            kk.getjButtonPrikaziObrisi().setVisible(false);
            kk.getjButtonPrikaziPromeni().setVisible(false);
            kk.getjButtonObrisiKvalifikaciju().setVisible(true);
            kk.getjButtonPromeni().setVisible(true);

            kk.getjTextFieldTip().setText(kvalifikacija.getTip());
            kk.getjTextFieldOrganizacija().setText(kvalifikacija.getOrganizacija());
            kk.getjTextFieldTip().setEditable(false);
            kk.getjTextFieldOrganizacija().setEditable(false);
            JOptionPane.showMessageDialog(kk, "Sistem je našao kvalifikaciju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            kk.setVisible(true);
        }

    }

    public void prikaziFormuPretraziKvalifikaciju() {

        popuniTabelu();
        pk.setVisible(true);
    }

    private void popuniTabelu() {
        List<Kvalifikacija> lista = new ArrayList<>();
        ModelTabeleKvalifikacija modelTabele = new ModelTabeleKvalifikacija(lista);
        pk.getjTable().setModel(modelTabele);
    }

}
