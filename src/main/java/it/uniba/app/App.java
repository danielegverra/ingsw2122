package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "Hello World!";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        //System.out.println(new App().getGreeting());
        if(args.length != 0 && (args[0].equals("-h") || args[0].equals("--help"))){
            Wordle.visualizzaRegole();
            Wordle.visualizzaComandi();
        }
        Scanner sc = new Scanner(System.in);
        while(Wordle.isInCorso()){
            Manager.inputComando(sc);
        }
        sc.close();
    }
}

