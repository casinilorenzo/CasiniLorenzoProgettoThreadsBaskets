/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casinilorenzoprogettothreadsbasket;

/**
 *
 * @author loren
 */
public class GameState {

    private static GameState instance;
    private String[] nomiGiocatori = {"Squadra A", "Squadra B", "Squadra C", "Squadra D"};
    private int[] forzaGiocatori = {5, 5, 5, 5};
    private String squadraScelta;
    private double importoBet, quotaBet;
    private String vincitoreGara;
    private boolean garaCompletata;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public String[] getNomiGiocatori() {
        return nomiGiocatori;
    }

    public int[] getForzaGiocatori() {
        return forzaGiocatori;
    }

    public void setGiocatore(int i, String nome, int forza) {
        nomiGiocatori[i] = nome;
        forzaGiocatori[i] = forza;
    }

    public void setCategoria(Concorrente.Categoria c) {
    }

    public String getSquadraScelta() {
        return squadraScelta;
    }

    public void setSquadraScelta(String s) {
        squadraScelta = s;
    }

    public double getImportoBet() {
        return importoBet;
    }

    public void setImportoBet(double v) {
        importoBet = v;
    }

    public double getQuotaBet() {
        return quotaBet;
    }

    public void setQuotaBet(double v) {
        quotaBet = v;
    }

    public String getVincitoreGara() {
        return vincitoreGara;
    }

    public void setVincitoreGara(String v) {
        vincitoreGara = v;
        garaCompletata = (v != null);
    }

    public boolean isGaraCompletata() {
        return garaCompletata;
    }

    public boolean hasScommessa() {
        return squadraScelta != null && importoBet > 0;
    }
}
