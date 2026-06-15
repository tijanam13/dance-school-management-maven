package kontroleri;

import forme.KreirajVrstuPlesa;
import forme.PretraziVrstuPlesa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.ModelTabeleVrstePlesa;
import model.VrstaPlesa;
import servis.JsonServis;

/**
 *
 * @author Tijana
 */
public class VrstaPlesaKontroler {

    private final KreirajVrstuPlesa kvp;
    private final PretraziVrstuPlesa pvp;

    public VrstaPlesaKontroler(KreirajVrstuPlesa kvp, PretraziVrstuPlesa pvp) {
        this.kvp = kvp;
        this.pvp = pvp;
        addActionListeners();
    }

    private void addActionListeners() {
        kvp.ZapamtiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapamti();

            }

            private void zapamti() {
                double cenaCasa = 0.0;
                String naziv = null;
                String kategorija = null;
                String cena = null;

                try {
                    cena = kvp.getjTextFieldCenaCasa().getText().trim();
                    naziv = kvp.getjTextFieldNaziv().getText().trim();
                    kategorija = kvp.getjTextFieldKategorija().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kvp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cena.trim().isEmpty() || naziv.trim().isEmpty() || kategorija.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kvp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30 || kategorija.trim().length() > 20) {

                    if (naziv.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kvp, "Naziv može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (kategorija.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kvp, "Kategorija može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                try {
                    cenaCasa = Double.parseDouble(cena);
                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(kvp, "Greška pri obradi cene časa. Proverite format u kome je uneta cena časa.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                VrstaPlesa vp = new VrstaPlesa(naziv, kategorija, cenaCasa);

                boolean uspesno = Komunikacija.getInstance().kreirajVrstaPlesa(vp);
                if (!uspesno) {
                    JOptionPane.showMessageDialog(kvp, "Sistem ne može da zapamti vrstu plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(kvp, "Sistem je zapamtio vrstu plesa.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    kvp.dispose();
                }
            }

        });
        kvp.PromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni();

            }

            private void promeni() {
                double cenaCasa = 0.0;
                String cena = null;
                String naziv = null;
                String kategorija = null;

                try {
                    cena = kvp.getjTextFieldCenaCasa().getText().trim();
                    naziv = kvp.getjTextFieldNaziv().getText().trim();
                    kategorija = kvp.getjTextFieldKategorija().getText().trim();

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(kvp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cena.trim().isEmpty() || naziv.trim().isEmpty() || kategorija.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(kvp, "Niste uneli sve podatke.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv.trim().length() > 30 || kategorija.trim().length() > 20) {

                    if (naziv.trim().length() > 30) {
                        JOptionPane.showMessageDialog(kvp, "Naziv može sadržati maksimalno 30 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }
                    if (kategorija.trim().length() > 20) {
                        JOptionPane.showMessageDialog(kvp, "Kategorija može sadržati maksimalno 20 karaktera.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                try {
                    cenaCasa = Double.parseDouble(cena);
                } catch (NumberFormatException numberFormatException) {

                    JOptionPane.showMessageDialog(kvp, "Greška pri obradi cene časa. Proverite format u kome je uneta cena časa.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                VrstaPlesa vp = new VrstaPlesa(kvp.getVrstaPlesa().getIdVrstaPlesa(), naziv, kategorija, cenaCasa);

                int odgovor = JOptionPane.showConfirmDialog(kvp, "Da li ste sigurni da želite da sačuvate unete promene?", "Potvrda", JOptionPane.YES_NO_OPTION);

                if (odgovor == 0) {
                    boolean uspesno = Komunikacija.getInstance().promeniVrstaPlesa(vp);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kvp, "Sistem ne može da zapamti vrstu plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kvp, "Sistem je zapamtio vrstu plesa.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kvp.dispose();
                    }
                }
            }

        });

        kvp.ObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi();

            }

            private void obrisi() {
                int odgovor = JOptionPane.showConfirmDialog(kvp, "Da li ste sigurni da želite da obrišete vrstu plesa?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (odgovor == 0) {
                    double cenaCasa = 0.0;
                    String naziv = null;
                    String kategorija = null;

                    naziv = kvp.getjTextFieldNaziv().getText().trim();
                    kategorija = kvp.getjTextFieldKategorija().getText().trim();
                    try {

                        cenaCasa = Double.parseDouble(kvp.getjTextFieldCenaCasa().getText().trim());

                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(kvp, "Greška pri obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    VrstaPlesa vp = new VrstaPlesa(kvp.getVrstaPlesa().getIdVrstaPlesa(), naziv, kategorija, cenaCasa);

                    boolean uspesno = Komunikacija.getInstance().obrisiVrstaPlesa(vp);
                    if (!uspesno) {
                        JOptionPane.showMessageDialog(kvp, "Sistem ne može da obriše vrstu plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(kvp, "Sistem je obrisao vrstu plesa.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        kvp.dispose();
                    }
                }
            }

        });

        pvp.VratiListuSvihVrstaPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihVrstaPlesa();

            }

            private void vratiListuSvihVrstaPlesa() {

                List<VrstaPlesa> lista = Komunikacija.getInstance().vratiListuSviVrstaPlesa();

                if (lista == null || lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pvp, "Sistem ne može da nađe sve vrste plesa.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pvp, "Sistem je našao sve vrste plesa.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleVrstePlesa mt = (ModelTabeleVrstePlesa) pvp.getjTable().getModel();

                if (lista.size() > 50) {
                    List<VrstaPlesa> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }
            }
        });

        pvp.VratiListuVrstaPlesaSaUslovomAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuvrstaPlesaSaUslovom();

            }

            private void vratiListuvrstaPlesaSaUslovom() {
                List<VrstaPlesa> lista = new ArrayList<>();
                String naziv = null;
                String kategorija = null;

                try {

                    if (!pvp.getjTextFieldKategorija().getText().trim().isEmpty()) {
                        kategorija = pvp.getjTextFieldKategorija().getText().trim();
                    }

                    if (!pvp.getjTextFieldNaziv().getText().trim().isEmpty()) {
                        naziv = pvp.getjTextFieldNaziv().getText().trim();
                    }

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(pvp, "Greška pri unosu ili obradi podataka.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ((kategorija == null || kategorija.trim().isEmpty()) && (naziv == null || naziv.trim().isEmpty())) {
                    JOptionPane.showMessageDialog(kvp, "Morate uneti bar jedan kriterijum za pretraživanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (naziv != null || kategorija != null) {
                    VrstaPlesa vp = new VrstaPlesa(naziv, kategorija);

                    List<VrstaPlesa> lista1 = Komunikacija.getInstance().vratiListuVrstaPlesaVrstaPlesa(vp);

                    for (VrstaPlesa v : lista1) {
                        if (!lista.contains(v)) {
                            lista.add(v);
                        }

                    }
                }

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(pvp, "Sistem ne može da nađe vrste plesa po zadatom kriterijumu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(pvp, "Sistem je našao vrste plesa po zadatom kriterijumu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);

                }

                ModelTabeleVrstePlesa mt = (ModelTabeleVrstePlesa) pvp.getjTable().getModel();

                if (lista.size() > 50) {
                    List<VrstaPlesa> lista1 = lista.subList(0, Math.min(50, lista.size()));
                    mt.setLista(lista1);
                    mt.refresujPodatke();
                } else {
                    mt.setLista(lista);
                    mt.refresujPodatke();
                }
            }
        });

        pvp.PrikaziVrstuPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziVrstuPlesa();

            }

            private void prikaziVrstuPlesa() {
                int selektovaniRed = pvp.getjTable().getSelectedRow();

                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(pvp, "Morate izabrati vrstu plesa iz tabele.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ModelTabeleVrstePlesa mt = (ModelTabeleVrstePlesa) pvp.getjTable().getModel();
                VrstaPlesa vp = mt.getLista().get(selektovaniRed);
                VrstaPlesa vpDetalji = Komunikacija.getInstance().pretraziVrstaPlesa(vp);
                if (vpDetalji == null) {
                    JOptionPane.showMessageDialog(pvp, "Sistem ne može da nađe vrstu plesa.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pvp.dispose();
                KontrolerGlavni.getInstance().pokreniKreirajVrstuPlesa(vpDetalji);

            }
        });

        kvp.OdustaniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odustani();

            }

            private void odustani() {
                int izbor = JOptionPane.showConfirmDialog(kvp, "Da li ste sigurni da želite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == 0) {
                    kvp.dispose();
                }
            }

        });
        kvp.ObrisiVrstuPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiVrstuPlesa();

            }

            private void obrisiVrstuPlesa() {
                kvp.getjButtonSacuvaj().setVisible(false);
                kvp.getjButtonZapamti().setVisible(false);
                kvp.getjButtonOdustani().setVisible(true);
                kvp.getjButtonObrisi().setVisible(true);
                kvp.getjButtonPromeni().setVisible(true);
                kvp.getjButtonObrisiVrstuPlesa().setVisible(false);
                kvp.getjButtonPrikaziObrisi().setVisible(true);
                kvp.getjButtonPrikaziPromeni().setVisible(false);
                kvp.getjTextFieldNaziv().setText(kvp.getVrstaPlesa().getNaziv());
                kvp.getjTextFieldKategorija().setText(kvp.getVrstaPlesa().getKategorija());
                kvp.getjTextFieldCenaCasa().setText(String.valueOf(kvp.getVrstaPlesa().getCenaCasa()));
                kvp.getjTextFieldCenaCasa().setEditable(false);
                kvp.getjTextFieldKategorija().setEditable(false);
                kvp.getjTextFieldNaziv().setEditable(false);
            }

        });

        kvp.PrikaziObrisiAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziObrisi();

            }

            private void prikaziObrisi() {
                kvp.getjButtonSacuvaj().setVisible(false);
                kvp.getjButtonZapamti().setVisible(false);
                kvp.getjButtonOdustani().setVisible(false);
                kvp.getjButtonObrisi().setVisible(false);
                kvp.getjButtonPromeni().setVisible(true);
                kvp.getjButtonObrisiVrstuPlesa().setVisible(true);
                kvp.getjButtonPrikaziObrisi().setVisible(false);
                kvp.getjButtonPrikaziPromeni().setVisible(false);
                kvp.getjTextFieldNaziv().setText(kvp.getVrstaPlesa().getNaziv());
                kvp.getjTextFieldKategorija().setText(kvp.getVrstaPlesa().getKategorija());
                kvp.getjTextFieldCenaCasa().setText(String.valueOf(kvp.getVrstaPlesa().getCenaCasa()));
                kvp.getjTextFieldCenaCasa().setEditable(false);
                kvp.getjTextFieldKategorija().setEditable(false);
                kvp.getjTextFieldNaziv().setEditable(false);
            }

        });
        kvp.PromeniVrstuPlesaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeniVrstuPlesa();

            }

            private void promeniVrstuPlesa() {
                kvp.getjButtonSacuvaj().setVisible(true);
                kvp.getjButtonZapamti().setVisible(false);
                kvp.getjButtonOdustani().setVisible(true);
                kvp.getjButtonObrisi().setVisible(false);
                kvp.getjButtonPromeni().setVisible(false);
                kvp.getjButtonObrisiVrstuPlesa().setVisible(true);
                kvp.getjButtonPrikaziObrisi().setVisible(false);
                kvp.getjButtonPrikaziPromeni().setVisible(true);
                kvp.getjTextFieldCenaCasa().setEditable(true);
                kvp.getjTextFieldKategorija().setEditable(true);
                kvp.getjTextFieldNaziv().setEditable(true);
            }

        });
        kvp.PrikaziPromeniAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziPromeni();

            }

            private void prikaziPromeni() {
                kvp.getjButtonSacuvaj().setVisible(false);
                kvp.getjButtonZapamti().setVisible(false);
                kvp.getjButtonOdustani().setVisible(false);
                kvp.getjButtonObrisi().setVisible(false);
                kvp.getjButtonPromeni().setVisible(true);
                kvp.getjButtonObrisiVrstuPlesa().setVisible(true);
                kvp.getjButtonPrikaziObrisi().setVisible(false);
                kvp.getjButtonPrikaziPromeni().setVisible(false);
                kvp.getjTextFieldNaziv().setText(kvp.getVrstaPlesa().getNaziv());
                kvp.getjTextFieldKategorija().setText(kvp.getVrstaPlesa().getKategorija());
                kvp.getjTextFieldCenaCasa().setText(String.valueOf(kvp.getVrstaPlesa().getCenaCasa()));
                kvp.getjTextFieldCenaCasa().setEditable(false);
                kvp.getjTextFieldKategorija().setEditable(false);
                kvp.getjTextFieldNaziv().setEditable(false);
            }

        });

    }

    public void prikaziFormuKreirajVrstuPlesa(VrstaPlesa vrstaPlesa) {

        if (vrstaPlesa == null) {

            kvp.getjButtonSacuvaj().setVisible(false);
            kvp.getjButtonZapamti().setVisible(true);
            kvp.getjButtonOdustani().setVisible(true);
            kvp.getjButtonObrisi().setVisible(false);
            kvp.getjButtonPromeni().setVisible(false);
            kvp.getjButtonObrisiVrstuPlesa().setVisible(false);
            kvp.getjButtonPrikaziObrisi().setVisible(false);
            kvp.getjButtonPrikaziPromeni().setVisible(false);
            JOptionPane.showMessageDialog(kvp, "Sistem je kreirao vrstu plesa.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            kvp.setVisible(true);

        } else {

            kvp.getjButtonSacuvaj().setVisible(false);
            kvp.getjButtonZapamti().setVisible(false);
            kvp.getjButtonOdustani().setVisible(false);
            kvp.getjButtonObrisi().setVisible(false);
            kvp.getjButtonPromeni().setVisible(true);
            kvp.getjButtonObrisiVrstuPlesa().setVisible(true);
            kvp.getjButtonPrikaziObrisi().setVisible(false);
            kvp.getjButtonPrikaziPromeni().setVisible(false);
            kvp.getjTextFieldNaziv().setText(vrstaPlesa.getNaziv());
            kvp.getjTextFieldKategorija().setText(vrstaPlesa.getKategorija());
            kvp.getjTextFieldCenaCasa().setText(String.valueOf(vrstaPlesa.getCenaCasa()));
            kvp.getjTextFieldCenaCasa().setEditable(false);
            kvp.getjTextFieldKategorija().setEditable(false);
            kvp.getjTextFieldNaziv().setEditable(false);

            try {
                JsonServis js = new JsonServis();
                double cenaEur = js.konvertujUEvre(vrstaPlesa.getCenaCasa());
                String poruka = String.format("Sistem je našao vrstu plesa.\nCena časa: %.2f RSD (%.2f EUR)",
                        vrstaPlesa.getCenaCasa(), cenaEur);
                JOptionPane.showMessageDialog(kvp, poruka, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(kvp, "Sistem je našao vrstu plesa.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            }

            kvp.setVisible(true);
        }

    }

    public void prikaziFormuPretraziVrstuPlesa() {

        popuniTabelu();
        pvp.setVisible(true);
    }

    private void popuniTabelu() {

        List<VrstaPlesa> vrste = new ArrayList<>();
        ModelTabeleVrstePlesa modelTabele = new ModelTabeleVrstePlesa(vrste);
        pvp.getjTable().setModel(modelTabele);

    }

}