package it.uniba.app;

/**
 * Questa classe è di tipo 'Entity' e 'Control'.
 * Si tratta di una classe Singleton.
 * Si occupa di gestire il nucleo del gioco.
 */
public final class Wordle {

    /**
     * MAX_PAROLA è una costante
     * relativa alla lunghezza massima
     * della parola da indovinare.
     */
    private static final int MAX_PAROLA = 5;

    /**
     * MAX_TENTATIVI è una costante
     * relativa al numero massimo di
     * tentativi effettuabili dall'utente.
     */
    private static final int MAX_TENTATIVI = 6;

    /**
     * L'attributo w è l'istanza
     * della classe Singleton Wordle.
    */
    private static Wordle w = new Wordle();

    /**
     * L'attributo parolaSegreta è
     * la parola che l'utente deve
     * indovinare durante il gioco.
     */
    private String parolaSegreta;

    /**
     * L'attributo dimensioneParola è
     * la lunghezza della parola da
     * indovinare.
     */
    private int dimensioneParola;

    /**
     * L'attributo maxTentativi rappresenta
     * il numero massimo di tentativi che
     * l'utente può effettuare.
     */
    private int maxTentativi;

    /**
     * L'attributo inCorso indica se la
     * partita è in corso o è terminata.
     */
    private boolean inCorso;

    /**
     * E' il costruttore della classe Wordle
     * che inizializza gli attributi della
     * classe Wordle.
     */
    private Wordle() {
        parolaSegreta = "";
        dimensioneParola = MAX_PAROLA;
        maxTentativi = MAX_TENTATIVI;
        inCorso = true;
    }

    /**
     * Il seguente metodo restituisce
     * l'unica istanza della classe singoletto.
     * @return restituisce l'istanza w di Wordle.
     */
    public static Wordle getWordle() {
        return w;
    }

    /**
     * E' un metodo di acesso.
     * @return viene restituita la
     * parola segreta da indovinare.
     */
    public static String getParolaSegreta() {
        return new String(w.parolaSegreta);
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare la parola segreta
     * applicando diversi controlli su di essa
     * verificandone la lunghezza.
     * @param p è la parola segreta da settare.
     */

    public static void setParolaSegreta(final String p) {
        if (p.length() > w.dimensioneParola) {
            Monitor.messaggi("parolalunga", getDimensioneParola());
        } else {
            if (p.length() < w.dimensioneParola) {
                Monitor.messaggi("parolacorta", getDimensioneParola());
            } else {
                if (!Manager.parolaValida(p)) {
                    Monitor.messaggi("parolanonvalida");
                } else {
                    w.parolaSegreta = new String(p);
                    Monitor.messaggi("parolavalida");
                }
            }
        }
    }

    /**
     * E' un metodo di accesso.
     * @return viene restituita la lunghezza
     * della parola segreta.
     */

    public static int getDimensioneParola() {
        return w.dimensioneParola;
    }

    /**
     * Questo metodo fornisce la possiblità
     * di modificare la dimensione della
     * parola segreta.
     * @param dim è la lunghezza della parola
     * segreta.
     */

    public static void setDimensioneParola(final int dim) {
        w.dimensioneParola = dim;
    }

    /**
     * E' un metodo di accesso.
     * @return viene restituito il numero
     * massimo di tentativi effettuati dall'utente.
     */

    public static int getMaxTentativi() {
        return w.maxTentativi;
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare il numero massimo di
     * tentativi.
     * @param tent è il numero di tentativi
     * che deve essere settato.
     */

    public static void setMaxTentativi(final int tent) {
        w.maxTentativi = tent;
    }

    /**
     * E' un metodo di accesso.
     * @return viene restituito il
     * booleano che indica se è in corso
     * una partita o meno.
     */

    public static boolean isInCorso() {
        return w.inCorso;
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare l'attributo inCorso.
     * @param inCorso è il booleano da assegnare
     * all'attributo inCorso.
     */

    public static void setInCorso(final boolean inCorso) {
        w.inCorso = inCorso;
    }

    /**
     * Questo metodo permette di chiudere l'intera sessione di gioco.
     */
    public static void chiudiGioco() {
        setInCorso(false);
        Monitor.messaggi("chiudi");
    }
}
