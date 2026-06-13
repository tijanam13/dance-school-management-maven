/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;

/**
 *
 * @author Tijana
 */
public class GlavnaFormaKontroler {

    private final GlavnaForma gf;

    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();

    }

    private void addActionListeners() {
        gf.GlavnaFormaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odjava();

            }

            private void odjava() {
                Komunikacija.getInstance().odjaviSe(KontrolerGlavni.getInstance().getPrijavljeni());
                System.out.println("Odjavili ste se.");
                gf.dispose();
                KontrolerGlavni.getInstance().pokreniLoginForma();

            }
        });

    }

    public void prikaziFormu() {
        gf.getjLabelIme().setText("Zdravo, " + KontrolerGlavni.getInstance().getPrijavljeni().getIme() + " " + KontrolerGlavni.getInstance().getPrijavljeni().getPrezime() + "!");
        gf.setVisible(true);
    }

}
