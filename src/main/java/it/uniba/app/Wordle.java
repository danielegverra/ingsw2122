package it.uniba.app;

/**
 * Questa classe è di tipo 'Entity'.
 * Si occupa di gestire il nucleo del gioco.
 */
public class Wordle {
    
    /**
     * ATTRIBUTI
    */

    private static String parolaSegreta = "";
    private static int dimensioneParola = 5;
    private static int maxTentativi = 6;
    private static boolean inCorso = true;
    

    /**
     * METODI DI ACCESSO
     */
    
    public static String getParolaSegreta(){
        return new String(parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        if(p.length() > dimensioneParola){
            Monitor.messaggi("parolalunga", getDimensioneParola());
        }else{
            if(p.length() < dimensioneParola){
                Monitor.messaggi("parolacorta", getDimensioneParola()); 
            }else{
                if(!Manager.parolaValida(p)){
                    Monitor.messaggi("parolanonvalida"); 
                }else{
                    parolaSegreta = new String(p);
                    Monitor.messaggi("parolavalida");
                }
            }
        }
    }

    public static int getDimensioneParola(){
        return dimensioneParola;
    }

    public static void setDimensioneParola(int dim){
        dimensioneParola = dim;
    }

    public static int getMaxTentativi() {
        return maxTentativi;
    }

    public static void setMaxTentativi(int tent) {
        maxTentativi = tent;
    }

    public static boolean isInCorso() {
        return inCorso;
    }

    public static void setInCorso(boolean inCorso) {
        Wordle.inCorso = inCorso;
    }

    /**
     * METODI
    */

    /**
     * Questo metodo permette di chiudere l'intera sessione di gioco.
     */
    public static void chiudiGioco(){
        setInCorso(false);
        Monitor.messaggi("chiudi");
    }
}
