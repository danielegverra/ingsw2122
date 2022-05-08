package it.uniba.app;

/**
 * Questa classe è di tipo ' '.
 * Si occupa di 
 */
public class Paroliere {

    /**
     * ATTRIBUTI
     */


    /**
     * METODI
     */
    
    public static void impostaParolaSegreta(String s){
        if(Wordle.getRuoloUtente().equals("PAROLIERE")){
            Wordle.setParolaSegreta(s);
        }else{
            //gestisci eccezione 'ruolo'
        }
    }
}
