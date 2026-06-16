
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import komunikacija.Komunikacija;
import kontrolerGlavni.KontrolerGlavni;
import model.Gradijent;

/**
 *
 * @author Tijana
 */
public class GlavnaForma extends javax.swing.JFrame {

    public JLabel getjLabelIme() {
        return jLabelIme;
    }

    public void setjLabelIme(JLabel jLabelIme) {
        this.jLabelIme = jLabelIme;
    }

    public GlavnaForma() {
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
        setTitle("Glavna stranica");
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                Komunikacija.getInstance().odjaviSe(KontrolerGlavni.getInstance().getPrijavljeni());
                System.exit(0);

            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int sirina = getWidth();
                int visina = getHeight();

                int font1 = Math.min(sirina / 25, visina / 25);
                int font2 = Math.min(sirina / 30, visina / 30);
                int font3 = Math.min(sirina / 40, visina / 40);

                Color boja = new Color(0, 102, 153);
                jLabel1.setFont(new Font("Gabriola", Font.BOLD, font1));
                jLabelIme.setFont(new Font("Gabriola", Font.BOLD, font1));

                jMenu1.setFont(new Font("Segoe UI", Font.BOLD, font3));
                jMenu1.setForeground(boja);
                jMenu1.setPreferredSize(new Dimension(getWidth() / 10, jMenuBar1.getHeight()));
                jMenu2.setFont(new Font("Segoe UI", Font.BOLD, font3));

                jMenu2.setForeground(boja);
                jMenu2.setPreferredSize(new Dimension(getWidth() / 7, jMenuBar1.getHeight()));
                jMenu3.setFont(new Font("Segoe UI", Font.BOLD, font3));

                jMenu3.setForeground(boja);
                jMenu3.setPreferredSize(new Dimension(getWidth() / 7, jMenuBar1.getHeight()));
                jMenu4.setFont(new Font("Segoe UI", Font.BOLD, font3));

                jMenu4.setForeground(boja);
                jMenu4.setPreferredSize(new Dimension(getWidth() / 11, jMenuBar1.getHeight()));
                jMenu18.setFont(new Font("Segoe UI", Font.BOLD, font3));

                jMenu18.setForeground(boja);
                jMenu18.setPreferredSize(new Dimension(getWidth() / 4, jMenuBar1.getHeight()));
                jMenu19.setFont(new Font("Segoe UI", Font.BOLD, font3));

                jMenu19.setForeground(boja);
                jMenu19.setPreferredSize(new Dimension(getWidth() / 9, jMenuBar1.getHeight()));

                jMenu10.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu10.setForeground(boja);

                jMenu11.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu11.setForeground(boja);

                jMenu12.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu12.setForeground(boja);

                jMenu5.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu5.setForeground(boja);

                jMenu6.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu6.setForeground(boja);

                jMenu9.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenu9.setForeground(boja);

                jMenuItemInformacije.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemInformacije.setForeground(boja);

                jMenuItemKonfiguracija.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKonfiguracija.setForeground(boja);

                jMenuItemKreirajInstruktora.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajInstruktora.setForeground(boja);

                jMenuItemKreirajKvalifikacija.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajKvalifikacija.setForeground(boja);

                jMenuItemKreirajPolaznik.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajPolaznik.setForeground(boja);

                jMenuItemKreirajUpisnica.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajUpisnica.setForeground(boja);

                jMenuItemKreirajUzrast.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajUzrast.setForeground(boja);

                jMenuItemKreirajVrstaPlesa.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajVrstaPlesa.setForeground(boja);

                jMenuItemPretraziVrstaPlesa.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziVrstaPlesa.setForeground(boja);

                jMenuItemPretraziInstruktora.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziInstruktora.setForeground(boja);

                jMenuItemPretraziKvalifikacija.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziKvalifikacija.setForeground(boja);

                jMenuItemPretraziPolaznik.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziPolaznik.setForeground(boja);

                jMenuItemPretraziUpisnica.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziUpisnica.setForeground(boja);

                jMenuItemPretraziUzrast.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziUzrast.setForeground(boja);

                jMenuItemPretraziVrstaPlesa.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziVrstaPlesa.setForeground(boja);

                jMenu7.setFont(new Font("Segoe UI", Font.BOLD, font3));
                jMenu7.setForeground(boja);
                jMenu7.setPreferredSize(new Dimension(getWidth() / 11, jMenuBar1.getHeight()));

                jMenuItemKreirajSertifikat.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemKreirajSertifikat.setForeground(boja);
                jMenuItemPretraziSertifikat.setFont(new Font("Segoe UI", Font.PLAIN, font3));
                jMenuItemPretraziSertifikat.setForeground(boja);

                jButtonOdjaviSe.setFont(new Font("Segoe UI", Font.BOLD, font2));

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

        jPanel1 = new Gradijent();
        jLabelIme = new javax.swing.JLabel();
        jButtonOdjaviSe = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItemKreirajUpisnica = new javax.swing.JMenuItem();
        jMenuItemPretraziUpisnica = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItemKreirajPolaznik = new javax.swing.JMenuItem();
        jMenuItemPretraziPolaznik = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItemKreirajInstruktora = new javax.swing.JMenuItem();
        jMenuItemPretraziInstruktora = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItemKreirajVrstaPlesa = new javax.swing.JMenuItem();
        jMenuItemPretraziVrstaPlesa = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItemKreirajKvalifikacija = new javax.swing.JMenuItem();
        jMenuItemPretraziKvalifikacija = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItemKreirajUzrast = new javax.swing.JMenuItem();
        jMenuItemPretraziUzrast = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItemKreirajSertifikat = new javax.swing.JMenuItem();
        jMenuItemPretraziSertifikat = new javax.swing.JMenuItem();
        jMenu18 = new javax.swing.JMenu();
        jMenuItemKonfiguracija = new javax.swing.JMenuItem();
        jMenu19 = new javax.swing.JMenu();
        jMenuItemInformacije = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 153));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelIme.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabelIme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonOdjaviSe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonOdjaviSe.setForeground(new java.awt.Color(0, 102, 153));
        jButtonOdjaviSe.setText("Odjavi se");
        jButtonOdjaviSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdjaviSeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gabriola", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dobrodošli u aplikaciju za praćenje rada plesne škole.");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/Glavna_forma_2.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/slike/Glavna_forma_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(jButtonOdjaviSe, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabelIme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(jLabelIme, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonOdjaviSe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap())))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 102, 153));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(500, 50));

        jMenu1.setBackground(new java.awt.Color(0, 102, 153));
        jMenu1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jMenu1.setText("Dokumenti");
        jMenu1.setAlignmentX(2.0F);
        jMenu1.setMinimumSize(new java.awt.Dimension(200, 150));
        jMenu1.setPreferredSize(new java.awt.Dimension(200, 250));

        jMenu9.setBackground(new java.awt.Color(0, 102, 153));
        jMenu9.setText("Upisnica");

        jMenuItemKreirajUpisnica.setBackground(new java.awt.Color(0, 102, 153));
        jMenuItemKreirajUpisnica.setText("Kreiraj");
        jMenuItemKreirajUpisnica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajUpisnicaActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItemKreirajUpisnica);

        jMenuItemPretraziUpisnica.setText("Pretraži");
        jMenuItemPretraziUpisnica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziUpisnicaActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItemPretraziUpisnica);

        jMenu1.add(jMenu9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Primalac usluge");
        jMenu2.setPreferredSize(new java.awt.Dimension(150, 22));

        jMenu12.setText("Polaznik");

        jMenuItemKreirajPolaznik.setText("Kreiraj");
        jMenuItemKreirajPolaznik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajPolaznikActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItemKreirajPolaznik);

        jMenuItemPretraziPolaznik.setText("Pretraži");
        jMenuItemPretraziPolaznik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziPolaznikActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItemPretraziPolaznik);

        jMenu2.add(jMenu12);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pružalac usluge");
        jMenu3.setPreferredSize(new java.awt.Dimension(150, 22));

        jMenu10.setText("Instruktor");

        jMenuItemKreirajInstruktora.setText("Kreiraj");
        jMenuItemKreirajInstruktora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajInstruktoraActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItemKreirajInstruktora);

        jMenuItemPretraziInstruktora.setText("Pretraži");
        jMenuItemPretraziInstruktora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziInstruktoraActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItemPretraziInstruktora);

        jMenu3.add(jMenu10);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Šifarnici");
        jMenu4.setPreferredSize(new java.awt.Dimension(150, 22));

        jMenu5.setText("Vrsta plesa");

        jMenuItemKreirajVrstaPlesa.setText("Kreiraj");
        jMenuItemKreirajVrstaPlesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajVrstaPlesaActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemKreirajVrstaPlesa);

        jMenuItemPretraziVrstaPlesa.setText("Pretraži");
        jMenuItemPretraziVrstaPlesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziVrstaPlesaActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItemPretraziVrstaPlesa);

        jMenu4.add(jMenu5);

        jMenu6.setText("Kvalifikacija");
        jMenu6.setFocusCycleRoot(true);

        jMenuItemKreirajKvalifikacija.setText("Kreiraj");
        jMenuItemKreirajKvalifikacija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajKvalifikacijaActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemKreirajKvalifikacija);

        jMenuItemPretraziKvalifikacija.setText("Pretraži");
        jMenuItemPretraziKvalifikacija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziKvalifikacijaActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemPretraziKvalifikacija);

        jMenu4.add(jMenu6);

        jMenu11.setText("Uzrasna kategorija");

        jMenuItemKreirajUzrast.setText("Kreiraj");
        jMenuItemKreirajUzrast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajUzrastActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItemKreirajUzrast);

        jMenuItemPretraziUzrast.setText("Pretraži");
        jMenuItemPretraziUzrast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziUzrastActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItemPretraziUzrast);

        jMenu4.add(jMenu11);

        jMenu7.setText("Sertifikat");
        jMenu7.setPreferredSize(new java.awt.Dimension(150, 22));

        jMenuItemKreirajSertifikat.setText("Kreiraj");
        jMenuItemKreirajSertifikat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKreirajSertifikatActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItemKreirajSertifikat);

        jMenuItemPretraziSertifikat.setText("Pretraži");
        jMenuItemPretraziSertifikat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziSertifikatActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItemPretraziSertifikat);

        jMenu4.add(jMenu7);

        jMenuBar1.add(jMenu4);

        jMenu18.setText("Podešavanja softverskog sistema");
        jMenu18.setPreferredSize(new java.awt.Dimension(200, 22));

        jMenuItemKonfiguracija.setText("Konfiguracija");
        jMenuItemKonfiguracija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKonfiguracijaActionPerformed(evt);
            }
        });
        jMenu18.add(jMenuItemKonfiguracija);

        jMenuBar1.add(jMenu18);

        jMenu19.setText("O programu");
        jMenu19.setPreferredSize(new java.awt.Dimension(150, 22));
        jMenu19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu19ActionPerformed(evt);
            }
        });

        jMenuItemInformacije.setText("Autor");
        jMenuItemInformacije.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformacijeActionPerformed(evt);
            }
        });
        jMenu19.add(jMenuItemInformacije);

        jMenuBar1.add(jMenu19);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(954, 506));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemKreirajPolaznikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajPolaznikActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajPolaznika(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira polaznika.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajPolaznikActionPerformed

    private void jMenuItemPretraziPolaznikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziPolaznikActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziPolaznika();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje polaznika.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziPolaznikActionPerformed

    private void jMenuItemPretraziInstruktoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziInstruktoraActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretrazinstruktora();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje instruktora.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziInstruktoraActionPerformed

    private void jMenuItemKreirajInstruktoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajInstruktoraActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajInstruktora(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira instruktora.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajInstruktoraActionPerformed

    private void jMenuItemKreirajVrstaPlesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajVrstaPlesaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajVrstuPlesa(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira vrstu plesa.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajVrstaPlesaActionPerformed

    private void jMenuItemPretraziVrstaPlesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziVrstaPlesaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziVrstuPlesa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje vrsta plesa.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziVrstaPlesaActionPerformed

    private void jMenuItemKreirajKvalifikacijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajKvalifikacijaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajKvalifikaciju(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira kvalifikaciju.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajKvalifikacijaActionPerformed

    private void jMenuItemPretraziKvalifikacijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziKvalifikacijaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziKvalifikaciju();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje kvalifikacija.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziKvalifikacijaActionPerformed

    private void jMenuItemKreirajUzrastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajUzrastActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajUzrasnuKategoriju(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira uzrasnu kategoriju.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajUzrastActionPerformed

    private void jMenuItemPretraziUzrastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziUzrastActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziUzrasnuKategoriju();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje uzrasnih kategorija.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziUzrastActionPerformed

    private void jMenuItemPretraziUpisnicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziUpisnicaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziUpisnicu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje upisnica.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziUpisnicaActionPerformed

    private void jMenuItemKreirajUpisnicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajUpisnicaActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajUpisnicu(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira upisnicu.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajUpisnicaActionPerformed

    private void jMenuItemKonfiguracijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKonfiguracijaActionPerformed
        // TODO add your handling code here:
        try {
            Konfiguracija ks = new Konfiguracija(this, true);
            ks.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže konfiguracije.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKonfiguracijaActionPerformed

    private void jMenu19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu19ActionPerformed

    private void jMenuItemInformacijeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformacijeActionPerformed
        // TODO add your handling code here:
        try {
            Autor autor = new Autor(this, true);
            autor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže informacije o autoru.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemInformacijeActionPerformed

    private void jButtonOdjaviSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdjaviSeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOdjaviSeActionPerformed

    private void jMenuItemKreirajSertifikatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKreirajSertifikatActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniKreirajSertifikat(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira sertifikat.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemKreirajSertifikatActionPerformed

    private void jMenuItemPretraziSertifikatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziSertifikatActionPerformed
        // TODO add your handling code here:
        try {
            KontrolerGlavni.getInstance().pokreniPretraziSertifikat();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da prikaže formu za pretraživanje sertifikata.", "Greška", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPretraziSertifikatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOdjaviSe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelIme;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemInformacije;
    private javax.swing.JMenuItem jMenuItemKonfiguracija;
    private javax.swing.JMenuItem jMenuItemKreirajInstruktora;
    private javax.swing.JMenuItem jMenuItemKreirajKvalifikacija;
    private javax.swing.JMenuItem jMenuItemKreirajPolaznik;
    private javax.swing.JMenuItem jMenuItemKreirajSertifikat;
    private javax.swing.JMenuItem jMenuItemKreirajUpisnica;
    private javax.swing.JMenuItem jMenuItemKreirajUzrast;
    private javax.swing.JMenuItem jMenuItemKreirajVrstaPlesa;
    private javax.swing.JMenuItem jMenuItemPretraziInstruktora;
    private javax.swing.JMenuItem jMenuItemPretraziKvalifikacija;
    private javax.swing.JMenuItem jMenuItemPretraziPolaznik;
    private javax.swing.JMenuItem jMenuItemPretraziSertifikat;
    private javax.swing.JMenuItem jMenuItemPretraziUpisnica;
    private javax.swing.JMenuItem jMenuItemPretraziUzrast;
    private javax.swing.JMenuItem jMenuItemPretraziVrstaPlesa;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void GlavnaFormaAddActionListeners(ActionListener actionListener) {
        jButtonOdjaviSe.addActionListener(actionListener);
    }

}
