package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo 'Control'.
 * Si occupa di gestire le azioni che può svolgere il giocatore.
 */
public class Giocatore {

    /**
     * METODI
     */

    /**
     * Questo metodo permette di partecipare ad una partita di Wordle,
     * al suo interno vengono richiesti i tentativi all'utente.
     */
    public static void iniziaPartita(Scanner sc){
        if(!Wordle.getParolaSegreta().equals("")){            
            Partita p = new Partita(Wordle.getMaxTentativi(), Wordle.getParolaSegreta()); 
            Boolean partitaFinita = false;
            System.out.println("\nUna nuova partita sta iniziando!");
            Wordle.visualizzaRegole();
            Wordle.visualizzaComandi();
            stampaGriglia(p);
            while(p.getTentativiEffettuati() < p.getMaxTentativi() && !partitaFinita){
                String parolaTentata;
                parolaTentata= Manager.isTentativo(sc, p, partitaFinita);
                if (parolaTentata.equals("/ESCI")){
                    return;
                }
                else if (parolaTentata.equals("/ABBANDONA")){
                    return;
                }
                else{
                    p.setGrigliaTentativi(p.getTentativiEffettuati(), parolaTentata);
                    p.setTentativiEffettuati(p.getTentativiEffettuati()+1);
                    stampaGriglia(p);
                
                    if(parolaTentata.compareTo(p.getParola()) == 0){
                        partitaFinita = true;
                        System.out.println("Parola segreta indovinata.");
                        System.out.println("Numero tentativi: " + p.getTentativiEffettuati());
                        p.setTentativiEffettuati(p.getTentativiEffettuati()-1);
                    }
                }
            }

            if(p.getTentativiEffettuati() >= p.getMaxTentativi()){
                System.out.println("Hai raggiunto il numero massimo di tentativi!");
                System.out.println("\nLa parola segreta e': " + p.getParola());
            }
        }else if(Wordle.getParolaSegreta().equals("")){
            System.out.println("Parola segreta mancante.");
        } 
    }

    private static void stampaGriglia(Partita p){
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
