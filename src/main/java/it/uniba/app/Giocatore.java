package it.uniba.app;

import java.util.Scanner;

public class Giocatore {
    public static void iniziaPartita(){
        Partita p = new Partita(0, Wordle.getMaxTentativi(), Wordle.getParolaSegreta()); 
        System.out.println("Una nuova partita sta iniziando!");
        /** stampare l'help */
        /** stampare griglia vuota */
        Scanner sc = new Scanner(System.in);
        while(p.getTentativiEffettuati() < p.getMaxTentativi()){
            System.out.print("Inserisci il tuo tentativo:\n>");
            String parolaTentata = sc.nextLine();
            while(parolaTentata.length() != p.getParola().length()){
                System.out.print("La parola da inserire deve avere lunghezza " + p.getParola().length() + ":\n>");
                parolaTentata= sc.nextLine();
            }
            /** inserire tentativo*/
            p.setTentativiEffettuati(p.getTentativiEffettuati()+1);
        }
        /** chiudere partita a partita indovinata */

        sc.close();
    }
}
