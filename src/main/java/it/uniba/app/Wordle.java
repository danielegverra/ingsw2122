package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo ' '.
 * Si occupa di gestire il nucleo del gioco.
 */
public class Wordle {
    
    /**
     * ATTRIBUTI
    */

    private static String parolaSegreta;
    private static int dimensioneParola = 5;
    private static String ruoloUtente;
    private static int maxTentativi;

    /**
     * METODI DI ACCESSO
     */
    
    public static String getParolaSegreta(){
        return new String(parolaSegreta);
    }
    
    public static void setParolaSegreta(String p){
        if(p.length() > dimensioneParola){
            //gestisci eccezione '>'
        }else{
            if(p.length() < dimensioneParola){
                //gestisci eccezione '<'
            }else{
                if(!parolaValida(p)){
                    //gestisci eccezione 'non valida'
                }else{
                    parolaSegreta = new String(p);
                    System.out.println("OK");
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

    public static String getRuoloUtente(){
        return new String(ruoloUtente);
    }

    public static void setRuoloUtente(String s){
        ruoloUtente = new String(s);
    }

    public static int getMaxTentativi() {
        return maxTentativi;
    }

    public static void setMaxTentativi(int tent) {
        maxTentativi = tent;
    }

    /**
     * METODI
    */

    /**
     * Questo metodo controlla che nella parola non ci siano
     * caratteri che non appartengono all'alfabeto.
     */
    private static boolean parolaValida(String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) > 90 || s.charAt(i) < 65){
                return false;
            }
        }
        return true;
    }

    /**
     * Questo metodo permette di ricevere il comando dell'utente
     * da tastiera, ne controlla la sintassi (scanner) e richiama
     * il metodo adeguato (parser).
     */
    public static void inputComando(){
        System.out.print("\nInserire un comando:\n> ");
        Scanner sc = new Scanner(System.in);
        String c = sc.nextLine().toUpperCase();
        String s[] = scannerWordle(c);
        parserWordle(s);
        sc.close();
    }
    
    /**
     * Questo metodo controlla la validità sintattica del
     * comando dato in input.
     */
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
    
    /**
     * Questo metodo associa ad ogni comando il rispettivo metodo
     * da richiamare.
     */
    private static void parserWordle(String s[]){
        if(s[0].equals("/NUOVA")){
            if(s[1] != null){
                Paroliere.impostaParolaSegreta(s[1]);
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
