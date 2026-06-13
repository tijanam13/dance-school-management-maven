/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajUzrasnuKategoriju;
import forme.PretraziUzrasnuKategoriju;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.ModelTabeleUzrast;
import model.UzrasnaKategorija;

/**
 *
 * @author Tijana
 */
public class UzrasnaKategorijaKontroler {

    private final KreirajUzrasnuKategoriju kuk;
    private final PretraziUzrasnuKategoriju puk;

    public UzrasnaKategorijaKontroler(KreirajUzrasnuKategoriju kuk, PretraziUzrasnuKategoriju puk) {
        this.kuk = kuk;
        this.puk = puk;
        addActionListeners();
    }

    private void addActionListeners() {
        kuk.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                String naziv = null;
                String opsegGodina = null;

                try {

                    naziv = kuk.getjTextFieldNaziv().getText().trim();
                    opsegGodina = kuk.getjTextFieldOpsegGodina().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kuk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (naziv.trim().isEmpty() || opsegGodina.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kuk, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30 || opsegGodina.trim().length() > 20) {

                    if (naziv.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kuk, "Naziv kategorije može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (opsegGodina.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kuk, "Opseg godina može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                UzrasnaKategorija uz = new UzrasnaKategorija(opsegGodina, naziv);

                boolean uspesno = Komunikacija.getInstance().kreirajUzrasnaKategorija(uz);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(kuk, "Sistem ne može da zapamti uzrasnu kategoriju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kuk, "Sistem je zapamtio uzrasnu kategoriju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kuk.dispose();
                }
            }

        });

        kuk.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                String naziv = null;
                String opsegGodina = null;

                try {

                    naziv = kuk.getjTextFieldNaziv().getText().trim();
                    opsegGodina = kuk.getjTextFieldOpsegGodina().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kuk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (naziv.trim().isEmpty() || opsegGodina.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kuk, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30 || opsegGodina.trim().length() > 20) {

                    if (naziv.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kuk, "Naziv kategorije može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (opsegGodina.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kuk, "Opseg godina može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                UzrasnaKategorija uz = new UzrasnaKategorija(kuk.getUzrast().getIdUzrast(), opsegGodina, naziv);

                int odgovor = JOptionPane.showConfirmDialog(kuk, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {

                    boolean uspesno = Komunikacija.getInstance().promeniUzrasnaKategorija(uz);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kuk, "Sistem ne može da zapamti uzrasnu kategoriju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kuk, "Sistem je zapamtio uzrasnu kategoriju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kuk.dispose();
                    }
                }
            }

        });

        kuk.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kuk, "Da li ste sigurni da želite da obrišete uzrasnu kategoriju?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    String naziv = null;
                    String opsegGodina = null;

                    naziv = kuk.getjTextFieldNaziv().getText().trim();
                    opsegGodina = kuk.getjTextFieldOpsegGodina().getText().trim();

                    UzrasnaKategorija uz = new UzrasnaKategorija(kuk.getUzrast().getIdUzrast(), opsegGodina, naziv);

                    boolean uspesno = Komunikacija.getInstance().obrisiUzrasnaKategorija(uz);

                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kuk, "Sistem ne može da obriše uzrasnu kategoriju.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(kuk, "Sistem je obrisao uzrasnu kategoriju.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kuk.dispose();
                    }
                }
            }
        });

        puk.VratiListuSvihUzrasnihKategorijaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihUzrasnihKategorija();

            }

            private void vratiListuSvihUzrasnihKategorija() {

                List<UzrasnaKategorija> lista = Komunikacija.getInstance().vratiListuSviUzrasnaKategorija();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(puk, "Sistem ne može da nađe sve uzrasne kategorije.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(puk, "Sistem je našao sve uzrasne kategorije.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }
                ModelTabeleUzrast mt = (ModelTabeleUzrast) puk.getjTable().getModel();

                if (lista.size() > 50) {
                    List<UzrasnaKategorija> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }
            }

        });

        puk.VratiListuUzrasnihKategorijaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuUzrasnihKategorijaSaUslovom(e);

            }

            public void vratiListuUzrasnihKategorijaSaUslovom(ActionEvent e) {

                List<UzrasnaKategorija> lista = new ArrayList<>();
                String naziv = null;
                String opsegGodina = null;

                try {

                    if (!puk.getjTextFieldNaziv().getText().trim().isEmpty()) {
                        naziv = puk.getjTextFieldNaziv().getText().trim();
                    }
                    if (!puk.getjTextFieldOpsegGodina().getText().trim().isEmpty()) {
                        opsegGodina = puk.getjTextFieldOpsegGodina().getText().trim();
                    }

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(puk, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ((opsegGodina == null || opsegGodina.trim().isEmpty()) && (naziv == null || naziv.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(kuk, "Morate uneti bar jedan kriterijum za pretraživanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv != null || opsegGodina != null) {
                    UzrasnaKategorija uzk = new UzrasnaKategorija(opsegGodina, naziv);

                    List<UzrasnaKategorija> lista1 = Komunikacija.getInstance().vratiListuUzrasnaKategorijaUzrasnaKategorija(uzk);

                    for (UzrasnaKategorija uz : lista1) {
                        if (!lista.contains(uz)) {
                            lista.add(uz);
                        }

                    }
                }

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(puk, "Sistem ne može da nađe uzrasne kategorije po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(puk, "Sistem je našao uzrasne kategorije po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleUzrast mt = (ModelTabeleUzrast) puk.getjTable().getModel();

                if (lista.size() > 50) {
                    List<UzrasnaKategorija> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }

            }

        });

        puk.PrikaziUzrasnuKategorijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziUzrasnukategoriju();

            }

            private void prikaziUzrasnukategoriju() {
                int selektovaniRed = puk.getjTable().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(puk, "Morate izabrati uzrasnu kategoriju iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleUzrast mt = (ModelTabeleUzrast) puk.getjTable().getModel();
                UzrasnaKategorija uz = mt.getLista().get(selektovaniRed);
                UzrasnaKategorija uzDetalji = Komunikacija.getInstance().pretraziUzrasnaKategorija(uz);
                if (uzDetalji == null) {
                    JOptionPane.showMessageDialog(puk, "Sistem ne može da nađe uzrasnu kategoriju.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;

                }
                puk.dispose();
                KontrolerGlavni.getInstance().pokreniKreirajUzrasnuKategoriju(uzDetalji);

            }
        });

        kuk.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kuk, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kuk.dispose();
                }
            }
        });

        kuk.PromeniUzrasnuKategorijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniUzrasnuKategoriju();

            }

            private void promeniUzrasnuKategoriju() {
                kuk.getjButtonSacuvaj().setVisible(true);
                kuk.getjButtonZapamti().setVisible(false);
                kuk.getjButtonOdustani().setVisible(true);
                kuk.getjButtonObrisi().setVisible(false);
                kuk.getjButtonPrikaziObrisi().setVisible(false);
                kuk.getjButtonPrikaziPromeni().setVisible(true);
                kuk.getjButtonPromeni().setVisible(false);
                kuk.getjButtonObrisiKategoriju().setVisible(true);
                kuk.getjTextFieldNaziv().setEditable(true);
                kuk.getjTextFieldOpsegGodina().setEditable(true);
            }
        });

        kuk.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kuk.getjButtonSacuvaj().setVisible(false);
                kuk.getjButtonZapamti().setVisible(false);
                kuk.getjButtonOdustani().setVisible(false);
                kuk.getjButtonObrisi().setVisible(false);
                kuk.getjButtonPrikaziObrisi().setVisible(false);
                kuk.getjButtonPrikaziPromeni().setVisible(false);
                kuk.getjButtonPromeni().setVisible(true);
                kuk.getjButtonObrisiKategoriju().setVisible(true);
                kuk.getjTextFieldNaziv().setText(kuk.getUzrast().getNaziv());
                kuk.getjTextFieldOpsegGodina().setText(kuk.getUzrast().getOpsegGodina());
                kuk.getjTextFieldNaziv().setEditable(false);
                kuk.getjTextFieldOpsegGodina().setEditable(false);
            }
        });

        kuk.ObrisiUzrasnuKategorijuAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiUzrasnuKategoriju();

            }

            private void obrisiUzrasnuKategoriju() {
                kuk.getjButtonSacuvaj().setVisible(false);
                kuk.getjButtonZapamti().setVisible(false);
                kuk.getjButtonOdustani().setVisible(true);
                kuk.getjButtonObrisi().setVisible(true);
                kuk.getjButtonPrikaziObrisi().setVisible(true);
                kuk.getjButtonPrikaziPromeni().setVisible(false);
                kuk.getjButtonPromeni().setVisible(true);
                kuk.getjButtonObrisiKategoriju().setVisible(false);
                kuk.getjTextFieldNaziv().setText(kuk.getUzrast().getNaziv());
                kuk.getjTextFieldOpsegGodina().setText(kuk.getUzrast().getOpsegGodina());
                kuk.getjTextFieldNaziv().setEditable(false);
                kuk.getjTextFieldOpsegGodina().setEditable(false);
            }
        });

        kuk.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kuk.getjButtonSacuvaj().setVisible(false);
                kuk.getjButtonZapamti().setVisible(false);
                kuk.getjButtonOdustani().setVisible(false);
                kuk.getjButtonObrisi().setVisible(false);
                kuk.getjButtonPrikaziObrisi().setVisible(false);
                kuk.getjButtonPrikaziPromeni().setVisible(false);
                kuk.getjButtonPromeni().setVisible(true);
                kuk.getjButtonObrisiKategoriju().setVisible(true);
                kuk.getjTextFieldNaziv().setText(kuk.getUzrast().getNaziv());
                kuk.getjTextFieldOpsegGodina().setText(kuk.getUzrast().getOpsegGodina());
                kuk.getjTextFieldNaziv().setEditable(false);
                kuk.getjTextFieldOpsegGodina().setEditable(false);
            }
        });

    }

    public void prikaziFormuKreirajUzrasnuKategoriju(UzrasnaKategorija uzrasnaKategorija) {
        if (uzrasnaKategorija == null) {

            kuk.getjButtonSacuvaj().setVisible(false);
            kuk.getjButtonObrisi().setVisible(false);
            kuk.getjButtonZapamti().setVisible(true);
            kuk.getjButtonOdustani().setVisible(true);
            kuk.getjButtonPrikaziObrisi().setVisible(false);
            kuk.getjButtonPrikaziPromeni().setVisible(false);
            kuk.getjButtonPromeni().setVisible(false);
            kuk.getjButtonObrisiKategoriju().setVisible(false);
            JOptionPane.showMessageDialog(kuk, "Sistem je kreirao uzrasnu kategoriju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            kuk.setVisible(true);

        } else {
            kuk.getjButtonSacuvaj().setVisible(false);
            kuk.getjButtonZapamti().setVisible(false);
            kuk.getjButtonOdustani().setVisible(false);
            kuk.getjButtonObrisi().setVisible(false);
            kuk.getjButtonPrikaziObrisi().setVisible(false);
            kuk.getjButtonPrikaziPromeni().setVisible(false);
            kuk.getjButtonPromeni().setVisible(true);
            kuk.getjButtonObrisiKategoriju().setVisible(true);
            kuk.getjTextFieldNaziv().setText(uzrasnaKategorija.getNaziv());
            kuk.getjTextFieldOpsegGodina().setText(uzrasnaKategorija.getOpsegGodina());
            kuk.getjTextFieldNaziv().setEditable(false);
            kuk.getjTextFieldOpsegGodina().setEditable(false);

            JOptionPane.showMessageDialog(kuk, "Sistem je našao uzrasnu kategoriju.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            kuk.setVisible(true);
        }

    }

    public void prikaziFormuPretraziUzrasnuKategoriju() {

        popuniTabelu();
        puk.setVisible(true);

    }

    private void popuniTabelu() {

        List<UzrasnaKategorija> lista = new ArrayList<>();
        ModelTabeleUzrast modelTabele = new ModelTabeleUzrast(lista);
        puk.getjTable().setModel(modelTabele);
    }
}
