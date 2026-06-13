/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import kontroler.ServerKontroler;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import model.Instruktor;
import model.ModelTabeleInstruktori;
import niti.ServerNit;
import konfiguracija.Konfiguracija;
import niti.ObradaKlijentskihZahteva;
import repozitorijum.db.DBKonekcija;

/**
 *
 * @author Tijana
 */
public class ServerskaForma extends javax.swing.JFrame {

    private ServerNit server;
    private List<Instruktor> sviKorisnici = new ArrayList<>();
    private List<Instruktor> nepovezaniKorisnici = new ArrayList<>();
    public static FormaKonfiguracija konfig;
    public static FormaKreirajKonfiguraciju fkk;

    public ServerskaForma() {
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

        getContentPane().setBackground(new Color(0, 102, 153));
        this.setTitle("Server");
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        popuniTabeluPovezani();
        popuniTabeluNepovezani();
        jLabelStartStop.setText("Server nije pokrenut.");
        jLabelStartStop.setForeground(Color.RED);
        jButtonPokreni.setEnabled(true);
        jButtonZaustavi.setEnabled(false);

        JTableHeader heder1 = jTableNepovezani.getTableHeader();
        JTableHeader heder2 = jTablePovezani.getTableHeader();
        heder1.setForeground(new Color(0, 102, 153));
        heder2.setForeground(new Color(0, 102, 153));

        jTableNepovezani.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tabela, Object vrednost, boolean selektovano, boolean fokusirano, int red, int kolona) {
                Component celija = super.getTableCellRendererComponent(tabela, vrednost, selektovano, fokusirano, red, kolona);
                if (!selektovano) {
                    if (red % 2 == 0) {
                        celija.setBackground(Color.WHITE);
                    } else {
                        celija.setBackground(new Color(204, 255, 255));
                    }
                }
                if (selektovano) {
                    celija.setBackground(new Color(204, 204, 204));
                }
                celija.setForeground(new Color(0, 102, 153));

                return celija;
            }
        });

        jTablePovezani.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tabela, Object vrednost, boolean selektovano, boolean fokusirano, int red, int kolona) {
                Component celija = super.getTableCellRendererComponent(tabela, vrednost, selektovano, fokusirano, red, kolona);
                if (!selektovano) {
                    if (red % 2 == 0) {
                        celija.setBackground(Color.WHITE);
                    } else {
                        celija.setBackground(new Color(204, 255, 255));
                    }
                }
                if (selektovano) {
                    celija.setBackground(new Color(204, 204, 204));
                }
                celija.setForeground(new Color(0, 102, 153));

                return celija;
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int sirina = getWidth();
                int visina = getHeight();

                int font1 = Math.min(sirina / 35, visina / 35);
                int font2 = Math.min(sirina / 50, visina / 50);
                int font3 = Math.min(sirina / 45, visina / 45);

                jLabelStartStop.setFont(new Font("Segoe UI", Font.PLAIN, font1));
                jLabel1.setFont(new Font("Segoe UI", Font.PLAIN, font1));
                jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, font1));
                jLabel4.setFont(new Font("Segoe UI", Font.PLAIN, font1));
                jMenu1.setFont(new Font("Segoe UI", Font.BOLD, font1));
                jMenuItemPodaci.setFont(new Font("Segoe UI", Font.BOLD, font3));
                jMenuItemUnesiPodatke.setFont(new Font("Segoe UI", Font.BOLD, font3));
                jTableNepovezani.setRowHeight(font1 + 5);
                jTablePovezani.setRowHeight(font1 + 5);
                JTableHeader heder1 = jTableNepovezani.getTableHeader();
                JTableHeader heder2 = jTablePovezani.getTableHeader();
                heder1.setFont(new Font("Segoe UI", Font.BOLD, font2));
                heder2.setFont(new Font("Segoe UI", Font.BOLD, font2));
                jButtonPokreni.setFont(new Font("Segoe UI", Font.BOLD, font3));
                jButtonZaustavi.setFont(new Font("Segoe UI", Font.BOLD, font3));

            }
        });

        konfig = new FormaKonfiguracija(this, true);
        fkk = new FormaKreirajKonfiguraciju(this, true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelStartStop = new javax.swing.JLabel();
        jButtonZaustavi = new javax.swing.JButton();
        jButtonPokreni = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePovezani = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableNepovezani = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemPodaci = new javax.swing.JMenuItem();
        jMenuItemUnesiPodatke = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Status servera: ");

        jLabelStartStop.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabelStartStop.setForeground(new java.awt.Color(255, 255, 255));

        jButtonZaustavi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonZaustavi.setForeground(new java.awt.Color(0, 102, 153));
        jButtonZaustavi.setText("Zaustavi server");
        jButtonZaustavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZaustaviActionPerformed(evt);
            }
        });

        jButtonPokreni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonPokreni.setForeground(new java.awt.Color(0, 102, 153));
        jButtonPokreni.setText("Pokreni server");
        jButtonPokreni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPokreniActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Povezani korisnici");

        jTablePovezani.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTablePovezani.setModel(new javax.swing.table.DefaultTableModel(
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
        jTablePovezani.setRowHeight(25);
        jTablePovezani.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePovezani.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTablePovezani.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTablePovezani);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nepovezani korisnici");

        jTableNepovezani.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableNepovezani.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableNepovezani.setRowHeight(25);
        jTableNepovezani.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableNepovezani.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableNepovezani.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableNepovezani);

        jMenu1.setForeground(new java.awt.Color(0, 102, 153));
        jMenu1.setText("Konfiguracija");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jMenuItemPodaci.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuItemPodaci.setForeground(new java.awt.Color(0, 102, 153));
        jMenuItemPodaci.setText("Podaci");
        jMenuItemPodaci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPodaciActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPodaci);

        jMenuItemUnesiPodatke.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenuItemUnesiPodatke.setForeground(new java.awt.Color(0, 102, 153));
        jMenuItemUnesiPodatke.setText("Unesi podatke");
        jMenuItemUnesiPodatke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnesiPodatkeActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemUnesiPodatke);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jButtonPokreni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(jButtonZaustavi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(138, 138, 138))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addGap(257, 257, 257))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(20, 20, 20))))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStartStop, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addGap(753, 753, 753))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabelStartStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPokreni, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jButtonZaustavi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemPodaciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPodaciActionPerformed
        // TODO add your handling code here:
        konfig.setVisible(true);
    }//GEN-LAST:event_jMenuItemPodaciActionPerformed

    private void jButtonZaustaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZaustaviActionPerformed
        // TODO add your handling code here:
        jButtonPokreni.setEnabled(true);
        jButtonZaustavi.setEnabled(false);

        server.zaustaviServer();

        jLabelStartStop.setText("Server nije pokrenut.");
        jLabelStartStop.setForeground(Color.RED);

        nepovezaniKorisnici = new ArrayList<>();
        ModelTabeleInstruktori mti1 = (ModelTabeleInstruktori) jTableNepovezani.getModel();
        mti1.setInstruktori(nepovezaniKorisnici);
        mti1.refresujPodatke();
        ModelTabeleInstruktori mti2 = (ModelTabeleInstruktori) jTablePovezani.getModel();
        List<Instruktor> povezaniKorisnici1 = new ArrayList<>();
        mti2.setInstruktori(povezaniKorisnici1);
        mti2.refresujPodatke();

    }//GEN-LAST:event_jButtonZaustaviActionPerformed

    private void jButtonPokreniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPokreniActionPerformed
        // TODO add your handling code here: 

        boolean ispravniPodaci = konfig.proveriPodatke();
        if (!ispravniPodaci) {
            JOptionPane.showMessageDialog(this, "Server ne može biti pokrenut, jer podaci o konfiguraciji nisu ispravni. Unesite podatke o konfiguraciji i pokušajte ponovo.", "Greška", JOptionPane.ERROR_MESSAGE);
            fkk.setVisible(true);
            return;
        }

        server = new ServerNit();

        server.start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!server.isPokrenut()) {

            JOptionPane.showMessageDialog(this, "Server je već pokrenut.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;

        }

        ServerKontroler.getInstance().setSf(this);

        try {
            sviKorisnici = ServerKontroler.getInstance().vratiListuSviInstruktor();
        } catch (Exception ex) {
            System.err.println("Sistem ne može da učita instruktore iz baze.");
        }
        if (sviKorisnici == null) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita instruktore iz baze. Proverite da li je uspostavljena veza sa MySQL serverom.", "Greška", JOptionPane.ERROR_MESSAGE);
            server.zaustaviServer();
            DBKonekcija.resetuj();
            unesiPodatke();
            return;
        }

        jButtonPokreni.setEnabled(false);
        jButtonZaustavi.setEnabled(true);

        jLabelStartStop.setText("Server je pokrenut.");
        jLabelStartStop.setForeground(Color.GREEN);
        nepovezaniKorisnici = new ArrayList<>();
        List<Instruktor> povezaniKorisnici = new ArrayList<>();
        for (ObradaKlijentskihZahteva korisnik : ServerNit.getPrijavljeniKorisnici()) {
            Instruktor i = new Instruktor(korisnik.getKorisnickoIme(), korisnik.getSifra(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail());
            povezaniKorisnici.add(i);
        }
        for (Instruktor instruktor : sviKorisnici) {
            if (!povezaniKorisnici.contains(instruktor)) {
                nepovezaniKorisnici.add(instruktor);
            }
        }

        ModelTabeleInstruktori mti1 = (ModelTabeleInstruktori) jTableNepovezani.getModel();
        mti1.setInstruktori(nepovezaniKorisnici);
        mti1.refresujPodatke();
        ModelTabeleInstruktori mti2 = (ModelTabeleInstruktori) jTablePovezani.getModel();
        mti2.setInstruktori(povezaniKorisnici);
        mti2.refresujPodatke();

        unesiPodatke();
    }//GEN-LAST:event_jButtonPokreniActionPerformed

    private void jMenuItemUnesiPodatkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnesiPodatkeActionPerformed
        // TODO add your handling code here:
        fkk.setVisible(true);
    }//GEN-LAST:event_jMenuItemUnesiPodatkeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPokreni;
    private javax.swing.JButton jButtonZaustavi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelStartStop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemPodaci;
    private javax.swing.JMenuItem jMenuItemUnesiPodatke;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableNepovezani;
    private javax.swing.JTable jTablePovezani;
    // End of variables declaration//GEN-END:variables

    private void popuniTabeluPovezani() {
        List<Instruktor> povezaniKorisnici1 = new ArrayList<>();

        ModelTabeleInstruktori modelTabele = new ModelTabeleInstruktori(povezaniKorisnici1);
        jTablePovezani.setModel(modelTabele);
    }

    private void popuniTabeluNepovezani() {
        ModelTabeleInstruktori modelTabele = new ModelTabeleInstruktori(nepovezaniKorisnici);
        jTableNepovezani.setModel(modelTabele);
    }

    public void osveziTabele() {
        try {
            sviKorisnici = ServerKontroler.getInstance().vratiListuSviInstruktor();
        } catch (Exception ex) {
            System.err.println("Sistem ne može da učita instruktore iz baze.");
        }
        nepovezaniKorisnici = new ArrayList<>();
        List<Instruktor> povezaniKorisnici1 = new ArrayList<>();
        for (ObradaKlijentskihZahteva korisnik : ServerNit.getPrijavljeniKorisnici()) {
            Instruktor i = new Instruktor(korisnik.getKorisnickoIme(), korisnik.getSifra(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail());
            povezaniKorisnici1.add(i);
        }
        for (Instruktor instruktor : sviKorisnici) {
            if (!povezaniKorisnici1.contains(instruktor)) {
                nepovezaniKorisnici.add(instruktor);
            }
        }
        ModelTabeleInstruktori mti1 = (ModelTabeleInstruktori) jTableNepovezani.getModel();
        mti1.setInstruktori(nepovezaniKorisnici);
        mti1.refresujPodatke();
        ModelTabeleInstruktori mti2 = (ModelTabeleInstruktori) jTablePovezani.getModel();
        mti2.setInstruktori(povezaniKorisnici1);
        mti2.refresujPodatke();

    }

    public void azuriraj() {
        konfig.setIspravniPodaci(true);
        unesiPodatke();
    }

    public void unesiPodatke() {
        if (Konfiguracija.getInstance().daLiPostojiKonfigFajl()) {
            konfig.unesiPodatke();

        } else {
            konfig.getjLabelPort().setText("N/A");
            konfig.getjLabelURL().setText("N/A");
            konfig.getjLabelKorisnik().setText("N/A");
            konfig.getjLabelLozinka().setText("N/A");

            fkk.setVisible(true);
        }
    }

}
