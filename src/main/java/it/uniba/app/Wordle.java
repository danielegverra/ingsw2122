package it.uniba.app;

/**
 * Questa classe è di tipo ' '.
 * Si occupa di gestire il nucleo del gioco.
 */
public class Wordle {
    
    /**
     * ATTRIBUTI
    */

    private static String parolaSegreta = "";
    private static int dimensioneParola = 5;
    private static String ruoloUtente = "PAROLIERE";
    private static int maxTentativi = 6;
    private static boolean inCorso = true;
    

    /**
     * METODI DI ACCESSO
     */
    
    public static String getParolaSegreta(){
        return new String(parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        if(p.length() > dimensioneParola){
            System.out.println("Parola segreta troppo lunga, deve avere "+ getDimensioneParola() + " caratteri." );
        }else{
            if(p.length() < dimensioneParola){
                System.out.println("Parola segreta troppo corta, deve avere "+ getDimensioneParola() + " caratteri." );
            }else{
                if(!Manager.parolaValida(p)){
                     System.out.println("Parola segreta non valida.");
                }else{
                    parolaSegreta = new String(p);
                    System.out.println("OK.");
                }
            }
        }
    }

    public static int getDimensioneParola(){
        return dimensioneParola;
    }

    public static void setDimensioneParola(int dim){
        dimensioneParola = dim;
    }

    public static String getRuoloUtente(){
        return new String(ruoloUtente);
    }

    public static void setRuoloUtente(String s){
        ruoloUtente = new String(s);
    }

    public static int getMaxTentativi() {
        return maxTentativi;
    }

    public static void setMaxTentativi(int tent) {
        maxTentativi = tent;
    }

    public static boolean isInCorso() {
        return inCorso;
    }

    public static void setInCorso(boolean inCorso) {
        Wordle.inCorso = inCorso;
    }

    /**
     * METODI
    */

    static void visualizzaComandi(){
        System.out.println("\nAll'interno del gioco e' possibile eseguire i seguenti comandi:");
        System.out.println(" 1. /help: consente di visualizzare i comandi disponibili e le regole del gioco");
        System.out.println(" 2. /ruolo <parola>: consente di selezionare il ruolo di paroliere/giocatore");
        System.out.println(" 3. /ruolo: consente di mostrare il ruolo corrente");
        System.out.println(" 4. /esci: consente di chiudere l'applicazione a seguito di una conferma positiva dell'utente ");

        System.out.println("\nDa paroliere e' possibile eseguire i seguenti comandi: ");
        System.out.println(" 1. /nuova <parola>: consente di impostare una nuova parola segreta, anche durante la sessione di gioco senza uscire dall'applicazione");
        System.out.println(" 2. /mostra: consente di visualizzare la parola segreta");
       
        System.out.println("\nDa giocatore e' possibile eseguire i seguenti comandi: ");
        System.out.println(" 1. /gioca: consente di visualizzare la matrice dei tentativi vuota se nessuna partita e' in corso");
        System.out.println(" 2. /abbandona: consente di abbandonare la partita a seguito di una conferma positiva dell'utente\n");
    }

    static void visualizzaRegole(){
        System.out.println("Di seguito vengono mostrate le regole e il funzionamento del gioco:\n");
        System.out.println("Lo scopo del gioco e' indovinare una parola di cinque lettere utilizzando un massimo di sei tentativi.");
        System.out.println("La parola segreta viene decisa dal paroliere, che per ogni tentativo restituisce tre tipi di indizi utili a restringere il cerchio sulla soluzione");
        System.out.println("Ogni lettera indovinata nella posizione e' segnata con una V,");
        System.out.println("ogni lettera presente nella parola segreta ma inserita nella posizione errata e' segnata con S,");
        System.out.println("e ogni lettera del tutto assente dalla soluzione e' segnata con X.");
    }

    public static void chiudiGioco(){
        setInCorso(false);
        System.out.println("\nSei uscito dal gioco.");
    }
}
