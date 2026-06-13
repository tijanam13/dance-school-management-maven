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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import model.Instruktor;
import model.Kvalifikacija;
import model.Nivo;

/**
 *
 * @author Tijana
 */
public class KreirajInstruktora extends javax.swing.JDialog {

    private GlavnaForma gf;
    private Instruktor instruktor;
    private boolean prikazi = true;

    public boolean getPrikazi() {
        return prikazi;
    }

    public void setPrikazi(boolean prikazi) {
        this.prikazi = prikazi;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public JButton getjButtonDodaj() {
        return jButtonDodaj;
    }

    public void setjButtonDodaj(JButton jButtonDodaj) {
        this.jButtonDodaj = jButtonDodaj;
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public void setjButtonObrisi(JButton jButtonObrisi) {
        this.jButtonObrisi = jButtonObrisi;
    }

    public JButton getjButtonObrisiInstruktora() {
        return jButtonObrisiInstruktora;
    }

    public void setjButtonObrisiInstruktora(JButton jButtonObrisiInstruktora) {
        this.jButtonObrisiInstruktora = jButtonObrisiInstruktora;
    }

    public JButton getjButtonObrisiKvalifikaciju() {
        return jButtonObrisiKvalifikaciju;
    }

    public void setjButtonObrisiKvalifikaciju(JButton jButtonObrisiKvalifikaciju) {
        this.jButtonObrisiKvalifikaciju = jButtonObrisiKvalifikaciju;
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public void setjButtonOdustani(JButton jButtonOdustani) {
        this.jButtonOdustani = jButtonOdustani;
    }

    public JButton getjButtonPrikaziSifru() {
        return jButtonPrikaziSifru;
    }

    public void setjButtonPrikaziSifru(JButton jButtonPrikazi) {
        this.jButtonPrikaziSifru = jButtonPrikazi;
    }

    public JButton getjButtonPrikaziObrisi() {
        return jButtonPrikaziObrisi;
    }

    public void setjButtonPrikaziObrisi(JButton jButtonPrikaziObrisi) {
        this.jButtonPrikaziObrisi = jButtonPrikaziObrisi;
    }

    public JButton getjButtonPrikaziPromeni() {
        return jButtonPrikaziPromeni;
    }

    public void setjButtonPrikaziPromeni(JButton jButtonPrikaziPromeni) {
        this.jButtonPrikaziPromeni = jButtonPrikaziPromeni;
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

    public JComboBox<Nivo> getjComboBoxNivo() {
        return jComboBoxNivo;
    }

    public void setjComboBoxNivo(JComboBox<Nivo> jComboBoxNivo) {
        this.jComboBoxNivo = jComboBoxNivo;
    }

    public JComboBox<Kvalifikacija> getjComboBoxKvalifikacije() {
        return jComboBoxKvalifikacije;
    }

    public void setjComboBoxKvalifikacije(JComboBox<Kvalifikacija> jComboBoxKvalifikacije) {
        this.jComboBoxKvalifikacije = jComboBoxKvalifikacije;
    }

    public JPasswordField getjPasswordField() {
        return jPasswordField;
    }

    public void setjPasswordField(JPasswordField jPasswordField) {
        this.jPasswordField = jPasswordField;
    }

    public JTable getjTableKvalifikacija() {
        return jTableKvalifikacija;
    }

    public void setjTableKvalifikacija(JTable jTableKvalifikacija) {
        this.jTableKvalifikacija = jTableKvalifikacija;
    }

    public JTextField getjTextFieldDatumSticanja() {
        return jTextFieldDatumSticanja;
    }

    public void setjTextFieldDatumSticanja(JTextField jTextFieldDatumSticanja) {
        this.jTextFieldDatumSticanja = jTextFieldDatumSticanja;
    }

    public JTextField getjTextFieldEmail() {
        return jTextFieldEmail;
    }

    public void setjTextFieldEmail(JTextField jTextFieldEmail) {
        this.jTextFieldEmail = jTextFieldEmail;
    }

    public JTextField getjTextFieldIme() {
        return jTextFieldIme;
    }

    public void setjTextFieldIme(JTextField jTextFieldIme) {
        this.jTextFieldIme = jTextFieldIme;
    }

    public JTextField getjTextFieldKorisnickoIme() {
        return jTextFieldKorisnickoIme;
    }

    public void setjTextFieldKorisnickoIme(JTextField jTextFieldKorisnickoIme) {
        this.jTextFieldKorisnickoIme = jTextFieldKorisnickoIme;
    }

    public JTextField getjTextFieldPrezime() {
        return jTextFieldPrezime;
    }

    public void setjTextFieldPrezime(JTextField jTextFieldPrezime) {
        this.jTextFieldPrezime = jTextFieldPrezime;
    }

    public JButton getjButtonDodajNovuKvalifikaciju() {
        return jButtonDodajNovuKvalifikaciju;
    }

    public void setjButtonDodajNovuKvalifikaciju(JButton jButtonDodajNovuKvalifikaciju) {
        this.jButtonDodajNovuKvalifikaciju = jButtonDodajNovuKvalifikaciju;
    }

    /**
     * Creates new form KreirajInstruktor
     *
     * @param parent
     * @param modal
     * @param instruktor
     */
    public KreirajInstruktora(java.awt.Frame parent, boolean modal, Instruktor instruktor) {
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

        this.setSize(1100, 565);
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setTitle("Kreiraj instruktora");

        this.gf = (GlavnaForma) parent;
        this.instruktor = instruktor;

        jTableKvalifikacija.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        cell.setBackground(Color.WHITE);
                    } else {
                        cell.setBackground(new Color(204, 255, 255));
                    }
                }
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

        jFrame1 = new javax.swing.JFrame();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButtonObrisiKvalifikaciju = new javax.swing.JButton();
        jButtonDodaj = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxNivo = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDatumSticanja = new javax.swing.JTextField();
        jButtonDodajNovuKvalifikaciju = new javax.swing.JButton();
        jComboBoxKvalifikacije = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableKvalifikacija = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldIme = new javax.swing.JTextField();
        jButtonSacuvaj = new javax.swing.JButton();
        jButtonZapamti = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonOdustani = new javax.swing.JButton();
        jButtonObrisiInstruktora = new javax.swing.JButton();
        jButtonPromeni = new javax.swing.JButton();
        jButtonPrikaziObrisi = new javax.swing.JButton();
        jButtonPrikaziPromeni = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldKorisnickoIme = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldPrezime = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jButtonPrikaziSifru = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonObrisiKvalifikaciju.setBackground(new java.awt.Color(0, 102, 153));
        jButtonObrisiKvalifikaciju.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonObrisiKvalifikaciju.setForeground(new java.awt.Color(255, 255, 255));
        jButtonObrisiKvalifikaciju.setText("Obriši");
        jButtonObrisiKvalifikaciju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiKvalifikacijuActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonObrisiKvalifikaciju, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 90, 30));

        jButtonDodaj.setBackground(new java.awt.Color(0, 102, 153));
        jButtonDodaj.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDodaj.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDodaj.setText("Dodaj");
        jButtonDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDodaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 90, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 153));
        jLabel19.setText("Datum sticanja (dd.MM.yyyy.) :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 60));

        jPanel1.add(jComboBoxNivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 110, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 153));
        jLabel20.setText("Nivo:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        jPanel1.add(jTextFieldDatumSticanja, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 110, 25));

        jButtonDodajNovuKvalifikaciju.setBackground(new java.awt.Color(0, 102, 153));
        jButtonDodajNovuKvalifikaciju.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDodajNovuKvalifikaciju.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDodajNovuKvalifikaciju.setText("Dodaj novu kvalifikaciju");
        jButtonDodajNovuKvalifikaciju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajNovuKvalifikacijuActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDodajNovuKvalifikaciju, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 200, 30));

        jComboBoxKvalifikacije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKvalifikacijeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxKvalifikacije, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 560, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("KVALIFIKACIJA");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 20, 120, -1));

        jTableKvalifikacija.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableKvalifikacija.setName(""); // NOI18N
        jTableKvalifikacija.setRowHeight(25);
        jTableKvalifikacija.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableKvalifikacija.setShowGrid(false);
        jTableKvalifikacija.getTableHeader().setResizingAllowed(false);
        jTableKvalifikacija.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTableKvalifikacija);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 560, 180));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 580, 460));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jTextFieldIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 142, 320, 29));

        jButtonSacuvaj.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSacuvaj.setForeground(new java.awt.Color(0, 102, 153));
        jButtonSacuvaj.setText("Sačuvaj");
        jButtonSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSacuvajActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 150, 30));

        jButtonZapamti.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonZapamti.setForeground(new java.awt.Color(0, 102, 153));
        jButtonZapamti.setText("Zapamti");
        jButtonZapamti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZapamtiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonZapamti, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 150, 30));

        jButtonObrisi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonObrisi.setForeground(new java.awt.Color(0, 102, 153));
        jButtonObrisi.setText("Obriši");
        jButtonObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 150, 30));

        jButtonOdustani.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonOdustani.setForeground(new java.awt.Color(0, 102, 153));
        jButtonOdustani.setText("Odustani");
        jButtonOdustani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdustaniActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 150, 30));

        jButtonObrisiInstruktora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonObrisiInstruktora.setForeground(new java.awt.Color(0, 102, 153));
        jButtonObrisiInstruktora.setText("Obriši instruktora");
        jButtonObrisiInstruktora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiInstruktoraActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonObrisiInstruktora, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 150, 30));

        jButtonPromeni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPromeni.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPromeni.setText("Promeni instruktora");
        jButtonPromeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPromeniActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 150, 30));

        jButtonPrikaziObrisi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPrikaziObrisi.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPrikaziObrisi.setText("Prikaži instruktora");
        jButtonPrikaziObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziObrisiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPrikaziObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 150, 30));

        jButtonPrikaziPromeni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPrikaziPromeni.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPrikaziPromeni.setText("Prikaži instruktora");
        jButtonPrikaziPromeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziPromeniActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPrikaziPromeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 150, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("INSTRUKTOR");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 25, 137, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ime:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 37, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Prezime:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 87, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("E-mail adresa:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 92, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Korisničko ime:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Šifra:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 37, 30));

        jTextFieldKorisnickoIme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKorisnickoImeActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldKorisnickoIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 292, 320, 29));

        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 242, 320, 29));
        jPanel2.add(jTextFieldPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 192, 320, 29));
        jPanel2.add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 342, 210, 29));

        jButtonPrikaziSifru.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPrikaziSifru.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPrikaziSifru.setText("Prikaži šifru");
        jButtonPrikaziSifru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrikaziSifruActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPrikaziSifru, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 100, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSacuvajActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonSacuvajActionPerformed

    private void jButtonZapamtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZapamtiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonZapamtiActionPerformed

    private void jButtonOdustaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdustaniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOdustaniActionPerformed

    private void jButtonObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonObrisiActionPerformed

    private void jButtonObrisiInstruktoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiInstruktoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonObrisiInstruktoraActionPerformed

    private void jButtonPromeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPromeniActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPromeniActionPerformed

    private void jButtonPrikaziObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziObrisiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPrikaziObrisiActionPerformed

    private void jButtonPrikaziPromeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziPromeniActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPrikaziPromeniActionPerformed

    private void jTextFieldKorisnickoImeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKorisnickoImeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKorisnickoImeActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButtonPrikaziSifruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrikaziSifruActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonPrikaziSifruActionPerformed

    private void jButtonObrisiKvalifikacijuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiKvalifikacijuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonObrisiKvalifikacijuActionPerformed

    private void jButtonDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDodajActionPerformed

    private void jButtonDodajNovuKvalifikacijuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajNovuKvalifikacijuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDodajNovuKvalifikacijuActionPerformed

    private void jComboBoxKvalifikacijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKvalifikacijeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxKvalifikacijeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodaj;
    private javax.swing.JButton jButtonDodajNovuKvalifikaciju;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonObrisiInstruktora;
    private javax.swing.JButton jButtonObrisiKvalifikaciju;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonPrikaziObrisi;
    private javax.swing.JButton jButtonPrikaziPromeni;
    private javax.swing.JButton jButtonPrikaziSifru;
    private javax.swing.JButton jButtonPromeni;
    private javax.swing.JButton jButtonSacuvaj;
    private javax.swing.JButton jButtonZapamti;
    private javax.swing.JComboBox<Kvalifikacija> jComboBoxKvalifikacije;
    private javax.swing.JComboBox<Nivo> jComboBoxNivo;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableKvalifikacija;
    private javax.swing.JTextField jTextFieldDatumSticanja;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIme;
    private javax.swing.JTextField jTextFieldKorisnickoIme;
    private javax.swing.JTextField jTextFieldPrezime;
    // End of variables declaration//GEN-END:variables

    public void ZapamtiAddActionListeners(ActionListener actionListener) {
        jButtonZapamti.addActionListener(actionListener);
    }

    public void PromeniAddActionListeners(ActionListener actionListener) {
        jButtonSacuvaj.addActionListener(actionListener);
    }

    public void ObrisiAddActionListeners(ActionListener actionListener) {
        jButtonObrisi.addActionListener(actionListener);
    }

    public void ObrisiInstruktoraAddActionListeners(ActionListener actionListener) {
        jButtonObrisiInstruktora.addActionListener(actionListener);
    }

    public void PrikaziObrisiAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziObrisi.addActionListener(actionListener);
    }

    public void PrikaziPromeniAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziPromeni.addActionListener(actionListener);
    }

    public void PromeniInstruktoraAddActionListeners(ActionListener actionListener) {
        jButtonPromeni.addActionListener(actionListener);
    }

    public void DodajNovuKvalifikacijuAddActionListeners(ActionListener actionListener) {
        jButtonDodajNovuKvalifikaciju.addActionListener(actionListener);
    }

    public void OdustaniAddActionListeners(ActionListener actionListener) {
        jButtonOdustani.addActionListener(actionListener);
    }

    public void PrikaziSifruAddActionListeners(ActionListener actionListener) {
        jButtonPrikaziSifru.addActionListener(actionListener);
    }

    public void ObrisiKvalifikacijuAddActionListeners(ActionListener actionListener) {
        jButtonObrisiKvalifikaciju.addActionListener(actionListener);
    }

    public void DodajKvalifikacijuAddActionListeners(ActionListener actionListener) {
        jButtonDodaj.addActionListener(actionListener);
    }

}
