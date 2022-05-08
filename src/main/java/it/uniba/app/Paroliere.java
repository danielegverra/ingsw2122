package it.uniba.app;

/**
 * Questa classe è di tipo ' '.
 * Si occupa di gestire tutte le operazioni che il paroliere può
 * compiere.
 */
public class Paroliere {

    /**
     * METODI
     */
    
    /**
     * Questo metodo permette al paroliere di cambiare la parola segreta
     * da indovinare.
     */
    public static void impostaParolaSegreta(String s){
        if(Wordle.getRuoloUtente().equals("PAROLIERE")){
            Wordle.setParolaSegreta(s);
        }else{
            //gestisci eccezione 'ruolo'
        }
    }
}
