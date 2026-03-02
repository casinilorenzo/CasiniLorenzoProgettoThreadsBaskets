package casinilorenzoprogettothreadsbasket;

import javax.swing.*;

public class Concorrente extends Thread {

    public interface OnArrivoListener {
        void onArrivo(String nome);
    }

    public enum Categoria {
        THREE_POINT_CONTEST, SKILL_CHALLENGE
    }

    private String nome;
    private JProgressBar progressBar;
    private OnArrivoListener arrivoListener;
    protected volatile boolean inPausa = false;
    protected volatile boolean interrotto = false;
    private int forza = 5;

    public Concorrente(String nome, JProgressBar progressBar, int forza, OnArrivoListener listener) {
        this.nome = nome;
        this.progressBar = progressBar;
        this.forza = forza;
        this.arrivoListener = listener;
    }

    public String getNome() { return nome; }
    public int getForza() { return forza; }
    public OnArrivoListener getArrivoListener() { return arrivoListener; }
    public boolean inPausa() { return inPausa; }
    public boolean interrotto() { return interrotto; }

    public synchronized void mettiInPausa() { inPausa = true; }

    public synchronized void riprendi() {
        inPausa = false;
        notifyAll();
    }

    public void ferma() {
        interrotto = true;
        synchronized (this) {
            inPausa = false;
            notifyAll();
        }
        this.interrupt();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(0);
            progressBar.setMaximum(100);
        });

        for (int i = 1; i <= 100; i++) {
            synchronized (this) {
                while (inPausa && !interrotto) {
                    try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
            }
            if (interrotto || Thread.currentThread().isInterrupted()) return;

            final int valore = i;
            SwingUtilities.invokeLater(() -> progressBar.setValue(valore));

            try {
                int sleepBase = Math.max(20, 210 - (forza * 18));
                Thread.sleep((long) (Math.random() * 60 + sleepBase));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        if (!interrotto && arrivoListener != null) {
            SwingUtilities.invokeLater(() -> arrivoListener.onArrivo(nome));
        }
    }

    public static double calcolaMoltiplicatore(int forza) {
        return Math.round((1.2 + (10 - forza) * 0.42) * 10.0) / 10.0;
    }
}
