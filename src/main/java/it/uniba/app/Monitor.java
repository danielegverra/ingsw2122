package it.uniba.app;

/**
 * Questa classe è di tipo 'Boundary'.
 * Si occupa di gestire la comunicazione in uscita con l'utente.
 */

public class Monitor {

    /**
     * METODI
    */

    /**
     * Questo metodo restituisce i messaggi per la gestione del gioco.
     */
    public static void messaggi(String msg){
        if (msg.equals("parolanonvalida")){
            System.out.println("Parola segreta non valida.");
        }else if (msg.equals("parolavalida")){
            System.out.println("OK.");
        }else if (msg.equals("chiudi")){
            System.out.println("\nSei uscito dal gioco.");
        }else if (msg.equals("nonesisteparola")){
            System.out.println("Non e' presente una parola segreta.");
        }else if (msg.equals("nuovapartita")){
            System.out.println("\nUna nuova partita sta iniziando!");
        }else if (msg.equals("parolaindovinata")){
            System.out.println("Parola segreta indovinata.");
        }else if (msg.equals("numeromaxtentativi")){
            System.out.println("Hai raggiunto il numero massimo di tentativi!");
        }
    } 

    public static void messaggi(String msg, Object o){
        if (msg.equals("parolalunga")){
            System.out.println("Parola segreta troppo lunga, deve avere "+ o + " caratteri." );
        }else if (msg.equals("parolacorta")){
            System.out.println("Parola segreta troppo corta, deve avere "+ o + " caratteri." );
        }else if (msg.equals("rivelaparola")){
            System.out.println("La parola segreta e' " + o);
        }else if (msg.equals("numerotentativi")){
            System.out.println("Numero tentativi: " + o);
        }    
    }

    /**
     * Questo metodo permette di visualizzare la griglia della partita in corso.
     */
    public static void stampaGriglia(Partita p){
        /** 
         * Stampa del separatore superiore
        */
        System.out.print(ConsoleColors.WHITE + " ");
        for (int j = 0; j < p.getParola().length(); j++){
            System.out.print(ConsoleColors.WHITE + "~~~~~ " + ConsoleColors.RESET);
        }
        System.out.print("\n");

        System.out.println(ConsoleColors.WHITE + "|           WORDLE            |" + ConsoleColors.RESET);

        System.out.print(ConsoleColors.WHITE + " ");
        for (int j = 0; j < p.getParola().length(); j++){
            System.out.print(ConsoleColors.WHITE + "~~~~~ " + ConsoleColors.RESET);
        }
        System.out.print("\n");
        /** 
         * Stampa delle righe
        */
        for(int i = 0; i < p.getMaxTentativi(); i++){
            if(p.getGrigliaTentativi(i).compareTo("") != 0){                                       //????
                stampaRigaGriglia(p.getGrigliaTentativi(i), p.getParola());
            } else {
                stampaRigaGrigliaVuota(p.getParola().length());
            }
            /** 
             * Stampa del separatore inferiore per ogni riga
             */
            System.out.print("\n" + ConsoleColors.WHITE + " ");
            for (int j = 0; j < p.getParola().length(); j++){
                System.out.print(ConsoleColors.WHITE + "~~~~~ " + ConsoleColors.RESET);
            }
            System.out.print("\n");
        }

    }

    /**
     * Questo metodo viene richiamato in stampaGriglia per stampare il tentativo appena effettuato.
     */
    private static void stampaRigaGriglia(String parolaTentata, String parolaSegreta){
        /**
         * per ogni posizione, 0=grigio 1=verde 2=giallo
        */

        Integer[] coloriLettereTentate = calcolaColori(parolaTentata, parolaSegreta);       
        System.out.print(ConsoleColors.WHITE + "|");
        for(int i = 0; i < parolaSegreta.length(); i++){
            if(coloriLettereTentate[i] == 0){
                System.out.print(ConsoleColors.GREY + "  " + parolaTentata.charAt(i)+ "  " + ConsoleColors.WHITE + "|" + ConsoleColors.RESET);
            } else if(coloriLettereTentate[i] == 1){
                System.out.print(ConsoleColors.GREEN + "  " + parolaTentata.charAt(i) + "  "+ ConsoleColors.WHITE + "|" + ConsoleColors.RESET);
            } else if(coloriLettereTentate[i] == 2){
                System.out.print(ConsoleColors.YELLOW + "  " + parolaTentata.charAt(i) + "  " + ConsoleColors.WHITE + "|" + ConsoleColors.RESET);
            }
        }
    }

    /**
     * Questo metodo viene richiamato in stampaGriglia per stampare una riga vuota.
     */
    private static void stampaRigaGrigliaVuota(int lunghezza){
        System.out.print(ConsoleColors.WHITE + "|");
        for(int i = 0; i < lunghezza; i++){
            System.out.print(ConsoleColors.DARK_WHITE + "     " + ConsoleColors.WHITE + "|" + ConsoleColors.RESET);
        }
    } 

    /**
     * Questo metodo, sulla base del tentativo effettuato dall'utente, riesce
     * a indicare ad esso dei suggerimenti visivi, tramite i colori, per poter 
     * indovinare la parola segreta.
     */
    private static Integer[] calcolaColori(String parolaTentata, String parolaSegreta){
        Integer[] coloriLettereSegrete = new Integer[parolaSegreta.length()];
        Integer[] coloriLettereTentate = new Integer[parolaSegreta.length()];

        for(int i = 0; i < coloriLettereSegrete.length; i++){
            coloriLettereTentate[i] = 0;
            coloriLettereSegrete[i] = 0;
        }
        for(int i = 0; i < coloriLettereSegrete.length; i++){
            if(parolaTentata.charAt(i) == parolaSegreta.charAt(i)){
                coloriLettereTentate[i] = 1;
                coloriLettereSegrete[i] = 1;
            }
        }
        for(int i = 0; i < coloriLettereSegrete.length; i++){
            if(coloriLettereTentate[i] == 0){
                for(int j = 0; j < coloriLettereSegrete.length && coloriLettereTentate[i] == 0; j++){
                    if(coloriLettereSegrete[j] == 0 && parolaSegreta.charAt(j) == parolaTentata.charAt(i)){
                        coloriLettereTentate[i] = 2;
                        coloriLettereSegrete[j] = 2;
                    }
                }
            }
        }

        return coloriLettereTentate;
    }

     /**
     * Questo metodo permette di visualizzare a video l'elenco completo dei
     * comandi che l'utente può richiamare.
     * nb. alcuni di essi possono essere effettuati solo in veste di un 
     * ruolo specifico.
     */
    static void visualizzaComandi(){
        System.out.println("\nAll'interno del gioco e' possibile eseguire i seguenti comandi:");
        System.out.println(" 1. /help: consente di visualizzare i comandi disponibili e le regole del gioco");
        System.out.println(" 2. /esci: consente di chiudere l'applicazione a seguito di una conferma positiva dell'utente ");

        System.out.println("\nDa paroliere e' possibile eseguire i seguenti comandi, ma solo se non vi e' una partita in corso: ");
        System.out.println(" 1. /nuova <parola>: consente di impostare una nuova parola segreta, anche durante la sessione di gioco senza uscire dall'applicazione");
        System.out.println(" 2. /mostra: consente di visualizzare la parola segreta");
       
        System.out.println("\nDa giocatore e' possibile eseguire i seguenti comandi: ");
        System.out.println(" 1. /gioca: consente di visualizzare la matrice dei tentativi vuota se nessuna partita e' in corso");
        System.out.println(" 2. /abbandona: consente di abbandonare la partita a seguito di una conferma positiva dell'utente\n");
    }

    /**
     * Questo metodo permette di visualizzare a video il regolamento del gioco.
     */
    static void visualizzaRegole(){
        System.out.println("Di seguito vengono mostrate le regole e il funzionamento del gioco:\n");
        System.out.println("Lo scopo del gioco e' indovinare una parola di cinque lettere utilizzando un massimo di sei tentativi.");
        System.out.println("La parola segreta viene decisa dal paroliere, che per ogni tentativo restituisce tre tipi di indizi utili a restringere il cerchio sulla soluzione");
        System.out.println("Ogni lettera nella posizione corretta e' evidenziata in verde,");
        System.out.println("ogni lettera presente nella parola segreta ma inserita nella posizione errata e' evidenziata in giallo");
        System.out.println("e ogni lettera del tutto assente dalla soluzione e' evidenziata in grigio.");
    }

}
