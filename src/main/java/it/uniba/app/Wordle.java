package it.uniba.app;

/**
 * Questa classe è di tipo 'Entity' e 'Control'. 
 * Si tratta di una classe Singleton.
 * Si occupa di gestire il nucleo del gioco.
 */
public class Wordle {
    
    /**
     * ATTRIBUTI
    */

    private static Wordle w = new Wordle();
    private String parolaSegreta;
    private int dimensioneParola;
    private int maxTentativi;
    private boolean inCorso;
    

    /**
     * METODI DI ACCESSO
     */

    private Wordle(){
        parolaSegreta = "";
        dimensioneParola = 5;
        maxTentativi = 6;
        inCorso = true;
    }

    /**
     * Il seguente metodo restituisce l'unica istanza della classe singoletto.
     */
    public static Wordle getWordle(){
        return w;
    }
    
    public static String getParolaSegreta(){
        return new String(w.parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        if(p.length() > w.dimensioneParola){
            Monitor.messaggi("parolalunga", getDimensioneParola());
        }else{
            if(p.length() < w.dimensioneParola){
                Monitor.messaggi("parolacorta", getDimensioneParola()); 
            }else{
                if(!Manager.parolaValida(p)){
                    Monitor.messaggi("parolanonvalida"); 
                }else{
                    w.parolaSegreta = p;
                    Monitor.messaggi("parolavalida");
                }
            }
        }
    }

    public static int getDimensioneParola(){
        return w.dimensioneParola;
    }

    public static void setDimensioneParola(int dim){
        w.dimensioneParola = dim;
    }

    public static int getMaxTentativi() {
        return w.maxTentativi;
    }

    public static void setMaxTentativi(int tent) {
        w.maxTentativi = tent;
    }

    public static boolean isInCorso() {
        return w.inCorso;
    }

    public static void setInCorso(boolean inCorso) {
        w.inCorso = inCorso;
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
