package it.uniba.app;

import java.util.Scanner;

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
                if(!parolaValida(p)){
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

    /**
     * Questo metodo controlla che nella parola non ci siano
     * caratteri che non appartengono all'alfabeto.
     */
    public static boolean parolaValida(String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) > 90 || s.charAt(i) < 65){
                return false;
            }
        }
        return true;
    }

    /**
     * Questo metodo permette di ricevere il comando dell'utente
     * da tastiera, ne controlla la sintassi (scanner) e richiama
     * il metodo adeguato (parser).
     */
    public static void inputComando(Scanner sc){
        String c;
        String[] s;
        do {
            System.out.print("\nInserire un comando:\n[" + getRuoloUtente() + "] > ");
            c = sc.nextLine().toUpperCase();
            s = scannerWordle(c);
        } while(s.length < 1);
        
        parserWordle(sc, s);
    }
    
    /**
     * Questo metodo controlla la validità sintattica del
     * comando dato in input.
     */
    private static String[] scannerWordle(String c){
        String s[] = c.trim().split("\\s+");
        if(s.length >= 1){
            if(s[0].charAt(0) != '/'){
                System.out.println("Tutti i comandi devono iniziare per '/'");
            }else{
                if(s.length > 2){
                    System.out.println("Tutti i comandi devono avere massimo due parole.");
                }else{
                    if(s.length == 1){
                        String ss[] = new String[2];
                        ss[0] = s[0];
                        ss[1] = null;
                        return ss;
                    }
                }
            }
        }
        return s;
    }
    
    /**
     * Questo metodo associa ad ogni comando il rispettivo metodo
     * da richiamare.
     */
    private static void parserWordle(Scanner sc, String s[]){
        if(s[0].equals("/NUOVA")){
            if(s[1] != null){
                Paroliere.impostaParolaSegreta(s[1]);
            }else{
                System.out.println("Parola segreta assente.");
            }
        }else if(s[0].equals("/GIOCA")){
            if(s[1] == null){
                Giocatore.iniziaPartita(sc);
            }else{
                System.out.println("Questo comando non ha bisogno di una seconda parola.");
            }  
        }else if(s[0].equals("/HELP")){
            if(s[1] == null){
                visualizzaComandi();
                visualizzaRegole();
            }else{
                System.out.println("Questo comando non ha bisogno di una seconda parola.");
            }
        }else if(s[0].equals("/MOSTRA")){
            if(s[1] == null){
                Paroliere.visualizzaParola();
            }else{
                System.out.println("Questo comando non ha bisogno di una seconda parola.");
            }
        }else if(s[0].equals("/ESCI")){
            if(s[1] == null && richiediConferma(sc)){
                chiudiGioco();
            }else{
                System.out.println("Questo comando non ha bisogno di una seconda parola.");
            }
        }else if(s[0].equals("/RUOLO")){
            if(s[1] == null){
                System.out.println("RUOLO CORRENTE: " + getRuoloUtente());
            }else if(s[1].equals("PAROLIERE") || s[1].equals("GIOCATORE")){
                setRuoloUtente(s[1]);
                System.out.println("Hai scelto il ruolo: " + getRuoloUtente());
            }else{
                System.out.println("Mi spiace, non hai inserito un ruolo valido!\n");
                System.out.println("Riprova digitando il comando /ruolo seguito da un ruolo valido");
            }
        }
    }

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

    static boolean richiediConferma(Scanner sc){
        System.out.println("\nSei sicuro della tua scelta?");
        System.out.println("\nDigita S se vuoi confermare la tua decisione.");
        System.out.println("Digita N se vuoi tornare a giocare.\n");
        String r = sc.nextLine().toUpperCase();

        while(!r.equals("S") && !r.equals("N")){
            System.out.println("Non hai inserito una parola valida!");
            System.out.println("\nInserisci S o N:");
            r = sc.nextLine().toUpperCase();
        }

        if(r.equals("S")){
            return true;
        } else {
            return false;
        }
    }

    public static void chiudiGioco(){
        setInCorso(false);
        System.out.println("\nSei uscito dal gioco.");
    }
}
