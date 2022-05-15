package it.uniba.app;

/**
 * Questa classe è di tipo 'Control'.
 * Si occupa di gestire le azioni che può svolgere il paroliere.
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
            System.out.println("Questo comando puo' essere effettuato solo dal paroliere.");
        }
    }

    /**
     * Questo metodo permette al paroliere di visualizzare la parola
     * segreta corrente.
     */
    public static void  visualizzaParola(){

        if(Wordle.getParolaSegreta() != null && Wordle.getRuoloUtente().equals("PAROLIERE")){
            System.out.println("La parola segreta e' " + Wordle.getParolaSegreta());
        }else if(!Wordle.getRuoloUtente().equals("PAROLIERE")){
            System.out.println("Non puoi visualizzare la parola segreta perche' non sei il paroliere!");
        }
        else{
            System.out.println("Non e' presente una parola segreta");
        }
    }
}
