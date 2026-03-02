package casinilorenzoprogettothreadsbasket;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.datatransfer.*;
import javax.swing.*;

public class JFrmInizio extends javax.swing.JFrame {

    private static final String[] nomiDisponibili = {
        "LeBron James", "Stephen Curry", "Kevin Durant",
        "Giannis Antetokounmpo", "Luka Doncic", "Nikola Jokic",
        "Anthony Edwards", "Jayson Tatum", "Damian Lillard", "Anthony Davis"
    };
    private static final int[] forzaDisponibile = {9, 10, 9, 10, 8, 10, 9, 7, 7, 8};

    private String[] slotNomi = new String[4];
    private JLabel[] slotLabels = new JLabel[4];

    public JFrmInizio() {
        initComponents();
        setupExtra();
    }

    private void setupExtra() {
        JCmbConcorrenti.setModel(new DefaultComboBoxModel<>(nomiDisponibili));

        JPanel slotPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        slotPanel.setBorder(BorderFactory.createTitledBorder("Slot giocatori (usa il bottone Carica)"));

        for (int i = 0; i < 4; i++) {
            slotLabels[i] = new JLabel("Slot " + (i + 1) + " — vuoto");
            slotLabels[i].setPreferredSize(new java.awt.Dimension(200, 70));
            slotLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            slotLabels[i].setOpaque(true);
            slotLabels[i].setBackground(Color.LIGHT_GRAY);
            slotLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            slotPanel.add(slotLabels[i]);

            final int idx = i;
            slotLabels[i].setTransferHandler(new TransferHandler() {
                @Override
                public boolean canImport(TransferSupport s) {
                    return s.isDataFlavorSupported(DataFlavor.stringFlavor);
                }
                @Override
                public boolean importData(TransferSupport s) {
                    try {
                        String nome = (String) s.getTransferable().getTransferData(DataFlavor.stringFlavor);
                        impostaSlot(idx, nome);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });
        }

        slotPanel.setBounds(0, 180, 900, 110);
        getContentPane().add(slotPanel);
        setSize(900, 340);
    }

    private void impostaSlot(int idx, String nome) {
        boolean isSkill = jRadioButton2.isSelected();
        int f = 5;
        for (int i = 0; i < nomiDisponibili.length; i++) {
            if (nomiDisponibili[i].equals(nome)) {
                f = forzaDisponibile[i];
            }
        }
        if (isSkill) {
            f = Math.max(1, (int) Math.round(f * 0.7));
        }
        double mult = Math.round((1.2 + (10 - f) * 0.42) * 10.0) / 10.0;
        slotNomi[idx] = nome;
        slotLabels[idx].setText(nome + "Forza: " + f + "  Quota: " + mult);
        slotLabels[idx].setBackground(new Color(180, 230, 180));
        slotLabels[idx].setBorder(BorderFactory.createLineBorder(new Color(0, 150, 0), 2));
        GameState.getInstance().setGiocatore(idx, nome, f);
        GameState.getInstance().setCategoria(
            isSkill ? Concorrente.Categoria.SKILL_CHALLENGE : Concorrente.Categoria.THREE_POINT_CONTEST
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        JCmbConcorrenti = new javax.swing.JComboBox<>();
        BtnCaricaConcorrente = new javax.swing.JButton();
        BtnBet = new javax.swing.JButton();
        BtnGara = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28));
        jLabel1.setText("INIZIO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 10, 120, 40);

        JCmbConcorrenti.setFont(new java.awt.Font("Segoe UI", 0, 14));
        getContentPane().add(JCmbConcorrenti);
        JCmbConcorrenti.setBounds(420, 100, 250, 40);

        BtnCaricaConcorrente.setFont(new java.awt.Font("Segoe UI", 0, 14));
        BtnCaricaConcorrente.setText("Carica Concorrente");
        BtnCaricaConcorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCaricaConcorrenteActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCaricaConcorrente);
        BtnCaricaConcorrente.setBounds(150, 100, 240, 40);

        BtnBet.setFont(new java.awt.Font("Segoe UI", 0, 14));
        BtnBet.setText("Bet");
        BtnBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBetActionPerformed(evt);
            }
        });
        getContentPane().add(BtnBet);
        BtnBet.setBounds(700, 10, 90, 40);

        BtnGara.setFont(new java.awt.Font("Segoe UI", 0, 14));
        BtnGara.setText("Gara");
        BtnGara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGaraActionPerformed(evt);
            }
        });
        getContentPane().add(BtnGara);
        BtnGara.setBounds(800, 10, 90, 40);

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jRadioButton1.setText("Three Point Contest");
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(150, 55, 220, 30);

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jRadioButton2.setText("Skill Challenge");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(390, 55, 180, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBetActionPerformed
        new JFrmBet().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBetActionPerformed

    private void BtnGaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGaraActionPerformed
        new JFrmGara().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnGaraActionPerformed

    private void BtnCaricaConcorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCaricaConcorrenteActionPerformed
        String nome = (String) JCmbConcorrenti.getSelectedItem();
        if (nome == null) return;
        for (int i = 0; i < 4; i++) {
            if (slotNomi[i] == null) {
                impostaSlot(i, nome);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Già 4 giocatori selezionati.");
    }//GEN-LAST:event_BtnCaricaConcorrenteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBet;
    private javax.swing.JButton BtnCaricaConcorrente;
    private javax.swing.JButton BtnGara;
    private javax.swing.JComboBox<String> JCmbConcorrenti;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
