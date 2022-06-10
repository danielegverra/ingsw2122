package it.uniba.app;

import java.nio.charset.StandardCharsets;
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
     * Questa classe è di tipo 'Boundary'.
     * Entrypoint of the application.
     *
     * @param args argomento a linea di comando
     * @param isArgs booleano che verifica la presenza di 
     * argomenti in input 
     */
    public static void getHelp(final String[] args, boolean isArgs) {
        if (isArgs && (args[0].equals("-h") || args[0].equals("--help"))) {
            Monitor.visualizzaComandi();
            Monitor.visualizzaRegole();
        }
    }

    /**
     * Questa classe è di tipo 'Boundary'.
     * Entrypoint of the application.
     *
     * @param args argomento a linea di comando
     */
    public static void main(final String[] args) {
        //System.out.println(new App().getGreeting());
        boolean isArgs = args.length != 0;
        App.getHelp(args, isArgs);
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
        while (Wordle.isInCorso()) {
            Manager.inputComando(sc);
        }
        sc.close();
    }
}

