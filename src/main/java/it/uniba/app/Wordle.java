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
        if(p.length() != dimensioneParola){
            parolaSegreta = new String(p);
        }/*else{
            gestire eccezione !!!
        }*/
    }

    public static int getDimensioneParola(){
        return dimensioneParola;
    }

    public static void setDimensioneParola(int dim){
        dimensioneParola = dim;
    }

    public static void parserWordle(String s[]){
        if(s[0].equals("/nuova")){
            setParolaSegreta(s[1]);
        }
    }

}
