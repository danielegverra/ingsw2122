package it.uniba.app;

import java.util.Scanner;

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
    //private static String ruoloUtente;

    /**
     * METODI
    */

    public static String getParolaSegreta(){
        return new String(parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        if(p.length() == dimensioneParola){
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

    public static void inputComando(){
        System.out.print("\nInserire un comando:\n> ");
        Scanner sc = new Scanner(System.in);
        String c = sc.nextLine();
        String s[] = scannerWordle(c);
        parserWordle(s);
        sc.close();
    }

    
    private static String[] scannerWordle(String c){
        String s[] = c.split(" ");
        if(s[0].charAt(0) != '/'){
            //gestisci eccezione
        }else{
            if(s.length > 2){
                //gestisci eccezione
            }else{
                if(s.length == 1){
                    String ss[] = new String[2];
                    ss[0] = s[0];
                    ss[1] = null;
                    return ss;
                }
            }
        }
        return s;
    }
    
    private static void parserWordle(String s[]){
        if(s[0].equals("/nuova")){
            if(s[1] != null){
                setParolaSegreta(s[1]);
            }else{
                //gestisci eccezione
            }
            
        }
    }

    public static void main(String[] args) {
        inputComando();
        System.out.println(getParolaSegreta());
    }
}
