package it.uniba.app;

/**
 * Questa classe è di tipo ' '.
 * Si occupa di 
 */
public class Wordle {
    
    /**
     * ATTRIBUTI
    */

    private static String parolaSegreta;
    private static int dimensioneParola = 5;

    /**
     * METODI
    */

    public static String getParolaSegreta(){
        return new String(parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        parolaSegreta = new String(p);
    }

    public static int getDimensioneParola(){
        return dimensioneParola;
    }

    public static void setDimensioneParola(int dim){
        dimensioneParola = dim;
    }

}
