package casinilorenzoprogettothreadsbasket;

import java.awt.*;
import javax.swing.*;

public class BasketProgressBar extends JPanel {

    private int valore = 0;
    private final int MAX = 100;
    private final Color coloreSquadra;

    public BasketProgressBar(Color coloreSquadra) {
        this.coloreSquadra = coloreSquadra;
        setPreferredSize(new Dimension(400, 50));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public void setValore(int v) {
        this.valore = Math.min(v, MAX);
        repaint();
    }

    public int getValore() {
        return valore;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int pista_x_start = 30;
        int pista_x_end = w - 50;
        int pista_larghezza = pista_x_end - pista_x_start;
        int pista_y = h / 2;
        g2.setColor(new Color(210, 180, 140));
        g2.fillRoundRect(pista_x_start, pista_y - 6, pista_larghezza, 12, 8, 8);
        int progresso_w = (int) ((valore / (double) MAX) * pista_larghezza);
        g2.setColor(coloreSquadra.darker());
        g2.fillRoundRect(pista_x_start, pista_y - 6, progresso_w, 12, 8, 8);
        disegnaCanestro(g2, pista_x_end + 5, pista_y, h);
        int palla_x = pista_x_start + progresso_w - 14;
        palla_x = Math.max(pista_x_start, palla_x);
        disegnaPalla(g2, palla_x, pista_y - 13, 26);
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2.drawString(valore + "%", 2, h / 2 + 4);
    }

    private void disegnaPalla(Graphics2D g2, int x, int y, int size) {
        g2.setColor(new Color(230, 100, 20));
        g2.fillOval(x, y, size, size);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawArc(x + size / 4, y - size / 4, size / 2, size + size / 2, 0, 180);
        g2.drawArc(x + size / 4, y - size / 4, size / 2, size + size / 2, 180, 180);
        g2.drawLine(x, y + size / 2, x + size, y + size / 2);
        g2.drawOval(x, y, size, size);
        g2.setStroke(new BasicStroke(1f));
    }

    private void disegnaCanestro(Graphics2D g2, int x, int cy, int h) {
        g2.setColor(new Color(220, 220, 220));
        g2.fillRect(x + 20, cy - h / 2 + 2, 14, h / 2 + 4);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(x + 20, cy - h / 2 + 2, 14, h / 2 + 4);
        g2.setColor(new Color(80, 80, 80));
        g2.setStroke(new BasicStroke(3f));
        g2.drawLine(x + 27, cy + h / 2 - 2, x + 27, cy - 2);
        g2.drawLine(x, cy - 2, x + 27, cy - 2);
        g2.setColor(new Color(200, 30, 30));
        g2.setStroke(new BasicStroke(3f));
        g2.drawLine(x - 8, cy + 4, x + 14, cy + 4);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1f));
        for (int i = 0; i <= 4; i++) {
            int rx = x - 8 + i * 5;
            g2.drawLine(rx, cy + 4, rx + 2, cy + 16);
        }
        g2.drawLine(x - 6, cy + 16, x + 12, cy + 16);
        g2.setStroke(new BasicStroke(1f));
    }
}
