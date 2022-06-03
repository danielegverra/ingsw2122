package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo 'Boundary'.
 * Si occupa di ricevere direttamente dall'utente l'input
 * per poter effettuare le azioni di gioco.
 */
public class Manager {
    
    /**
     * METODI
    */

    /**
     * Questo metodo permette di ricevere il comando dell'utente
     * da tastiera, ne controlla la sintassi (scanner) e richiama
     * il metodo adeguato (parser).
     */
    public static void inputComando(Scanner sc){
        String c;
        String[] s;
        try {
            do {
                System.out.print("\nInserire un comando:\n> ");
                c = sc.nextLine().toUpperCase();
                s = scannerWordle(c);
            } while(s.length < 1);
            
            parserWordle(sc, s);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("");
        }
        
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
                Wordle.visualizzaComandi();
                Wordle.visualizzaRegole();
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
            if(s[1] == null){
                if(richiediConferma(sc)){
                    Wordle.chiudiGioco();
                }
            }else{
                System.out.println("Questo comando non ha bisogno di una seconda parola.");
            }
        }else{
            System.out.println("Comando non riconosciuto.");
        }
    }

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
     * Questo metodo serve ogni qualvolta viene chiesta
     * una conferma all'utente.
     */
    static boolean richiediConferma(Scanner sc){
        System.out.println("\nSei sicuro della tua scelta?");
        System.out.println("\nDigita S se vuoi confermare la tua decisione.");
        System.out.println("Digita N se vuoi tornare a giocare.");
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

     /**
     * Questo metodo permette di eseguire un tentativo.
     */
    static String isTentativo(Scanner sc, Partita p, Boolean partitaFinita){
        String parolaTentata;
        do{
            System.out.print("\nInserisci il tuo tentativo:\n> ");
            parolaTentata= sc.nextLine().toUpperCase().trim();
            if(parolaTentata.equals("/ESCI")){
                if(Manager.richiediConferma(sc)){
                    Wordle.chiudiGioco();
                    return parolaTentata;
                }
            }else if(parolaTentata.equals("/ABBANDONA")){
                if(Manager.richiediConferma(sc)){
                    partitaFinita = true;
                    System.out.println("Hai deciso di abbandonare la partita!\n");
                    System.out.println("Ci rivediamo presto!");
                    return parolaTentata;
                }
            }else if(!Manager.parolaValida(parolaTentata)){
                System.out.println("Tentativo non valido.");
                System.out.print("\nLa parola da inserire deve avere lunghezza " + p.getParola().length() + " e deve\nessere composta da soli caratteri dell'alfabeto:\n");
            }else if(parolaTentata.length() < p.getParola().length()){
                System.out.println("Tentativo incompleto.");
                System.out.print("\nLa parola da inserire deve avere lunghezza " + p.getParola().length() + " e deve\nessere composta da soli caratteri dell'alfabeto:\n");
            }else if(parolaTentata.length() > p.getParola().length()){
                System.out.println("Tentativo eccessivo.");
                System.out.print("\nLa parola da inserire deve avere lunghezza " + p.getParola().length() + " e deve\nessere composta da soli caratteri dell'alfabeto:\n");
            }
        } while((parolaTentata.length() != p.getParola().length() || !Manager.parolaValida(parolaTentata)) && !partitaFinita);

        return parolaTentata;
    }  
}
