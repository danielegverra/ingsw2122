package it.uniba.app;

/**
 * Questa classe è di tipo 'Boundary'.
 * Si occupa di gestire la comunicazione in uscita con l'utente.
 */
public final class Monitor {

    private Monitor() {

    }

    /**
     * Questo metodo restituisce i messaggi per la gestione del gioco.
     * @param msg è un identificativo che serve per selezione
     * il tipo di stampa a video
     */
    public static void messaggi(final String msg) {
        if (msg.equals("parolanonvalida")) {
            System.out.println("Parola segreta non valida.");
        } else if (msg.equals("parolavalida")) {
            System.out.println("OK.");
        } else if (msg.equals("chiudi")) {
            System.out.println("\nSei uscito dal gioco.");
        } else if (msg.equals("nonesisteparola")) {
            System.out.println("Non e' presente una parola segreta.");
        } else if (msg.equals("nuovapartita")) {
            System.out.println("\nUna nuova partita sta iniziando!");
        } else if (msg.equals("parolaindovinata")) {
            System.out.println("Parola segreta indovinata.");
        } else if (msg.equals("numeromaxtentativi")) {
            System.out.println("Hai raggiunto il numero massimo di tentativi!");
        } else if (msg.equals("secondaparola")) {
            System.out.println("Questo comando non ha bisogno"
            + "di una seconda parola.");
        }
    }

      /**
     * Questo metodo restituisce i messaggi per la gestione del gioco
     * a cui è stato applicato l'overloading per ricevere in input
     * un secondo parametro.
     * @param msg è un identificativo che serve per selezione
     * il tipo di stampa a video
     * @param o è un eventuale secondo elemento richiesto
     * nella stampa.
     */
    public static void messaggi(final String msg, final Object o) {
        if (msg.equals("parolalunga")) {
            System.out.println("Parola segreta troppo lunga, deve avere "
            + o + " caratteri.");
        } else if (msg.equals("parolacorta")) {
            System.out.println("Parola segreta troppo corta, deve avere "
            + o + " caratteri.");
        } else if (msg.equals("rivelaparola")) {
            System.out.println("La parola segreta e' " + o);
        } else if (msg.equals("numerotentativi")) {
            System.out.println("Numero tentativi: " + o);
        }
    }

    /**
     * Questo metodo permette di visualizzare la griglia della partita in corso.
     * @param p è un'istanza della classe partita.
     */
    public static void stampaGriglia(final Partita p) {
        /*
         * Stampa del separatore superiore
        */
        System.out.print(ConsoleColors.WHITE + " ");
        for (int j = 0; j < p.getParola().length(); j++) {
            System.out.print(ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET);
        }
        System.out.print("\n");

        System.out.println(ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET);

        System.out.print(ConsoleColors.WHITE + " ");
        for (int j = 0; j < p.getParola().length(); j++) {
            System.out.print(ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET);
        }
        System.out.print("\n");
        /*
         * Stampa delle righe
        */
        for (int i = 0; i < p.getMaxTentativi(); i++) {
            if (p.getGrigliaTentativi(i).compareTo("") != 0) {
                printRow(p.getGrigliaTentativi(i), p.getParola());
            } else {
                printEmptyRow(p.getParola().length());
            }
            /*
             * Stampa del separatore inferiore per ogni riga
             */
            System.out.print("\n" + ConsoleColors.WHITE + " ");
            for (int j = 0; j < p.getParola().length(); j++) {
                System.out.print(ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET);
            }
            System.out.print("\n");
        }

    }

    /**
     * Questo metodo viene richiamato in stampaGriglia
     * per stampare il tentativo appena effettuato.
     * @param pTentata è il tentativo effettuato dall'utente
     * @param pSegreta è la parola da indovinare
     */
    private static void printRow(final String pTentata, final String pSegreta) {
        /*
         * per ogni posizione, 0=grigio 1=verde 2=giallo
        */

        Integer[] coloriLettere = calcColori(pTentata, pSegreta);
        System.out.print(ConsoleColors.WHITE + "|");
        for (int i = 0; i < pSegreta.length(); i++) {
            if (coloriLettere[i] == 0) {
                System.out.print(ConsoleColors.GREY + "  "
                + pTentata.charAt(i) + "  "
                + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET);
            } else if (coloriLettere[i] == 1) {
                System.out.print(ConsoleColors.GREEN + "  "
                + pTentata.charAt(i) + "  "
                + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET);
            } else if (coloriLettere[i] == 2) {
                System.out.print(ConsoleColors.YELLOW + "  "
                + pTentata.charAt(i) + "  "
                + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET);
            }
        }
    }

    /**
     * Questo metodo viene richiamato in stampaGriglia
     * per stampare una riga vuota.
     * @param lunghezza è la lunghezza della riga
     */
    private static void printEmptyRow(final int lunghezza) {
        System.out.print(ConsoleColors.WHITE + "|");
        for (int i = 0; i < lunghezza; i++) {
            System.out.print(ConsoleColors.DARK_WHITE
            + "     " + ConsoleColors.WHITE + "|"
            + ConsoleColors.RESET);
        }
    }

    /**
     * Questo metodo, sulla base del tentativo effettuato dall'utente, riesce
     * a indicare ad esso dei suggerimenti visivi, tramite i colori, per poter
     * indovinare la parola segreta.
     * @param pTent è il tentativo dell'utente
     * @param pSeg è la parola da indovinare
     * @return il metodo restituisce gli interi
     * che rappresentano per ogni lettera
     * il colore da stampare.
     */
    private static Integer[] calcColori(final String pTent, final String pSeg) {
        Integer[] colorSeg = new Integer[pSeg.length()];
        Integer[] colorTent = new Integer[pSeg.length()];

        for (int i = 0; i < colorSeg.length; i++) {
            colorTent[i] = 0;
            colorSeg[i] = 0;
        }
        for (int i = 0; i < colorSeg.length; i++) {
            if (pTent.charAt(i) == pSeg.charAt(i)) {
                colorTent[i] = 1;
                colorSeg[i] = 1;
            }
        }
        for (int i = 0; i < colorSeg.length; i++) {
            if (colorTent[i] == 0) {
                for (int j = 0; j < colorSeg.length && colorTent[i] == 0; j++) {
                    if (colorSeg[j] == 0 && pSeg.charAt(j) == pTent.charAt(i)) {
                        colorTent[i] = 2;
                        colorSeg[j] = 2;
                    }
                }
            }
        }

        return colorTent;
    }

     /**
     * Questo metodo permette di visualizzare a video l'elenco completo dei
     * comandi che l'utente può richiamare.
     * nb. alcuni di essi possono essere effettuati solo in veste di un
     * ruolo specifico.
     */
    static void visualizzaComandi() {
        System.out.println("\nAll'interno del gioco e' possibile"
        + "eseguire i seguenti comandi:");
        System.out.println(" 1. /help: consente di visualizzare i comandi"
        + "disponibili e le regole del gioco");
        System.out.println(" 2. /esci: consente di chiudere "
        + "l'applicazione a seguito di una conferma positiva dell'utente ");

        System.out.println("\nDa paroliere e' possibile eseguire "
        + "i seguenti comandi, ma solo se non vi e' una partita in corso: ");
        System.out.println(" 1. /nuova <parola>: consente di impostare "
        + "una nuova parola segreta, anche durante la sessione di gioco"
        + "senza uscire dall'applicazione");
        System.out.println(" 2. /mostra: consente di visualizzare "
        + "la parola segreta");
        System.out.println("\nDa giocatore e' possibile "
        + "eseguire i seguenti comandi: ");
        System.out.println(" 1. /gioca: consente di visualizzare la"
        + "matrice dei tentativi vuota se nessuna partita e' in corso");
        System.out.println(" 2. /abbandona: consente di abbandonare"
        + "la partita a seguito di una conferma positiva dell'utente\n");
    }

    /**
     * Questo metodo permette di visualizzare a video il regolamento del gioco.
     */
    static void visualizzaRegole() {
        System.out.println("Di seguito vengono mostrate "
        + "le regole e il funzionamento del gioco:\n");
        System.out.println("Lo scopo del gioco e' indovinare "
        + "una parola di cinque lettere "
        + "utilizzando un massimo di sei tentativi.");
        System.out.println("La parola segreta viene decisa "
        + "dal paroliere, che per ogni tentativo restituisce "
        + "tre tipi di indizi utili a restringere "
        + "il cerchio sulla soluzione");
        System.out.println("Ogni lettera nella posizione "
        + "corretta e' evidenziata in verde,");
        System.out.println("ogni lettera presente nella parola"
        + "segreta ma inserita nella posizione "
        + "errata e' evidenziata in giallo");
        System.out.println("e ogni lettera del tutto assente"
        + "dalla soluzione e' evidenziata in grigio.");
    }

}
