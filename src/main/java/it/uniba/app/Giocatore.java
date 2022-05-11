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
                while(parolaTentata.length() != p.getParola().length()){
                    System.out.print("La parola da inserire deve avere lunghezza " + p.getParola().length() + ":\n>");
                    parolaTentata= sc.nextLine().toUpperCase();
                }
                p.setGrigliaTentativi(p.getTentativiEffettuati(), parolaTentata);
                p.setTentativiEffettuati(p.getTentativiEffettuati()+1);
                stampaGriglia(p);

                /**controllo se la parola è giusta*/ 

            }
            sc.close();
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
                /**stampare riga */
            } else {
                /**stampare riga vuota */
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


}
