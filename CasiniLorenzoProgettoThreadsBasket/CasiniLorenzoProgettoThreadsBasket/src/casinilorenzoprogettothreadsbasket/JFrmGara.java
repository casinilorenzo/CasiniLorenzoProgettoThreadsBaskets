package casinilorenzoprogettothreadsbasket;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class JFrmGara extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JFrmGara.class.getName());
    private List<Concorrente> concorrenti = new ArrayList<>();
    private int posizione = 1;
    private boolean garaInCorso = false;

    private BasketProgressBar[] customBars = new BasketProgressBar[4];
    private JLabel[] fotoLabels = new JLabel[4];

    private static final Color[] COLORI = {
        new Color(220, 60, 60),
        new Color(60, 180, 60),
        new Color(60, 100, 220),
        new Color(200, 160, 0)
    };

    public JFrmGara() {
        initComponents();
        setupGara();
        setupCustomBars();
    }

    private void setupGara() {
        GameState gs = GameState.getInstance();
        LblConcorrente1.setText(gs.getNomiGiocatori()[0]);
        LblConcorrente2.setText(gs.getNomiGiocatori()[1]);
        LblConcorrente3.setText(gs.getNomiGiocatori()[2]);
        LblConcorrente4.setText(gs.getNomiGiocatori()[3]);
        TxtAreaClassifica.setEditable(false);
        BtnPausa.setEnabled(false);
        BtnReset.setEnabled(false);
    }

    private void setupCustomBars() {
        GameState gs = GameState.getInstance();
        String[] nomi = gs.getNomiGiocatori();
        JLabel[] lbls = {LblConcorrente1, LblConcorrente2, LblConcorrente3, LblConcorrente4};
        for (int i = 0; i < 4; i++) {
            fotoLabels[i] = new JLabel();
            fotoLabels[i].setPreferredSize(new Dimension(60, 60));
            fotoLabels[i].setBorder(BorderFactory.createLineBorder(COLORI[i], 2));
            caricaFoto(fotoLabels[i], nomi[i]);
            customBars[i] = new BasketProgressBar(COLORI[i]);
            customBars[i].setPreferredSize(new Dimension(400, 50));
            JPanel riga = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            riga.setOpaque(false);
            riga.add(fotoLabels[i]);
            riga.add(customBars[i]);
            int y = 55 + i * 70;
            riga.setBounds(120, y, 490, 60);
            getContentPane().add(riga);
            switch (i) {
                case 0: PgbConcorrente1.setVisible(false);
                case 1: PgbConcorrente2.setVisible(false);
                case 2: PgbConcorrente3.setVisible(false);
                case 3: PgbConcorrente4.setVisible(false);
            }
        }

        setSize(Math.max(getWidth(), 780), Math.max(getHeight(), 450));
        repaint();
    }

    private void caricaFoto(JLabel lbl, String nomeGiocatore) {
        try {
            String path = "/casinilorenzoprogettothreadsbasket/img/" + nomeGiocatore + ".png";
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image scaled = icon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
                lbl.setIcon(new ImageIcon(scaled));
            } else {
                lbl.setText(iniziali(nomeGiocatore));
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setBackground(new Color(180, 180, 200));
                lbl.setOpaque(true);
            }
        } catch (Exception e) {
            lbl.setText(iniziali(nomeGiocatore));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private String iniziali(String nome) {
        String[] parti = nome.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parti) if (!p.isEmpty()) sb.append(p.charAt(0));
        return sb.toString();
    }

    public synchronized void registraArrivo(String nome) {
        TxtAreaClassifica.append(posizione + "° posto: " + nome + "\n");
        if (posizione == 1) {
            GameState.getInstance().setVincitoreGara(nome);
        }
        posizione++;
        if (posizione > concorrenti.size()) {
            garaInCorso = false;
            BtnAvvia.setEnabled(false);
            BtnPausa.setEnabled(false);
            BtnReset.setEnabled(true);
            TxtAreaClassifica.append("--- GARA TERMINATA ---\n");
            mostraEsitoBet();
        }
    }

    private void mostraEsitoBet() {
        GameState gs = GameState.getInstance();
        if (!gs.hasScommessa()) return;
        String vincitore = gs.getVincitoreGara();
        String scommessa = gs.getSquadraScelta();
        double importo = gs.getImportoBet();
        double quota = gs.getQuotaBet();
        SwingUtilities.invokeLater(() -> {
            if (scommessa.equals(vincitore)) {
                JOptionPane.showMessageDialog(this, "🏆 HAI VINTO!\nVincita: €" + String.format("%.2f", importo * quota), "VITTORIA!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Hai perso €" + String.format("%.2f", importo) + "\nVincitore: " + vincitore, "SCONFITTA", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        LblConcorrente1 = new javax.swing.JLabel();
        PgbConcorrente1 = new javax.swing.JProgressBar();
        PgbConcorrente4 = new javax.swing.JProgressBar();
        LblConcorrente2 = new javax.swing.JLabel();
        LblConcorrente4 = new javax.swing.JLabel();
        PgbConcorrente2 = new javax.swing.JProgressBar();
        PgbConcorrente3 = new javax.swing.JProgressBar();
        LblConcorrente3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtAreaClassifica = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        BtnBet = new javax.swing.JButton();
        BtnInizio = new javax.swing.JButton();
        BtnAvvia = new javax.swing.JButton();
        BtnPausa = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel1.setText("GARA");

        LblConcorrente1.setText("Squadra A");
        LblConcorrente2.setText("Squadra B");
        LblConcorrente3.setText("Squadra C");
        LblConcorrente4.setText("Squadra D");

        TxtAreaClassifica.setColumns(20);
        TxtAreaClassifica.setRows(5);
        jScrollPane1.setViewportView(TxtAreaClassifica);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel6.setText("Classifica");

        BtnBet.setText("Bet");
        BtnInizio.setText("Inizio");

        BtnAvvia.setText("Avvia");
        BtnAvvia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAvviaActionPerformed(evt);
            }
        });

        BtnPausa.setText("Pausa");
        BtnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPausaActionPerformed(evt);
            }
        });

        BtnReset.setText("Reset");
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblConcorrente2)
                            .addComponent(LblConcorrente3)
                            .addComponent(LblConcorrente4)
                            .addComponent(LblConcorrente1))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PgbConcorrente4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PgbConcorrente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PgbConcorrente2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PgbConcorrente1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnAvvia, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnInizio)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBet, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnPausa)
                        .addGap(18, 18, 18)
                        .addComponent(BtnReset)
                        .addGap(71, 71, 71))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnBet)
                        .addComponent(BtnInizio)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(LblConcorrente1)
                        .addGap(53, 53, 53)
                        .addComponent(LblConcorrente2)
                        .addGap(53, 53, 53)
                        .addComponent(LblConcorrente3)
                        .addGap(52, 52, 52)
                        .addComponent(LblConcorrente4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PgbConcorrente1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PgbConcorrente2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(PgbConcorrente3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(PgbConcorrente4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnPausa, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(BtnAvvia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();

        BtnBet.addActionListener(e -> { new JFrmBet().setVisible(true); this.dispose(); });
        BtnInizio.addActionListener(e -> { new JFrmInizio().setVisible(true); this.dispose(); });
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAvviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvviaActionPerformed
        GameState gs = GameState.getInstance();
        gs.setVincitoreGara(null);
        concorrenti.clear();
        posizione = 1;
        garaInCorso = true;
        TxtAreaClassifica.setText("");

        for (int i = 0; i < 4; i++) {
            String nome = gs.getNomiGiocatori()[i];
            int forza = gs.getForzaGiocatori()[i];
            if (nome == null || nome.isEmpty()) nome = "Squadra " + (char) ('A' + i);
            if (forza <= 0) forza = 5;

            final BasketProgressBar bar = customBars[i];
            bar.setValore(0);

            JProgressBar fakeBar = new JProgressBar(0, 100);
            Concorrente c = new Concorrente(nome, fakeBar, forza, this::registraArrivo) {
                @Override
                public void run() {
                    for (int v = 1; v <= 100; v++) {
                        synchronized (this) {
                            while (inPausa() && !interrotto()) {
                                try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                            }
                        }
                        if (interrotto() || Thread.currentThread().isInterrupted()) return;
                        final int val = v;
                        SwingUtilities.invokeLater(() -> bar.setValore(val));
                        try {
                            int sleepBase = Math.max(20, 210 - (getForza() * 18));
                            Thread.sleep((long) (Math.random() * 60 + sleepBase));
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    if (!interrotto() && getArrivoListener() != null) {
                        SwingUtilities.invokeLater(() -> getArrivoListener().onArrivo(getNome()));
                    }
                }
            };
            concorrenti.add(c);
            c.start();
        }

        BtnAvvia.setEnabled(false);
        BtnPausa.setEnabled(true);
        BtnReset.setEnabled(true);
    }//GEN-LAST:event_BtnAvviaActionPerformed

    private void BtnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPausaActionPerformed
        if (BtnPausa.getText().equals("Pausa")) {
            for (Concorrente c : concorrenti) c.mettiInPausa();
            BtnPausa.setText("Riprendi");
        } else {
            for (Concorrente c : concorrenti) c.riprendi();
            BtnPausa.setText("Pausa");
        }
    }//GEN-LAST:event_BtnPausaActionPerformed

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        for (Concorrente c : concorrenti) c.ferma();
        concorrenti.clear();
        garaInCorso = false;
        posizione = 1;
        for (BasketProgressBar b : customBars) b.setValore(0);
        TxtAreaClassifica.setText("");
        BtnPausa.setText("Pausa");
        BtnAvvia.setEnabled(true);
        BtnPausa.setEnabled(false);
        BtnReset.setEnabled(false);
    }//GEN-LAST:event_BtnResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAvvia;
    private javax.swing.JButton BtnBet;
    private javax.swing.JButton BtnInizio;
    private javax.swing.JButton BtnPausa;
    private javax.swing.JButton BtnReset;
    private javax.swing.JLabel LblConcorrente1;
    private javax.swing.JLabel LblConcorrente2;
    private javax.swing.JLabel LblConcorrente3;
    private javax.swing.JLabel LblConcorrente4;
    private javax.swing.JProgressBar PgbConcorrente1;
    private javax.swing.JProgressBar PgbConcorrente2;
    private javax.swing.JProgressBar PgbConcorrente3;
    private javax.swing.JProgressBar PgbConcorrente4;
    private javax.swing.JTextArea TxtAreaClassifica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
