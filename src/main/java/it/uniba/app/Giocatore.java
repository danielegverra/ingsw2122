package it.uniba.app;

import java.util.Scanner;

public class Giocatore {
    public static void iniziaPartita(){
        if(Wordle.getParolaSegreta() != null){            
            Partita p = new Partita(Wordle.getMaxTentativi(), Wordle.getParolaSegreta()); 
            System.out.println("Una nuova partita sta iniziando!");
            Wordle.visualizzaRegole();
            Wordle.visualizzaComandi();
            stampaGriglia(p);
            Scanner sc = new Scanner(System.in);
            while(p.getTentativiEffettuati() < p.getMaxTentativi()){
                System.out.print("Inserisci il tuo tentativo:\n>");
                String parolaTentata = sc.nextLine().toUpperCase();
                while(parolaTentata.length() != p.getParola().length() || !Wordle.parolaValida(parolaTentata)){
                    System.out.print("La parola da inserire deve avere lunghezza " + p.getParola().length() + " e deve\nessere composta da soli caratteri dell'alfabeto:\n>");
                    parolaTentata= sc.nextLine().toUpperCase();
                }
                p.setGrigliaTentativi(p.getTentativiEffettuati(), parolaTentata);
                p.setTentativiEffettuati(p.getTentativiEffettuati()+1);
                stampaGriglia(p);

                if(parolaTentata.compareTo(p.getParola()) == 0){
                    /**uscita dalla partita */
                }

            }
            sc.close();
            /**stampa partita persa se i tentativi sono di più */
        }else{
            //gestisci eccezione
        }
    }

    private static void stampaGriglia(Partita p){
        /** 
         * Stampa del separatore superiore
        */
        System.out.print(" ");
        for (int j = 0; j < p.getParola().length(); j++){
            System.out.print("~~~~~ ");
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
            System.out.print("\n ");
            for (int j = 0; j < p.getParola().length(); j++){
                System.out.print("~~~~~ ");
            }
            System.out.print("\n");
        }

    }

    private static void stampaRigaGriglia(String parolaTentata, String parolaSegreta){
        /**
         * per ogni posizione, 0=grigio 1=verde 2=giallo
        */

        Integer[] coloriLettereTentate = calcolaColori(parolaTentata, parolaSegreta);

        /**Controllo sulla correttezza della singola lettera */
        
        System.out.print("|");
        for(int i = 0; i < parolaSegreta.length(); i++){
            if(coloriLettereTentate[i] == 0){
                System.out.print("  " + ConsoleColors.RED + parolaTentata.charAt(i) + ConsoleColors.RESET + "  |");
            } else if(coloriLettereTentate[i] == 1){
                System.out.print("  " + ConsoleColors.GREEN + parolaTentata.charAt(i) + ConsoleColors.RESET +"  |");
            } else if(coloriLettereTentate[i] == 2){
                System.out.print("  " + ConsoleColors.YELLOW + parolaTentata.charAt(i) + ConsoleColors.RESET + "  |");
            }
        }
    }

    private static void stampaRigaGrigliaVuota(int lunghezza){
        System.out.print("|");
        for(int i = 0; i < lunghezza; i++){
            System.out.print("     |");
        }
    } 

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
