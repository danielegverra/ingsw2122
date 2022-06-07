package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo 'Boundary'.
 * Si occupa di ricevere direttamente dall'utente l'input
 * per poter effettuare le azioni di gioco.
 */
public final class Manager {

    /**
     * La variabile MAX_ASCII indica
     * la posizione nella tabella ASCII
     * della prima lettera maiuscola dell'alfabeto.
     */
    private static final int MAX_ASCII = 90;
    /**
     * La variabile MIN_ASCII indica
     * la posizione nella tabella ASCII
     * dell'ultima lettera maiuscola dell'alfabeto.
     */
    private static final int MIN_ASCII = 65;

    private Manager() {

    }

    /**
     * Questo metodo permette di ricevere il comando dell'utente
     * da tastiera, ne controlla la sintassi (scanner) e richiama
     * il metodo adeguato (parser).
     * @param sc è lo scanner in input.
     */
    public static void inputComando(final Scanner sc) {
        String c;
        String[] s;
        try {
            do {
                System.out.print("\nInserire un comando:\n> ");
                c = sc.nextLine().toUpperCase();
                s = scannerWordle(c);
            } while (s.length < 1);
            parserWordle(sc, s);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("");
        }
    }
    /**
     * Questo metodo controlla la validità sintattica del
     * comando dato in input.
     * @param c è la stringa letta in input dall'utente.
     * @return un array di Stringhe
     * contenente i comandi dati in input.
     */
    private static String[] scannerWordle(final String c) {
        String[] s = c.trim().split("\\s+");
        if (s.length >= 1) {
            if (s[0].charAt(0) != '/') {
                System.out.println("Tutti i comandi devono iniziare per '/'");
            } else {
                if (s.length > 2) {
                    System.out.println("Tutti i comandi devono"
                    + "avere massimo due parole.");
                } else {
                    if (s.length == 1) {
                        String[] ss = new String[2];
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
     * @param sc è lo scanner in input
     * @param s è l'array di stringhe contenente i comandi splittati
     */
    private static void parserWordle(final Scanner sc, final String[] s) {
        if (s[0].equals("/NUOVA")) {
            if (s[1] != null) {
                Paroliere.impostaParolaSegreta(s[1]);
            } else {
                System.out.println("Parola segreta assente.");
            }
        } else if (s[0].equals("/GIOCA")) {
            if (s[1] == null) {
                Giocatore.iniziaPartita(sc);
            } else {
                Monitor.messaggi("secondaparola");
            }
        } else if (s[0].equals("/HELP")) {
            if (s[1] == null) {
                Monitor.visualizzaComandi();
                Monitor.visualizzaRegole();
            } else {
                Monitor.messaggi("secondaparola");
            }
        } else if (s[0].equals("/MOSTRA")) {
            if (s[1] == null) {
                Paroliere.visualizzaParola();
            } else {
                Monitor.messaggi("secondaparola");
            }
        } else if (s[0].equals("/ESCI")) {
            if (s[1] == null) {
                if (richiediConferma(sc)) {
                    Wordle.chiudiGioco();
                }
            } else {
                Monitor.messaggi("secondaparola");
            }
        } else {
            System.out.println("Comando non riconosciuto.");
        }
    }

    /**
     * Questo metodo controlla che nella parola non ci siano
     * caratteri che non appartengono all'alfabeto.
     * @param s è la stringa letta in input dall'utente
     * @return viene restituito un booleano:
     * vero se viene inserita una parola composta
     * da lettere dall'alfabeto, falso altrimenti.
     */
    public static boolean parolaValida(final String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > MAX_ASCII || s.charAt(i) < MIN_ASCII) {
                return false;
            }
        }
        return true;
    }

    /**
     * Questo metodo serve ogni qualvolta viene chiesta
     * una conferma all'utente.
     * @param sc è lo scanner in input
     * @return viene restituito un booleano:
     * vero se l'utente risponde positivamente
     * falso altrimenti.
     */
    static boolean richiediConferma(final Scanner sc) {
        System.out.println("\nSei sicuro della tua scelta?");
        System.out.println("\nDigita S se vuoi confermare la tua decisione.");
        System.out.println("Digita N se vuoi tornare a giocare.");
        String r = sc.nextLine().toUpperCase();

        while (!r.equals("S") && !r.equals("N")) {
            System.out.println("Non hai inserito una parola valida!");
            System.out.println("\nInserisci S o N:");
            r = sc.nextLine().toUpperCase();
        }

        return r.equals("S");
    }

     /**
     * Questo metodo permette di eseguire un tentativo.
     * @param sc è lo scanner in input
     * @param d è la lunghezza della parola tentata
     * @param end indica se la partita è finita o meno
     * @return una stringa contenente il tentativo
     * corretto inserito dall'utente.
     */
    static String tentativo(final Scanner sc, final int d, final Boolean end) {
        String attpt;
        do {
            System.out.print("\nInserisci il tuo tentativo:\n> ");
            attpt = sc.nextLine().toUpperCase().trim();
            if (attpt.equals("/ESCI")) {
                if (Manager.richiediConferma(sc)) {
                    Wordle.chiudiGioco();
                    return attpt;
                }
            } else if (attpt.equals("/ABBANDONA")) {
                if (Manager.richiediConferma(sc)) {
                    System.out.println("Hai deciso di abbandonare la partita!");
                    System.out.println("\nCi rivediamo presto!");
                    return attpt;
                }
            } else if (!Manager.parolaValida(attpt)) {
                System.out.println("Tentativo non valido.");
                System.out.print("\nLa parola da inserire deve avere lunghezza "
                + d + " e deve\nessere composta"
                + " da soli caratteri dell'alfabeto:\n");
            } else if (attpt.length() < d) {
                System.out.println("Tentativo incompleto.");
                System.out.print("\nLa parola da inserire deve avere lunghezza "
                + d + " e deve\nessere composta"
                + " da soli caratteri dell'alfabeto:\n");
            } else if (attpt.length() > d) {
                System.out.println("Tentativo eccessivo.");
                System.out.print("\nLa parola da inserire deve avere lunghezza "
                + d + " e deve\nessere composta da soli"
                + "caratteri dell'alfabeto:\n");
            }
        } while ((attpt.length() != d || !Manager.parolaValida(attpt)) && !end);

        return attpt;
    }
}
