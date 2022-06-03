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
            Monitor.messaggi("nuovapartita");
            Monitor.visualizzaRegole();
            Monitor.visualizzaComandi();
            Monitor.stampaGriglia(p);
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
                    Monitor.stampaGriglia(p);
                
                    if(parolaTentata.compareTo(p.getParola()) == 0){
                        partitaFinita = true;
                        Monitor.messaggi("parolaindovinata");
                        Monitor.messaggi("numerotentativi", p.getTentativiEffettuati());
                        p.setTentativiEffettuati(p.getTentativiEffettuati()-1);
                    }
                }
            }

            if(p.getTentativiEffettuati() >= p.getMaxTentativi()){
                Monitor.messaggi("numeromaxtentativi");
                Monitor.messaggi("rivelaparola", Wordle.getParolaSegreta());
            }
        }else if(Wordle.getParolaSegreta().equals("")){
            Monitor.messaggi("nonesisteparola");
        } 
    }
  
}
