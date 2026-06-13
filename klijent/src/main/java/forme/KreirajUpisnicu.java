/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import model.Instruktor;
import model.Polaznik;
import model.Upisnica;
import model.VrstaPlesa;

/**
 *
 * @author Tijana
 */
public class KreirajUpisnicu extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Upisnica upisnica;

    public Upisnica getUpisnica() {
        return upisnica;
    }

    public void setUpisnica(Upisnica upisnica) {
        this.upisnica = upisnica;
    }

    public JComboBox<String> getjComboBoxBrojCasova() {
        return jComboBoxBrojCasova;
    }

    public void setjComboBoxBrojCasova(JComboBox<String> jComboBoxBrojCasova) {
        this.jComboBoxBrojCasova = jComboBoxBrojCasova;
    }

    public JComboBox<Instruktor> getjComboBoxInstruktor() {
        return jComboBoxInstruktor;
    }

    public void setjComboBoxInstruktor(JComboBox<Instruktor> jComboBoxInstruktor) {
        this.jComboBoxInstruktor = jComboBoxInstruktor;
    }

    public JComboBox<Polaznik> getjComboBoxPolaznik() {
        return jComboBoxPolaznik;
    }

    public void setjComboBoxPolaznik(JComboBox<Polaznik> jComboBoxPolaznik) {
        this.jComboBoxPolaznik = jComboBoxPolaznik;
    }

    public JComboBox<VrstaPlesa> getjComboBoxVrstaPlesa() {
        return jComboBoxVrstaPlesa;
    }

    public void setjComboBoxVrstaPlesa(JComboBox<VrstaPlesa> jComboBoxVrstaPlesa) {
        this.jComboBoxVrstaPlesa = jComboBoxVrstaPlesa;
    }

    public JTable getjTableStavke() {
        return jTableStavke;
    }

    public void setjTableStavke(JTable jTableStavke) {
        this.jTableStavke = jTableStavke;
    }

    public JTextField getjTextFieldDatumUpisa() {
        return jTextFieldDatumUpisa;
    }

    public void setjTextFieldDatumUpisa(JTextField jTextFieldDatumUpisa) {
        this.jTextFieldDatumUpisa = jTextFieldDatumUpisa;
    }

    public JTextField getjTextFieldUkupnaClanarina() {
        return jTextFieldUkupnaClanarina;
    }

    public void setjTextFieldUkupnaClanarina(JTextField jTextFieldUkupnaClanarina) {
        this.jTextFieldUkupnaClanarina = jTextFieldUkupnaClanarina;
    }

    public JButton getjButtonDodajStavku() {
        return jButtonDodajStavku;
    }

    public void setjButtonDodajStavku(JButton jButtonDodajStavku) {
        this.jButtonDodajStavku = jButtonDodajStavku;
    }

    public JButton getjButtonObrisiStavku() {
        return jButtonObrisiStavku;
    }

    public void setjButtonObrisiStavku(JButton jButtonObrisiStavku) {
        this.jButtonObrisiStavku = jButtonObrisiStavku;
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public void setjButtonOdustani(JButton jButtonOdustani) {
        this.jButtonOdustani = jButtonOdustani;
    }

    public JButton getjButtonPrikazi() {
        return jButtonPrikazi;
    }

    public void setjButtonPrikazi(JButton jButtonPrikazi) {
        this.jButtonPrikazi = jButtonPrikazi;
    }

    public JButton getjButtonPromeni() {
        return jButtonPromeni;
    }

    public void setjButtonPromeni(JButton jButtonPromeni) {
        this.jButtonPromeni = jButtonPromeni;
    }

    public JButton getjButtonSacuvaj() {
        return jButtonSacuvaj;
    }

    public void setjButtonSacuvaj(JButton jButtonSacuvaj) {
        this.jButtonSacuvaj = jButtonSacuvaj;
    }

    public JButton getjButtonZapamti() {
        return jButtonZapamti;
    }

    public void setjButtonZapamti(JButton jButtonZapamti) {
        this.jButtonZapamti = jButtonZapamti;
    }

    /**
     * Creates new form KreirajUpisnicu
     * @param parent
     * @param modal
     * @param upisnica
     */
    public KreirajUpisnicu(java.awt.Frame parent, boolean modal, Upisnica upisnica) {
        super(parent, modal);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.err.println("Greška prilikom postavljanja Nimbus dizajna: " + ex.getMessage());
        }
        initComponents();
        this.setSize(720, 680);

        this.setLocationRelativeTo(null);
        setResizable(false);

        this.setTitle("Kreiraj upisnicu");
        this.gf = (GlavnaForma) parent;
        this.upisnica = upisnica;

        jTableStavke.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Get the default rendering component
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Set the text color for specific rows
                if (!isSelected) {
                    if (row % 2 == 0) { // For even rows
                        cell.setBackground(Color.WHITE);
                    } else { // For odd rows
                        cell.setBackground(new Color(204, 255, 255));
                    }
                }
                // Highlight the selected row
                if (isSelected) {

                    cell.setBackground(new Color(204, 204, 204));
                }

                cell.setForeground(new Color(0, 102, 153));

                return cell;
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStavke = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxBrojCasova = new javax.swing.JComboBox<>();
        jButtonObrisiStavku = new javax.swing.JButton();
        jButtonDodajStavku = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxVrstaPlesa = new javax.swing.JComboBox<>();
        jComboBoxPolaznik = new javax.swing.JComboBox<>();
        jComboBoxInstruktor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUkupnaClanarina = new javax.swing.JTextField();
        jTextFieldDatumUpisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonOdustani = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSacuvaj = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonPromeni = new javax.swing.JButton();
        jButtonPrikazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableStavke.setRowHeight(25);
        jTableStavke.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableStavke.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableStavke.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableStavke);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 670, 190));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Stavka upisnice");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Vrsta plesa:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 50));

        jComboBoxBrojCasova.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxBrojCasova.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8", "9", "10", "11", "12" }));
        jPanel1.add(jComboBoxBrojCasova, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 60, 27));

        jButtonObrisiStavku.setBackground(new java.awt.Color(0, 102, 153));
        jButtonObrisiStavku.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonObrisiStavku.setForeground(new java.awt.Color(255, 255, 255));
        jButtonObrisiStavku.setText("Obriši stavku");
        jButtonObrisiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiStavkuActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonObrisiStavku, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 110, -1));

        jButtonDodajStavku.setBackground(new java.awt.Color(0, 102, 153));
        jButtonDodajStavku.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDodajStavku.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDodajStavku.setText("Dodaj stavku");
        jButtonDodajStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajStavkuActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDodajStavku, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Broj časova:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, 30));

        jComboBoxVrstaPlesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVrstaPlesaActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxVrstaPlesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 200, 27));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 340, 210));

        jPanel2.add(jComboBoxPolaznik, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 180, 27));

        jComboBoxInstruktor.setPreferredSize(new java.awt.Dimension(72, 10));
        jComboBoxInstruktor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxInstruktorActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBoxInstruktor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, 27));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Polaznik:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 130));

        jTextFieldUkupnaClanarina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUkupnaClanarinaActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldUkupnaClanarina, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 160, 27));

        jTextFieldDatumUpisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDatumUpisaActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldDatumUpisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 580, 160, 27));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Datum upisa (dd.MM.yyyy.) :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 190, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ukupna članarina:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 130, 30));

        jButtonOdustani.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonOdustani.setForeground(new java.awt.Color(0, 102, 153));
        jButtonOdustani.setText("Odustani");
        jButtonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdustaniActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 580, 130, 30));

        jButtonZapamti.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonZapamti.setForeground(new java.awt.Color(0, 102, 153));
        jButtonZapamti.setText("Zapamti");
        jButtonZapamti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZapamtiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 130, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Instruktor:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("UPISNICA");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, -1));

        jButtonSacuvaj.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSacuvaj.setForeground(new java.awt.Color(0, 102, 153));
        jButtonSacuvaj.setText("Sačuvaj promene");
        jButtonSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSacuvajActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 130, 30));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 670, 190));

        jButtonPromeni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPromeni.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPromeni.setText("Promeni upisnicu");
        jButtonPromeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPromeniActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, 130, 30));

        jButtonPrikazi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPrikazi.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPrikazi.setText("Prikaži upisnicu");
        jButtonPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPrikazi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, 130, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonZapamtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZapamtiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonZapamtiActionPerformed

    private void jTextFieldDatumUpisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDatumUpisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDatumUpisaActionPerformed

    private void jButtonDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajStavkuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonDodajStavkuActionPerformed

    private void jButtonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdustaniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOdustaniActionPerformed

    private void jTextFieldUkupnaClanarinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUkupnaClanarinaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextFieldUkupnaClanarinaActionPerformed

    private void jButtonSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSacuvajActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonSacuvajActionPerformed

    private void jButtonObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiStavkuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonObrisiStavkuActionPerformed

    private void jComboBoxInstruktorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxInstruktorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxInstruktorActionPerformed

    private void jButtonPromeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPromeniActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPromeniActionPerformed

    private void jButtonPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPrikaziActionPerformed

    private void jComboBoxVrstaPlesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVrstaPlesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVrstaPlesaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajStavku;
    private javax.swing.JButton jButtonObrisiStavku;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikazi;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JComboBox<String> jComboBoxBrojCasova;
    private javax.swing.JComboBox<Instruktor> jComboBoxInstruktor;
    private javax.swing.JComboBox<Polaznik> jComboBoxPolaznik;
    private javax.swing.JComboBox<VrstaPlesa> jComboBoxVrstaPlesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStavke;
    private javax.swing.JTextField jTextFieldDatumUpisa;
    private javax.swing.JTextField jTextFieldUkupnaClanarina;
    // End of variables declaration//GEN-END:variables

    public void ZapamtiAddActionListeners(ActionListener actionListener) {
        jButtonZapamti.addActionListener(actionListener);
    }

    public void PromeniAddActionListeners(ActionListener actionListener) {
        jButtonSacuvaj.addActionListener(actionListener);
    }

    public void OdustaniAddActionListeners(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void PrikaziAddActionListeners(ActionListener actionListener) {
        jButtonPrikazi.addActionListener(actionListener);
    }

    public void ObrisiStavkuAddActionListeners(ActionListener actionListener) {
        jButtonObrisiStavku.addActionListener(actionListener);
    }

    public void DodajStavkuAddActionListeners(ActionListener actionListener) {
        jButtonDodajStavku.addActionListener(actionListener);
    }

    public void PromeniUpisnicuAddActionListeners(ActionListener actionListener) {
        jButtonPromeni.addActionListener(actionListener);
    }

}
