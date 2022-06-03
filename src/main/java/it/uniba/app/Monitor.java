package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo 'Boundary'.
 * Si occupa di gestire la comunicazione in uscita con l'utente.
 */

public class Monitor {

    /**
     * METODI
    */

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

}
