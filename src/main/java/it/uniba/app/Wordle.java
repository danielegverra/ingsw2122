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
            System.out.println("Parola segreta troppo lunga, deve avere "+ getDimensioneParola() + " caratteri." );
        }else{
            if(p.length() < dimensioneParola){
                System.out.println("Parola segreta troppo corta, deve avere "+ getDimensioneParola() + " caratteri." );
            }else{
                if(!Manager.parolaValida(p)){
                     System.out.println("Parola segreta non valida.");
                }else{
                    parolaSegreta = new String(p);
                    System.out.println("OK.");
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
        System.out.println("\nSei uscito dal gioco.");
    }
}
